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
				<th>Kantier</th>
				<th>Date</th>
				<th>Oret e punuara </th>
				<th>Oret Shtese</th>
				<th>Recuo</th>
				<th>Total Recu</th>
				<th>Gjendja Recu</th>
				<th>Lejet Ditore</th>
				<th>Festat</th>
				<th>Leja</th>
				<th>Comment</th>
				<th>Option</th>
			</tr>
		</thead>
		<c:forEach var="paga" items="${pagat}">
			<tr>
				<td><c:out value="${paga.recuperoEmployee.employee.firstName}"></c:out></td>
				<td><c:out value="${paga.recuperoEmployee.employee.lastName}"></c:out></td>
				<td><c:out value="${paga.recuperoEmployee.employee.securityNumber}"></c:out></td>
				<td><c:out value="${paga.kantier.kantierName}"></c:out></td>
				<td><fmt:formatDate value="${paga.date}"
						pattern="dd/MM/yyyy" /></td>
				<td><c:out value="${paga.oretepunuara}"></c:out></td>
				<td><c:out value="${paga.oretshtese}"></c:out></td>
				<td><c:out value="${paga.recupero}"></c:out></td>
				<td><c:out value="${paga.totalirecupero}"></c:out></td>
				<td><c:out value="${paga.recuperoEmployee.gjendjerecupero}"></c:out></td>
				<td><c:out value="${paga.lejeDitore}"></c:out></td>
				<td><c:out value="${paga.festa}"></c:out></td>
				<td><c:out value="${paga.lejeVjetore}"></c:out></td>
				<td><c:out value="${paga.comment}"></c:out></td>
				<td><a
					href="${pageContext.request.contextPath}/deletePagen/${paga.id}"><span
						onclick="return confirm('Are you sure to delete this?')"
						class="glyphicon glyphicon-remove"></span></a><a
					href="${pageContext.request.contextPath}/editPagen/${paga.id}"><span
						class="glyphicon glyphicon-pencil"></span></a></td>
			</tr>

		</c:forEach>
	</table>
	
	
	<script>
		$(document).ready(function() {
			$('#myTable').dataTable();
		});
	</script>

	<a href="${pageContext.request.contextPath}/addpage"
		class="btn btn-primary">Add time</a>
	<a href="${pageContext.request.contextPath}/reportOfPresence"
					class="btn btn-primary" >Excel</a>
		
</body>
</html>