package com.fastcampus.jpalecture.jpapeeknapply.controller;

import com.fastcampus.jpalecture.jpapeeknapply.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
  static final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserService userService;

  @RequestMapping("hello")
  public String hello(Model model){
    log.info("hello");
    model.addAttribute("message", "hello User");
    return "hello";
  }

  @RequestMapping("/getUser")
  public String getUser(Model model){
    log.info("getUser");
    model.addAttribute("message", "get user");
    userService.find(1L);
    return "hello";

  }

  @RequestMapping("/addUser")
  public String addUser(Model model) {
    log.info("add User");
    model.addAttribute("message", "hello new User");
    userService.addUser();
    return "hello";
  }

  @RequestMapping("/addAdmin")
  public String addAdmin(Model model){
    log.info("add Admin");
    model.addAttribute("message", "hello new Admin");
    userService.addAdmin();
    return "hello";
  }

  @RequestMapping("/setGroupToUser")
  public String setGroupToUser(Model model){
    log.info("set group to user");
    model.addAttribute("message", "set group to user");
    userService.setGroupToUser();

    return "hello";
  }

  @RequestMapping("/removeGroupFromUser")
  public String removeGroupToUser(Model model){
    log.info("remove group from user");
    model.addAttribute("message", "remove group from user");
    userService.removeGroupFromUser();

    return "hello";
  }

}

