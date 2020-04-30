package com.ohap.monitor.owner.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.Arrays;

import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestAccountController extends TestAbstractController {

    @Test
    @DisplayName("회원 정보 조회")
    public void getAccount() throws Exception {
        mockMvc.perform(
                get("/api/v3/account")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .header(HubHttpHeaders.AUTHORIZATION, getAuthorizationToken())
                        .header(HubHttpHeaders.APP_PLATFORM, "ios")
                        .header(HubHttpHeaders.APP_VERSION, "3.0.0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(documentationResultHandler.document(
                        requestHeaders(
                                getRequestHeaders(true, true, false)
                        ),
                        responseFields(
                                getResponseFields(JsonFieldType.OBJECT, false,
                                        fieldWithPath("result.seq").type(JsonFieldType.NUMBER).description("순번"),
                                        fieldWithPath("result.id").type(JsonFieldType.STRING).description("아이디"),
                                        fieldWithPath("result.name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("result.phone").type(JsonFieldType.STRING).description("휴대전화번호"),
                                        fieldWithPath("result.type").type(JsonFieldType.NUMBER).description("유저형식").attributes(getFormat("0 : 개인회원, 1 : 법인회원")),
                                        fieldWithPath("result.typeName").type(JsonFieldType.STRING).description("유저형식이름"),
                                        fieldWithPath("result.corpType").type(JsonFieldType.NUMBER).description("법인형식").attributes(getFormat("0 : 임대사업, 1 : 임대관리업, 2 : 시행/시공업, 3 : 중개업")).optional(),
                                        fieldWithPath("result.corpTypeName").type(JsonFieldType.STRING).description("법인형식이름").optional(),
                                        fieldWithPath("result.corpName").type(JsonFieldType.STRING).description("법인이름").optional(),
                                        fieldWithPath("result.corpCeoName").type(JsonFieldType.STRING).description("법인대표자이름").optional(),
                                        fieldWithPath("result.businessClass").type(JsonFieldType.NUMBER).description("사업자형식").attributes(getFormat("0 : 개인사업자, 1 : 법인사업자")).optional(),
                                        fieldWithPath("result.businessClassName").type(JsonFieldType.STRING).description("사업자형식이름").optional(),
                                        fieldWithPath("result.isCorpCertReceipt").type(JsonFieldType.BOOLEAN).description("법인승인접수유무"),
                                        fieldWithPath("result.corpCertReceiptName").type(JsonFieldType.STRING).description("법인승인접수이름"),
                                        fieldWithPath("result.isCorpConversionReceipt").type(JsonFieldType.BOOLEAN).description("법인전환접수유무"),
                                        fieldWithPath("result.corpConversionReceiptName").type(JsonFieldType.STRING).description("법인전환접수이름"),
                                        fieldWithPath("result.isUsersWithdrawReceipt").type(JsonFieldType.BOOLEAN).description("회원탈퇴접수유무"),
                                        fieldWithPath("result.usersWithdrawReceiptName").type(JsonFieldType.STRING).description("회원탈퇴접수이름"),
                                        fieldWithPath("result.corpSeq").type(JsonFieldType.NUMBER).description("회사순번").optional(),
                                        fieldWithPath("result.isMaster").type(JsonFieldType.BOOLEAN).description("계정관리자유무")
                                )
                        )
                ));
    }
}
