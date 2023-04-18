package com.fastcampus.jpalecture.jpapeeknapply.controller;

import com.fastcampus.jpalecture.jpapeeknapply.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
public class MemberController {
  static final Logger log = LoggerFactory.getLogger(MemberController.class);
  @Autowired
  MemberService memberService;

  @RequestMapping("/newMember")
  public String addNewMember(Model model) {
    log.info("newMember");
    model.addAttribute("message", "hello new member");
    memberService.addNewMember();
    return "hello";
  }

  @RequestMapping("/getMember")
  public String getMember(Model model) {
    log.info("getMember");
    model.addAttribute("message", "get member finished");
    memberService.findMember(1L);
    return "hello";
  }

  @RequestMapping("/editMember")
  public String editMember(Model model){
    log.info("edit member");
    model.addAttribute("message", "edited");
    memberService.editMember();

    return "hello";
  }

  @RequestMapping("deleteMember")
  public String deleteMember(Model model){
    log.info("delete member");
    model.addAttribute("message", "deleted");
    memberService.delete();

    return "hello";
  }

  @RequestMapping("remove")
  public String remove(Model model) {
    log.info("delete");
    model.addAttribute("hello", "memberRemoved");
    memberService.delete();
    return "hello";
  }
}

