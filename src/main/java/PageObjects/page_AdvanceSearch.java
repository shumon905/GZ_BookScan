package PageObjects;


import BaseClass.CommonApi;
import BaseClass.DBUtil;
import Reporting.TestLogger;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class page_AdvanceSearch extends CommonApi {

    public String lbl_Author = "//label[text()='Author']";
    public String lbl_Supercategory = "//label[text()='Supercategory']";
    public String lbl_Subcategory = "//label[text()='Subcategory']";
    public String lbl_Bisac1 = "//label[text()='BISAC Level 1']";
    public String lbl_Bisac2 = "//label[text()='BISAC Level 2']";
    public String lbl_TitleKeyword = "//label[text()='Title Keyword']";
    public String lbl_Format = "//label[text()='Format']";
    public String lbl_Vintage = "//label[text()='Vintage']";
    public String lbl_PubDateRange = "//label[text()='Publication Date Range']";
    public String lbl_PriceRange = "//label[text()='Price Range']";
    public String lbl_RTDRange = "//label[text()='RTD Range']";
    public String lbl_weekSales = "//p[text()='User Defined Sales']";
    public String lbl_PageRange = "//label[text()='Page Range']";
    public String lbl_GeneralFilters = "//p[text()='General Filters']";
    public String lbl_Ranges = "//p[text()='Ranges']";
    public String lbl_Publisher = "//label[text()='Publisher']";

    public String txt_Author = "//input[@id='author']";
    public String bt_xpath_AdvanceSearch = "//span[text()='Advanced Search']";
//    public String txt_xpath_publisher = "//input[contains(@id,'downshift') and @placeholder='Search up to 10 publishers']";
    public String txt_xpath_publisher = "//div[contains(@class,'MuiGrid-root MuiGrid-item')]//ancestor::div[text()='Search up to 10 publishers']";



//    public String txt_xpath_superCategory = "//div[contains(@class,'MuiGrid-root MuiGrid-item')]//ancestor::div[text()='Search up to 5 Supercategories']]";
    public String txt_xpath_superCategory = "//div[contains(@class,'MuiGrid-root')]//ancestor::div[text()='Search up to 5 Supercategories']";
    private String input_xPath_superCategory = "//input[@placeholder='Search up to 5 Supercategories']";

    public String txt_xpath_subCategory = "//div[contains(@class,'MuiGrid-root MuiGrid-item')]//ancestor::div[text()='Search up to 5 Subcategories']";
    private String input_xPath_subCategory = "//input[@placeholder='Search up to 5 Subcategories']";


    public String txt_xpath_bisac1 = "//div[contains(@class,'MuiGrid-root MuiGrid-item')]//ancestor::div[text()='Search up to 5 BISAC Level 1s']";
    private String input_xPath_bisac1 = "//input[@placeholder='Search up to 5 BISAC Level 1s']";
    public String txt_xpath_bisac2 = "//div[contains(@class,'MuiGrid-root MuiGrid-item')]//ancestor::div[text()='Search up to 5 BISAC Level 2s']";
    private String input_xPath_bisac2 = "//input[@placeholder='Search up to 5 BISAC Level 2s']";
    public String txt_xpath_titlekeyword = "//input[@id='title']";
    public String dd_format = "//div[@id='mui-component-select-format']";
    public String dd_vintage = "//div[@id='mui-component-select-vintage']";
    public String txt_start_PubDataRange = "//label[text()='Publication Date Range']//parent::div//parent::div[@class='MuiGrid-root MuiGrid-item']//child::div//child::div[1]//child::div//child::div//child::input[@id='date-picker-inline-to']";
    public String txt_end_PubDataRange = "//label[text()='Publication Date Range']//parent::div//parent::div[@class='MuiGrid-root MuiGrid-item']//child::div//child::div[3]//child::div//child::div//child::input[@id='date-picker-inline-to']";
    public String txt_start_SalesRange = "//p[text()='User Defined Sales']//parent::div//parent::div//div[2]//input[@id='date-picker-inline-to']";
    public String txt_end_SalesRange = "//p[text()='User Defined Sales']//parent::div//parent::div//div[4]//input[@id='date-picker-inline-to']";
    public String txt_PriceFrom = "//input[@id='priceFrom']";
    public String txt_PriceTo = "//input[@id='priceTo']";
    public String txt_RTDFrom = "//input[@id='rtdUnitsFrom']";
    public String txt_RTDTo = "//input[@id='rtdUnitsTo']";
    public String txt_PageFrom = "//input[@id='pagesFrom']";
    public String txt_PageTo = "//input[@id='pagesTo']";
    private String bt_xpath_RefineSearch= "//span[@class='MuiButton-label' and text()='Refine Search']";

    public String bt_Apply = "//span[text()='APPLY']";
    public String bt_Cancel = "//span[text()='CANCEL']";

    public String lnk_ClearAll_Enable = "//div[contains(@id,'vertical-tabpanel-0')]//ancestor::span[text()='Clear All']";
    public String lnk_ClearAll_Disable = "//button[contains(@class,'Mui-disabled Mui-disabled')]//ancestor::span[text()='Clear All']";
    public String bt_xpath_ISBNSearch = "//span[text()='Multi-ISBN Search']";


    public String dd_xpath_contains = "//div[contains(@id,'downshift-multiple-item-')]";

    By bt_AdvanceSearch = By.xpath(bt_xpath_AdvanceSearch);
    By txt_superCategory = By.xpath(txt_xpath_superCategory);
    By txt_subCategory = By.xpath(txt_xpath_subCategory);
    By txt_bisac1 = By.xpath(txt_xpath_bisac1);
    By txt_bisac2 = By.xpath(txt_xpath_bisac2);


    //This Methos Check all the Lemnets in Advance Search
    public void CheckPointAdvanceSearchElements() throws Exception {



        //Page Object
        page_AdvanceSearch page_advanceSearch = new page_AdvanceSearch();

        //Navigate To Advance Search
        page_advanceSearch.NaviagateToAdvanceSearch();

        boolean overallresult = true;

        //Check Labels Present
        if(IsElementPresent(lbl_Author,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_Supercategory,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_Subcategory,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_Bisac1,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_Bisac2,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_TitleKeyword,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_Format,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_Vintage,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_PubDateRange,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_PriceRange,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_RTDRange,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_weekSales,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_PageRange,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_GeneralFilters,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_Publisher,true)==false){ overallresult = false; }
        if(IsElementPresent(lbl_Ranges,true)==false){ overallresult = false; }

        if(IsElementPresent(txt_Author,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_xpath_publisher,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_xpath_superCategory,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_xpath_subCategory,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_xpath_bisac1,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_xpath_bisac2,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_xpath_titlekeyword,true)==false){ overallresult = false; }
        if(IsElementPresent(dd_format,true)==false){ overallresult = false; }
        if(IsElementPresent(dd_vintage,true)==false){ overallresult = false; }

        if(IsElementPresent(txt_start_PubDataRange,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_end_PubDataRange,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_start_SalesRange,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_end_SalesRange,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_PriceFrom,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_PriceTo,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_RTDFrom,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_RTDTo,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_PageFrom,true)==false){ overallresult = false; }
        if(IsElementPresent(txt_PageTo,true)==false){ overallresult = false; }
        if(IsElementPresent(bt_Apply,true)==false){ overallresult = false; }
        if(IsElementPresent(bt_Cancel,true)==false){ overallresult = false; }


        //Go Back to Home
        ClickOnHome();

        if(overallresult==false){
            Assert.fail();
        }



    }




    public void getItemsfromSearchDropDown() throws Exception {


        //Page Object
        page_AdvanceSearch page_advanceSearch = new page_AdvanceSearch();

        //Navigate To Advance Search
        page_advanceSearch.NaviagateToAdvanceSearch();

        //Click Super Category Drop Down
        driver.findElement(txt_bisac2).click();
        Thread.sleep(3000);

        //getAllOptions(By.id("downshift-multiple-input"));
        System.out.println(driver.findElements(By.xpath(dd_xpath_contains)).size());

        int TotalCount = driver.findElements(By.xpath(dd_xpath_contains)).size();

        Actions action  = new Actions(driver);



        for(int i = 0; i<=TotalCount-1;i++){
            action.sendKeys(Keys.DOWN).build().perform();
            System.out.println(driver.findElements(By.xpath(dd_xpath_contains)).get(i).getText());

        }


    }


    public void CheckAdvanceSearchFunctionality(String FieldName,String SearchKey,String FieldType) throws Exception {


        boolean IsFailed = false;

        //Log in if already not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Derive xPath
        String xPath_field = getxPath(FieldName);

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        //Page Object
        page_AdvanceSearch page_advanceSearch = new page_AdvanceSearch();
        Thread.sleep(3000);

        //Select Datasource


        //Navigate To Advance Search
        page_advanceSearch.NaviagateToAdvanceSearch();

        //Clear All the Fields
        page_advanceSearch.ClearADVSrchFields();

        //Exception for Publisher
        if(FieldName.equals("Publisher")){

            //Open Drop Down
            driver.findElement(By.xpath(xPath_field)).click();
            Thread.sleep(2000);

            //Enter Key
            driver.findElement(By.xpath("//input[@placeholder='Search up to 10 publishers']")).sendKeys(SearchKey);
            Thread.sleep(200);

        }


        //Select From Drop Down
        if(FieldType.equals("Drop Down")){


            //Drop down is already open for Publisher in prior step
            if(FieldName.equals("Publisher")){

            }else{
                //Open Drop Down
                driver.findElement(By.xpath(xPath_field)).click();
                Thread.sleep(2000);
//                driver.findElement(By.xpath(xPath_field)).sendKeys(SearchKey);
//                Thread.sleep(2000);

            }


//            driver.findElement(By.xpath("//div[contains(@class,'MuiButtonBase-root MuiListItem-root MuiMenuItem-root') and text()='" + SearchKey + "']")).click();
            driver.findElement(By.xpath("//input[@data-text='" + SearchKey + "']")).click();
            Thread.sleep(200);
        }else{

            //Enter Key
            driver.findElement(By.xpath(xPath_field)).sendKeys(SearchKey);
            Thread.sleep(200);

        }

        //Close Drop Down
        closeASOpenDropDown();
/*        String ddicon = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall']";
        int ddCount = driver.findElements(By.xpath(ddicon)).size();

        for(int i=0;i<=ddCount;i++){

            try{
                driver.findElements(By.xpath(ddicon)).get(i).click();
            }catch (Exception e){
                continue;
            }

        }*/


        Thread.sleep(2000);
        //Click on Apply Button
        driver.findElement(By.xpath(bt_Apply)).click();
        Thread.sleep(200);

        boolean IsResultAppearing = IsSearchResultAppearing();

        if(IsResultAppearing==false){
            IsFailed =true;
        }


        if(IsFailed==true){
            Assert.fail();

        }
/*        //Wait for search Result
        waitUntilVisible("//tr[contains(@class,'MuiTableRow-root')]");

        //Check Search Result
//        int TotalRow = driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size();
        if(driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size()>1){
            int TotalRow = driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size();
            TestLogger.logPass("Total Row record appeared : " + TotalRow);
        }else{
            TestLogger.logFail("Total Row record appeared");
            Assert.fail();
        }*/

        //Go Back to Home
        ClickOnHome();






    }


    public void CloseDateDropDown() throws Exception {

        //Close Year Drop Down
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
        Thread.sleep(3000);
        TestLogger.logPass("Year Drop Down has been Closed.");

    }



    public void AdvanceSearchRangeCheck(String FieldName,String dataFrom,String dataTo) throws Exception {

        boolean IsFailed = false;

        //Log in if already not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


//        //Derive xPath
//        String xPath_field = getxPath(FieldName);

        //Page Object
        page_AdvanceSearch page_advanceSearch = new page_AdvanceSearch();
        Thread.sleep(3000);

        //Navigate To Advance Search
        page_advanceSearch.NaviagateToAdvanceSearch();

        //Clear All the Fields
        page_advanceSearch.ClearADVSrchFields();

        //Derive xPath
        String xPath_start_field = getxPath(FieldName + " - start");
        String xPath_end_field = getxPath(FieldName + " - end");

        //Enter Key
        driver.findElement(By.xpath(xPath_start_field)).sendKeys(dataFrom);
        Thread.sleep(200);

        driver.findElement(By.xpath(xPath_end_field)).sendKeys(dataTo);
        Thread.sleep(200);


        //Click on Apply Button
        driver.findElement(By.xpath(bt_Apply)).click();
        Thread.sleep(5000);

        //Check Search Result
        boolean IsResultAppearing = IsSearchResultAppearing();

        if(IsResultAppearing==false){
            IsFailed = true;
        }

/*
        //Wait for search Result
        waitUntilVisible("//tr[contains(@class,'MuiTableRow-root')]");

        //Check Search Result
//        int TotalRow = driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size();
        if(driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size()>1){
            int TotalRow = driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size();
            TestLogger.logPass("Total Row record appeared : " + TotalRow);
        }else{
            TestLogger.logFail("No Data is appearing");
            Assert.fail();
        }
*/

        //Go Back to Home
        ClickOnHome();

        if(IsFailed==true){
            Assert.fail();
        }

    }


    public void CheckAllFilterAdvanceSearch() throws Exception {

        boolean IsFailed = false;

        //Log in if already not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //        //Derive xPath
//        String xPath_field = getxPath(FieldName);

        //Page Object
        page_AdvanceSearch page_advanceSearch = new page_AdvanceSearch();
        Thread.sleep(3000);

        //Navigate To Advance Search
        page_advanceSearch.NaviagateToAdvanceSearch();

        //Clear All the Fields
        page_advanceSearch.ClearADVSrchFields();

        //Enter Author
        //Enter Key
        driver.findElement(By.xpath(txt_Author)).sendKeys("J. K. Rowling");
        Thread.sleep(200);
        TestLogger.logPass("Entered Author Name : J. K. Rowling");

        if(SelectDropDownValue(txt_xpath_superCategory,input_xPath_superCategory,"Adult Fiction")==false){
            IsFailed = true;
        }


        //Select Sub Category
        if(SelectDropDownValue(txt_xpath_subCategory,input_xPath_subCategory,"General Fiction - AF")==false){
            IsFailed = true;
        }

        //BISAC Level 1

        if(SelectDropDownValue(txt_xpath_bisac1,input_xPath_bisac1,"Fiction")==false){
            IsFailed=true;
        }

        //BISAC Level 2

//        if(SelectDropDownValue(txt_xpath_bisac2,input_xPath_bisac2,"Juvenile Fiction / Fantasy & Magic")==false){
//            IsFailed=true;
//        }

        //Enter Publication Date Range
        EnterRangeValues(txt_start_PubDataRange,txt_end_PubDataRange,"01012010","04032020");

        //Enter User Defined Sales Date Range
        EnterRangeValues(txt_start_SalesRange,txt_end_SalesRange,"01012010","04032020");

        //Enter Price Range
        EnterRangeValues(txt_PriceFrom,txt_PriceTo,"0","1000");

        //Enter RTD Range
        EnterRangeValues(txt_RTDFrom,txt_RTDTo,"0","5000000");

        Thread.sleep(2000);
        //Click on Apply Button
        driver.findElement(By.xpath(bt_Apply)).click();
        TestLogger.log("Click On Apply Button.");
        Thread.sleep(5000);

        boolean ResultResultappearing = IsSearchResultAppearing();

        if(ResultResultappearing==false){
            IsFailed = true;
        }

        //Click on Refine Refine Search
        if(ClickOnRefineSearch()==false){
            IsFailed = true;
        }

        //Clear All the Fields
        page_advanceSearch.ClearADVSrchFields();

        //Go Back to Home
        ClickOnHome();

        Thread.sleep(3000);

        if(IsFailed==true){
            Assert.fail();
        }

    }



    public void ExportAdvanceSearchResults() throws Exception {

        boolean IsFailed = false;

        //Log in if already not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //Page Object
        page_AdvanceSearch page_advanceSearch = new page_AdvanceSearch();
        Thread.sleep(3000);

        //Navigate To Advance Search
        page_advanceSearch.NaviagateToAdvanceSearch();

        //Clear All the Fields
        page_advanceSearch.ClearADVSrchFields();

        //Enter Author
        //Enter Key
        driver.findElement(By.xpath(txt_Author)).sendKeys("J. K. Rowling");
        Thread.sleep(200);
        TestLogger.logPass("Entered Author Name : J. K. Rowling");


        Thread.sleep(2000);
        //Click on Apply Button
        driver.findElement(By.xpath(bt_Apply)).click();
        TestLogger.log("Click On Apply Button.");
        Thread.sleep(5000);

        boolean ResultResultappearing = IsSearchResultAppearing();

        if(ResultResultappearing==false){
            IsFailed = true;
        }

        //Export
        page_BasicSearch page_basicSearch = new page_BasicSearch();
        page_basicSearch.ClickONExportExcel();

        //Check if File Exists
        boolean fileCheck = page_basicSearch.ExportedFileCheck(1000);

        //Click on Refine Refine Search
        if(ClickOnRefineSearch()==false){
            IsFailed = true;
        }

        //Clear All the Fields
        page_advanceSearch.ClearADVSrchFields();

        //Go Back to Home
        ClickOnHome();

        Thread.sleep(3000);

        if(IsFailed==true){
            Assert.fail();
        }


    }


 /*   public boolean IsSearchResultAppearing() throws Exception {

        int waitCouter = 0;
        do{

            TimeUnit.SECONDS.sleep(1);
            waitCouter ++;

            if(waitCouter>60){
                break;
            }

            System.out.println("Record Count : "+ driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size());

        }while(driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size()<2);

        //Wait for search Result
        waitUntilVisible("//tr[contains(@class,'MuiTableRow-root')]");

        //Check Search Result
        if(driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size()>1){
            int TotalRow = driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size();
            TestLogger.logPass("Total Row record appeared : " + TotalRow);
            TestLogger.log("Search took : " + waitCouter + " seconds.");
            return true;
        }else{
            TestLogger.logFail("No Records are appearing after 60 Seconds.");
            return false;

        }


    }*/


    public boolean ClickOnRefineSearch() throws Exception {

        boolean refineSearch = false;

        driver.findElement(By.xpath(bt_xpath_RefineSearch)).click();
        TestLogger.log("Clicked On Refine Search Button.");

        //Wait Until Advance Search Screen is visible

        if(waitUntilVisible(txt_Author)==true){
            waitUntilVisible(txt_Author);
            TestLogger.log("Navigated To Filtered Search Screen.");
            return true;

        }else{
            TestLogger.logFail("Unable to navigate to Advance Search Screen");
            return false;
        }


    }

    public boolean SelectDropDownValue(String xPath,String xPath_input, String valueToSelect) throws Exception {

        boolean itemfound = true;

        //Open Drop Down
        driver.findElement(By.xpath(xPath)).click();
        Thread.sleep(2000);
        TestLogger.log("Clicked On Drop Down : " + xPath);

        //Write Name of the Selection
        driver.findElement(By.xpath(xPath_input)).sendKeys(valueToSelect);
        TestLogger.log("Entered Drop Down Value : " + valueToSelect);
        Thread.sleep(200);


//        WebElement webEle = driver.findElement(By.xpath("//input[@data-text='" + valueToSelect + "']"));

        int item_cnt = driver.findElements(By.xpath("//input[@data-text='" + valueToSelect + "']")).size();
        if(item_cnt>0){
            TestLogger.logPass(valueToSelect + " found in the dropdown " + xPath);
            ScrollTotheElemnet("//input[@data-text='" + valueToSelect + "']");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@data-text='" + valueToSelect + "']")).click();
            TestLogger.log("Selected Drop Down Value : " + valueToSelect);
            Thread.sleep(200);
            //Close Drop Down
            closeASOpenDropDown();
            Thread.sleep(2000);
            return true;
        }else{
            TestLogger.logFail(valueToSelect + " did not find in the dropdown " + xPath);
            //Close Drop Down
            closeASOpenDropDown();
            Thread.sleep(2000);
            return false;
        }

    }

    public void EnterRangeValues(String xPath_From,String xPath_To,String FromValue,String ToValue) throws Exception {

        //Enter Key
        driver.findElement(By.xpath(xPath_From)).sendKeys(FromValue);
        Thread.sleep(200);
        TestLogger.log("Range Enter -> From Field : " + xPath_From + " Value : " + FromValue);

        driver.findElement(By.xpath(xPath_To)).sendKeys(ToValue);
        Thread.sleep(200);
        TestLogger.log("Range Enter -> To Field : " + xPath_To + " Value : " + ToValue);

    }



/*    public void closeASOpenDropDown(){
        //Close Drop Down
        String ddicon = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall']";
        int ddCount = driver.findElements(By.xpath(ddicon)).size();

        for(int i=0;i<=ddCount;i++){

            try{
                driver.findElements(By.xpath(ddicon)).get(i).click();
                TestLogger.log("Closed Drop Down Value.");
            }catch (Exception e){
                continue;
            }

        }
    }*/

    public String getxPath(String FieldName){

        switch (FieldName){
            case "Author":
                return txt_Author;

            case "Supercategory":
                return txt_xpath_superCategory;

            case "Subcategory":
                return txt_xpath_subCategory;

            case "BISAC1":
                return txt_xpath_bisac1;

            case "BISAC2":
                return txt_xpath_bisac2;

            case "Publisher":
                return txt_xpath_publisher;

            case "TitleKeyword":
                return txt_xpath_titlekeyword;

            case "Publication Date Range - start":
                return txt_start_PubDataRange;

            case "Publication Date Range - end":
                return txt_end_PubDataRange;

            case "User Defined Sales Date Range - start":
                return txt_start_SalesRange;

            case "User Defined Sales Date Range - end":
                return txt_end_SalesRange;

            case "Price Range - start":
                return txt_PriceFrom;

            case "Price Range - end":
                return txt_PriceTo;

            case "RTD Range - start":
                return txt_RTDFrom;

            case "RTD Range - end":
                return txt_RTDTo;

            case "Page Range - start":
                return txt_PageFrom;

            case "Page Range - end":
                return txt_PageTo;

            default:

        }

        return "xPath Not Found";

    }


    public void NaviagateToAdvanceSearch() throws Exception {


        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);


        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");
        Thread.sleep(3000);

        //Navigate To Advance Search
        //Click on Advance Search Button
        int cntAdvanceSrch = driver.findElements(bt_AdvanceSearch).size();

        if(cntAdvanceSrch>0){
            driver.findElement(bt_AdvanceSearch).click();
            Thread.sleep(3000);
            TestLogger.log("Cliecked on Advance Search Link.");

        }else{
            TestLogger.logFail("Unable to Find Advance Search link");
        }

        //Wait Until Advance Search Screen is visible
        waitUntilVisible(txt_Author);
        TestLogger.log("Navigated To Filtered Search Screen.");

    }



    List<String> getAllOptions(By by) {
        List<String> options = new ArrayList<String>();
        for (WebElement option : new Select(driver.findElement(by)).getOptions()) {
            String txt = option.getText();
            if (option.getAttribute("value") != "") options.add(option.getText());
            System.out.println(option.getAttribute("value"));
        }
        return options;
    }


/*    public void CheckDataIntegrity() throws Exception {

        //Go Back to Home
        ClickOnHome();

*//*        //Derive xPath
        String xPath_field = getxPath(FieldName);*//*
        //Page Object
        page_AdvanceSearch page_advanceSearch = new page_AdvanceSearch();

        page_advanceSearch.NavigateToISBNScreen();


    }*/



    //Navigate To ISBN Search Screen
    public void CheckDataIntegrity(String datasource,String SheetName) throws Exception {


        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
//        page_landingPage.SelectDatasource("US BookScan POS - Ops/Product Total Market");
        page_landingPage.SelectDatasource(datasource);


        //Page Object
        page_AdvanceSearch page_advanceSearch = new page_AdvanceSearch();
        Thread.sleep(3000);

        //Navigate To Advance Search
        page_advanceSearch.NaviagateToAdvanceSearch();

        //Clear All the Fields
        page_advanceSearch.ClearADVSrchFields();


        DBUtil dbUtil = new DBUtil();


        //Click on ISBN Search
        driver.findElement(By.xpath(bt_xpath_ISBNSearch)).click();

//        String sISBNs = "9780064430104|9780064430111|9780395252963";
//        String[] splitISBNs = sISBNs.split("[|]");

        String row_xPath_Price;
        String row_xPath_RTD;
        String row_xPath_TW;
        String row_xPath_YTD;

        String exp_isbn = null;
        String exp_Price;
        String exp_RTD = null;
        String exp_TW = null;
        String exp_YTD = null;
        String exp_WKSales;
        String exp_WKYTD;
        String exp_WKRTD;

        String exp_Week;
        String acc_isbn;
        String acc_Price;
        String acc_RTD;
        String acc_TW;
        String acc_YTD;
        String acc_WKSales;
        String acc_WKYTD;
        String acc_WKRTD;

        int acc_WKSales_int;
        int acc_WKYTD_int;
        int acc_WKRTD_int;
        int exp_WKSales_int;
        int exp_WKYTD_int;
        int exp_WKRTD_int;

        double diff_WKSales;
        double diff_WKYTD;
        double diff_WKRTD;


        boolean overallFail = false;


        //Get Expected Values
        FileInputStream fis = new FileInputStream(sResourceFile);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet(SheetName);
        int TotalRow = sheet.getLastRowNum();

        String sISBNs = "";


        //Get ISBNs
        for(int ex_row=0;ex_row<=sheet.getLastRowNum()-1;ex_row++){

            if(sISBNs.equals("")){
                sISBNs = sheet.getRow(ex_row+1).getCell(0).toString();
            }else{
                sISBNs = sISBNs + "|" + sheet.getRow(ex_row+1).getCell(0).toString();
            }

        }

//        String sISBNs = "9780064430104|9780064430111|9780395252963";
        String[] splitISBNs = sISBNs.split("[|]");


        //Check Data from Basic Search
        for(int i_split=0;i_split<=splitISBNs.length-1;i_split++){


            driver.findElement(By.xpath("//input[@id='isbn-search-" + i_split + "']")).sendKeys(splitISBNs[i_split]);
            driver.findElement(By.xpath("//input[@id='isbn-search-" + i_split + "']")).sendKeys(Keys.ENTER);
            Thread.sleep(300);
            driver.findElement(By.xpath("//input[@id='isbn-search-" + i_split + "']")).sendKeys(Keys.ENTER);
            Thread.sleep(300);

        }

        //Click on Apply Button
        driver.findElement(By.xpath(bt_Apply)).click();
        Thread.sleep(200);



        int sRow = 0;

        for(int idxIsbn=0;idxIsbn<=splitISBNs.length-1;idxIsbn++){

            row_xPath_Price = "//tbody[@class='MuiTableBody-root']//ancestor::a[text()='" + splitISBNs[idxIsbn] + "']//parent::td[contains(@class,'MuiTableCell-root MuiTableCell-body')]//following-sibling::td[3]";
            row_xPath_RTD = "//tbody[contains(@class,'MuiTableBody-root')]//ancestor::a[text()='" + splitISBNs[idxIsbn] + "']//parent::td[contains(@class,'MuiTableCell-root MuiTableCell-body')]//following-sibling::td[7]";
            row_xPath_TW = "//tbody[contains(@class,'MuiTableBody-root')]//ancestor::a[text()='" + splitISBNs[idxIsbn] + "']//parent::td[contains(@class,'MuiTableCell-root MuiTableCell-body')]//following-sibling::td[8]";
            row_xPath_YTD = "//tbody[contains(@class,'MuiTableBody-root')]//ancestor::a[text()='" + splitISBNs[idxIsbn] + "']//parent::td[contains(@class,'MuiTableCell-root MuiTableCell-body')]//following-sibling::td[9]";


//            acc_Price = driver.findElement(By.xpath(row_xPath_Price)).getText().replace(",","");
            acc_RTD = driver.findElement(By.xpath(row_xPath_RTD)).getText().replace(",","");
            acc_TW = driver.findElement(By.xpath(row_xPath_TW)).getText().replace(",","");
            acc_YTD = driver.findElement(By.xpath(row_xPath_YTD)).getText().replace(",","");


            //Expected Value(get it from database)
/*            ResultSet rs_rtd = dbUtil.ConnectSybase("SELECT unitssold_rtd FROM bsc_mkt_bnw_D1.w_aggr_ytd WHERE upc_code='" + splitISBNs[idxIsbn] + "' AND ppWeek = 2613");
            ResultSet rs_ytd = dbUtil.ConnectSybase("SELECT unitssold_ytd FROM bsc_mkt_bnw_D1.w_aggr_ytd WHERE upc_code='" + splitISBNs[idxIsbn] + "' AND ppWeek = 2613");
            ResultSet rs_tw = dbUtil.ConnectSybase("SELECT unitssold_py FROM bsc_mkt_bnw_D1.w_aggr_ytd WHERE upc_code='" + splitISBNs[idxIsbn] + "' AND ppWeek = 2613");*/

/*
            if(datasource.equals("US BookScan POS - Admin")){
                exp_RTD = getSalesData_DB("SELECT Round(proj_unitssold_rtd,0) FROM bsc_mkt_usw_d2.w_aggr_ytd WHERE upc_code='" + splitISBNs[idxIsbn] + "' AND ppWeek = 2613");
                exp_YTD = getSalesData_DB("SELECT Round(proj_unitssold_ytd,0) FROM bsc_mkt_usw_d2.w_aggr_ytd WHERE upc_code='" + splitISBNs[idxIsbn] + "' AND ppWeek = 2613");
                exp_TW = getSalesData_DB("SELECT Round(proj_unitssold,0) FROM bsc_mkt_usw_d2.w_aggr_ytd WHERE upc_code='" + splitISBNs[idxIsbn] + "' AND ppWeek = 2613");

            }else if(datasource.equals("US BookScan POS - Dashboard - Walmart")){



            }
*/


/*            for(int i_excelRow=1;i_excelRow<=TotalRow;i_excelRow++){
                exp_isbn = sheet.getRow(i_excelRow).getCell(0).toString();

                if(exp_isbn.equals(splitISBNs[idxIsbn])){
                    sRow = i_excelRow;
                    break;
                }

            }*/

  /*          exp_Price = sheet.getRow(sRow).getCell(1).toString();
            exp_RTD = sheet.getRow(sRow).getCell(2).toString();
            exp_TW = sheet.getRow(sRow).getCell(3).toString();
            exp_YTD = sheet.getRow(sRow).getCell(4).toString();
*/
/*            if(acc_Price.equals(exp_Price)){
                TestLogger.logPass("Price Matched : " + acc_Price + " for ISBN : " + exp_isbn);
            }else{
                TestLogger.logFail("Price did not match. Expected : " + exp_Price + " Actual : " + acc_Price + " for ISBN : " + exp_isbn);
                overallFail = true;
            }*/


/*
            if(acc_RTD.equals(exp_RTD)){
                TestLogger.logPass("RTD Matched : " + acc_RTD + " for ISBN : " + splitISBNs[idxIsbn]);
            }else{
                TestLogger.logFail("RTD did not match. Expected : " + exp_RTD + " Actual : " + acc_RTD + " for ISBN : " + splitISBNs[idxIsbn]);
                overallFail = true;
            }


            if(acc_TW.equals(exp_TW)){
                TestLogger.logPass("TW Matched : " + acc_TW + " for ISBN : " + splitISBNs[idxIsbn]);
            }else{
                TestLogger.logFail("TW did not match. Expected : " + exp_TW + " Actual : " + acc_TW + " for ISBN : " + splitISBNs[idxIsbn]);
                overallFail = true;
            }

            if(acc_YTD.equals(exp_YTD)){
                TestLogger.logPass("YTD Matched : " + acc_YTD + " for ISBN : " + splitISBNs[idxIsbn]);
            }else{
                TestLogger.logFail("YTD did not match. Expected : " + exp_YTD + " Actual : " + acc_YTD + " for ISBN : " + splitISBNs[idxIsbn]);
                overallFail = true;
            }
*/


        }



        //Check Data from Sales And Rank History
        for(int i_isbn_SR=0;i_isbn_SR <=TotalRow-1;i_isbn_SR++){

            //Click ISBN
            driver.findElement(By.xpath("//a[text()='" + splitISBNs[i_isbn_SR] + "']")).click();
            Thread.sleep(2000);

            exp_Week = sheet.getRow(i_isbn_SR+1).getCell(1).toString();

            acc_WKSales = driver.findElement(By.xpath("//td[text()='" + exp_Week + "']//following-sibling::td[1]")).getText();
            acc_WKYTD = driver.findElement(By.xpath("//td[text()='" + exp_Week + "']//following-sibling::td[2]")).getText();
            acc_WKRTD = driver.findElement(By.xpath("//td[text()='" + exp_Week + "']//following-sibling::td[3]")).getText();

            exp_WKSales = sheet.getRow(i_isbn_SR+1).getCell(2).toString();
            exp_WKYTD = sheet.getRow(i_isbn_SR+1).getCell(3).toString();
            exp_WKRTD = sheet.getRow(i_isbn_SR+1).getCell(4).toString();

            //converted Value
            acc_WKSales_int = Integer.valueOf(acc_WKSales);
            exp_WKSales_int = Integer.valueOf(exp_WKSales);
            acc_WKYTD_int = Integer.valueOf(acc_WKYTD.replace(",",""));
            exp_WKYTD_int = Integer.valueOf(exp_WKYTD.replace(",",""));
            acc_WKRTD_int = Integer.valueOf(acc_WKRTD.replace(",",""));
            exp_WKRTD_int = Integer.valueOf(exp_WKRTD.replace(",",""));

            diff_WKSales = Math.abs(acc_WKSales_int-exp_WKSales_int);
            diff_WKRTD = Math.abs(acc_WKRTD_int-exp_WKRTD_int);
            diff_WKYTD = Math.abs(acc_WKYTD_int-exp_WKYTD_int);

//            if(acc_WKSales.equals(exp_WKSales)){
            if(diff_WKSales <=3){
                TestLogger.logPass("Weekly Sales Matched : " + acc_WKSales + " for ISBN : " + splitISBNs[i_isbn_SR]);
            }else{
                TestLogger.logFail("Weekly Sales did not match. Expected : " + exp_WKSales + " Actual : " + acc_WKSales + " for ISBN : " + splitISBNs[i_isbn_SR] + " Week : " + exp_Week);
                overallFail = true;

            }

//            if(acc_WKYTD.equals(exp_WKYTD)){
            if(diff_WKYTD <=3){
                TestLogger.logPass("Weekly YTD Sales Matched : " + acc_WKYTD + " for ISBN : " + splitISBNs[i_isbn_SR]);
            }else{
                TestLogger.logFail("Weekly YTD Sales did not match. Expected : " + exp_WKYTD + " Actual : " + acc_WKYTD + " for ISBN : " + splitISBNs[i_isbn_SR] + " Week : " + exp_Week);
                overallFail = true;

            }


//            if(acc_WKRTD.equals(exp_WKRTD)){
            if(diff_WKRTD <=3){
                TestLogger.logPass("Weekly RTD Sales Matched : " + acc_WKRTD + " for ISBN : " + splitISBNs[i_isbn_SR]);
            }else{
                TestLogger.logFail("Weekly RTD Sales did not match. Expected : " + exp_WKRTD + " Actual : " + acc_WKRTD + " for ISBN : " + splitISBNs[i_isbn_SR] + " Week : " + exp_Week);
                overallFail = true;

            }


            //Navigate To Back Screen
            driver.navigate().back();
            Thread.sleep(3000);


        }

        if(overallFail==true){
            Assert.fail();
        }



/*
        //Write ISBN
        driver.findElement(By.xpath("//input[@id='isbn-search-0']")).sendKeys("9780064430104");
        Thread.sleep(300);
        driver.findElement(By.xpath("//input[@id='isbn-search-0']")).sendKeys(Keys.ENTER);
        Thread.sleep(300);

        driver.findElement(By.xpath("//input[@id='isbn-search-1']")).sendKeys("9780064430111");
        Thread.sleep(300);
        driver.findElement(By.xpath("//input[@id='isbn-search-1']")).sendKeys(Keys.ENTER);
        Thread.sleep(300);

        driver.findElement(By.xpath("//input[@id='isbn-search-2']")).sendKeys("9780395252963");
        Thread.sleep(300);
*/


        Thread.sleep(3000);
        //Go Back to Home
        ClickOnHome();


    }



    //ClearAll
    public void ClearADVSrchFields() throws Exception {

        if(driver.findElements(By.xpath(lnk_ClearAll_Disable)).size()>1){
            TestLogger.log("Clear All Button is disabled");
        }else{
            driver.findElement(By.xpath(lnk_ClearAll_Enable)).click();
            TestLogger.log("Clicked on Cleared All button.");
            Thread.sleep(3000);
        }


    }


    public String getSalesData_DB(String query) throws Exception {

        DBUtil dbUtil = new DBUtil();
        ResultSet rs = dbUtil.ConnectSybase(query);
        String salesVal = null;
        
        if (rs.next()) {
//            Date currentDate = rs.getDate(1); // get first column returned
             salesVal = rs.getString(1).replace(".0","");
//            int result = Integer.parseInt(dbval);

//            salesVal = Integer.toString(result);
        }
        
        return salesVal;
    }




}
