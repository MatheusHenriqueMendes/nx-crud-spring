package com.crudjava.crudspring.model;


import com.crudjava.crudspring.dto.EmpresaDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String razao_social;

    @Column(length = 50, nullable = false)
    private String nome_fantasia;

    @Column(length = 14, nullable = false)
    private String cnpj;

    @Column(length = 14, nullable = false)
    private String status;

    @OneToMany (cascade = CascadeType.PERSIST)
    private List<Colaborador> colaboradores;

    public EmpresaDTO toDTO() {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(id);
        dto.setRazao_social(razao_social);
        dto.setNome_fantasia(nome_fantasia);
        dto.setCnpj(cnpj);
        dto.setStatus(status);
        dto.setColaboradores(new Colaborador().toListDTO(colaboradores));
        return dto;
    }





}
