package Test_Api_Bookscan;

import BaseClass.CommonApi;
import PageObjects.page_BasicSearch;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import MethodsApi.BasicSearch_api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TC_BasicSearchResults_api extends CommonApi {

    CommonApi commonApi = new CommonApi();
    private static page_BasicSearch basicsrch = PageFactory.initElements(driver, page_BasicSearch.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Bookscan_Basic Search Api";
    private String htmlreporturl;
    private String sanityOutput = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
    private String htmloutput;
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

            //Get Token
            Api_Token = getApiTokenString();


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test(priority = 1)
    public void Reg_Api_1_BasicSearch_SearchAll() throws IOException, InvalidFormatException {
        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
            bsapi.searchCount("Search All",Api_Token);

        }catch(RuntimeException e){
            throw(e);
        }

    }


    @Test(priority = 2)
    public void Reg_Api_2_BasicSearch_ISBN() throws IOException, InvalidFormatException {
        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
            bsapi.searchCount("ISBN",Api_Token);

        }catch(RuntimeException e){
            throw(e);
        }

    }


    @Test(priority = 3)
    public void Reg_Api_3_BasicSearch_Author() throws IOException, InvalidFormatException {
        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
            bsapi.searchCount("Author",Api_Token);

        }catch(RuntimeException e){
            throw(e);
        }

    }

    @Test(priority = 4)
    public void Reg_Api_4_BasicSearch_Title() throws IOException, InvalidFormatException {
        ScreenshotRequired = false;
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
            bsapi.searchCount("Title",Api_Token);

        }catch(RuntimeException e){
            throw(e);
        }

    }


/*
    @Test
    public void getSearchApi() throws IOException, InvalidFormatException {
        ScreenshotRequired = false;
        try{
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            BasicSearch_api bsapi = new BasicSearch_api();
//            bsapi.searchCount("Title");
            bsapi.testBasicSearchApi();

        }catch(RuntimeException e){
            throw(e);
        }

    }
*/



    @AfterTest
    public void moveFiles() throws Exception {

/*        sendSanityOutput("DKMobileSanity", "PROD", "PROD".toUpperCase(), testName, "Bookscan Validation", sanityOutput,htmlreporturl,"Bookscan_Test_Automation");
        System.out.println("Total Tests : " + Tests.size());

        for (int i = 1; i <= Tests.size(); i++){
            copyFiles(htmlReportFile,"\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\Results\\HTML Reports\\ExtentReport.html");
            copyDirectory(ScreenShootLocal,BSresourcefile_parent + "\\Results\\Screenshots");

        }*/

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
