package com.crudjava.crudspring.repository;

import com.crudjava.crudspring.model.Colaborador;
import com.crudjava.crudspring.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    boolean existsByCnpj(String cnpj);

}
