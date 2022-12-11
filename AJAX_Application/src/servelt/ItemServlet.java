package servelt;

import model.ItemDTO;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/item")

public class ItemServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        ArrayList<ItemDTO> allItems = new ArrayList();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1234");

//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("select * from item");
            ResultSet rst = pstm.executeQuery();
            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
//            while (rst.next()) {
//                String id = rst.getString("id");
//                String name = rst.getString("name");
//                String qty = rst.getString("qty");
//                Double price = rst.getDouble("price");
//                ItemDTO dto = new ItemDTO(id, name, qty, price);
//                jsonArray.add(dto);
//
//
//            }
            /*Array ekkta data add krnne obj ekin nisa json ekma object ekk hadagnnwa*/


            while (rst.next()) {

                /*me part ekn wenne jaosn eke thiyna object builder kiyna methord ekn obect cerate krla aliye thiyna json array ekta initialize krnwa*/
                JsonObjectBuilder object = Json.createObjectBuilder();
                object.add("id", rst.getString("id"));
                object.add("name", rst.getString("name"));
                object.add("qty", rst.getString("qty"));
                object.add("price", rst.getDouble("price"));
                jsonArray.add(object.build());


            }
            resp.setContentType("application/json");

            JsonObjectBuilder respObj = Json.createObjectBuilder();
            respObj.add("data", jsonArray.build());
            resp.getWriter().print(respObj.build());


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        resp.getWriter().print(resp);
        resp.getWriter().print("methord invoke");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String qty = req.getParameter("qty");
        Double price = Double.valueOf(req.getParameter("price"));


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1234");
            PreparedStatement pstm = con.prepareStatement("insert into item values(?,?,?,?)");
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, qty);
            pstm.setObject(4, price);
            boolean i = pstm.executeUpdate() > 0;
            if (i) {
                JsonObjectBuilder obj = Json.createObjectBuilder();
                resp.getWriter().print(obj.build());

            }


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1234");
            PreparedStatement pstm = con.prepareStatement("delete from item where id=?");
            pstm.setObject(1, id);

            boolean execute = pstm.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

//            JsonObjectBuilder jObject = Json.createObjectBuilder();
//            if (execute) {
//                jObject.add("state","done");
//                jObject.add("message","Successfully Deleted..!");
//            }else{
//                jObject.add("state","error");
//                jObject.add("message","No such Customer to Delete..!");
//                resp.setStatus(400);
//            }
//            resp.getWriter().print(jObject.build());
//        } catch (ClassNotFoundException e) {
//            JsonObjectBuilder jsonObject = Json.createObjectBuilder();
//            jsonObject.add("state","error");
//            jsonObject.add("message",e.getMessage());
//            resp.getWriter().print(jsonObject.build());
//            resp.setStatus(500);
//        } catch (SQLException e) {
//            JsonObjectBuilder jsonObject = Json.createObjectBuilder();
//            jsonObject.add("state","error");
//            jsonObject.add("message",e.getMessage());
//            resp.getWriter().print(jsonObject.build());
//            resp.setStatus(400);
//        }


    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*Form data ekk widhta data ekk ewqoth allagnna puluwn prst methord ekat wirhri puluwn.natttn null ekk enne*/
//        eka nisa apita form data widhta data giynne nathuwa wena widhtka data req krnna wenwa .eka nisa api JSON type eka use krwna

//   Json waldi ui ekn req krna parameter eka allgnna ."req.getParameter use krnne ba" eka nisa api Json wala thiyna methord ekk use krnwa

        /*"req.getReader"meka argena Json.createReader ekkta dala JsonReader obj ekn Read krgnna one*/
        JsonReader reader = Json.createReader(req.getReader());

//        data tika  argena store krla dgnna arrya ekk hri object ekk hri hadgnna one,eka json ekk wenna one

        JsonObject itemObj = reader.readObject();

        String id = itemObj.getString("id");
        String name = itemObj.getString("name");
        String qty = itemObj.getString("qty");
        Double price = Double.valueOf(itemObj.getString("price"));


        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1234");
            PreparedStatement pstm = con.prepareStatement("update item set name=?,qty=?,price=? where id=?");
            pstm.setObject(4,id);/*mark theese imoirtaant place*/
            pstm.setObject(1,name);
            pstm.setObject(2,qty);
            pstm.setObject(3,price);
            boolean b = pstm.executeUpdate() > 0;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
      
    


    }
}
