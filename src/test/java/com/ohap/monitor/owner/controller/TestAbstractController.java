package com.ohap.monitor.owner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohap.monitor.owner.CustomRestDocumentationExtension;
import com.ohap.monitor.owner.MonitorApplicationTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Attributes.Attribute;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@ExtendWith({CustomRestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest(classes = MonitorApplicationTests.class)
public abstract class TestAbstractController {
	protected MockMvc mockMvc;

	protected RestDocumentationResultHandler documentationResultHandler;

	protected class HubHttpHeaders extends HttpHeaders {
		public static final String SAFE_AUTH_CONFIRM = "Safe-Auth-Confirm";
		public static final String APP_PLATFORM = "App-Platform";
		public static final String APP_VERSION = "App-Version";
	}

	@BeforeEach
	public void setUp(WebApplicationContext webAppContext, RestDocumentationContextProvider restDocumentation) {
		this.documentationResultHandler = document("{class-name}/{method-name}"
				, preprocessRequest(modifyUris().host("http").host("test-dabang-owner-v3.dabangapp.com").removePort())
				, preprocessResponse(prettyPrint()));
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
				.apply(documentationConfiguration(restDocumentation)).alwaysDo(documentationResultHandler).build();
	}

	protected Attribute getFormat(String value) {
		return new Attribute("format", value);
	}

	protected HeaderDescriptor[] getRequestHeaders(boolean isGet, boolean isAuthorization, boolean isSafeAuthConfirm) {
		return getRequestHeaders(isGet, isAuthorization, isSafeAuthConfirm, false, true);
	}

	protected HeaderDescriptor[] getRequestHeaders(boolean isGet, boolean isAuthorization, boolean isSafeAuthConfirm, boolean isMultipart, boolean isAppInfo) {
		List<HeaderDescriptor> headerDescriptors = new ArrayList<>();

		if (!isGet) {
			if (isMultipart) {
				headerDescriptors.add(headerWithName(HttpHeaders.CONTENT_TYPE).description("본문형식").attributes(getFormat(MediaType.MULTIPART_FORM_DATA_VALUE)));
			} else {
				headerDescriptors.add(headerWithName(HttpHeaders.CONTENT_TYPE).description("본문형식").attributes(getFormat(MediaType.APPLICATION_JSON_UTF8_VALUE)));
			}
		}

		headerDescriptors.add(headerWithName(HttpHeaders.ACCEPT).description("응답형식").attributes(getFormat(MediaType.APPLICATION_JSON_UTF8_VALUE)));

		if (isAuthorization) {
			headerDescriptors.add(headerWithName(HttpHeaders.AUTHORIZATION).description("인증토큰").attributes(getFormat("Bearer 로그인 시 발급되는 토큰")));
		}

		if (isSafeAuthConfirm) {
			headerDescriptors.add(headerWithName(HubHttpHeaders.SAFE_AUTH_CONFIRM).description("본인인증확인정보"));
		}

		if (isAppInfo) {
			headerDescriptors.add(headerWithName(HubHttpHeaders.APP_PLATFORM).description("기기 앱 플랫폼").attributes(getFormat("ios : 애플, android : 안드로이드")));
			headerDescriptors.add(headerWithName(HubHttpHeaders.APP_VERSION).description("기기 앱 버전").attributes(getFormat("2.0.0 : 애플, 2 : 안드로이드")));
		}

		return headerDescriptors.toArray(new HeaderDescriptor[] {});
	}

	protected FieldDescriptor[] getResponseFields(JsonFieldType resultType, boolean isResultOptional, FieldDescriptor... resultFields) {
		return getResponseFields(resultType, isResultOptional, false, resultFields);
	}

	protected FieldDescriptor[] getResponseFieldsWithPagination(FieldDescriptor... resultFields) {
		return getResponseFields(JsonFieldType.OBJECT, false, true, resultFields);
	}

	private FieldDescriptor[] getResponseFields(JsonFieldType resultType, boolean isResultOptional, boolean isPagination, FieldDescriptor... resultFields) {
		List<FieldDescriptor> fieldDescriptors = new ArrayList<>();

		FieldDescriptor resultField = fieldWithPath((JsonFieldType.ARRAY.equals(resultType) ?  "result[]" : "result")).type(resultType).description("결과");

		if (isResultOptional) {
			resultField.optional();
		}

		fieldDescriptors.add(fieldWithPath("code").type(JsonFieldType.NUMBER).description("코드"));
		fieldDescriptors.add(fieldWithPath("msg").type(JsonFieldType.STRING).description("메세지"));
		fieldDescriptors.add(resultField);
		fieldDescriptors.addAll(Arrays.asList(resultFields));
		if (isPagination) {
			fieldDescriptors.add(fieldWithPath("result.count").type(JsonFieldType.NUMBER).description("전체 수"));
			fieldDescriptors.add(fieldWithPath("result.page").type(JsonFieldType.NUMBER).description("페이지"));
			fieldDescriptors.add(fieldWithPath("result.limit").type(JsonFieldType.NUMBER).description("화면당 개체 수"));
			fieldDescriptors.add(fieldWithPath("result.isMore").type(JsonFieldType.BOOLEAN).description("다음페이지 유무"));
		}
		fieldDescriptors.add(fieldWithPath("responseTime").type(JsonFieldType.STRING).description("응답시간").attributes(getFormat("yyyy-MM-dd HH:mm:ss")));

		return fieldDescriptors.toArray(new FieldDescriptor[] {});
	}

	protected String getAuthorizationToken() {
		return "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvd25lcl9hcGlfdG9rZW4iLCJpc3MiOiJvd25lciIsImlhdCI6MzEzMzYzMjA5NTQwLCJpZHgiOjExNTA5NCwibmFtZSI6Iuq5gOuzke2biCIsInR5cGUiOjEsImF1ZCI6ImJoa2ltMkBzdGF0aW9uMy5jby5rciIsImV4cCI6MzEzMzYzMjA5NTQwfQ.4Xy2Q_8CQ57PqHvSj8D-qXNv5Qt6avggUqGkP3t3zMc";
	}
}
