package com.autoscript.springproject.web;

import com.autoscript.springproject.domain.Admin;
import com.autoscript.springproject.domain.Designer;
import com.autoscript.springproject.domain.Script;
import com.autoscript.springproject.domain.User;
import com.autoscript.springproject.service.AdminService;
import com.autoscript.springproject.service.DesignerService;
import com.autoscript.springproject.service.ScriptService;
import com.autoscript.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//To control access redirect web pages within adding personal information.

@Controller
public class AccessController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private DesignerService designerService;
    @Autowired
    private ScriptService scriptService;

    //redirect to my profile
    @RequestMapping("/MyInfo")
    public ModelAndView MyInfoIndex(HttpServletRequest request, RedirectAttributes
            attributes, Model model) {
        try {
            Object entity = request.getSession().getAttribute("entity");
        List<Script> scriptList = new ArrayList<Script>();
        List<String> nameList = new ArrayList<String>();
        switch (entity.getClass().toString()) {
                case "class com.autoscript.springproject.domain.User":
                    User user = (User) entity;
                    model.addAttribute("entity", user);
                    List<Integer> scriptidList = userService.getUserBookmarks(user.getId());
                    Script s = null;
                    for(int i =0;i < scriptidList.size();i++){
                        long id = Long.parseLong(scriptidList.get(i).toString());
                        s = scriptService.findById(id);
                        scriptList.add(s);
                        nameList.add(scriptService.getDesignerName(id));
                    }
                    model.addAttribute("scriptList",scriptList);
                    model.addAttribute("nameList",nameList);
                    return new ModelAndView("userInfo");
                case "class com.autoscript.springproject.domain.Admin":
                    Admin admin = (Admin) entity;
                    model.addAttribute("entity", admin);
                    return new ModelAndView("adminInfo");
                case "class com.autoscript.springproject.domain.Designer":
                    Designer designer = (Designer) entity;
                    model.addAttribute("entity", designer);
                    List<Integer> scriptidList1 = designerService.getDeisngerBookmarks(designer.getId());
                    Script s1 = null;
                    for(int i =0;i < scriptidList1.size();i++){
                        long id = Long.parseLong(scriptidList1.get(i).toString());
                        s1 = scriptService.findById(id);
                        scriptList.add(s1);
                        nameList.add(scriptService.getDesignerName(id));
                    }
                    model.addAttribute("scriptList",scriptList);
                    model.addAttribute("nameList",nameList);
                    return new ModelAndView("designerInfo");
                default:
                    return new ModelAndView("index");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("type");
        }
    }
//    redirect to upload pages
    @RequestMapping("/TryToAccessUpload")
    public ModelAndView CheckUploadStatus(HttpServletRequest request, RedirectAttributes
            attributes, Model model) {
        try {
            Object entity = request.getSession().getAttribute("entity");
            if (entity.getClass().toString().equals("class com.autoscript.springproject.domain.Designer")){
                return new ModelAndView("Upload");
            }
            else{
                System.err.println("not designer");
                return new ModelAndView("SignUp");
            }
        }
        catch (Exception e){
            System.err.println("login plz");
            return new ModelAndView("type");
        }
    }
}
