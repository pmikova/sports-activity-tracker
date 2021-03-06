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
            <%--<p align="right">--%>
                <form:form method="get" action="${pageContext.request.contextPath}/activityrecord/create/" cssClass="form-horizontal">

                <button class="btn btn-lg btn-success btn-jumbotron" role="button" type="submit" align="right">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    <fmt:message key="records.create"/>
                </button>
                 </form:form>
            <%--</p>--%>

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
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${records}" var="record">
                            <c:set var="count" value="${count+1}" scope="page"/>
                            <tr>
                                <td class="col-xs-1 lead-column">${count}</td>
                                <td class="col-xs-2 lead-column"><c:out value="${record.sportActivity.activityName}"/></td>
                                <td class="col-xs-2 lead-column"><javatime:format value="${record.startTime}" pattern="dd.MM.YYYY HH:mm"/></td>
                                <td class="col-xs-2 lead-column"><javatime:format value="${record.endTime}" pattern="dd.MM.YYYY HH:mm"/></td>
                                <td class="col-xs-2 lead-column"><c:out value="${record.distance}"/></td>
                                <td class="col-xs-2 lead-column"><c:out value="${record.averageSpeed}"/></td>

                                <form:form method="get" action="${pageContext.request.contextPath}/activityrecord/edit/${record.id}" cssClass="form-horizontal">
                                    <td class="col-xs-1 text-center">
                                        <button class="btn btn-default" type="submit">
                                            <span class="sr-only"><fmt:message key="edit"/></span>
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </form:form>
                                <form:form method="post" action="${pageContext.request.contextPath}/activityrecord/delete/${record.id}" cssClass="form-horizontal">
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
