package ai.maum.ucbb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "Device")
public class DeviceEntity implements Serializable {

  @Id
  @Column(name = "deviceId")
  private String deviceId;

  @Column(name = "count")
  private Integer count;

  @Column(name = "status")
  private String status;

  @Column(name = "createAt")
  private Date createAt;

  @Column(name = "updateAt")
  private Date updateAt;
}
