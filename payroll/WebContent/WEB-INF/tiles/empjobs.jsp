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
			<th>Job</th>
			<th>From</th>
			<th>to</th>
			<th>Option</th>
			
		</tr>
	</thead>
	<c:forEach var="employeejob" items="${empJobs}">
		<tr>
			 <td><c:out value="${employeejob.employee.firstName}"></c:out></td>
			<td><c:out value="${employeejob.employee.lastName}"></c:out></td>
			<td><c:out value="${employeejob.employee.securityNumber}"></c:out></td>
			<td><c:out value="${employeejob.job.jobDescription}"></c:out></td> 
			<fmt:formatDate value="${employeejob.startDate}" pattern="dd/MM/yyyy" var="startDate"/>
			<td><c:out value="${startDate}"></c:out></td>
			<fmt:formatDate value="${employeejob.endDate}" pattern="dd/MM/yyyy" var="endDate"/>
			<td><c:out value="${endDate}"></c:out></td>
			<td><a
					href="${pageContext.request.contextPath}/editEmployeeJob/${employeejob.id}"><span
						class="glyphicon glyphicon-pencil"></span></a>
			<a
					href="${pageContext.request.contextPath}/deleteEmployeeJob/${employeejob.id}"><span
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
<a href="${pageContext.request.contextPath}/reportEmpJob" class="btn btn-primary">Excel</a>
<a href="<c:url value = "/employees" />" class="btn btn-primary">Back</a>
</div>
</body>
</html>