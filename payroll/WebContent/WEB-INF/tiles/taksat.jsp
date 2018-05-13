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
				<th>Year</th>
				<th>Paga min</th>
				<th>Paga max</th>
				<th>Min %</th>
				<th>Max %</th>
				<th>Option</th>
			</tr>
		</thead>
		<c:forEach var="taksa" items="${taksat}">
			<tr>
				<td><c:out value="${taksa.year}"></c:out></td>
				<td><c:out value="${taksa.pageminimale}"></c:out></td>
				<td><c:out value="${taksa.pagemaksimale}"></c:out></td>
				<td><c:out value="${taksa.kfpagmin}"></c:out></td>
				<td><c:out value="${taksa.kfpagmax}"></c:out></td>
				<td><a
					href="${pageContext.request.contextPath}/deletetaksat/${taksa.taksId}"><span
						onclick="return confirm('Are you sure to delete this?')"
						class="glyphicon glyphicon-remove"></span></a><a
					href="${pageContext.request.contextPath}/edittaksat/${taksa.taksId}"><span
						class="glyphicon glyphicon-pencil"></span></a></td>
			</tr>

		</c:forEach>
	</table>

	<script>
		$(document).ready(function() {
			$('#myTable').dataTable();
		});
	</script>

	<a href="${pageContext.request.contextPath}/addtaksat"
		class="btn btn-primary">Add</a>
	<%-- <a href="${pageContext.request.contextPath}/reportAktivPasiv"
					class="btn btn-primary" >Excel</a> --%>
</body>
</html>