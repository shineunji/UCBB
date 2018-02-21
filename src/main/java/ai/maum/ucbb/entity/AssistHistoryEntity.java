package ai.maum.ucbb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AssistHistoryEntity implements Serializable {

  private String requestAt;
  private String assistStatus;
  private String modelUuid;
  private String modelName;
  private String assistRequestBlob;
  private String builderRequestData;
  private String builderRequestAt;
  private String builderReturnCode;
  private String builderReturnMsg;
  private String builderReturnAt;
  private String assistOutCode;
  private String assistOutMsg;
  private String assistOutDetail;
  private String assistOutAt;
  private String requestUserId;

  private String skillUuid;
  private String skillName;

  private Integer id;
}
