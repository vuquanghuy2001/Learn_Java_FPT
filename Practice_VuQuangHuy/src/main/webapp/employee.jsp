<!DOCTYPE html>
<html>

<head>
    <title>Add Employee</title>
</head>

<body>

<div id="container">
    <h3>Add Employee</h3>

    <form action="EmployeeControllerServlet" method="GET">

        <input type="hidden" name="command" value="ADD" />

        <table>
            <tbody>
            <tr>
                <td><label>Full Name:</label></td>
                <td><input type="text" name="fullname" /></td>
            </tr>

            <tr>
                <td><label>DOB:</label></td>
                <td><input type="text" name="dob" /></td>
            </tr>

            <tr>
                <td><label>Address:</label></td>
                <td><input type="text" name="address" /></td>
            </tr>

            <tr>
                <td><label>Position:</label></td>
                <td><input type="text" name="position" /></td>
            </tr>

            <tr>
                <td><label>Department:</label></td>
                <td><input type="text" name="department" /></td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <p>
        <a href="employee.jsp">Reset</a>
    </p>
</div>
</body>

</html>











