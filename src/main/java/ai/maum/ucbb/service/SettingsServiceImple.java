package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.SettingsEntity;
import ai.maum.ucbb.repository.SettingsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImple implements SettingsService{

  @Autowired
  private SettingsRepository settingsRepository;

  @Override
  public List<SettingsEntity> getSettingsList() {
    return settingsRepository.findAll();
  }

  @Override
  public Boolean checkExists(SettingsEntity param) {
    return settingsRepository.exists(param.getSetting());
  }

  @Override
  public void insertSetting(SettingsEntity param) {
    settingsRepository.save(param);
  }

  @Override
  public void updateSetting(SettingsEntity param) {
    settingsRepository.updateValue(param.getValue(), param.getSetting());
  }

  @Override
  public void deleteSetting(SettingsEntity param) {
    settingsRepository.delete(param.getSetting());
  }
}
