package employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDbUtil {
    private DataSource dataSource;

    public EmployeeDbUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();   // doesn't really close it ... just puts back in connection pool
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public List<Employee> getEmployees() throws Exception {

        List<Employee> employees = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // get a connection
            myConn = dataSource.getConnection();

            // create sql statement
            String sql = "select * from employee order by id";

            myStmt = myConn.createStatement();

            // execute query
            myRs = myStmt.executeQuery(sql);

            // process result set
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String fullname = myRs.getString("fullname");
                String dob = myRs.getString("dob");
                String address = myRs.getString("address");
                String position = myRs.getString("position");
                String department = myRs.getString("department");

                // create new student object
                Employee tempEmployee = new Employee(id, fullname, dob, address, position, department);

                // add it to the list of students
                employees.add(tempEmployee);
            }

            return employees;
        } finally {
            // close JDBC objects
            close(myConn, myStmt, myRs);
        }
    }

    public void addEmployee(Employee theEmpoyee) throws Exception {

        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            // get db connection
            myConn = dataSource.getConnection();

            // create sql for insert
            String sql = "insert into employee "
                    + "(fullname, dob, address, position, department) "
                    + "values (?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            // set the param values for the student
            myStmt.setString(1, theEmpoyee.getFullname());
            myStmt.setString(2, theEmpoyee.getDob());
            myStmt.setString(3, theEmpoyee.getAddress());
            myStmt.setString(4, theEmpoyee.getPosition());
            myStmt.setString(5, theEmpoyee.getDepartment());

            // execute sql insert
            myStmt.execute();
        } finally {
            // clean up JDBC objects
            close(myConn, myStmt, null);
        }
    }
}
