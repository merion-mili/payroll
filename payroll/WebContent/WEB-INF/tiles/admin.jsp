<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<div class="page-header">
			<h1>All Users</h1>
		</div>

		<table class="table table-striped table-hover">
			<thead>
				<tr class="bg-success">
					<th>Username</th>
					<th >Name</th>
					<th >Email</th>
					<th>Role</th>
					<th>Enabled</th>
					<th>Option</th>

				</tr>
			</thead>
			<c:forEach var="user" items="${users}">
				<tr>
					<td><c:out value="${user.username}"></c:out></td>
					<td><c:out value="${user.name}"></c:out></td>
					<td><c:out value="${user.email}"></c:out></td>
					<td><c:out value="${user.authority}"></c:out></td>
					<td><c:out value="${user.enabled}"></c:out></td>
					<%-- <td><a
				href="deleteUser?id=${user.id}" 
				onclick="return confirm('Are you sure?')"><span
					id="delete" class="glyphicon glyphicon-remove"></span></a><a
				href="editUser?id=${user.id}"><span
					class="glyphicon glyphicon-pencil"></span></a></td> --%>
					
					<td><a
				href="${pageContext.request.contextPath}/deleteUser/${user.id}" 
				onclick="return confirm('Are you sure to delete this?')"><span
					id="delete" class="glyphicon glyphicon-remove"></span></a><a
				href="${pageContext.request.contextPath}/editUser/${user.id}"><span
					class="glyphicon glyphicon-pencil"></span></a></td>
				</tr>

			</c:forEach>
		</table>
		<a href="${pageContext.request.contextPath}/newaccount"
	class="btn btn-primary">Add User</a>


