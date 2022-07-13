package com.crudjava.crudspring.repository;

import com.crudjava.crudspring.model.Colaborador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    boolean existsByCpf(String cpf);

}
