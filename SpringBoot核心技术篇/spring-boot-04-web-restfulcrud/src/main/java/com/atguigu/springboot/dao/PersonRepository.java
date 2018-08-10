package com.atguigu.springboot.dao;

import com.atguigu.springboot.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: 王俊超
 * Date: 2017-07-18 07:56
 * All Rights Reserved !!!
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
