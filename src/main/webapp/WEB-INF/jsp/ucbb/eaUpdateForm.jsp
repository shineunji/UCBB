<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/includeTop.jsp" flush="true">
    <jsp:param name="menu" value="buildHistories"/>
</jsp:include>


<nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
    <ul class="nav nav-pills flex-column">
        <li class="nav-item">
            <a class="nav-link active"
               href="<c:url value="/eaUpdateForm"/>">Entity 업데이트 등록<span
                    class="sr-only">(current)</span></a></li>
        <li class="nav-item">
            <a class="nav-link"
               href="<c:url value="/eaUpdateStatus"/>">Entity 업데이트 현황</a></li>
    </ul>
</nav>


<main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-3">

<toolbar><h2 class="title">Entity 업데이트 등록</h2></toolbar>
<div class="table-responsive">


    <section _ngcontent-cxm-82=""><%--<h2 _ngcontent-cxm-82="">
                    Build List</h2>--%>
        <%--<p _ngcontent-cxm-82="">Add users and update information
            about users.</p>--%>
        <app-table>
            <div class="table" _ejcontent>
                <table _ejcontent>
                    <colgroup>
                        <col>
                    </colgroup>
                    <tbody>
                    <tr>
                        <td _ejcontent>
                            <div class="table">
                                <table>
                                    <colgroup>
                                        <col width="25%">
                                        <col width="30%">
                                        <col width="20%">
                                        <col width="25%">
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <th align="left">Agent name</th>
                                        <td>
                                            <input class="input ng-untouched ng-pristine ng-valid"
                                                   tabindex="1"
                                                   name="name" type="text" value="${data.modelName}"
                                                   placeholder="Agent name#1">

                                        </td>
                                        <th align="left">Skill name</th>
                                        <td>

                                            <input class="input ng-untouched ng-pristine ng-valid"
                                                   tabindex="1"
                                                   name="desc" type="text" value="${data.skillName}"
                                                   placeholder="Skill name#1">

                                        </td>
                                    </tr>
                                    <tr>
                                        <th align="left">Agent UUID</th>
                                        <td><input
                                                class="input ng-untouched ng-pristine ng-valid"
                                                tabindex="1"
                                                name="name" type="text" value="${data.modelUuid}"
                                                placeholder="Agent UUID#1">

                                        </td>
                                        <th align="left"><span>Skill UUID</span></th>
                                        <td>


                                            <input class="input ng-untouched ng-pristine ng-valid"
                                                   tabindex="1"
                                                   name="desc" type="text" value="${data.skillUuid}"
                                                   placeholder="Skill UUID#1">


                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td _ejcontent>
                            <div class="table">
                                <table>
                                    <colgroup>
                                        <col width="25%">
                                        <col width="30%">
                                        <col width="20%">
                                        <col width="25%">
                                    </colgroup>
                                    <tbody>

                                    <tr>
                                        <th align="left">Request at</th>
                                        <td>

                                            <input class="input ng-untouched ng-pristine ng-valid"
                                                   tabindex="1"
                                                   name="name" type="text" value="${data.requestAt}"
                                                   placeholder="yyyy.mm.dd hh:mm:ss">

                                        </td>
                                        <th align="left">Status</th>
                                        <td>


                                            <input class="input ng-untouched ng-pristine ng-valid"
                                                   tabindex="1"
                                                   name="desc" type="text"
                                                   value="${data.assistStatus}"
                                                   placeholder="Assist status">

                                        </td>
                                    </tr>


                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td _ejcontent>
                            <div class="table">
                                <table>
                                    <colgroup>
                                        <col width="25%">
                                        <col width="30%">
                                        <col width="20%">
                                        <col width="25%">
                                    </colgroup>
                                    <tbody>


                                    <tr>
                                        <th align="left">Response at</th>
                                        <td>
                                            <input class="input ng-untouched ng-pristine ng-valid"
                                                   tabindex="1"
                                                   name="name" type="text"
                                                   value="${data.assistOutAt}"
                                                   placeholder="yyyy.mm.dd hh:mm:ss">

                                        </td>
                                        <th align="left"></th>
                                        <th align="left"></th>
                                    </tr>
                                    <tr>
                                        <th align="left">Response</th>
                                        <td>
                                            <input class="input ng-untouched ng-pristine ng-valid"
                                                   tabindex="1"
                                                   name="name" type="text"
                                                   value="${data.assistOutCode}"
                                                   placeholder="Assist out code">

                                        </td>
                                        <td colspan="2"><input
                                                class="input ng-untouched ng-pristine ng-valid"
                                                tabindex="1"
                                                name="name" type="text" value="${data.assistOutMsg}"
                                                placeholder="Assist out msg"></td>
                                    </tr>
                                    <tr>
                                        <th align="left">Detail msg</th>
                                        <td colspan="3"><input
                                                class="input ng-untouched ng-pristine ng-valid"
                                                tabindex="1"
                                                name="name" type="text"
                                                value="${data.assistOutDetail}"
                                                placeholder="Assist out detail"></td>
                                    </tr>


                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td _ejcontent>
                            <div class="table">
                                <table>
                                    <colgroup>
                                        <col width="25%">
                                        <col width="30%">
                                        <col width="20%">
                                        <col width="25%">
                                    </colgroup>
                                    <tbody>


                                    <tr></tr>
                                    <tr>
                                        <th align="left">Builder Request at</th>
                                        <td colspan="3"><input
                                                class="input ng-untouched ng-pristine ng-valid"
                                                tabindex="1"
                                                name="name" type="text"
                                                value="${data.builderRequestAt}"
                                                placeholder="yyyy.mm.dd hh:mm:ss"></td>
                                    </tr>
                                    <tr>
                                        <th align="left"><span>Builder Response at</span>
                                        </th>
                                        <td colspan="3"><input
                                                class="input ng-untouched ng-pristine ng-valid"
                                                tabindex="1"
                                                name="name" type="text"
                                                value="${data.builderReturnAt}"
                                                placeholder="yyyy.mm.dd hh:mm:ss"></td>
                                    </tr>
                                    <tr>
                                        <th align="left">Response</th>
                                        <td>
                                            <input class="input ng-untouched ng-pristine ng-valid"
                                                   tabindex="1"
                                                   name="name" type="text"
                                                   value="${data.builderReturnCode}"
                                                   placeholder="Builder return code">

                                        </td>
                                        <td colspan="2"><input
                                                class="input ng-untouched ng-pristine ng-valid"
                                                tabindex="1"
                                                name="name" type="text"
                                                value="${data.builderReturnMsg}"
                                                placeholder="Builder return msg"></td>
                                    </tr>
                                    <tr>
                                        <th align="left" colspan="4">Input full text</th>
                                    </tr>
                                    <tr>
                                        <td colspan="4">
                                            <div autocapitalize="off" autocomplete="off"
                                                 autocorrect="off"
                                                 class="transcript-textarea"
                                                 contenteditable="true"
                                                 spellcheck="false"
                                                 style="height: 200px;"> ${data.assistRequestBlob}</div>
                                        </td>
                                    </tr>


                                    </tbody>
                                </table>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </app-table>
    </section>
</div>
</main>
<%@ include file="../common/includeBottom.jsp" %>














