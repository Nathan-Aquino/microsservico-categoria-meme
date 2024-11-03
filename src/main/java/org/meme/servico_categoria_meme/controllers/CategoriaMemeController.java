package org.meme.servico_categoria_meme.controllers;

import java.text.ParseException;
import java.util.List;

import org.meme.servico_categoria_meme.dtos.CategoriaMemeDTO;
import org.meme.servico_categoria_meme.entities.CategoriaMemeEntity;
import org.meme.servico_categoria_meme.exceptions.UsuarioNaoEncontradoException;
import org.meme.servico_categoria_meme.services.CategoriaMemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria-meme")
public class CategoriaMemeController {

    private Logger logger = LoggerFactory.getLogger(CategoriaMemeController.class);

    @Autowired
    private CategoriaMemeService service;

    @GetMapping
    public List<CategoriaMemeEntity> getAllCatMeme () {
        logger.info("Requisitando todas categorias de memes");
        List<CategoriaMemeEntity> response = service.getAllCatMeme();
        logger.info("Total de categorias encontradas: {}", response.size());
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaMemeEntity> getCatMemeByID (@PathVariable Long id) {
        logger.info("Requisitando usuario com ID: {}", id);
        CategoriaMemeEntity categoriaMemeEntity = service.getCatMemeById(id);

        if (categoriaMemeEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaMemeEntity);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<?> postCategoriaMeme(@RequestBody CategoriaMemeDTO categoriaMemeDTO) throws ParseException {
        try {
            CategoriaMemeEntity categoriaMemeEntity = service.saveCatMeme(categoriaMemeDTO);
            logger.info("Categoria de id {} salva com sucesso", categoriaMemeEntity.getId());
            return ResponseEntity.status(HttpStatus.OK).body(categoriaMemeEntity);
        } catch (UsuarioNaoEncontradoException e) {
            logger.warn("Erro, usuario nao existe!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
}
