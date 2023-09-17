package lk.ijse.JavaEE.servlet;

import lk.ijse.JavaEE.bo.BOFactory;
import lk.ijse.JavaEE.bo.SuperBO;
import lk.ijse.JavaEE.bo.custom.PurchaseOrderBO;
import lk.ijse.JavaEE.dto.OrderItemDTO;
import lk.ijse.JavaEE.dto.PurchaseOrderDTO;
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


@WebServlet(urlPatterns = {"/purchase_order"})
public class PurchaseOrderServletAPI extends HttpServlet {
    private final PurchaseOrderBO orderBO = (PurchaseOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PURCHASEORDER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try(Connection connection = pool.getConnection()) {
            String orderID = req.getParameter("oid");

            PreparedStatement pstm = connection.prepareStatement("select Orders.oid,Orders.date,Orders.customerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice from Orders " +
                    "inner join OrderDetails on Orders.oid = OrderDetails.oid where Orders.oid=?");
            pstm.setObject(1, orderID);

            ResultSet rst = pstm.executeQuery();

            JsonArrayBuilder allOrders = Json.createArrayBuilder();
            while (rst.next()) {
                String oid = rst.getString(1);
                String date = rst.getString(2);
                String customerID = rst.getString(3);
                String itemCode = rst.getString(4);
                String qty = rst.getString(5);
                String unitPrice = rst.getString(6);

                JsonObjectBuilder orders = Json.createObjectBuilder();
                orders.add("oid", oid);
                orders.add("date", date);
                orders.add("customerID", customerID);
                orders.add("itemCode", itemCode);
                orders.add("qty", qty);
                orders.add("unitPrice", unitPrice);

                allOrders.add(orders.build());
            }
            //create the response Object
            resp.getWriter().print(ResponseUtil.genJson("Success", "Loaded", allOrders.build()));
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String oId = jsonObject.getString("oId");
        double cash = Double.parseDouble(jsonObject.getString("cash"));
        double balance = Double.parseDouble(jsonObject.getString("balance"));
        Date date = Date.valueOf(jsonObject.getString("date"));
        String cusId = jsonObject.getString("cusId");
        JsonArray itemData = jsonObject.getJsonArray("itemData");

        ArrayList<OrderItemDTO> items = new ArrayList<>();
        for (JsonValue item : itemData) {
            JsonObject jsonObject1 = item.asJsonObject();
            String itemCode1 = jsonObject1.getString("code");
            int itemQty = Integer.parseInt(jsonObject1.getString("qty"));
            items.add(new OrderItemDTO(itemCode1,itemQty));
        }

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try(Connection connection = pool.getConnection()) {

            boolean placeOrder = orderBO.purchaseOrder(new PurchaseOrderDTO(oId, cash, balance, date, cusId, items), connection);

            if (placeOrder){
                resp.getWriter().print(ResponseUtil.genJson("Success", "Successfully Added.!"));
            }else {
                throw new SQLException("Order Details Error");
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            resp.getWriter().print(ResponseUtil.genJson("Error", e.getMessage()));
        }

    }
}

