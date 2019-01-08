<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:pagetemplate title="Settings">
        <jsp:attribute name="scripts">
        <script>
            $(function () {
                $(".datepicker").datetimepicker({ format: 'DD.MM.YYYY'});
            });
        </script>
    </jsp:attribute>
    <jsp:attribute name="body">

            <a href="${pageContext.request.contextPath}" class="btn btn-default" role="button">
                <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                <fmt:message key="back"/>
            </a>

            <div class="page-header">
                <h1>
                   <fmt:message key="user.update"/>
                </h1>
            </div>
            <form:form method="POST"
                   action="${pageContext.request.contextPath}/settings"
                   acceptCharset=""
                   modelAttribute="user"
                   cssClass="form-horizontal">


            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="name" cssClass="col-sm-2 control-label"><fmt:message key="user.name"/></form:label>
                <div class="col-sm-10">
                    <form:input path="name" cssClass="form-control"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${surname_error?'has-error':''}">
                <form:label path="surname" cssClass="col-sm-2 control-label"><fmt:message key="user.surname"/></form:label>
                <div class="col-sm-10">
                    <form:input path="surname" cssClass="form-control"/>
                    <form:errors path="surname" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${email_error?'has-error':''}">
                <form:label path="email" cssClass="col-sm-2 control-label" style="display: none"><fmt:message key="user.email"/></form:label>
                <div class="col-sm-10">
                    <form:input path="email" cssClass="form-control" style="display: none"/>
                    <form:errors path="email" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${weight_error?'has-error':''}">
                <form:label path="weight" cssClass="col-sm-2 control-label"><fmt:message key="user.weight"/></form:label>
                <div class="col-sm-10">
                    <form:input path="weight" cssClass="form-control"/>
                    <form:errors path="weight" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${birthdate_error?'has-error':''}">
                <form:label path="birthdate" cssClass="col-sm-2 control-label"><fmt:message key="user.birthdate"/></form:label>
                <div class="col-sm-10">
                    <form:input path="birthdate" cssClass="form-control datepicker"/>
                    <form:errors path="birthdate" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${gender_error?'has-error':''}">
                <form:label path="gender" cssClass="col-sm-2 control-label"><fmt:message key="user.gender"/></form:label>
                <div class="col-sm-10">
                    <form:select path="gender" cssClass="form-control" >
                        <form:option value="MALE">Male</form:option>
                        <form:option value="FEMALE">Female</form:option>
                    </form:select>
                    <form:errors path="gender" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${passwordHash_error?'has-error':''}">
                <form:label path="passwordHash" cssClass="col-sm-2 control-label" style="display: none"><fmt:message key="user.passwordHash"/></form:label>
                <div class="col-sm-10">
                    <form:input path="passwordHash" cssClass="form-control" style="display: none"/>
                    <form:errors path="passwordHash" cssClass="help-block"/>
                </div>
            </div>

            <div class="form-group ${userType_error?'has-error':''}">
                <form:label path="userType" cssClass="col-sm-2 control-label" style="display: none"><fmt:message key="user.userType"/></form:label>
                <div class="col-sm-10">
                    <form:input path="userType" cssClass="form-control" style="display: none"/>
                    <form:errors path="userType" cssClass="help-block"/>
                </div>
            </div>
            <button class="btn btn-primary createBtn center-block allow-vertical-space" type="submit"><fmt:message key="submit"/></button>
        </form:form>
    </jsp:attribute>
</own:pagetemplate>