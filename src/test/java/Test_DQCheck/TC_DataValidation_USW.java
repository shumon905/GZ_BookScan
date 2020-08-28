package Test_DQCheck;

import BaseClass.CommonApi;
import BaseClass.DBUtil;
import MethodsApi.ElasticSearchLoad_api;
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

public class TC_DataValidation_USW extends CommonApi{

    CommonApi commonApi = new CommonApi();
/*    private static page_Login login = PageFactory.initElements(driver, page_Login.class);
    private static page_BasicSearch basicsrch = PageFactory.initElements(driver, page_BasicSearch.class);
    private static page_AdvanceSearch advancesearch = PageFactory.initElements(driver, page_AdvanceSearch.class);
    private static page_Bestsellers bestsellers = PageFactory.initElements(driver, page_Bestsellers.class);
    private static page_Collections collections = PageFactory.initElements(driver, page_Collections.class);
    private static page_LandingPage landingPage = PageFactory.initElements(driver, page_LandingPage.class);*/

    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Data Quality Check - USW";
    private String htmlreporturl;
    private String htmloutput;
    private String sanityOutput = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
    private String archivehtmlOutput;




    @BeforeSuite
    public void SetUp() throws IOException {

//        cleanTextFiles(logLocal);
//        genericAPI.cleanDirectory(ScreenShootLocal);
        ScreenshotRequired = false;
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
//            envName = "PROD";

//            CommonApi com = new CommonApi();

            envName = getDQCheck_Env();

//            launchbrowser();
//            login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Test(priority = 1)
    public void BS_DQ_LatestTimePeriod_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheck_LatestTimePeriod("US BookScan");

    }




    @Test(priority = 2)
    public void BS_DQ_BookSales_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheck_SalesData("US BookScan");

    }





    @Test(priority = 3)
    public void BS_DQ_SalesAndRank_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheck_SalesAndRankHistory("US BookScan");

    }





    @Test(priority = 4)
    public void BS_DQ_SalesDMA_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckDMASales("US BookScan");

    }





    @Test(priority = 5)
    public void BS_DQ_SalesCD_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckCDSales("US BookScan");

    }




    @Test(priority = 6)
    public void BS_DQ_Rank_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckRank("US BookScan","Ranks");

    }





    @Test(priority = 7)
    public void BS_DQ_GeoRank_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckRank("US BookScan","Geo");

    }





    @Test(priority = 8)
    public void BS_DQ_YTDRank_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckRank("US BookScan","YTD");

    }







    @Test(priority = 9)
    public void BS_DQ_52Weeks_USBookScan_DMA() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheck52Weeks("US BookScan","DMA");

    }


    @Test(priority = 10)
    public void BS_DQ_52Weeks_USBookScan_CD() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheck52Weeks("US BookScan","National");

    }

    @Test(priority = 11)
    public void BS_DQ_BookSales_Bulk_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheck_SalesData_Bulk("US BookScan");


    }



    @Test(priority = 12)
    public void BS_DQ_SalesAndRank_Bulk_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheck_SalesAndRankHistory_Bulk("US BookScan");

    }


    @Test(priority = 13)
    public void BS_DQ_SalesDMA_Bulk_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckDMASales_Bulk("US BookScan");

    }

    @Test(priority = 14)
    public void BS_DQ_SalesCD_Bulk_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckCDSales_Bulk("US BookScan");

    }


/*    @Test(priority = 26)
    public void BS_DQ_Rank_Bulk_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckRank_Bulk("US BookScan","Ranks");

    }


    @Test(priority = 27)
    public void BS_DQ_GeoRank_Bulk_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckRank_Bulk("US BookScan","Geo");

    }

    @Test(priority = 28)
    public void BS_DQ_YTDRank_Bulk_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckRank_Bulk("US BookScan","YTD");
//        ele_api.ConvertMonthNameToNumber("Jun");

    }*/

    @Test(priority = 15)
    public void BS_DQ_MarketOverview_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckMarketOverview("US BookScan");

    }


    @Test(priority = 16)
    public void BS_DQ_ALR_BookSales_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheck_SalesData_ALR("US BookScan");

    }


    @Test(priority = 17)
    public void BS_DQ_ALR_SalesAndRank_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheck_SalesAndRankHistory_ALR("US BookScan");

    }


    @Test(priority = 18)
    public void BS_DQ_ALR_SalesDMA_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckDMASales_ALR("US BookScan");

    }


    @Test(priority = 19)
    public void BS_DQ_ALR_SalesCD_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckCDSales_ALR("US BookScan");

    }



    @Test(priority = 20)
    public void BS_DQ_WSJ_Rank_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckRank_WSJ("US BookScan","Ranks");

    }

    @Test(priority = 21)
    public void BS_DQ_ALR_Rank_USBookScan() throws Exception {

        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.DQCheckRank_ALR("US BookScan","Ranks");

    }


    /*

 @Test(priority = 9)
        public void BS_DQ_BookCount_USBookScan() throws Exception {
        ElasticSearchLoad_api ele_api = new ElasticSearchLoad_api();
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        ele_api.CheckAllBooksCount();

    }*/


    @AfterTest
    public void moveFiles() throws Exception {

        //Close Browser
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
//            copyDirectory(ScreenShootLocal,BSresourcefile_parent + "\\Results\\Screenshots");

        }

    }


}
