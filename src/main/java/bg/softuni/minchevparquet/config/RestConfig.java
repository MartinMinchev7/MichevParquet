package bg.softuni.minchevparquet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient parquetRestClient(ParquetApiConfig parquetApiConfig) {
        return RestClient.builder()
                .baseUrl(parquetApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
