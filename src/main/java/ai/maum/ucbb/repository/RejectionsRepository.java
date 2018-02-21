package ai.maum.ucbb.repository;

import ai.maum.ucbb.entity.RejectionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectionsRepository extends JpaRepository<RejectionsEntity, Integer>{

}
