package ai.maum.ucbb.service;

import ai.maum.ucbb.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService{

  @Autowired
  private DeviceRepository deviceRepository;
}
