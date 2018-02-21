package ai.maum.ucbb.repository;

import ai.maum.common.entity.Paging;
import ai.maum.ucbb.entity.AssistHistoryEntity;
import ai.maum.ucbb.entity.ConfigEntity;
import ai.maum.ucbb.entity.LockEntity;
import com.rqlite.Rqlite;
import com.rqlite.RqliteFactory;
import com.rqlite.dto.ExecuteResults;
import com.rqlite.dto.QueryResults;
import com.rqlite.dto.QueryResults.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class BuildRepository {

  @Value("${rqlite.server.proto}")
  private String proto;

  @Value("${rqlite.server.ip}")
  private String ip;

  @Value("${rqlite.server.port}")
  private int port;

  Rqlite rqlite;

  public int getBuildListCount() {
    int cnt = 0;

    if (rqlite == null) {
      rqlite = RqliteFactory.connect(proto, ip, port);
    }

    QueryResults rows = null;
    String query = "SELECT count(*) FROM LOCK";
    rows = rqlite.Query(query, Rqlite.ReadConsistencyLevel.WEAK);

    cnt = Integer.parseInt(rows.results[0].values[0][0].toString());

    //   rows.results[0].values[0][0].hashCode();
    return cnt;
  }

  public Object getBuildList(Paging paging) {

    if (rqlite == null) {
      rqlite = RqliteFactory.connect(proto, ip, port);
    }
    QueryResults rows = null;
    //String query = "SELECT * FROM MODEL_CHATBOT_SKILL ORDER BY REQUEST_AT LIMIT 5 OFFSET 5";
    StringBuffer query = new StringBuffer();
    query.append("SELECT * FROM LOCK");
    query.append(
        " ORDER BY REQUEST_AT LIMIT " + paging.getPageSize() + " OFFSET "
            + (paging.getPageNo() - 1) * paging.getPageSize());

    rows = rqlite.Query(query.toString(), Rqlite.ReadConsistencyLevel.WEAK);
    List<LockEntity> lockEntities = new ArrayList();
    for (Result result : rows.results) {
      for (Object[] value : result.values) {
        LockEntity lockEntity = new LockEntity();
        int col = 0;
        for (String column : result.columns) {
          if ("REQUEST_AT".equalsIgnoreCase(column)) {
            lockEntity.setRequestAt(value[col].toString());
          }
          if ("MODEL_NAME".equalsIgnoreCase(column)) {
            lockEntity.setModelName(value[col].toString());
          }
          if ("MODEL_UUID".equalsIgnoreCase(column)) {
            lockEntity.setModelUuid(value[col].toString());
          }
          if ("SKILL_NAME".equalsIgnoreCase(column)) {
            lockEntity.setSkillName(value[col].toString());
          }
          if ("SKILL_UUID".equalsIgnoreCase(column)) {
            lockEntity.setSkillUuid(value[col].toString());
          }
          col++;
        }
        lockEntities.add(lockEntity);

      }
    }
    return lockEntities;
  }

  public int getBuildHistoryListCount(Map requestParams) {
    int cnt = 0;

    if (rqlite == null) {
      rqlite = RqliteFactory.connect(proto, ip, port);
    }

    QueryResults rows = null;

    String keyword = "";
    String startDate = "";
    String endDate = "";
    if (requestParams.get("keyword") != null) {
      keyword = requestParams.get("keyword").toString();
    }
    if (requestParams.get("startDate") != null) {
      startDate = requestParams.get("startDate").toString();
    }
    if (requestParams.get("endDate") != null) {
      endDate = requestParams.get("endDate").toString();
    }
    //String query = "SELECT * FROM MODEL_CHATBOT_SKILL ORDER BY REQUEST_AT LIMIT 5 OFFSET 5";
    StringBuffer query = new StringBuffer();
    query.append("SELECT count(*) FROM ASSIST_HISTORY WHERE 1=1");
    if (!"".equals(keyword)) {
      query.append(
          " AND " + requestParams.get("type") + " = '" + requestParams.get("keyword") + "'");
    }
    if (!"".equals(startDate) && !"".equals(endDate)) {
      query.append(
          " AND strftime('%Y-%m-%d', REQUEST_AT) BETWEEN \"" + startDate + "\" AND \"" + endDate
              + "\"");
    }

    rows = rqlite.Query(query.toString(), Rqlite.ReadConsistencyLevel.WEAK);

    cnt = Integer.parseInt(rows.results[0].values[0][0].toString());

    return cnt;
  }

  public Object getBuildHistoryList(Map requestParams, Paging paging) {

    if (rqlite == null) {
      rqlite = RqliteFactory.connect(proto, ip, port);
    }
    QueryResults rows = null;
    String keyword = "";
    String startDate = "";
    String endDate = "";
    if (requestParams.get("keyword") != null) {
      keyword = requestParams.get("keyword").toString();
    }
    if (requestParams.get("startDate") != null) {
      startDate = requestParams.get("startDate").toString();
    }
    if (requestParams.get("endDate") != null) {
      endDate = requestParams.get("endDate").toString();
    }
    //String query = "SELECT * FROM MODEL_CHATBOT_SKILL ORDER BY REQUEST_AT LIMIT 5 OFFSET 5";
    StringBuffer query = new StringBuffer();
    query.append("SELECT * FROM ASSIST_HISTORY WHERE 1=1");
    if (!"".equals(keyword)) {
      query.append(
          " AND " + requestParams.get("type") + " = '" + requestParams.get("keyword") + "'");
    }
    if (!"".equals(startDate) && !"".equals(endDate)) {
      query.append(
          " AND strftime('%Y-%m-%d', REQUEST_AT) BETWEEN \"" + startDate + "\" AND \"" + endDate
              + "\"");
    }

    query.append(
        " ORDER BY REQUEST_AT LIMIT " + paging.getPageSize() + " OFFSET "
            + (paging.getPageNo() - 1) * paging.getPageSize());
    rows = rqlite.Query(query.toString(), Rqlite.ReadConsistencyLevel.WEAK);
    List<AssistHistoryEntity> assistHistoryEntitys = new ArrayList<AssistHistoryEntity>();
    for (Result result : rows.results) {
      for (Object[] value : result.values) {
        AssistHistoryEntity assistHistoryEntity = new AssistHistoryEntity();
        int col = 0;
        for (String column : result.columns) {
          if ("ID".equalsIgnoreCase(column)) {
            assistHistoryEntity.setId(Integer.parseInt(value[col].toString()));
          }
          if ("MODEL_NAME".equalsIgnoreCase(column)) {
            assistHistoryEntity.setModelName(value[col].toString());
          }
          if ("MODEL_UUID".equalsIgnoreCase(column)) {
            assistHistoryEntity.setModelUuid(value[col].toString());
          }
          if ("SKILL_NAME".equalsIgnoreCase(column)) {
            assistHistoryEntity.setSkillName(value[col].toString());
          }
          if ("SKILL_UUID".equalsIgnoreCase(column)) {
            assistHistoryEntity.setSkillUuid(value[col].toString());
          }
          if ("REQUEST_AT".equalsIgnoreCase(column)) {
            assistHistoryEntity.setRequestAt(value[col].toString());
          }
          if ("ASSIST_STATUS".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistStatus(value[col].toString());
          }
          if ("ASSIST_REQUEST_BLOB".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistRequestBlob(value[col].toString());
          }
          if ("BUILDER_REQUEST_DATA".equalsIgnoreCase(column)) {
            assistHistoryEntity.setBuilderRequestData(value[col].toString());
          }
          if ("BUILDER_REQUEST_AT".equalsIgnoreCase(column)) {
            assistHistoryEntity.setBuilderRequestAt(value[col].toString());
          }
          if ("BUILDER_RETURN_CODE".equalsIgnoreCase(column)) {
            assistHistoryEntity.setBuilderReturnCode(value[col].toString());
          }
          if ("BUILDER_RETURN_MSG".equalsIgnoreCase(column)) {
            assistHistoryEntity.setBuilderReturnMsg(value[col].toString());
          }
          if ("BUILDER_RETURN_AT".equalsIgnoreCase(column)) {
            assistHistoryEntity.setRequestAt(value[col].toString());
          }
          if ("ASSIST_OUT_CODE".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistOutCode(value[col].toString());
          }
          if ("ASSIST_OUT_MSG".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistOutMsg(value[col].toString());
          }
          if ("ASSIST_OUT_DETAIL".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistOutDetail(value[col].toString());
          }
          if ("ASSIST_OUT_AT".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistOutAt(value[col].toString());
          }
          if ("REQUEST_USER_ID".equalsIgnoreCase(column)) {
            assistHistoryEntity.setRequestUserId(value[col].toString());
          }
          col++;
        }
        assistHistoryEntitys.add(assistHistoryEntity);
      }
    }
    return assistHistoryEntitys;
  }

  public Object getBuildHistoryListView(Map requestParams) {

    if (rqlite == null) {
      rqlite = RqliteFactory.connect(proto, ip, port);
    }
    QueryResults rows = null;
    //String query = "SELECT * FROM MODEL_CHATBOT_SKILL ORDER BY REQUEST_AT LIMIT 5 OFFSET 5";
    StringBuffer query = new StringBuffer();
    query.append("SELECT * FROM ASSIST_HISTORY");
    query.append(" WHERE ID = " + requestParams.get("id"));

    rows = rqlite.Query(query.toString(), Rqlite.ReadConsistencyLevel.WEAK);
    AssistHistoryEntity assistHistoryEntity = new AssistHistoryEntity();
    for (Result result : rows.results) {
      for (Object[] value : result.values) {
        int col = 0;
        for (String column : result.columns) {
          if ("ID".equalsIgnoreCase(column)) {
            assistHistoryEntity.setId(Integer.parseInt(value[col].toString()));
          }
          if ("MODEL_NAME".equalsIgnoreCase(column)) {
            assistHistoryEntity.setModelName(value[col].toString());
          }
          if ("MODEL_UUID".equalsIgnoreCase(column)) {
            assistHistoryEntity.setModelUuid(value[col].toString());
          }
          if ("SKILL_NAME".equalsIgnoreCase(column)) {
            assistHistoryEntity.setSkillName(value[col].toString());
          }
          if ("SKILL_UUID".equalsIgnoreCase(column)) {
            assistHistoryEntity.setSkillUuid(value[col].toString());
          }
          if ("REQUEST_AT".equalsIgnoreCase(column)) {
            assistHistoryEntity.setRequestAt(value[col].toString());
          }
          if ("ASSIST_STATUS".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistStatus(value[col].toString());
          }
          if ("ASSIST_REQUEST_BLOB".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistRequestBlob(value[col].toString());
          }
          if ("BUILDER_REQUEST_DATA".equalsIgnoreCase(column)) {
            assistHistoryEntity.setBuilderRequestData(value[col].toString());
          }
          if ("BUILDER_REQUEST_AT".equalsIgnoreCase(column)) {
            assistHistoryEntity.setBuilderRequestAt(value[col].toString());
          }
          if ("BUILDER_RETURN_CODE".equalsIgnoreCase(column)) {
            assistHistoryEntity.setBuilderReturnCode(value[col].toString());
          }
          if ("BUILDER_RETURN_MSG".equalsIgnoreCase(column)) {
            assistHistoryEntity.setBuilderReturnMsg(value[col].toString());
          }
          if ("BUILDER_RETURN_AT".equalsIgnoreCase(column)) {
            assistHistoryEntity.setRequestAt(value[col].toString());
          }
          if ("ASSIST_OUT_CODE".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistOutCode(value[col].toString());
          }
          if ("ASSIST_OUT_MSG".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistOutMsg(value[col].toString());
          }
          if ("ASSIST_OUT_DETAIL".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistOutDetail(value[col].toString());
          }
          if ("ASSIST_OUT_AT".equalsIgnoreCase(column)) {
            assistHistoryEntity.setAssistOutAt(value[col].toString());
          }
          if ("REQUEST_USER_ID".equalsIgnoreCase(column)) {
            assistHistoryEntity.setRequestUserId(value[col].toString());
          }
          col++;
        }
      }
    }
    return assistHistoryEntity;
  }

  public Object getConfigList() {

    if (rqlite == null) {
      rqlite = RqliteFactory.connect(proto, ip, port);
    }
    QueryResults rows = null;
    StringBuffer query = new StringBuffer();
    query.append("SELECT * FROM CONFIG");
    rows = rqlite.Query(query.toString(), Rqlite.ReadConsistencyLevel.WEAK);
    List<ConfigEntity> configEntities = new ArrayList();
    for (Result result : rows.results) {
      for (Object[] value : result.values) {
        ConfigEntity configEntity = new ConfigEntity();
        int col = 0;
        for (String column : result.columns) {
          if ("ID".equalsIgnoreCase(column)) {
            configEntity.setId(Integer.parseInt(value[col].toString()));
          }
          if ("CODE".equalsIgnoreCase(column)) {
            configEntity.setCode(value[col].toString());
          }
          if ("VALUE".equalsIgnoreCase(column)) {
            configEntity.setValue(value[col].toString());
          }
          if ("TYPE".equalsIgnoreCase(column)) {
            configEntity.setType(value[col].toString());
          }
          if ("DESC".equalsIgnoreCase(column)) {
            configEntity.setDesc(value[col].toString());
          }
          col++;
        }
        configEntities.add(configEntity);

      }
    }
    return configEntities;

  }

  public Object insertSystemProperty(ConfigEntity configEntity) {
    if (rqlite == null) {
      rqlite = RqliteFactory.connect(proto, ip, port);
    }
    StringBuffer query = new StringBuffer();
    query.append("INSERT INTO CONFIG(CODE, VALUE, TYPE, DESC) VALUES(");
    query.append("'" + configEntity.getCode() + "', ");
    query.append("'" + configEntity.getValue() + "', ");
    query.append("'" + configEntity.getType() + "', ");
    query.append("'" + configEntity.getDesc() + "' ) ");

    ExecuteResults results = null;
    results = rqlite.Execute(query.toString());
    System.out.println(results.toString());
    return results;
  }

  public Object updateSystemProperty(ConfigEntity configEntity) {
    if (rqlite == null) {
      rqlite = RqliteFactory.connect(proto, ip, port);
    }
    StringBuffer query = new StringBuffer();
    query.append("UPDATE CONFIG SET ");
    query.append("CODE = '" + configEntity.getCode() + "', ");
    query.append("VALUE = '" + configEntity.getValue() + "', ");
    query.append("TYPE = '" + configEntity.getType() + "', ");
    query.append("DESC = '" + configEntity.getDesc() + "'  ");
    query.append("WHERE ID = " + configEntity.getId());

    ExecuteResults results = null;
    results = rqlite.Execute(query.toString());
    System.out.println(results.toString());
    return results;
  }

  public Object deleteSystemProperty(ConfigEntity configEntity) {
    if (rqlite == null) {
      rqlite = RqliteFactory.connect(proto, ip, port);
    }
    StringBuffer query = new StringBuffer();
    query.append("DELETE FROM CONFIG WHERE ID = " + configEntity.getId());

    ExecuteResults results = null;
    results = rqlite.Execute(query.toString());
    System.out.println(results.toString());
    return results;
  }
}
