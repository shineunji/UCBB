package ai.maum.ucbb.repository;

import ai.maum.ucbb.entity.UpdateStatusEntity;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateStatusRepository extends JpaRepository<UpdateStatusEntity, Integer> {

  @Query(value = "SELECT u"
      + "         FROM UpdateStatusEntity u"
      + "         WHERE (u.createAt BETWEEN :startDate AND :endDate)"
      + "         AND u.id in(SELECT ue "
      + "                     FROM UpdateEntitiesEntity ue"
      + "                     WHERE (ue.name LIKE CONCAT('%',:searchKeyword,'%') OR :searchKeyword IS NULL))")
  Page<UpdateStatusEntity> findUpdateStatusList(@Param("startDate") Date startDate,
      @Param("endDate") Date endDate, @Param("searchKeyword") String searchKeyword, Pageable pageable);


  @Modifying
  @Transactional
  @Query(value = "UPDATE UpdateStatusEntity u"
      + "         SET u.statusCode = :statusCode "
      + "         WHERE u.id = :id ")
  void updateStatus(@Param("id") Integer id, @Param("statusCode") String statusCode);
}
