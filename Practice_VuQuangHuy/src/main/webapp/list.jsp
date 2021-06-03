<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>Employee</title>
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>Employee List</h2>
    </div>
</div>


    <div >


        <input type="button" value="Add Employee"
               onclick="window.location.href='employee.jsp'; return false;"
               class="add-employee-button"
        />

        <table>

            <tr>
                <th>STT</th>
                <th>Full Name</th>
                <th>DOB</th>
                <th>Address</th>
                <th>Position</th>
                <th>Department</th>
            </tr>

            <c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">

                <tr>
                    <td> ${tempEmployee.id} </td>
                    <td> ${tempEmployee.fullname} </td>
                    <td> ${tempEmployee.dob} </td>
                    <td> ${tempEmployee.address} </td>
                    <td> ${tempEmployee.position} </td>
                    <td> ${tempEmployee.department} </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</body>


</html>








