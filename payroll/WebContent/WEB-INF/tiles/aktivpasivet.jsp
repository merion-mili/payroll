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
				<th>Gjendje Bank</th>
				<th>Gejndje Cash</th>
				<th>Option</th>
			</tr>
		</thead>
		<c:forEach var="aktivpasiv" items="${aktivpasivet}">
			<tr>
				<td><c:out value="${aktivpasiv.employee.firstName}"></c:out></td>
				<td><c:out value="${aktivpasiv.employee.lastName}"></c:out></td>
				<td><c:out value="${aktivpasiv.employee.securityNumber}"></c:out></td>
				<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${aktivpasiv.gjendjeBank}"/></td>
        		 <%-- <c:out value="${aktivpasiv.gjendjeBank}"></c:out> --%>
				<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${aktivpasiv.gjendjeCash}"/></td>
				<td><a
					href="${pageContext.request.contextPath}/deleteaktivpasiv/${aktivasiv.id}"><span
						onclick="return confirm('Are you sure to delete this?')"
						class="glyphicon glyphicon-remove"></span></a><a
					href="${pageContext.request.contextPath}/editaktivpasiv/${aktivpasiv.id}"><span
						class="glyphicon glyphicon-pencil"></span></a></td>
			</tr>

		</c:forEach>
	</table>

	<script>
		$(document).ready(function() {
			$('#myTable').dataTable();
		});
	</script>

	<a href="${pageContext.request.contextPath}/addaktivpasiv"
		class="btn btn-primary">Add</a>
	<a href="${pageContext.request.contextPath}/reportAktivPasiv"
					class="btn btn-primary" >Excel</a>
</body>
</html>