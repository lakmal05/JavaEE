package servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")

public class CustomerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //        Get Mehotd eka use krnne data ui ekta ywnna .


        //        {

//            /*UI EKN ENA values allaganna*/
//            String id = req.getParameter("id");
//            String name = req.getParameter("name");
//            String address = req.getParameter("address");
//            String salary = req.getParameter("salary");
//
//            System.out.println("methord invoke do get");
//
//            /*Ui ekta ai value ekk ywnna */
//            PrintWriter writer = resp.getWriter();
//            //  writer.write("send res to ui");
//
//            writer.write("<table border ='2' width='100%'>");
//            writer.write("<table>");
//            writer.write("<thead>");
//            writer.write("<tr><th>Code</th><th>Name</th><th>Address</th><th>Salary</th></tr> ");
//            writer.write("</thead>");
//            writer.write("<tbody>");
//            writer.write("<tr><td>" + id + "</td><td>" + name + "</td><td>" + address + "</td><td>" + salary + "</td></tr> ");
//            writer.write("</tbody>");
//            writer.write("</table>");
//
//        }


        //DO GET METERD

        /*values get ekn ywwma qurry string ekk widihata url bar eke pennwanwa .
          samannyen get eka use krnne backened eke data ekk gnna withri .
          ui  ekn data ekk ywwoth eka url bar eke qurry string ekk widhta pena nisa*/


        // Qurry String
        // http://localhost:8080/moduelName/customer?id=C001&name=Lakmal


        /*------------------------------------------------------------------------------------------------------------------------*/


    }
         /*methors HttpMethords :::: GET(get all,search) , DELETE(server eka patte thiyna deyk delet krnna) , POST, OPTIONS(ui eken  ywna req servler ekn allow krnne na.ee sada optin method use krnwda prefild req walata) ,
         PUT(update)*/


    /*ui (frontend ) eke data backend ekta ywnna .url bar eka dige neme ynne form data ekking yna nisa*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("get Post");

        /*Ena values allagnna*/
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String salary = req.getParameter("salary");

        /*for Delete*/
        String option=req.getParameter("option");

        switch (option){
            case "delete":


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","1234");

                    PreparedStatement pstm = connection.prepareStatement("delete customer from where customerId=?");

                    pstm.setObject(1,id);

                    boolean i = pstm.executeUpdate()>0;


                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }





                break;
            case "add":

                /*db ekta dnna */
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");//dirver eka hdagnnwa
                    //connectin ekk set krgnnwa (link krgnnwa)db ekth ekka
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1234");

                    PreparedStatement psmt = connection.prepareStatement("insert into customer values(?,?,?,?)");

                    psmt.setObject(1, id);
                    psmt.setObject(2, name);
                    psmt.setObject(3, address);
                    psmt.setObject(4, salary);

                    boolean execute = psmt.executeUpdate() > 0;
//            boolean execute = psmt.execute();

                    System.out.println(execute);

                    /*Page ekata apu req eka gihin relod wenwa page eka .evelema page eka relod wenwa*/
//                    meka dmme nattn wite page ekata ywna.ena req eka ai harawala ywnwa eka krnne mekan
                    resp.sendRedirect("customer.jsp");

                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

        }



    }
}
