package com.autoscript.springproject.web;

import com.autoscript.springproject.domain.*;
import com.autoscript.springproject.service.FileService;
import com.autoscript.springproject.service.ResultService;
import com.autoscript.springproject.service.ScriptService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
//a controller to handle script using result.
@Controller
public class ResultController {

    @Autowired
    ResultService resultService;
    @Autowired
    ScriptService scriptService;
    //a client want to access its result, add its identification and redirect to myresult page.
    @RequestMapping("/MyResults")
    public ModelAndView myResult(Model model, HttpServletRequest request){
        Object entity = request.getSession().getAttribute("entity");
        List<Script> scriptList = new ArrayList<Script>();
        switch (entity.getClass().toString()){
            case "class com.autoscript.springproject.domain.User":
                User user = (User) entity;
                List<Result> userResultList = resultService.findByUserId(user.getId());
                model.addAttribute("resultList", userResultList);
                for(Result result: userResultList){
                    scriptList.add(scriptService.findById(result.getScriptid()));
                }
                model.addAttribute("scriptList",scriptList);
                return new ModelAndView("/userResult");
            case "class com.autoscript.springproject.domain.Designer":
                Designer designer = (Designer) entity;
                List<Result> designerResultList = resultService.findByDesignerId(designer.getId());
                model.addAttribute("resultList", designerResultList);
                for(Result result: designerResultList){
                    scriptList.add(scriptService.findById(result.getScriptid()));
                }
                model.addAttribute("scriptList",scriptList);
                return new ModelAndView("/designerResult");
            default:
                return new ModelAndView("/index");
        }
    }
    //to print detail of a result
    @RequestMapping("/printResult{id}")
    public ModelAndView printResult(@PathVariable long id, Model model, HttpServletRequest request) throws IOException {
        Object entity = request.getSession().getAttribute("entity");
        Result result = resultService.findById(id);
        String resultText = FileService.readFile(result.getResultPath());
        model.addAttribute("result",result);
        model.addAttribute("resultText",resultText);
        Script script = scriptService.findById(result.getScriptid());
        model.addAttribute("script",script);
        switch (entity.getClass().toString()){
            case "class com.autoscript.springproject.domain.User":
                return new ModelAndView("/userResultDetail");
            case "class com.autoscript.springproject.domain.Designer":
                return new ModelAndView("/designerResultDetail");
            default:
                return new ModelAndView("/index");
        }
    }
    //to download a result
    @RequestMapping("/downloadResult{id}")
    public void downloadResult(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {

        Result result = resultService.findById(id);
        try (
                InputStream inputStream = new FileInputStream(new File(result.getResultPath()));
                OutputStream outputStream = response.getOutputStream()
        ) {
            //指明为下载
            response.setContentType("application/x-download");
            String fileName = "result.txt";
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);   // 设置文件名


            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);

            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
