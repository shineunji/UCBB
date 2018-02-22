package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.SettingsEntity;
import java.util.List;

public interface SettingsService {

  List<SettingsEntity> getSettingsList();
  Boolean checkExists(SettingsEntity param);

  void insertSetting(SettingsEntity param);
  void updateSetting(SettingsEntity param);
  void deleteSetting(SettingsEntity param);
}
