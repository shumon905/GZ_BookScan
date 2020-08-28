package Test_UI_Bookscan;

import BaseClass.CommonApi;
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
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class TC_AdvanceSearch extends CommonApi{


    CommonApi commonApi = new CommonApi();
    private static page_AdvanceSearch advancesearch = PageFactory.initElements(driver, page_AdvanceSearch.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Regression_BS_AdvanceSearch";
    private String htmlreporturl;
    private String htmloutput;
    private String sanityOutput = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
    private String archivehtmlOutput;
    private Instant start_TIME;
    private Instant end_TIME;

    @BeforeSuite
    public void SetUp() throws IOException {
        start_TIME = Instant.now();
//        cleanTextFiles(logLocal);
//        genericAPI.cleanDirectory(ScreenShootLocal);
        ScreenshotRequired = true;

        //Overall ExecTime
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


    @BeforeTest
    public void launchandnavigateToApp(){
        //Launch Browser and Navigate
        try{
            launchbrowser();
            Thread.sleep(3000);
//            page_Login page_login = new page_Login();
//            page_login.Login("Bob Backline","Baseball1");

        }catch(Exception e){
            e.printStackTrace();

        }

    }


    @Test(priority = 1)
    public void UI_REG_AdvanceSearch_1_CheckPoints() throws Exception {

        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckPointAdvanceSearchElements();

    }

    @Test(priority = 2)
    public void UI_REG_AdvanceSearch_2_Author() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckAdvanceSearchFunctionality("Author","Stephen","Text");
    }


    @Test(priority = 3)
    public void UI_REG_AdvanceSearch_3_Supercategory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckAdvanceSearchFunctionality("Supercategory","Adult Fiction","Drop Down");
    }

    @Test(priority = 4)
    public void UI_REG_AdvanceSearch_4_Subcategory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckAdvanceSearchFunctionality("Subcategory","Architecture - ANF","Drop Down");
    }

    @Test(priority = 5)
    public void UI_REG_AdvanceSearch_5_Publisher() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckAdvanceSearchFunctionality("Publisher","Scholastic Books","Drop Down");
    }


    @Test(priority = 6)
    public void UI_REG_AdvanceSearch_6_TitleKeyword() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckAdvanceSearchFunctionality("TitleKeyword","Harry Potter and the Deathly Hallows","Text");
    }


    @Test(priority = 7)
    public void UI_REG_AdvanceSearch_7_BISAC1() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckAdvanceSearchFunctionality("BISAC1","Architecture","Drop Down");
    }


    @Test(priority = 8)
    public void UI_REG_AdvanceSearch_8_BISAC2() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckAdvanceSearchFunctionality("BISAC2","Antiques & Collectibles / Advertising","Drop Down");
    }



    @Test(priority = 9)
    public void UI_REG_AdvanceSearch_9_PublicationDateRange() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.AdvanceSearchRangeCheck("Publication Date Range","11202019","11302019");
    }


    @Test(priority = 10)
    public void UI_REG_AdvanceSearch_10_UserDefinedSalesDateRange() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.AdvanceSearchRangeCheck("User Defined Sales Date Range","11202019","11302019");
    }


    @Test(priority = 11)
    public void UI_REG_AdvanceSearch_11_PriceRange() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.AdvanceSearchRangeCheck("Price Range","0","100");
    }



    @Test(priority = 12)
    public void UI_REG_AdvanceSearch_12_RTDRange() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.AdvanceSearchRangeCheck("RTD Range","0","100");
    }


    @Test(priority = 13)
    public void UI_REG_AdvanceSearch_13_PageRange() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.AdvanceSearchRangeCheck("Page Range","1","3");
    }



    @Test(priority = 14)
    public void UI_REG_AdvanceSearch_14_AllFilters() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckAllFilterAdvanceSearch();
    }


    @Test(priority = 15)
    public void UI_REG_AdvanceSearch_15_ExportAdvanceSearch() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.ExportAdvanceSearchResults();
    }


/*    @Test(priority = 9)
    public void UI_REG_AdvanceSearch_9_CheckDataIntegrity() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.CheckDataIntegrity("US BookScan POS - Ops/Product Total Market","DataIntegrity_BS");
    }*/



 /*   @Test(priority = 10)
    public void UI_ConnectSybase() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.sybaseConnect();
    }
*/

/*    @Test
    public void UI_AdvanceSearch_SuperCategory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        advancesearch.getItemsfromSearchDropDown();
    }*/




    @AfterTest
    public void moveFiles() throws Exception {

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

        end_TIME = Instant.now();
        Duration ElapsedTime = Duration.between(start_TIME,end_TIME);
        System.out.println(ElapsedTime);


    }


}
