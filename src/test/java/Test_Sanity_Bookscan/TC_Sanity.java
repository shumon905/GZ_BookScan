package Test_Sanity_Bookscan;

import BaseClass.CommonApi;
import PageObjects.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TC_Sanity extends CommonApi{


    CommonApi commonApi = new CommonApi();
    private static page_Login login = PageFactory.initElements(driver, page_Login.class);
    private static page_BasicSearch basicsrch = PageFactory.initElements(driver, page_BasicSearch.class);
    private static page_AdvanceSearch advancesearch = PageFactory.initElements(driver, page_AdvanceSearch.class);
    private static page_Bestsellers bestsellers = PageFactory.initElements(driver, page_Bestsellers.class);
    private static page_Collections collections = PageFactory.initElements(driver, page_Collections.class);
    private static page_LandingPage landingPage = PageFactory.initElements(driver, page_LandingPage.class);

    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Sanity";
    private String htmlreporturl;
    private String htmloutput;
    private String sanityOutput = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
    private String archivehtmlOutput;




    @BeforeSuite
    public void SetUp() throws IOException {

//        cleanTextFiles(logLocal);
//        genericAPI.cleanDirectory(ScreenShootLocal);
        ScreenshotRequired = true;
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

            launchbrowser();
//            login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Test (priority=1)
    public void UI_Sanity_LandingPage_1_CheckVersion() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        landingPage.CheckAppVersion();
    }


    @Test(priority=2)
    public void UI_Sanity_Login_2_SuccessfullLogin() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);
    }



    @Test(priority=3)
    public void UI_Sanity_BasicSearch_3_getResultSearchAll() throws Exception {
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchbyKey("Steve","Search All");
    }


    @Test(priority = 4)
    public void UI_Sanity_AdvanceSearch_4_Author() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckAdvanceSearchFunctionality("Author","Stephen","Text");
    }



/*    @Test(priority = 5)
    public void UI_Sanity_Data_5_CheckDataIntegrity() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckDataIntegrity("US BookScan","DataIntegrity_BS");
    }*/


    @Test(priority=6)
    public void UI_Sanity_Bestseller_6_CreateBSL() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        bestsellers.CreateAndDeleteBestsellerList("Sanity Test Bestseller List");
    }


    @Test(priority=7)
    public void UI_Sanity_Collection_7_CreateCollection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        collections.CreateCollections();
    }


    @Test(priority = 8)
    public void UI_Sanity_BasicSearch_8_CheckSTTab_SalesAndRankHistory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckSingleTitleSelectionTab("Sales & Rank History");
    }


    @Test(priority = 9)
    public void UI_Sanity_BasicSearch_9_CheckSTTab_Metadata() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckSingleTitleSelectionTab("Metadata");
    }



    @Test(priority = 10)
    public void UI_Sanity_BasicSearch_10_CheckSTTab_AuthorHistory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckSingleTitleSelectionTab("Author History");
    }


    @Test(priority = 11)
    public void UI_Sanity_BasicSearch_11_CheckSTTab_RegionalSales() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckSingleTitleSelectionTab("Regional Sales");
    }


    @Test (priority=12)
    public void UI_Sanity_LandingPage_12_NavigateToHelpCenterScreen() throws Exception {

        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        landingPage.NavigateToHelpCenter();

    }



    @AfterTest
    public void moveFiles() throws Exception {

        //Close Browser
        driver.close();
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
