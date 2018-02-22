package ai.maum.ucbb.repository;

import ai.maum.ucbb.entity.UpdateEntitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateEntitiesRepository extends JpaRepository<UpdateEntitiesEntity, Integer>{

}
