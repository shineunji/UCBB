package ai.maum.ucbb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Entities")
public class EntitiesEntity implements Serializable {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "entity")
  private String entity;

  @OneToMany(mappedBy = "entityId", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<AttributesEntity> attributesEntities;
}
