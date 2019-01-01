package com.autoscript.springproject.web;

import com.autoscript.springproject.domain.Designer;
import com.autoscript.springproject.domain.Script;
import com.autoscript.springproject.service.FileService;
import com.autoscript.springproject.service.ScriptService;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
// a controller for upload
@Controller
public class UploadController {

    @Autowired
    private ScriptService scriptService;

    //to access upload page
    @RequestMapping(value="/TryToUpload",method= RequestMethod.POST)
    public String upload(HttpServletRequest request, RedirectAttributes
            attributes, @RequestParam("file") MultipartFile mfile) throws IOException {
        String s_name = request.getParameter("name");
        String s_lan = request.getParameter("language");
        String s_des = request.getParameter("description");
        String s_UploadOptions = request.getParameter("UploadOptions");
        String s_code = request.getParameter("code");
        String s_file = request.getParameter("file");

        int s_para = -1;
        try {
            s_para = Integer.parseInt(request.getParameter("parameter").toString());
        }
        catch (Exception e){
            System.err.println("Parameter is not a correct integer.");
            return "redirect:/Upload?error=Parameter is not a correct integer.";
        }
        String lan = "";
        switch (s_lan){
            case "Java":
                s_lan = "java";
                lan = "Java";
                break;
            case "C":
                s_lan = "c";
                lan = "C";
                break;
            case "Python3":
                s_lan = "py";
                lan = "Python";
                break;
        }
        Object entity = request.getSession().getAttribute("entity");
        Designer thisdesigner = (Designer) entity;
        String path = scriptService.pathGenerator(thisdesigner.getId(), s_name);
        if (s_UploadOptions.equals("upload")) {
            FileService.uploadFile(mfile.getBytes(), path, s_name + "." + s_lan);
        }
        else
            FileService.uploadFile(s_code.getBytes(), path, s_name + "." + s_lan);

        long scriptid = scriptService.getNext();
        scriptService.addScript(new Script(s_name, s_para, lan, Script.CHECK, s_des, path +"\\"+s_name+"."+s_lan));
        scriptService.linkToDesignerAndScript(thisdesigner.getId(),scriptid);
        return "redirect:/MyScripts";

    }



}
