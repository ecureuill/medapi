package ecureuill.medapi.infra.security;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public class CORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                      .allowedOrigins("*")
                      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                      .allowCredentials(true)
                      .maxAge(3600);
    }
}
