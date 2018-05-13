<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<h3>Please fill in your information below:</h3>
<br />
<sf:form action="${pageContext.request.contextPath}/createpage"
	method="post" commandName="page" cssStyle="form-horizontal">
	<input type="hidden" name="id" value="${page.id}" />

	<div class="form-group">
		<label style="vertical-align: top; text-align: right;"  for="js-example" class="col-sm-2 control-label">Employee:</label>
		<select  id="js-example" name="recuperoEmployee"  class="js-example-basic-single" style="width: 250px; margin-left: 250px">
	        <c:forEach items="${recuperot}" var="recupero"> 
	            <option value="${recupero.id}">${recupero.employee.firstName}</option>
	        </c:forEach>
	    </select>   
	</div>   
	<br />
		
	<div class="form-group">
		<label style="vertical-align: top; text-align: right;" for="kantier"
			class="col-sm-2 control-label">Kantier:</label> <select id="kantier"
			name="kantier" class="form-control"
			style="width: 250px; margin-left: 196px">
			<c:forEach items="${kantiers}" var="kant">
				<option value="${kant.kantierId}">${kant.kantierName}</option>
			</c:forEach>
		</select>
	</div>
	<br />
	<div class="form-group">
		<label for="date" style="vertical-align: top; text-align: right;"
			class="col-sm-2 control-label">Date:</label><input class="date"
			name="date" type="text" style="margin-left: 20px" />
	</div>
	<br />
	<div class="form-group">
		<label for="oretepunuara" style="text-align: right;"
			class="col-sm-2 control-label">Oret E Punuara:</label>
		<sf:input path="oretepunuara" id="oretepunuara" type="text"
			name="oretepunuara" cssStyle="margin-left:20px"
			cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="oretepunuara" />
		</div>
	</div>
	<div class="form-group">
		<label for="oretshtese" style="text-align: right;"
			class="col-sm-2 control-label">Oret Shtese:</label>
		<sf:input path="oretshtese" id="oretshtese" type="text"
			name="oretshtese" cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="oretshtese" />
		</div>
	</div>

	<div class="form-group">
		<label for="recupero" style="text-align: right;"
			class="col-sm-2 control-label">Recuperot:</label>
		<sf:input path="recupero" id="recupero" type="text" name="recupero"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="recupero" />
		</div>
	</div>
	<br/>

	<div class="form-group">
		<label for="comment" style="text-align: right;"
			class="col-sm-2 control-label">Comments:</label>
		<sf:input path="comment" id="reference" type="text" name="comment"
			cssStyle="margin-left:20px" cssClass="form-Control" />
	<div class="erroracount">
			<sf:errors path="comment" />
	</div>
	<br />
	</div>
	<br />
	
	<div class="form-group">
		<label for="lejeDitore" style="text-align: right;"
			class="col-sm-2 control-label">Lejet Ditore:</label>
		<sf:input path="lejeDitore" id="lejeDitore" type="text"
			name="lejeDitore" cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="lejeDitore" />
		</div>
	</div>
	<br/>
	
	<div class="form-group">
		<label for="raportet" style="text-align: right;"
			class="col-sm-2 control-label">Raportet :</label>
		<sf:input path="raportet" id="raportet" type="text" name="raportet"
			cssStyle="margin-left:20px" cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="raportet" />
		</div>
	</div>
	<br />
	<div class="form-group">
		<label for="lejeVjetore" style="text-align: right;"
			class="col-sm-2 control-label">Leje vjetore:</label>
		<sf:input path="lejeVjetore" id="lejeVjetore" type="text"
			name="lejeVjetore" cssStyle="margin-left:20px"
			cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="lejeVjetore" />
		</div>
	</div>
	<br />
		<div class="form-group">
		<label for="festa" style="text-align: right;"
			class="col-sm-2 control-label">Festa:</label>
		<sf:input path="festa" id="festa" type="text"
			name="festa" cssStyle="margin-left:20px"
			cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="festa" />
		</div>
	</div>
	<br />
	
	<div class="form-group">
		<label for="comment" style="text-align: right;"
			class="col-sm-2 control-label">Comment:</label>
		<sf:input path="comment" id="comment" type="text"
			name="comment" cssStyle="margin-left:20px"
			cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="comment" />
		</div>
	</div>
	<br />

	<div class="form-group">
		<label for="bonous" style="text-align: right;"
			class="col-sm-2 control-label">Bonous:</label>
		<sf:input path="bonous" id="bonous" type="text"
			name="bonous" cssStyle="margin-left:20px"
			cssClass="form-Control" />
		<br />
		<div class="erroracount">
			<sf:errors path="bonous" />
		</div>
	</div>
	<br />

	
	<input type="submit" value="submit" class="btn btn-default"
		style="margin-left: 200px">
	<a href="<c:url value="/allpagat" />" class="btn btn-default">Cancel</a>
</sf:form>
<script>
	$(document)
			.ready(
					function() {
						var date_input = $('.date'); //our date input has the name "date"
						var container = $('.bootstrap-iso form').length > 0 ? $(
								'.bootstrap-iso form').parent()
								: "body";
						var options = {
							format : 'dd/mm/yyyy',
							container : container,
							todayHighlight : true,
							autoclose : true,
			};
						date_input.datepicker(options);

			 $(".js-example-basic-single").select2({ width: 'resolve' });
					
	});
</script>


