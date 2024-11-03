package org.meme.servico_categoria_meme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicoCategoriaMemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoCategoriaMemeApplication.class, args);
	}

}
