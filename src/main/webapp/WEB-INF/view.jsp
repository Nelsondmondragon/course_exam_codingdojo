<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
			<%@ page isErrorPage="true" %>
				<!DOCTYPE html>
				<html>


				<head>
					<meta charset="ISO-8859-1">
					<link rel="stylesheet" type="text/css" href="/css/style.css">

					<link rel="stylesheet" type="text/css" href="/css/view.css">
					<title>Exam</title>

				</head>

				<body>
					<section class="container">
						<div class="container__title">
							<h1 class="container__title">${course.getName()}</h1>
						</div>
						<section class="container__form">
							<div class="form__field">
								<label>Instructor:</label>
								<input type="text" disabled value="${course.getInstructor()}"></input>
							</div>

							<div class="form__field">
								<label>Sign ups</label>
								<input disabled type=" text" disbaled value="${course.getUsersJoined().size()}"></input>
							</div>




							<div class="ninja">
								<a href="">Sign Up Data ASC</a>
								<a href="">Sign Up Data DESC</a>
							</div>


							<table class="container__table">
								<thead>
									<tr>
										<th>Name</th>
										<th>Sign Up Date</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${course.getUsersJoined()}" var="student">
										<tr>
											<th>
												<c:out value="${student.getName()}"></c:out>
											</th>
											<th>
												<c:out
													value="${dateRegister.findAllByIdUserAndIdCourse(student.getId(),course.getId()).getCreatedAt()}">
												</c:out>
											</th>
											<th>
												<c:if test="${userLoginId==student.getId()}">
													<a href="/courses/deleteuser/${course.getId()}">Remove</a>
												</c:if>
											</th>
										</tr>

									</c:forEach>
								</tbody>
							</table>


							<div class="form__button">
								<form action="/courses/${course.getId()}/edit">
									<input class="button" type="submit" value="Edit">
								</form>

								<form action="/course/${course.getId()}" method="POST">
									<input type="hidden" name="_method" value="DELETE">
									<input class="button" type="submit" value="Delete">
								</form>


							</div>

						</section>

					</section>
				</body>

				</html>