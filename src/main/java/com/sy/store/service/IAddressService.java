package com.sy.store.service;


import com.sy.store.entity.Address;

import java.util.List;

public interface IAddressService {

    void addNewAddress(Address address, Integer uid, String username);

    List<Address> getByUid(Integer uid);
}
