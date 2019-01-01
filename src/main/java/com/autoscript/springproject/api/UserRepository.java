package com.autoscript.springproject.api;

import com.autoscript.springproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from user where USER_NAME=?1 and USER_PASSWORD=?2", nativeQuery=true)
    List<User> findByNameAndPassword(String name, String password);

    @Query(value = "select * from user where USER_NAME=?1", nativeQuery=true)
    List<User> findByName(String name);

    @Query(value = "update user set NICK_NAME=?1 where USER_ID=?2", nativeQuery = true)
    @Modifying
    void updateNickName(String name,long id);

    @Query(value = "update user set USER_PASSWORD=?1 where USER_ID=?2", nativeQuery = true)
    @Modifying
    void updatePassword(String password,long id);

    @Query(value = "select SCRIPT_ID from user_bookmark where USER_ID=?1", nativeQuery=true)
    List<Integer> getUserBookmarks(long user_id);

    @Modifying
    @Query(value = "insert into user_bookmark (USER_ID,SCRIPT_ID) values (?, ?)", nativeQuery=true)
    void insertBookmarkIntoUser(long user_id, long script_id);

    @Modifying
    @Query(value = "DELETE FROM user_bookmark WHERE (USER_ID=?1 and SCRIPT_ID=?2)", nativeQuery = true)
    void deleteBookmarkFromUser(long user_id,long script_id);

    @Query(value = "select USER_ID from user_bookmark where USER_ID=?1 and SCRIPT_ID=?2", nativeQuery=true)
    List<Integer> findByuserandbookmark(long user_id, long script_id);
}
