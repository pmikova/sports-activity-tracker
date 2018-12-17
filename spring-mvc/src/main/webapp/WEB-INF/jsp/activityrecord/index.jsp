<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<own:pagetemplate title="Activity Records">
    <jsp:attribute name="body">
        <div class="jumbotron">
            <h1><fmt:message key="records.header"/></h1>
            <p class="lead"><fmt:message key="records.subheader"/></p>

            <%--   <c:if test="${isAdministrator}"> --%>
            <p align="right">
                <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/records/create" role="button">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <fmt:message key="records.create"/>
                </a>
            </p>
            <%--  </c:if> --%>
        </div>
        <c:if test="${not empty records}">
            <div class="row">
                <table class="table">
                    <thead>
                        <tr>
                            <th><fmt:message key="num"></fmt:message></th>
                            <th><fmt:message key="records.activity"></fmt:message></th>
                            <th><fmt:message key="records.start"></fmt:message></th>
                            <th><fmt:message key="records.end"></fmt:message></th>
                            <th><fmt:message key="records.distance"></fmt:message></th>
                            <th><fmt:message key="records.averageSpeed"></fmt:message></th>
                            <th class="text-center"><fmt:message key="edit"/></th>
                            <th class="text-center"><fmt:message key="remove"/></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${records}" var="record">
                            <c:set var="count" value="${count+1}" scope="page"/>
                            <tr>
                                <td class="col-xs-1 lead-column">${count}</td>
                                <td class="col-xs-2 lead-column"><c:out value="${record.sportActivity.activityName}"/></td>
                                <td class="col-xs-2 lead-column"><javatime:format value="${record.startTime}" pattern="HH:mm dd.MM.yyyy"/></td>
                                <td class="col-xs-2 lead-column"><javatime:format value="${record.endTime}" pattern="HH:mm dd.MM.yyyy"/></td>
                                <td class="col-xs-2 lead-column"><c:out value="${record.distance}"/></td>
                                <td class="col-xs-2 lead-column"><c:out value="${record.averageSpeed}"/></td>

                                <form:form method="get" action="${pageContext.request.contextPath}/records/update/${report.id}" cssClass="form-horizontal">
                                    <td class="col-xs-1 text-center">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="edit"/></span>
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </form:form>
                                <form:form method="post" action="${pageContext.request.contextPath}/records/remove/${record.id}" cssClass="form-horizontal">
                                    <td class="col-xs-1 text-center">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="remove"/></span>
                                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </form:form>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>

    </jsp:attribute>
</own:pagetemplate>
