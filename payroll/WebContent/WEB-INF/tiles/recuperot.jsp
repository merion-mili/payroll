<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
	<table id="myTable" class="table table-striped table-hover">
		<thead>
			<tr class="bg-success">
				<th>Emri</th>
				<th>Mbiemri</th>
				<th>SN</th>
				<th>Reucuperot</th>
				<th>Option</th>
			</tr>
		</thead>
		<c:forEach var="recupero" items="${recuperot}">
			<tr>
				<td><c:out value="${recupero.employee.firstName}"></c:out></td>
				<td><c:out value="${recupero.employee.lastName}"></c:out></td>
				<td><c:out value="${recupero.employee.securityNumber}"></c:out></td>
				<td><c:out value="${recupero.gjendjerecupero}"></c:out></td>
				<td><a
					href="${pageContext.request.contextPath}/deleterecupero/${recupero.id}"><span
						onclick="return confirm('Are you sure to delete this?')"
						class="glyphicon glyphicon-remove"></span></a><a
					href="${pageContext.request.contextPath}/editrecupero/${recupero.id}"><span
						class="glyphicon glyphicon-pencil"></span></a></td>
			</tr>

		</c:forEach>
	</table>

	<script>
		$(document).ready(function() {
			$('#myTable').dataTable();
		});
	</script>

	<a href="${pageContext.request.contextPath}/addrecupero"
		class="btn btn-primary">Add recupero</a>
	<a href="${pageContext.request.contextPath}/reportRecuperot"
					class="btn btn-primary" >Excel</a>
</body>
</html>