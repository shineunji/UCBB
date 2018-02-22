package ai.maum.ucbb.repository;

import ai.maum.ucbb.entity.UpdateStatusEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateStatusRepository extends JpaRepository<UpdateStatusEntity, Integer>{

  @Modifying
  @Transactional
  @Query(value = "UPDATE UpdateStatusEntity u"
      + "         SET u.statusCode = :statusCode "
      + "         WHERE u.id = :id ")
  void updateStatus(@Param("id") Integer id, @Param("statusCode") String statusCode);
}
