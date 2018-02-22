package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.UpdateEntitiesEntity;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UpdateEntitiesService {

  void insertUpdateEntities(List<UpdateEntitiesEntity> param);
  void deleteUpdateEntities(List<UpdateEntitiesEntity> param);
}
