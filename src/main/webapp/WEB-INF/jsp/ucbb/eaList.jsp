<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/includeTop.jsp" flush="true">
    <jsp:param name="menu" value="buildList"/>
</jsp:include>
<script type="text/javascript">
  function goPage(page) {
    if(page > 0){
      var frm = document.ENTITY_ATTRIBUTE_FORM;
      frm.action = "<c:url value="/eaList"/>";
      frm.page_no.value = page;
      frm.submit();
    }
  }

  function goView(id) {
    var frm = document.BUILD_LIST_FORM;
    frm.action = "<c:url value="/api/buildView"/>";
    frm.skill_uuid = id;
    frm.submit();
  }

</script>

<main role="main" class="col-sm-9 col-md-10 pt-3">
    <toolbar><h2 class="title">Entity-Attribute 관리</h2></toolbar>
    <form id="ENTITY_ATTRIBUTE_FORM" name="ENTITY_ATTRIBUTE_FORM" method="post"
          action="<c:url value="/eaList"/>">
        <input type="hidden" id="page_no" name="page_no"
               value="1"/>
    <div>
        <label class="sr-only">Search</label>
        <select name="type">
            <option value="All" ${type == 'All' ? 'selected' : ''}>ALL</option>
            <option value="Entity" ${type == 'Entity' ? 'selected' : ''}>Entity</option>
            <option value="Attribute" ${type == 'Attribute' ? 'selected' : ''}>Attribute</option>
        </select>
        <input type="text" name="searchKeyword" value="${searchKeyword}"/>
        <button type="submit" class="">조회</button>
    </div>
    </form>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Entity</th>
                <th>Attributes</th>
            </tr>
            </thead>
            <tbody>

            <c:choose>
                <c:when test="${paging.finalPageNo == 0}">
                    <tr>
                        <th colspan="5">
                            조회 할 data가 없습니다.
                        </th>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="data" items="${result}" varStatus="i">
                        <tr>
                            <td>${data.entity}</td>
                            <td>
                                <c:forEach var="data2" items="${data.attributesEntities}" varStatus="i">
                                    ${data2.attribute}<c:if test="${not i.last}">,</c:if>
                                </c:forEach>
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
</main>
<%@ include file="../common/includeBottom.jsp" %>