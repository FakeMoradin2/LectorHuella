package com.chong.lectorDeHuella.cors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsSettings
{

        @Bean
        public WebMvcConfigurer corsConfigurer()
        {
            return new WebMvcConfigurer()
            {
                @Override
                public void addCorsMappings(CorsRegistry registry)
                {
                    registry.addMapping("/**")
                            .allowedOrigins("*") // tu frontend local
                            .allowedMethods("GET","POST","PUT","DELETE","OPTIONS");
                }
            };
    }

}
