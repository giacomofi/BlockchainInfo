package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import java.util.*;

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

    private static HashMap <String,List<String>> protocolTable = new HashMap<>();




    public OpReturnData(){

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OpReturnData dummy = new OpReturnData(); //Just to inizialize HashMaps

        dummy.addCategories();


        request.setAttribute("protocolTable",protocolTable);
    }

    public void addCategories(){

        //Connection
        Connection conn = null;

        //Statements - A statement for every SQL query
        Statement stmnt = null;
        Statement stmnt1 = null;
        Statement stmnt2 = null;
        Statement stmnt3 = null;
        Statement stmnt4 = null;

        try{

            conn = db.getConnection();
            stmnt = conn.createStatement();
            stmnt1 = conn.createStatement();
            stmnt2 = conn.createStatement();
            stmnt3 = conn.createStatement();
            stmnt4 = conn.createStatement();

            String protocolquery = "SELECT DISTINCT protocol FROM opreturn.opreturnoutput";


            ResultSet rs = stmnt.executeQuery(protocolquery); //Protocol Result Set


            while(rs.next()){

                //Dummies save MySQL db data

                Integer elementsDummy;
                Integer totalSizeDummy;
                Integer averageSizeDummy;
                Timestamp dateDummy;
                String protocolDummy = rs.getString("protocol");
                List<String> tableData = new ArrayList<>();

                //Result Sets

                ResultSet epprs; //Elements Per Protocol Result Set
                ResultSet daters; //Txdate Result Sets
                ResultSet totalsizers; //Total size of Metadata Per Protocol
                ResultSet averagesizers; //Average size of Metadata per Protocol



                switch(protocolDummy){

                    case "unknown":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'unknown' ");
                        while(epprs.next()){
                            elementsDummy = epprs.getInt("count");
                            tableData.add(elementsDummy.toString());
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'unknown'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            tableData.add(dateDummy.toString());
                        }
                        totalsizers = stmnt3.executeQuery("select sum(length(metadata)) as totalsize " +
                                "from opreturn.opreturnoutput " +
                                "where protocol = 'unknown'");
                        while(totalsizers.next()){
                            totalSizeDummy = totalsizers.getInt("totalsize");
                            tableData.add(totalSizeDummy.toString());
                        }
                        averagesizers = stmnt4.executeQuery("SELECT o.totalsize / p.numberofel as average  " +
                                "from(select sum(length(metadata)) as totalsize from opreturn.opreturnoutput where protocol = 'unknown') o " +
                                "cross join " +
                                "(select count(metadata) as numberofel from opreturn.opreturnoutput where protocol = 'unknown') p");
                        while(averagesizers.next()){
                            averageSizeDummy = averagesizers.getInt("average");
                            tableData.add(averageSizeDummy.toString());
                        }
                        tableData.add("Unknown");
                        tableData.add("???");
                        tableData.add("???");
                        protocolTable.put(protocolDummy,tableData);


                        break;

                    case "empty":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count " +
                                "FROM opreturn.opreturnoutput" +
                                " WHERE protocol = 'empty' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            tableData.add(elementsDummy.toString());
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'empty'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            tableData.add(dateDummy.toString());
                        }
                        totalsizers = stmnt3.executeQuery("select sum(length(metadata)) as totalsize " +
                                "from opreturn.opreturnoutput " +
                                "where protocol = 'empty'");
                        while(totalsizers.next()){
                            totalSizeDummy = totalsizers.getInt("totalsize");
                            tableData.add(totalSizeDummy.toString());
                        }
                        averagesizers = stmnt4.executeQuery("SELECT o.totalsize / p.numberofel as average  " +
                                "from(select sum(length(metadata)) as totalsize from opreturn.opreturnoutput where protocol = 'empty') o " +
                                "cross join " +
                                "(select count(metadata) as numberofel from opreturn.opreturnoutput where protocol = 'empty') p");
                        while(averagesizers.next()){
                            averageSizeDummy = averagesizers.getInt("average");
                            tableData.add(averageSizeDummy.toString());
                        }
                        tableData.add("Empty");
                        tableData.add("--");
                        tableData.add("OP_RETURN");
                        protocolTable.put(protocolDummy,tableData);
                        break;

                    case "proofofexistence":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'proofofexistence' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            tableData.add(elementsDummy.toString());
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'proofofexistence'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            tableData.add(dateDummy.toString());
                        }
                        totalsizers = stmnt3.executeQuery("select sum(length(metadata)) as totalsize " +
                                "from opreturn.opreturnoutput " +
                                "where protocol = 'proofofexistence'");
                        while(totalsizers.next()){
                            totalSizeDummy = totalsizers.getInt("totalsize");
                            tableData.add(totalSizeDummy.toString());
                        }
                        averagesizers = stmnt4.executeQuery("SELECT o.totalsize / p.numberofel as average  " +
                                "from(select sum(length(metadata)) as totalsize from opreturn.opreturnoutput where protocol = 'proofofexistence') o " +
                                "cross join " +
                                "(select count(metadata) as numberofel from opreturn.opreturnoutput where protocol = 'proofofexistence') p");
                        while(averagesizers.next()){
                            averageSizeDummy = averagesizers.getInt("average");
                            tableData.add(averageSizeDummy.toString());
                        }
                        tableData.add("Notary");
                        tableData.add("Hash");
                        tableData.add("OP_RETURN");
                        protocolTable.put(protocolDummy,tableData);
                        break;

                    case "openassets":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput" +
                                " WHERE protocol = 'openassets' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            tableData.add(elementsDummy.toString());
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min " +
                                "FROM opreturn.opreturnoutput" +
                                " WHERE protocol = 'openassets'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            tableData.add(dateDummy.toString());
                        }
                        totalsizers = stmnt3.executeQuery("select sum(length(metadata)) as totalsize " +
                                "from opreturn.opreturnoutput" +
                                " where protocol = 'openassets'");
                        while(totalsizers.next()){
                            totalSizeDummy = totalsizers.getInt("totalsize");
                            tableData.add(totalSizeDummy.toString());
                        }
                        averagesizers = stmnt4.executeQuery("SELECT o.totalsize / p.numberofel as average  " +
                                "from(select sum(length(metadata)) as totalsize from opreturn.opreturnoutput where protocol = 'openassets') o " +
                                "cross join " +
                                "(select count(metadata) as numberofel from opreturn.opreturnoutput where protocol = 'openassets') p");
                        while(averagesizers.next()){
                            averageSizeDummy = averagesizers.getInt("average");
                            tableData.add(averageSizeDummy.toString());
                        }
                        tableData.add("Financial");
                        tableData.add("Financial Record");
                        tableData.add("OP_RETURN");
                        protocolTable.put(protocolDummy,tableData);
                        break;

                    case "counterparty":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'counterparty' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            tableData.add(elementsDummy.toString());
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min " +
                                "FROM opreturn.opreturnoutput" +
                                " WHERE protocol = 'counterparty'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            tableData.add(dateDummy.toString());
                        }
                        totalsizers = stmnt3.executeQuery("select sum(length(metadata)) as totalsize " +
                                "from opreturn.opreturnoutput " +
                                "where protocol = 'counterparty'");
                        while(totalsizers.next()){
                            totalSizeDummy = totalsizers.getInt("totalsize");
                            tableData.add(totalSizeDummy.toString());
                        }
                        averagesizers = stmnt4.executeQuery("SELECT o.totalsize / p.numberofel as average  " +
                                "from(select sum(length(metadata)) as totalsize from opreturn.opreturnoutput where protocol = 'counterparty') o " +
                                "cross join " +
                                "(select count(metadata) as numberofel from opreturn.opreturnoutput where protocol = 'counterparty') p");
                        while(averagesizers.next()){
                            averageSizeDummy = averagesizers.getInt("average");
                            tableData.add(averageSizeDummy.toString());
                        }
                        tableData.add("Financial");
                        tableData.add("Financial Record");
                        tableData.add("P2PKH / MULTISIG");
                        protocolTable.put(protocolDummy,tableData);
                        break;

                    case "coinspark":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count " +
                                "FROM opreturn.opreturnoutput" +
                                " WHERE protocol = 'coinspark' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            tableData.add(elementsDummy.toString());
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'coinspark'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            tableData.add(dateDummy.toString());
                        }
                        totalsizers = stmnt3.executeQuery("select sum(length(metadata)) as totalsize " +
                                "from opreturn.opreturnoutput " +
                                "where protocol = 'coinspark'");
                        while(totalsizers.next()){
                            totalSizeDummy = totalsizers.getInt("totalsize");
                            tableData.add(totalSizeDummy.toString());
                        }
                        averagesizers = stmnt4.executeQuery("SELECT o.totalsize / p.numberofel as average  " +
                                "from(select sum(length(metadata)) as totalsize from opreturn.opreturnoutput where protocol = 'coinspark') o " +
                                "cross join " +
                                "(select count(metadata) as numberofel from opreturn.opreturnoutput where protocol = 'coinspark') p");
                        while(averagesizers.next()){
                            averageSizeDummy = averagesizers.getInt("average");
                            tableData.add(averageSizeDummy.toString());
                        }
                        tableData.add("Financial");
                        tableData.add("Financial Record");
                        tableData.add("OP_RETURN");
                        protocolTable.put(protocolDummy,tableData);
                        break;

                    case "cryptocopyright":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'cryptocopyright' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            tableData.add(elementsDummy.toString());
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'cryptocopyright'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            tableData.add(dateDummy.toString());
                        }
                        totalsizers = stmnt3.executeQuery("select sum(length(metadata)) as totalsize " +
                                "from opreturn.opreturnoutput " +
                                "where protocol = 'cryptocopyright'");
                        while(totalsizers.next()){
                            totalSizeDummy = totalsizers.getInt("totalsize");
                            tableData.add(totalSizeDummy.toString());
                        }
                        averagesizers = stmnt4.executeQuery("SELECT o.totalsize / p.numberofel as average  " +
                                "from(select sum(length(metadata)) as totalsize from opreturn.opreturnoutput where protocol = 'cryptocopyright') o " +
                                "cross join " +
                                "(select count(metadata) as numberofel from opreturn.opreturnoutput where protocol = 'cryptocopyright') p");
                        while(averagesizers.next()){
                            averageSizeDummy = averagesizers.getInt("average");
                            tableData.add(averageSizeDummy.toString());
                        }
                        tableData.add("Notary");
                        tableData.add("Hash");
                        tableData.add("OP_RETURN");
                        protocolTable.put(protocolDummy,tableData);
                        break;

                    case "blocksign":
                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'blocksign' ");
                        while(epprs.next()) {
                            elementsDummy = epprs.getInt("count");
                            tableData.add(elementsDummy.toString());
                        }
                        daters = stmnt2.executeQuery("SELECT MIN(txdate) AS min " +
                                "FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'blocksign'");
                        while(daters.next()){
                            dateDummy = daters.getTimestamp("min");
                            tableData.add(dateDummy.toString());
                        }
                        totalsizers = stmnt3.executeQuery("select sum(length(metadata)) as totalsize " +
                                "from opreturn.opreturnoutput " +
                                "where protocol = 'blocksign'");
                        while(totalsizers.next()){
                            totalSizeDummy = totalsizers.getInt("totalsize");
                            tableData.add(totalSizeDummy.toString());
                        }
                        averagesizers = stmnt4.executeQuery("SELECT o.totalsize / p.numberofel as average  " +
                                "from(select sum(length(metadata)) as totalsize from opreturn.opreturnoutput where protocol = 'blocksign') o " +
                                "cross join " +
                                "(select count(metadata) as numberofel from opreturn.opreturnoutput where protocol = 'blocksign') p");
                        while(averagesizers.next()){
                            averageSizeDummy = averagesizers.getInt("average");
                            tableData.add(averageSizeDummy.toString());
                        }
                        tableData.add("Notary");
                        tableData.add("Hash");
                        tableData.add("OP_RETURN");
                        protocolTable.put(protocolDummy,tableData);
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
                    stmnt1.close();

                if(stmnt2 != null)
                    stmnt2.close();

                if(stmnt3 != null){

                    stmnt3.close();
                }

                if(stmnt4 != null){

                    stmnt4.close();
                }

                if(conn != null)
                    conn.close();

            }catch (SQLException e){

                e.printStackTrace();
            }
        }
    }


    public void printAll(){

        TreeMap<String,List<String>> orderedMap = new TreeMap<>(protocolTable);

        orderedMap.forEach((k,v)->{
                    System.out.print(k);
                    System.out.print(" " + v);
                    System.out.println("");
                }
        );

    }


}
