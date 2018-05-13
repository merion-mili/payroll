<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<body>
	<div class="navbar-wrapper">
		<div class="container">

			<nav class="navbar navbar-inverse navbar-static-top"
				role="navigation">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Pagat</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">

						<ul class="nav navbar-nav">
						<%-- 	<li><a href="<c:url value="/"/>">Home</a></li> --%>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">Employees <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="<c:url value="/employees"/>">All Employees</a></li>
								<li><a href="<c:url value="/allempenabled"/>">Enable/Disable Employees</a></li>
								<li><a href="<c:url value="/searchEnabledEmployees"/>">Search Enabled Employees</a></li>
								<li><a href="<c:url value="/allempbanks"/>">Employee Banks</a></li>
								<li><a href="<c:url value="/allempjobs"/>">Employee Jobs</a></li>
									<li><a href="<c:url value="/allempsalaries"/>">Employee Salaries</a></li>
								<li><a href="<c:url value="/jobs"/>">Jobs</a>
								<li><a href="<c:url value="/kantiere"/>">Kantiere</a></li>
							</ul>
							</li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">Pagat <span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="<c:url value="/allpagat"/>">Permbledhje e List Prezencave</a></li>
										<li><a href="<c:url value="/searchRaport"/>">Listprezencat Mujore</a></li>
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<li><a href="<c:url value="/searchRaportEmployee"/>">ListPrezencat Mujore per Employee</a></li>
										</sec:authorize>
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<li><a href="<c:url value="/searchpresenceperEmployee"/>">Gjenero Excel Prezencat Mujore per Employee</a></li>
										</sec:authorize>
										<sec:authorize access="hasRole('ROLE_ADMIN')">
											<li><a href="<c:url value="/searchWorkingsDays"/>">Ditet e Punes per Muaj</a></li>
											<li><a href="<c:url value="/searchWorkingsDaysForExcel"/>">Generate Excel for Working Days</a></li>
											<li><a href="<c:url value="/search"/>">Kerko Pagen</a></li>
											<li><a href="<c:url value="/searchMonthSalaryExcel"/>">Gjenero Pagen Ne Excel</a></li>
											<li><a href="<c:url value="/searchemployeeregister"/>">Gjenero regjistrin</a></li>
										</sec:authorize>
										<li><a href="<c:url value="/recuperot"/>">Recuperot</a></li>
									</ul></li>
							
							<li class="dropdown">
									<a href="#" class="dropdown-toggle"
									data-toggle="dropdown">Pagesat <span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="<c:url value="/banks"/>">Bankat</a></li>
										<li><a href="<c:url value="/aktivpasivet"/>">Aktiv Pasiv</a></li>
										<li><a href="<c:url value="/pagesat"/>">Pagesat</a></li>
									</ul>
							</li>
							<li class="dropdown">
									<a href="#" class="dropdown-toggle"
									data-toggle="dropdown">Upload <span class="caret"></span></a>
									<ul class="dropdown-menu" role="menu">
										<li><a href="<c:url value="/uploadFile"/>">Upload Pagat</a></li>
										<li><a href="<c:url value="/uploadpagesen"/>">Upload Pagesen</a></li>
										<li><a href="<c:url value="/uploadEmployeeJob"/>">Upload Job To Employee</a></li>
										<li><a href="<c:url value="/uploadSalary"/>">Upload Employees Salaries</a></li>
										<li><a href="<c:url value="/uploadEmployeeBank"/>">Upload Bank To Employee</a></li>
										<li><a href="<c:url value="/uploadEmployeeEnabled"/>">Upload Enbale/Disable Employee</a></li>
										<li><a href="<c:url value="/uploadEmployee"/>">Upload Employee</a></li>
									</ul>
							</li>
							<!-- <li><a href="#contact">Contact</a></li> -->
						</ul>

<!-- 
						<ul class="nav navbar-nav">
							
							
						
						</ul> -->

						<ul class="nav navbar-nav pull-right">
							<c:if test="${pageContext.request.userPrincipal.name !=null}">
								<li><a>Welcome:
										${pageContext.request.userPrincipal.name}</a></li>
								<li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
									<li><a href="<c:url value='/admin'/>">Users</a></li>
								</sec:authorize>
							</c:if>
							<c:if test="${pageContext.request.userPrincipal.name == null}">
								<li><a href="<c:url value="/login" />">Login</a></li>
								<li><a href="<c:url value="/newaccount" />">Register</a></li>
							</c:if>

						</ul>

					</div>
				</div>
			</nav>

		</div>
	</div>