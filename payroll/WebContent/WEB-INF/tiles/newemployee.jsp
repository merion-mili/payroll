<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
	<sf:form action="${pageContext.request.contextPath}/createemployee"
			method="post" commandName="employee" cssStyle="form-horizontal" enctype="multipart/form-data">
	<input type="hidden" name="id"  value="${employee.empId}"/>   
	
	<div class="form-group">
		<label for="firstName" style="text-align: right;"class="col-sm-2 control-label">Firstname:</label>
		<sf:input path="firstName" id="firstName" type="text" name="firstName"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="firstName" />
		</div>
	</div>
	<br/>
	
	<div class="form-group">
		<label for="fatherName" style="text-align: right;"class="col-sm-2 control-label">FatherName:</label>
		<sf:input path="fatherName" id="fatherName" type="text" name="fatherName"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="fatherName" />
		</div>
	</div>
	<br/>
	
	<div class="form-group">
		<label for="lastName" style="text-align: right;"class="col-sm-2 control-label">Lastname:</label>
		<sf:input path="lastName" id="lastName" type="text" name="lastName"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="lastName" />
		</div>
	</div>
	<br/>
	
	<div class="form-group">
		<label for="email" style="text-align: right;" class="col-sm-2 control-label">Email:</label>
		<sf:input path="email" id="email" type="text" name="email"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="email" />
		</div>
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
	<br/>
	
	<div class="form-group">
		<label for="date" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">DOB:</label><sf:input
			 class="date" path="birsthDate" type="text" style="margin-left:20px"/>
		<br/>
		 <div class="erroracount">
				<sf:errors path="birsthDate" />
		</div>
	</div>
	<br/>	 
	
	<div class="form-group">
		<label for="kontrateDate" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Kontrate Date:</label><sf:input
		 class="date" path="kontrateDate" type="text" style="margin-left:20px"/>
		 <br/>
		 <div class="erroracount">
				<sf:errors path="birsthDate" />
		</div>
	</div>
	<br/>	
	
	
	
	<div class="form-group">
		<label for="address" style="text-align: right;" class="col-sm-2 control-label">Address:</label>
		<sf:input path="address" id="address" type="text" name="address"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="address" />
		</div>
	</div>
	<br/>
	
	<div class="form-group">
		<label for="telephone" style="text-align: right;" class="col-sm-2 control-label">Telephone:</label>
		<sf:input path="telephone" id="telephone" type="text" name="telephone"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="telephone" />
		</div>
	</div>
	<br/>
	<div class="form-group">
		<label for="nationality" style="text-align: right;" class="col-sm-2 control-label">Nat:</label>
		<sf:input path="nationality" id="nationality" type="text" name="nationality"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="nationality" />
		</div>
	</div>
	<br/>
	
		<div class="form-group">
		<label for="pervoja" style="text-align: right;" class="col-sm-2 control-label">Pervoja:</label>
		<sf:input path="pervoja" id="pervoja" type="text" name="pervoja"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="pervoja" />
		</div>
	</div>
	<br/>
	
		
	<%-- <div class="form-group">
		<label style="vertical-align: top; text-align: right;"  for="empBanks" class="col-sm-2 control-label">Bank:</label>
		<select id="empBanks" name="empBanks" class="form-control" style="width: 250px;margin-left:200px">
	        <c:forEach items="${banks}" var="item"> 
	            <option value="${item.bankId}">${item.bankName}</option>
	        </c:forEach>
	    </select>   
	</div>  
	<br/>
	<div class="form-group">
		<label for="bankAccount" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Bank Acount:</label>
		<input  name="bankAccount" type="text" style="margin-left:20px" />
	</div>
	<br/>
	
	<div class="form-group">
		<label style="vertical-align: top; text-align: right;"  for="empJobs" class="col-sm-2 control-label">Job:</label>
		<select id="empJobs" name="empJobs" class="form-control" style="width: 250px;margin-left:200px">
	        <c:forEach items="${jobs}" var="item"> 
	            <option value="${item.jobId}">${item.jobDescription}</option>
	        </c:forEach>
	    </select>   
	</div>  
	<br/>

	
	<div class="form-group">
		<label for="pagasera" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Paga Sera:</label>
		<input  name="pagasera" type="text" style="margin-left:20px" />
	</div>
	<br/>
	
	<div class="form-group">
		<label for="pagakantier" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Paga Knatier:</label>
		<input name="pagakantier" type="text" style="margin-left:20px" />
	</div>
	<br/>
	
	<div class="form-group">
		<label for="pagakontrate" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Paga Sera:</label>
		<input  name="pagakontrate" type="text" style="margin-left:20px" />
	</div>
	<br/>
	
	<div class="form-group">
		<label for="startDate" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Start:</label>
		<input 			 class="date" name="startDate" type="text" style="margin-left:20px" />
	</div>
	<br/>
	<div class="form-group">
		<label for="endDate" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Finish:</label><input
			 class="date" name="endDate" type="text" style="margin-left:20px" />
	</div>
	<br/> --%>
	 <div class="form-group">
            <label style="vertical-align: top; text-align: right;" class="col-sm-2 control-label" for="productImage">Upload Picture</label>
            <sf:input id="employeeCard" path="employeeCard" type="file" class="form:input-large"/>
     </div>
     <br/>
	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 200px">
	<a href="<c:url value="/" />" class="btn btn-default">Cancel</a>
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
					autoclose : true
					
				};
				date_input.datepicker(options);
				
			})
</script>

