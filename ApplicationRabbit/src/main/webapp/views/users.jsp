<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>List of Users</title>
<body>
	<h2>List of users</h2>
	<c:choose>
		<c:when test="${users != null}">
			<table cellpadding="6" cellspacing="6">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Birthday</th>
					    <th>Hobby</th>
					    <th>Hours</th>
					    <th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="t" items="${users}">
						<tr>
							<td>${t.id}</td>
							<td>${t.name}</td>
							<td>${t.birthday}</td>
							<td>${t.hobby}</td>
							<td>${t.hours}</td>
			                <td>${t.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
        No User found in the DB!
        </c:otherwise>
	</c:choose>
</body>
</html>