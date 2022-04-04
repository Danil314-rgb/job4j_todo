<%@ page import="model.Task" %>
<%@ page import="java.util.Collection" %>
<%@ page import="model.DbStore" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Список дел!</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    Task task = Task.of("", LocalDateTime.now(), false, User.of(0 ,"lol", "lol.@mail.ru", "lol"));
    if (id != null) {
        task = DbStore.instOf().findByTaskId(Integer.parseInt(id));
    }
%>
<div class="container">
    <jsp:include page="/topPanel.jsp"/>
    <div class="row">
        <div class="card" style="width:50%;">
            <div class="card-header">
                Изменение состояния задачи
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/done.do?id=<%=task.getId()%>" method="post">
                    <div class="form-group">
                        <label>Задача</label>
                        <input type="text" class="form-control" name="description" value="<%=task.getDescription()%>" id="newTask" readonly>
                    </div>
                    <div class="form-group">
                        <label>Состояние</label>
                        <select class="form-control" name="done">
                            <option>true</option>
                            <option>false</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
                </form>
            </div>

        </div>
    </div>
</div>
</body>
