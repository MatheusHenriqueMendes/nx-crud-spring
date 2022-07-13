package com.crudjava.crudspring.controller;

import com.crudjava.crudspring.dto.EmpresaDTO;
import com.crudjava.crudspring.model.Empresa;
import com.crudjava.crudspring.repository.EmpresaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empresa")
@AllArgsConstructor
public class EmpresaController {
  private final EmpresaRepository empresaRepository;

  @PostMapping
  public ResponseEntity<EmpresaDTO> criar(@RequestBody EmpresaDTO empresaDTO) {
    Empresa empresa = empresaDTO.toEntity();

    boolean cnpjExiste = empresaRepository.existsByCnpj(empresa.getCnpj());
    if(cnpjExiste){
      return new ResponseEntity<>(empresa.toDTO(), HttpStatus.CONFLICT);
    }

    empresaRepository.save(empresa);
    return new ResponseEntity<>(empresa.toDTO(), HttpStatus.CREATED);
  }

  @GetMapping
  public List<Empresa> listar() {
    return empresaRepository.findAll();
  }

  @GetMapping(params = {"id"})
  public Optional<Empresa> listar(@RequestParam long id) {
    return empresaRepository.findById(id);
  }

  @DeleteMapping(path = { "/{id}" })
  public ResponseEntity<Long> excluir(@PathVariable long id) {
    Optional<Empresa> empresa = empresaRepository.findById(id);

    if (empresa.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    empresaRepository.deleteById(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }

  @PutMapping(value="/{id}")
  public ResponseEntity alterar(@PathVariable long id,  @RequestBody EmpresaDTO empresaDTO) {
    Empresa empresa = empresaDTO.toEntity();

    boolean cnpjExiste = empresaRepository.existsByCnpj(empresa.getCnpj());
    if(cnpjExiste){
      return new ResponseEntity<>(empresa.toDTO(), HttpStatus.CONFLICT);
    }
    return empresaRepository.findById(id).map(
            record ->{
              record.setId(empresa.getId());
              record.setNome_fantasia(empresa.getNome_fantasia());
              record.setRazao_social(empresa.getRazao_social());
              record.setCnpj(empresa.getCnpj());
              record.setStatus(empresa.getStatus());
              Empresa alterado = empresaRepository.save(record);
              return ResponseEntity.ok().body(alterado);
            }
    ).orElse(ResponseEntity.notFound().build());
  }
}
