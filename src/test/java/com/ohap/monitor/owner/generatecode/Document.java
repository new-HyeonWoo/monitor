package com.ohap.monitor.owner.generatecode;

import com.ohap.monitor.config.auth.dto.annotation.GenerateCode;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Document {
    private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();
    private static final MetadataReaderFactory METADATA_READER_FACTORY = new SimpleMetadataReaderFactory();
    private static final Resource[] EMPTY_RESOURCES = new Resource[]{};

    Generator generator;

    private String directory;

    public Document(Generator generator, String directory) {
        this.generator = generator;
        this.directory = directory;
    }

    public void write(String packagePath, String fileName, String title) throws IOException {
        StringBuilder sb = new StringBuilder(generator.getStart())
                .append(generator.getTitle(title, packagePath));

        getResourceMetadataReaders(String.format("classpath:%s/*.class", packagePath)).stream()
                .filter(reader -> reader.getAnnotationMetadata().hasAnnotation(GenerateCode.class.getName()))
                .map(reader -> {
                    try {
                        return Class.forName(reader.getClassMetadata().getClassName());
                    } catch (Exception e) {
                        throw new IllegalStateException(String.format("코드 리소스 %s 가 없습니다.", reader.getClassMetadata().getClassName()), e);
                    }
                })
                .filter(Class::isEnum)
                .forEach(clazz -> {
                    GenerateCode generateCode = clazz.getAnnotation(GenerateCode.class);

                    sb.append(generator.getTableHeader(generateCode.value(), clazz.getSimpleName()));

                    for (Object value : clazz.getEnumConstants()) {
                        try {
                            Field name = ReflectionUtils.findField(value.getClass(), generateCode.name());
                            Field type = ReflectionUtils.findField(value.getClass(), generateCode.type());
                            name.setAccessible(true);
                            type.setAccessible(true);
                            sb.append(generator.getTableRow(String.valueOf(name.get(value)), String.valueOf(type.get(value))));
                        } catch (Exception e) {
                            throw new IllegalStateException(String.format("코드 리소스 %s 필드 %s, %s 값을 가져올수 없습니다.", clazz.getName(), generateCode.name(), generateCode.type()), e);
                        }
                    }

                    sb.append(generator.getTableBottom());
                });

        sb.append(generator.getEnd());

        Path path = Paths.get(directory);

        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }

        Files.write(path.resolve(String.format("%s.%s", fileName, generator.getFileExtension())), sb.toString().getBytes());
    }

    private List<MetadataReader> getResourceMetadataReaders(String locationPattern) throws IOException {
        return Arrays.stream(Optional.of(RESOURCE_PATTERN_RESOLVER.getResources(locationPattern)).orElseGet(() -> EMPTY_RESOURCES))
                .map(resource -> {
                    try {
                        return METADATA_READER_FACTORY.getMetadataReader(resource);
                    } catch (Exception e) {
                        throw new IllegalStateException("코드 리소스 정보 가져오는중 오류가 발생하였습니다.", e);
                    }
                }).collect(Collectors.toList());
    }
}
