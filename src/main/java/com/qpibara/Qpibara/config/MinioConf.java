package com.qpibara.Qpibara.config;

import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
@AllArgsConstructor
public class MinioConf {

    private final MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() throws NoSuchAlgorithmException, KeyManagementException {
        MinioClient client = MinioClient.builder()
                .endpoint(minioProperties.getMinioUrl())
                .credentials(minioProperties.getMinioName(), minioProperties.getMinioPassword())
                .build();

        client.ignoreCertCheck();
        return client;
    }
}
