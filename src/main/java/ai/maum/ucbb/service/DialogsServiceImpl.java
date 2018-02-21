package ai.maum.ucbb.service;

import ai.maum.ucbb.repository.DialogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DialogsServiceImpl implements DialogsService{

  @Autowired
  private DialogsRepository dialogsRepository;
}
