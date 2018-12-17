
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:pagetemplate>
    <jsp:attribute name="title"><fmt:message key="user.users"/></jsp:attribute>
    <jsp:attribute name="body">
        <div class="jumbotron">
            <h1><fmt:message key="menu_statistics"/></h1>
            <p class="lead"><fmt:message key="statistics.statisticsText"/></p>
        </div>

        <h1><fmt:message key="statistics.burnedCalories"/></h1>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="statistics.total"/></th>
                    <th><fmt:message key="statistics.week"/></th>
                    <th><fmt:message key="statistics.month"/></th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="col-xs-3 lead-column"><c:out value="${userStats.calories}"/></td>
                        <td class="col-xs-3 lead-column"><c:out value="${userStats.caloriesWeek}"/></td>
                        <td class="col-xs-3 lead-column"><c:out value="${userStats.caloriesMonth}"/></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <h1><fmt:message key="statistics.sportActivities"/></h1>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="statistics.total"/></th>
                    <th><fmt:message key="statistics.week"/></th>
                    <th><fmt:message key="statistics.month"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="col-xs-3 lead-column"><c:out value="${userStats.activities}"/></td>
                    <td class="col-xs-3 lead-column"><c:out value="${userStats.activitiesWeek}"/></td>
                    <td class="col-xs-3 lead-column"><c:out value="${userStats.activitiesMonth}"/></td>
                </tr>
                </tbody>
            </table>
        </div>

        <h1><fmt:message key="statistics.sportActivityDetails"/></h1>
        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="statistics.sportActivity"/></th>
                    <th><fmt:message key="statistics.count"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userStats.activitiesSumUp}" var="activityEntry">
                    <tr>
                        <td class="col-xs-2 lead-column"><c:out value="${activityEntry.key.activityName}"/></td>
                        <td class="col-xs-2 lead-column"><c:out value="${activityEntry.value}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </jsp:attribute>
</own:pagetemplate>