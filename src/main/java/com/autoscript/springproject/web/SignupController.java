package com.autoscript.springproject.web;

import com.autoscript.springproject.domain.Designer;
import com.autoscript.springproject.domain.User;
import com.autoscript.springproject.service.AdminService;
import com.autoscript.springproject.service.DesignerService;
import com.autoscript.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
//a controller for signup
@Controller

public class SignupController {

    @Autowired
    private UserService userService;
    @Autowired
    private DesignerService designerService;
    //to access signup pages
    @RequestMapping(value="/TryToSign",method= RequestMethod.POST)
    public String signup(HttpServletRequest request, RedirectAttributes
            attributes) throws IOException {
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("password"));
        System.out.println(request.getParameter("confirm"));
        System.out.println(request.getParameter("SignUpOptions"));

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String c_password = request.getParameter("confirm");
        String type = request.getParameter("SignUpOptions");

        if (!c_password.equals(password)){
            attributes.addFlashAttribute("msg","The password and confirmation password are different");
            return "redirect:/SignUp";
        }
        if (type.equals("user")){
            if (userService.checkName(name)){
                attributes.addFlashAttribute("msg","The E-mail address already exists");
                return "redirect:/SignUp";
            }
            else {
                userService.addUser(new User(name,password));
                return "redirect:/LogInType";
            }
        }
        else if (type.equals("designer")){
            if (designerService.checkName(name)){
                attributes.addFlashAttribute("msg","The E-mail address already exists");
                return "redirect:/SignUp";
            }
            else {
                designerService.addDesigner(new Designer(name,password));
                return "redirect:/LogInType";
            }
        }
        return "redirect:/index.html";
    }
}
