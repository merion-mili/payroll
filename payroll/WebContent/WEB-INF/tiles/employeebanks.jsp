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
			<th>Banks</th>
			<th>Bank Account</th>
			<th>Option</th>
			
		</tr>
	</thead>
	<c:forEach var="employeebank" items="${employeeBanks}">
		<tr>
			 <td><c:out value="${employeebank.employee.firstName}"></c:out></td>
			<td><c:out value="${employeebank.employee.lastName}"></c:out></td>
			<td><c:out value="${employeebank.employee.securityNumber}"></c:out></td>
			<td><c:out value="${employeebank.bank.bankName}"></c:out></td> 
			<td><c:out value="${employeebank.bankAccount}"></c:out></td> 
			<td><a
					href="${pageContext.request.contextPath}/editEmployeeBank/${employeebank.id}"><span
						class="glyphicon glyphicon-pencil"></span></a>
			<a
					href="${pageContext.request.contextPath}/deleteEmployeeBank/${employeebank.id}"><span
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
<div style="margin-left:10px"><a href="${pageContext.request.contextPath}/reportEmpBank" class="btn btn-primary" >Excel</a>
<a href="<c:url value = "/employees" />" class="btn btn-primary">Back</a>
</div>
</body>
</html>