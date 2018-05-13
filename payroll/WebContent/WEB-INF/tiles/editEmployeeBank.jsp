<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
	<sf:form action="${pageContext.request.contextPath}/editEmployeeBank/${empBank.id}"
			method="post" commandName="empBank" cssStyle="form-horizontal">
<sf:input type="hidden" path="id"  value="${empBank.id}"/>   
	<div class="form-group">
		<label  style="text-align: right;" class="col-sm-2 control-label">Current Employee:</label>
			<span style="margin-left:20px" class="form-Control">
			<c:out   value="${empBank.employee.firstName} ${empBank.employee.lastName} ${empBank.employee.securityNumber}" ></c:out></span>
	</div>
	
		<div class="form-group">
		<label  style="text-align: right;" class="col-sm-2 control-label">Employee:</label>
		<select  id="employee" name="employee"  style="width: 250px;margin-left:200px">
        	<c:forEach items="${employees}" var="item" > 
           		 <option value="${item.empId}" >${item.firstName} ${item.lastName}</option>
        	</c:forEach>
    </select>   
	</div>  
	
	
	<div class="form-group">
			<label  style="text-align: right;" class="col-sm-2 control-label">CurrentBank:</label>
			<span style="margin-left:20px" class="form-Control"><c:out   value="${empBank.bank.bankName}" ></c:out></span>
	</div>
	<br/>
	<div class="form-group">
		<label  style="text-align: right;" class="col-sm-2 control-label">Banks:</label>
		<select  id="bank" name="bank"  class="form-control" style="width: 250px;margin-left:200px">
        	<c:forEach items="${banks}" var="item" > 
           		 <option value="${item.bankId}" >${item.bankName}</option>
        	</c:forEach>
    </select>   
	</div>  
	<br/>
	<div class="form-group">
		<label for="bankAccount" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Bank Account:</label>
		<input  name="bankAccount" type="text" style="margin-left:20px" value="${empBank.bankAccount}"/>
	</div>
	<br/>

	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 200px">
	<a href="<c:url value="/allempbanks" />" class="btn btn-default">Cancel</a>
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

				 $("#employee").select2({ width: 'resolve' });
				
			})
</script> 

