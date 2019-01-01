package com.autoscript.springproject.service;

import com.autoscript.springproject.api.AdminRepository;
import com.autoscript.springproject.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    //check whether the password correct
    public boolean checkPassword(String username, String input_password){
        List<Admin> userList = adminRepository.findByNameAndPassword(username,input_password);
        if(userList.size() == 1){
            return true;
        }
        else{
            return false;
        }
    }
    //get a admin by name
    public Admin getByName(String name){
        List<Admin> adminList = adminRepository.findByName(name);
        return adminList.get(0);
    }
}

