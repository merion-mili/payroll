<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div class="container-wrapper">
	<div class="container">
		<div class="page-header">
			<h1>Employee Detail</h1>
			<p class="lead">Here is the detail information of employee</p>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<img
						src="<c:url value="/static/images/${employee.empId}.png" />"
						style="width: 100%; height: 300px" />
				</div>
				<div class="col-md-5">
					<h3>${employee.firstName} ${employee.lastName}</h3>
					
					<p>
						<strong>DOB</strong> : ${employee.birsthDate}

					</p>
				<c:set value="${employee.empId}" var="employeeid"></c:set>
				
					<c:forEach items="${empJobs[employeeid]}" var="item"
						varStatus="loop">

						<c:if test="${loop.last}">
							<%-- <strong>Paga Sera</strong>: ${item.totalsera}<br/> 
							<strong>Paga Kantier</strong>: ${item.totalkantier}<br/>  --%>
							<strong>Job</strong>:  ${item.job.jobDescription } 
						</c:if>

					</c:forEach>
					<p/>
					
					<c:forEach items="${empSalaries[employeeid]}" var="item2"
						varStatus="loop">

						<c:if test="${loop.last}">
							<strong>Paga Sera</strong>: ${item2.totalsera}<br/> 
							<strong>Paga Kantier</strong>: ${item2.totalkantier}<br/> 
							
						</c:if>

					</c:forEach>
					
					<c:set var="employee" scope="page" value="/employees" />
					<br/>
					<a href="<c:url value = "${employee}" />" class="btn btn-default">Back</a>
					
				</div>
			</div>

		</div>


	</div>
</div>