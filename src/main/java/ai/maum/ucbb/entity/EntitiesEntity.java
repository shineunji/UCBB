package ai.maum.ucbb.entity;

import ai.maum.common.entity.PageParameters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Entities")
public class EntitiesEntity extends PageParameters implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "entity")
  private String entity;

  @OneToMany(mappedBy = "entityId", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<AttributesEntity> attributesEntities;

  @Transient
  private String type;

  @Transient
  private String searchKeyword;

  @Transient
  private String entityKeyword;

  @Transient
  private String attributeKeyword;
}
