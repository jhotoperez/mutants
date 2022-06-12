package com.mutants.mutants.controller;

import com.mutants.mutants.model.Person;
import com.mutants.mutants.model.RequestMutant;
import com.mutants.mutants.model.ResponseStats;
import com.mutants.mutants.service.IPersonService;
import com.mutants.mutants.shared.AbstractGenericRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
    public class PersonController extends AbstractGenericRestController<Person,Long> {

    private final IPersonService service;

    public PersonController(IPersonService service) {
        super(service);
        this.service = service;
    }



    @PostMapping("/mutant")
    public ResponseEntity mutants(@RequestBody RequestMutant request){
        String regex = "^[A|T|C|G|]+$";
        Pattern pattern = Pattern.compile(regex);
        for (String name: request.getDna()) {
            Matcher matcher = pattern.matcher(name);
            if(!matcher.matches()){
               return new ResponseEntity<>("NO SE ADMITE CARACTERES DIFERENTES DE: A,T,C,G", HttpStatus.BAD_REQUEST);
            }
        }
        ResponseEntity<?> finalResponse = null;
        if (service.isMutant(request.getDna())) {
            finalResponse = new ResponseEntity<>("MUTANTE", HttpStatus.OK);
        } else {
            finalResponse = new ResponseEntity<>("HUMANO", HttpStatus.FORBIDDEN);
        }
        return finalResponse;
    }

    @GetMapping("/stats")
    public ResponseEntity<ResponseStats> stats(){
        ResponseEntity<?> finalResponse = null;
        ResponseStats stast = service.Stast();
           return new ResponseEntity<>(stast, HttpStatus.OK);
    }






}
