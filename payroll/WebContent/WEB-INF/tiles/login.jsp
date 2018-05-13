<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
	$(document).ready(function(){
		document.f.username.focus();

		})
</script>


		<div class="login-card">
			<div id="login-form">
				<c:if test="${not empty msg}">
					<div class="msg">${msg}</div>
				</c:if>

				<form name='f' action="<c:url value='/j_spring_security_check'/>"
					method="POST">

					<c:if test="${param.error != null}">
						<div class="error" style="color: #ff0000;">${error}</div>
					</c:if>

					<div class="input-group input-sm">
						<label class="input-group-addon" for="username"><i
							class="fa fa-user"></i></label> 
						<input type="text" id="username"
							name="username" class="form-control" />
					</div>
					<div class="input-group input-sm">
						<label class="input-group-addon" for="username"><i
							class="fa fa-lock"></i></label> 
						<input type="password" id="password"
							name="password" class="form-control" />
					</div>
					<div class="form-group login-group-checkbox">
						<div class="checkbox">
							<label><input checked="checked" type="checkbox"
							id="checkbox" name="remember-me" />Remember me</label>
						</div>
					</div>
					
					 <div class="form-actions">
                                <input type="submit" name="submit" style="width: 250px;"
                                    class="btn btn-block btn-primary btn-default" value="Log in">
                            </div>
						<%-- <a
						href="<c:url value="/newaccount" />"
						
						class="btn btn-primary  btn-default">Sing In</a> --%> <input
						type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

				</form>


			</div>
		</div>

