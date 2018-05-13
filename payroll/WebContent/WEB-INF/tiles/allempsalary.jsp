<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<table id="myTable" class="table table-striped table-hover">
	<thead>
		<tr class="bg-success">
			<th>FirsName</th>
			<th>Lastname</th>
			<th>Sn</th>
			<th>Pagabaze</th>
			<th>Bruto Salary</th>
			<th>Bonous</th>
			<th>Paga per banke</th>
			<th>Cash Amoun</th>
			<th>Sigurimet</th>
			<th>Taksat</th>
			<th>Paga Neto</th>
			<th>Bank</th>
		</tr>
	</thead>
	<c:forEach var="employee" items="${standing}">
		<tr>
			<td><c:out value="${employee.firstName}"></c:out></td>
			<td><c:out value="${employee.lastName}"></c:out></td>
			<td><c:out value="${employee.sn}"></c:out></td>
			<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${employee.pagabaze}"/></td>
        	<%-- 	 <c:out value="${employee.pagabaze}"></c:out> --%>
			<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${employee.brutoSalary}"/></td>
			<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${employee.bonous}"/></td>
			<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${employee.bankAmount}"/></td>
			<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${employee.cashAmount}"/></td>
			<td><c:out value="${employee.sigurimet}"></c:out></td>
			<td><c:out value="${employee.taksat}"></c:out></td>
			<td><c:out value="${employee.paganeto}"></c:out></td>
			
				<td><c:set value="${employee.sn}" var="employeeid"></c:set>
					<c:forEach items="${empBanks[employeeid]}" var="item"
						>

							<c:out value="${item.bank.bankName},"></c:out>
							<c:out value="${item.bankAccount}"></c:out>
					</c:forEach></td>
			
			
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