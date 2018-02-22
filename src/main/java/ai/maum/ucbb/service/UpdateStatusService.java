package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.UpdateStatusEntity;
import java.util.List;

public interface UpdateStatusService {

  List<UpdateStatusEntity> findUpdateStatusList(UpdateStatusEntity param);

  void insertUpdateStatus(UpdateStatusEntity param);
  void updateUpdateStatus(UpdateStatusEntity param);
}
