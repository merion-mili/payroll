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
			<th>Pershkrimi</th>
			<th>Option</th>
		</tr>
	</thead>
	<c:forEach var="job" items="${jobs}">
		<tr>
			<td><c:out value="${job.jobDescription}"></c:out></td>

			<td><a
				href="${pageContext.request.contextPath}/delete/${job.jobId}"><span
					onclick="return confirm('Are you sure to delete this?')" class="glyphicon glyphicon-remove"></span></a><a
				href="${pageContext.request.contextPath}/update/${job.jobId}"><span
					class="glyphicon glyphicon-pencil"></span></a></td>
		</tr>

	</c:forEach>
</table>

<script>
$(document).ready(function(){
    $('#myTable').dataTable();
});
</script>

<a href="${pageContext.request.contextPath}/add"
	class="btn btn-primary">Add Job</a>
</body>
</html>