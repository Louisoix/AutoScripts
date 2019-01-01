package com.autoscript.springproject.service;

import com.autoscript.springproject.api.DesignerRepository;
import com.autoscript.springproject.domain.Designer;
import com.autoscript.springproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class DesignerService {

    @Autowired
    private DesignerRepository designerRepository;
    //check whether the password correct
    public boolean checkPassword(String name, String input_password){
        List<Designer> designerList = designerRepository.findByNameAndPassword(name,input_password);
        if(designerList.size() == 1){
            return true;
        }
        else{
            return false;
        }
    }
    //check if the designer email exist
    public boolean checkName(String name){
        List<Designer> designerList = designerRepository.findByName(name);
        if (designerList.size() == 1){
            return true;
        }
        else
            return false;
    }
    //get a designer by email
    public Designer getByName(String name){
        List<Designer> designerList = designerRepository.findByName(name);
        return designerList.get(0);
    }
    //add designer
    public Designer addDesigner(Designer designer){
        return designerRepository.save(designer);
    }
    //find all designer
    public List<Designer> findAll(){
        return designerRepository.findAll();
    }
    //find by designer id
    public Designer findById(long id) {
        return designerRepository.findById(id).get();
    }
    //update a designer password
    @Transactional
    public void updateUserPassword(String password, long id) {
        designerRepository.updatePassword(password,id);
    }
    //update a designer nickname
    @Transactional
    public void updateUserNickName(String nickName, long id) {
        designerRepository.updateNickName(nickName,id);
    }
    //get designer's bookmark
    public List<Integer>  getDeisngerBookmarks(long designerid){
        return  designerRepository.getDesignerBookmarks(designerid);
    }
    //add a bookmark
    @Transactional
    public void  insertBookmarkIntoDesigner(long designerid, long script_id){
        if (designerRepository.findBydesignerandbookmark(designerid,script_id).size()==0)
            designerRepository.insertBookmarkIntoDesigner(designerid,script_id);
    }
    //delete a designer bookmark
    @Transactional
    public void  deleteBookmarkFromDesigner(long designerid,long script_id){
        designerRepository.deleteBookmarkFromDesigner(designerid,script_id);

    }
    //delete a designer by designer id
    public void deleteById(long id){
        designerRepository.deleteById(id);
    }
}
