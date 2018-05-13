<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
	<sf:form action="${pageContext.request.contextPath}/resultMonthSalaryExcel"
			method="get" commandName="workingdays" cssStyle="form-horizontal">

<div class="form-group">
		<label style="vertical-align: top; text-align: right;"  for="kantier" class="col-sm-2 control-label">Muaj:</label>
		<select id="month" name="month" class="form-control" style="width: 250px;margin-left:200px">
	        <c:forEach items="${enums}" var="entry"> 
	            <option value="${entry.value}">${entry.key}</option>
	        </c:forEach>
	    </select>   
	</div>  
	
	
		
	<div class="form-group">
		<label style="vertical-align: top; text-align: right;"  for="year" class="col-sm-2 control-label">Years:</label>
		<select id="year" name="year" class="form-control" style="width: 250px;margin-left:200px">
	        <c:forEach items="${years}" var="year"> 
	            <option value="${year.year}">${year.year}</option>
	        </c:forEach>
	    </select>   
	</div>  
	<br/>
	
	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 200px">
	<a href="<c:url value="/allpagat" />" class="btn btn-default">Cancel</a>
</sf:form>

<%-- <table id="myTable" class="table table-striped table-hover">
	<thead>
		<tr class="bg-success">
			<th>FirstName</th>
			<th>LastName</th>
		</tr>
	</thead>
	<c:forEach var="employee" items="${standing}">
		<tr>
			<td><c:out value="${employee.firstName}"></c:out></td>
			<td><c:out value="${employee.lastName}"></c:out></td>
		
		</tr>

	</c:forEach>
</table> --%>

