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
    private static HashMap<String,Integer> elementsPerProtocol = new HashMap<>();
    private static HashMap<String,Timestamp> firstAppearences = new HashMap<>();

    public Category(){

    }


    public void addCategories(){

        //Connection
        Connection conn = null;

        //Statements - A statement for every SQL query
        Statement stmnt = null;
        Statement stmnt1 = null;
        Statement stmnt2 = null;

        try{

             conn = db.getConnection();
             stmnt = conn.createStatement();
             stmnt1 = conn.createStatement();
             stmnt2 = conn.createStatement();

             String protocolquery = "SELECT DISTINCT protocol FROM opreturn.opreturnoutput";


             ResultSet rs = stmnt.executeQuery(protocolquery); //Protocol Result Set


            while(rs.next()){

                //Dummies save MySQL db data

                Integer elementsDummy;
                Timestamp dateDummy;
                String protocolDummy = rs.getString("protocol");

                //Result Sets

                ResultSet epprs; //Elements Per Protocol Result Set
                ResultSet daters; //Txdate Result Sets



                switch(protocolDummy){

                    case "unknown":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count FROM opreturn.opreturnoutput WHERE protocol = 'unknown' ");
                        while(epprs.next()){
                            elementsDummy = epprs.getInt("count");
                            elementsPerProtocol.put(protocolDummy,elementsDummy);
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min FROM opreturn.opreturnoutput WHERE protocol = 'unknown'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            firstAppearences.put(protocolDummy,dateDummy);
                        }
                        categories.put(protocolDummy,"Unknown");
                        break;

                    case "empty":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count FROM opreturn.opreturnoutput WHERE protocol = 'empty' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            elementsPerProtocol.put(protocolDummy, elementsDummy);
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min FROM opreturn.opreturnoutput WHERE protocol = 'empty'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            firstAppearences.put(protocolDummy,dateDummy);
                        }
                        categories.put(protocolDummy,"Empty");
                        break;

                    case "proofofexistence":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count FROM opreturn.opreturnoutput WHERE protocol = 'proofofexistence' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            elementsPerProtocol.put(protocolDummy, elementsDummy);
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min FROM opreturn.opreturnoutput WHERE protocol = 'proofofexistence'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            firstAppearences.put(protocolDummy,dateDummy);
                        }
                        categories.put(protocolDummy,"Notary");
                        break;

                    case "openassets":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count FROM opreturn.opreturnoutput WHERE protocol = 'openassets' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            elementsPerProtocol.put(protocolDummy, elementsDummy);
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min FROM opreturn.opreturnoutput WHERE protocol = 'openassets'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            firstAppearences.put(protocolDummy,dateDummy);
                        }
                        categories.put(protocolDummy,"Financial");
                        break;

                    case "counterparty":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count FROM opreturn.opreturnoutput WHERE protocol = 'counterparty' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            elementsPerProtocol.put(protocolDummy, elementsDummy);
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min FROM opreturn.opreturnoutput WHERE protocol = 'counterparty'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            firstAppearences.put(protocolDummy,dateDummy);
                        }
                        categories.put(protocolDummy,"Financial");
                        break;

                    case "coinspark":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count FROM opreturn.opreturnoutput WHERE protocol = 'coinspark' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            elementsPerProtocol.put(protocolDummy, elementsDummy);
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min FROM opreturn.opreturnoutput WHERE protocol = 'coinspark'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            firstAppearences.put(protocolDummy,dateDummy);
                        }
                        categories.put(protocolDummy,"Financial");
                        break;

                    case "cryptocopyright":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count FROM opreturn.opreturnoutput WHERE protocol = 'cryptocopyright' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            elementsPerProtocol.put(protocolDummy, elementsDummy);
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min FROM opreturn.opreturnoutput WHERE protocol = 'cryptocopyright'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            firstAppearences.put(protocolDummy,dateDummy);
                        }
                        categories.put(protocolDummy,"Notary");
                        break;

                    case "blocksign":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count FROM opreturn.opreturnoutput WHERE protocol = 'blocksign' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            elementsPerProtocol.put(protocolDummy, elementsDummy);
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min FROM opreturn.opreturnoutput WHERE protocol = 'blocksign'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            firstAppearences.put(protocolDummy,dateDummy);
                        }
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

                if(stmnt1 != null)
                    stmnt.close();

                if(stmnt2 != null)
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

    public void returnFirstAppearences(){

        for(String s : firstAppearences.keySet()){
            System.out.print(firstAppearences.get(s));
            System.out.print(" " + s);
            System.out.println("");
        }
    }

    public void returnelementsPerProtocol(){

        for(String s : elementsPerProtocol.keySet()){
            System.out.print(elementsPerProtocol.get(s));
            System.out.print(" " + s);
            System.out.println("");
        }


    }


}
