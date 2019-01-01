package com.autoscript.springproject.api;

import com.autoscript.springproject.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.BitSet;
import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long> {
    @Modifying
    @Query(value = "insert into designer_result (DESIGNER_ID, RESULT_ID) values (?, ?)", nativeQuery=true)
    void insertToLinkDesigner(long did, long rid);

    @Modifying
    @Query(value = "insert into user_result (USER_ID, RESULT_ID) values (?, ?)", nativeQuery=true)
    void insertToLinkUser(long uid, long rid);

    @Query(value = "SELECT * from hibernate_sequence limit 1", nativeQuery=true)
    long getNext();

    @Query(value = "SELECT * from result r join designer_result dr on r.RESULT_ID = dr.RESULT_ID WHERE dr.DESIGNER_ID=?1", nativeQuery=true)
    List<Result> findByDesignerId(long id);

    @Query(value = "SELECT * from result r join user_result ur on r.RESULT_ID = ur.RESULT_ID WHERE ur.USER_ID=?1", nativeQuery=true)
    List<Result> findByUserId(long id);

}
