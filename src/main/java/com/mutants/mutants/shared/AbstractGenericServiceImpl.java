package com.mutants.mutants.shared;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class AbstractGenericServiceImpl<E, V extends Serializable> implements IGenericService<E, V> {

    @Override
    public List<E> getAll() {
        return getRepository().findAll();
    }

    public abstract JpaRepository<E, V> getRepository();
}
