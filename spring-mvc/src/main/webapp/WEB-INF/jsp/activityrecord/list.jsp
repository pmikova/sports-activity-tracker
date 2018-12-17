<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="own" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<own:pagetemplate>

    <jsp:attribute name="body">

        <div class="jumbotron homepage">

           <table class="table">
               <caption><fmt:message key="record.all"/></caption>
               <thead>
               <tr>
                   <th>ID</th>
                   <th>Activity</th>
                   <th>Date performed</th>
                   <th>Calories burned</th>
               </tr>
               </thead>
               <tbody>
               <c:forEach items="${calories}" var="calorie">
                   <tr>
                       <td>${calorie.activityRecord.id}</td>
                       <td>${calorie.activityRecord.sportActivity.activityName}</td>
                       <td>${calorie.burnedCalories}</td>
                       <%--<td><fmt:formatDate pattern="yyyy/MM/dd"
                                           value="${record.startTime}"/></td>
                       <td><fmt:formatDate type="time"
                                           value="${record.duration}"/></td>--%>

                       <td>
                           <a href="records/detail/${calorie.id}" class="btn btn-primary"><fmt:message key="view"/></a>
                       </td>

                   </tr>
               </c:forEach>
               </tbody>
           </table>
        <a href="records/create" class="btn btn-primary"><fmt:message key="create"/></a>

    </jsp:attribute>
</own:pagetemplate>
