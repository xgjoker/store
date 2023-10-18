package com.sy.store.service.impl;

import com.sy.store.entity.Address;
import com.sy.store.entity.District;
import com.sy.store.mapper.AddressMapper;
import com.sy.store.mapper.DistrictMapper;
import com.sy.store.service.IAddressService;
import com.sy.store.service.IDistrictService;
import com.sy.store.service.ex.AddressCountLimitException;
import com.sy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {

        List<District> distList = districtMapper.findByParent(parent);
        for (District d: distList
             ) {
            d.setId(null);
            d.setParent(null);
        }
        return distList;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
