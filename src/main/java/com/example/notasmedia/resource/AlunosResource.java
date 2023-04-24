package com.example.notasmedia.resource;


import com.example.notasmedia.dto.AlunosDTO;
import com.example.notasmedia.entity.Alunos;
import com.example.notasmedia.exceptions.ResourceNotFoundException;
import com.example.notasmedia.repository.AlunosRepository;
import com.example.notasmedia.services.AlunosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Validated
@RestController
@RequestMapping(value = "alunos")
public class AlunosResource {

    @Autowired
    private AlunosServices alunosServices;


    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<AlunosDTO>> findAll() {
        List<Alunos> list = alunosServices.findAll();
        List<AlunosDTO> listDto = list.stream().map(obj -> new AlunosDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
        //reporta todas as categorias
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Alunos> findById(@PathVariable Long id){
        Alunos obj = alunosServices.findById(id);

        return ResponseEntity.ok().body(obj);
    }


    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody @Valid AlunosDTO alunosDTO) {
        Alunos obj = alunosServices.fromDTO(alunosDTO);
        obj = alunosServices.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody @Valid AlunosDTO objDto, @PathVariable Long id){
        Alunos obj = alunosServices.fromDTO(objDto);
        obj.setId(id);
        obj = alunosServices.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        alunosServices.delete(id);
        return ResponseEntity.noContent().build();
    }


    //validator
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}
