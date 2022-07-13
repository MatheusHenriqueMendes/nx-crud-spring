package com.crudjava.crudspring.dto;

import com.crudjava.crudspring.model.Colaborador;
import com.crudjava.crudspring.model.Empresa;
import lombok.Data;

import java.util.List;

@Data
public class EmpresaDTO {
  private Long id;
  private String nome_fantasia;
  private String razao_social;
  private String cnpj;
  private String status;

  private List<ColaboradorDTO> colaboradores;
  public Empresa toEntity() {
    Empresa entity = new Empresa();
    entity.setRazao_social(razao_social);
    entity.setNome_fantasia(nome_fantasia);
    entity.setCnpj(cnpj);
    entity.setStatus(status);
    entity.setColaboradores(new Colaborador().toListEntity(colaboradores));
    return entity;
  }

}
