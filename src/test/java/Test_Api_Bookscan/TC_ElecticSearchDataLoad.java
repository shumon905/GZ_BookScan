package Test_Api_Bookscan;

import BaseClass.CommonApi;
import MethodsApi.BasicSearch_api;
import MethodsApi.ElasticSearchLoad_api;
import PageObjects.page_BasicSearch;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.ws.rs.GET;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TC_ElecticSearchDataLoad extends CommonApi {

    private static page_BasicSearch basicsrch = PageFactory.initElements(driver, page_BasicSearch.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Bookscan_DataLoadinAdvanceSearch";
    private String htmlreporturl;
    private String htmloutput;
    private String sanityOutput = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
    private String archivehtmlOutput;



    @BeforeSuite
    public void SetUp() throws IOException {
//        cleanTextFiles(logLocal);
//        genericAPI.cleanDirectory(ScreenShootLocal);

        htmlreporturl = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\Results\\HTML Reports\\ExtentReport.html";
        DeleteFiles(htmlReportLocal);
        createSanityOutput(sanityOutput);
        File file_output = new File(sanityOutput);
        try {
            FileInputStream file = new FileInputStream(file_output);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet_wb = workbook.getSheetAt(0);
            sheet_wb.getRow(0).createCell(3).setCellValue("Elapsed Time");
            FileOutputStream fos = new FileOutputStream(file_output);
            workbook.write(fos);
            fos.close();

            //Generate Token
//            Api_Token = getApiTokenString();


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test(priority = 1)
    public void ElesticSearchLoad_Bisacs1_BNN() throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("bisacs");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"bisaclevel1s");
            elasticApi.DataLoadElasticSearch_Updated("bisaclevel1s",Api_Token,"BNN");

            //elasticApi.getLoadRecordCountFromHive("formats");

        }catch(Exception e){
            throw (e);
        }
    }


    @Test(priority = 2)
    public void ElesticSearchLoad_Bisacs2_BNN() throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("bisacs");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"bisaclevel2s");
            elasticApi.DataLoadElasticSearch_Updated("bisaclevel2s",Api_Token,"BNN");

            //elasticApi.getLoadRecordCountFromHive("formats");

        }catch(Exception e){
            throw (e);
        }
    }


    @Test(priority = 3)
    public void ElesticSearchLoad_Formats_BNN() throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("formats");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"formats");
            elasticApi.DataLoadElasticSearch_Updated("formats",Api_Token,"BNN");

        }catch(RuntimeException e){
            throw(e);
        }
    }


    @Test(priority = 4)
    public void ElesticSearchLoad_Vintages_BNN()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("vintages");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"vintages");
            elasticApi.DataLoadElasticSearch_Updated("vintages",Api_Token,"BNN");

        }catch(RuntimeException e){
            throw(e);
        }
    }


/*    @Test(priority = 5)
    public void ElesticSearchLoad_Rankaggregates_BNN()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("rankaggregates");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"rankaggregates");
            elasticApi.DataLoadElasticSearch_Updated("rankaggregates",Api_Token,"BNN");

        }catch(RuntimeException e){
            throw(e);
        }
    }*/



/*    @Test(priority = 6)
    public void ElesticSearchLoad_Rankcategorygroups_BNN()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("rankcategorygroups");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"rankcategorygroups");
            elasticApi.DataLoadElasticSearch_Updated("rankcategorygroups",Api_Token,"BNN");

        }catch(RuntimeException e){
            throw(e);
        }
    }*/


    @Test(priority = 7)
    public void ElesticSearchLoad_Rankcategories_BNN()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("rankcategories");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"rankcategories");
            elasticApi.DataLoadElasticSearch_Updated("categories",Api_Token,"BNN");

        }catch(RuntimeException e){
            throw(e);
        }
    }



    @Test(priority = 8)
    public void ElesticSearchLoad_Ranksubjectgroups_BNN()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("ranksubjectgroups");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"ranksubjectgroups");
            elasticApi.DataLoadElasticSearch_Updated("subjectgroups",Api_Token,"BNN");

        }catch(RuntimeException e){
            throw(e);
        }
    }

/*
    @Test
    public void getTimeStamp(){
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        getCurrentDateTime();
    }*/



/*    @Test
    public void testgetDBValues(){

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();
        elasticApi.getRecordFromDatabase();


    }*/


/*    @Test
    public void testgetSybaseDBValues() throws Exception {

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();
        DBUtil scon = new DBUtil();
//        scon.getSybaseCon();
        ResultSet resultSet = scon.ConnectSybase("SELECT Count(*) FROM bsc_mkt_usw_d2.m_format");

        if(resultSet.next()){
            System.out.println(resultSet.getString(1));
        }


    }*/

/*    @Test
    public void TestGetApiData(){
        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
            elasticApi.getSecuredApiData();

        }catch(RuntimeException e){
            throw(e);
        }


    }*/



    @Test(priority = 9)
    public void ElesticSearchLoad_Bisacs1_Bookscan() throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("bisacs");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"bisaclevel1s");
            elasticApi.DataLoadElasticSearch_Updated("bisaclevel1s",Api_Token,"Bookscan");

            //elasticApi.getLoadRecordCountFromHive("formats");

        }catch(Exception e){
            throw (e);
        }
    }


    @Test(priority = 10)
    public void ElesticSearchLoad_Bisacs2_Bookscan() throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("bisacs");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"bisaclevel2s");
            elasticApi.DataLoadElasticSearch_Updated("bisaclevel2s",Api_Token,"Bookscan");

            //elasticApi.getLoadRecordCountFromHive("formats");

        }catch(Exception e){
            throw (e);
        }
    }


    @Test(priority = 11)
    public void ElesticSearchLoad_Formats_Bookscan() throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("formats");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"formats");
            elasticApi.DataLoadElasticSearch_Updated("formats",Api_Token,"Bookscan");

        }catch(RuntimeException e){
            throw(e);
        }
    }


    @Test(priority = 12)
    public void ElesticSearchLoad_Vintages_Bookscan()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("vintages");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"vintages");
            elasticApi.DataLoadElasticSearch_Updated("vintages",Api_Token,"Bookscan");

        }catch(RuntimeException e){
            throw(e);
        }
    }


/*    @Test(priority = 13)
    public void ElesticSearchLoad_Rankaggregates_Bookscan()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("rankaggregates");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"rankaggregates");
            elasticApi.DataLoadElasticSearch_Updated("aggregates",Api_Token,"Bookscan");

        }catch(RuntimeException e){
            throw(e);
        }
    }*/



    @Test(priority = 14)
    public void ElesticSearchLoad_Rankcategorygroups_Bookscan()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("rankcategorygroups");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"rankcategorygroups");
            elasticApi.DataLoadElasticSearch_Updated("categorygroups",Api_Token,"Bookscan");

        }catch(RuntimeException e){
            throw(e);
        }
    }


    @Test(priority = 15)
    public void ElesticSearchLoad_Rankcategories_Bookscan()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("rankcategories");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"rankcategories");
            elasticApi.DataLoadElasticSearch_Updated("categories",Api_Token,"Bookscan");

        }catch(RuntimeException e){
            throw(e);
        }
    }



    @Test(priority = 16)
    public void ElesticSearchLoad_Ranksubjectgroups_Bookscan()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("ranksubjectgroups");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"ranksubjectgroups");
            elasticApi.DataLoadElasticSearch_Updated("subjectgroups",Api_Token,"Bookscan");

        }catch(RuntimeException e){
            throw(e);
        }
    }



    @Test(priority = 17)
    public void ElesticSearchLoad_Bisacs1_Pubtrack() throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("bisacs");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"bisaclevel1s");
            elasticApi.DataLoadElasticSearch_Updated("bisaclevel1s",Api_Token,"Pubtrack");

            //elasticApi.getLoadRecordCountFromHive("formats");

        }catch(Exception e){
            throw (e);
        }
    }


    @Test(priority = 18)
    public void ElesticSearchLoad_Bisacs2_Pubtrack() throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("bisacs");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"bisaclevel2s");
            elasticApi.DataLoadElasticSearch_Updated("bisaclevel2s",Api_Token,"Pubtrack");

            //elasticApi.getLoadRecordCountFromHive("formats");

        }catch(Exception e){
            throw (e);
        }
    }


    @Test(priority = 19)
    public void ElesticSearchLoad_Formats_Pubtrack() throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("formats");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"formats");
            elasticApi.DataLoadElasticSearch_Updated("formats",Api_Token,"Pubtrack");

        }catch(RuntimeException e){
            throw(e);
        }
    }


    @Test(priority = 20)
    public void ElesticSearchLoad_Vintages_Pubtrack()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("vintages");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"vintages");
            elasticApi.DataLoadElasticSearch_Updated("vintages",Api_Token,"Pubtrack");

        }catch(RuntimeException e){
            throw(e);
        }
    }


/*    @Test(priority = 21)
    public void ElesticSearchLoad_Rankaggregates_Pubtrack()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("rankaggregates");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"rankaggregates");
            elasticApi.DataLoadElasticSearch_Updated("rankaggregates",Api_Token,"Pubtrack");

        }catch(RuntimeException e){
            throw(e);
        }
    }*/



    @Test(priority = 22)
    public void ElesticSearchLoad_Rankcategorygroups_Pubtrack()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("rankcategorygroups");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"rankcategorygroups");
            elasticApi.DataLoadElasticSearch_Updated("categorygroups",Api_Token,"Pubtrack");

        }catch(RuntimeException e){
            throw(e);
        }
    }


    @Test(priority = 23)
    public void ElesticSearchLoad_Rankcategories_Pubtrack()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("rankcategories");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"rankcategories");
            elasticApi.DataLoadElasticSearch_Updated("categories",Api_Token,"Pubtrack");

        }catch(RuntimeException e){
            throw(e);
        }
    }



    @Test(priority = 24)
    public void ElesticSearchLoad_Ranksubjectgroups_Pubtrack()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            elasticApi.DataLoadinElasticSearch("ranksubjectgroups");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"ranksubjectgroups");
            elasticApi.DataLoadElasticSearch_Updated("subjectgroups",Api_Token,"Pubtrack");

        }catch(RuntimeException e){
            throw(e);
        }
    }



/*    @Test(priority = 25)
    public void AzureInsightTest()throws Exception{

        ElasticSearchLoad_api elasticApi = new ElasticSearchLoad_api();

        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            ElasticSearchLoad_api ele = new ElasticSearchLoad_api();
//            elasticApi.DataLoadinElasticSearch("ranksubjectgroups");
//            elasticApi.DataLoadElasticSearch_Updated(elasticApi.getApiTokenString(),"ranksubjectgroups");
            ele.AzureInsightApi();

        }catch(RuntimeException e){
            throw(e);
        }
    }*/

    @AfterTest
    public void moveFiles() throws Exception {

        CommonApi commonApi = new CommonApi();

        BSresourcefile_parent = BSresourcefile_parent + envName.toUpperCase() + "\\";
        htmloutput = BSresourcefile_parent + "\\Results\\HTML Reports\\" + TestModule +".html";
        archivehtmlOutput = BSresourcefile_parent + "\\Results\\HTML Reports\\Archive\\" + TestModule + commonApi.GetCurrentTimeStamp() +".html";

        sendSanityOutput(TestModule, envName, envName.toUpperCase(), testName, TestModule, sanityOutput,archivehtmlOutput,"Bookscan_Test_Automation");
        System.out.println("Total Tests : " + Tests.size());
//        System.out.println(htmlReportFile);
//        System.out.println(dkresourcefile_parent + "\\" + getEnv() + "\\Results\\HTML Reports\\Mobile_Sanity_Results.html");

        for (int i = 1; i <= Tests.size(); i++){
            copyFiles(htmlReportFile,htmloutput);
            copyFiles(htmlReportFile,archivehtmlOutput);
            copyDirectory(ScreenShootLocal,BSresourcefile_parent + "\\Results\\Screenshots");

        }

    }




}
