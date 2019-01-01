package com.autoscript.springproject.service;

import com.autoscript.springproject.api.UserRepository;
import com.autoscript.springproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserService {

    @Autowired
    private UserRepository userRepository;
    //whether password correct.
    public boolean checkPassword(String username, String input_password){
        List<User> userList = userRepository.findByNameAndPassword(username,input_password);
        if(userList.size() == 1){
            return true;
        }
        else{
            return false;
        }
    }
    //check email whether exist
    public boolean checkName(String username){
        List<User> userList = userRepository.findByName(username);
        if (userList.size() == 1){
            return true;
        }
        else
            return false;
    }
    //get user by email
    public User getByName(String name){
        List<User> userList = userRepository.findByName(name);
        return userList.get(0);
    }
    //add user
    public User addUser(User user){
        return userRepository.save(user);
    }
    //find all user
    public List<User> findAll(){
        return userRepository.findAll();
    }
    // update nick name
    @Transactional
    public void updateUserNickName(String nickName, long id){
        userRepository.updateNickName(nickName,id);
    }
    //update user password
    @Transactional
    public void updateUserPassword(String password, long id){
        userRepository.updatePassword(password,id);
    }
    //find by user id
    public User findById(long id){
        return userRepository.findById(id).get();
    }

    //get user's bookmark
    public List<Integer> getUserBookmarks(long user_id){
        return  userRepository.getUserBookmarks(user_id);
    }
    //add user bookmark
    @Transactional
    public void  insertBookmarkIntoUser(long user_id, long script_id){
        if (this.findexist(user_id,script_id)){

        }
        else
            userRepository.insertBookmarkIntoUser(user_id,script_id);
    }
    //delete one from user bookmark
    @Transactional
    public void  deleteBookmarkFromUser(long user_id,long script_id){
        userRepository.deleteBookmarkFromUser(user_id,script_id);

    }
    //delete user by user id
    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }
    //find a script whether exist.
    public boolean findexist(long user_id,long script_id){
        if (userRepository.findByuserandbookmark(user_id,script_id).size() != 0){
            return true;
        }
        return false;
    }
}
