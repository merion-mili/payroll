<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

            <h1>Add/Edit Job</h1>
		<spring:url value="/save" var="saveURL"/>
        <sf:form action="${saveURL}"
                   method="POST" modelAttribute="jobForm" >
            <sf:hidden path="jobId" />       
            
            <div class="form-group">
			<label for="jobDescription" style="vertical-align: top;text-align: right;">Pershkrimi:</label>
			<sf:input path="jobDescription" id="jobDescription" cssClass="form-Control" ></sf:input>
			</div>
			<br/> 
            <input type="submit" value="submit" class="btn btn-default" style="margin-left: 75px">
             <a href='<c:url value="/jobs" />' class="btn btn-default">Cancel</a>
            
           </sf:form>

