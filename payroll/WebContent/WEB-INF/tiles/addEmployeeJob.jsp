<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
	<sf:form action="${pageContext.request.contextPath}/createEmployeeJob"
			method="post" commandName="employeeJob" cssStyle="form-horizontal">
	<sf:input type="hidden" path="id"  value="${employeeJob.id}"/>   
	
	<div class="form-group">
		<label for="employee" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Employee:</label>	
		<select id="employee" name="employee" class="form-control" style="width: 250px;margin-left:200px">
       	 	<c:forEach items="${employees}" var="item"> 
            	<option value="${item.empId}">${item.firstName} ${item.lastName}</option>
        	</c:forEach>
    	</select>   
	</div> 

	
	<div class="form-group"> 	
	<label for="job" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Job:</label>	
		<select id="job" name="job" class="form-control" style="width: 250px;margin-left:200px">
       	 	<c:forEach items="${jobs}" var="item"> 
            	<option value="${item.jobId}">${item.jobDescription}</option>
        	</c:forEach>
    	</select>   
	</div>  
	<br/>
	
<!-- 	<div class="form-group">
		<label for="pagasera" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Paga Sera:</label>
		<input  name="pagasera" type="text" style="margin-left:20px" />
	</div>
	<br/>
	
	<div class="form-group">
		<label for="pagakantier" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Paga Kantier:</label>
		<input name="pagakantier" type="text" style="margin-left:20px" />
	</div>
	<br/>
		<div class="form-group">
		<label for="pagakontrate" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Paga Kontrate:</label>
		<input name="pagakontrate" type="text" style="margin-left:20px" />
	</div>
	<br/> -->
	<div class="form-group">
		<label for="startDate" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Start:</label><input
		class="date"	name="startDate" type="text" style="margin-left:20px" />
	</div>
	<br/>
	<div class="form-group">
		<label for="endDate" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Finish:</label><input
			class="date" name="endDate" type="text" style="margin-left:20px" />
	</div>
	<!-- <div class="form-group">
		<label for="enabled" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Enabled:</label> 
		<input	name="enabled"  value="false" type="checkbox"
				style="margin-left: 20px" class="form-Control" />No
			<input name="enabled" type="checkbox"
				value="true" style="margin-left: 2px"
				class="form-Control" />Yes
	</div> -->
	<br />

	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 200px">
	<a href="${pageContext.request.contextPath}/editEmployee/${employees[0].empId}"
						class="btn btn-default">Cancel</a> 
	<a href="<c:url value = "/employees" />" class="btn btn-primary">Back</a>
</sf:form>
<script>
	$(document).ready(
			function() {
				var date_input = $('.date'); //our date input has the name "date"
				var container = $('.bootstrap-iso form').length > 0 ? $(
						'.bootstrap-iso form').parent() : "body";
				var options = {
					format : 'dd/mm/yyyy',
					container : container,
					todayHighlight : true,
					autoclose : true,
				};
				date_input.datepicker(options);
				
			})
</script>

