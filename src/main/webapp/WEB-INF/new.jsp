<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ page isErrorPage="true" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="ISO-8859-1">
                    <link rel="stylesheet" type="text/css" href="/css/style.css">

                    <link rel="stylesheet" type="text/css" href="/css/new.css">
                    <title>Exam</title>

                </head>

                <body>
                    <section class="container">
                        <div class="container__title">
                            <h1 class="container__title">Create a new course</h1>
                        </div>
                        <form:form class="container__form" action="/courses/new" modelAttribute="course">
                            <input type="hidden" name="_method" value="post">
                            <form:errors class="error" path="name" />
                            <div class="form__field">
                                <form:label path="name">Name:</form:label>
                                <form:input path="name" type="text"></form:input>
                            </div>

                            <form:errors class="error" path="instructor" />
                            <div class="form__field">
                                <form:label path="instructor">instructor:</form:label>
                                <form:input path="instructor" type="text"></form:input>
                            </div>

                            <form:errors class="error" path="capacity" />
                            <div class="form__field">
                                <form:label path="capacity">Capacity:</form:label>
                                <form:input path="capacity" type="number" min="1"></form:input>
                            </div>

                            <div class="form__submit">
                                <input class="button" type="submit" value="Create">
                            </div>
                        </form:form>
                    </section>
                </body>

                </html>