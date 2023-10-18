package com.sy.store.controller;

import com.sy.store.controller.ex.*;
import com.sy.store.entity.Address;
import com.sy.store.entity.User;
import com.sy.store.service.IAddressService;
import com.sy.store.service.IUserService;
import com.sy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private HttpSession session;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address){
        addressService.addNewAddress(address,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(HttpStatus.OK.value());
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(){
        return new JsonResult<>(HttpStatus.OK.value(),addressService.getByUid(getUidFromSession(session)));
    }

    // RestFul 风格请求
//    @RequestMapping("{aid}/setDefault")
//    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
//        Integer uid = getuidFromSession(session);
//        String username = getUsernameFromSession(session);
//        addressService.setDefault(aid, uid, username);
//        return new JsonResult<>(OK);
//    }
//
//    @RequestMapping("{aid}/delete")
//    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
//        Integer uid = getuidFromSession(session);
//        String username = getUsernameFromSession(session);
//        addressService.delete(aid, uid, username);
//        return new JsonResult<>(OK);
//    }
}
