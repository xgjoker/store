package com.sy.store.service.impl;

import com.sy.store.entity.Address;
import com.sy.store.entity.User;
import com.sy.store.mapper.AddressMapper;
import com.sy.store.mapper.UserMapper;
import com.sy.store.service.IAddressService;
import com.sy.store.service.IDistrictService;
import com.sy.store.service.IUserService;
import com.sy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        int count = addressMapper.countByUid(uid);
        if(count>maxCount){
            throw new AddressCountLimitException("more than 20 addresses under this user");
        }

        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));

        address.setUid(uid);
        Integer isDefault = count==0? 1:0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        Date date = new Date();
        address.setCreatedTime(date);
        address.setModifiedTime(date);

        int row = addressMapper.insert(address);
        if(row!=1){
            throw new InsertException("address insert exception");
        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {

        List<Address> list = addressMapper.findByUid(uid);
        for (Address a: list) {
            a.setAid(null);
            a.setUid(null);
            a.setProvinceName(null);
            a.setProvinceCode(null);
            a.setCityCode(null);
            a.setAreaCode(null);
            a.setTel(null);
            a.setCreatedTime(null);
            a.setCreatedUser(null);
            a.setModifiedTime(null);
            a.setModifiedUser(null);
        }

        return list;
    }
}
