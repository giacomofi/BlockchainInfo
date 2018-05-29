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

@WebServlet(name = "ChartServlet")
public class ChartServlet extends HttpServlet {

    private ConnectionManager db = new ConnectionManager();

    private static HashMap <String,List<Integer>> protocolChart = new HashMap<>();

    public ChartServlet(){

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void getChartData(){

        Connection conn = null;

        Statement stmnt = null;
        Statement stmnt1 = null;

        try{

            conn = db.getConnection();
            stmnt = conn.createStatement();

            String protocolquery = "SELECT DISTINCT protocol FROM opreturn.opreturnoutput";


            ResultSet rs = stmnt.executeQuery(protocolquery); //Protocol Result Set


            while(rs.next()){

                Integer transactionsPerYear;
                Integer transactionsPerProtocol;
                List<Integer> chartData = new ArrayList<>();

                String protocolDummy = rs.getString("protocol");

                ResultSet epprs; //Elements Per Protocol Result Set

                switch(protocolDummy){

                    case "unknown":

                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'unknown' ");
                        while(epprs.next()){
                            transactionsPerProtocol = epprs.getInt("count");
                            chartData.add(transactionsPerProtocol);
                        }
                        protocolChart.put(protocolDummy,chartData);
                        break;

                    case "empty":

                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'empty' ");
                        while(epprs.next()){
                            transactionsPerProtocol = epprs.getInt("count");
                            chartData.add(transactionsPerProtocol);
                        }
                        protocolChart.put(protocolDummy,chartData);
                        break;

                    case "proofofexistence":

                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'proofofexistence' ");
                        while(epprs.next()){
                            transactionsPerProtocol = epprs.getInt("count");
                            chartData.add(transactionsPerProtocol);
                        }
                        protocolChart.put(protocolDummy,chartData);
                        break;

                    case "openassets":

                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'openassets' ");
                        while(epprs.next()){
                            transactionsPerProtocol = epprs.getInt("count");
                            chartData.add(transactionsPerProtocol);
                        }
                        protocolChart.put(protocolDummy,chartData);
                        break;

                    case "counterparty":

                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'counterparty' ");
                        while(epprs.next()){
                            transactionsPerProtocol = epprs.getInt("count");
                            chartData.add(transactionsPerProtocol);
                        }
                        protocolChart.put(protocolDummy,chartData);
                        break;

                    case "coinspark":

                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'coinspark' ");
                        while(epprs.next()){
                            transactionsPerProtocol = epprs.getInt("count");
                            chartData.add(transactionsPerProtocol);
                        }
                        protocolChart.put(protocolDummy,chartData);
                        break;

                    case "cryptocopyright":

                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'cryptocopyright' ");
                        while(epprs.next()){
                            transactionsPerProtocol = epprs.getInt("count");
                            chartData.add(transactionsPerProtocol);
                        }
                        protocolChart.put(protocolDummy,chartData);
                        break;

                    case "blocksign":

                        epprs = stmnt1.executeQuery("SELECT COUNT(protocol) AS count" +
                                " FROM opreturn.opreturnoutput " +
                                "WHERE protocol = 'blocksign' ");
                        while(epprs.next()){
                            transactionsPerProtocol = epprs.getInt("count");
                            chartData.add(transactionsPerProtocol);
                        }
                        protocolChart.put(protocolDummy,chartData);
                        break;
                }

            }



        }catch(SQLException e){

            e.printStackTrace();

        }finally {

            try {
                if (conn != null)

                    conn.close();

                if (stmnt != null)

                    stmnt.close();

                if(stmnt1 != null){

                    stmnt1.close();
                }

            } catch(SQLException e){

                e.printStackTrace();
            }
        }



    }


}
