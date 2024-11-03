package org.meme.servico_categoria_meme.clients;

import org.meme.servico_categoria_meme.exceptions.UsuarioNaoEncontradoException;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecode implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new UsuarioNaoEncontradoException();
        }
        return FeignException.errorStatus(methodKey, response);
    }
}