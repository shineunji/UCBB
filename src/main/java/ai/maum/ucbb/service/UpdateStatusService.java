package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.UpdateStatusEntity;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UpdateStatusService {

  Page<UpdateStatusEntity> findUpdateStatusList(UpdateStatusEntity param);

  void insertUpdateStatus(UpdateStatusEntity param);
  void updateUpdateStatus(UpdateStatusEntity param);
}
