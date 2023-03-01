package com.huawei.service;

import com.huawei.GraphqlApplication;
import com.huawei.wrong.example.po.Order;
import com.huawei.wrong.example.po.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author king
 * @date 2023/3/1-0:15
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.huawei.GraphqlApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonServiceTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceTest.class);

    @Autowired
    private PersonService personService;

    @Test
    public void testSelectPerson() {
        System.out.println("runing ...");
        List<Person> person = personService.findPerson();
        System.out.println("person.get(1).hashCode() = " + person.get(1).hashCode());
        Set<Person> personSet = new HashSet<>(person);
        Order order = new Order(4L,2L,"2ddd",92D);
        List<Order> orders = Arrays.asList(order);
        Person person1 = new Person(2L,"lisi", orders);
        System.out.println("person1.hashCode() = " + person1.hashCode());
        System.out.println(personSet.contains(person1));
        System.out.println(person);
    }

}