package ai.maum.ucbb.entity;

import ai.maum.common.entity.PageParameters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "UpdateStatus")
public class UpdateStatusEntity extends PageParameters implements Serializable{

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "createAt")
  private Date createAt;

  @Column(name = "excuteAt")
  private Date excuteAt;

  @Column(name = "entityCount")
  private Integer entityCount;

  @Column(name = "statusCode")
  private String statusCode;

  @OneToMany(mappedBy = "updateStatusId", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<UpdateEntitiesEntity> updateEntitiesEntities;

  @Transient
  private Date startDate;

  @Transient
  private Date endDate;

  @Transient
  private String searchKeyword;

}
