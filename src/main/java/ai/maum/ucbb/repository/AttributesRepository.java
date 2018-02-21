package ai.maum.ucbb.repository;

import ai.maum.ucbb.entity.AttributesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributesRepository extends JpaRepository<AttributesEntity, String>{

}
