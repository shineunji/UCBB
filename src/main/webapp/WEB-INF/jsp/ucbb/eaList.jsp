<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/includeTop.jsp" flush="true">
    <jsp:param name="menu" value="buildList"/>
</jsp:include>
<script type="text/javascript">
  function goPage(page) {
    var frm = document.BUILD_LIST_FORM;
    frm.action = "<c:url value="/api/buildList"/>";
    frm.page_no.value = page;
    frm.submit();
  }

  function goView(id) {
    var frm = document.BUILD_LIST_FORM;
    frm.action = "<c:url value="/api/buildView"/>";
    frm.skill_uuid = id;
    frm.submit();
  }

</script>

<main role="main" class="col-sm-9 col-md-10 pt-3">
<toolbar><h2 class="title">대화 Trigger 설정</h2></toolbar>
<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>Request At</th>
            <th>Skill UUID</th>
            <th>Skill Name</th>
            <th>Model Name</th>
            <th>Model UUID</th>
        </tr>
        </thead>
        <tbody>

        <c:choose>
            <c:when test="${paging.finalPageNo == null}">
                <tr>
                    <th colspan="5">
                        조회 할 data가 없습니다.
                    </th>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="lock" items="${lockList}" varStatus="i">
                    <tr>
                        <td>${lock.requestAt}</td>
                        <td>${lock.skillUuid} </td>
                        <td>${lock.skillName}</td>
                        <td>${lock.modelName}</td>
                        <td>${lock.modelUuid}</td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

    <form id="BUILD_LIST_FORM" name="BUILD_LIST_FORM" method="post"
          action="<c:url value="/api/buildList"/>">
        <input type="hidden" id="page_no" name="page_no"
               value="${paging.pageNo}"/>
        <input type="hidden" id="skill_uuid" name="skill_uuid" value=""/>
    </form>
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
</main>
<%@ include file="../common/includeBottom.jsp" %>