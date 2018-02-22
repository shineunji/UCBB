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

                <form class="form-inline" id="HISTORIES_LIST_FORM" name="HISTORIES_LIST_FORM"
                      action="<c:url value="/api/buildHistories"/>">
                    <input type="hidden" id="page_no" name="page_no" value="${paging.pageNo}"/>
                    <div class="form-group mx-sm-3 mb-2">
                        <label for="keyword" class="sr-only">Search</label>
                        <input class="form-control" id="keyword" name="keyword" type="text"
                               placeholder="keyword...">
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
                        <td><button type="button" class="btn btn-info"
                                    onClick="updateConfig(${i.index})">취소
                        </button>
                            <button type="button" class="btn btn-info"
                                    onClick="updateConfig(${i.index})">재예약
                            </button>
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