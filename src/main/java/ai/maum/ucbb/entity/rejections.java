package ai.maum.ucbb.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class rejections implements Serializable {
  private long id;
  private String title;
  private String content;
  private Date created_at;
  private Date updated_at;
}
