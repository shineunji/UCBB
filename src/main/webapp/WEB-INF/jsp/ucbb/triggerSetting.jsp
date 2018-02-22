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
<h3>UCBB System Property 설정</h3>

<div class="table-responsive">
    <div class="row mb-2">
        <div class="col-md-6">
            <div class="card flex-md-row mb-4 box-shadow h-md-250">
                <div class="card-body d-flex flex-column align-items-start">
                    <h3 class="mb-0">
                        Property 등록
                    </h3>
                    <form id="SYSTEM_PROPERTY_FORM" name="SYSTEM_PROPERTY_FORM"
                          action="<c:url value="/api/insertProperty"/>">
                        <input type="hidden" id="id" name="id">
                        <div class="form-row">
                            <div class="col-md-4 mb-3">
                                <label for="code">Code</label>
                                <input type="text" class="form-control" id="code" name="code"
                                       placeholder="Code" value="" required="">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="value">Value</label>
                                <input type="text" class="form-control" id="value" name="value"
                                       placeholder="Value" value="" required="">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="inputType">Type</label>
                                <div class="input-group" id="inputType">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="type"
                                               id="id_Int" value="Int">
                                        <label class="form-check-label"
                                               for="inlineRadio1">Int</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="type"
                                               id="id_String" value="String">
                                        <label class="form-check-label"
                                               for="inlineRadio2">String</label>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="form-row align-items-center">
                            <div class="col-9 col-auto my-1">
                                <label class="mr-sm-2" for="desc">Description</label>
                                <input type="text" class="form-control" id="desc" name="desc"
                                       placeholder="Description" required="">
                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary">등록</button>

                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class="col-6 placeholder">

    </div>

    <h3>

        Property 조회 및 수정
    </h3>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Code</th>
            <th scope="col">Value</th>
            <th scope="col">Type</th>
            <th scope="col">Description</th>
            <th scope="col">Modift/Delete</th>
        </tr>
        </thead>
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
                                    onClick="updateConfig(${i.index})">수정
                            </button>

                            <button type="button" class="btn btn-danger"
                                    onclick="deleteConfig(${data.id})">삭제
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

</div>
<%@ include file="../common/includeBottom.jsp" %>