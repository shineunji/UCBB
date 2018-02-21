package ai.maum.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
public class PageParameters {

  @Transient
  private int pageSize;

  @Transient
  private int pageIndex;

  @Transient
  private String orderDirection;

  @Transient
  private String orderProperty;

  @JsonIgnore
  public PageRequest getPageRequest() {
    Sort sort = null;

    if (orderDirection == null || orderDirection.equals("")) {
      return new PageRequest(this.pageIndex, this.pageSize);
    } else if (orderDirection.equals("asc")) {
      sort = new Sort(Direction.ASC, orderProperty);
    } else if (orderDirection.equals("desc")) {
      sort = new Sort(Direction.DESC, orderProperty);
    }
    return new PageRequest(this.pageIndex, this.pageSize, sort);
  }

}
