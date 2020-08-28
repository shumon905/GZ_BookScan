package Test_UI_Bookscan;

import BaseClass.CommonApi;
import PageObjects.*;
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

public class TC_WSJ extends CommonApi{

    CommonApi commonApi = new CommonApi();
    private static page_WSJ WSJ = PageFactory.initElements(driver, page_WSJ.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Regression_BS_WSJ";
    private String htmlreporturl;
    private String htmloutput;
    private String sanityOutput = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
    private String archivehtmlOutput;


    @BeforeSuite
    public void SetUp() throws IOException {

        ScreenshotRequired = true;
        htmlreporturl = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\Results\\HTML Reports\\ExtendReport.html";
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

            //Launch Browser
            launchbrowser();


        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test(priority=1)
    public void UI_REG_WSJ_1_WSJCheckpoint() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckWSJElements();
    }

    @Test(priority=2)
    public void UI_REG_WSJ_2_HardNonFictionTableHeader() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableHeaders("Hardcover Non-Fiction");
    }


    @Test(priority=3)
    public void UI_REG_WSJ_3_NonFictionEBooks() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableHeaders("Non-Fiction E-Books");
    }

    @Test(priority=4)
    public void UI_REG_WSJ_4_NonFictionCombined() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableHeaders("Non-Fiction Combined");
    }

    @Test(priority=5)
    public void UI_REG_WSJ_5_HardCoverFiction() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableHeaders("Hardcover Fiction");
    }


    @Test(priority=6)
    public void UI_REG_WSJ_6_FictionEBooks() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableHeaders("Fiction E-Books");
    }


    @Test(priority=7)
    public void UI_REG_WSJ_7_FictionCombined() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableHeaders("Fiction Combined");
    }


    @Test(priority=8)
    public void UI_REG_WSJ_8_HardcoverBusiness() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableHeaders("Hardcover Business");
    }


    @Test(priority=9)
    public void UI_REG_WSJ_9_HardNonFictionTableRowCount() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableRowCount("Hardcover Non-Fiction");
    }


    @Test(priority=10)
    public void UI_REG_WSJ_10_NonFictionEBooksRowCount() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableRowCount("Non-Fiction E-Books");
    }

    @Test(priority=11)
    public void UI_REG_WSJ_11_NonFictionCombinedRowCount() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableRowCount("Non-Fiction Combined");
    }

    @Test(priority=12)
    public void UI_REG_WSJ_12_HardCoverFictionRowCount() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableRowCount("Hardcover Fiction");
    }


    @Test(priority=13)
    public void UI_REG_WSJ_13_FictionEBooksRowCount() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableRowCount("Fiction E-Books");
    }


    @Test(priority=14)
    public void UI_REG_WSJ_14_FictionCombinedRowCount() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableRowCount("Fiction Combined");
    }


    @Test(priority=15)
    public void UI_REG_WSJ_15_HardcoverBusinessRowCount() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckTableRowCount("Hardcover Business");
    }


    @Test(priority=16)
    public void UI_REG_WSJ_16_GlobalExport() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckGlobalExport();
    }


    @Test(priority=17)
    public void UI_REG_WSJ_17_Export_HardcoverBusiness() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckLocalExport("Hardcover Business");
    }


    @Test(priority=18)
    public void UI_REG_WSJ_18_Export_HardNonFiction() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckLocalExport("Hardcover Non-Fiction");
    }


    @Test(priority=19)
    public void UI_REG_WSJ_19_Export_NonFictionEBooks() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckLocalExport("Non-Fiction E-Books");
    }

    @Test(priority=20)
    public void UI_REG_WSJ_20_Export_NonFictionCombined() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckLocalExport("Non-Fiction Combined");
    }

    @Test(priority=21)
    public void UI_REG_WSJ_21_Export_HardCoverFiction() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckLocalExport("Hardcover Fiction");
    }


    @Test(priority=22)
    public void UI_REG_WSJ_22_Export_FictionEBooks() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckLocalExport("Fiction E-Books");
    }


    @Test(priority=23)
    public void UI_REG_WSJ_23_Export_FictionCombined() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        WSJ.CheckLocalExport("Fiction Combined");
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
