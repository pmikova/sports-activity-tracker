
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:pagetemplate>

    <jsp:attribute name="body">

        <div class="jumbotron homepage">
        <h1><fmt:message key="application_name"/></h1>
        <p class="lead"><fmt:message key="index_welcome"/></p>
            <c:choose>
                <c:when test="${empty loggedUser}">
                <p><fmt:message key="index_text"/></p>
                    <p align="right">
                        <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/login" role="button">
                            <fmt:message key="sign_in"/>
                        </a>
                        <a class="btn btn-lg btn-success btn-jumbotron" href="${pageContext.request.contextPath}/register" role="button">
                            <fmt:message key="user_register"/>
                        </a>
                    </p>

                </c:when>
            </c:choose>
        </div>

    </jsp:attribute>
</own:pagetemplate>
