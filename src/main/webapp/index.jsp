<%@ page import="model.Task" %>
<%@ page import="model.DbStore" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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

    <script>
        function validate() {
            var res = true;
            if ($('#newTask').val() === '') {
                alert('Заполните поле: Новая запись');
                res = false;
            }
            return res;
        }
    </script>

    <script>
        function change() {
            var checkbox = document.getElementById('res');
            /*if (checkbox.checked != true) {
                alert("Что то произошло")
            }*/
            alert("Что то произошло")
        }
        /*$(document).ready(function ()
        {
            $('#res').change(function () {
                alert('Снова что то произошло');
            })
        });*/
    </script>

    <title>Список дел!</title>
</head>
<body>
<div class="container">
    <jsp:include page="topPanel.jsp"/>
    <div class="row">
        <div class="card" style="width:50%;">
            <div class="card-header">
                Добавление записи
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/index.do" method="post">
                    <div class="form-group">
                        <label>Новая запись</label>
                        <input type="text" class="form-control" name="description" id="newTask">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Добавить задачу</button>
                </form>
            </div>

        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Список всех задач
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Задача</th>
                        <th scope="col">Дата создания</th>
                        <th scope="col">Сделано ли?</th>
                        <th scope="col">Автор</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% Collection<Task> list = DbStore.instOf().allTasks();
                        for (Task task : list) { %>
                    <tr>
                        <td>
                            <%=task.getDescription()%>
                        </td>
                        <td>
                            <%=task.getCreated()%>
                        </td>
                        <td>
                            <input type="checkbox" name="enabled" onclick="change()" id="res">
                            <%=task.getDone()%> <%--ToDO убрать--%>
                        </td>
                        <td>
                            <%=task.getUser().getName()%>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
