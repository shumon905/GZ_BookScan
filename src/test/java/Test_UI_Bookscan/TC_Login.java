package Test_UI_Bookscan;

import BaseClass.CommonApi;
import PageObjects.page_Login;
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

public class TC_Login extends CommonApi{


    CommonApi commonApi = new CommonApi();
    private static page_Login login = PageFactory.initElements(driver, page_Login.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Bookscan_Login";
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


/*    @BeforeTest
    public void launchandnavigateToApp(){
        //Launch Browser and Navigate
        try{
            launchbrowser();
            Thread.sleep(3000);
            //login.Login();


        }catch(Exception e){
            e.printStackTrace();

        }

    }*/


    @Test(priority=1)
    public void UI_REG_Login_1_CheckLoginElements() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        launchbrowser();
        login.CheckLoginPageElements();
        driver.close();
    }


    @Test(priority=2)
    public void UI_REG_Login_2_SuccessfullLogin() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        launchbrowser();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);
        driver.close();
    }


    @Test(priority=3)
    public void UI_REG_Login_3_WrongUserID() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        launchbrowser();
        login.CheckLoginBoundary("WrongUser ID","Baseball1",false);
        driver.close();
    }


    @Test(priority=4)
    public void UI_REG_Login_4_WrongPassword() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        launchbrowser();
        login.CheckLoginBoundary("Bob Backline","wrongpassword",false);
        driver.close();
    }


    @Test(priority=5)
    public void UI_REG_Login_5_BlankPassword() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        launchbrowser();
        login.BlankPasswordCheck("Bob Backline");
        driver.close();
    }


    @Test(priority=6)
    public void UI_REG_Login_6_BlankUserID() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        launchbrowser();
        login.BlankUserIDCheck();
        driver.close();
    }


    @Test(priority=7)
    public void UI_REG_Login_7_Impersonate() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        launchbrowser();
        login.CheckLoginBoundary("Bob Backline/Scholastic, inc","Baseball1",true);
        driver.close();
    }


    @Test(priority=8)
    public void UI_REG_Login_8_NoProAccess() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        launchbrowser();
        login.CheckNoProAccess("dashboardonlyuser","Baseball4");
        driver.close();
    }



    @AfterTest
    public void moveFiles() throws Exception {

/*        //Close Browser
        driver.close();*/
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
