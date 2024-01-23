package com.project.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.entity.UserInfo;
import com.project.service.UserInfoService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserInfoController {

  @Autowired
	private UserInfoService userInfoService;

  @PostMapping("/login")
	public ResponseEntity<UserInfo> login(@RequestBody UserInfo info) {
		return new ResponseEntity<>(userInfoService.login(info),HttpStatus.ACCEPTED);
	}
  @PostMapping("/save")
  public ResponseEntity<Map<String, String>> save(@RequestBody UserInfo userInfo) {
    Map<String, String> response = new HashMap<>();
    response.put("status", userInfoService.save(userInfo));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }



}
