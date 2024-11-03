package org.meme.servico_categoria_meme.configs;

import org.meme.servico_categoria_meme.clients.CustomErrorDecode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecode();
    }
}
