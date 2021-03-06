package ai.maum.ucbb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@Table(name = "Dialogs1")
public class DialogsEntity implements Serializable {

  @Id
  @Column(name = "seq")
  private Integer seq;

  @Column(name = "entityId")
  private Integer entityId;

  @Column(name = "attributeId")
  private Integer attributeId;

  @Column(name = "count")
  private Integer count;

}
