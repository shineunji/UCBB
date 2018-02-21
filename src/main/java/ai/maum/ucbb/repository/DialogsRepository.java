package ai.maum.ucbb.repository;

import ai.maum.ucbb.entity.DialogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogsRepository extends JpaRepository<DialogsEntity, String> {

}
