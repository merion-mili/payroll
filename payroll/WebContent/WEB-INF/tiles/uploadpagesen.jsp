<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

            <h1 style="margin-left:70px" >Upload Pagesen</h1>
		<spring:url value="/doUploadPagesen" var="pagesenUploadUrl"/>
        <sf:form action="${pagesenUploadUrl}"
                   method="POST" modelAttribute="pagesenUpload" enctype="multipart/form-data">
                 
            
            <div class="form-group">
				<label for="files" style="text-align: right;"class="col-sm-2 control-label">Browse files:</label>
				<sf:input  id="files" path="files" type="file" cssClass="form-Control"  multiple="multiple"></sf:input>
			</div>
			<br/> 
			<div class="erroracount">
				<sf:errors path="files" />
			</div>
			<br/>
            <input style="margin-left:180px"  type="submit" value="Upload" class="btn btn-default">
            <a href='<c:url value="/employees" />' class="btn btn-default">Cancel</a>
            
           </sf:form>

