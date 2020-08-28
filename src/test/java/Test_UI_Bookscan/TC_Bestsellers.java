package Test_UI_Bookscan;

import BaseClass.CommonApi;
import PageObjects.page_Bestsellers;
import PageObjects.page_LandingPage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TC_Bestsellers extends CommonApi{

    CommonApi commonApi = new CommonApi();
    private static page_Bestsellers bestsellers = PageFactory.initElements(driver, page_Bestsellers.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Bookscan_Bestsellers";
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
    public void UI_REG_Bestsellers_1_BSLCheckpoint_RecentView() throws Exception {

//        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            bestsellers.BestSelListCheckPoint_RecentView();
/*        }catch (Exception e){
            throw (e);
        }*/

    }


/*    @Test(priority=13)
    public void UI_REG_Bestsellers_2_CreateDeleteBestSellerList() throws Exception {

//        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            bestsellers.CreateAndDeleteBestsellerList("Sanity Test Bestseller List");

*//*        }catch(Exception e){
            throw (e);
//            Assert.fail();
        }*//*

    }*/


    @Test(priority=3)
    public void UI_REG_Bestsellers_3_CheckRecentViewTableHeaders() throws Exception {

        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            bestsellers.CheckBSLRecentViewColumns();
        }catch (Exception e){
            throw (e);
        }
    }



    @Test(priority=4)
    public void UI_REG_Bestsellers_4_CheckBSLPreviewUSBookscan() throws Exception {

        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            bestsellers.CheckBSLPreview("US Bookscan Weekly Field Name","US BookScan","Weekly");

        }catch (Exception e){
            throw (e);
        }
    }



    @Test(priority=5)
    public void UI_REG_Bestsellers_5_CheckBSLPreviewBAndN() throws Exception {

        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            bestsellers.CheckBSLPreview("B&N Bookscan Weekly Field Name","B&N BookScan","Weekly");
        }catch (Exception e){
            throw (e);
        }

    }

    @Test(priority=6)
    public void UI_REG_Bestsellers_6_BSTemplate_USBookscan() throws Exception {
        try{
            sStartTime = System.currentTimeMillis();
            currentTestName = new Throwable().getStackTrace()[0].getMethodName();
            testName = new Throwable().getStackTrace()[0].getMethodName();
            Tests.add(testName);
            bestsellers.BSLTemplateValidation("US BookScan","Weekly|Year-to-Date|Regional|Publisher");
        }catch (Exception e){
            throw (e);
        }


    }



    @Test(priority=7)
    public void UI_REG_Bestsellers_7_BSTemplate_BNBookscan() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        bestsellers.BSLTemplateValidation("B&N BookScan","Weekly|Year-to-Date|Regional|Outlet|Publisher");
    }

    @Test(priority=8)
    public void UI_REG_Bestsellers_8_BSTemplate_USPubTrack() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        bestsellers.BSLTemplateValidation("US PubTrack Digital","Monthly|Year-to-Date");
    }

/*    @Test(priority=12)
    public void UI_REG_Bestsellers_9_DateRangeState() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        bestsellers.CheckDateRangeState();
    }*/



    @Test(priority=10)
    public void UI_REG_Bestsellers_10_BestsellerLinkNavigation() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        bestsellers.Check_BestsellerLink_BSLCreation();
    }



    @Test(priority=11)
    public void UI_REG_Bestsellers_11_CheckPointBSLCreation() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        bestsellers.Checkpoint_BSLCreation();
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
