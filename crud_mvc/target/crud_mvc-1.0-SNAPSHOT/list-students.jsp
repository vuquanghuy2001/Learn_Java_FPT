<%--
  Created by IntelliJ IDEA.
  User: thidk
  Date: 29/05/2021
  Time: 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>Student Tracker App</title>

<%--    <link type="text/css" rel="stylesheet" href="css/style.css">--%>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>CodeLean Academy</h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <!-- put new button: Add Student -->

        <input type="button" value="Add Student"
               onclick="window.location.href='add-student-form.jsp'; return false;"
               class="add-student-button"
        />

        <table>

            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Action</th>
            </tr>

            <c:forEach var="tempStudent" items="${STUDENT_LIST}">

                <!-- set up a link for each student -->
                <c:url var="tempLink" value="StudentControllerServlet">
                    <c:param name="command" value="LOAD"/>
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>

                <!-- set up a link to delete a student -->
                <c:url var="deleteLink" value="StudentControllerServlet">
                    <c:param name="command" value="DELETE"/>
                    <c:param name="studentId" value="${tempStudent.id}"/>
                </c:url>

                <tr>
                    <td> ${tempStudent.firstname} </td>
                    <td> ${tempStudent.lastname} </td>
                    <td> ${tempStudent.email} </td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>
</body>


</html>








