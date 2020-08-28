package Test_UI_Bookscan;

import BaseClass.CommonApi;
import BaseClass.DBUtil;
import PageObjects.page_AdvanceSearch;
import PageObjects.page_Login;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TC_DataIntegrity extends CommonApi{


    CommonApi commonApi = new CommonApi();
    private static page_AdvanceSearch advancesearch = PageFactory.initElements(driver, page_AdvanceSearch.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Bookscan_AdvanceSearch";
    private String htmlreporturl;
    private String htmloutput;
    private String sanityOutput = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
    private String archivehtmlOutput;

    @BeforeSuite
    public void SetUp() throws IOException {
//        cleanTextFiles(logLocal);
//        genericAPI.cleanDirectory(ScreenShootLocal);

        //Overall ExecTime
        ScreenshotRequired = true;
        sOverallStartTime = System.currentTimeMillis();
        System.out.println("Overall Start Time : " + sOverallStartTime);

        htmlreporturl = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\Results\\HTML Reports\\ExtendReport.html";
        DeleteFiles(htmlReportLocal);
        createSanityOutput(sanityOutput);
//        File file_output = new File("C:\\Program Files\\APP_DecisionKey_Reports\\MobileSanityOutput.xlsx");
        File file_output = new File(sanityOutput);
        try {
            FileInputStream file = new FileInputStream(file_output);
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet_wb = workbook.getSheetAt(0);
            sheet_wb.getRow(0).createCell(3).setCellValue("Elapsed Time");
            FileOutputStream fos = new FileOutputStream(file_output);
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


 /*   @BeforeTest
    public void launchandnavigateToApp(){
        //Launch Browser and Navigate
        try{
            launchbrowser();
            Thread.sleep(3000);
            page_Login page_login = new page_Login();
            page_login.Login("Bob Backline","Baseball1");

        }catch(Exception e){
            e.printStackTrace();

        }

    }*/


    @Test(priority = 1)
    public void UI_CheckDataIntegrity_Bookscan() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        launchbrowser();
        Thread.sleep(3000);
        page_Login page_login = new page_Login();
        page_login.Login("Bob Backline","Baseball1");

        Tests.add(testName);
        advancesearch.CheckDataIntegrity("US BookScan POS - Admin","DataIntegrity_BS");

        driver.close();


    }

    @Test(priority = 2)
    public void UI_CheckDataIntegrity_Walmart() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        launchbrowser();
        Thread.sleep(3000);
        page_Login page_login = new page_Login();
        page_login.Login("Bob Backline/Scholastic, inc","Baseball1");

        Tests.add(testName);
        advancesearch.CheckDataIntegrity("US BookScan POS - Dashboard - Walmart","DataIntegrity_Walmart");

        driver.close();


    }



    @Test(priority = 3)
    public void TestSybase() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        launchbrowser();
        Thread.sleep(3000);
        page_Login page_login = new page_Login();
        page_login.Login("Bob Backline/Scholastic, inc","Baseball1");

        Tests.add(testName);
        DBUtil dbUtil = new DBUtil();

        dbUtil.ConnectSybase("SELECT Round(unitssold_rtd,0) FROM bsc_mkt_usw_d2.w_aggr_ytd WHERE upc_code='9780064430104' AND ppWeek = 2613");

        driver.close();


    }



    @AfterTest
    public void moveFiles() throws Exception {

//        driver.close();

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
