package com.autoscript.springproject.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//to control redirect webpage.

@Controller

public class IndexController {
    @RequestMapping("/index.html")
    public ModelAndView index(HttpServletRequest request) {
        try{
            String status = request.getSession().getAttribute("login").toString();
            if (status.equals("1")){
                System.err.println("yes,login");
                switch (request.getSession().getAttribute("type").toString()){
                    case "designer":
                        return new ModelAndView("designerIndex");
                    case "user":
                        return new ModelAndView("userIndex");
                    case "admin":
                        return new ModelAndView("adminIndex");
                }
            }
        }catch (Exception e){
            System.err.println("Not login yet");
        }
        return new ModelAndView("index");
    }

//    to access index
    @RequestMapping("/index")
    public ModelAndView index1(HttpServletRequest request) {
        try{
            String status = request.getSession().getAttribute("login").toString();
            if (status.equals("1")){
                System.err.println("yes,login");
                switch (request.getSession().getAttribute("type").toString()){
                    case "designer":
                        return new ModelAndView("designerIndex");
                    case "user":
                        return new ModelAndView("userIndex");
                    case "admin":
                        return new ModelAndView("adminIndex");
                }
            }
        }catch (Exception e){
            System.err.println("Not login yet");
        }
        return new ModelAndView("index");
    }

//    log out and delete seccesion
    @RequestMapping("/logOut")
    public ModelAndView signoutindex(HttpServletRequest request) {
        try {
            request.getSession().invalidate();
        }
        catch (Exception e){
            System.err.println("Not Login yet!");
        }
        return new ModelAndView("index");
    }

//    access index page
    @RequestMapping("/")
    public ModelAndView index2(HttpServletRequest request) {
        try{
            String status = request.getSession().getAttribute("login").toString();
            if (status.equals("1")){
                System.err.println("yes,login");
                switch (request.getSession().getAttribute("type").toString()){
                    case "designer":
                        return new ModelAndView("designerIndex");
                    case "user":
                        return new ModelAndView("userIndex");
                    case "admin":
                        return new ModelAndView("adminIndex");
                }
            }
        }catch (Exception e){
            System.err.println("Not login yet");
        }
        return new ModelAndView("index");
    }

//    choose a type to log in pages
    @RequestMapping("/LogInType")
    public ModelAndView LoginTypeIndex() {
        return new ModelAndView("type");
    }
    // admin choose a type to manage page
    @RequestMapping("/manageType")
    public ModelAndView manageTypeIndex() {
        return new ModelAndView("customerTypeManage");
    }
    // user log in page
    @RequestMapping("/userLogIn")
    public ModelAndView userLoginIndex() {
        return new ModelAndView("userLogIn");
    }
    // admin log in page
    @RequestMapping("/adminLogIn")
    public ModelAndView adminLoginIndex() {
        return new ModelAndView("adminLogIn");
    }
    // designer log in page
    @RequestMapping("/designerLogIn")
    public ModelAndView designerLoginIndex() {
        return new ModelAndView("designerLogIn");
    }
    // sign up page
    @RequestMapping("/SignUp")
    public ModelAndView SignupIndex() {
        return new ModelAndView("SignUp");
    }
    // upload page
    @RequestMapping("/Upload")
    public ModelAndView uploadIndex() {
        return new ModelAndView("Upload");
    }
    // designer's profile page
    @RequestMapping("/designerInfo")
    public ModelAndView designerInfoIndex() {
        return new ModelAndView("designerInfo");
    }
    // user's profile page
    @RequestMapping("/userInfo")
    public ModelAndView userInfoIndex() {
        return new ModelAndView("userInfo");
    }
    // user's result
    @RequestMapping("/userResult")
    public ModelAndView userResultIndex(){
        return new ModelAndView("userResult");
    }
}
