package com.mutants.mutants.controller;



import com.mutants.mutants.model.Person;
import com.mutants.mutants.model.RequestMutant;
import com.mutants.mutants.model.ResponseStats;
import com.mutants.mutants.service.IPersonService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PersonController.class)
@AutoConfigureMockMvc
public class PersonControllerTest {



    @MockBean
    IPersonService personService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getAll() throws Exception {
        Person person = new Person();
        List<Person> surveys = Collections.singletonList(person);
        Mockito.when(personService.getAll()).thenReturn(surveys);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/all");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.getStatus());
    }

    @Test
    public void statsFailded() throws Exception {
        ResponseStats responseStats = new ResponseStats();

        Mockito.when(personService.Stast()).thenReturn(responseStats);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/stats");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.getStatus());
    }


}