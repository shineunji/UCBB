package ai.maum.ucbb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Attributes")
public class attributes implements Serializable {
  @Column(name = "id")
  private Integer id;
  @Column(name = "attribute")
  private String attribute;
  @Column(name = "entityId")
  private Integer entityId;
}