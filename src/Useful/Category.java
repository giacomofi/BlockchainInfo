package Useful;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

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
    private static HashMap<String,String> categories = new HashMap<>();

    public Category(){

    }


    public void addCategories(){

        Connection conn = null;
        Statement stmnt = null;

        try{

             conn = db.getConnection();
             stmnt = conn.createStatement();

            String query = "SELECT DISTINCT protocol from opreturn.opreturnoutput";
            ResultSet rs = stmnt.executeQuery(query);


            while(rs.next()){

                String protocolDummy = rs.getString("protocol");

                switch(protocolDummy){

                    case "unknown":
                        categories.put(protocolDummy,"Unknown");
                        break;

                    case "empty":
                        categories.put(protocolDummy,"Empty");
                        break;

                    case "proofofexistence":
                        categories.put(protocolDummy,"Notary");
                        break;

                    case "openassets":
                        categories.put(protocolDummy,"Financial");
                        break;

                    case "counterparty":
                        categories.put(protocolDummy,"Financial");
                        break;

                    case "coinspark":
                        categories.put(protocolDummy,"Financial");
                        break;

                    case "cryptocopyright":
                        categories.put(protocolDummy,"Notary");
                        break;

                    case "blocksign":
                        categories.put(protocolDummy,"Notary");
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


    public void returnCategories(){

        for(String  s : categories.keySet()){
            System.out.print(categories.get(s));
            System.out.print(" " + s);
            System.out.println("");
        }

    }
}
