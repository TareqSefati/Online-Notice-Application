<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


	<div class="container">
		<sf:form class="form-horizontal" method="post"
			commandName="message">
			<fieldset>
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
				<input type="hidden" name="_eventId" value="send" />

				<!-- Form Name -->
				<legend>Send Message</legend>

				<!-- Name input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="name">Name</label>
					<div class="col-md-6">
						<sf:input id="name" path="name" name="name" value="${fromName}"
							type="text" placeholder="Enter Your Name"
							class="form-control input-md" />
						<sf:errors path="name" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				
				<!-- Email input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="email">Email</label>
					<div class="col-md-6">
						<sf:input id="email" path="email" name="email" value="${fromEmail}"
							type="email" placeholder="Enter Email"
							class="form-control input-md" />
						<sf:errors path="email" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				
				<!-- Subject input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="subject">Subject</label>
					<div class="col-md-6">
						<sf:input id="subject" path="subject" name="subject"
							type="text" placeholder="Enter Subject"
							class="form-control input-md" />
						<sf:errors path="subject" cssClass="alert-danger"></sf:errors>
					</div>
				</div>
				
				<!-- Content input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="content">Content</label>
					<div class="col-md-6">
						<sf:textarea id="content" path="content" name="content"
							type="text" placeholder="Enter Content"
							class="form-control input-md" />
						<sf:errors path="content" cssClass="alert-danger"></sf:errors>
					</div>
				</div>

				
				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="submit"></label>
					<div class="col-md-4">
						<button id="submit" name="submit" class="btn btn-primary">Send</button>
					</div>
				</div>

			</fieldset>
		</sf:form>
	</div>
	