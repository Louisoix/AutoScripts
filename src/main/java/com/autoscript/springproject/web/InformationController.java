package com.autoscript.springproject.web;


import com.autoscript.springproject.domain.Admin;
import com.autoscript.springproject.domain.Designer;
import com.autoscript.springproject.domain.User;
import com.autoscript.springproject.service.AdminService;
import com.autoscript.springproject.service.DesignerService;
import com.autoscript.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
// to manage clients' information
@Controller
public class InformationController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private DesignerService designerService;

    // change information mapping.
    @RequestMapping(value = "/ChangeInfo", method = RequestMethod.POST)
    public String changeInfo(HttpServletRequest request, RedirectAttributes
            attributes){

        Object thisClient = request.getSession().getAttribute("entity");
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");

        System.out.println(nickName);
        System.out.println(password);

        switch (thisClient.getClass().toString()){
            case "class com.autoscript.springproject.domain.User":
                User user = (User) thisClient;
                if (nickName.length()!=0){
                    userService.updateUserNickName(nickName,user.getId());
                }
                if (password.length()!=0){
                    userService.updateUserPassword(password,user.getId());
                }
                user = userService.findById(user.getId());
                request.getSession().setAttribute("entity",user);
                return "redirect:/MyInfo";
            case "class com.autoscript.springproject.domain.Designer":
                Designer designer = (Designer) thisClient;
                if (nickName.length()!=0){
                    designerService.updateUserNickName(nickName,designer.getId());
                }
                if (password.length()!=0){
                    designerService.updateUserPassword(password,designer.getId());
                }
                designer = designerService.findById(designer.getId());
                request.getSession().setAttribute("entity",designer);
                return "redirect:/MyInfo";
            default:
                return "redirect:/";
        }

    }
    //a page for admin manage user's information
    @RequestMapping("/userManage")
    public ModelAndView userManageIndex(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);
        return new ModelAndView("ManageUser");
    }
    //a page for admin manage designer's
    @RequestMapping("/designerManage")
    public ModelAndView designerManageIndex(Model model) {
        List<Designer> designerList = designerService.findAll();
        model.addAttribute("designerList",designerList);
        return new ModelAndView("ManageDesigner");
    }
    //delete a user by id
    @GetMapping("/deleteUser{id}")
    public String deleteUser(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes){
        userService.deleteUserById(id);
        return "redirect:/userManage";
    }
    //delete a designer by id
    @GetMapping("/deleteDesigner{id}")
    public String deleteDesigner(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes){
        designerService.deleteById(id);
        return "redirect:/designerManage";
    }

}
