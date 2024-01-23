package com.project.repository;

import com.project.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Serializable> {
	
	UserInfo findByUemailAndUpass(String uemail, String upass);
}

