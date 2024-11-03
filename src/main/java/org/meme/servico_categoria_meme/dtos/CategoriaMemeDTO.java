package org.meme.servico_categoria_meme.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaMemeDTO {
    public Long id;
    public String nome;
    public String descricao;
    public String dataCadastro;
    public String usuarioId;
}
