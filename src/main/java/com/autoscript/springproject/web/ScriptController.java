package com.autoscript.springproject.web;

import com.autoscript.springproject.domain.*;
import com.autoscript.springproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//a controller for script
@Controller
public class ScriptController {
    @Autowired
    private ScriptService scriptService;
    @Autowired
    private ResultService resultService;

    //the request to submit a script using, redirect to my result page
    @RequestMapping(value = "/submitTest", method = RequestMethod.POST)
    public String use(HttpServletRequest request, RedirectAttributes
            attributes, Model model) {
        System.out.println(request.getParameterMap().keySet().toString());
        String parameterRaw = request.getParameterMap().keySet().toString();
        ArrayList<String> parameterList = new ArrayList<String>();
        StringBuilder parametercmd = new StringBuilder();
        if (parameterRaw.length()>2){
            String a[] = parameterRaw.substring(1, parameterRaw.length()-1).split(",");
            for(String s:a){
                parameterList.add(s.split(":")[1].split("\"")[1]);
            }
            for(String p: parameterList){
                System.out.println(p);
                parametercmd.append(p).append(" ");
            }
        }
        else
            parametercmd.append("");


        long id = Long.parseLong(request.getSession().getAttribute("scriptid").toString());
        System.out.println("script id is "+id +" parameter is "+parametercmd);


        Object entity = request.getSession().getAttribute("entity");
        String type = request.getSession().getAttribute("type").toString();
        System.out.println(type);
        if (type.equals("user")){
            User thisClient = (User) entity;
            System.out.println(id);
            String savePath = "D:\\result\\"+thisClient.getId()+"\\"+ Calendar.getInstance().getTime().getTime() + "\\" + id;
            Script thisScript = scriptService.findById(id);
            long resultid = resultService.getNext();
            resultService.compile(thisScript.getPath(),thisScript.getLanguage(),savePath, parametercmd.toString());
            resultService.addResult(new Result(savePath+ "\\" + "result.txt",id));
            resultService.linkToUserAndResult(thisClient.getId(),resultid);
        }

        if (type.equals("designer")){
            Designer thisClient = (Designer) entity;
            System.out.println(id);
            String savePath = "D:\\result\\"+thisClient.getId()+"\\"+ Calendar.getInstance().getTime().getTime() + "\\" + id;
            Script thisScript = scriptService.findById(id);
            long resultid = resultService.getNext();
            resultService.compile(thisScript.getPath(),thisScript.getLanguage(),savePath, parametercmd.toString());
            resultService.addResult(new Result(savePath+ "\\" + "result.txt",id));
            resultService.linkToDesignerAndResult(thisClient.getId(),resultid);
        }

        return "redirect:/MyResults";
    }

    //use a script
    @RequestMapping("/use{id}")
    public ModelAndView _use(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes, Model model){
        List<Integer> parameterList = new ArrayList<Integer>();
        request.getSession().setAttribute("scriptid",id);
        model.addAttribute("scriptid",id);
        model.addAttribute("description", scriptService.findById(id).getDescription());
        for(int i=0;i<scriptService.findById(id).getParameter();i++)
            parameterList.add(1);
        model.addAttribute("parameterList",parameterList);

        if (request.getSession().getAttribute("type").equals("user")){
            return new ModelAndView("userUseScript");
        }
        if (request.getSession().getAttribute("type").equals("designer")){
            return new ModelAndView("designerUseScript");
        }
        return new ModelAndView("index");

    }
    // access scriptmarket page
    @RequestMapping("/scriptsMarket")
    public ModelAndView scriptsMaket(Model model, HttpServletRequest request, RedirectAttributes
            attributes) throws IOException {
        List<Script> scriptList = scriptService.findAllPass();
        model.addAttribute("scriptList",scriptList);
        List<String> namelist = new ArrayList<String>();
        for(Script s: scriptList){
            namelist.add(scriptService.getDesignerName(s.getId()));
        }
        model.addAttribute("nameList",namelist);
        String type = request.getSession().getAttribute("type").toString();
        if(type.equals("user"))
            return new ModelAndView("userViewScriptsMarket");
        if(type.equals("designer"))
            return new ModelAndView("designerViewScriptsMarket");
        else
            return new ModelAndView("index");
    }
    //to access a specific script detail page
    @GetMapping("/scriptDetail{id}")
    public ModelAndView saveResult(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes, Model model) throws IOException {
        String type = request.getSession().getAttribute("type").toString();
        Script thisScript = scriptService.findById(id);
        model.addAttribute("script",thisScript);
        if(type.equals("admin")){
            model.addAttribute("code",FileService.readFile(thisScript.getPath()));
            return new ModelAndView("adminScriptDetail");
        }
        if(type.equals("user")){
            return new ModelAndView("userScriptDetail");
        }
        if(type.equals("designer")){
            return new ModelAndView("designerScriptDetail");
        }
        return new ModelAndView("index");
    }
    //check designer itself script
    @RequestMapping("/MyScripts")
    public ModelAndView myScripts(Model model, HttpServletRequest request, RedirectAttributes
            attributes) throws IOException {
        Designer designer = (Designer) request.getSession().getAttribute("entity");
        List<Script> scriptList = scriptService.findByDesignerId(designer.getId());
        model.addAttribute("scriptList",scriptList);
        List<String> namelist = new ArrayList<String>();
        for(Script s: scriptList){
            namelist.add(scriptService.getDesignerName(s.getId()));
        }
        model.addAttribute("nameList",namelist);
        String type = request.getSession().getAttribute("type").toString();
        if(type.equals("designer"))
            return new ModelAndView("designerScript");
        else
            return new ModelAndView("index");
    }
    //delete a script by id
    @GetMapping("/deleteScript{id}")
    public String deleteScript(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes){
        String type = request.getSession().getAttribute("type").toString();
        if(type.equals("admin")){
            scriptService.deleteByAdmin(id);
            return "redirect:/ScriptManage";
        }
        if(type.equals("designer")){
            Designer designer = (Designer) request.getSession().getAttribute("entity");
            scriptService.deleteOne(designer.getId(),id);
            return "redirect:/MyScripts";
        }
        return "redirect:/index";
    }
    //for admin to access script manage page
    @RequestMapping("/ScriptManage")
    public String manager(Model model, HttpServletRequest request, RedirectAttributes
            attributes) throws IOException {
        List<Script> scriptList = scriptService.findall();
        model.addAttribute("scriptList",scriptList);
        List<String> namelist = new ArrayList<String>();
        for(Script s: scriptList){
            namelist.add(scriptService.getDesignerName(s.getId()));
        }
        model.addAttribute("nameList",namelist);
        return "scriptManage";
    }
    //admin pass a script
    @GetMapping("/pass{id}")
    public String passScript(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes){
        scriptService.updateFlag(id,Script.PASS);
        return "redirect:/ScriptManage";
    }
    //admin failed a script
    @GetMapping("/failed{id}")
    public String failedScript(@PathVariable long id, HttpServletRequest request, RedirectAttributes
            attributes){
        scriptService.updateFlag(id,Script.FAILED);
        return "redirect:/ScriptManage";
    }
    //search engine for client
    @RequestMapping("/searchScript")
    public ModelAndView searchScript(@RequestParam(value = "searching") String search, HttpServletRequest request, RedirectAttributes
            attributes,Model model){
        System.out.println("search: "+search);
        List<Script> scriptList = scriptService.search(search);
        model.addAttribute("scriptList",scriptList);
        List<String> namelist = new ArrayList<String>();
        for(Script s: scriptList){
            namelist.add(scriptService.getDesignerName(s.getId()));
        }
        model.addAttribute("nameList",namelist);
        if(request.getSession().getAttribute("type").equals("user"))
            return new ModelAndView("userViewScriptsMarket");
        else
            return new ModelAndView("designerViewScriptsMarket");
    }
    //show java language script
    @RequestMapping("/showJava")
    public ModelAndView showJava(HttpServletRequest request, RedirectAttributes
            attributes,Model model){
        List<Script> scriptList = scriptService.showJava();
        model.addAttribute("scriptList",scriptList);
        List<String> namelist = new ArrayList<String>();
        for(Script s: scriptList){
            namelist.add(scriptService.getDesignerName(s.getId()));
        }
        model.addAttribute("nameList",namelist);
        if(request.getSession().getAttribute("type").equals("user"))
            return new ModelAndView("userViewScriptsMarket");
        else
            return new ModelAndView("designerViewScriptsMarket");
    }
    //show c language script
    @RequestMapping("/showC")
    public ModelAndView showC(HttpServletRequest request, RedirectAttributes
            attributes,Model model){
        List<Script> scriptList = scriptService.showC();
        model.addAttribute("scriptList",scriptList);
        List<String> namelist = new ArrayList<String>();
        for(Script s: scriptList){
            namelist.add(scriptService.getDesignerName(s.getId()));
        }
        model.addAttribute("nameList",namelist);
        if(request.getSession().getAttribute("type").equals("user"))
            return new ModelAndView("userViewScriptsMarket");
        else
            return new ModelAndView("designerViewScriptsMarket");
    }
    //show python type script
    @RequestMapping("/showPython")
    public ModelAndView showPython(HttpServletRequest request, RedirectAttributes
            attributes,Model model){
        List<Script> scriptList = scriptService.showPython();
        model.addAttribute("scriptList",scriptList);
        List<String> namelist = new ArrayList<String>();
        for(Script s: scriptList){
            namelist.add(scriptService.getDesignerName(s.getId()));
        }
        model.addAttribute("nameList",namelist);
        if(request.getSession().getAttribute("type").equals("user"))
            return new ModelAndView("userViewScriptsMarket");
        else
            return new ModelAndView("designerViewScriptsMarket");
    }
}
