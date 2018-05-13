<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
	<sf:form action="${pageContext.request.contextPath}/enabledEmployees"
			method="get" commandName="employeeenabled" cssStyle="form-horizontal">

<div class="form-group">
		<label for="startDate" style="vertical-align: top; text-align: right;" 
		class="col-sm-2 control-label">Start Date:</label>
		<input class="date"	name="startDate" type="text" style="margin-left:20px" />
	</div>  
	<br/>
	
	<div class="form-group">
		<label for="enabled" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Enabled:</label> 
		<input	name="enabled"  value="false" type="checkbox"
				style="margin-left: 20px" class="form-Control" />No
			<input name="enabled" type="checkbox"
				value="true" style="margin-left: 2px"
				class="form-Control" />Yes
	</div>   
	
	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 200px">
	<a href="<c:url value="/employees" />" class="btn btn-default">Cancel</a>
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

