package com.autoscript.springproject.web;

import com.autoscript.springproject.domain.Designer;
import com.autoscript.springproject.domain.User;
import com.autoscript.springproject.service.DesignerService;
import com.autoscript.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

//to control action about bookmark

@Controller
public class BookmarkController {
    @Autowired
    private UserService userService;
    @Autowired
    private DesignerService designerService;

//    get user bookmark.
    @GetMapping("/userBookmark{id}")
    public String userBookmark(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes){
        User user = (User)request.getSession().getAttribute("entity");
        userService.insertBookmarkIntoUser(user.getId(),id);
        if (request.getSession().getAttribute("type").equals("user")){
            return "redirect:/scriptsMarket";
        }
        if (request.getSession().getAttribute("type").equals("designer")){
            return "redirect:/scriptsMarket";
        }
        return "redirect:/index";

    }
//    to get designer bookmark
    @GetMapping("/designerBookmark{id}")
    public String designerBookmark(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes){
        Designer designer = (Designer)request.getSession().getAttribute("entity");
        designerService.insertBookmarkIntoDesigner(designer.getId(),id);
        if (request.getSession().getAttribute("type").equals("user")){
            return "redirect:/scriptsMarket";
        }
        if (request.getSession().getAttribute("type").equals("designer")){
            return "redirect:/scriptsMarket";
        }
        return "redirect:/index";

    }

//    to delete bookmark
    @GetMapping("/deleteBookmark{id}")
    public String deleteBookmark(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes){
        if (request.getSession().getAttribute("type").equals("user")){
            User user = (User) request.getSession().getAttribute("entity");
            userService.deleteBookmarkFromUser(user.getId(),id);
            return "redirect:/MyInfo";
        }
        if (request.getSession().getAttribute("type").equals("designer")){
            Designer designer = (Designer) request.getSession().getAttribute("entity");
            designerService.deleteBookmarkFromDesigner(designer.getId(),id);
            return "redirect:/MyInfo";
        }
        return "redirect:/index";

    }
}
