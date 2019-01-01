package com.autoscript.springproject.api;

import com.autoscript.springproject.domain.Designer;
import com.autoscript.springproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DesignerRepository extends JpaRepository<Designer,Long> {
    @Query(value = "select * from designer where DESIGNER_NAME=?1 and DESIGNER_PASSWORD=?2", nativeQuery=true)
    List<Designer> findByNameAndPassword(String name, String password);

    @Query(value = "select * from designer where DESIGNER_NAME=?1", nativeQuery=true)
    List<Designer> findByName(String name);

    @Query(value = "update designer set NICK_NAME=?1 where DESIGNER_ID=?2", nativeQuery = true)
    @Modifying
    void updateNickName(String name,long id);

    @Query(value = "update designer set DESIGNER_PASSWORD=?1 where DESIGNER_ID=?2", nativeQuery = true)
    @Modifying
    void updatePassword(String password,long id);

    @Query(value = "select SCRIPT_ID from designer_bookmark where DESIGNER_ID=?1", nativeQuery=true)
    List<Integer> getDesignerBookmarks(long designer_id);

    @Modifying
    @Query(value = "insert into designer_bookmark (DESIGNER_ID,SCRIPT_ID) values (?, ?)", nativeQuery=true)
    void insertBookmarkIntoDesigner(long designer_id, long script_id);

    @Modifying
    @Query(value = "DELETE FROM designer_bookmark WHERE (DESIGNER_ID=?1 and SCRIPT_ID=?2)", nativeQuery = true)
    void deleteBookmarkFromDesigner(long designer_id,long script_id);

    @Query(value = "select DESIGNER_ID from designer_bookmark where DESIGNER_ID=?1 and SCRIPT_ID=?2", nativeQuery=true)
    List<Integer> findBydesignerandbookmark(long designer_id, long script_id);
}
