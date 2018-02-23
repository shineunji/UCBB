package ai.maum.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashMap;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
public class PageParameters implements Serializable{

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
      return new PageRequest(this.pageIndex, 10);
    } else if (orderDirection.equals("asc")) {
      sort = new Sort(Direction.ASC, orderProperty);
    } else if (orderDirection.equals("desc")) {
      sort = new Sort(Direction.DESC, orderProperty);
    }
    return new PageRequest(this.pageIndex, 10, sort);
  }

  @JsonIgnore
  public void setPageIndex(HashMap<String, String> map){
    if(map.get("page_no") != null) this.pageIndex = Integer.parseInt(map.get("page_no"))-1;
    else this.pageIndex = 0;
  }

}
