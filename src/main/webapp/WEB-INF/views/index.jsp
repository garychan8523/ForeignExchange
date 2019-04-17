<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="FXM">
<title>Foreign Exchange</title>

<link href="bootstrap.min.css" rel="stylesheet">


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="dashboard.css" rel="stylesheet">
</head>
<body>

	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="loginTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">Login</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body col-md-6" style="margin: auto;">
					<sf:form action="login" method="post" modelAttribute="login_user">
						<div class="form-group">
							<sf:input type="text" class="form-control" path="userName"
								placeholder="username" required="true" />
						</div>
						<div class="form-group">
							<sf:input type="password" class="form-control" path="password"
								placeholder="password" required="true" />
						</div>
						<input type="hidden" class="form-control" name="frompage"
							value="${fromurl}">
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-sm btn-outline-dark">Reset</button>
					<button type="submit" class="btn btn-sm btn-primary">
						<strong>Login</strong>
					</button>
				</div>
				</sf:form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="signupModal" tabindex="-1" role="dialog"
		aria-labelledby="signupTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">Signup</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body col-md-6" style="margin: auto;">
					<sf:form action="signup" method="post" modelAttribute="signup_user">
						<div class="form-group">
							<sf:input type="text" class="form-control" path="userName"
								placeholder="username" pattern=".{2,}"
								title="username must contain two or more characters"
								required="true" />
						</div>
						<div class="form-group">
							<sf:input type="password" class="form-control" path="password"
								placeholder="password" pattern=".{6,}"
								title="password must cnotain six or more characters"
								required="true" />
						</div>
						<div class="form-group">
							<sf:input type="email" class="form-control" path="email"
								placeholder="email"
								pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required="true" />
						</div>
						<input type="hidden" class="form-control" name="frompage"
							value="${fromurl}">
				</div>
				<div class="modal-footer">
					<button type="reset" class="btn btn-sm btn-outline-dark">Reset</button>
					<button type="submit" class="btn btn-sm btn-primary">
						<strong>Register</strong>
					</button>
				</div>
				</sf:form>
			</div>
		</div>
	</div>

	<nav
		class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/"><strong>Foreign
				Exchange</strong></a> <input
			class="form-control form-control-dark col-sm-7 col-md-6"
			style="height: 100%; margin-right: 100%;" type="text"
			placeholder="Search" aria-label="Search">
		<!-- <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#">Sign out</a>
    </li>
  </ul> -->
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link active" href="#">
								<span data-feather="home"></span> Home <span class="sr-only">(current)</span>
						</a></li>

						<%
							if (session.getAttribute("username") == null) {
						%>
						<li class="nav-item"><a class="nav-link" href=""
							data-toggle="modal" data-target="#signupModal"> <span
								data-feather="user-plus"></span> Signup
						</a></li>
						<li class="nav-item"><a class="nav-link" href=""
							data-toggle="modal" data-target="#loginModal"> <span
								data-feather="log-in"></span> Login
						</a></li>
						<%
							} else {
						%>

						<li class="nav-item">
							<form action="login" method="post" name="logoutForm">
								<input type="hidden" class="form-control" name="frompage"
									value="${fromurl}" /> <input type="hidden" name="logout"
									value="true" /> <a class="nav-link" href="#"
									onclick="document.logoutForm.submit()"> <span
									data-feather="log-out"></span> Logout
								</a>
							</form>
						</li>

						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="file"></span> Orders
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="users"></span> Profile
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="shopping-cart"></span> Account and Balance
						</a></li>
						<!-- <li class="nav-item">
	            <a class="nav-link" href="#">
	              <span data-feather="bar-chart-2"></span>
	              Reports
	            </a>
	          </li> -->
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="layers"></span> Trade History
						</a></li>
						<%
							}
						%>

					</ul>

					<div style="display: none;">
						<h6
							class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
							<span>Saved reports</span> <a
								class="d-flex align-items-center text-muted" href="#"> <span
								data-feather="plus-circle"></span>
							</a>
						</h6>
						<ul class="nav flex-column mb-2">
							<li class="nav-item"><a class="nav-link" href="#"> <span
									data-feather="file-text"></span> Current month
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#"> <span
									data-feather="file-text"></span> Last quarter
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#"> <span
									data-feather="file-text"></span> Social engagement
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#"> <span
									data-feather="file-text"></span> Year-end sale
							</a></li>
						</ul>
					</div>

				</div>
			</nav>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3"
				style="margin-top: 10px; width: 75%;">

				<h5>
					Welcome,
					<%
					if (session.getAttribute("username") != null) {
						out.println(session.getAttribute("username"));
					} else {
						out.println("Guest");
					}
				%>
				</h5>
				<div class="btn-toolbar mb-2 mb-md-0">

					<div class="dropdown">
						<a class="btn btn-outline-secondary btn-sm dropdown-toggle"
							href="#" role="button" id="dropdownMenuLink"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span data-feather="corner-left-up"></span>USD
						</a>

						<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							<a class="dropdown-item" href="#">CAD</a> <a
								class="dropdown-item" href="#">GBP</a> <a class="dropdown-item"
								href="#">JPY</a>
						</div>
					</div>

					<div class="dropdown">
						<a class="btn btn-outline-secondary btn-sm dropdown-toggle"
							href="#" role="button" id="dropdownMenuLink"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span data-feather="corner-right-down"></span>HKD
						</a>

						<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
							<a class="dropdown-item" href="#">CAD</a> <a
								class="dropdown-item" href="#">GBP</a> <a class="dropdown-item"
								href="#">JPY</a>
						</div>
					</div>

				</div>
			</div>

			<!-- <canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas> -->
			
			<div class="table-responsive"
				style="width: 40%; display: inline-block;">
				<h6>Last Trade</h6>
				<table class="table table-striped table-sm">
					<thead>
						<tr>
							<th>Time</th>
							<th>Buy Amt.</th>
							<th>Rate</th>
							<th>Sell Amt.</th>
							<th>Buy</th>
							<th>Sell</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${tradeList}" var="entry">
							<tr>
								<td>${entry.timestamp}</td>
								<td>${entry.amount}</td>
								<td>${entry.order.rate}</td>
								<td>sell amount</td>
								<td>${entry.order.currencyBuy.currencyCode}</td>
								<td>${entry.order.currencySell.currencyCode}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>


			<div style="width: 50%; display: inline-block; margin-left: 30px;">
				<h6>Order Book</h6>
				<table class="table table-striped table-sm"
					style="width: 33%; display: inline-block;">
					<thead>
						<tr>
							<th>Value</th>
							<th>Amount</th>
							<th>Bid</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${activeBuyOrder}" var="entry">
							<tr>
								<td>times</td>
								<td>${entry.currentAmount}</td>
								<td>${entry.rate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<table class="table table-striped table-sm"
					style="width: 33%; display: inline-block;">
					<thead>
						<tr>
							<th>Ask</th>
							<th>Amount</th>
							<th>Value</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${activeSellOrder}" var="entry">
						<tr>
							<td>${entry.rate}</td>
							<td>${entry.currentAmount}</td>
							<td>times</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			</main>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.21.0/feather.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
	<script src="dashboard.js"></script>
</body>
</html>
