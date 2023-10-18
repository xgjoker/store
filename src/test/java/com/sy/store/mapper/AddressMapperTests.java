package com.sy.store.mapper;


import com.sy.store.entity.Address;
import com.sy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(5);
        address.setName("Lily");
        address.setPhone("13268594568");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid(){
        Integer cout = addressMapper.countByUid(5);
        System.out.println(cout);
    }

    @Test
    public void findByUid(){
        System.out.println(addressMapper.findByUid(5));
    }

}
