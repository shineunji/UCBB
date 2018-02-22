package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.UpdateEntitiesEntity;
import ai.maum.ucbb.repository.UpdateEntitiesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UpdateEntitiesServiceImpl implements UpdateEntitiesService{

  @Autowired
  private UpdateEntitiesRepository updateEntitiesRepository;

  @Override
  public void insertUpdateEntities(List<UpdateEntitiesEntity> param) {
    updateEntitiesRepository.save(param);
  }

  @Override
  public void deleteUpdateEntities(List<UpdateEntitiesEntity> param) {
    updateEntitiesRepository.delete(param);
  }
}
