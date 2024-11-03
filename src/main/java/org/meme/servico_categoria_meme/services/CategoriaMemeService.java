package org.meme.servico_categoria_meme.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.meme.servico_categoria_meme.clients.UsuarioClient;
import org.meme.servico_categoria_meme.dtos.CategoriaMemeDTO;
import org.meme.servico_categoria_meme.entities.CategoriaMemeEntity;
import org.meme.servico_categoria_meme.exceptions.UsuarioNaoEncontradoException;
import org.meme.servico_categoria_meme.repositories.CategoriaMemeRepositorie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMemeService {
    @Autowired
    private CategoriaMemeRepositorie repository;

    private Logger logger = LoggerFactory.getLogger(CategoriaMemeService.class);

    @Autowired
    private UsuarioClient client;

    public List<CategoriaMemeEntity> getAllCatMeme () {
        return repository.findAll();
    }

    public CategoriaMemeEntity getCatMemeById (Long id) {
        return repository.findById(id).orElseGet(() -> null);
    }

    public CategoriaMemeEntity saveCatMeme (CategoriaMemeDTO categoriaMeme) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        Long usuarioId = Long.parseLong(categoriaMeme.getUsuarioId());

        logger.info("Conferindo usuario de id {}",usuarioId);
        
        ResponseEntity<Void> response = client.verificarUsuarioPorId(usuarioId);

        if (response.getStatusCode() == HttpStatus.NOT_FOUND){
            logger.warn("Usuario de id {} nao encontrado!");
            throw new UsuarioNaoEncontradoException();
        }

        Date dataSql = new Date (formato.parse(categoriaMeme.getDataCadastro()).getTime());

        CategoriaMemeEntity categoriaMemeEntity = new CategoriaMemeEntity();

        categoriaMemeEntity.setNome(categoriaMeme.getNome());
        categoriaMemeEntity.setDescricao(categoriaMeme.getDescricao());
        categoriaMemeEntity.setDataCadastro(dataSql);
        categoriaMemeEntity.setUsuarioId(usuarioId);

        return repository.save(categoriaMemeEntity);
    }
}
