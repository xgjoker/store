package com.sy.store.service;

import com.sy.store.entity.Address;
import com.sy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {

    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent() {
        for (District district:
                districtService.getByParent("110101")) {
            System.out.println(district);
        };
    }
}
