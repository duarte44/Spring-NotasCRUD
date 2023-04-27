package com.example.notasmedia.resource;

import com.example.notasmedia.dto.AlunosDTO;
import com.example.notasmedia.dto.ProfessoresDTO;
import com.example.notasmedia.entity.Alunos;
import com.example.notasmedia.entity.Professores;
import com.example.notasmedia.exceptions.ResourceNotFoundException;
import com.example.notasmedia.services.ProfessorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorResource {

    @Autowired
    private ProfessorServices professorServices;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<ProfessoresDTO>> findAll() {
        List<Professores> list = professorServices.findAll();
        List<ProfessoresDTO> listDto = list.stream().map(obj -> new ProfessoresDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
        //reporta todas as categorias
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Professores> findById(@PathVariable Long id){

        verificaSeExiste(id);

        Professores obj = professorServices.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){

        verificaSeExiste(id);

        professorServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody @Valid ProfessoresDTO professoresDTO) {
        Professores obj = professorServices.fromDTO(professoresDTO);
        obj = professorServices.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody @Valid ProfessoresDTO objDto, @PathVariable Long id){
        //Professores prof = professorServices.findById(id);
        verificaSeExiste(id);

        Professores obj = professorServices.fromDTO(objDto);
        obj.setId(id);
        obj = professorServices.update(obj);
        return ResponseEntity.noContent().build();
    }

    private void verificaSeExiste(Long id){
        if(professorServices.findById(id) == null){
            throw new ResourceNotFoundException("Professor não encontrado, id: " + id);
        }
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
