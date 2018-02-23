package ai.maum.ucbb.repository;

import ai.maum.ucbb.entity.EntitiesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntitiesRepository extends JpaRepository<EntitiesEntity, Integer>{

  @Query(value = "SELECT e "
      + "         FROM EntitiesEntity e"
      + "         WHERE e.entity LIKE CONCAT('%',:entityKeyword,'%')"
      + "         OR e.id IN(SELECT a.entityId "
      + "                     FROM AttributesEntity a "
      + "                     WHERE a.attribute LIKE CONCAT('%',:attributeKeyword,'%'))")
  Page<EntitiesEntity> findEntitiesList(
      @Param("entityKeyword") String entityKeyword,
      @Param("attributeKeyword") String attributeKeyword,
      Pageable pageable
  );

}
