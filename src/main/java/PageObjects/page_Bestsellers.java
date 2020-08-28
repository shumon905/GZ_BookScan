package PageObjects;

import BaseClass.CommonApi;
import Reporting.TestLogger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class page_Bestsellers extends CommonApi {

    private String bt_xpath_leftContent_Bestsellers = "//div[contains(@class,'MuiDrawer-root MuiDrawer-docked')]//ancestor::div[text()='Bestsellers']";
//    private String dd_xpath_DateRange = "//div[@id = 'mui-component-select-period']";
    private String dd_xpath_DateRange = "//input[@id = 'date-picker-inline-to']";
    private String bt_viewsave = "//span[text()='VIEW & SAVE']";

    private String lbl_xpath_USBookscan = "//div[contains(@class,'MuiGrid-root')]//p[text()='US BookScan']";
    private String lbl_xpath_Bestsellers = "//div[contains(@class,'MuiGrid-root')]//p[text()='Bestsellers']";
   private String lbl_xpath_SelectShareBSL = "//div[contains(@class,'MuiGrid-root')]//p[text()='Select, save, share, and export your own bestseller lists']";
    private String input_xpath_BSLSearch = "//input[@placeholder='Search Bestsellers by Name']";
    private String bt_xpath_addBSL_Plus = "//span[@class='MuiFab-label']";
    private String bt_xpath_GlobalDownload = "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')]//ancestor::span[@class='MuiIconButton-label']//img[@alt='download'][1]";
    private String bt_xpath_expandRecentList = "//tbody[contains(@class,'MuiTableBody-root')]//tr[1]//td[2]/child::div[1]/*[name()='svg']";

    private String lbl_xpath_RecentlyViewed = "//div[contains(@class,'MuiGrid-root') and text()='Recently Viewed Bestseller Lists']";
    private String lnk_xpath_bestsellers = "//div[@class='MuiGrid-root MuiGrid-item']//a//span[text()='Bestsellers']";
    private String lbl_xpath_1 = "//div[contains(@class,'MuiAvatar-root MuiAvatar-circle') and text()='1']";
    private String lbl_xpath_2 = "//div[contains(@class,'MuiAvatar-root MuiAvatar-circle') and text()='2']";
    private String lbl_xpath_3 = "//div[contains(@class,'MuiAvatar-root MuiAvatar-circle') and text()='3']";
    private String lbl_xpath_starttemplate = "//div[contains(@class,'MuiGrid-root')]//span[text()='Start with a template']";
    private String lbl_xpath_defineAttribute = "//div[contains(@class,'MuiGrid-root')]//span[text()='Define Your Attributes']";
    private String lbl_xpath_NameList = "//div[contains(@class,'MuiGrid-root')]//span[text()='Name Your List']";
    private String bt_xpath_CLEAR = "//span[@class='MuiButton-label' and text()='CLEAR']";
    private String bt_xpath_VIEWSAVE = "//span[@class='MuiButton-label' and text()='VIEW & SAVE']";

    By bt_leftContent_Bestsellers = By.xpath(bt_xpath_leftContent_Bestsellers);



    public void BestSelListCheckPoint_RecentView() throws Exception {

        boolean IsTestFail = false;

        //Test Data
        String sTimePeriod = "Nov 24 2019 - Nov 30 2019";
        String bsl = "Sanity Test Bestseller List";


        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        CommonApi commonApi = new CommonApi();
        boolean FailedResult = false;

        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);

        //Create BSL if not Exist
        int cnt = driver.findElements(By.xpath("//p[contains(@class,'MuiTypography-root') and contains(text(),'Add a bestseller list to view')]")).size();
        if(cnt>0){
            //Create BestSeller List
            CreateBestsellerList("Test BSL for REG");

            //Select BestSeller Option
            driver.findElement(bt_leftContent_Bestsellers).click();
            Thread.sleep(2000);

        }


        if(IsElementPresent(lbl_xpath_USBookscan,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_Bestsellers,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_SelectShareBSL,true)==false){ IsTestFail = true; }
        if(IsElementPresent(input_xpath_BSLSearch,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_addBSL_Plus,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_GlobalDownload,true)==false){ IsTestFail = true; }

        if(IsTestFail==true){
            Assert.fail();
        }

        //Go Back to Home
        ClickOnHome();



    }


    public void selectBestsellerfromLeftPanel() throws Exception {


        //Login Application
        CommonApi commonApi = new CommonApi();
        page_Login login = new page_Login();

        //Login
        login.Login("Bob Backline","Baseball1");

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='MuiFab-label']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[text()='Weekly']")).click();
        Thread.sleep(2000);
//        driver.findElement(By.xpath("//div[@id='mui-component-select-aggregate']")).click();

//        driver.findElement(By.xpath("//div[@id='mui-component-select-subjectGroup']")).click();
//        driver.findElement(By.xpath("//div[@id='mui-component-select-rankCategory']")).click();

//        driver.findElement(By.xpath("//div[@id='mui-component-select-format']")).click();
        driver.findElement(By.xpath("//div[@id='mui-component-select-vintage']")).click();


        Thread.sleep(2000);

        //div[contains(@class,'MuiButtonBase-root MuiListItem-root MuiMenuItem-root')]
        int TotalItems = driver.findElements(By.xpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root MuiMenuItem-root')]")).size();

        for(int i_dd = 0; i_dd<= TotalItems-1;i_dd++){
            System.out.println(driver.findElements(By.xpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root MuiMenuItem-root')]")).get(i_dd).getText());


        }


    }


    List<String> getAllOptions(String xpathdetail) {
        List<String> options = new ArrayList<String>();
        for (WebElement option : new Select(driver.findElement(By.xpath(xpathdetail))).getOptions()) {
            String txt = option.getText();
            if (option.getAttribute("value") != "") options.add(option.getText());
            System.out.println(option.getAttribute("value"));
        }
        return options;
    }


    public void ScrollTotheElemnet(String xpathdetail) throws Exception {
        WebElement element = driver.findElement(By.xpath(xpathdetail));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);


    }


    public void CreateAndDeleteBestsellerList(String bsl) throws Exception {

        //Test Data
//        String sTimePeriod = "Nov 24 2019 - Nov 30 2019";
//        String bsl = "Sanity Test Bestseller List";


        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        CommonApi commonApi = new CommonApi();
        boolean FailedResult = false;

        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='MuiFab-label']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[text()='Weekly']")).click();
        Thread.sleep(2000);

        //Select DateRange
//        scrollDownToXPath(driver,dd_xpath_DateRange);
//        Thread.sleep(1000);

        String xPath_LeftCalendeBT = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton' and @tabindex=0][1]";
        String xPath_TimeDD = "//input[@id='date-picker-inline-to']//parent::div//div[contains(@class,'MuiInputAdornment-root MuiInputAdornment-positionEnd')]";
        String xPath_Date = "//div[@class='MuiPickersCalendar-week'][2]";

        driver.findElement(By.xpath(xPath_TimeDD)).click();
        Thread.sleep(1000);
        //Go Left
        driver.findElement(By.xpath(xPath_LeftCalendeBT)).click();
        Thread.sleep(100);
        driver.findElement(By.xpath(xPath_LeftCalendeBT)).click();
        Thread.sleep(100);
        //Select Date
        driver.findElement(By.xpath(xPath_Date)).click();
        Thread.sleep(100);


        //Find Date Range
//        int cntTimeRange = driver.findElements(By.xpath("//div[text()='" + sTimePeriod + "']")).size();

/*        if(cntTimeRange>0){
            TestLogger.logPass("Time Period : " + sTimePeriod + " found.");
        }else{
            TestLogger.logFail("Time Period : " + sTimePeriod + " is not found.");
            FailedResult = true;
        }*/


//        WebElement sdate = driver.findElement(By.xpath("//div[text()='" + sTimePeriod + "']"));
//        scrollDownToElement(driver,sdate);
//        Thread.sleep(1000);



//        driver.findElement(By.xpath("//div[text()='" + sTimePeriod + "']")).click();
//        Thread.sleep(1000);

        //Enter Name of the bestseller List
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys(bsl);

        //Click on VIEW and Save
        driver.findElement(By.xpath(bt_viewsave)).click();
        Thread.sleep(5000);

/*
        //Wait for populating list
        if(driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size()>2){
            TestLogger.logPass("Best Seller List has been created");
        }else{
            TestLogger.logFail("Best Seller List has not been created");
            FailedResult = true;
        }
*/


        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);

        if(driver.findElements(By.xpath("//a[text()='" + bsl + "']")).size()>0){
            TestLogger.logPass("BestSeller List has been Saved");

            //Delete Bestseller List
            driver.findElement(By.xpath("//a[text()='" + bsl + "']//parent::div//parent::td//preceding-sibling::td")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Delete']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Delete']")).click();
            Thread.sleep(3000);

            int scntBSL = driver.findElements(By.xpath("//a[text()='" + bsl + "']")).size();
            if(scntBSL!=0){
                TestLogger.logFail("BSL : " + bsl + " is not deleted.");
                FailedResult = true;
            }else{
                TestLogger.logPass("BSL : " + bsl + " is deleted.");
            }

        }else{
            TestLogger.logFail("BestSeller List has not been Saved");
            FailedResult = true;
        }


        //Go Back to Home
        ClickOnHome();

        Assert.assertFalse(FailedResult);

//        if(FailedResult==true){
//            Assert.fail();
//        }

    }



    public String CreateBestsellerList(String bsl) throws Exception {


        CommonApi commonApi = new CommonApi();
        boolean FailedResult = false;

        //Generate RandomNumber
        String ranNum = commonApi.GenerateRandomNumber();
        bsl = bsl + ranNum;


        //Test Data
        String sTimePeriod = "Nov 24 2019 - Nov 30 2019";
//        String bsl = "Sanity Test Bestseller List";

       int cnt = driver.findElements(By.xpath("//p[contains(@class,'MuiTypography-root') and contains(text(),'Add a bestseller list to view')]")).size();


       if(cnt==0){
           //Log in if alreadu not logged in
           page_Login login = new page_Login();
           login.CheckLoginBoundary("Bob Backline","Baseball1",true);

       }

        //Go Back to Home
        ClickOnHome();


        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='MuiFab-label']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[text()='Weekly']")).click();
        Thread.sleep(2000);

 /*       //Select DateRange
        driver.findElement(By.xpath(dd_xpath_DateRange)).click();
        Thread.sleep(1000);


        //Find Date Range
        int cntTimeRange = driver.findElements(By.xpath("//div[text()='" + sTimePeriod + "']")).size();

        if(cntTimeRange>0){
            TestLogger.logPass("Time Period : " + sTimePeriod + " found.");
        }else{
            TestLogger.logFail("Time Period : " + sTimePeriod + " is not found.");
            FailedResult = true;
        }


        WebElement sdate = driver.findElement(By.xpath("//div[text()='" + sTimePeriod + "']"));
        scrollDownToElement(driver,sdate);
        Thread.sleep(1000);



        driver.findElement(By.xpath("//div[text()='" + sTimePeriod + "']")).click();
        Thread.sleep(1000);*/

        //Select Date Range
        String xPath_LeftCalendeBT = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton' and @tabindex=0][1]";
        String xPath_TimeDD = "//input[@id='date-picker-inline-to']//parent::div//div[contains(@class,'MuiInputAdornment-root MuiInputAdornment-positionEnd')]";
        String xPath_Date = "//div[@class='MuiPickersCalendar-week'][2]";

        driver.findElement(By.xpath(xPath_TimeDD)).click();
        Thread.sleep(1000);
        //Go Left
        driver.findElement(By.xpath(xPath_LeftCalendeBT)).click();
        Thread.sleep(100);
        driver.findElement(By.xpath(xPath_LeftCalendeBT)).click();
        Thread.sleep(100);
        //Select Date
        driver.findElement(By.xpath(xPath_Date)).click();
        Thread.sleep(100);

        //Enter Name of the bestseller List
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys(bsl);

        //Click on VIEW and Save
        driver.findElement(By.xpath(bt_viewsave)).click();
        Thread.sleep(5000);

        //Wait for populating list
        if(driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size()>2){
            TestLogger.logPass("Best Seller List has been created");
        }else{
            TestLogger.logFail("Best Seller List has not been created");
//            FailedResult = true;
        }


        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);

        if(driver.findElements(By.xpath("//a[text()='" + bsl + "']")).size()>0){
            TestLogger.logPass("BestSeller List has been Saved");

/*
            //Delete Bestseller List
            driver.findElement(By.xpath("//a[text()='" + bsl + "']//parent::div//parent::td//preceding-sibling::td")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Delete']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Delete']")).click();
            Thread.sleep(3000);

            int scntBSL = driver.findElements(By.xpath("//a[text()='" + bsl + "']")).size();
            if(scntBSL!=0){
                TestLogger.logFail("BSL : " + bsl + " is not deleted.");
                FailedResult = true;
            }else{
                TestLogger.logPass("BSL : " + bsl + " is deleted.");
            }
*/

        }else{
            TestLogger.logFail("BestSeller List has not been Saved");
            FailedResult = true;
        }


        //Go Back to Home
        ClickOnHome();


        if(FailedResult==true){
            Assert.fail();
        }

        return bsl;

    }


    public void CheckBSLRecentViewColumns() throws Exception {


        boolean IsTestFail = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        CommonApi commonApi = new CommonApi();
        boolean FailedResult = false;

        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);

        //Get Expected Column List
        String expected_Columns = getExpectedColumnNames("Bestsellers RecentView");
        String[] splitExpected = expected_Columns.split("[;]");

        //Get Actual Column Names from Application
        String[] app_columns = getActualColumnName();
        Thread.sleep(2000);

        if(splitExpected.length==app_columns.length){
            TestLogger.logPass("Both Expected And Actual Columns Count have been matched.");
        }else{
            TestLogger.logFail("Expected: " + splitExpected.length + " And Actual:" + app_columns.length + " Columns Count did not match.");
            IsTestFail = true;
        }

        for(int i_act=0;i_act<=app_columns.length-1;i_act++){

            if(expected_Columns.contains(app_columns[i_act])){
                TestLogger.logPass("Actual Column Found : " + app_columns[i_act]);
            }else{
                TestLogger.logFail("Actual Column not Found : " + app_columns[i_act] + " expected List : " + expected_Columns);
                IsTestFail = true;
            }

        }

        //Go Back to Home
        ClickOnHome();


        if(IsTestFail == true){
            Assert.fail();
        }

    }


    public void CheckBSLPreview(String ResourceColumnName,String datasource,String Type) throws Exception {


        boolean IsTestFail = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();



        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource(datasource);


        CommonApi commonApi = new CommonApi();

        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);

        //Check is Bestsellerlist is available
        String BSLName = "REG Test " + datasource + Type;

        int cnt = driver.findElements(By.xpath("//p[contains(text(),'Add a bestseller list to view, share, export, and come back to')]")).size();
        if(cnt>0){
            //driver.findElement(By.xpath("//span[@class='MuiFab-label']")).click();
            //Thread.sleep(3000);

            //Go Back to Home
            ClickOnHome();


            CreateBestsellerList(BSLName);
            driver.findElement(bt_leftContent_Bestsellers).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(input_xpath_BSLSearch)).sendKeys(BSLName);
            Thread.sleep(2000);
        }

        //Enter BSL Name in Search box
        driver.findElement(By.xpath(input_xpath_BSLSearch)).sendKeys(BSLName);
        Thread.sleep(2000);

        int rowCnt = driver.findElements(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]")).size();

        if(rowCnt<2){

            //Go Back to Home
            ClickOnHome();

            //Create Test BSL
            CreateBestsellerList(BSLName);
            driver.findElement(bt_leftContent_Bestsellers).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(input_xpath_BSLSearch)).sendKeys(BSLName);
            Thread.sleep(2000);

        }

        //Expand BSL
        driver.findElement(By.xpath(bt_xpath_expandRecentList)).click();
        Thread.sleep(3000);


        int cntFields = driver.findElements(By.xpath("//tbody[@class='MuiTableBody-root']//tr[1]//td[4]//div//div")).size();
        cntFields = cntFields/2;

        String [][] sFieldName_Value = new String[cntFields+1][cntFields+1];

        sFieldName_Value[0][0]=driver.findElement(By.xpath("//tr[@class='MuiTableRow-root']//td[3]//p[1]")).getText();
        sFieldName_Value[0][1]=driver.findElement(By.xpath("//tr[@class='MuiTableRow-root']//td[3]//p[2]")).getText();

        //Get Actual Values
        for(int idx_field=1;idx_field<=cntFields;idx_field++){

            try{
                sFieldName_Value[idx_field][0] = driver.findElement(By.xpath("//tbody[@class='MuiTableBody-root']//tr[1]//td[4]//div//div[" + idx_field + "]//p[1]")).getText();
                sFieldName_Value[idx_field][1] = driver.findElement(By.xpath("//tbody[@class='MuiTableBody-root']//tr[1]//td[4]//div//div[" + idx_field + "]//p[2]")).getText();

                TestLogger.log("Field Name : " + sFieldName_Value[idx_field][0]);

            }catch(Exception e){
                break;
            }


        }

        //Get Expected Values
        String expectedValues = getBSLPreviewExpectedFields(ResourceColumnName);


        //Validation
        for(int i=0;i<=sFieldName_Value.length-1;i++){

            try{
                if(expectedValues.contains(sFieldName_Value[i][0])){
                    TestLogger.logPass(sFieldName_Value[i][0] + " found in expected list");
                }else{
                    TestLogger.logFail(sFieldName_Value[i][0] + " not found in expected list");
                    IsTestFail = true;            }

                if(!sFieldName_Value[i][1].equals("")){
                    TestLogger.logPass(sFieldName_Value[i][1] + " is appearing under " + sFieldName_Value[i][0]);
                }else{
                    TestLogger.logFail(" No Value is appearing under " + sFieldName_Value[i][0]);
                    IsTestFail = true;
                }

            }catch (Exception e){
                break;
            }


        }


        //Go Back to Home
        ClickOnHome();

    /*    if(IsTestFail==true){
            Assert.fail();
        }*/
        Assert.assertFalse(IsTestFail);



    }


    public void Check_BestsellerLink_BSLCreation() throws Exception {

        boolean IsTestFail=false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        CommonApi commonApi = new CommonApi();

        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='MuiFab-label']")).click();
        Thread.sleep(2000);


        //Click On Bestsellers Link
        driver.findElement(By.xpath(lnk_xpath_bestsellers)).click();
        TestLogger.log("Clicked on Bestsellets Link in Bestsellers list creation screen.");
        Thread.sleep(3000);

        if(IsElementPresent(lbl_xpath_RecentlyViewed,true)==false){ IsTestFail = true; }


        //Go Back to Home
        ClickOnHome();


        if(IsTestFail==true){
            Assert.fail();
        }



    }



    public void Checkpoint_BSLCreation() throws Exception {

        boolean IsTestFail=false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        CommonApi commonApi = new CommonApi();

        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='MuiFab-label']")).click();
        Thread.sleep(2000);

 /*       //Click On Bestsellers Link
        driver.findElement(By.xpath(lnk_xpath_bestsellers)).click();
        TestLogger.log("Clicked on Bestsellets Link in Bestsellers list creation screen.");
        Thread.sleep(3000);*/

        if(IsElementPresent(lbl_xpath_1,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_2,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_3,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_starttemplate,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_defineAttribute,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_NameList,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_CLEAR,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_VIEWSAVE,true)==false){ IsTestFail = true; }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }

    }



    public String getBSLPreviewExpectedFields(String ResourceColName) throws Exception {


        //Get Expected Values
        FileInputStream fis = new FileInputStream(sResourceFile);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet("BSLPreview");
        int TotalRowRes = sheet.getLastRowNum();

        String Rowval;
        String FieldNames = null;


        //get Column number
        int colNum = 0;
        do{

            try{
                if(sheet.getRow(0).getCell(colNum).toString().equals(ResourceColName)){
                    break;
                }

                colNum++;

            }catch (Exception e){
                TestLogger.logFail("Column Not found in the resource File : " + ResourceColName);
            }

        }while(colNum == 10);

        //grab expected data
        int rowNum = 0;
        String fieldName = "";
        do{

            try{
                if(fieldName.equals("")){
                    fieldName = sheet.getRow(rowNum).getCell(colNum).toString();
                }else{
                    fieldName = fieldName + "|" + sheet.getRow(rowNum).getCell(colNum).toString();
                }
            }catch(Exception e){
                break;
            }

            rowNum++;
        }while (rowNum<100);

        return fieldName;

    }


    public void BSLTemplateValidation(String datasource,String TamplateNames) throws Exception {

        boolean IsTestFail=false;

        String ExpetedList = TamplateNames;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource(datasource);

        CommonApi commonApi = new CommonApi();

        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='MuiFab-label']")).click();
        Thread.sleep(2000);

        //Split Expected List
        String[] splitExpectedList = ExpetedList.split("[|]");

        for(int idx=0;idx<=splitExpectedList.length-1;idx++){

            if(IsElementPresent("//div[@class='MuiGrid-root MuiGrid-item']//button[contains(@class,'MuiButtonBase-root MuiToggleButton-root')]//span//div[text()='" + splitExpectedList[idx] + "']",true)==false){ IsTestFail = true; }

        }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }


    }


    public void CheckDateRangeState() throws Exception {


        boolean IsTestFail = false;

        String datasource = "US BookScan";

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource(datasource);


        CommonApi commonApi = new CommonApi();

        //Select BestSeller Option
        driver.findElement(bt_leftContent_Bestsellers).click();
        TestLogger.log("Click on Bestsellers list from Left Navigation.");
        Thread.sleep(2000);

        //Check is Bestsellerlist is available
        String BSLName = "REG Test " + datasource;

        //Enter BSL Name in Search box
        driver.findElement(By.xpath(input_xpath_BSLSearch)).sendKeys(BSLName);
        TestLogger.log("Enter BSL Name : " + BSLName + " in Search Box");
        Thread.sleep(2000);

        String updatedBSL = BSLName;
//        int rowCnt = driver.findElements(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]")).size();
        int rowCnt = driver.findElements(By.xpath("//a[text()='" + updatedBSL + "']")).size();
        if(rowCnt<2){

            //Go Back to Home
            ClickOnHome();

            //Create Test BSL
            updatedBSL = CreateBestsellerList(BSLName);
            driver.findElement(bt_leftContent_Bestsellers).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(input_xpath_BSLSearch)).sendKeys(BSLName);
            Thread.sleep(2000);

        }

        //Open BSL
        driver.findElement(By.xpath("//a[text()='" + updatedBSL + "']")).click();
        Thread.sleep(3000);
        TestLogger.log("Open Bestseller List Called : " + updatedBSL);


        int TotalRow = driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size();
        if(TotalRow>0){
            TestLogger.logPass("Total Row record appeared : " + TotalRow);
        }else{
            TestLogger.logFail("Total Row record appeared : " + TotalRow);
            Assert.fail();
        }

        //Click on Date Range Filter
/*        driver.findElement(By.xpath("//div[contains(@class,'MuiSelect-root MuiSelect-select MuiSelect-selectMenu') and @id='mui-component-select-dataRangeFilter']")).click();
        TestLogger.log("Clicked on Date Range Drop Down to Open.");

        String DateRange = "Feb 16 2020 - Feb 22 2020";

        WebElement we_date = driver.findElement(By.xpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root MuiMenuItem-root') and text()='" + DateRange + "']"));

        //Scroll to the Element
        scrollDownToElement(driver,we_date);
        Thread.sleep(1000);*/

//        we_date.click();
//        TestLogger.log("Selected Date Range : " + DateRange);
//        Thread.sleep(3000);

        String xPath_LeftCalendeBT = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton' and @tabindex=0][1]";
        String xPath_TimeDD = "//input[@id='date-picker-inline-to']//parent::div//div[contains(@class,'MuiInputAdornment-root MuiInputAdornment-positionEnd')]";
        String xPath_Date = "//div[@class='MuiPickersCalendar-week'][2]";

        driver.findElement(By.xpath(xPath_TimeDD)).click();
        Thread.sleep(1000);
        //Go Left
        driver.findElement(By.xpath(xPath_LeftCalendeBT)).click();
        Thread.sleep(100);
        driver.findElement(By.xpath(xPath_LeftCalendeBT)).click();
        Thread.sleep(100);
        //Select Date
        driver.findElement(By.xpath(xPath_Date)).click();
        Thread.sleep(100);
        //Get Selected Date Value
        String sDateValue = driver.findElement(By.xpath("//input[@id='date-picker-inline-to']")).getAttribute("value");

        //Browser Back
        driver.navigate().back();
        Thread.sleep(1000);

        driver.navigate().forward();
        Thread.sleep(3000);

//        int cntDateRange = driver.findElements(By.xpath("//div[contains(@class,'MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-outlined') and text()='" + DateRange + "']")).size();
        String sDateValue2 = driver.findElement(By.xpath("//input[@id='date-picker-inline-to']")).getAttribute("value");

        if(sDateValue2.equals(sDateValue)){
            TestLogger.logPass("Date Range State persists");
        }else{
            TestLogger.logFail("Date Range State does not persist");
            TestLogger.logFail("Prev Date : " + sDateValue + " Current Date : " + sDateValue2);
            IsTestFail = true;

        }


        //Go Back to Home
        ClickOnHome();


        if(IsTestFail==true){
            Assert.fail();
        }



    }


}
