package com.autoscript.springproject.service;

import com.autoscript.springproject.api.ScriptRepository;
import com.autoscript.springproject.domain.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
public class ScriptService {

    @Autowired
    private ScriptRepository scriptRepository;

    public Script addScript(Script script){
        return scriptRepository.save(script);
    }
    //constrains between designer and script
    @Transactional
    public void linkToDesignerAndScript(long did, long sid){
        scriptRepository.insertToLink(did, sid);
    }
    //generate a path for script, if needed, change it
    public String pathGenerator(long id, String name){
        System.out.println("\\" + id + "\\" + name);
        return ("D:\\scriptStorage\\" + id + "\\"+ Calendar.getInstance().getTime().getTime() + "\\" + name);
    }
    //find all script
    public List<Script> findall(){
        return scriptRepository.findAll();
    }
    //get next id
    public long getNext(){
        return scriptRepository.getNext();
    }
    //get designer name
    public String getDesignerName(long sid){
        try {
            return scriptRepository.findByScriptId(sid).get(0);
        }
        catch (Exception e){
            return "被注销";
        }
    }
    //update a script's flag to pass or to failed or check
    @Transactional
    public void updateFlag(long scriptid, String flag){
        scriptRepository.updateFlag(flag, scriptid);
    }
    //find by script id
    public Script findById(long id){
        if (scriptRepository.exist(id).size() == 1) {
            Script script = scriptRepository.findById(id).get();
            return script;
        }
        else
            return new Script("*DELETED*",0,"None","FAILED","Be deleted","*");
    }
    //delete by id
    @Transactional
    public void deleteOne(long designerid, long scriptid) {
        scriptRepository.deleteConstrainScriptWithDesigner(designerid,scriptid);
        scriptRepository.deleteConstrainResult(scriptid);
        scriptRepository.deleteById(scriptid);
    }
    //find all passed script
    public List<Script> findAllPass(){
        return scriptRepository.findAllPass();
    }
    //find by designer id
    public List<Script> findByDesignerId(long designerId){
        return scriptRepository.findByDesignerId(designerId);
    }
    //delete by admin by id
    @Transactional
    public void deleteByAdmin(long id) {
        scriptRepository.deleteById(id);

    }
    //search script
    public List<Script> search(String search){
        return scriptRepository.search("%"+search+"%");
    }
    //show java script
    public List<Script> showJava(){
        return scriptRepository.showJava();
    }
    //show c script
    public List<Script> showC() {
        return scriptRepository.showC();
    }
    //show python script
    public List<Script> showPython() {
        return scriptRepository.showPython();
    }
}
