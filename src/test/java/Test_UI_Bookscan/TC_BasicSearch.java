package Test_UI_Bookscan;

import BaseClass.CommonApi;
import PageObjects.page_AdvanceSearch;
import PageObjects.page_BasicSearch;
import PageObjects.page_Login;
import Reporting.ExtentTestManager;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TC_BasicSearch extends CommonApi {

    CommonApi commonApi = new CommonApi();
    private static page_BasicSearch basicsrch = PageFactory.initElements(driver, page_BasicSearch.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Regression_BS_BasicSearch";
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
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @BeforeTest
    public void launchandnavigateToApp(){
        //Launch Browser and Navigate
        try{

            CommonApi commonApi = new CommonApi();
            launchbrowser();
            Thread.sleep(3000);
//            page_Login page_login = new page_Login();
//            page_login.Login("Bob Backline","Baseball1");
        }catch(Exception e){
            e.printStackTrace();

        }

    }
    @Test(priority = 1)
    public void UI_REG_BasicSearch_1_checkpoints() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.basicSearchCheckpoints();
    }


    @Test(priority = 2)
    public void UI_REG_BasicSearch_2_getResultSearchAll() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchbyKey("Steve","Search All");
    }


    @Test(priority = 3)
    public void UI_REG_BasicSearch_3_getResultAuthor() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchbyKey("Steve","Author");
    }

    @Test(priority = 4)
    public void UI_REG_BasicSearch_4_getResultTitle() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchbyKey("The Shining","Title");
    }



    @Test(priority = 5)
    public void UI_REG_BasicSearch_5_getResultISBN() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchbyKey("9780743437493","ISBN");
    }


    @Test(priority = 6)
    public void UI_REG_BasicSearch_6_MultiISBNBasicSearch() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchbyKey("9781982110567 9781982127794","Search All");
    }


    @Test(priority = 7)
    public void UI_REG_BasicSearch_7_ExportBasicSearch() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.exportBasicSearchResults();
    }


    @Test(priority = 8)
    public void UI_REG_BasicSearch_8_CheckTableHeaders() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckTableHeaders();
    }


    @Test(priority = 9)
    public void UI_REG_BasicSearch_9_CheckSTTab_SalesAndRankHistory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckSingleTitleSelectionTab("Sales & Rank History");
    }


    @Test(priority = 10)
    public void UI_REG_BasicSearch_10_CheckSTTab_Metadata() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckSingleTitleSelectionTab("Metadata");
    }



    @Test(priority = 11)
    public void UI_REG_BasicSearch_11_CheckSTTab_AuthorHistory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckSingleTitleSelectionTab("Author History");
    }


    @Test(priority = 12)
    public void UI_REG_BasicSearch_12_CheckSTTab_RegionalSales() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckSingleTitleSelectionTab("Regional Sales");
    }


    @Test(priority = 13)
    public void UI_REG_BasicSearch_13_CheckpointSalesAndRank_Graph() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.HighChartCheckpoint();
    }


    @Test(priority = 14)
    public void UI_REG_BasicSearch_14_SaledRankGraph_dropdownSales() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.SalesRank_Graph_DropDownValues("Sales");
    }


    @Test(priority = 15)
    public void UI_REG_BasicSearch_15_SaledRankGraph_dropdownRanks() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.SalesRank_Graph_DropDownValues("Ranks");
    }

    @Test(priority = 16)
    public void UI_REG_BasicSearch_16_getResultISBNwithDash() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchbyKey("978-0743437493","ISBN");
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
