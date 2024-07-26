package bg.softuni.minchevparquet.config;

import bg.softuni.minchevparquet.service.JwtService;
import bg.softuni.minchevparquet.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Configuration
public class RestConfig {

    @Bean
    public RestClient parquetRestClient(ParquetApiConfig parquetApiConfig) {
        return RestClient.builder()
                .baseUrl(parquetApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public ClientHttpRequestInterceptor requestInterceptor(UserService userService,
                                                           JwtService jwtService) {

        return (request, body, execution) -> {
            userService
                    .getCurrentUser()
                    .ifPresent(mpud -> {
                        String bearerToken = jwtService.generateJwtToken(
                                mpud.getUuid().toString(),
                                Map.of(
                                        "roles",
                                        mpud.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                                )
                        );
                        request.getHeaders().setBearerAuth(bearerToken);
                    });

            return execution.execute(request, body);
        };

    }
}
