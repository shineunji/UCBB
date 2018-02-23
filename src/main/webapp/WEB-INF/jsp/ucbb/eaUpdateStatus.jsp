<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/includeTop.jsp" flush="true">
    <jsp:param name="menu" value="buildHistories"/>
</jsp:include>

<script type="text/javascript">
  var attrArr = [];

  $(document).ready(function () {
    //달력 - 시작일
    $('#startDate').datepicker({
      format: 'yyyy-mm-dd',
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
      format: 'yyyy-mm-dd',
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

    <c:forEach var="data" items="${result}">
        var tempArr = [];
        <c:forEach var="data2" items="${data.updateEntitiesEntities}">
            var json = new Object();
            json.id = "${data2.id}";
            json.updateStatusId = "${data2.updateStatusId}";
            json.name = "${data2.name}";
            tempArr.push(json);
        </c:forEach>
        attrArr.push(tempArr);
    </c:forEach>
  });

  function goPage(page) {
    var frm = document.STATUS_LIST_FORM;
    frm.action = "<c:url value="/eaUpdateStatus"/>";
    frm.page_no.value = page;
    frm.submit();
  }

  function goView(id) {
    var frm = document.HISTORIES_LIST_FORM;
    frm.action = "<c:url value="/api/buildView"/>";
    frm.skill_uuid = id;
    frm.submit();
  }

  function popUpdateEntities(idx) {
    console.log(attrArr[idx]);
  }
</script>


<nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a class="nav-link"
               href="<c:url value="/eaUpdateForm"/>">Entity 업데이트 등록</a></li>
        <li class="nav-item">
            <a class="nav-link active"
               href="<c:url value="/eaUpdateStatus"/>">Entity 업데이트 현황<span
                    class="sr-only">(current)</span></a></li>
    </ul>
</nav>


<main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-3">

    <toolbar><h2 class="title">Entity 업데이트 현황</h2></toolbar>
    <div class="row mb-2">
        <div class="col-md-9">
            <div class="card flex-md-row mb-2 box-shadow h-md-250">
                <div class="card-body d-flex flex-column align-items-start">

                    <form class="form-inline" id="STATUS_LIST_FORM" name="STATUS_LIST_FORM"
                          action="<c:url value="/eaUpdateStatus"/>">
                        <input type="hidden" id="page_no" name="page_no" value="1"/>
                        <div class="form-group mx-sm-3 mb-2">
                            <label for="keyword" class="sr-only">Search</label>
                            <input class="form-control" id="keyword" name="searchKeyword"
                                   type="text" value="${searchKeyword}"
                                   placeholder="keyword...">
                        </div>
                        <div class="form-group mx-sm-3 mb-2">
                            <label class="mr-sm-2" class="sr-only">date</label>

                            <div class="input-group" id="inputType">
                                <fmt:parseDate value="${startDate}" var="startDate"
                                               pattern="yyyy-MM-dd"
                                               dateStyle="full"/>
                                <input id="startDate" name="startDate"
                                       value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"
                                       class="form-control" type="text" datepicker
                                       data-trigger="#show-datepicker1">
                                <div id="show-datepicker1" class="input-group-append">
                                <span class="input-group-text"><i
                                        class="material-icons">&#xE916;</i></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group mx-sm-3 mb-2">
                            <label class="mr-sm-2" class="sr-only">~&nbsp;</label>
                            <div class="input-group" id="inputType">
                                <fmt:parseDate value="${endDate}" var="endDate"
                                               pattern="yyyy-MM-dd"
                                               dateStyle="full"/>
                                <input id="endDate" name="endDate"
                                       value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"
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
        <table class="table">
            <thead>
            <tr>
                <th>업데이트 예약일</th>
                <th>업데이트 실행일</th>
                <th>Entity count</th>
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${paging.finalPageNo == 0}">
                    <tr>
                        <th colspan="8">
                            조회 할 data가 없습니다.
                        </th>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="data" items="${result}" varStatus="i">
                        <tr>
                            <td>
                                <fmt:formatDate value="${data.createAt}" type="both"
                                                pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${data.excuteAt}" type="both"
                                                pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <button type="button" class="btn btn-info"
                                        onClick="popUpdateEntities(${i.index})">
                                        ${data.entityCount}
                                </button>
                            </td>
                            <td><c:choose>
                                <c:when test="${data.statusCode == 'C'}">
                                    완료
                                </c:when>
                                <c:when test="${data.statusCode == 'R'}">
                                    예약중
                                </c:when>
                                <c:when test="${data.statusCode == 'F'}">
                                    취소
                                </c:when>
                            </c:choose>
                            </td>
                            <td><c:choose>
                                <c:when test="${data.statusCode == 'R'}">
                                    <button type="button" class="btn btn-info"
                                            onClick="updateConfig(${data.id})">
                                        취소
                                    </button>
                                </c:when>
                                <c:when test="${data.statusCode == 'F'}">
                                    <button type="button" class="btn btn-info"
                                            onClick="updateConfig(${data.id})">
                                        재예약
                                    </button>
                                </c:when>
                            </c:choose>
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
</main>