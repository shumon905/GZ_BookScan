package PageObjects;

import BaseClass.CommonApi;
import Reporting.TestLogger;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.File;
import java.util.ServiceLoader;

public class page_MarketOverview extends CommonApi {

    private String bt_xpath_leftContent_MarketOverview = "//div[contains(@class,'MuiDrawer-root MuiDrawer-docked')]//ancestor::div[text()='Market Overview']";
    private String lbl_xpath_MO = "//p[contains(@class,'MuiTypography-root') and text()='Market Overview']";
    private String lbl_xpath_SalesOverview = "//p[contains(@class,'MuiTypography-root') and text()='Sales Overview']";
    private String title_xpath_SalesOverview = "//*[name()='svg']//*[name()='text']//*[name()='tspan' and text()='Sales Overview']";
    private String subtitle_xpath_Overall = "//span[@class='highcharts-subtitle']//div//span[contains(text(),'Overall')]";
    private String lbl_xpath_TotalMktPriceSold = "//p[contains(@class,'MuiTypography-root') and text()='Total Units Sold']";
    private String lbl_xpath_percentchange = "//p[contains(@class,'MuiTypography-root') and text()='% Change Vs. Year Ago']";
    private String lbl_xpath_Filter = "//p[contains(@class,'MuiTypography-root') and text()='Filters']";

    private String dd_xpath_PubFamily = "//div[contains(@class,'MuiGrid-root')]//div//span[text()='Publisher Family']";
    private String dd_xpath_SuperCat = "//div[contains(@class,'MuiGrid-root')]//div//span[text()='Supercategory']";
    private String dd_xpath_SubCat = "//div[contains(@class,'MuiGrid-root')]//div//span[text()='Subcategory']";
    private String dd_xpath_Format = "//div[contains(@class,'MuiGrid-root')]//div//span[text()='Format']";
    private String dd_xpath_Vintage = "//div[contains(@class,'MuiGrid-root')]//div//span[text()='Vintage']";

    private String bt_xpath_salesoverview_graph = "//p[text()='Sales Overview']//parent::div//parent::div//div[2]//div[1]//div[@aria-label='table graph toggle']//button[1][@value='graph']";
    private String bt_xpath_salesoverview_table = "//p[text()='Sales Overview']//parent::div//parent::div//div[2]//div[1]//div[@aria-label='table graph toggle']//button[2][@value='table']";

    private String bt_xpath_TotalMarketSales_graph = "//p[text()='Total Units Sold']//parent::div//parent::div//div[2]//div[1]//div[@aria-label='table graph toggle']//button[1][@value='graph']";
    private String bt_xpath_TotalMarketSales_table = "//p[text()='Total Units Sold']//parent::div//parent::div//div[2]//div[1]//div[@aria-label='table graph toggle']//button[2][@value='table']";

    private String bt_xpath_PercentChange_graph = "//p[text()='% Change Vs. Year Ago']//parent::div//parent::div//div[2]//div[1]//div[@aria-label='table graph toggle']//button[1][@value='graph']";
    private String bt_xpath_PercentChange_table = "//p[text()='% Change Vs. Year Ago']//parent::div//parent::div//div[2]//div[1]//div[@aria-label='table graph toggle']//button[2][@value='table']";



    private String bt_xpath_Apply = "//span[@class='MuiButton-label' and text()='APPLY']";

    private String lbl_xpath_PR_YTD = "//div[contains(@style,'display: flex')]//div[text()='YTD']";
    private String lbl_xpath_PR_Weekly = "//div[contains(@style,'display: flex')]//div[text()='Weekly']";
    private String switch_xpath_PR_salesSwitch = "//span[contains(@class,'MuiSwitch-root')]";
    private String dd_xpath_PR_TimePeriod = "//input[@id='date-picker-inline-to']";
    private String lbl_xpath_PR_Filters = "//div[@class='MuiGrid-root MuiGrid-item']//p[text()='Filters']";
    private String dd_xpath_PR_Supercategory = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column MuiGrid-justify-xs-center']//child::span[text()='Supercategory']";
    private String dd_xpath_PR_Subcategory = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column MuiGrid-justify-xs-center']//child::span[text()='Subcategory']";
    private String dd_xpath_PR_Format = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column MuiGrid-justify-xs-center']//child::span[text()='Format']";
    private String lbl_xpath_PR_MarketShare = "//*[name()='svg']//*[name()='text']//*[name()='tspan' and text()='Market Share']";
    private String lbl_xpath_PR_Overall = "//*[name()='svg']//*[name()='text']//*[name()='tspan' and text()='Overall']";
    private String lbl_xpath_PR_Ranking = "//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')]//child::div[text()='Ranking']";
    private String th_xpath_PR_RANK = "//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')]//child::th[text()='RANK']";
    private String th_xpath_PR_PUBLISHER_FAMILY = "//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')]//child::th[text()='PUBLISHER FAMILY']";
    private String th_xpath_PR_SHARE = "//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')]//child::th[text()='SHARE']";
    private String th_xpath_PR_UNITS = "//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid')]//child::th[text()='UNITS']";

    private String lbl_xpath_RI_RegionSalesIdx = "//p[contains(@class,'MuiTypography-root') and text()='Regional Sales Index']";
    private String lbl_xpath_RI_Filters = "//p[contains(@class,'MuiTypography-root') and text()='Filters']";
    private String dd_xpath_RI_PublisherFamily = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column MuiGrid-justify-xs-center']//child::span[text()='Publisher Family']";
    private String dd_xpath_RI_Supercategory = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column MuiGrid-justify-xs-center']//child::span[text()='Supercategory']";
    private String dd_xpath_RI_Subcategory = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column MuiGrid-justify-xs-center']//child::span[text()='Subcategory']";
    private String dd_xpath_RI_Format = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column MuiGrid-justify-xs-center']//child::span[text()='Format']";
    private String dd_xpath_RI_Vintage = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-direction-xs-column MuiGrid-justify-xs-center']//child::span[text()='Vintage']";
    private String dd_xpath_RI_TimePeriod = "//div[@id='mui-component-select-salesDropDown']";
    private String lbl_xpath_RI_OverallSalesIndex = "//*[name()='svg']//*[name()='text']//*[name()='tspan' and text()='Overall Sales Index']";
    private String input_xpath_RI_Regions = "//input[@id='region-search-autocomplete-big']";


    public void CheckMarketOverViewElements() throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();


        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        Thread.sleep(2000);

        if(IsElementPresent(lbl_xpath_MO,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_SalesOverview,true)==false){ IsTestFail = true; }
        if(IsElementPresent(title_xpath_SalesOverview,true)==false){ IsTestFail = true; }
        if(IsElementPresent(subtitle_xpath_Overall,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_TotalMktPriceSold,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_percentchange,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_Filter,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_PubFamily,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_SuperCat,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_MO,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_SubCat,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_Format,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_Vintage,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_salesoverview_graph,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_salesoverview_table,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_TotalMarketSales_graph,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_TotalMarketSales_table,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_PercentChange_graph,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_PercentChange_table,true)==false){ IsTestFail = true; }




        if(IsTestFail==true){
            Assert.fail();
        }

        //Go Back to Home
        ClickOnHome();


    }


    public boolean ClickMOTabs(String tabName) throws Exception {

        //Click on the Tab
        String xTabPath = "//div[@class='MuiTabs-scroller MuiTabs-fixed']//ancestor::span[text()='" + tabName + "']";

        System.out.println(driver.findElements(By.xpath(xTabPath)).size());
        String identifyScreenxPath = null;
        driver.findElement(By.xpath(xTabPath)).click();
        Thread.sleep(3000);

/*        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();


        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        Thread.sleep(2000);

        //Click on Publisher Ranking Tab
        ClickMOTabs("Publisher Rankings");*/

        if(tabName.equals("Publisher Rankings")){
            identifyScreenxPath = "//div[contains(@class,'MuiToolbar-root MuiToolbar-regular')]//p[text()='Publisher Family Market Share']";
        }else if(tabName.equals("Regional Index")){
            identifyScreenxPath = "//div[contains(@class,'MuiToolbar-root MuiToolbar-regular')]//p[text()='Regional Sales Index']";
        }else{
            identifyScreenxPath = "//div[contains(@class,'MuiToolbar-root MuiToolbar-regular')]//p[text()='Sales Overview']";
        }

        if(driver.findElements(By.xpath(identifyScreenxPath)).size()>0){
            TestLogger.logPass(tabName + " is appearing.");
            return true;
        }else{
            TestLogger.logPass(tabName + " is not appearing.");
            return false;
        }

    }


    public void CheckPointinPublisherRanking() throws Exception {

        //Log in if already not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();


        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        Thread.sleep(2000);

        //Click on Publisher Ranking Tab
        ClickMOTabs("Publisher Rankings");

        if(IsElementPresent(lbl_xpath_PR_YTD,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_PR_Weekly,true)==false){ IsTestFail = true; }
        if(IsElementPresent(switch_xpath_PR_salesSwitch,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_PR_TimePeriod,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_PR_Filters,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_PR_Supercategory,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_PR_Subcategory,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_PR_Format,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_PR_MarketShare,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_PR_Overall,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_PR_Ranking,true)==false){ IsTestFail = true; }
        if(IsElementPresent(th_xpath_PR_RANK,true)==false){ IsTestFail = true; }
        if(IsElementPresent(th_xpath_PR_SHARE,true)==false){ IsTestFail = true; }
        if(IsElementPresent(th_xpath_PR_UNITS,true)==false){ IsTestFail = true; }
        if(IsElementPresent(th_xpath_PR_PUBLISHER_FAMILY,true)==false){ IsTestFail = true; }


        if(IsTestFail==true){
            Assert.fail();
        }

        //Go Back to Home
        ClickOnHome();


    }



    public void CheckFilterSelect(String DropDownName,String ValueToSelect) throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        Thread.sleep(2000);

        //Select Filter DropDown
        SelectFilterDropDown(DropDownName,ValueToSelect);

/*        //Click On Apply
        ClickOnApply();*/

        //Check Sales Overview Subtitles
        String txtSubtitle = "//span[@class='highcharts-subtitle']//div//span[contains(text(),'" + ValueToSelect + "')]";

        if(IsElementPresent(txtSubtitle,true)==false){ IsTestFail = true; }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }



    }


    public void CheckFilterSelect_PublisherRanking(String DropDownName,String ValueToSelect) throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        Thread.sleep(2000);

        //Click on Publisher Ranking Tab
        ClickMOTabs("Publisher Rankings");

        //Select Filter Drop Down
        SelectFilterDropDown(DropDownName,ValueToSelect);

        //Check Market Share Subtitles
        String txtSubtitle = "//*[name()='svg']//*[name()='text']//*[name()='tspan' and text()='by " + ValueToSelect + "']";

        if(IsElementPresent(txtSubtitle,true)==false){ IsTestFail = true; }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }


    }


    public void CheckFilterSelect_RegionalIndex(String DropDownName,String ValueToSelect) throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        Thread.sleep(2000);

        //Click on Publisher Ranking Tab
        ClickMOTabs("Regional Index");

        //Select Filter Drop Down
        SelectFilterDropDown(DropDownName,ValueToSelect);

        //Check Market Share Subtitles
        String txtSubtitle = "//*[name()='svg']//*[name()='text']//*[name()='tspan' and text()='Sales Indexed by " + ValueToSelect + "']";

        if(IsElementPresent(txtSubtitle,true)==false){ IsTestFail = true; }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }

    }


    public void  SelectFilterDropDown(String DropDownName,String ValueToSelect) throws Exception {

        if(DropDownName.equals("Format")||DropDownName.equals("Vintage")){

            //Scroll Down
            if(!currentTestName.contains("PR") && !currentTestName.contains("RI")){
                ScrollTotheElemnet(lbl_xpath_percentchange);
                Thread.sleep(3000);
            }
        }

        //Click on DropDown
        driver.findElement(By.xpath("//div[contains(@class,'MuiGrid-root')]//div//span[text()='" + DropDownName + "']")).click();
        TestLogger.log("Click on Filter DropDown : " + DropDownName);

        //Enter Value To Select in input box
        driver.findElement(By.xpath("//input[contains(@placeholder,'" + DropDownName + "')]")).sendKeys(ValueToSelect);
        TestLogger.log("Selected Filter DropDown Value: " + ValueToSelect);

        //Select Checkbox
        driver.findElement(By.xpath("//input[@type='checkbox' and @data-text='" + ValueToSelect + "']")).click();
        TestLogger.log("Selected Filter DropDown Value Checkbox for: " + ValueToSelect);

        //Close Drop Down
        closeASOpenDropDown();
        Thread.sleep(3000);

    }

/*    //Apply Button is removed
    public void ClickOnApply() throws Exception {

        //Scroll
        ScrollTotheElemnet(lbl_xpath_percentchange);
        Thread.sleep(3000);

        //Click On Apply
        driver.findElement(By.xpath(bt_xpath_Apply)).click();
        TestLogger.log("Clicked on Apply Button");
        Thread.sleep(3000);


    }*/


    public void ExportMO(String SectionName,String ExportOption,String FileName,String type,String FileExtention) throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        TestLogger.log("Clicked on Market View from Left Panel");
        Thread.sleep(2000);

        //Navigate To the Section
        String lbl_xpath_section = "//p[contains(@class,'MuiTypography-root') and text()='" + SectionName + "']";
        ScrollTotheElemnet(lbl_xpath_section);

        if(type.equals("table")){
//            String xpathType = "//p[text()='" + SectionName + "']//parent::div//div[contains(@class,'MuiToggleButtonGroup-root')]//button[@type='button' and @value='table']//span[@class='MuiToggleButton-label']";
            String xpathType = "//p[text()='" + SectionName + "']//parent::div//parent::div//div[2]//div[1]//div[@aria-label='table graph toggle']//button[2][@value='table']";
            driver.findElement(By.xpath(xpathType)).click();
            TestLogger.logPass("Clicked On Table View Under Section : " + SectionName);
            Thread.sleep(2000);
        }

        //Click on Export to SalesOverview
//        String xPath_bt_Export = "//p[contains(@class,'MuiTypography-root') and text()='" + SectionName + "']//parent::div//child::button[contains(@class,'MuiButtonBase-root')]//span//img[@alt='download']";
        String xPath_bt_Export = "//p[text()='" + SectionName + "']//parent::div//parent::div//div[2]//div[1]//child::button[contains(@class,'MuiButtonBase-root')]//span//img[@alt='download']";
        String xPath_Option = "//span[text()='" + ExportOption + "']";

        ClickONExportExcelAndExport(xPath_bt_Export,xPath_Option);
        String loc_directory = System.getProperty("java.io.tmpdir") + "Bookscan";

        //Check if File Exists
        FileExists_PartialText(loc_directory,FileName,FileExtention);
/*

        File exportedFile = new File(loc_directory + "\\" + FileName);

        boolean FileExists = exportedFile.exists();

        if(FileExists==true){
            TestLogger.logPass("Exported File Exists : " + loc_directory + "\\" + FileName);
        }else{
            TestLogger.logFail("Exported File does not Exist : " + loc_directory + "\\" + FileName);
            IsTestFail = true;
        }*/

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }



    }


 /*   public boolean FileExists_PartialText(String Folder,String PartialFileName,String extention){

        File[] listfiles = new File(Folder).listFiles();

        boolean FileFound = false;

        for(int i=0;i<listfiles.length;i++){

            if(listfiles[i].isFile()){

                String FileName = listfiles[i].getName();

                if(FileName.contains(PartialFileName) && FileName.endsWith(extention)){
                    FileFound = true;
                    TestLogger.logPass("Expected File Found. Partial Text Name : " + PartialFileName + " Extention : " + extention);
                }
            }
        }

        if(FileFound==false){
            TestLogger.logFail("Expected File Not Found. Partial Text Name : " + PartialFileName + " Extention : " + extention);
        }
        return FileFound;

    }*/





    public void CheckSelectYearDDSelection(String Section,String SelectValue) throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        TestLogger.log("Clicked on Market View from Left Panel");
        Thread.sleep(2000);

        //Click on Year DropDown
        ClickYearDropDown(Section);

        //Select Value
        SelectValueFromYearDropDown(SelectValue);

        //Close Year Drop Down
        CloseYearDropDown();
        TestLogger.log("Drop Down has been closed.");

        //Go Back to Home
        ClickOnHome();


    }


    public void ClickYearDropDown(String SectionName) throws Exception {

        String xPath = "//p[contains(@class,'MuiTypography-root') and text()='Years']//parent::div//div[contains(@class,'MuiSelect-root MuiSelect-select')]";

        //Navigate To the Section
        String lbl_xpath_section = "//p[contains(@class,'MuiTypography-root') and text()='" + SectionName + "']";
        ScrollTotheElemnet(lbl_xpath_section);

        if(SectionName.equals("Sales Overview")){
            WebElement we = driver.findElements(By.xpath(xPath)).get(0);
            we.click();
        }else if(SectionName.equals("Total Market Prints Sold")){
            WebElement we = driver.findElements(By.xpath(xPath)).get(1);
            we.click();
        }else{
            WebElement we = driver.findElements(By.xpath(xPath)).get(2);
            we.click();
        }


    }


    public void SelectValueFromYearDropDown(String ValueToSelect) throws Exception {

        String xPath_All = "//ul[@class='MuiList-root MuiMenu-list MuiList-padding']//li[contains(@class,'MuiButtonBase-root MuiListItem-root') and text()='All']";
        String xPathValue = "//ul[@class='MuiList-root MuiMenu-list MuiList-padding']//li[contains(@class,'MuiButtonBase-root MuiListItem-root') and text()='" + ValueToSelect + "']";
        String xPath_Selected_Value = "//div[contains(@class,'MuiSelect-root MuiSelect-select') and text()='" + ValueToSelect + "']";

        //Clear DropDown Value
        driver.findElement(By.xpath(xPath_All)).click();
        Thread.sleep(1000);
        TestLogger.log("Clear Drop Down Value selections");

        //Select Value
        driver.findElement(By.xpath(xPathValue)).click();
        Thread.sleep(1000);
        TestLogger.log("Clicked On Drop Down Value " + ValueToSelect);

        int ValueSelected = driver.findElements(By.xpath(xPath_Selected_Value)).size();

        if(ValueSelected>0){
            TestLogger.logPass(ValueToSelect + " has been Selected.");
        }else{
            TestLogger.logFail(ValueToSelect + " has not been Selected.");
        }


    }


    public void CloseYearDropDown() throws Exception {

        //Close Year Drop Down
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
        Thread.sleep(3000);
        TestLogger.logPass("Year Drop Down has been Closed.");


    }


    public void CheckTableColumnHeaders(String SectionName) throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        TestLogger.log("Clicked on Market View from Left Panel");
        Thread.sleep(2000);

        //Scroll To The Element
        //Scroll
        ScrollTotheElemnet("//p[contains(@class,'MuiTypography-root') and text()='" + SectionName + "']");
        Thread.sleep(3000);


        //Click on Table View
//        driver.findElement(By.xpath("//p[text()='" + SectionName + "']//parent::div//div[contains(@class,'MuiToggleButtonGroup-root')]//button[@type='button' and @value='table']//span[@class='MuiToggleButton-label']")).click();

        driver.findElement(By.xpath("//p[text()='" + SectionName + "']//parent::div//parent::div//div[2]//div[1]//div[@aria-label='table graph toggle']//button[2][@value='table']")).click();


        TestLogger.log("Click On Table : " + bt_xpath_salesoverview_table);

        //Get Expected Column Names from Resource File
        String expected_Columns = getExpectedColumnNames(SectionName);
        String[] splitExpected = expected_Columns.split("[;]");

        //Get Actual Column Names from Application
        String[] app_columns = getColumHeader_MO(SectionName);

/*        if(splitExpected.length==app_columns.length){
            TestLogger.logPass("Both Expected And Actual Columns Count have been matched.");
        }else{
            TestLogger.logFail("Expected: " + splitExpected.length + " And Actual:" + app_columns.length + " Columns Count did not match.");
            IsTestFail = true;
        }*/

  /*      for(int i_act=0;i_act<=app_columns.length-1;i_act++){

            if(expected_Columns.contains(app_columns[i_act])){
                TestLogger.logPass("Actual Column Found : " + app_columns[i_act]);
            }else{
                TestLogger.logFail("Actual Column not Found : " + app_columns[i_act] + " expected List : " + expected_Columns);
                IsTestFail = true;
            }

        }
        */
/*        for(int i_exp=0;i_exp<=splitExpected.length;i_exp++){

            if(expected_Columns.contains(app_columns[i_exp])){
                TestLogger.logPass("Actual Column Found : " + app_columns[i_exp]);
            }else{
                TestLogger.logFail("Actual Column not Found : " + app_columns[i_exp] + " expected List : " + expected_Columns);
                IsTestFail = true;
            }


        }*/

        for(int i_act=0;i_act<=app_columns.length;i_act++){

            try{
                if(expected_Columns.contains(app_columns[i_act])){
                    TestLogger.logPass("Actual Column Found : " + app_columns[i_act]);
                }else{
                    TestLogger.logFail("Actual Column not Found : " + app_columns[i_act] + " expected List : " + expected_Columns);
                    IsTestFail = true;
                }

            }catch(Exception e){
                continue;
            }



        }

        //Go Back to Home
        ClickOnHome();


        if(IsTestFail == true){
            Assert.fail();
        }


    }


    public String[] getColumHeader_MO(String SectionName){

//        String tableHeader = "//span[contains(@class,'MuiButtonBase-root MuiTableSortLabel-root')]";
        String tableHeader = "//p[text()='" + SectionName + "']//parent::div//parent::div//parent::div//parent::div//table[1]//tr[contains(@class,'MuiTableRow-head')][last()]//th";

        int HeaderCount = driver.findElements(By.xpath(tableHeader)).size();
        String[] actual_columns = new String[HeaderCount];

        int tabidx = 0;

        for(int tab_h=0;tab_h<=HeaderCount-1;tab_h++){
            tabidx++;

            try{
//                String xPathTH = "//p[contains(@class,'MuiTypography-root') and text() = '" + SectionName + "']//parent::div//parent::div//parent::div//th[" + tabidx + "][contains(@class,'MuiTableCell-root MuiTableCell-head')]//span[contains(@class,'MuiButtonBase-root MuiTableSortLabel-root')]";

                String xPathTH = "//p[text()='" + SectionName + "']//parent::div//parent::div//parent::div//parent::div//table[1]//tr[contains(@class,'MuiTableRow-head')][last()]//th[" + tabidx + "]//span";

                System.out.println(driver.findElement(By.xpath(xPathTH)).getText());
                actual_columns[tab_h] = driver.findElement(By.xpath(xPathTH)).getText();
            }catch(Exception e){
                break;
            }

        }

        return actual_columns;

    }


    public void RegionalIndexCheckPoints() throws Exception {

        //Log in if already not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();


        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_MarketOverview)).click();
        Thread.sleep(2000);

        //Click on Publisher Ranking Tab
        ClickMOTabs("Regional Index");

        if(IsElementPresent(lbl_xpath_RI_RegionSalesIdx,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_RI_Filters,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_RI_PublisherFamily,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_RI_Supercategory,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_RI_Subcategory,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_RI_Format,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_RI_Vintage,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_RI_TimePeriod,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_RI_OverallSalesIndex,true)==false){ IsTestFail = true; }
        if(IsElementPresent(input_xpath_RI_Regions,true)==false){ IsTestFail = true; }

        if(IsTestFail==true){
            Assert.fail();
        }

        //Go Back to Home
        ClickOnHome();



    }



}
