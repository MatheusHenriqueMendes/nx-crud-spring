package com.crudjava.crudspring.model;


import javax.persistence.*;

import com.crudjava.crudspring.dto.ColaboradorDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String observacao;

    @Column(length = 11, nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    public ColaboradorDTO toDTO() {
        ColaboradorDTO dto = new ColaboradorDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setObservacao(observacao);
        dto.setCpf(cpf);
        return dto;
    }

    public List<ColaboradorDTO> toListDTO(List<Colaborador> entity) {
        if (entity == null) {
            return null;
        }
        List<ColaboradorDTO> list = new ArrayList<ColaboradorDTO>(entity.size());
        for (Colaborador colaborador : entity) {
            list.add(colaborador.toDTO());
        }
        return list;
    }

    public List<Colaborador> toListEntity(List<ColaboradorDTO> dto) {
        if (dto == null) {
            return null;
        }
        List<Colaborador> list = new ArrayList<Colaborador>(dto.size());
        for (ColaboradorDTO colaboradorDTO : dto) {
            list.add(colaboradorDTO.toEntity());
        }
        return list;
    }

}
