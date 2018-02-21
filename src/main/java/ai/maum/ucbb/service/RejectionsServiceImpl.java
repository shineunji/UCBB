package ai.maum.ucbb.service;

import ai.maum.ucbb.repository.RejectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RejectionsServiceImpl implements RejectionsService{

  @Autowired
  private RejectionsRepository rejectionsRepository;
}
