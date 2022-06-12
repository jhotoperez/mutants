package com.mutants.mutants.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("all")
public abstract class AbstractGenericRestController<T, V extends Serializable> {

    private final IGenericService<T, V> serviceAPI;

    @GetMapping("/all")
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok().body(serviceAPI.getAll());
    }

}
