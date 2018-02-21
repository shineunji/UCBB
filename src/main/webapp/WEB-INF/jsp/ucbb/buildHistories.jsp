<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/includeTop.jsp" flush="true">
    <jsp:param name="menu" value="buildHistories"/>
</jsp:include>

<script type="text/javascript">
  function goPage(page) {
    var frm = document.HISTORIES_LIST_FORM;
    frm.action = "<c:url value="/api/buildHistories"/>";
    frm.page_no.value = page;
    frm.submit();
  }

  function goView(id) {
    var frm = document.HISTORIES_LIST_FORM;
    frm.action = "<c:url value="/api/buildView"/>";
    frm.skill_uuid = id;
    frm.submit();
  }

</script>

<script type="text/javascript">
  $(document).ready(function () {
    //달력 - 시작일
    $('#startDate').datepicker({
      format: 'yyyy.mm.dd',
      changeMonth: true,
      changeYear: true,
      onSelect: function (dateText, inst) {
        var sEndDate = jQuery.trim($('#endDate').val());
        if (sEndDate.length > 0) {
          var iEndDate = parseInt(sEndDate.replace(/\./g, ''));
          var iStartDate = parseInt(jQuery.trim(dateText).replace(/\./g, ''));
          if (iStartDate > iEndDate) {
            alert('시작일보다 종료일이 과거일 수 없습니다.');
            $('#startDate').val(sEndDate);
          }
        }
      }
    });
    // 달력 - 종료일
    $('#endDate').datepicker({
      format: 'yyyy.mm.dd',
      changeMonth: true,
      changeYear: true,
      onSelect: function (dateText, inst) {
        var sStartDate = jQuery.trim($('#startDate').val());
        if (sStartDate.length > 0) {
          var iStartDate = parseInt(sStartDate.replace(/\./g, ''));
          var iEndDate = parseInt(jQuery.trim(dateText).replace(/\./g, ''));
          if (iStartDate > iEndDate) {
            alert('종료일이 시작일보다 과거일 수 없습니다.');
            $('#endDate').val(sStartDate);
          }
        }
      }
    });
  });
</script>


<h1>Build 이력 조회</h1>
<div class="row mb-2">
    <div class="col-md-9">
        <div class="card flex-md-row mb-2 box-shadow h-md-250">
            <div class="card-body d-flex flex-column align-items-start">

                <form class="form-inline" id="HISTORIES_LIST_FORM" name="HISTORIES_LIST_FORM"
                      action="<c:url value="/api/buildHistories"/>">
                    <input type="hidden" id="page_no" name="page_no" value="${paging.pageNo}"/>
                    <input type="hidden" id="skill_uuid" name="skill_uuid" value=""/>
                    <div class="form-group mb-2">

                        <label for="type" class="sr-only">Search</label>
                        <select id="type" name="type" class="form-control">
                            <option value="SKILL_UUID">Skill UUID</option>
                            <option value="SKILL_NAME">Skill name</option>
                            <option value="MODEL_UUID">Model UUID</option>
                            <option value="MODEL_NAME">Model name</option>
                        </select>

                    </div>
                    <div class="form-group mx-sm-3 mb-2">
                        <label for="keyword" class="sr-only">keyword</label>
                        <input class="form-control" id="keyword" name="keyword" type="text"
                               placeholder="Search...">
                    </div>
                    <div class="form-group mx-sm-3 mb-2">
                        <label class="mr-sm-2" class="sr-only">date</label>

                        <div class="input-group" id="inputType">
                            <fmt:parseDate value="${startDate}" var="startDate"
                                           pattern="yyyy.MM.dd"
                                           dateStyle="full"/>
                            <input id="startDate" name="startDate"
                                   data-date="<fmt:formatDate value="${startDate}" pattern="yyyy.MM.dd"/>"
                                   class="form-control" type="text" datepicker
                                   data-trigger="#show-datepicker1">
                            <div id="show-datepicker1" class="input-group-append">
                                <span class="input-group-text"><i
                                        class="material-icons">&#xE916;</i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group mx-sm-3 mb-2">
                        <label class="mr-sm-2" class="sr-only">~</label>
                        <div class="input-group" id="inputType">
                            <fmt:parseDate value="${endDate}" var="endDate" pattern="yyyyMMdd"
                                           dateStyle="full"/>
                            <input id="endDate" name="endDate"
                                   value="<fmt:formatDate value="${endDate}" pattern="yyyy.MM.dd"/>"
                                   class="form-control" type="text" datepicker
                                   data-trigger="#show-datepicker2">
                            <div id="show-datepicker2" class="input-group-append">
                                <span class="input-group-text"><i
                                        class="material-icons">&#xE916;</i></span>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary mb-2">조회</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="table-responsive">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Request at</th>
            <th>Agent name</th>
            <th>Agent UUID</th>
            <th>Skill Name</th>
            <th>Skill UUID</th>
            <th>Assist Status</th>
            <th>Assist out code</th>
            <th>Assist out msg</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${paging.finalPageNo == null}">
                <tr>
                    <th colspan="8">
                        조회 할 data가 없습니다.
                    </th>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="data" items="${list}" varStatus="i">
                    <form id="HISTORY_${i.index}" name="HISTORY_${i.index}"
                          action="<c:url value="/api/buildView"/>">
                        <input type="hidden" id="id" name="id"
                               value="${data.id}"/>
                    </form>
                    <tr>
                        <td><a href="javascript:document.HISTORY_${i.index}.submit();"
                        >${data.requestAt}</a>
                        </td>
                        <td><a
                        >${data.modelName}</a>
                        </td>
                        <td><a
                        >${data.modelUuid}</a>
                        </td>
                        <td><a
                        >${data.skillName}</a></td>
                        <td><a
                        >${data.skillUuid}</a>
                        </td>
                        <td><a
                        >${data.assistStatus}</a>
                        </td>
                        <td><a
                        >${data.assistOutCode}</a>
                        </td>
                        <td><a
                        >${data.assistOutMsg}</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <jsp:include page="../common/paging.jsp" flush="true">
        <jsp:param name="firstPageNo" value="${paging.firstPageNo}"/>
        <jsp:param name="prevPageNo" value="${paging.prevPageNo}"/>
        <jsp:param name="startPageNo" value="${paging.startPageNo}"/>
        <jsp:param name="pageNo" value="${paging.pageNo}"/>
        <jsp:param name="endPageNo" value="${paging.endPageNo}"/>
        <jsp:param name="nextPageNo" value="${paging.nextPageNo}"/>
        <jsp:param name="finalPageNo" value="${paging.finalPageNo}"/>
    </jsp:include>
</div>
<div class="rolling"
     hidden=""></div>
<%@ include file="../common/includeBottom.jsp" %>