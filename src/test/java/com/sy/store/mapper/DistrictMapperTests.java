package com.sy.store.mapper;


import com.sy.store.entity.Address;
import com.sy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findDistrictByParent(){
        for (District district:
        districtMapper.findByParent("110100")) {
            System.out.println(district);
        };
    }

    @Test
    public void fidNameByCode(){
        System.out.println(districtMapper.findNameByCode("110101"));
    }

}
