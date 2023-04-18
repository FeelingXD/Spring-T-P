package com.fastcampus.jpalecture.jpapeeknapply.controller;

import com.fastcampus.jpalecture.jpapeeknapply.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class BasicController {
  static final Logger log = LoggerFactory.getLogger(BasicController.class);
  @Autowired
  MemberService memberService;

  @RequestMapping("/")
  public String hello(Model model) {
    log.info("hello");
    model.addAttribute("message", "hello, world");
    return "hello";
  }

}