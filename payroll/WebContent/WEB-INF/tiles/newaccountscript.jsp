<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

function onload() {
		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpass").keyup(checkPasswordsMatch);
		$("#details").submit(canSubmit);
	}


function canSubmit(){
		var password= $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if(password != confirmpass){
			alert("Password didnt match");
			return false;
			}
		else{

			return true;
			}
		
		}


function checkPasswordsMatch(){

		var password= $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if(password.length < 3 || confirmpass.length  >3){
			if(password == confirmpass){
				$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password'/>");
				$("#matchpass").addClass("valid");
				$("#matchpass").removeClass("erroracount");
				} else{

					$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password'/>");
					$("#matchpass").addClass("erroracount");
					$("#matchpass").removeClass("valid");
					}
			}
	}

$(document).ready(onload);
</script>