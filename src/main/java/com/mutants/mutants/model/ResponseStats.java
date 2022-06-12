package com.mutants.mutants.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ResponseStats {

    private int count_mutant_dna;
    private int count_human_dna;
    private float ratio;
}
