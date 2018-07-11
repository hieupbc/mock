package com.example.mockbus.controllers;

import com.example.mockbus.entities.UserDomain;
import com.example.mockbus.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//@Controller
public class TestController {

  private Logger logger = LogManager.getLogger(TestController.class);
  private UserService userService;

  public TestController(UserService userService) {
    this.userService = userService;
  }


  @PostMapping("/test2")
  public String test2(Model model,
      @ModelAttribute("userInfo") UserDomain userDomain) {

    //model.addAttribute("idUser", idUser);
    System.out.println(userDomain.getId());
    return "userinfo";
  }


}
