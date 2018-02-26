package ai.maum.ucbb.controller;


import ai.maum.common.entity.Paging;
import ai.maum.ucbb.entity.EntitiesEntity;
import ai.maum.ucbb.entity.SettingsEntity;
import ai.maum.ucbb.entity.UpdateStatusEntity;
import ai.maum.ucbb.service.AttributesService;
import ai.maum.ucbb.service.EntitiesService;
import ai.maum.ucbb.service.SettingsService;
import ai.maum.ucbb.service.UpdateStatusService;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

  @Autowired
  private AttributesService attributesService;

  @Autowired
  private UpdateStatusService updateStatusService;

  @RequestMapping("/login")
  public String login(Model model) {
    System.out.println("login() 실행은 됨");
    model.addAttribute("message", "Hello, World!!!");
    return "/home";
  }

  @RequestMapping("/dashboard")
  public String dashboard(Model model) {
    logger.info("======= call request :: /dashboard =======");

    try {

    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/ucbb/dashboard";
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
    return "/ucbb/triggerSetting";
  }

  @RequestMapping("/triggerSetting/insert")
  public String insertSetting(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /triggerSetting/insert =======");

    try {
      SettingsEntity param = new SettingsEntity();
      param.setSetting(requestParams.get("setting"));
      param.setValue(requestParams.get("value"));
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
    return "/ucbb/triggerSetting";
  }

  @RequestMapping("/triggerSetting/update")
  public String updateSetting(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /triggerSetting/update =======");

    try {
      SettingsEntity param = new SettingsEntity();
      param.setSetting(requestParams.get("setting"));
      param.setValue(requestParams.get("value"));

      settingsService.updateSetting(param);

      List<SettingsEntity> result = settingsService.getSettingsList();
      model.addAttribute("result", result);
    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/ucbb/triggerSetting";
  }

  @RequestMapping("/triggerSetting/delete")
  public String deleteSetting(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /triggerSetting/delete =======");

    try {
      SettingsEntity param = new SettingsEntity();
      param.setSetting(requestParams.get("setting"));

      settingsService.deleteSetting(param);

      List<SettingsEntity> result = settingsService.getSettingsList();
      model.addAttribute("result", result);
    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/ucbb/triggerSetting";
  }

  /********************************************************************************************************************/
  /*********************************** Entity Agent *******************************************************************/
  /********************************************************************************************************************/
  @RequestMapping("/eaList")
  public String eaList(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /eaList =======");

    try {
      EntitiesEntity param = new EntitiesEntity();
      param.setSearchKeyword(requestParams.get("searchKeyword") == null ? "" : requestParams.get("searchKeyword"));
      param.setType(requestParams.get("type") == null ? "All" : requestParams.get("type"));
      param.setPageIndex(requestParams);

      Page<EntitiesEntity> result = entitiesService.findEntitiesList(param);

      Paging paging = new Paging();
      paging.setPageNo(result.getNumber()+1);
      paging.setPageSize(result.getSize());
      paging.setTotalCount(Long.valueOf(result.getTotalElements()).intValue());

      model.addAttribute("searchKeyword", requestParams.get("searchKeyword"));
      model.addAttribute("type", requestParams.get("type"));
      model.addAttribute("paging", paging);
      model.addAttribute("result", result.getContent());
    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/ucbb/eaList";
  }

  @RequestMapping("/eaUpdateForm")
  public String eaUpdateForm(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /eaUpdateForm =======");

    try {
      /*
      *  grcp 연동 부분
      * */
    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/ucbb/eaUpdateForm";
  }

  @RequestMapping("/eaUpdateForm/execute")
  public String eaUpdateFormExcute(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /eaUpdateForm/execute =======");

    try {
      /*
      *  grcp 연동 부분
      * */

      //model.addAttribute("result", findUpdateStatusList(requestParams));
    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/ucbb/eaUpdateStatus";
  }

  @RequestMapping("/eaUpdateStatus")
  public String eaUpdateStatus(@RequestParam HashMap<String, String> requestParams, Model model) {
    logger.info("======= call request :: /eaUpdateStatus =======");

    String startDate = requestParams.get("startDate");
    String endDate = requestParams.get("endDate");

    try {
      Page<UpdateStatusEntity> result = findUpdateStatusList(requestParams);

      Paging paging = new Paging();
      paging.setPageNo(result.getNumber()+1);
      paging.setPageSize(result.getSize());
      paging.setTotalCount(Long.valueOf(result.getTotalElements()).intValue());

      model.addAttribute("searchKeyword", requestParams.get("searchKeyword"));
      model.addAttribute("startDate", checkEmpty(startDate) ? getTimestamp(-6) : strToTimestamp(startDate));
      model.addAttribute("endDate", checkEmpty(endDate) ? getTimestamp(0) : strToTimestamp(endDate));
      model.addAttribute("paging", paging);
      model.addAttribute("result", result.getContent());
    }catch (Exception e){
      e.printStackTrace();
      model.addAttribute("message", e.getMessage());
    }
    return "/ucbb/eaUpdateStatus";
  }

  public Page<UpdateStatusEntity> findUpdateStatusList(HashMap<String, String> requestParams) {
    String startDate = requestParams.get("startDate");
    String endDate = requestParams.get("endDate");

    UpdateStatusEntity param = new UpdateStatusEntity();
    param.setStartDate(checkEmpty(startDate) ? getDate(-6, "s") : strToDate(startDate, "s"));
    param.setEndDate(checkEmpty(endDate) ? getDate(0, "e") : strToDate(endDate, "e"));
    param.setSearchKeyword(requestParams.get("searchKeyword"));
    param.setPageIndex(requestParams);

    Page<UpdateStatusEntity> result = updateStatusService.findUpdateStatusList(param);

    return result;
  }

  public Date strToDate(String s, String point) {
    String newStr = s;
    try{
      if("s".equals(point)){
        s += " 00:00:00";
      }else if("e".equals(point)){
        s += " 23:59:59";
      }
      return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(s);
    }catch (Exception e){
      return null;
    }
  }

  public Date getDate(int range, String point) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, range);
    if("s".equals(point)){
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.HOUR, 0);
    }else if("e".equals(point)){
      cal.set(Calendar.SECOND, 59);
      cal.set(Calendar.MINUTE, 59);
      cal.set(Calendar.HOUR, 23);
    }
    return cal.getTime();
  }

  public Timestamp strToTimestamp(String s) {
    try{
      return new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(s).getTime());
    }catch (Exception e){
      return null;
    }
  }

  public Timestamp getTimestamp(int range) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, range);
    return new Timestamp(cal.getTimeInMillis());
  }

  public Boolean checkEmpty(String obj) {
    return obj == null || "".equals(obj) ? true : false;
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
