package ai.maum.ucbb.service;

import ai.maum.common.entity.Paging;
import ai.maum.ucbb.entity.ConfigEntity;
import ai.maum.ucbb.repository.BuildRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildService {

  @Autowired
  private BuildRepository buildRepository;

  public Object getBuildList(Paging paging) {
    return buildRepository.getBuildList(paging);
  }

  public Object getBuildHistoryList(Map requestParams, Paging paging) {
    return buildRepository.getBuildHistoryList(requestParams, paging);
  }

  public int getBuildListCount() {
    return buildRepository.getBuildListCount();
  }

  public int getBuildHistoryListCount(Map requestParams) {
    return buildRepository.getBuildHistoryListCount(requestParams);
  }

  public Object insertSystemProperty(ConfigEntity configEntity) {
    return buildRepository.insertSystemProperty(configEntity);
  }

  public Object getConfigList(){
    return buildRepository.getConfigList();
  }

  public Object updateSystemProperty(ConfigEntity configEntity) {
    return buildRepository.updateSystemProperty(configEntity);
  }

  public Object deleteSystemProperty(ConfigEntity configEntity) {
    return buildRepository.deleteSystemProperty(configEntity);
  }

  public Object getBuildHistoryListView(Map<String, String> requestParams) {
    return buildRepository.getBuildHistoryListView(requestParams);
  }
}
