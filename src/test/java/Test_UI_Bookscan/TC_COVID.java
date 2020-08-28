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

public class TC_COVID extends CommonApi {

    CommonApi commonApi = new CommonApi();
    private static page_BasicSearch basicsrch = PageFactory.initElements(driver, page_BasicSearch.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Regression_BS_COVIDChecks";
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
    public void UI_REG_COVID_1_CheckCOVIDDataColor() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDDataColor_BasicSearch("COVID","9780395401460","#0078BE");
    }


    @Test(priority = 2)
    public void UI_REG_COVID_2_NonCOVIDColor_BasicSearch() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDDataColor_BasicSearch("NON COVID","9780590353427","#565A5C");
    }


    @Test(priority = 3)
    public void UI_REG_COVID_3_COVIDDataColor_SalesRanking() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDData_SalesAndRank("COVID","9780395401460","Apr 05 2020 - Apr 11 2020","#0078BE");
    }


    @Test(priority = 4)
    public void UI_REG_COVID_4_NonCOVIDDataColor_SalesRanking() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDData_SalesAndRank("NON COVID","9780590353427","Apr 05 2020 - Apr 11 2020","#565A5C");
    }


    @Test(priority = 5)
    public void UI_REG_COVID_5_COVIDDataColor_Metadata() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDFormatMetaData("COVID","9780395401460","#0078BE");
    }

    @Test(priority = 6)
    public void UI_REG_COVID_6_NonCOVIDDataColor_Metadata() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDFormatMetaData("NON COVID","9780590353427","#565A5C");
    }



    @Test(priority = 7)
    public void UI_REG_COVID_7_COVIDDataColor_AuthorHistory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDFormatAuthorHistory("COVID","9780395401460","#0078BE");
    }


    @Test(priority = 8)
    public void UI_REG_COVID_8_NonCOVIDDataColor_AuthorHistory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDFormatAuthorHistory("NON COVID","9780590353427","#565A5C");
    }


    @Test(priority = 9)
    public void UI_REG_COVID_9_COVIDDataColor_SingleTitle() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDDataFormatSingleTitle("COVID","9780395401460","#0078BE");
    }


    @Test(priority = 10)
    public void UI_REG_COVID_10_NONCOVIDDataColor_SingleTitle() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDDataFormatSingleTitle("NON COVID","9780590353427","#565A5C");
    }


    @Test(priority = 11)
    public void UI_REG_COVID_11_COVIDToolTipTextCheck() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.CheckCOVIDToolTip("RTD","9780395401460");
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
