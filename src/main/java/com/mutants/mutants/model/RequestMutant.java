package com.mutants.mutants.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Getter
@Setter
public class RequestMutant {

    @NotEmpty
    private ArrayList<String> dna;
}
