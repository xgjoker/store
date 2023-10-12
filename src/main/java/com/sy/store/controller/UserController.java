package com.sy.store.controller;

import com.sy.store.controller.ex.*;
import com.sy.store.entity.User;
import com.sy.store.service.IUserService;
import com.sy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(HttpStatus.OK.value());
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User result = userService.login(username,password);
        session.setAttribute("uid",result.getUid());
        session.setAttribute("username",result.getUsername());
        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<>(HttpStatus.OK.value(),result);
    }
    //@PathVariable("oldPassword") @RequestBody @RequestParam
    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(HttpStatus.OK.value());
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        return new JsonResult<>(HttpStatus.OK.value(),userService.getByUid(getUidFromSession(session)));
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(HttpStatus.OK.value());
    }

    public static final int AVATAR_MAX_SIZE = 10*1024*1024;

    public static final List<String> AVATAR_TYPE= new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(
            HttpSession session,
            @RequestParam("file") MultipartFile file) {

        if(file.isEmpty()){
            throw new FileEmptyException("file is empty");
        }
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("file oversize");
        }
        if(!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException("file type is not correct");
        }
        String parent = session.getServletContext().getRealPath("upload");
        File dir = new File(parent);
        if(!dir.exists()){
            dir.mkdir();
        }
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() +suffix;

        File dest = new File(dir,filename);
        try {
            file.transferTo(dest);
        } catch (FileStateException e){
            throw new FileStateException("file state exception");
        }
        catch (IOException e) {
            throw new FileUploadIOException("file IO exception");
        }
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        String avatar = "/upload/"+filename;
        userService.changeAvatar(uid,username,avatar);
        return new JsonResult<>(HttpStatus.OK.value(),avatar);
    }

}
