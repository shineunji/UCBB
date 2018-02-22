package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.UpdateStatusEntity;
import ai.maum.ucbb.repository.UpdateEntitiesRepository;
import ai.maum.ucbb.repository.UpdateStatusRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UpdateStatusServiceImpl implements UpdateStatusService {

  @Autowired
  private UpdateStatusRepository updateStatusRepository;

  @Autowired
  private UpdateEntitiesRepository updateEntitiesRepository;

  @Override
  public Page<UpdateStatusEntity> findUpdateStatusList(UpdateStatusEntity param) {
    return updateStatusRepository
        .findUpdateStatusList(param.getStartDate(), param.getEndDate(), param.getSearchKeyword(),
            param.getPageRequest());
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
