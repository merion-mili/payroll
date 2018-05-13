<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
	<sf:form action="${pageContext.request.contextPath}/editPagen/${page.id}"
			method="post" commandName="page" cssStyle="form-horizontal">
<input type="hidden" name="id"  value="${page.id}"/> 
	<div class="form-group">
		<label for="date" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Date:</label><input
			 class="date" name="date" type="text" style="margin-left:20px"  value="<fmt:formatDate value="${page.date}" pattern="dd/MM/yyyy" />"/>
	</div>
	<br/>
	<div class="erroracount">
			<sf:errors path="date" />
		</div>
	<br/>	  
	<div class="form-group">
		<label for="oretepunuara" style="text-align: right;"class="col-sm-2 control-label">Oret:</label>
		<input   id="oretepunuara" type="text" name="oretepunuara" value="${page.oretepunuara}"
			style="margin-left:20px" class="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="oretepunuara" />
		</div>
	</div>
	<div class="form-group">
		<label for="oretshtese" style="text-align: right;"class="col-sm-2 control-label">Shtesat:</label>
		<input   id="oretshtese" type="text" name="oretshtese" value="${page.oretshtese}"
			style="margin-left:20px" class="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="oretshtese" />
		</div>
	</div>
	<div class="form-group">
		<label for="lejeDitore" style="text-align: right;" class="col-sm-2 control-label">Leje Ditorek:</label>
		<input  id="lejeDitore" type="text" name="lejeDitore" value="${page.lejeDitore}"
			style="margin-left:20px" class="form-Control" />
		<br />
	</div>
	<div class="erroracount">
			<sf:errors path="lejeDitore" />
		</div>
		<br/>
	
	<div class="form-group">
		<label for="festa" style="text-align: right;" class="col-sm-2 control-label">Festa:</label>
		<input  id="festa" type="text" name="festa" value="${page.festa}"
			style="margin-left:20px" class="form-Control" />
		<br />
	</div>
	<div class="erroracount">
			<sf:errors path="festa" />
	</div>
	<br/>
		
	<div class="form-group">
		<label for="recupero" style="text-align: right;" class="col-sm-2 control-label">Recupero:</label>
		<input  id="recupero" type="text" name="recupero" value="${page.recupero}"
			style="margin-left:20px" class="form-Control" />
		<br />
	</div>
	<div class="erroracount">
			<sf:errors path="recupero" />
		</div>
		<br/>
	<div class="form-group">
		<label for="raportet" style="text-align: right;" class="col-sm-2 control-label">Raportet:</label>
		<input  id="raportet" type="text" name="raportet" value="${page.raportet}"
			style="margin-left:20px" class="form-Control" />
		<br />
	</div>
	<div class="erroracount">
			<sf:errors path="raportet" />
		</div>
		<br/>	

	<div class="form-group">
		<label for="lejeVjetore" style="text-align: right;" class="col-sm-2 control-label">Leja Vjetore:</label>
		<input  id="lejeVjetore" type="text" name="lejeVjetore" value="${page.lejeVjetore}"
			style="margin-left:20px" class="form-Control" />
		<br />
	</div>
	<div class="erroracount">
			<sf:errors path="lejeVjetore" />
		</div>
		<br/>
			<div class="form-group">
		<label for="comment" style="text-align: right;" class="col-sm-2 control-label">Comment:</label>
		<input  id="comment" type="text" name="comment" value="${page.comment}"
			style="margin-left:20px" class="form-Control" />
		<br />
	</div>
	<div class="erroracount">
			<sf:errors path="comment" />
		</div>
		<br/>
		<div class="form-group">
			<label  style="text-align: right;" class="col-sm-2 control-label">Kantier:</label>
			<span style="margin-left:20px" class="form-Control"><c:out   value="${page.kantier.kantierName}" ></c:out></span>
		</div>
		<br/>
		<div class="form-group">
		<label style="vertical-align: top; text-align: right;"  for="kantier" class="col-sm-2 control-label">Kantier:</label>
		<select id="kantier" name="kantier" class="form-control" style="width: 250px;margin-left:200px">
	        <c:forEach items="${kantiers}" var="kant"> 
	            <option value="${kant.kantierId}">${kant.kantierName}</option>
	        </c:forEach>
	    </select>   
	</div>  
	<br/>
	<div class="form-group">
			<label  style="text-align: right;" class="col-sm-2 control-label">CurrentEmployee:</label>
			<span style="margin-left:20px" class="form-Control"><c:out   value="${page.recuperoEmployee.employee.firstName}" ></c:out></span>
	</div>
	<br/>	
	<div class="form-group">
		<label style="vertical-align: top; text-align: right;"  for="recuperoEmployee" class="col-sm-2 control-label">Employee:</label>
		<select id="recuperoEmployee" name="recuperoEmployee" class="form-control" style="width: 250px;margin-left:200px">
	        <c:forEach items="${recuperot}" var="recupero"> 
	            <option value="${recupero.id}">${recupero.employee.firstName} ${recupero.employee.lastName}</option>
	        </c:forEach>
	    </select>   
	</div>  
	<br/>
	<div class="form-group">
		<label for="createDateTime" style="vertical-align: top; text-align: right;" class="col-sm-2 control-label">Created Date:</label><input
			 class="date" name="createDateTime" type="text" style="margin-left:20px"  value="<fmt:formatDate value="${page.createDateTime}" pattern="dd/MM/yyyy" />"/>
	</div>
	<div class="form-group">
		<label for="bonous" style="text-align: right;" class="col-sm-2 control-label">Bonous:</label>
		<input  id="bonous" type="text" name="bonous" value="${page.bonous}"
			style="margin-left:20px" class="form-Control" />
		<br />
	</div>
	<div class="erroracount">
			<sf:errors path="bonous" />
	</div>
	<br/>

  <br/>
	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 200px">
	<a href="<c:url value="/allpagat" />" class="btn btn-default">Cancel</a>
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
			});

</script> 

