<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ page isErrorPage="true" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="ISO-8859-1">
                    <link rel="stylesheet" type="text/css" href="/css/style.css">
                    <link rel="stylesheet" type="text/css" href="/css/dashboard.css">
                    <title>Exam</title>

                </head>


                <body>
                    <section class="container">
                        <div class="container__title">
                            <h1 class="container__title">Welcome, ${user.getName()}</h1>

                        </div>

                        <div>
                            <h3>Course</h3>

                        </div>
                        <div class="ninja">
                            <a href="/courses">low ign Up</a>
                            <a href="/courses?order=desc">High sign Up</a>
                        </div>
                        <table class="container__table">
                            <thead>
                                <tr>
                                    <th>Course</th>
                                    <th>Instructor</th>
                                    <th>Signups</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${courses}" var="course">
                                    <tr>
                                        <th>
                                            <a href="courses/${course.getId()}">
                                                <c:out value="${course.getName()}"></c:out>
                                            </a>

                                        </th>
                                        <th>
                                            <c:out value="${course.getInstructor()}"></c:out>
                                        </th>
                                        <th>
                                            <c:out value="${course.getUsersJoined().size()}/${course.getCapacity()}">
                                            </c:out>
                                        </th>
                                        <th>
                                            <c:if test="${!course.getUsersJoined().contains(user)}">

                                                <c:if test="${course.getCapacity() > course.getUsersJoined().size()}">
                                                    <a href="courses/join/${course.getId()}">Add</a>
                                                </c:if>
                                                <c:if test="${course.getCapacity() == course.getUsersJoined().size()}">
                                                    <label for="">Full</label>
                                                </c:if>

                                            </c:if>

                                            <c:if test="${course.getUsersJoined().contains(user)}">
                                                <label for="">Already added</label>
                                            </c:if>

                                        </th>
                                    </tr>

                                </c:forEach>
                            </tbody>
                        </table>


                        <a class="container__create" href="/courses/new">
                            <input class="button" type="submit" value="Add a course">
                        </a>


                    </section>
                </body>

                </html>