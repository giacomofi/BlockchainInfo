package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.Statement;
import DBconnector.ConnectionManager;


@WebServlet(name = "OpReturnData")
public class OpReturnData extends HttpServlet {


    private ConnectionManager db = new ConnectionManager();

    private static HashMap<String,String> categories = new HashMap<>();
    private static HashMap<String,Integer> elementsPerProtocol = new HashMap<>();
    private static HashMap<String,Timestamp> firstAppearences = new HashMap<>();
    private static HashMap<String,String> protocolMetadata = new HashMap<>();
    private static HashMap<String,String> protocolEmbeddingMethods = new HashMap<>();

    public OpReturnData(){

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HashMap> allOpData = new ArrayList<>();
        OpReturnData dummy = new OpReturnData(); //Just to inizialize HashMaps

        dummy.addCategories();
        dummy.addtoList(allOpData);

        request.setAttribute("allOpData",allOpData);
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
                        protocolMetadata.put(protocolDummy,"???");
                        protocolEmbeddingMethods.put(protocolDummy,"???");

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
                        protocolMetadata.put(protocolDummy,"--");
                        protocolEmbeddingMethods.put(protocolDummy,"OP_RETURN");
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
                        protocolMetadata.put(protocolDummy,"Hash");
                        protocolEmbeddingMethods.put(protocolDummy,"OP_RETURN");
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
                        protocolMetadata.put(protocolDummy,"Financial Record");
                        protocolEmbeddingMethods.put(protocolDummy,"OP_RETURN");
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
                        protocolMetadata.put(protocolDummy,"Financial Record");
                        protocolEmbeddingMethods.put(protocolDummy,"P2PKH / MULTISIG");
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
                        protocolMetadata.put(protocolDummy,"Financial Record");
                        protocolEmbeddingMethods.put(protocolDummy,"OP_RETURN");
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
                        protocolMetadata.put(protocolDummy,"Hash");
                        protocolEmbeddingMethods.put(protocolDummy,"OP_RETURN");
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
                        protocolMetadata.put(protocolDummy,"Hash");
                        protocolEmbeddingMethods.put(protocolDummy,"OP_RETURN");
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

        categories.forEach((k,v)->{
            System.out.print(k);
            System.out.print(" " + v);
            System.out.println("");
        });
    }

    public void returnFirstAppearences(){

        firstAppearences.forEach((k,v)->{
            System.out.print(k);
            System.out.print(" " + v);
            System.out.println("");
        });
    }

    public void returnElementsPerProtocol(){

        elementsPerProtocol.forEach((k,v)->{
            System.out.print(k);
            System.out.print(" " + v);
            System.out.println("");
        });
    }

    public void returnProtocolMetadata(){

        protocolMetadata.forEach((k,v)->{
            System.out.print(k);
            System.out.print(" " + v);
            System.out.println("");
        });

    }


    public void returnProtocolEmbeddingMethods(){

        protocolEmbeddingMethods.forEach((k,v)->{
            System.out.print(k);
            System.out.print(" " + v);
            System.out.println("");
        });
    }

    public void addtoList(List<HashMap> l){

        l.add(categories);
        l.add(elementsPerProtocol);
        l.add(firstAppearences);
        l.add(protocolMetadata);
        l.add(protocolEmbeddingMethods);

    }
}
