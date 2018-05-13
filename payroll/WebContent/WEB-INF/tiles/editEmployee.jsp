<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<a	href="${pageContext.request.contextPath}/addEmployeeJob/${employee.empId}"
					class="btn btn-primary" style="margin-left: 200px">Add Job To Employee</a>
<a	href="${pageContext.request.contextPath}/addEmployeeEnabled/${employee.empId}"
					class="btn btn-primary" >Enabled Employee</a>
<a href="${pageContext.request.contextPath}/addEmployeeBank/${employee.empId}"
					class="btn btn-primary">Add Bank to Employee</a>
<a  href="${pageContext.request.contextPath}/addEmpSalary/${employee.empId}" class="btn btn-primary">Add Salary</a>

					
<h3 style="margin-left: 200px">Please fill in your information below:</h3>
<br />
	<sf:form action="${pageContext.request.contextPath}/editEmployee/${employee.empId}"
			method="post" commandName="employee" cssStyle="form-horizontal" enctype="multipart/form-data">
<sf:input type="hidden" path="empId"  value="${employee.empId}"/>   
<div class="form-group">
		<label for="firstName" style="text-align: right;"class="col-sm-2 control-label">Firstname:</label>
		<sf:input path="firstName" id="firstName" type="text" name="firstName"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="firstName" />
		</div>
	</div>
		<div class="form-group">
		<label for="fatherName" style="text-align: right;"class="col-sm-2 control-label">FatherName:</label>
		<sf:input path="fatherName" id="fatherName" type="text" name="fatherName"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="fatherName" />
		</div>
	</div>
	<div class="form-group">
		<label for="lastName" style="text-align: right;"class="col-sm-2 control-label">Lastname:</label>
		<sf:input path="lastName" id="lastName" type="text" name="lastName"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="lastName" />
		</div>
	</div>
	<div class="form-group">
		<label for="email" style="text-align: right;" class="col-sm-2 control-label">Email:</label>
		<sf:input path="email" id="email" type="text" name="email"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="email" />
		</div>
		<br/>
		<div class="form-group">
		<label for="securityNumber" style="text-align: right;"class="col-sm-2 control-label">Sn:</label>
		<sf:input path="securityNumber" id="securityNumber" type="text" name="securityNumber"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="securityNumber" />
		</div>
	</div>
	<div class="form-group">
		<label for="date" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">DOB:</label><sf:input
			class="date" path="birsthDate" type="text" style="margin-left:20px"/>
	</div>
	
	<div class="form-group">
		<label for="kontrateDate" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Kontrate date:</label><sf:input
			class="date" path="kontrateDate" type="text" style="margin-left:20px"/>
	</div>
		
		
	</div>
		<div class="form-group">
		<label for="address" style="text-align: right;" class="col-sm-2 control-label">Address:</label>
		<sf:input path="address" id="address" type="text" name="address"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="address" />
		</div>
	</div >
		<div class="form-group">
		<label for="telephone" style="text-align: right;" class="col-sm-2 control-label">Telephone:</label>
		<sf:input path="telephone" id="telephone" type="text" name="telephone"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="telephone" />
		</div>
	</div >
	<div class="form-group">
		<label for="nationality" style="text-align: right;" class="col-sm-2 control-label">Nat:</label>
		<sf:input path="nationality" id="nationality" type="text" name="nationality"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="nationality" />
		</div>
	</div >
	
		<div class="form-group">
		<label for="pervoja" style="text-align: right;" class="col-sm-2 control-label">Nat:</label>
		<sf:input path="pervoja" id="pervoja" type="text" name="pervoja"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="pervoja" />
		</div>
	</div >
	<%-- <div class="form-group">
			<label  style="text-align: right;" class="col-sm-2 control-label">CurrentJob:</label>
			<span style="margin-left:20px" class="form-Control"><c:out   value="${empJobs[0].job.jobDescription}" ></c:out></span>
	</div> --%>
	 <div class="form-group">
            <label style="vertical-align: top; text-align: right;" class="col-sm-2 control-label" for="productImage">Upload Picture</label>
            <sf:input id="employeeCard" path="employeeCard" type="file" class="form:input-large" style="margin-left: 200px"/>
     </div>
	<br/>
	
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

				/*  if ('empJobs.length == 0') {
                	 $("#change").hide();
              
                } else {
                	
                	 $("#change").show();
                
                } */
				
			})
</script> 


