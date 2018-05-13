<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>

<body>
<div class="table-responsive">
	<table id="myTable" class="table table-striped table-hover display compact nowrap" >
		<thead>
			<tr class="bg-success">
			
			<!-- 	<th>Card</th> -->
				<th>Emri</th>
				<th>Mbiemri</th>
				<th>Fathername</th>
			<!-- 	<th>Email</th> -->
				<th>DOB</th>
				<th>Sn</th>
				<th>Address</th>
				<th>Telephone</th>
				<th>Job</th>
				<th>Pervoja</th>
				<th>View</th>
				<!-- <th>Salaries</th>
				<th>Jobs</th>
				<th>Banks</th>
				<th>Enabled</th> -->
				<th>Edit</th>
			</tr>
		</thead>
	
		<c:forEach var="employee" items="${employees}">
			<tr>
				<%-- <td><img
					src="<c:url value="/static/images/${employee.empId}.png" />"
					 alt="image"
					style="width: 50%" /></td> --%>
				<td><c:out value="${employee.firstName}"></c:out></td>
				<td><c:out value="${employee.lastName}"></c:out></td>
				 <td><c:out value="${employee.fatherName}"></c:out></td> 
			 
				<td><fmt:formatDate value="${employee.birsthDate}"
						pattern="dd/MM/yyyy" /></td>
				<td><c:out value="${employee.securityNumber}"></c:out></td>
				<td><c:out value="${employee.address}"></c:out></td>
				<td><c:out value="${employee.telephone}"></c:out></td>
				<%-- <td><c:out value="${employee.nationality}"></c:out></td> --%>

				<td><c:set value="${employee.empId}" var="employeeid"></c:set>
					<c:forEach items="${empJobs[employeeid]}" var="item"
						varStatus="loop">

						<c:if test="${loop.last}">
							<c:out value="${item.job.jobDescription}"></c:out>
						</c:if>

					</c:forEach></td>
				<td><c:out value="${employee.pervoja}"></c:out></td> 
				<td>
					<select id="dataPicker" class="selectpicker" data-style="btn-new" onchange="window.location=this.options[this.selectedIndex].value">
						<option value="default" selected="selected">Select..</option>
	    				<option value="${pageContext.request.contextPath}/empsalaries/${employee.empId}" >Salary</option>
	    				<option value="${pageContext.request.contextPath}/empjobs/${employee.empId}" >Jobs</option>
	    				<option value="${pageContext.request.contextPath}/empbanks/${employee.empId}" >Banks</option>
	    				<option value="${pageContext.request.contextPath}/empenabled/${employee.empId}" >Enabled</option>
					</select>
					
				</td>
				
			 	<%-- <td>
					<a href="${pageContext.request.contextPath}/empsalaries/${employee.empId}"><span
						class="glyphicon glyphicon-search"></span></a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/empjobs/${employee.empId}"><span
						class="glyphicon glyphicon-search"></span></a></td>
				<td>
				
					<a href="${pageContext.request.contextPath}/empbanks/${employee.empId}"><span
						class="glyphicon glyphicon-search"></span></a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/empenabled/${employee.empId}"><span
						class="glyphicon glyphicon-search"></span></a>
				</td> --%> 
				<td>
					<a href="${pageContext.request.contextPath}/editEmployee/${employee.empId}"><span
						class="glyphicon glyphicon-pencil"></span></a> 
				
					<a href="${pageContext.request.contextPath}/deleteEmployee/${employee.empId}"><span
						onclick="return confirm('Are you sure to delete this?')"
						class="glyphicon glyphicon-remove"></span></a>
					
					<a href="${pageContext.request.contextPath}/viewEmployee/${employee.empId}"><span
						class="glyphicon glyphicon-info-sign"></span></a>
				
				</td>
			</tr>

		</c:forEach>
		
	</table>
</div>
	<script>
		$(document).ready(function() {
			$('#myTable').DataTable();
	
		});
		$('#dataPicker option').each(function () {
		    if (this.defaultSelected) {
		        this.selected = true;
		       
		    }
		});
	</script>
	
	<a href="${pageContext.request.contextPath}/newemployee"
		class="btn btn-primary">Add Employee</a>
	<a href="${pageContext.request.contextPath}/reportEmployee"
					class="btn btn-primary" >Excel</a>
		
		
</body>
</html>