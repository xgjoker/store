package com.sy.store.controller;

import com.sy.store.entity.Address;
import com.sy.store.entity.District;
import com.sy.store.service.IAddressService;
import com.sy.store.service.IDistrictService;
import com.sy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {

    @Autowired
    private IDistrictService districtService;

    @Autowired
    private HttpSession session;

    @RequestMapping({"","/"})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(HttpStatus.OK.value(),data);
    }

}
