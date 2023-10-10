package lk.ijse.JavaEE.servlet;

import lk.ijse.JavaEE.bo.BOFactory;
import lk.ijse.JavaEE.bo.custom.ItemBO;
import lk.ijse.JavaEE.dto.ItemDTO;
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


@WebServlet(urlPatterns = {"/item"})
public class ItemServletAPI extends HttpServlet {
    private final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try(Connection connection = pool.getConnection()) {
            JsonArrayBuilder allItems = Json.createArrayBuilder();
            ArrayList<ItemDTO> dtos = itemBO.searchItem(connection);
            for (ItemDTO i : dtos) {
                String code = i.getCode();
                String description = i.getDescription();
                int qty = i.getQty();
                double price = i.getPrice();

                JsonObjectBuilder itemObject = Json.createObjectBuilder();
                itemObject.add("code", code);
                itemObject.add("description", description);
                itemObject.add("qty", qty);
                itemObject.add("price", price);

                allItems.add(itemObject.build());
            }
            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allItems.build()));
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String description = req.getParameter("description");
        int itemQty = Integer.parseInt(req.getParameter("qty"));
        double unitPrice = Double.parseDouble(req.getParameter("price"));

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try(Connection connection = pool.getConnection()) {
            boolean isAdded = itemBO.addItem(new ItemDTO(code,description,itemQty,unitPrice), connection);
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

        String code = jsonObject.getString("code");
        String description = jsonObject.getString("description");
        int itemQty = Integer.parseInt(jsonObject.getString("qty"));
        double unitPrice = Double.parseDouble(jsonObject.getString("price"));

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try(Connection connection = pool.getConnection()) {
            boolean isUpdate = itemBO.updateItem(new ItemDTO(code, description, itemQty, unitPrice), connection);
            if (isUpdate){
                resp.getWriter().print(ResponseUtil.genJson("Success", "Item Updated..!"));
            } else {
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Item Updated Failed..!"));
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try(Connection connection = pool.getConnection()) {
            boolean isDelete = itemBO.deleteItem(code, connection);
            if (isDelete){
                resp.getWriter().print(ResponseUtil.genJson("Success", "Item Deleted..!"));
            } else {
                resp.getWriter().print(ResponseUtil.genJson("Failed", "Item Delete Failed..!"));
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }
}
