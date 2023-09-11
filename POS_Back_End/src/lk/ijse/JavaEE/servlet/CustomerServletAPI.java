package lk.ijse.JavaEE.servlet;

import lk.ijse.JavaEE.bo.BOFactory;
import lk.ijse.JavaEE.bo.SuperBO;
import lk.ijse.JavaEE.bo.custom.CustomerBO;
import lk.ijse.JavaEE.dto.CustomerDTO;
import lk.ijse.JavaEE.util.ResponseUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/customer")
public class CustomerServletAPI extends HttpServlet {
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public CustomerServletAPI() {
        System.out.println("Customer Servlet Constructor Invoked");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()) {
            JsonArrayBuilder allCustomers = Json.createArrayBuilder();

            ArrayList<CustomerDTO> customerDTOS = customerBO.searchCustomer(connection);
            for (CustomerDTO c : customerDTOS) {
                String id = c.getId();
                String name = c.getName();
                String address = c.getAddress();
                double salary = c.getSalary();

                JsonObjectBuilder customerObject = Json.createObjectBuilder();
                customerObject.add("id", id);
                customerObject.add("name", name);
                customerObject.add("address", address);
                customerObject.add("salary", salary);
                allCustomers.add(customerObject.build());
            }
            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allCustomers.build()));
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cusID = req.getParameter("cusID");
        String cusName = req.getParameter("cusName");
        String cusAddress = req.getParameter("cusAddress");
        double cusSalary = Double.parseDouble(req.getParameter("cusSalary"));

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()) {
            boolean isAdded = customerBO.addCustomer(new CustomerDTO(cusID,cusName,cusAddress,cusSalary), connection);
            if (isAdded){
                resp.getWriter().print(ResponseUtil.genJson("Success", "Successfully Added.!"));
            }

        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String cusID = jsonObject.getString("id");
        String cusName = jsonObject.getString("name");
        String cusAddress = jsonObject.getString("address");
        double salary = Double.parseDouble(jsonObject.getString("salary"));

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()) {
            boolean isUpdated = customerBO.updateCustomer(new CustomerDTO(cusID,cusName,cusAddress,salary), connection);
            if (isUpdated){
                resp.getWriter().print(ResponseUtil.genJson("Success", "Customer Updated..!"));
            }else {
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Customer Updated Failed..!"));
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cusID = req.getParameter("cusID");

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()) {
            boolean isDelete = customerBO.deleteCustomer(cusID, connection);
            if (isDelete){
                resp.getWriter().print(ResponseUtil.genJson("Success", "Customer Deleted..!"));
            }else {
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Customer Delete Failed..!"));
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

}
