package PageObjects;

import BaseClass.CommonApi;
import Reporting.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class page_WSJ extends CommonApi{

    private String bt_xpath_leftContent_WSJ = "//div[contains(@class,'MuiDrawer-root MuiDrawer-docked')]//ancestor::div[text()='Wall Street Journal']";
    private String title_WSJ = "//p[contains(@class,'MuiTypography-root') and text()='Wall Street Journal']";
    private String title_BSL = "//p[contains(@class,'MuiTypography-root') and text()='Bestseller Lists']";
    private String lbl_DateRange = "//label[@id='label' and text()='Date Range:']";
    private String fld_DateRange = "//input[@id='date-picker-inline-to']";
    private String dd_DateRange = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall']";


    private String table_hard_NonFiction = "//span[text()='Hardcover Non-Fiction']";
    private String table_NonFiction_EBook = "//span[text()='Non-Fiction E-Books']";
    private String table_NonFiction_Combined = "//span[text()='Non-Fiction Combined']";
    private String table_hard_Fiction = "//span[text()='Hardcover Fiction']";
    private String table_Fiction_EBook = "//span[text()='Fiction E-Books']";
    private String table_Fiction_Combined = "//span[text()='Fiction Combined']";
    private String table_hard_Business = "//span[text()='Hardcover Business']";

    private String bt_xPath_Global_Download = "(//img[@alt='download'])[1]";
    private String bt_xPath_Local_Download = "(//img[@alt='download'])[2]";
    private String lnk_xPath_ExportAll = "//span[contains(@class,'MuiTypography-root') and text()='Export All']";


    public void CheckWSJElements() throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();


        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select Market Overview Option
        driver.findElement(By.xpath(bt_xpath_leftContent_WSJ)).click();
        Thread.sleep(2000);

        if(IsElementPresent(title_WSJ,true)==false){ IsTestFail = true; }
        if(IsElementPresent(title_BSL,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_DateRange,true)==false){ IsTestFail = true; }
        if(IsElementPresent(fld_DateRange,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_DateRange,true)==false){ IsTestFail = true; }
        if(IsElementPresent(table_hard_NonFiction,true)==false){ IsTestFail = true; }
        if(IsElementPresent(table_NonFiction_EBook,true)==false){ IsTestFail = true; }
        if(IsElementPresent(table_NonFiction_Combined,true)==false){ IsTestFail = true; }
        if(IsElementPresent(table_hard_Fiction,true)==false){ IsTestFail = true; }
        if(IsElementPresent(table_Fiction_EBook,true)==false){ IsTestFail = true; }
        if(IsElementPresent(table_Fiction_Combined,true)==false){ IsTestFail = true; }
        if(IsElementPresent(table_hard_Business,true)==false){ IsTestFail = true; }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }

    }


    public void CheckTableHeaders(String TabName) throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select WSJ Option
//        if(IsInWSJTab()==false){
            driver.findElement(By.xpath(bt_xpath_leftContent_WSJ)).click();
            Thread.sleep(2000);
//        }


        //Click on The Tab
        String tabXPath = getTabXPath(TabName);
        driver.findElement(By.xpath(tabXPath)).click();
        Thread.sleep(2000);

        //Get Table Index
        int idx_table = getTabIndex(TabName);

        String xPath_TWRank = "(//span[text()='TW Rank'])[" + idx_table + "]";
        String xPath_LWRank = "(//span[text()='LW Rank'])[" + idx_table + "]";
        String xPath_Title = "(//span[text()='Title'])[" + idx_table + "]";
        String xPath_Author = "(//span[text()='Author'])[" + idx_table + "]";
        String xPath_Imprint = "(//span[text()='Imprint'])[" + idx_table + "]";

        if(IsElementPresent(xPath_TWRank,true)==false){ IsTestFail = true; }
        if(IsElementPresent(xPath_LWRank,true)==false){ IsTestFail = true; }
        if(IsElementPresent(xPath_Title,true)==false){ IsTestFail = true; }
        if(IsElementPresent(xPath_Author,true)==false){ IsTestFail = true; }
        if(IsElementPresent(xPath_Imprint,true)==false){ IsTestFail = true; }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }



    }


    //Check Table Row Count
    public void CheckTableRowCount(String TabName) throws Exception {


        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select WSJ Option
        driver.findElement(By.xpath(bt_xpath_leftContent_WSJ)).click();
        Thread.sleep(2000);


        //Click on The Tab
        String tabXPath = getTabXPath(TabName);
        driver.findElement(By.xpath(tabXPath)).click();
        Thread.sleep(2000);

        //Get Table Index
        int idx_table = getTabIndex(TabName);

        //Select Date Range
        SetDate("2020-Apr-25");


        String xPathTabkeRow = "((//table[contains(@aria-labelledby,'tableTitle')])[" + idx_table + "]//tbody//tr)";
        int cnt_Row = driver.findElements(By.xpath(xPathTabkeRow)).size();

        if(cnt_Row==20){
            TestLogger.logPass("10 Rows has been present");
        }else{
            TestLogger.logFail("Row Count did not match. Expected Row : 10 " + " Actual Row : " + cnt_Row);
            IsTestFail = true;
        }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }


    }


    //Global Export Check
    public void CheckGlobalExport() throws Exception {

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select WSJ Option
        driver.findElement(By.xpath(bt_xpath_leftContent_WSJ)).click();
        Thread.sleep(2000);

        //Click on Global Export
        driver.findElement(By.xpath(bt_xPath_Global_Download)).click();
        Thread.sleep(1000);

        //click on Export All
        driver.findElement(By.xpath(lnk_xPath_ExportAll)).click();
        Thread.sleep(3000);

        //Check Exported File Exists
        boolean fileExist = IsFileExist("WSJ_Bestsellers_All");

        if(fileExist==true){
            TestLogger.logPass("Global Exported File Exists.");
        }else{
            TestLogger.logFail("Global Exported File does not exist.");
        }

        deleteAllFilesInDirectory(System.getProperty("java.io.tmpdir") + "Bookscan");

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }

    }


    //Check Local Table Export
    public void CheckLocalExport(String TabName) throws Exception {
        //Clear Exported Folder
        deleteAllFilesInDirectory(System.getProperty("java.io.tmpdir") + "Bookscan");

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        CommonApi commonApi = new CommonApi();
        boolean IsTestFail = false;

        //Select WSJ Option
        driver.findElement(By.xpath(bt_xpath_leftContent_WSJ)).click();
        Thread.sleep(2000);

        //Click on The Tab
        String tabXPath = getTabXPath(TabName);
        driver.findElement(By.xpath(tabXPath)).click();
        Thread.sleep(2000);

        //Click on Local Export
        driver.findElement(By.xpath(bt_xPath_Local_Download)).click();
        Thread.sleep(1000);

//        //click on Export All
//        driver.findElement(By.xpath(lnk_xPath_ExportAll)).click();
//        Thread.sleep(3000);

        //Check Exported File Exists
        boolean fileExist = IsFileExist("WSJ_Bestsellers");

        if(fileExist==true){
            TestLogger.logPass("Local Exported File Exists.");
        }else{
            TestLogger.logFail("Local Exported File does not exist.");
        }

        deleteAllFilesInDirectory(System.getProperty("java.io.tmpdir") + "Bookscan");

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }

    }






    //Get WSJ Tab
    public String getTabXPath(String TabName){

        String SelectTabXPath = "";

        if(TabName.equals("Hardcover Non-Fiction")){
            SelectTabXPath = table_hard_NonFiction;
        }else if(TabName.equals("Non-Fiction E-Books")){
            SelectTabXPath = table_NonFiction_EBook;
        }else if(TabName.equals("Non-Fiction Combined")){
            SelectTabXPath = table_NonFiction_Combined;
        }else if(TabName.equals("Hardcover Fiction")){
            SelectTabXPath = table_hard_Fiction;
        }else if(TabName.equals("Fiction E-Books")){
            SelectTabXPath = table_Fiction_EBook;
        }else if(TabName.equals("Fiction Combined")){
            SelectTabXPath = table_Fiction_Combined;
        }else if(TabName.equals("Hardcover Business")){
            SelectTabXPath = table_hard_Business;
        }

        return SelectTabXPath;


    }


    //Get Tab Index
    public int getTabIndex(String TabName){

        int TabIndex = 0;

        if(TabName.equals("Hardcover Non-Fiction")){
            TabIndex = 1;
        }else if(TabName.equals("Non-Fiction E-Books")){
            TabIndex = 2;
        }else if(TabName.equals("Non-Fiction Combined")){
            TabIndex = 3;
        }else if(TabName.equals("Hardcover Fiction")){
            TabIndex = 4;
        }else if(TabName.equals("Fiction E-Books")){
            TabIndex = 5;
        }else if(TabName.equals("Fiction Combined")){
            TabIndex = 6;
        }else if(TabName.equals("Hardcover Business")){
            TabIndex = 7;
        }

        return TabIndex;

    }


    //Check if app in WSJ Tab
    public boolean IsInWSJTab(){

        boolean openWSJTab = false;

        int cnt = driver.findElements(By.xpath(title_WSJ)).size();

        if(cnt>0){
            openWSJTab = true;
        }

        return openWSJTab;
    }


    //Set Date in WSJ
    public void SetDate(String EndDate) throws Exception {

        String[] EndDateRange = EndDate.split("[-]");
        String sYear = EndDateRange[0];
        String sMonth= EndDateRange[1];
        String sDate = EndDateRange[2];

        String xPath_Date_Year = "//span[@class='MuiButton-endIcon MuiButton-iconSizeSmall']";
        String xPath_Date_Year_Selection = "//div[contains(@class,'MuiPickersYear-root') and text()='" + sYear + "']";
        String xPath_Date_Month_Selection = "//div[@class='MuiTypography-root MuiPickersMonth-root MuiTypography-subtitle1' and text()='" + sMonth + "']";
        String xPath_Date_Date_Selection = "//span[text()='" + sDate + "']";


        //Click on Date Range
        driver.findElement(By.xpath(dd_DateRange)).click();
        Thread.sleep(3000);

        //Click on Year
        driver.findElement(By.xpath(xPath_Date_Year)).click();
        Thread.sleep(3000);

        //Select Year
        driver.findElement(By.xpath(xPath_Date_Year_Selection)).click();
        Thread.sleep(1000);

        //Select Month
        driver.findElement(By.xpath(xPath_Date_Month_Selection)).click();
        Thread.sleep(1000);

        //Select Date
        driver.findElement(By.xpath(xPath_Date_Date_Selection)).click();
        Thread.sleep(1000);



    }

}
