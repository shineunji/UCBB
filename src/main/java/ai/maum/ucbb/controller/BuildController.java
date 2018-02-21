package ai.maum.ucbb.controller;


import ai.maum.common.entity.Paging;
import ai.maum.ucbb.entity.ConfigEntity;
import ai.maum.ucbb.entity.LockEntity;
import ai.maum.ucbb.service.BuildService;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class BuildController {

  static final Logger logger = LoggerFactory.getLogger(BuildController.class);

  @Autowired
  private BuildService buildService;


  @RequestMapping("/login")
  public String login(Model model) {
    System.out.println("login() 실행은 됨");
    model.addAttribute("message", "Hello, World!!!");
    return "/home";
  }

  @RequestMapping("/systemProperty")
  public String systemProperty(Model model) {
    model.addAttribute("list", (List<ConfigEntity>)buildService.getConfigList());
    return "ucbb/systemProperty";
  }

  @RequestMapping("/buildList")
  public String buildList(@RequestParam Map<String, String> requestParams, Model model) {
    try {
      int totalCount = buildService.getBuildListCount();
      int _page_no = 1;

      if(requestParams.get("page_no") != null) {
        _page_no = Integer.parseInt(requestParams.get("page_no"));
      }
      Paging paging = new Paging();
      paging.setPageNo(_page_no);
      paging.setPageSize(10);
      paging.setTotalCount(totalCount);

      model.addAttribute("paging", paging);
      model.addAttribute("lockList", (List<LockEntity>)buildService.getBuildList(paging));

      System.out.println(model);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return "ucbb/buildList";
  }

  @RequestMapping("/buildHistories")
  public String buildHistories(@RequestParam Map<String, String> requestParams, Model model) {
    try {
      int totalCount = buildService.getBuildHistoryListCount(requestParams);
      int _page_no = 1;

      if(requestParams.get("page_no") != null) {
        _page_no = Integer.parseInt(requestParams.get("page_no"));
      }

      Paging paging = new Paging();
      paging.setPageNo(_page_no);
      paging.setPageSize(10);
      paging.setTotalCount(totalCount);

      model.addAttribute("paging", paging);
      model.addAttribute("list", buildService.getBuildHistoryList(requestParams, paging));
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return "ucbb/buildHistories";
  }

  @RequestMapping("/buildView")
  public String buildView(@RequestParam Map<String, String> requestParams, Model model) {
    System.out.println("buildView() 실행은 됨");
    try {
      model.addAttribute("data", buildService.getBuildHistoryListView(requestParams));
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return "ucbb/buildView";
  }



  @RequestMapping("/insertProperty")
  public String insertProperty(@RequestParam Map<String, String> requestParams, Model model) {
    System.out.println("insertProperty() 실행은 됨");
    try {
      ConfigEntity configEntity = new ConfigEntity();
      configEntity.setCode(requestParams.get("code"));
      configEntity.setValue(requestParams.get("value"));
      configEntity.setType(requestParams.get("type"));
      configEntity.setDesc(requestParams.get("desc"));

      Object result = buildService.insertSystemProperty(configEntity);

      System.out.println(result);
      model.addAttribute("list", (List<ConfigEntity>)buildService.getConfigList());
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return "ucbb/systemProperty";
  }
  @RequestMapping("/updateProperty")
  public String updateProperty(@RequestParam Map<String, String> requestParams, Model model) {
    System.out.println("updateProperty() 실행은 됨");
    try {
      ConfigEntity configEntity = new ConfigEntity();
      configEntity.setId(Integer.parseInt(requestParams.get("id")));
      configEntity.setCode(requestParams.get("code"));
      configEntity.setValue(requestParams.get("value"));
      configEntity.setType(requestParams.get("type"));
      configEntity.setDesc(requestParams.get("desc")  );

      Object result = buildService.updateSystemProperty(configEntity);

      System.out.println(result);
      model.addAttribute("list", (List<ConfigEntity>)buildService.getConfigList());
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return "ucbb/systemProperty";
  }
  @RequestMapping("/deleteProperty")
  public String deleteProperty(@RequestParam Map<String, String> requestParams, Model model) {
    System.out.println("deleteProperty() 실행은 됨");
    try {
      ConfigEntity configEntity = new ConfigEntity();
      configEntity.setId(Integer.parseInt(requestParams.get("id")));

      Object result = buildService.deleteSystemProperty(configEntity);

      System.out.println(result);
      model.addAttribute("list", (List<ConfigEntity>)buildService.getConfigList());
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return "ucbb/systemProperty";
  }

}
