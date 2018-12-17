<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:pagetemplate>
    <jsp:attribute name="title"><fmt:message key="create_new"/></jsp:attribute>
    <jsp:attribute name="body">

            <div class="page-header">
                <h1>
                    <fmt:message key="create"/>
                </h1>
            </div>
            <form:form method="POST"
                       action="${pageContext.request.contextPath}/activities/create"
                       acceptCharset=""
                       modelAttribute="activityCreate"
                       cssClass="form-horizontal">


            <div class="form-group ${activityName_error?'has-error':''}">
                <form:label path="activityName" cssClass="col-sm-2 control-label"><fmt:message key="activity.name"/></form:label>
                <div class="col-sm-10">
                    <form:input path="activityName" cssClass="form-control"/>
                    <form:errors path="activityName" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${burnedCaloriesPerHour_error?'has-error':''}">
                <form:label path="burnedCaloriesPerHour" cssClass="col-sm-2 control-label"><fmt:message key="activity.burnedCaloriesPerHour"/></form:label>
                <div class="col-sm-10">
                    <form:input path="burnedCaloriesPerHour" cssClass="form-control"/>
                    <form:errors path="burnedCaloriesPerHour" cssClass="help-block"/>
                </div>
            </div>

            <button class="btn btn-primary createBtn center-block allow-vertical-space" type="submit"><fmt:message key="submit"/></button>
        </form:form>
    </jsp:attribute>
</own:pagetemplate>