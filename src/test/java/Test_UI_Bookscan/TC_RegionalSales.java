package Test_UI_Bookscan;

import BaseClass.CommonApi;
import PageObjects.page_Login;
import PageObjects.page_RegionalSales;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class TC_RegionalSales extends CommonApi{

    CommonApi commonApi = new CommonApi();
    private static page_RegionalSales RS = PageFactory.initElements(driver, page_RegionalSales.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String htmlreporturl;
    private String htmloutput;
    private String sanityOutput = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
    private String archivehtmlOutput;




    @BeforeSuite
    public void SetUp() throws IOException {
//        cleanTextFiles(logLocal);
//        genericAPI.cleanDirectory(ScreenShootLocal);
        ScreenshotRequired = true;
        TestModule = "Bookscan_RegionalSales";
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
/*            page_Login page_login = new page_Login();
            page_login.Login("Bob Backline","Baseball1");*/

        }catch(Exception e){
            e.printStackTrace();

        }

    }


    @Test(priority=1)
    public void UI_REG_RegionalSales_1_CheckDMAList() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        RS.checkRegionNames("DMA");

    }


    @Test(priority=2)
    public void UI_REG_RegionalSales_2_CheckNationalList() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        RS.checkRegionNames("National");

    }


    @Test(priority=3)
    public void UI_REG_RegionalSales_3_SelectDMA() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        RS.selectRegionValidation("DMA","Abilene-Sweetwater, TX");

    }


    @Test(priority=4)
    public void UI_REG_RegionalSales_4_SelectNational() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        RS.selectRegionValidation("National","New England");

    }


    @Test(priority=5)
    public void UI_REG_HiveNAV() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        RS.getHiveDataFromNAV();

    }


/*
    @Test
    public void DB_HadoopData(){

        DBUtil dbUtil = new DBUtil();

        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        dbUtil.getHiveConnect();


    }*/



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
