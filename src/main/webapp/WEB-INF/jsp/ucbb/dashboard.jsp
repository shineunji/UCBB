<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/includeTop.jsp" flush="true">
    <jsp:param name="menu" value="buildHistories"/>
</jsp:include>

<main class="mdl-layout__content">
    <div class="mdl-grid portfolio-max-width">

        <div class="mdl-cell mdl-cell--12-col mdl-card mdl-shadow--4dp">
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text">Process : total</h2>
            </div>
            <div class="mdl-grid portfolio-copy">
                <div class="mdl-tabs mdl-js-tabs mdl-js-ripple-effect">
                    <div class="mdl-tabs__tab-bar">
                        <a href="#starks-panel" class="mdl-tabs__tab is-active">LG U+ 대화</a>
                    </div>

                    <div class="mdl-tabs__panel is-active" id="starks-panel">
                        <ul>
                            <div id="p1" class="mdl-progress mdl-js-progress mdl-progress__indeterminate"></div>
                        </ul>
                    </div>
                    <div class="mdl-tabs__panel" id="lannisters-panel">
                        <ul>
                            <li>Tywin</li>
                            <li>Cersei</li>
                            <li>Jamie</li>
                            <li>Tyrion</li>
                        </ul>
                    </div>
                    <div class="mdl-tabs__panel" id="targaryens-panel">
                        <ul>
                            <li>Viserys</li>
                            <li>Daenerys</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>


    </div>
</main>



<%@ include file="../common/includeBottom.jsp" %>