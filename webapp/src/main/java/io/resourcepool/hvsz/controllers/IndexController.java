package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  @Autowired
  private StatusService statusService;

  /**
   * Get the index page.
   * @return String (index vue)
   */
  @GetMapping("/")
  public String index() {
    return "index";
  }

}