package com.mutants.mutants.service;

import com.mutants.mutants.model.Person;
import com.mutants.mutants.repository.IPersonRepository;
import com.mutants.mutants.service.impl.PersonServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

    @Mock
    IPersonRepository personRepository;
    @InjectMocks
    PersonServiceImpl service;



    @Test
    public void getAll() {
        Person survey = new Person();
        List<Person> surveys = Collections.singletonList(survey);
        Mockito.when(personRepository.findAll()).thenReturn(surveys);
        assertNotNull(service.getAll());
    }

    @Test
    public void isMutant(){
        ArrayList<String> dna = new ArrayList<String>();
        dna.add("ATGCGA");
        dna.add("CAGTGC");
        dna.add("TTATGA");
        dna.add("AGAAGT");
        dna.add("CCCCTA");
        dna.add("TCTATT");
        assertTrue(service.isMutant(dna));
    }

    @Test
    public void isMutantEnd(){
        ArrayList<String> dna = new ArrayList<String>();
        dna.add("CTGCCA");
        dna.add("CAGTGC");
        dna.add("TTATGA");
        dna.add("AGAAGT");
        dna.add("CACCTA");
        dna.add("TCTTTT");
        assertTrue(service.isMutant(dna));
    }

    @Test
    public void isHuman(){
        ArrayList<String> dna = new ArrayList<String>();
        dna.add("CTGCCA");
        dna.add("CAGTGC");
        dna.add("TTATGA");
        dna.add("AGAAGT");
        dna.add("CACCTA");
        dna.add("TCTCTT");
        assertFalse(service.isMutant(dna));
    }

    @Test
    public void stats(){
        Mockito.when(personRepository.findAllByType("MUTANTE")).thenReturn(1);
        Mockito.when(personRepository.findAllByType("HUMANO")).thenReturn(1);
        assertNotNull(service.Stast());
    }






}