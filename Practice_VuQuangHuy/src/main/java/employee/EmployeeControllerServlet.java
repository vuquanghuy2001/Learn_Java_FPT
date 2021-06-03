package employee;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EmployeeControllerServlet", value = "/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {

    private EmployeeDbUtil employeeDbUtil;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();

        Context initContext = null;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/practice");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            employeeDbUtil = new EmployeeDbUtil(dataSource);
        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
            // if the command is missing, then default to listing students
            if (theCommand == null) {
                theCommand = "LIST";
            }
            // route to the appropriate method
            switch (theCommand) {
                case "LIST":
                    listEmplyees(request, response);
                    break;
                case "ADD":
                    addEmployee(request, response);
                    break;
                default:
                    listEmplyees(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private void listEmplyees(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Employee> vocabularies = employeeDbUtil.getEmployees();

        // add students to the request
        request.setAttribute("EMPLOYEE_LIST", vocabularies);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
        dispatcher.forward(request, response);
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read student info from form data
        String fullname = request.getParameter("fullname");
        String dob = request.getParameter("dob");
        String address = request.getParameter("dob");
        String position = request.getParameter("position");
        String department = request.getParameter("department");



        // create a new student object
        Employee theEmployee = new Employee(fullname, dob, address, position, department);

        // add the student to the database
        employeeDbUtil.addEmployee(theEmployee);

        // send back to main page (the student list)
        listEmplyees(request, response);
    }
}
