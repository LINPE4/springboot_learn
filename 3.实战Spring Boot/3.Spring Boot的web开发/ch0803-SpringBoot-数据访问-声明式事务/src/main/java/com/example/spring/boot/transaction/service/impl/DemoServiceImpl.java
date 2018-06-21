package com.example.spring.boot.transaction.service.impl;

import com.example.spring.boot.transaction.dao.PersonRepository;
import com.example.spring.boot.transaction.domain.Person;
import com.example.spring.boot.transaction.service.DemoService;
import com.example.spring.boot.transaction.service.DemoService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * Author: 王俊超
 * Date: 2017-07-18 07:57
 * All Rights Reserved !!!
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DemoService2 demoService2;

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
     *              Read_uncommitted: 读未提交
     *              Read_committed:读提交
     *              REPEATABLE_READ:重复读
     *              SERIALIZABLE:序列化
     *
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

    /**
     * 不做异常捕获，事务能回滚
     * @return
     */
    @Override
    @Transactional
    public Person testException() {
        Person person = new Person();
        person.setName("peter");
        person.setAge(25);
        Person p = personRepository.save(person);
        int a = 10/0;
        return p;
    }

    /**
     * 如果在程序中捕获异常， 则事务不回滚
     * 要使事务回滚，则需要再catch中再次抛出异常
     *          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();或者
     *          throw new RuntimeException();
     * @return
     */
    @Override
    @Transactional
    public Person testCatchException() {
        try {
            Person person = new Person();
            person.setName("peter2");
            person.setAge(25);
            Person p = personRepository.save(person);
            int a = 10/0;
            return p;
        } catch (Exception e) {
            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            throw new RuntimeException();
        }
        return null;
    }

    /**
     * 在同一个类中，如果有事务嵌套，方法A（Propagation.REQUIRED）调用了方法B(Propagation.REQUIRES_NEW)，假如方法B出现异常，则一整个事务回滚，这是因为
     * spring开启事务的时候开启的是代理类， 同一个类中的方法都在此代理类中
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveA() {
        Person personA = new Person();
        personA.setName("peterA");
        personA.setAge(25);
        Person p = personRepository.save(personA);
        saveB();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveB() {
        Person personB = new Person();
        personB.setName("personB");
        personB.setAge(15);
        Person p = personRepository.save(personB);
        int a = 10 / 0;
    }

    /**
     * 网上说不同的service嵌套事务能生成各自的代理，如果demoService2.saveC()【requires_new】有异常，则saveAC（）不受影响能保存成功，
     * 但是经过本人测试也是同时回滚的;
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveAC() {
        Person peterAA = new Person();
        peterAA.setName("peterAA");
        peterAA.setAge(255);
        Person p = personRepository.save(peterAA);
        demoService2.saveC();
    }


}
