<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


	<div class="container">
		<fmt:message key="UnmatchedPassword.user.password" />
		<sf:form class="form-horizontal" method="post"
			action="${pageContext.request.contextPath}/createaccount"
			commandName="user">
			<fieldset>

				<!-- Form Name -->
				<legend>Create User</legend>

				<!-- Username input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="username">Username</label>
					<div class="col-md-6">
						<sf:input id="username" path="username" name="username"
							type="text" placeholder="Enter Username"
							class="form-control input-md" />
						<sf:errors path="username" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				
				<!-- Full name input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">Full Name</label>
					<div class="col-md-6">
						<sf:input id="name" path="name" name="name"
							type="text" placeholder="Enter Full name"
							class="form-control input-md" />
						<sf:errors path="name" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Email input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Email</label>
					<div class="col-md-6">
						<sf:input id="email" path="email" name="email" type="text"
							placeholder="Enter Email" class="form-control input-md" />
						<sf:errors path="email" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Password</label>
					<div class="col-md-6">
						<sf:input id="password" path="password" name="password"
							type="password" placeholder="Enter Password"
							class="form-control input-md" />
						<sf:errors path="password" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Confirm Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Confirm
						Password</label>
					<div class="col-md-6">
						<input id="confirmpassword" name="confirmpassword" type="password"
							placeholder="Enter Password Again" class="form-control input-md" />
						<sf:errors path="password" cssClass="alert-danger"></sf:errors>
						<div id="passwordmissmatch"></div>
					</div>
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submit"></label>
					<div class="col-md-4">
						<button id="submit" name="submit" class="btn btn-primary">Create
							User</button>
					</div>
				</div>

			</fieldset>
		</sf:form>
	</div>
	<script>
		var password1 = document.getElementById('password');
		var password2 = document.getElementById('confirmpassword');

		var checkPasswordValidity = function() {
			if (password1.value != password2.value) {
				password1.setCustomValidity('Passwords must match.');
			} else {
				password1.setCustomValidity('');
			}
		};

		password1.addEventListener('change', checkPasswordValidity, false);
		password2.addEventListener('change', checkPasswordValidity, false);

		var form = document.getElementById('user');
		form.addEventListener('submit', function() {
			checkPasswordValidity();
			if (!this.checkValidity()) {
				event.preventDefault();
				//Implement you own means of displaying error messages to the user here.
				password1.focus();
			}
		}, false);
	</script>
