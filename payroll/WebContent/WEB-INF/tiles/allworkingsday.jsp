<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<table id="myTable" class="table table-striped table-hover">
	<thead>
		<tr class="bg-success">
			<th>FirsName</th>
			<th>Lastname</th>
			<th>Sn</th>
			<th>Pune</th>
			<th>Festat</th>
			<th>Oret Shtese</th>
			<th>Lejet Ditore</th>
			<th>Raportet</th>
			<th>Leje Vjetore</th>
			<th>Recuperot</th>
			<th>Gjendje Rec</th>
		</tr>
	</thead>
	<c:forEach var="employee" items="${standing}">
		<tr>
			<td><c:out value="${employee.firstName}"></c:out></td>
			<td><c:out value="${employee.lastName}"></c:out></td>
			<td><c:out value="${employee.sn}"></c:out></td>
			<td><c:out value="${employee.ditetEPunuara}"></c:out></td>
			<td><c:out value="${employee.ditetEfestave}"></c:out></td>
			<td><c:out value="${employee.totaloretshteseSera}"></c:out></td>
			<td><c:out value="${employee.totalLejeDitore}"></c:out></td>
			<td><c:out value="${employee.totalRaportet}"></c:out></td>
			<td><c:out value="${employee.ditetLejeVjetore}"></c:out></td>
			<td><c:out value="${employee.totalRecupero}"></c:out></td>
			<td><c:out value="${employee.gjendjaRecupero}"></c:out></td>
			
		</tr>

	</c:forEach>
</table>

<script>
$(document).ready(function(){
    $('#myTable').dataTable();
});
</script>

<%-- <a href="${pageContext.request.contextPath}/addYear"
	class="btn btn-primary">Add Year</a> --%>
</body>
</html>