<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
<sf:form id="details"
	action="createaccount" method="post"
	commandName="user">
<input type="hidden" name="id"  value="${user.id}"/>   
	<div class="form-group">
		<label for="username" style="text-align: right;">Username:</label>
		<sf:input path="username" id="username" type="text" name="username"
			cssStyle="margin-left:90px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="username" />
		</div>
	</div>
	<div class="form-group">
		<label for="name" style="text-align: right;">Name:</label>
		<sf:input path="name" id="name" type="text" name="name"
			cssStyle="margin-left:120px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="name" />
		</div>
	</div>

	<div class="form-group">
		<label for="email" style="text-align: right;">Email:</label>
		<sf:input path="email" id="email" type="text" name="email"
			cssStyle="margin-left:120px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="email" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" style="text-align: right">Password:</label>
		<sf:password path="password" id="password" name="password"
			cssStyle="margin-left:90px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="password" />
		</div>
	</div>
	<div class="form-group">
		<label for="confirmpass" style="text-align: right;">Confirm
			Password:</label> <input name="confirmpass" id="confirmpass" type="text"
			style="margin-left: 30px" class="form-Control" />
		<div class="matchpass" id="matchpass"></div>
	</div>
	<div class="form-group">
		<label for="authority">Role</label> <label class="checkbox-inline"><sf:radiobutton
				path="authority" id="authority" value="ROLE_USER"
				style="margin-left: 110px" class="form-Control" />User</label> <label
			class="checkbox-inline"><sf:radiobutton path="authority"
				id="authority" value="ROLE_ADMIN" style="margin-left: 10px"
				class="form-Control" />Admin</label>
		<div class="erroracount">
			<sf:errors path="authority" />
		</div>
	</div>
		<div class="form-group">
		<label for="enabled">Enabled</label> <label class="checkbox-inline"><sf:radiobutton
				path="enabled" id="enabled" value="false"
				style="margin-left: 85px" class="form-Control" />Inactive</label> <label
			class="checkbox-inline"><sf:radiobutton path="enabled"
				id="enabled" value="true" style="margin-left: 2px"
				class="form-Control" />Active</label>
		<div class="erroracount">
			<sf:errors path="enabled" />
		</div>
	</div>
	<br />
	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 160px">
	<a href="<c:url value="/" />" class="btn btn-default">Cancel</a>
</sf:form>
