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
				<!-- <th>SN</th> -->
				<th>Date</th>
				<th>Paga Cash</th>
				<th>Paga Neto</th>
				<th>Pagesa Cash</th>
				<th>Pagesa Neto</th>
			<!-- 	<th>Paradhenie</th> -->
				<th>Banka</th>
				<!-- <th>Total Cash</th> -->
				<th>Gjendje Cash</th>
				<!-- <th>Total Bank</th> -->
				<th>Gjendje Bank</th>
				<th>Option</th>
			</tr>
		</thead>
		<c:forEach var="pagesa" items="${pagesat}">
			<tr>
				<td><c:out value="${pagesa.aktivPasiv.employee.firstName}"></c:out></td>
				<td><c:out value="${pagesa.aktivPasiv.employee.lastName}"></c:out></td>
			<%-- 	<td><c:out value="${pagesa.aktivPasiv.employee.securityNumber}"></c:out></td> --%>
				<td><fmt:formatDate value="${pagesa.date}"
						pattern="dd/MM/yyyy" /></td>
				<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${pagesa.cashAmount}"/></td>
				<%-- <c:out value="${pagesa.cashAmount}"></c:out></td> --%>
				<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${pagesa.bankAmount}"/></td>
				<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${pagesa.pagesaCash}"/></td>
				<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${pagesa.pagesaBank}"/></td>
				<%-- <td><c:out value="${pagesa.paradhenie}"></c:out></td> --%>
				<td><c:set value="${pagesa.aktivPasiv.employee.empId}" var="employeeid"></c:set>
					<c:forEach items="${empBanks[employeeid]}" var="item"
						varStatus="loop">

						<c:if test="${loop.last}">
							<c:out value="${item.bankAccount}"></c:out>
						</c:if>
					</c:forEach></td>
	
				
				<%-- <td><c:out value="${pagesa.totalCash}"></c:out></td> --%>
				<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${pagesa.aktivPasiv.gjendjeCash}"/></td>
				<%-- <td><c:out value="${pagesa.totalBank}"></c:out></td> --%>
				<td><fmt:formatNumber type = "number" 
        		 maxFractionDigits = "2"  value="${pagesa.aktivPasiv.gjendjeBank}"/></td>
				
				<td><a
					href="${pageContext.request.contextPath}/deletepagesen/${pagesa.id}"><span
						onclick="return confirm('Are you sure to delete this?')"
						class="glyphicon glyphicon-remove"></span></a><a
					href="${pageContext.request.contextPath}/editpagesen/${pagesa.id}"><span
						class="glyphicon glyphicon-pencil"></span></a></td>
			</tr>

		</c:forEach>
	</table>

	<script>
		$(document).ready(function() {
			$('#myTable').dataTable();
		});
	</script>

	<a href="${pageContext.request.contextPath}/addpagesen"
		class="btn btn-primary">Add Pagese</a>
</body>
</html>