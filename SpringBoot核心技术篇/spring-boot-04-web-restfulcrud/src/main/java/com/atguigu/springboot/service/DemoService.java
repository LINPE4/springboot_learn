package com.atguigu.springboot.service;


import com.atguigu.springboot.entities.Person;

/**
 * Author: 王俊超
 * Date: 2017-07-18 07:57
 * All Rights Reserved !!!
 */
public interface DemoService {
    Person savePersonWithRollBack(Person person);
    Person savePersonWithoutRollBack(Person person);

    Person testException();

    Person testCatchException();

    void saveA();

    void saveB();

    void saveAC();
}
