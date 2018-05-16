package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpreturnDataServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>All Opreturn data</title></head>");
        out.println("<body>");
        out.println("<center><h1>All Opreturn data</h1>");
        Connection conn = null;
        Statement stmnt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/opreturn","root","Cetriolodimare1?");
            stmnt = conn.createStatement();
            String orderBy = request.getParameter("sort");

            if(orderBy == null || orderBy == "")
                orderBy = "txdate";

            String orderByDir = request.getParameter("sortdir");

            if(orderByDir == null || orderByDir == "")
                orderByDir = "asc";

            String query = "SELECT DISTINCT opreturnoutput.protcol," + "opreturn.metadata,"  + "opreturn.txdate";
            ResultSet rs = stmnt.executeQuery(query);

            while(rs.next()){

                String protocol = rs.getString("protocol");
                String metadata = rs.getString("metadata");
                Timestamp txdate = rs.getTimestamp("txdate");
                out.print(protocol + "::");
                out.print(metadata + "::");
                out.print(txdate + "::");
            }

        }catch(SQLException e){

            e.printStackTrace();

        }catch(ClassNotFoundException e){

            throw(new ServletException(e.toString()));

        }finally {

            try {

                if (stmnt != null)
                    stmnt.close();

                if (conn != null)
                    conn.close();

            } catch (SQLException ex) {

            }
        }

        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
        out.close();

    }
}
