<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<jsp:include page="../common/includeTop.jsp" flush="true">
    <jsp:param name="menu" value="systemProperty"/>
</jsp:include>
<script type="text/javascript">
  function goPage(page) {
    var frm = document.BUILD_LIST_FORM;
    frm.action = "<c:url value="/api/buildList"/>";
    frm.page_no.value = page;
    frm.submit();
  }

  function deleteConfig(id) {
    var frm = document.SYSTEM_PROPERTY_FORM;
    frm.action = "<c:url value="/api/deleteProperty"/>";
    frm.id.value = id;
    frm.submit();
  }

  function updateConfig(index) {
    var frm = document.SYSTEM_PROPERTY_FORM;
    frm.action = "<c:url value="/api/updateProperty"/>";
    frm.id.value = $("[name='id" + index + "']").val();
    frm.code.value = $("[name='code" + index + "']").val();
    frm.value.value = $("[name='value" + index + "']").val();
    var type = "#id_" + $("[name='type" + index + "']:checked").val();

    $(type).prop("checked", true);

    frm.desc.value = $("[name='desc" + index + "']").val();
    frm.submit();

  }

</script>

<main role="main" class="col-sm-9 col-md-10 pt-3">
<toolbar><h2 class="title">대화 Trigger 설정</h2></toolbar>

<div class="table-responsive">
    <div class="jumbotron mt-3">
        <p class="lead">거부 의사에 따른 대화 Trigger 설정 방법</p>

        <p class="lead">대화 거부 의사를 밝힌 Device의 횟수에 따라 대화 재실행까지의 기간을 설정할 수 있습니다.</p>
        <br>예: 1회 대화 거부 의사 device를 1day로 설정 시 의사 표시 시점으로부터 1일(24시간) 동안 대화를 시도하지 않음</p>
    </div>

    <div class="col-6 placeholder">

    </div>

    <h3>

        Property 조회 및 수정
    </h3>

    <table class="table">
        <%--        <thead>
                <tr>
                    <th scope="col">Code</th>
                    <th scope="col">Value</th>
                    <th scope="col">Type</th>
                    <th scope="col">Description</th>
                    <th scope="col">Modift/Delete</th>
                </tr>
                </thead>--%>
        <tbody>

        <c:choose>
            <c:when test="${data.lenght < 0}">
                <tr>
                    <th colspan="5">
                        조회 할 data가 없습니다.
                    </th>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="data" items="${list}" varStatus="i">
                    <tr>
                        <td style="width: 200px">
                            <input name="id${i.index}" type="hidden"
                                   value="${data.id}">

                            <input
                                    class="form-control"
                                    tabindex="1"
                                    name="code${i.index}" type="text"
                                    value="${data.code}">
                        </td>
                        <td style="width: 200px"><input
                                class="form-control"
                                tabindex="1"
                                name="value${i.index}" type="text"
                                value="${data.value}"></td>
                        <td style="width: 150px">
                            <div class="input-group" id="inputType">
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio"
                                           name="type${i.index}" value="Int" <c:if
                                            test="${data.type == 'Int'}"> checked</c:if> /><label
                                        class="form-check-label">Int </label>
                                    <input class="form-check-input" type="radio"
                                           name="type${i.index}" value="String" <c:if
                                            test="${data.type == 'String'}"> checked</c:if> /><label
                                        class="form-check-label">String</label>
                                </div>
                            </div>
                        </td>

                        <td>
                            <input
                                    class="form-control"
                                    tabindex="1"
                                    name="desc${i.index}" type="text"
                                    value="${data.desc}">
                        </td>
                        <td style="width: 150px">
                            <button type="button" class="btn btn-info"
                                    onClick="updateConfig(${i.index})">저장
                            </button>
                                <%--
                                                            <button type="button" class="btn btn-danger"
                                                                    onclick="deleteConfig(${data.id})">삭제
                                                            </button>--%>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

</div>
</main>
<%@ include file="../common/includeBottom.jsp" %>