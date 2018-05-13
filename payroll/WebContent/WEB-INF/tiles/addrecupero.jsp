<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
	<sf:form action="${pageContext.request.contextPath}/createrecupero"
			method="post" commandName="recupero" cssStyle="form-horizontal">
<input type="hidden" name="id"  value="${recupero.id}"/>   
	<div class="form-group">
		<label for="gjendjerecupero" style="text-align: right;"class="col-sm-2 control-label">Recupero:</label>
		<sf:input path="gjendjerecupero" id="gjendjerecupero" type="text" name="gjendjerecupero"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="gjendjerecupero" />
		</div>
	</div>
	<br/>
	<div class="form-group">
		<label style="vertical-align: top; text-align: right;"  for="employee" class="col-sm-2 control-label">Employee:</label>
		<select id="employee" name="employee" class="form-control" style="width: 250px;margin-left:200px">
	        <c:forEach items="${employees}" var="emp"> 
	            <option value="${emp.empId}">${emp.firstName}</option>
	        </c:forEach>
	    </select>   
	</div>  
	<br/>
	
	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 200px">
	<a href="<c:url value="/recuperot" />" class="btn btn-default">Cancel</a>
</sf:form>

