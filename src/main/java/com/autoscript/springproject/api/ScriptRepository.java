package com.autoscript.springproject.api;

import com.autoscript.springproject.domain.Designer;
import com.autoscript.springproject.domain.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script,Long> {
    @Query(value = "SELECT * from script s join designer_script ds on s.SCRIPT_ID = ds.SCRIPT_ID WHERE ds.DESIGNER_ID=?1", nativeQuery=true)
    List<Script> findByDesignerId(long id);

    @Modifying
    @Query(value = "insert into designer_script (DESIGNER_ID, SCRIPT_ID) values (?, ?)", nativeQuery=true)
    void insertToLink(long did, long sid);

    @Query(value = "SELECT * from hibernate_sequence limit 1", nativeQuery=true)
    long getNext();

    @Query(value = "SELECT d.DESIGNER_NAME from designer d join designer_script ds on d.DESIGNER_ID = ds.DESIGNER_ID WHERE ds.SCRIPT_ID=?1", nativeQuery=true)
    List<String> findByScriptId(long id);

    @Modifying
    @Query(value = "update script set FLAG=?1 WHERE SCRIPT_ID=?2", nativeQuery=true)
    void updateFlag(String flag, long scriptid);

    @Query(value = "SELECT * FROM script where FLAG='PASS'", nativeQuery = true)
    List<Script> findAllPass();

    @Modifying
    @Query(value = "DELETE FROM designer_script WHERE DESIGNER_ID=?1 and SCRIPT_ID=?2", nativeQuery = true)
    void deleteConstrainScriptWithDesigner(long designerid,long resultid);

    @Modifying
    @Query(value = "DELETE FROM designer_script WHERE SCRIPT_ID=?1", nativeQuery = true)
    void deleteConstrainScriptWithDesignerByAdmin(long resultid);

    @Modifying
    @Query(value = "DELETE FROM result WHERE SCRIPT_ID=?1", nativeQuery = true)
    void deleteConstrainResult(long id);

    @Query(value = "SELECT * FROM script where SCRIPT_NAME LIKE ?",nativeQuery = true)
    List<Script> search(String search);

    @Query(value = "SELECT * FROM script where LANGUAGE='Java'",nativeQuery = true)
    List<Script> showJava();

    @Query(value = "SELECT * FROM script where LANGUAGE='C'",nativeQuery = true)
    List<Script> showC();

    @Query(value = "SELECT * FROM script where LANGUAGE='Python'",nativeQuery = true)
    List<Script> showPython();

    @Query(value = "SELECT * FROM script where SCRIPT_ID=?1", nativeQuery = true)
    List<Script> exist(long id);
}
