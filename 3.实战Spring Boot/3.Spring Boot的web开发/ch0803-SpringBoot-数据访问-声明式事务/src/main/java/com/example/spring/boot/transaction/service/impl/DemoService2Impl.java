package com.example.spring.boot.transaction.service.impl;

import com.example.spring.boot.transaction.dao.PersonRepository;
import com.example.spring.boot.transaction.domain.Person;
import com.example.spring.boot.transaction.service.DemoService;
import com.example.spring.boot.transaction.service.DemoService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: 王俊超
 * Date: 2017-07-18 07:57
 * All Rights Reserved !!!
 */
@Service
public class DemoService2Impl implements DemoService2 {

    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveC() {
        try {
            Person personC = new Person();
            personC.setName("personC");
            personC.setAge(35);
            Person p = personRepository.save(personC);
            int a = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
