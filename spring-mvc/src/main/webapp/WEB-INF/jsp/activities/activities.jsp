<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:pagetemplate>
    <jsp:attribute name="title"><fmt:message key="menu_activities"/></jsp:attribute>
    <jsp:attribute name="body">
        <div class="jumbotron">
            <h1><fmt:message key="menu_activities"/></h1>
            <p class="lead"><fmt:message key="activity.activityText"/></p>
        </div>

        <c:if test="${isUserAdmin}">
            <form:form method="get" action="${pageContext.request.contextPath}/activities/create" cssClass="form-horizontal">
                <td class="col-xs-1 text-center">
                    <a href="${pageContext.request.contextPath}/activities/create" class="btn btn-default"><fmt:message key="create_new"/></a>
                </td>
            </form:form>
        </c:if>

        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="activity.id"/></th>
                    <th><fmt:message key="activity.name"/></th>
                    <th><fmt:message key="activity.burnedCaloriesPerHour"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${activities}" var="activity">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                <tr>
                    <td class="col-xs-1 lead-column">${count}.</td>
                    <td class="col-xs-3 lead-column"><c:out value="${activity.activityName}"/></td>
                    <td class="col-xs-3 lead-column"><c:out value="${activity.burnedCaloriesPerHour}"/></td>
                    <c:if test="${isUserAdmin}">
                        <form:form method="get" action="${pageContext.request.contextPath}/activities/update/${activity.id}" cssClass="form-horizontal">
                            <td class="col-xs-1 text-center">
                                <a href="${pageContext.request.contextPath}/activities/update/${activity.id}" class="btn btn-default"><fmt:message key="update"/></a>
                            </td>
                        </form:form>
                        <form:form method="post" action="${pageContext.request.contextPath}/activities/remove/${activity.id}" cssClass="form-horizontal">
                            <td class="col-xs-1 text-center">
                                <button class="btn btn-default" type="submit">
                                    <span class="sr-only"><fmt:message key="remove"/></span>
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
                            </td>
                        </form:form>
                    </c:if>
                </tr>
                </c:forEach>
            </table>
        </div>

    </jsp:attribute>
</own:pagetemplate>