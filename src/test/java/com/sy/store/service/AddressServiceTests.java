package com.sy.store.service;

import com.sy.store.entity.Address;
import com.sy.store.entity.User;
import com.sy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {

    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("17858805555");
        address.setName("Bob");
        addressService.addNewAddress(address,5,"admin");
    }
}
