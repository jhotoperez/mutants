package com.mutants.mutants.service;


import com.mutants.mutants.model.Person;
import com.mutants.mutants.model.ResponseStats;
import com.mutants.mutants.shared.IGenericService;

import java.util.ArrayList;


public interface IPersonService extends IGenericService<Person, Long> {

    Boolean isMutant(ArrayList<String> dna);

    ResponseStats Stast();

}
