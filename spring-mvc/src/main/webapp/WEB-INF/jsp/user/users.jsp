
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
            <h1><fmt:message key="user.users"/></h1>
            <p class="lead"><fmt:message key="user.userText"/></p>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                    <tr>
                        <th><fmt:message key="user.id"/></th>
                        <th><fmt:message key="user.name"/></th>
                        <th><fmt:message key="user.surname"/></th>
                        <th><fmt:message key="user.userType"/></th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr>
                            <td class="col-xs-1 lead-column">${count}.</td>
                            <td class="col-xs-3 lead-column"><c:out value="${user.name}"/></td>
                            <td class="col-xs-3 lead-column"><c:out value="${user.surname}"/></td>
                            <td class="col-xs-3 lead-column"><c:out value="${user.userType}"/></td>

                            <c:if test="${isUserAdmin}">
                                <c:choose>
                                <c:when test="${user.userType !='ADMIN'}">
                                    <form:form method="post" action="${pageContext.request.contextPath}/users/update/${user.id}" cssClass="form-horizontal">
                                        <td class="col-xs-1 text-center">
                                             <a href="${pageContext.request.contextPath}/users/makeAdministrator/${user.id}" class="btn btn-default">Make Administrator</a>
                                        </td>
                                    </form:form>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${loggedUser.id!=user.id}">
                                        <form:form method="post" action="${pageContext.request.contextPath}/users/update/${user.id}" cssClass="form-horizontal">
                                                <td class="col-xs-1 text-center">
                                                    <a href="${pageContext.request.contextPath}/users/makeUser/${user.id}" class="btn btn-default">Make User </a>
                                                </td>
                                         </form:form>
                                    </c:if>
                                </c:otherwise>
                                </c:choose>
                                <c:if test="${loggedUser.id!=user.id}">
                                    <form:form method="post" action="${pageContext.request.contextPath}/users/remove/${user.id}" cssClass="form-horizontal">
                                        <td class="col-xs-1 text-center">
                                            <button class="btn btn-default" type="submit">
                                                <span class="sr-only"><fmt:message key="remove"/></span>
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </button>
                                        </td>
                                    </form:form>
                                </c:if>
                            </c:if>
                        </tr>
                    </c:forEach>
            </table>
        </div>

    </jsp:attribute>
</own:pagetemplate>