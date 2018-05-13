<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
	<sf:form action="${pageContext.request.contextPath}/getRaportEmployee"
			method="get" commandName="paga" cssStyle="form-horizontal">

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
	
	<div class="form-group">
		<label style="vertical-align: top; text-align: right;"  for="js-example" class="col-sm-2 control-label">Employee:</label>
		<select  id="js-example" name="recuperoEmployee"  class="js-example-basic-single" style="width: 250px; margin-left: 250px">
	        <c:forEach items="${recuperot}" var="recupero"> 
	            <option value="${recupero.id}">${recupero.employee.firstName}</option>
	        </c:forEach>
	    </select>   
	</div>   
	
	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 200px">
	<a href="<c:url value="/employees" />" class="btn btn-default">Cancel</a>
</sf:form>
<script>
	$(document)
			.ready(
					function() {
						
			 $(".js-example-basic-single").select2({ width: 'resolve' });
					
	});
</script>

