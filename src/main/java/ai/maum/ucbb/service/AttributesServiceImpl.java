package ai.maum.ucbb.service;

import ai.maum.ucbb.repository.AttributesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributesServiceImpl implements AttributesService{

  @Autowired
  private AttributesRepository attributesRepository;
}
