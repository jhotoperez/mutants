package com.mutants.mutants.shared;



import java.io.Serializable;
import java.util.List;

public interface IGenericService<E, V extends Serializable> {

    List<E> getAll();


}
