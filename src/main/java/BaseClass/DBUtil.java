package BaseClass;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private static Connection connect = null;
    private static Statement statement = null;
    private ResultSet resultSet = null;
    private static List<String> list = new ArrayList<>();

    //DDK Database
    private void connectToDatabaseDDK() throws SQLException, ClassNotFoundException {
        String driverClass = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@lpwracdkdqu-scan.npd.com:1521/srv_ddk";
        String userName = "QA_User";
        String password = "npd12qa_user";
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url,userName,password);
        //System.out.println("Database is connected");
    }
    public List<String> readDataBaseDDK(String SQLQuery, String ColumnLabel){
        try {
            connectToDatabaseDDK();
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery(SQLQuery);
            list = getResultSetData(resultSet, ColumnLabel);

            //System.out.println(list.size());
            //System.out.println(list);
            //System.out.println("Security key is: "+list.get(0));
        } catch (Exception e) {
            System.out.println("Error: "+e);
        } finally {
            close();
        }
        return list;
    }
    private void close() {
        try{
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connect != null){
                connect.close();
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    private List<String> getResultSetData(ResultSet resultSet, String ColumnLabel) throws SQLException {
        List<String> dataList = new ArrayList<>();

        String[] col_Database = ColumnLabel.split("[|]");


        while(resultSet.next()){
/*            String itemName = resultSet.getString(ColumnLabel);
            dataList.add(itemName);*/
            String itemName="";
            for(int idx=0;idx<=col_Database.length-1;idx++){

                if(itemName==""){
                    itemName = resultSet.getString(col_Database[idx]);
                }else{
                    itemName = itemName + "|" + resultSet.getString(col_Database[idx]);
                }
            }
            dataList.add(itemName);

        }
        return dataList;
    }

    //PDK Database
    private void connectToDatabasePDK() throws SQLException, ClassNotFoundException {
        String driverClass = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@crslxpphc-scan.npd.com:1521/SRV_PDK";
        String userName = "QA_User";
        String password = "npd08qa_user";
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url,userName,password);
        //System.out.println("Database is connected");
    }
    public List<String> readDataBasePDK(String SQLQuery, String ColumnLabel){
        try {
            connectToDatabasePDK();
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery(SQLQuery);
            list = getResultSetData(resultSet, ColumnLabel);

            //System.out.println(list.size());
            //System.out.println(list);
            //System.out.println("Security key is: "+list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    //Only Execute
    public void OnlyExecutePDK(String SQLQuery){
        try {
            connectToDatabasePDK();
            statement = connect.createStatement();
            statement.executeQuery(SQLQuery);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

/*
    public void ConnectSybase() throws SQLException, ClassNotFoundException {


 //       ' Get Parameters for DB Connection String
        String ENG = "lxsybprod7_iqpnpd";
        String DB = "lxsybprod7_iqpnpd";
        String IP = "lxsybprod7";
        int PORT = 2638;
        String UID="etl_user";
        String PWD = "npd11etl_user";

//        ' Create Sybase Connection String using the variables passed in
*//*        sDbConnectStr = "Driver={Sybase IQ};UID="&UID&";PWD="&PWD&";ENG="&ENG&";DBN="&DB&";LINKS=TCPIP(IP="&IP&";PORT="&PORT&")"
        CreateSybaseConnectionString = sDbConnectStr ' return the connection string*//*


//        String dburl = "jdbc:sqlanywhere:uid=etl_user;pwd=npd11etl_user;eng=lxsybprod7_iqpnpd;database=lxsybprod7_iqpnpd;links=tcpip(host=172.20.20.20)";
//        String dburl = "jdbc:sqlanywhere:uid=etl_user;pwd=npd11etl_user;eng=lxsybprod7_iqpnpd;database=lxsybprod7_iqpnpd;links=tcpip(ip=lxsybprod7;port=2638)";
//        String dburl = "Driver={Sybase IQ};UID=" + UID + ";PWD=" + PWD + ";ENG=" + ENG + ";DBN=" + DB + ";LINKS=TCPIP(IP=" + IP + ";PORT=" + PORT + ")";
//        String dburl = "jdbc:Sybase IQ:uid=" + UID + ";pwd=" + PWD + ";eng= "+ ENG + ";DBN=" + DB + ";links=tcpip(IP=" + IP + ";PORT=" + PORT + ")";

//        String dburl = "DNS=PROD";

//--------------------------------------------------------------------------------------
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/lxsybprod7_iqpnpd";
        String userName = "QA_User";
        String password = "npd12qa_user";
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url,userName,password);

*//*        SysProps.put("user","userid");
        SysProps.put("password","user_password");
        String  url = "jdbc:sybase:Tds:myserver:3697";
        Connection_con =
                DriverManager.getConnection(url,SysProps);*//*
//--------------------------------------------------------------------------------------

        // Connect to Sybase Database
        //Connection con = DriverManager.getConnection(dburl);
        Statement statement = connect.createStatement();

        // We use Sybase specific select getdate() query to return date
        ResultSet rs = statement.executeQuery("SELECT GETDATE()");


        if (rs.next()) {
            Date currentDate = rs.getDate(1); // get first column returned
            System.out.println("Current Date from Sybase is : "+currentDate);
        }
        rs.close();
        statement.close();
        connect.close();

    }*/


    public void getSybaseCon() throws Exception {

        DriverManager.registerDriver( (Driver)
                Class.forName( "com.sybase.jdbc4.jdbc.SybDriver" ).newInstance() );
        String dburl = "jdbc:sybase:Tds:lpscsybp26.npd.com:2638?ServiceName=LPSCSYBP26_IQPNPD";

        // Connect to Sybase Database
        Connection con = DriverManager.getConnection(dburl,"guddin","qap_guddin");
        Statement statement = con.createStatement();

        // We use Sybase specific select getdate() query to return date
        ResultSet rs = statement.executeQuery("SELECT Count(*) FROM bsc_mkt_usw_d2.m_format");

        if (rs.next()) {
//            Date currentDate = rs.getDate(1); // get first column returned
            String dbval = rs.getString(1);
            System.out.println(dbval);
        }
        rs.close();
        statement.close();
        con.close();

    }


    public ResultSet ConnectSybase(String query) throws Exception{

        DriverManager.registerDriver( (Driver)
                Class.forName( "com.sybase.jdbc4.jdbc.SybDriver" ).newInstance() );
        String dburl = "jdbc:sybase:Tds:lpscsybp26.npd.com:2638?ServiceName=LPSCSYBP26_IQPNPD";

        // Connect to Sybase Database
        Connection con = DriverManager.getConnection(dburl,"guddin","qap_guddin");
        Statement statement = con.createStatement();

        System.out.println(query);

        // Execute Query
        ResultSet rs = statement.executeQuery(query);

        return rs;

    }


    public ResultSet ConnectSybase_DKReportBuilder(String query) throws Exception{

        DriverManager.registerDriver( (Driver)
                Class.forName( "com.sybase.jdbc4.jdbc.SybDriver" ).newInstance() );
        String dburl = "jdbc:sybase:Tds:lpscsybp01.npd.com:2638?ServiceName=LPSCSYBP01_IQPNPD";

        // Connect to Sybase Database
        Connection con = DriverManager.getConnection(dburl,"NPDREPORTMANAGER_PROD","npd11pnpdreportmanager_prod");
//        Connection con = DriverManager.getConnection(dburl,"guddin","qap_guddin");
        Statement statement = con.createStatement();

        // Execute Query
        ResultSet rs = statement.executeQuery(query);

        return rs;

    }


    public ResultSet ConnectOracle(String query) throws Exception{

/*        DriverManager.registerDriver( (Driver)
                Class.forName( "com.sybase.jdbc4.jdbc.SybDriver" ).newInstance() );
        String dburl = "jdbc:sybase:Tds:lpscsybp26.npd.com:2638?ServiceName=LPSCSYBP26_IQPNPD";*/

        String driverClass = "oracle.jdbc.OracleDriver";
//        String dburl = "jdbc:oracle:thin:@crslxpphc-scan.npd.com:1521/SRV_PDK";
        String dburl = "jdbc:oracle:thin:@lpscracdkp-scan.npd.com:1521/SRV_PDK";
        String userName = "QA_User";
        String password = "npd08qa_user";
        Class.forName(driverClass);
        connect = DriverManager.getConnection(dburl,userName,password);


        // Connect to Sybase Database
//        Connection con = DriverManager.getConnection(dburl,userName,password);
        Statement statement = connect.createStatement();

        System.out.println(query);

        // Execute Query
        ResultSet rs = statement.executeQuery(query);

        return rs;

    }

    public void getHiveConnect(){

        String driverName = "org.apache.hive.jdbc.HiveDriver";

        try {
            Class.forName(driverName);
//            Connection con = DriverManager.getConnection("jdbc:hive2:lpwhdpnnd01.npd.com;Port=10000/default", "hdpsysdev", "NhGGZB&L");
            Connection con = DriverManager.getConnection("jdbc:hive2://lpschdnnp04.npd.com:10000/default", "hdpsysdev", "NhGGZB&L");
            Statement statement = con.createStatement();
            // We use Sybase specific select getdate() query to return date
//            ResultSet rs = statement.executeQuery("Select * from prod_bsc_pos_usw_c48836_ps2354_bre000_pfc00006_spr000_pfs00014.fact_posdata Where upc_codesuppressednc = '9780735219090'");
            ResultSet rs = statement.executeQuery("Select * from prod_bsc_pos_usw_c50405_ps2354_bre000_pfc00007_spr000_pfs00014.m_dma");

            if (rs.next()) {
//            Date currentDate = rs.getDate(1); // get first column returned
                String dbval = rs.getString(1);
                System.out.println(dbval);
            }



            rs.close();
            statement.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

    }




    public void getHiveConnect_NAV(){

        String driverName = "org.apache.hive.jdbc.HiveDriver";

        try {
            Class.forName(driverName);
//            Connection con = DriverManager.getConnection("jdbc:hive2:lpwhdpnnd01.npd.com;Port=10000/default", "hdpsysdev", "NhGGZB&L");
            Connection con = DriverManager.getConnection("jdbc:hive2://lpwhdpnnd01.npd.com:10000/Hive_Regular_QA", "hive", "hive");
            Statement statement = con.createStatement();
            // We use Sybase specific select getdate() query to return date
            ResultSet rs = statement.executeQuery("Select * from qa_closeout.pos_npditems");

            if (rs.next()) {
//            Date currentDate = rs.getDate(1); // get first column returned
                String dbval = rs.getString(1);
                System.out.println(dbval);
            }



            rs.close();
            statement.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

    }



    public ResultSet ConnectHive(String squery)throws Exception{

        String driverName = "org.apache.hive.jdbc.HiveDriver";
        Class.forName(driverName);
        Connection con = DriverManager.getConnection("jdbc:hive2://lpschdnnp04.npd.com:10000/default", "hdpsysdev", "NhGGZB&L");
        Statement statement = con.createStatement();
//        ResultSet rs = statement.executeQuery("Select * from prod_bsc_pos_usw_c48836_ps2354_bre000_pfc00006_spr000_pfs00014.fact_posdata Where upc_codesuppressednc = '9780735219090'");
        ResultSet rs = statement.executeQuery(squery);

        return rs;


/*
        try {
            Class.forName(driverName);
//            Connection con = DriverManager.getConnection("jdbc:hive2:lpwhdpnnd01.npd.com;Port=10000/default", "hdpsysdev", "NhGGZB&L");
            Connection con = DriverManager.getConnection("jdbc:hive2://lpschdnnp04.npd.com:10000/default", "hdpsysdev", "NhGGZB&L");
            Statement statement = con.createStatement();
            // We use Sybase specific select getdate() query to return date
            rs = statement.executeQuery("Select * from prod_bsc_pos_usw_c48836_ps2354_bre000_pfc00006_spr000_pfs00014.fact_posdata Where upc_codesuppressednc = '9780735219090'");

            if (rs.next()) {
//            Date currentDate = rs.getDate(1); // get first column returned
                String dbval = rs.getString(1);
                System.out.println(dbval);
            }


*/
/*
            rs.close();
            statement.close();
            con.close();
*//*



        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        return rs;
*/


    }

    public void connectToMariaDB() throws Exception {
        String driverName = "org.mariadb.jdbc.Driver";
        Class.forName(driverName);
//        Connection con = DriverManager.getConnection("jdbc:db-bksesd01.mariadb.database.azure.com", "bksdbadmin@db-bksesd01", "YDWUz3BRG3nYiUuhk9Im");
//        Connection con = DriverManager.getConnection("db-bksesd01.mariadb.database.azure.com", "bksdbadmin@db-bksesd01", "YDWUz3BRG3nYiUuhk9Im");
//        Connection con = DriverManager.getConnection("jdbc:mariadb://db-bksesd01.mariadb.database.azure.com?user=bksdbadmin@db-bksesd01&password=YDWUz3BRG3nYiUuhk9Im"
//                + "&useSSL=true&trustServerCertificate=true");

/*        //DEV
        Connection con = DriverManager.getConnection("jdbc:mariadb://mariadb-dev-bks.npd.com?user=dbadminbks@mariadb-dev-bks&password=m4%cb!9pR9+*KbAh"
                + "&useSSL=true&trustServerCertificate=true");*/
        //DEV
        Connection con = DriverManager.getConnection("jdbc:mariadb://mariadb-prod-bks.npd.com?user=dbadminbks@mariadb-prod-bks&password=m4%cb!9pR9+*KbAh"
                + "&useSSL=true&trustServerCertificate=true");


        Statement statement = con.createStatement();
//        ResultSet rs = statement.executeQuery("Select * from bookscan.preferences");
        ResultSet rs = statement.executeQuery("SELECT Id,UserId,Company,Rating,Comments,FeedbackDate FROM bookscan.feedbacks Where id>50");
//        ResultSet rs = statement.executeQuery(squery);

        if (rs.next()) {
//            Date currentDate = rs.getDate(1); // get first column returned
            String dbval1 = rs.getString(1);
            String dbval2 = rs.getString(2);
            String dbval3 = rs.getString(3);
            String dbval4 = rs.getString(4);
            String dbval5 = rs.getString(5);
            System.out.println(dbval1);
            System.out.println(dbval2);
            System.out.println(dbval3);
            System.out.println(dbval4);
            System.out.println(dbval5);
        }


    }


    public void UserFeedBackBookScan() throws Exception {

        CommonApi commonApi = new CommonApi();

        String driverName = "org.mariadb.jdbc.Driver";
        Class.forName(driverName);
//        Connection con = DriverManager.getConnection("jdbc:db-bksesd01.mariadb.database.azure.com", "bksdbadmin@db-bksesd01", "YDWUz3BRG3nYiUuhk9Im");
//        Connection con = DriverManager.getConnection("db-bksesd01.mariadb.database.azure.com", "bksdbadmin@db-bksesd01", "YDWUz3BRG3nYiUuhk9Im");
//        Connection con = DriverManager.getConnection("jdbc:mariadb://db-bksesd01.mariadb.database.azure.com?user=bksdbadmin@db-bksesd01&password=YDWUz3BRG3nYiUuhk9Im"
//                + "&useSSL=true&trustServerCertificate=true");

/*        //DEV
        Connection con = DriverManager.getConnection("jdbc:mariadb://mariadb-dev-bks.npd.com?user=dbadminbks@mariadb-dev-bks&password=m4%cb!9pR9+*KbAh"
                + "&useSSL=true&trustServerCertificate=true");*/
        //DEV
        Connection con = DriverManager.getConnection("jdbc:mariadb://mariadb-prod-bks.npd.com?user=dbadminbks@mariadb-prod-bks&password=m4%cb!9pR9+*KbAh"
                + "&useSSL=true&trustServerCertificate=true");


        Statement statement = con.createStatement();
//        ResultSet rs = statement.executeQuery("Select * from bookscan.preferences");
//        ResultSet rs = statement.executeQuery("SELECT Id,UserId,Company,Rating,Comments,FeedbackDate FROM bookscan.feedbacks Where id>50");
//        ResultSet rs = statement.executeQuery(squery);

 /*       if (rs.next()) {
//            Date currentDate = rs.getDate(1); // get first column returned
            String dbval1 = rs.getString(1);
            String dbval2 = rs.getString(2);
            String dbval3 = rs.getString(3);
            String dbval4 = rs.getString(4);
            String dbval5 = rs.getString(5);
            System.out.println(dbval1);
            System.out.println(dbval2);
            System.out.println(dbval3);
            System.out.println(dbval4);
            System.out.println(dbval5);
        }*/

        //Excel Output
        String outputFile = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\Feedback\\LatestFeedBack.xlsx";
        Workbook wb_output = new XSSFWorkbook();
        Sheet ws_output = wb_output.createSheet("LATEST_FEEDBACK");
        Sheet ws_All = wb_output.createSheet("ALL_FEEDBACK");
//        Sheet ws_Push = wb_output.createSheet("PUSH_FEEDBACK");

        //Open Excel User Map File
        String UserMap = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\Feedback\\General Feedback.xlsx";
        FileInputStream fis =new FileInputStream(UserMap);
        Workbook wb_userMap = WorkbookFactory.create(fis);
        Sheet ws_userMap = wb_userMap.getSheet("UserMap");

        //Open PrevFeedBeck Report
        String PrevFeedBack = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\Feedback\\PrevFeedback\\PrevFeedBack.xlsx";
        FileInputStream f_prev = new FileInputStream(PrevFeedBack);
        Workbook wb_prev = WorkbookFactory.create(f_prev);
        Sheet ws_prev = wb_prev.getSheetAt(1);

        int LastRow_Prev = ws_prev.getLastRowNum();
        int int_HighestID;

        if(LastRow_Prev==0){
            int_HighestID = 0;
        }else{
            String Last_HighestID = ws_prev.getRow(LastRow_Prev).getCell(0).toString();
            int_HighestID = Integer.parseInt(Last_HighestID);
        }

        //Get Highest ID in Database
        ResultSet rs_highID = statement.executeQuery("Select Max(Id) from bookscan.feedbacks");
        boolean IsNewFeedBack = false;
        String high_ID = "";
        if(rs_highID.next()){
            high_ID = rs_highID.getString(1);
        }


        if(Integer.parseInt(high_ID)==int_HighestID){
            System.out.println("There are no new Feedback");
        }else{
            IsNewFeedBack=true;
        }

//        if(IsNewFeedBack==true){


            ResultSet rs = statement.executeQuery("SELECT Id,UserId,Company,Rating,Comments,FeedbackDate FROM bookscan.feedbacks Where id>" + int_HighestID);
            ResultSet re_all = statement.executeQuery("SELECT Id,UserId,Company,Rating,Comments,FeedbackDate FROM bookscan.feedbacks Order By Id Asc");
            ResultSet rs_push = statement.executeQuery("Select FeedBackCampaignId,UserIdentifier,Rating,Comments,FeedbackDate from bookscan.feedbacks_campaign_users");
            ResultSet rs_newfeedback = statement.executeQuery("SELECT Count(*) FROM bookscan.feedbacks Where id>" + int_HighestID);

            while(rs_newfeedback.next()){
                String dbval1 = rs_newfeedback.getString(1);

                if(dbval1.equals("0")){
                    IsNewFeedBack = false;
                }
            }


            if(IsNewFeedBack==false){
                Row row = ws_output.createRow(0);

                row.createCell(0).setCellValue("No New Feedback Received...");


            }else{
                EnterFeedBackData(rs,ws_output,ws_userMap,wb_output);
            }


            //Enter All Generic Feedback
            EnterFeedBackData(re_all,ws_All,ws_userMap,wb_output);

//            //Enter PushFeedback
//            EnterPushFeedback(rs_push,ws_Push,ws_userMap,wb_output);


            //Save OutputFile
            FileOutputStream fileout = new FileOutputStream(outputFile);
            wb_output.write(fileout);
            fileout.close();

            //Copy File
            File source = new File(outputFile);
            File dest = new File(PrevFeedBack);

            FileUtils.copyFile(source, dest);

            String EmailList = "books@npd.com,David.Walter@npd.com,Patrick.Walker@npd.com,Anne.Gold@npd.com,Anita.Chen@npd.com,Christopher.Biggin@npd.com,Aldrich.Ang@npd.com,Exequiel.Sandoval@npd.com,Karthi.Nataraj@npd.com,Dan.Rizzi@npd.com,Hal.Danziger@npd.com,gazi.uddin@npd.com,Alert Notification - PQM Automation Team <dcf8a89f.npd.com@amer.teams.ms>, General - BookScan <eac457d1.npd.com@amer.teams.ms>";
//        String EmailList = "gazi.uddin@npd.com,Alert Notification - PQM Automation Team <dcf8a89f.npd.com@amer.teams.ms>";
//            String EmailList = "gazi.uddin@npd.com";

            if(IsNewFeedBack == false){
                commonApi.sendMail(EmailList,"BookScan User Feedback","<HTML><Body>No New Feedback Received...<Br><Br>Best Regards,<Br>BookScan Team</Body></HTML>","BookScan@Notification.com");
            }else{
                commonApi.sendMailWithExcelAttachment(EmailList,"BookScan User Feedback","<HTML><Body>Attached spreadsheet includes below lists - <Br><Br>Latest General Feedback <Br>All General Feedback<Br><Br>Best Regards,<Br>BookScan Team</Body></HTML>",outputFile,"BookScan@Notification.com","FeedBack.xlsx");
            }

        }

//        else{
//
//            System.out.println("No New FeedBack");
//            commonApi.sendMail("gazi.uddin@npd.com","BookScan User Feedback","<HTML><Body>No New Feedback Received...</Body></HTML>","BookScan@Notification.com");
//        }


//    }

    //Define Columns of output file
    public int getColumnIdx(String colName){

        switch (colName){

            case "ID":
                return 0;
            case "Email":
                return 1;
            case "Name":
                return 2;
            case "UserID":
                return 3;
            case "Company":
                return 4;
            case "Rating":
                return 5;
            case "Comments":
                return 6;
            case "FeedbackDate":
                return 7;

            default:
                return 999;
        }

//        int col_id = 0;
//        int col_Email = 1;
//        int col_Name = 2;
//        int col_Userid = 3;
//        int col_Company = 4;
//        int col_Rating = 5;
//        int col_Comments = 6;
//        int col_FeedbackDate = 7;

    }


    //This method will enter feedback data into an Excel Sheet
    public void EnterFeedBackData(ResultSet relset,Sheet sheet,Sheet ws_MAP,Workbook wb) throws Exception {

        int col_id = getColumnIdx("ID");
        int col_Email = getColumnIdx("Email");
        int col_Name = getColumnIdx("Name");
        int col_Userid = getColumnIdx("UserID");
        int col_Company = getColumnIdx("Company");
        int col_Rating = getColumnIdx("Rating");
        int col_Comments = getColumnIdx("Comments");
        int col_FeedbackDate = getColumnIdx("FeedbackDate");


        Row row = sheet.createRow(0);

        CreateHeaders(row,col_id,"ID", wb);
        CreateHeaders(row,col_Email,"Email", (Workbook) wb);
        CreateHeaders(row,col_Name,"Name", (Workbook) wb);
        CreateHeaders(row,col_Userid,"UserID", (Workbook) wb);
        CreateHeaders(row,col_Company,"Company", (Workbook) wb);
        CreateHeaders(row,col_Rating,"Rating", (Workbook) wb);
        CreateHeaders(row,col_Comments,"Comments", (Workbook) wb);
        CreateHeaders(row,col_FeedbackDate,"Feedback TimeStamp", (Workbook) wb);


        int rowidx = 0;
        String UserInfo = "";
        String Name = "";


        List<String> dataList = new ArrayList<>();
        while(relset.next()){
            String dbval1 = relset.getString(1);
            String dbval2 = relset.getString(2);
            String dbval3 = relset.getString(3);
            String dbval4 = relset.getString(4);
            String dbval5 = relset.getString(5);
            String dbval6 = relset.getString(6);

            String uInfo = getUserInfo(dbval2,ws_MAP);
            String[] splitUserInfo = uInfo.split("[|]");

            rowidx++;


            row = sheet.createRow(rowidx);

            row.createCell(col_id).setCellValue(dbval1);
            row.createCell(col_Userid).setCellValue(dbval2);
            row.createCell(col_Company).setCellValue(dbval3);
            row.createCell(col_Rating).setCellValue(dbval4);
            row.createCell(col_Comments).setCellValue(dbval5);
            row.createCell(col_FeedbackDate).setCellValue(dbval6);


            Name = splitUserInfo[0] + " " + splitUserInfo[1];
            row.createCell(col_Name).setCellValue(Name);
            row.createCell(col_Email).setCellValue(splitUserInfo[2]);

        }

        //Format Output file
        FormatFeedbackFile(sheet);


    }


    //Enter Push Feedback
    public void EnterPushFeedback(ResultSet relset,Sheet sheet,Sheet ws_MAP,Workbook wb) throws Exception {

        int col_FeedID = 0;
        int col_UserID = 1;
        int col_Name = 2;
        int col_Company = 3;
        int col_Email = 4;
        int col_Rating = 5;
        int col_Comments = 6;
        int col_FeedbackDate = 7;

        Row row = sheet.createRow(0);

        CreateHeaders(row,col_FeedID,"FeedBackID", wb);
        CreateHeaders(row,col_UserID,"UserID", wb);
        CreateHeaders(row,col_Name,"Name", wb);
        CreateHeaders(row,col_Email,"Email", wb);
        CreateHeaders(row,col_Company,"Company", wb);
        CreateHeaders(row,col_Rating,"Rating", wb);
        CreateHeaders(row,col_Comments,"Comments", wb);
        CreateHeaders(row,col_FeedbackDate,"Feedback TimeStamp", wb);

        int rowidx = 0;
        String UserInfo = "";
        String Name = "";
        String Company = "";

        List<String> dataList = new ArrayList<>();
        while(relset.next()){
            String dbval1 = relset.getString(1);
            String dbval2 = relset.getString(2);
            String dbval3 = relset.getString(3);
            String dbval4 = relset.getString(4);
            String dbval5 = relset.getString(5);

            String uInfo = getUserInfo(dbval2,ws_MAP);
            String[] splitUserInfo = uInfo.split("[|]");



            rowidx++;


            row = sheet.createRow(rowidx);

            row.createCell(col_FeedID).setCellValue(dbval1);
            row.createCell(col_UserID).setCellValue(dbval2);
            row.createCell(col_Rating).setCellValue(dbval3);
            row.createCell(col_Comments).setCellValue(dbval4);
            row.createCell(col_FeedbackDate).setCellValue(dbval5);


            Name = splitUserInfo[0] + " " + splitUserInfo[1];

            try{
                row.createCell(col_Name).setCellValue(Name);
                row.createCell(col_Email).setCellValue(splitUserInfo[2]);
                row.createCell(col_Company).setCellValue(splitUserInfo[3]);

            }catch (Exception e){
                continue;
            }



        }

        //Format Output file
        FormatFeedbackFile(sheet);


    }


    public String getUserInfo(String UDID,Sheet sheet){

        int TotalRow = sheet.getLastRowNum();
        boolean UserFound = false;
        String F_Name = "";
        String L_Name = "";
        String U_Email = "";
        String U_Company = "";

        for(int idx_user=0;idx_user<=TotalRow;idx_user++){

            if(sheet.getRow(idx_user).getCell(0).toString().equals(UDID)){

                F_Name = sheet.getRow(idx_user).getCell(4).toString();
                L_Name = sheet.getRow(idx_user).getCell(3).toString();
                U_Email = sheet.getRow(idx_user).getCell(2).toString();
                U_Company = sheet.getRow(idx_user).getCell(1).toString();
                UserFound = true;
                break;

            }

        }

        if(UserFound==true){
            return F_Name + "|" + L_Name + "|" + U_Email + "|" + U_Company;
        }else{
            return "-|-|-";
        }



    }





    //Format Output File
    public void FormatFeedbackFile(Sheet sheet){

        //Header Color


        //Auto fit Columns
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.setColumnWidth(6,10000);
        sheet.autoSizeColumn(7);




    }

    //Cell Style
    public void CellStyle(Workbook wb,Cell cell){

        CellStyle backgroundStyle = wb.createCellStyle();

        backgroundStyle.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        backgroundStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        backgroundStyle.setBorderBottom(CellStyle.BORDER_THIN);
        backgroundStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        backgroundStyle.setBorderLeft(CellStyle.BORDER_THIN);
        backgroundStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        backgroundStyle.setBorderRight(CellStyle.BORDER_THIN);
        backgroundStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        backgroundStyle.setBorderTop(CellStyle.BORDER_THIN);
        backgroundStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        backgroundStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        backgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(backgroundStyle);

    }


    public void CreateHeaders(Row row,int idx_col,String colName,Workbook wb){

        Cell cell_head1 = row.createCell(idx_col);
        cell_head1.setCellValue(colName);
        CellStyle(wb,cell_head1);
    }




}
