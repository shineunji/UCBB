package ai.maum.ucbb.controller;


import ai.maum.ucbb.entity.attributes;
import ai.maum.ucbb.entity.dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("api")
public class adminController {
  static final Logger logger = LoggerFactory.getLogger(adminController.class);

  @RequestMapping(
      value = "/systemProperty",
      method = RequestMethod.GET)
  public String systemProperty(Model model) {
    model.addAttribute("attributes",new attributes());
    return "systemProperty";
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String newPost(Model model) {
    model.addAttribute("dialogs", new dialogs());
    return "new";
  }
  @RequestMapping("/login")
  public String login(Model model) {
    System.out.println("login() 실행은 됨");
    model.addAttribute("message", "Hello, World!!!");
    return "/home";
  }
}
