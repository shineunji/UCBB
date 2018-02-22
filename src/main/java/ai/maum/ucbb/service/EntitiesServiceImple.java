package ai.maum.ucbb.service;

import ai.maum.ucbb.entity.EntitiesEntity;
import ai.maum.ucbb.repository.EntitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class EntitiesServiceImple implements EntitiesService{

  @Autowired
  private EntitiesRepository entitiesRepository;

  @Override
  public Page<EntitiesEntity> findEntitiesList(EntitiesEntity param) {
    param.setOrderProperty("entity");
    param.setOrderDirection("desc");

    if("All".equals(param.getType())){
      param.setEntityKeyword(param.getSearchKeyword());
      param.setAttributeKeyword(param.getAttributeKeyword());
    }else if("Entity".equals(param.getType())){
      param.setEntityKeyword(param.getSearchKeyword());
    }else if("Attribute".equals(param.getType())){
      param.setAttributeKeyword(param.getAttributeKeyword());
    }
    return entitiesRepository.findEntitiesList(param.getEntityKeyword(), param.getAttributeKeyword(), param.getPageRequest());
  }
}
