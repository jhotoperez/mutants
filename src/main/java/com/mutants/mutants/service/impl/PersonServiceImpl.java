package com.mutants.mutants.service.impl;


import com.mutants.mutants.model.Person;
import com.mutants.mutants.model.ResponseStats;
import com.mutants.mutants.repository.IPersonRepository;
import com.mutants.mutants.service.IPersonService;
import com.mutants.mutants.shared.AbstractGenericServiceImpl;
import io.micrometer.core.instrument.util.DoubleFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends AbstractGenericServiceImpl<Person, Long> implements IPersonService {

    private final IPersonRepository personRepository;

    @Override
    public JpaRepository<Person, Long> getRepository() {
        return personRepository;
    }

    @Override
    public Boolean isMutant(ArrayList<String> dna) {
        String[][] matriz = new String[dna.size()][dna.size()];
        String arrayDna = String.join(",", dna);
        Boolean mutant = false;
        Person person = new Person();
        matriz = arrayRecursive(dna,matriz,0,0);
        mutant = recursive(matriz, 0, 0, matriz[0].length - 1, mutant);
        if (mutant) {
            person.setType("MUTANTE");
        } else {
            person.setType("HUMANO");
        }
        person.setDna(arrayDna);
        try {
            personRepository.save(person);
        } catch (Exception e) {
        }
        return mutant;
    }


    private String [][] arrayRecursive(ArrayList<String> dna,String[][] matriz,int i,int j){
        String cadena = dna.get(j);
        String[] acadena = cadena.split("");
        matriz[j][i] = acadena[i];
        if(i == dna.size()-1 && j == dna.size()-1){
            return  matriz;
        }else{
            i++;
            if(i == dna.size()){
                j++; i = 0;
            }
            if(j <= dna.size()){
                matriz = arrayRecursive(dna,matriz,i,j);
            }
            return matriz;
        }
    }

    private Boolean recursive(String[][] matriz, int x, int y, int n, Boolean mutant) {
        String caracter = matriz[y][x];
        if ((x + 3) <= n) {
            if ((caracter.equals(matriz[y][(x + 1)]) && caracter.equals(matriz[y][(x + 2)]) && caracter.equals(matriz[y][(x + 3)]))) {
                return true;
            }
        }
        if ((y + 3) <= n) {
            if (caracter.equals(matriz[(y + 1)][x]) && caracter.equals(matriz[(y + 2)][x]) && caracter.equals(matriz[(y + 3)][x])) {
                return true;
            }
        }
        if ((x + 3) <= n && (y + 3) <= n) {
            if (caracter.equals(matriz[(y + 1)][x + 1]) && caracter.equals(matriz[(y + 2)][x + 2]) && caracter.equals(matriz[(y + 3)][x + 3])) {
                return true;
            }
        }
        if (x++ == n) {
            y++; x = 0;
        }
        if (y <= n) {
            mutant = recursive(matriz, x, y, n, mutant);
        }
        return mutant;
    }

    public ResponseStats Stast(){
        int countMutant = personRepository.findAllByType("MUTANTE");
        int countHuman = personRepository.findAllByType("HUMANO");
        float ratio = (float) countHuman/countMutant;
       ResponseStats stats = new ResponseStats();
       stats.setCount_human_dna(countHuman);
       stats.setCount_mutant_dna(countMutant);
       stats.setRatio(ratio);
       return stats;
    }

    public Boolean validation(ArrayList<String> dna) {
        boolean validation = false;
        String regex = "^[A|T|C|G|]+$";
        Pattern pattern = Pattern.compile(regex);
        for (String name : dna) {
            Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                validation = true;
            }
        }
        return validation;
    }

}
