package ai.maum.ucbb.repository;

import ai.maum.ucbb.entity.SettingsEntity;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SettingsRepository extends JpaRepository<SettingsEntity, String>{

  @Modifying
  @Transactional
  @Query(value = "UPDATE SettingsEntity s"
      + "         SET s.value = :value "
      + "         WHERE s.setting = :setting ")
  void updateValue(@Param("value") String value, @Param("setting") String setting);
}
