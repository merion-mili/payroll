<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

		<div class="page-header">
			<h1>Create Job</h1>

			<p class="lead">Please fill in your information below:</p>
		</div>

		<sf:form action="${pageContext.request.contextPath}/docreatejob"
			method="post" commandName="job" >

		<h3>Basic Info:</h3>
 		<sf:input type="hidden" name="jobId" path="jobId" value="${job.jobId}"/>   
		<div class="form-group">
			<label for="text" style="vertical-align: top;text-align: right;">Pershkrimi:</label>
			<sf:input path="jobDescription" id="jobDescription"  cssClass="form-Control" ></sf:input>
			<br />
			<sf:errors path="jobDescription" cssStyle="error" />
		</div>
		<br/>
		<input type="submit" value="submit" class="btn btn-default" style="margin-left: 75px">
		<a href="<c:url value="/jobs" />" class="btn btn-default">Cancel</a>
	</sf:form>

