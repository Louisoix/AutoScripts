package com.autoscript.springproject.api;

import com.autoscript.springproject.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query(value = "select * from admin where ADMIN_NAME=?1 and ADMIN_PASSWORD=?2", nativeQuery=true)
    List<Admin> findByNameAndPassword(String name, String password);

    @Query(value = "select * from admin where ADMIN_NAME=?1", nativeQuery=true)
    List<Admin> findByName(String name);
}
