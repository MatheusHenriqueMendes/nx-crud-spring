package com.crudjava.crudspring.controller;

import com.crudjava.crudspring.dto.ColaboradorDTO;
import com.crudjava.crudspring.model.Colaborador;
import com.crudjava.crudspring.repository.ColaboradorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colaborador")
@AllArgsConstructor
public class ColaboradorController {
  private final ColaboradorRepository colaboradorRepository;

  @PostMapping
  public ResponseEntity<ColaboradorDTO> criar(@RequestBody ColaboradorDTO colaboradorDTO) {
    Colaborador colaborador = colaboradorDTO.toEntity();

    boolean cpfExiste = colaboradorRepository.existsByCpf(colaborador.getCpf());
    if(cpfExiste){
      return new ResponseEntity<>(colaborador.toDTO(), HttpStatus.CONFLICT);
    }

    colaboradorRepository.save(colaborador);
    return new ResponseEntity<>(colaborador.toDTO(), HttpStatus.CREATED);
  }

  @GetMapping
  public List<Colaborador> listar() {
    return colaboradorRepository.findAll();
  }

  @GetMapping(params = {"id"})
  public Optional<Colaborador> listar(@RequestParam long id) {
    return colaboradorRepository.findById(id);
  }

  @DeleteMapping(path = { "/{id}" })
  public ResponseEntity<Long> excluir(@PathVariable long id) {
    Optional<Colaborador> colaborador = colaboradorRepository.findById(id);

    if (colaborador.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    colaboradorRepository.deleteById(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }

  @PutMapping(value="/{id}")
  public ResponseEntity alterar(@PathVariable long id,  @RequestBody ColaboradorDTO colaboradorDTO) {
    Colaborador colaborador = colaboradorDTO.toEntity();

    boolean cpfExiste = colaboradorRepository.existsByCpf(colaborador.getCpf());
    if(cpfExiste){
      return new ResponseEntity<>(colaborador.toDTO(), HttpStatus.CONFLICT);
    }
    return colaboradorRepository.findById(id).map(
            record ->{
              record.setId(colaborador.getId());
              record.setNome(colaborador.getNome());
              record.setObservacao(colaborador.getObservacao());
              record.setCpf(colaborador.getCpf());
              Colaborador alterado = colaboradorRepository.save(record);
              return ResponseEntity.ok().body(alterado);
            }
    ).orElse(ResponseEntity.notFound().build());
  }
}
