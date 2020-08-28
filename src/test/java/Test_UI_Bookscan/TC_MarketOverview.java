package Test_UI_Bookscan;

import BaseClass.CommonApi;
import PageObjects.page_AdvanceSearch;
import PageObjects.page_BasicSearch;
import PageObjects.page_Login;
import PageObjects.page_MarketOverview;
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

public class TC_MarketOverview extends CommonApi{

    CommonApi commonApi = new CommonApi();
    private static page_MarketOverview marketoverview = PageFactory.initElements(driver, page_MarketOverview.class);
    ArrayList<String> Tests = new ArrayList<>();
    private String testName;
    private String TestModule = "Regression_BS_MarketOverview";
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
    public void UI_REG_MarketOverview_1_BSLCheckpoint_RecentView() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckMarketOverViewElements();
    }

    @Test(priority=2)
    public void UI_REG_MarketOverview_2_MO_PublisherFamilySelection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect("Publisher Family","Scholastic");
    }


    @Test(priority=3)
    public void UI_REG_MarketOverview_3_MO_SupercategorySelection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect("Supercategory","Adult Fiction");
    }


    @Test(priority=4)
    public void UI_REG_MarketOverview_4_MO_SubcategorySelection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect("Subcategory","Action/Adventure - AF");
    }



    @Test(priority=5)
    public void UI_REG_MarketOverview_5_MO_SubcategorySelection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect("Subcategory","Action/Adventure - AF");
    }


    @Test(priority=6)
    public void UI_REG_MarketOverview_6_MO_FormatSelection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect("Format","Hardcover");
    }


    @Test(priority=7)
    public void UI_REG_MarketOverview_7_MO_VintageSelection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect("Vintage","Front List");
    }


    @Test(priority=8)
    public void UI_REG_MarketOverview_8_ExportPDFSalesOverview() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.ExportMO("Sales Overview","PDF","sales-overview","graph","pdf");
    }


    @Test(priority=9)
    public void UI_REG_MarketOverview_9_ExportPNGSalesOverview() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.ExportMO("Sales Overview","PNG","sales-overview","graph","png");
    }


    @Test(priority=10)
    public void UI_REG_MarketOverview_10_ExportPDFTotalMKRPrintSold() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.ExportMO("Total Units Sold","PDF","Total Units Sold","graph","pdf");
    }


    @Test(priority=11)
    public void UI_REG_MarketOverview_11_ExportPNGTotalMKRPrintSold() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.ExportMO("Total Units Sold","PNG","Total Units Sold","graph","png");
    }


    @Test(priority=12)
    public void UI_REG_MarketOverview_12_ExportPDFPercentChange() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.ExportMO("% Change Vs. Year Ago","PDF","Percentage Change Vs Year Ago","graph","pdf");
    }


    @Test(priority=13)
    public void UI_REG_MarketOverview_13_ExportPNGPercentChange() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.ExportMO("% Change Vs. Year Ago","PNG","Percentage Change Vs Year Ago","graph","png");
    }

    @Test(priority=14)
    public void UI_REG_MarketOverview_14_ExportExcelSalesOverview() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.ExportMO("Sales Overview","XLSX","Sales_Overview","table","xlsx");
    }


    @Test(priority=15)
    public void UI_REG_MarketOverview_15_ExportExcelTotalMarketPriceSold() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.ExportMO("Total Units Sold","XLSX","Sales_Overview_","table",".xlsx");
    }


    @Test(priority=16)
    public void UI_REG_MarketOverview_16_ExportExcelPercentChangeSold() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.ExportMO("% Change Vs. Year Ago","XLSX","Sales_Overview_","table",".xlsx");
    }


    @Test(priority=17)
    public void UI_REG_MarketOverview_17_SalesOverviewYear_YTD() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckSelectYearDDSelection("Sales Overview","YTD");
    }


    @Test(priority=18)
    public void UI_REG_MarketOverview_18_SalesOverviewYear_Year() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckSelectYearDDSelection("Sales Overview","2019");
    }


    @Test(priority=19)
    public void UI_REG_MarketOverview_19_TotalMarketYear_YTD() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckSelectYearDDSelection("Total Units Sold","YTD");
    }

    @Test(priority=20)
    public void UI_REG_MarketOverview_20_TotalMarketYear_Annual() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckSelectYearDDSelection("Total Units Sold","Annual");
    }


    @Test(priority=21)
    public void UI_REG_MarketOverview_21_PercentChangeYear_YTD() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckSelectYearDDSelection("% Change Vs. Year Ago","YTD");
    }

    @Test(priority=22)
    public void UI_REG_MarketOverview_22_PercentChangeYear_Annual() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckSelectYearDDSelection("% Change Vs. Year Ago","Annual");
    }

    @Test(priority=23)
    public void UI_REG_MarketOverview_23_SalesOverViewTableHeader() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckTableColumnHeaders("Sales Overview");
    }

    @Test(priority=24)
    public void UI_REG_MarketOverview_24_TotalMarketTableHeader() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckTableColumnHeaders("Total Units Sold");
    }


    @Test(priority=25)
    public void UI_REG_MarketOverview_25_PercentChangeTableHeader() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckTableColumnHeaders("% Change Vs. Year Ago");
    }


    @Test(priority=26)
    public void UI_REG_MarketOverview_26_CheckPointsPublisherRankings() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckPointinPublisherRanking();
    }


    @Test(priority=27)
    public void UI_REG_MarketOverview_27_PR_SupercategorySelection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect_PublisherRanking("Supercategory","Adult Fiction");
    }


    @Test(priority=28)
    public void UI_REG_MarketOverview_28_PR_SubcategorySelection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect_PublisherRanking("Subcategory","Action/Adventure - AF");
    }


    @Test(priority=29)
    public void UI_REG_MarketOverview_29_PR_FormatSelection() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect_PublisherRanking("Format","Hardcover");
    }


    @Test(priority=30)
    public void UI_REG_MarketOverview_30_RI_CheckPoints() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.RegionalIndexCheckPoints();
    }


    @Test(priority=31)
    public void UI_REG_MarketOverview_31_RI_PublisherFamily() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect_RegionalIndex("Publisher Family","Andrews & Mcmeel");
    }


    @Test(priority=32)
    public void UI_REG_MarketOverview_32_RI_Supercategory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect_RegionalIndex("Supercategory","Adult Fiction");
    }


    @Test(priority=33)
    public void UI_REG_MarketOverview_33_RI_Subcategory() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect_RegionalIndex("Subcategory","Adult Audio - Other");
    }


    @Test(priority=34)
    public void UI_REG_MarketOverview_34_RI_Format() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect_RegionalIndex("Format","Hardcover");
    }


    @Test(priority=35)
    public void UI_REG_MarketOverview_35_RI_Vintage() throws Exception {
        sStartTime = System.currentTimeMillis();
        currentTestName = new Throwable().getStackTrace()[0].getMethodName();
        testName = new Throwable().getStackTrace()[0].getMethodName();
        Tests.add(testName);
        marketoverview.CheckFilterSelect_RegionalIndex("Vintage","Front List");
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
