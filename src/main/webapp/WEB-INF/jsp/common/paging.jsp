<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav aria-label="...">
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <a class="page-link" href="javascript:goPage(${param.prevPageNo})"
               aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
            </a>
        </li>
        <c:forEach var="i" begin="${param.startPageNo}" end="${param.endPageNo}" step="1">
            <c:choose>
                <c:when test="${i eq param.pageNo}">
                    <li class="page-item active">
      <span class="page-link">
        ${i}
        <span class="sr-only">(current)</span>
      </span>
                    </li>
                </c:when>
                <c:when test="${i eq param.firstPageNo}">
                    <li class="page-item"><a class="page-link"
                                             href="javascript:goPage(${i})">${i}</a></li>
                </c:when>
                <c:when test="${i eq param.finalPageNo}">
                    <li class="page-item"><a class="page-link"
                                             href="javascript:goPage(${i})">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="javascript:goPage(${i})">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <li class="page-item">
            <a class="page-link" href="javascript:goPage(${param.nextPageNo})" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </a>
        </li>
    </ul>
</nav>