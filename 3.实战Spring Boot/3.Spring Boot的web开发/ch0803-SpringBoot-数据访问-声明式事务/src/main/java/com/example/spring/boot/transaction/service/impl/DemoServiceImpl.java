package com.example.spring.boot.transaction.service.impl;

import com.example.spring.boot.transaction.dao.PersonRepository;
import com.example.spring.boot.transaction.domain.Person;
import com.example.spring.boot.transaction.service.DemoService;
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
public class DemoServiceImpl implements DemoService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * 事务属性：
     *        propagation
     *              required：
     *                  方法A调用时没有事务新建一个事务， 当在方法A调用另外一个方法B的时候，方法B将使用相同的事务，
     *                  如果方法B发送一次需要数据回滚的时候，整个事务数据回滚
     *              requires_new：
     *                  对于方法A和B，再方法调用的时候无论是否有事务都开启一个新的事务，这样如果方法B有异常不会导致方法A
     *                  的数据回滚
     *              nested:
     *                  和Reuqires_new类似，但支持JDBC，不支持JPA或Hibernate
     *              supports：
     *                  方法调用时有事务就用事务，没事务就不用事务
     *              Not_supported
     *                  强制方法不在事务中执行，若有事务，再方法调用到结束阶段事务都将会被挂起
     *              never
     *                  强制方法不在事务中执行，若有事务则抛出异常
     *              mandatory
     *                  强制方法再事务中执行，若无事务则抛出异常
     *        isolation
     * @param person
     * @return
     */

    @Override
    @Transactional(rollbackFor = {IllegalArgumentException.class} )
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);

        if (person.getName().equals("王俊超")) {
            throw new IllegalArgumentException("王俊超已存在，数据将回滚"); //3
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class}) //4
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);

        if (person.getName().equals("王俊超")) {
            throw new IllegalArgumentException("王俊超虽已存在，数据将不会回滚");
        }
        return p;
    }
}
