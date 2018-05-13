<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

            <h1>Edit Year</h1>
		<spring:url value="/saveYear" var="saveyearURL"/>
        <sf:form action="${saveyearURL}"
                   method="POST" modelAttribute="yearForm" >
            <sf:hidden path="yearId" />       
            
            <div class="form-group">
			<label for="year" style="vertical-align: top;text-align: right;">Year:</label>
			<sf:input path="year" id="year" cssClass="form-Control" ></sf:input>
			</div>
			<br/> 
            <input type="submit" value="submit" class="btn btn-default" style="margin-left: 75px">
             <a href='<c:url value="/years" />' class="btn btn-default">Cancel</a>
            
           </sf:form>

