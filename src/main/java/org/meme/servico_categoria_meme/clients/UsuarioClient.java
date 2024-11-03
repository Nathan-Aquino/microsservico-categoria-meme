package org.meme.servico_categoria_meme.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servico-usuario", url = "http://localhost:8080/usuario")
public interface UsuarioClient {
    @GetMapping("/{id}")
    ResponseEntity<Void> verificarUsuarioPorId(@PathVariable("id") Long id);
}
