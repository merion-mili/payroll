<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<table id="myTable" class="table table-striped table-hover">
	<thead>
		<tr class="bg-success">
			<th>Emri</th>
			<th>Mbiemri</th>
			<th>Sn</th>
			<th>From</th>
			<th>to</th>
			<th>Active</th>
			<th>Option</th>
			
		</tr>
	</thead>
	<c:forEach var="employeeenabled" items="${employeeEnabled}">
		<tr>
			 <td><c:out value="${employeeenabled.employee.firstName}"></c:out></td>
			<td><c:out value="${employeeenabled.employee.lastName}"></c:out></td>
			<td><c:out value="${employeeenabled.employee.securityNumber}"></c:out></td>
			<fmt:formatDate value="${employeeenabled.startDate}" pattern="dd/MM/yyyy" var="startDate"/>
			<td><c:out value="${startDate}"></c:out></td>
			<fmt:formatDate value="${employeeenabled.endDate}" pattern="dd/MM/yyyy" var="endDate"/>
			<td><c:out value="${endDate}"></c:out></td>
			<td><c:choose><c:when test="${employeeenabled.enabled}">enabled</c:when><c:otherwise>disabled</c:otherwise></c:choose>
			<%-- <c:out value="${employeeenabled.enabled}"></c:out></td>  --%> 
			<td><a href="${pageContext.request.contextPath}/editEmployeeEnabled/${employeeenabled.id}"><span
						class="glyphicon glyphicon-pencil"></span></a>
			<a href="${pageContext.request.contextPath}/deleteEmployeeEnabled/${employeeenabled.id}"><span
						onclick="return confirm('Are you sure to delete this?')"
						class="glyphicon glyphicon-remove"></span></a></td>
		</tr>

	</c:forEach>
</table>


<script>
$(document).ready(function(){
    $('#myTable').dataTable();
});
</script>
<div style="margin-left:10px">
<a href="${pageContext.request.contextPath}/reportEmpEnabled"
					class="btn btn-primary" >Excel</a>
<a href="<c:url value = "/employees" />" class="btn btn-primary">Back</a>
</div>
</body>
</html>