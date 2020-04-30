package com.ohap.monitor.owner;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.extension.*;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CustomRestDocumentationExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
    private static final String SNIPPET_OUTPUT_DIRECTORY = "src/test/resources/api/snippet";
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        this.getDelegate(context).beforeTest(context.getRequiredTestClass(),
                context.getRequiredTestMethod().getName());
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        this.getDelegate(context).afterTest();
        deleteIfException(context);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) {
        if (isTestMethodContext(extensionContext)) {
            return RestDocumentationContextProvider.class
                    .isAssignableFrom(parameterContext.getParameter().getType());
        }
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext context) {
        return (RestDocumentationContextProvider) () -> getDelegate(context)
                .beforeOperation();
    }

    private boolean isTestMethodContext(ExtensionContext context) {
        return context.getTestClass().isPresent() && context.getTestMethod().isPresent();
    }

    private ManualRestDocumentation getDelegate(ExtensionContext context) {
        ExtensionContext.Namespace namespace = ExtensionContext.Namespace.create(getClass(), context.getUniqueId());
        return context.getStore(namespace).getOrComputeIfAbsent(
                ManualRestDocumentation.class, (key) -> new ManualRestDocumentation(SNIPPET_OUTPUT_DIRECTORY),
                ManualRestDocumentation.class);
    }

    private void deleteIfException(ExtensionContext context) {
        context.getExecutionException().ifPresent(throwable -> {
            Path path = Paths.get(new StringBuilder(SNIPPET_OUTPUT_DIRECTORY).append("/")
                    .append(toCamelCase(context.getRequiredTestClass().getSimpleName())).append("/")
                    .append(toCamelCase(context.getRequiredTestMethod().getName())).toString());
            if (Files.exists(path)) {
                try {
                    Files.walk(path)
                            .sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(File::delete);
                } catch (IOException e) {
                    throw new RuntimeException("오류 파일 삭제에 실패하였습니다", e);
                }
            }
        });
    }

    private String toCamelCase(String name) {
        return Arrays.stream(StringUtils.splitByCharacterTypeCamelCase(name)).map(String::toLowerCase).collect(Collectors.joining("-"));
    }
}
