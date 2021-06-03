package codelean.jdbc;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "jdbctest", value = "/jdbctest")
public class TestServlet extends HttpServlet {

    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Context initContext = null;
        try{
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/student");
        }catch (NamingException e){
            e.printStackTrace();
        }

        // Step 1: Set up the printwriter
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");

        // Step 2: Get a connection to the database
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try{
            myConn = dataSource.getConnection();
            // Step 3: Create a SQL statements
            String sql = "select * from student";
            myStmt = myConn.createStatement();
            // Step 4: Excute SQL query
            myRs = myStmt.executeQuery(sql);
            // Step 5: Process the result set
            while (myRs.next()){
                String email = myRs.getString("email");
                out.println(email);
            }
        }catch (Exception exc){
            exc.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
