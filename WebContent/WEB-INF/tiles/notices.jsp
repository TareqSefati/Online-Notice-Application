<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="container">
		<table class="table table-striped">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Notice</th>
			</tr>

			<c:forEach var="notice" items="${notices}">
				<tr>
					<td><c:out value="${notice.id}"></c:out></td>
					<td><c:out value="${notice.user.name}"></c:out></td>
					<td><c:out value="${notice.user.email}"></c:out></td>
					<td><c:out value="${notice.text}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
	</div>