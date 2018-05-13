<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<h1>Edit Knatier</h1>
<spring:url value="/updatekantier/${kantier.kantierId}" var="saveK" />
<sf:form action="${saveK}" method="POST" modelAttribute="kantier">
	<sf:hidden path="kantierId" />

	<div class="form-group">
		<label for="kantierName"
			style="vertical-align: top; text-align: right;">Kantieri:</label>
		<sf:input path="kantierName" id="kantierName" cssClass="form-Control"></sf:input>
	</div>
	<br />
	<div class="form-group">
		<label for="simbol" style="vertical-align: top; text-align: right;">Simbol:</label>
		<sf:input path="simbol" id="simbol" cssClass="form-Control"></sf:input>
	</div>
	<br />
	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 75px">
	<a href='<c:url value="/kantiere" />' class="btn btn-default">Cancel</a>

</sf:form>

