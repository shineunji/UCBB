package ai.maum.ucbb.service;

import ai.maum.ucbb.repository.EntitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntitiesServiceImple implements  EntitiesService{

  @Autowired
  private EntitiesRepository entitiesRepository;
}
