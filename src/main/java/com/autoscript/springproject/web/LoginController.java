package com.autoscript.springproject.web;

import com.autoscript.springproject.domain.Script;
import com.autoscript.springproject.domain.User;
import com.autoscript.springproject.service.AdminService;
import com.autoscript.springproject.service.DesignerService;
import com.autoscript.springproject.service.ScriptService;
import com.autoscript.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// a controller to control login action
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private DesignerService designerService;

    //try to login as a user
    @RequestMapping(value="/TryUserToLogin",method= RequestMethod.POST)
    public String userLogin(HttpServletRequest request, @ModelAttribute User user, RedirectAttributes
            attributes) throws IOException {
        if(userService.checkPassword(user.getName(),user.getPassword())){
            attributes.addFlashAttribute("message","Login Successfully!");
            request.getSession().setAttribute("entity", userService.getByName(user.getName()));
            request.getSession().setAttribute("type","user");
            request.getSession().setAttribute("login","1");
            return "redirect:/index";
        }else{
            attributes.addFlashAttribute("msg","Wrong Email Address or Password!");
            return "redirect:/userLogIn";
        }

    }
    //try to login as a designer
    @RequestMapping(value = "/TryDesignerToLogin", method = RequestMethod.POST)
    public String designerLogin(HttpServletRequest request, RedirectAttributes
            attributes) throws IOException{
        if(designerService.checkPassword(request.getParameter("name"),request.getParameter("password"))){
            attributes.addFlashAttribute("message","Login Successfully!");
            request.getSession().setAttribute("entity", designerService.getByName(request.getParameter("name")));
            request.getSession().setAttribute("type","designer");
            request.getSession().setAttribute("login","1");
            return "redirect:/index";
        }else{
            attributes.addFlashAttribute("msg","Wrong Email Address or Password!");
            return "redirect:/designerLogIn";
        }
    }
    //try to login as a admin
    @RequestMapping(value = "/TryAdminToLogin", method = RequestMethod.POST)
    public String adminLogin(HttpServletRequest request, RedirectAttributes
            attributes) throws IOException{
        if(adminService.checkPassword(request.getParameter("name"),request.getParameter("password"))){
            attributes.addFlashAttribute("message","Login Successfully!");
            request.getSession().setAttribute("entity", adminService.getByName(request.getParameter("name")));
            request.getSession().setAttribute("type","admin");
            request.getSession().setAttribute("login","1");
            return "redirect:/index";
        }else{
            attributes.addFlashAttribute("msg","Wrong Email Address or Password!");
            return "redirect:/adminLogIn";
        }
    }
}



