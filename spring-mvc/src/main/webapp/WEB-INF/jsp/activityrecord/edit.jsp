<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:pagetemplate title="Edit">
        <jsp:attribute name="scripts">
        <script>
            $(function () {
                $(".datepicker").datetimepicker({format:'DD/MM/YYYY HH:mm:ss',});
            });
        </script>
    </jsp:attribute>
    <jsp:attribute name="body">

            <a href="${pageContext.request.contextPath}/activityrecord/index" class="btn btn-default" role="button">
                <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                <fmt:message key="back"/>
            </a>

            <div class="page-header">
                <h1>
                    <fmt:message key="edit"/>
                </h1>
            </div>
            <form:form method="POST"
                       action="${pageContext.request.contextPath}/activityrecord/edit"
                       acceptCharset=""
                       modelAttribute="record"
                       cssClass="form-horizontal">

                <div class="form-group ${activity_error?'has-error':''}">
                  <form:label path="sportActivity" cssClass="col-sm-2 control-label">
                      <fmt:message key="records.activity"/>
                  </form:label>
                  <div class="col-sm-10">
                            <form:select path="sportActivity" cssClass="form-control">
                                <c:forEach items="${activities}" var="activity">
                                    <form:option value="${activity}">${activity.activityName}</form:option>
                                </c:forEach>
                            </form:select>
                      <form:errors path="sportActivity" cssClass="help-block"/>
                  </div>
                </div>

                <div class="form-group ${startTime_error?'has-error':''}">
                    <form:label path="startTime" cssClass="col-sm-2 control-label">
                        <fmt:message key="records.start"/>
                    </form:label>
                    <div class="col-sm-10">
                        <form:input path="startTime" cssClass="form-control datepicker"/>
                        <form:errors path="startTime" cssClass="help-block"/>
                    </div>
                </div>


                <div class="form-group ${endTime_error?'has-error':''}">
                    <form:label path="endTime" cssClass="col-sm-2 control-label">
                        <fmt:message key="records.end"/>
                    </form:label>
                    <div class="col-sm-10">
                        <form:input path="endTime" cssClass="form-control datepicker"/>
                        <form:errors path="endTime" cssClass="help-block"/>
                    </div>
                </div>

                      <%--<div class="form-group ${distance_error?'has-error':''}">--%>
                          <%--<form:label path="distance" cssClass="col-sm-2 control-label">--%>
                              <%--<fmt:message key="records.distance"/>--%>
                          <%--</form:label>--%>
                          <%--<div class="col-sm-10">--%>
                              <%--<form:input path="distance" cssClass="form-control"/>--%>
                              <%--<form:errors path="distance" cssClass="help-block"/>--%>
                          <%--</div>--%>
                      <%--</div>--%>

            <button class="btn btn-primary createBtn center-block allow-vertical-space" type="submit"><fmt:message
                    key="submit"/></button>
        </form:form>
    </jsp:attribute>
</own:pagetemplate>