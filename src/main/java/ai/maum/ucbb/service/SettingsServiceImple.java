package ai.maum.ucbb.service;

import ai.maum.ucbb.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImple implements SettingsService{

  @Autowired
  private SettingsRepository settingsRepository;
}
