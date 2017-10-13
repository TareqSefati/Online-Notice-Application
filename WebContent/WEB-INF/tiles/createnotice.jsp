<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	
	<div class="container">
		<sf:form class="form-horizontal" method="post"
			action="${pageContext.request.contextPath}/docreate" commandName="notice">
			<fieldset>

				<!-- Form Name -->
				<legend>Create Notice</legend>

				<sf:input path="id" name="id" type="hidden"/>
					
				<!-- Textarea -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="text">Notice</label>
					<div class="col-md-4">
						<sf:textarea class="form-control" id="text" path="text" name="text"></sf:textarea>
						<sf:errors path="text" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submit"></label>
					<div class="col-md-4">
						<button id="submit" name="submit" class="btn btn-primary">Save
							Notice</button>
					</div>
				</div>
				
				<c:if test="${notice.id != 0}">
				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submit"></label>
					<div class="col-md-4">
						<button id="delete" name="delete" class="btn btn-primary">Delete
							Notice</button>
					</div>
				</div>
				</c:if>

			</fieldset>
		</sf:form>
	</div>
