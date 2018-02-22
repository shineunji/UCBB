package ai.maum.ucbb.controller;


import ai.maum.ucbb.entity.EntitiesEntity;
import ai.maum.ucbb.entity.SettingsEntity;
import ai.maum.ucbb.service.EntitiesService;
import ai.maum.ucbb.service.SettingsService;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

  static final Logger logger = LoggerFactory.getLogger(AdminController.class);

  @Autowired
  private SettingsService settingsService;

  @Autowired
  private EntitiesService entitiesService;

  @RequestMapping("/login")
  public String login(Model model) {
    System.out.println("login() 실행은 됨");
    model.addAttribute("message", "Hello, World!!!");
    return "/home";
  }


  /********************************************************************************************************************/
  /*********************************** Trigger Setting  ***************************************************************/
  /********************************************************************************************************************/
  @RequestMapping("/triggerSetting")
  public String triggerSetting(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /triggerSetting =======");

    try {

      List<SettingsEntity> result = settingsService.getSettingsList();
      model.addAttribute("result", result);
    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/triggerSetting";
  }

  @RequestMapping("/triggerSetting/insert")
  public String insertSetting(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /triggerSetting/insert =======");

    try {
      SettingsEntity param = new SettingsEntity();
      param.setSetting(requestParams.get("setting"));
      param.setValue(requestParams.get("vlaue"));
      param.setTitle(requestParams.get("title"));
      param.setType(requestParams.get("type"));

      boolean checkExists = settingsService.checkExists(param);

      if(checkExists){
        throw new Exception("Already existing setting :: " + requestParams.get("setting"));
      }else{
        settingsService.insertSetting(param);
        List<SettingsEntity> result = settingsService.getSettingsList();
        model.addAttribute("result", result);
      }
    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/triggerSetting";
  }

  /********************************************************************************************************************/
  /*********************************** Entity Agent *******************************************************************/
  /********************************************************************************************************************/
  @RequestMapping("/eaList")
  public String eaList(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /eaList =======");

    try {


    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/eaList";
  }

  @RequestMapping("/eaUpdateForm")
  public String eaUpdateForm(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /eaUpdateForm =======");

    try {


    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/eaUpdateForm";
  }

  @RequestMapping("/eaUpdateStatus")
  public String eaUpdateStatus(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /eaUpdateStatus =======");

    try {


    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/eaUpdateStatus";
  }



 /* @RequestMapping("/systemProperty")
  public String systemProperty(Model model) {
    //model.addAttribute("list", (List<ConfigEntity>)buildService.getConfigList());
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
  }*/

}
