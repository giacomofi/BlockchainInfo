package Useful;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.Statement;
import DBconnector.ConnectionManager;


public class Category {

    private ConnectionManager db = new ConnectionManager();
    private static List<String> categories = new ArrayList<>();

    public Category(){

    }


    public void addCategories(){

        Connection conn = null;
        Statement stmnt = null;

        try{

             conn = db.getConnection();
             stmnt = conn.createStatement();

            String query = "SELECT DISTINCT protocol from oprerturn.opreturnoutput";
            ResultSet rs = stmnt.executeQuery(query);


            while(rs.next()){

                String protocolDummy = rs.getString("protocol");

                switch(protocolDummy){

                    case "unknown":
                        categories.add("Unknown");
                        break;

                    case "empty":
                        categories.add("Empty");
                        break;

                    case "proofofexistence":
                        categories.add("Notary");
                        break;

                    case "openassets":
                        categories.add("Financial");
                        break;

                    case "counterparty":
                        categories.add("Financial");
                        break;

                    case "coinspark":
                        categories.add("Financial");
                        break;

                    case "cryptocopyright":
                        categories.add("Notary");
                        break;

                    case "blocksign":
                        categories.add("Notary");
                        break;

                }
            }


        }catch (SQLException e){

            e.printStackTrace();

        }finally {

            try{

                if(stmnt != null)
                    stmnt.close();

                if(conn != null)
                    conn.close();

            }catch (SQLException e){

                e.printStackTrace();
            }
        }
    }


    public List<String> returnCategories(){

        return categories;

    }
}
