package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.UpdateStatusEntity;
import ai.maum.ucbb.repository.UpdateStatusRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStatusServiceImpl implements UpdateStatusService{

  @Autowired
  private UpdateStatusRepository updateStatusRepository;

  @Override
  public List<UpdateStatusEntity> findUpdateStatusList(UpdateStatusEntity param) {
    return null;
  }

  @Override
  public void insertUpdateStatus(UpdateStatusEntity param) {
    updateStatusRepository.save(param);
  }

  @Override
  public void updateUpdateStatus(UpdateStatusEntity param) {
    updateStatusRepository.updateStatus(param.getId(), param.getStatusCode());
  }
}
