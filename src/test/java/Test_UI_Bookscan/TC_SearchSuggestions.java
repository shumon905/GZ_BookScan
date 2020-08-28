package Test_UI_Bookscan;

import BaseClass.CommonApi;
import PageObjects.page_BasicSearch;
import PageObjects.page_Login;
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

public class TC_SearchSuggestions extends CommonApi {


    CommonApi commonApi = new CommonApi();
    private static page_BasicSearch basicsrch = PageFactory.initElements(driver, page_BasicSearch.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Bookscan_SearchSuggestions";
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
            launchbrowser();
            Thread.sleep(3000);
            page_Login page_login = new page_Login();
            page_login.Login("Bob Backline","Baseball1");

        }catch(Exception e){
            e.printStackTrace();

        }

    }


    @Test(priority = 1)
    public void UI_REG_SS_1_SearchAll() throws Exception {

        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchSuggestion("Search All");


    }



    @Test(priority = 2)
    public void UI_REG_SS_1__Author() throws Exception {

        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchSuggestion("Author");


    }


    @Test(priority = 3)
    public void UI_REG_SS_1__Title() throws Exception {

        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        basicsrch.searchSuggestion("Title");


    }




    @AfterTest
    public void moveFiles() throws Exception {

        try{
            driver.close();
        }catch(Exception e){

        }


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
