package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.EntitiesEntity;
import org.springframework.data.domain.Page;

public interface EntitiesService {

  Page<EntitiesEntity> findEntitiesList(EntitiesEntity param);
}
