<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<script type="text/javascript">
	$(document).ready(function (){
		$(j_username).focus();
	});
</script>

	<dir class="container">
		<form class="form-horizontal" name='f'
			action='${pageContext.request.contextPath}/j_spring_security_check'
			method='POST'>
			<fieldset>

				<!-- Form Name -->
				<legend>Login</legend>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="j_username">Username</label>
					<div class="col-md-4">
						<input id="j_username" name="j_username"
							placeholder="Enter Username" class="form-control input-md"
							type="text">

					</div>
				</div>


				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="j_password">Password</label>
					<div class="col-md-4">
						<input id="j_password" name="j_password"
							placeholder="Enter Password" class="form-control input-md"
							type="password">

					</div>
				</div>


				<!-- Remember Me Checkbox-->
				<div class="form-group">
					<label class="col-md-4 control-label" >Remember Me</label>
					<div class="col-md-4">
						<input id="_spring_security_remember_me" name="_spring_security_remember_me"
							class="form-control input-md" checked="checked"
							type="checkbox">

					</div>
				</div>


				<!-- Error Message showing div-->
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<c:if test="${param.error != null}">
						<div class="col-md-4 alert-danger">Incorrect Username or
							Password is provided.</div>
					</c:if>
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submit"></label>
					<div class="col-md-4">
						<button id="submit" name="submit" class="btn btn-primary">Login</button>
					</div>
				</div>

				<!-- Create New Account -->
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<p class="col-md-4">
						<a href="<c:url value='/newaccount'></c:url>">Create New
							Account</a>
					</p>
				</div>

			</fieldset>
		</form>
	</dir>
