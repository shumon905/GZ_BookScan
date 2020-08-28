package PageObjects;

import BaseClass.CommonApi;
import Reporting.TestLogger;
import org.openqa.selenium.By;
import org.testng.Assert;

public class page_Collections extends CommonApi {


    private String bt_xpath_leftContent_Collections = "//div[contains(@class,'MuiDrawer-root MuiDrawer-docked')]//ancestor::div[text()='Collections']";
    private String bt_xpath_AddToCollection = "//span[text()='Add to Collection']";
    private String txt_xpath_EnterCollectionName = "//input[@placeholder='Name Your Collection']";
    private String bt_xpath_Save = "//span[text()='Save']";
    private String bt_xpath_ViewCollection = "//span[text()='View Collection']";
    private String lbl_xpath_NoCollection = "//p[contains(@class,'MuiTypography-root') and text()='You do not have any collections.']";
    private String lbl_xpath_RC_Created = "//span[contains(@class,'MuiTypography-root') and contains(text(),'Created:')]";
    private String lbl_xpath_RC_listcount = "//span[contains(@class,'MuiTypography-root') and contains(text(),'3')]";
    private String lbl_xpath_RC_lastupdate = "//span[contains(@class,'MuiTypography-root') and contains(text(),'Last accessed')]";
    private String lbl_xpath_RC_Collections = "//div[contains(@class,'MuiGrid-root')]//p[contains(@class,'MuiTypography-root') and text()='Collections']";
    private String lbl_xpath_RC_info = "//div[contains(@class,'MuiGrid-root')]//p[contains(@class,'MuiTypography-root') and text()='View your collections to compare, export and view at a glance key title vs title sales']";
    private String lbl_xpath_RC_datasource = "//div[contains(@class,'MuiGrid-root')]//p[contains(@class,'MuiTypography-root') and text()='US BookScan']";
    private String lbl_xpath_RC_recentview = "//div[contains(@class,'MuiGrid-root') and text()='Recently Viewed Collections']";
    private String input_xpath_RC_search = "//input[@placeholder='Search Collections by Name' and @type='text']";
    private String bt_xpath_RC_GlobalExport = "//span[@class='MuiIconButton-label']//img[@alt='download']";


    By bt_leftContent_Collections = By.xpath(bt_xpath_leftContent_Collections);



    public void CreateCollections() throws Exception {

        String nameTestCollection = "SanityTestCollection";

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

        //Select Collections Option from Left Navigation
        driver.findElement(bt_leftContent_Collections).click();
        Thread.sleep(2000);

        DeleteCollection(nameTestCollection);

        //Basic Search
        page_BasicSearch page_basicSearch = new page_BasicSearch();
        page_basicSearch.basicSearchAction("Harry");

        //Select Check box
        String txt_xpath_checkbox = "//tbody[contains(@class,'MuiTableBody-root')]//ancestor::td[contains(@class,'Checkbox')]";
        int cntcheckbox = driver.findElements(By.xpath(txt_xpath_checkbox)).size();

        for(int i = 1;i<6;i++){

            driver.findElements(By.xpath(txt_xpath_checkbox)).get(i).click();
        }

        //Add To Collection
        driver.findElement(By.xpath(bt_xpath_AddToCollection)).click();
        Thread.sleep(2000);


        //Enter Collection Name
        driver.findElement(By.xpath(txt_xpath_EnterCollectionName)).sendKeys(nameTestCollection);
        Thread.sleep(1000);
        driver.findElement(By.xpath(bt_xpath_Save)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(bt_xpath_ViewCollection)).click();
        Thread.sleep(3000);

        if(driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size()>2){
            TestLogger.logPass("Collection List is Appearing");
        }else{
            TestLogger.logFail("Collection List is not appearing.");
            FailedResult=true;
        }


        //Select Collections Option from Left Navigation
        driver.findElement(bt_leftContent_Collections).click();
        Thread.sleep(2000);

        //Validate Collection list is appearing
        if(driver.findElements(By.xpath("//a[text()='" + nameTestCollection + "']")).size()>0){
            TestLogger.logPass("Collection List is appearing : " + nameTestCollection);
            boolean IsdeletePass = DeleteCollection(nameTestCollection);

            if(IsdeletePass==true){
                TestLogger.logPass("Successfully Deleted");
            }else{
                TestLogger.logFail("Unable to Delete");
                FailedResult = true;
            }

/*
            //Delete Created Collection List
            driver.findElement(By.xpath("//a[text()='" + nameTestCollection + "']//parent::td//parent::td//preceding-sibling::td")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Delete']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Delete']")).click();
            Thread.sleep(3000);

            int scntBSL = driver.findElements(By.xpath("//a[text()='" + nameTestCollection + "']")).size();
            if(scntBSL!=0){
                TestLogger.logFail("Collection : " + nameTestCollection + " is not deleted.");
                FailedResult = true;
            }else{
                TestLogger.logPass("Collection : " + nameTestCollection + " is deleted.");
            }
*/


        }else{
            TestLogger.logFail("Collection list is not appearing : " + nameTestCollection);
            FailedResult= true;
        }

        //Go Back to Home
        ClickOnHome();

        if(FailedResult==true){
            Assert.fail();
        }

    }


    public void CollectionRecentViewCheckPoint() throws Exception {

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
//        boolean FailedResult = false;

        //Select Collections Option from Left Navigation
        driver.findElement(bt_leftContent_Collections).click();
        Thread.sleep(2000);

        //Create Simple Collection if not available
        int NoColletionlblfound = driver.findElements(By.xpath(lbl_xpath_NoCollection)).size();
        String collectionName=null;
        boolean createCollection = false;

        if(NoColletionlblfound>0){

            //Create Simple Collection
            collectionName = CreateSimpleCollection();

            createCollection = true;

            //Click on Collection
            driver.findElement(bt_leftContent_Collections).click();
            Thread.sleep(2000);

        }

        //Check
        if(IsElementPresent(lbl_xpath_RC_Created,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_RC_lastupdate,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_RC_Collections,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_RC_datasource,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_RC_info,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_RC_recentview,true)==false){ IsTestFail = true; }
        if(IsElementPresent(input_xpath_RC_search,true)==false){ IsTestFail = true; }

        //Delete Collection
        if(createCollection==true){
            DeleteCollection(collectionName);
        }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }


    }


    //Create Simple Collection from Basic Search
    public String CreateSimpleCollection() throws Exception {


        String nameTestCollection = "Reg Test Collection " + GenerateRandomNumber();

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

        page_BasicSearch page_basicSearch = new page_BasicSearch();

        //Enter Item in Basic Search
        page_basicSearch.EnterIteminBasicSearch("Harry");

        //Click on Search
        page_basicSearch.ClickOnBasicSearch();

        //Check if Search Result Appearing
        page_basicSearch.IsSearchResultAppearing();

        //Select First 3 Items To Add in Collection
        //Select Check box
        String txt_xpath_checkbox = "//tbody[contains(@class,'MuiTableBody-root')]//ancestor::td[contains(@class,'Checkbox')]";
        int cntcheckbox = driver.findElements(By.xpath(txt_xpath_checkbox)).size();

        for(int i = 1;i<6;i++){

            driver.findElements(By.xpath(txt_xpath_checkbox)).get(i).click();
        }

        //Add To Collection
        driver.findElement(By.xpath(bt_xpath_AddToCollection)).click();
        Thread.sleep(2000);


        //Enter Collection Name
        driver.findElement(By.xpath(txt_xpath_EnterCollectionName)).sendKeys(nameTestCollection);
        Thread.sleep(1000);
        driver.findElement(By.xpath(bt_xpath_Save)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(bt_xpath_ViewCollection)).click();
        Thread.sleep(3000);

        if(driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size()>2){
            TestLogger.logPass("Collection List is Appearing");
        }else{
            TestLogger.logFail("Collection List is not appearing.");
            FailedResult=true;
        }

        return nameTestCollection;

    }


    public boolean DeleteCollection(String CollectionName) throws Exception {

        //Delete Created Collection List
        int cntColList = driver.findElements(By.xpath("//a[text()='" + CollectionName + "']//parent::td//parent::td//preceding-sibling::td")).size();

        if(cntColList>0){
            driver.findElement(By.xpath("//a[text()='" + CollectionName + "']//parent::td//parent::td//preceding-sibling::td")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Delete']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[text()='Delete']")).click();
            Thread.sleep(3000);
        }else{
            TestLogger.log("No List To Delete");
        }


        int scntBSL = driver.findElements(By.xpath("//a[text()='" + CollectionName + "']")).size();
        if(scntBSL!=0){
            TestLogger.logFail("Collection : " + CollectionName + " is not deleted.");
            return false;
        }else{
            TestLogger.logPass("Collection : " + CollectionName + " is deleted.");
            return true;
        }


    }


    public void RecentViewCollectionTableHeaders() throws Exception {

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
//        boolean FailedResult = false;

        //Select Collections Option from Left Navigation
        driver.findElement(bt_leftContent_Collections).click();
        Thread.sleep(2000);

        //Create Simple Collection if not available
        int NoColletionlblfound = driver.findElements(By.xpath(lbl_xpath_NoCollection)).size();
        String collectionName=null;
        boolean createCollection = false;

        if(NoColletionlblfound>0){

            //Create Simple Collection
            collectionName = CreateSimpleCollection();

            createCollection = true;

            //Click on Collection
            driver.findElement(bt_leftContent_Collections).click();
            Thread.sleep(2000);

        }

        //Get Expected Column Names from Resource File
        String expected_Columns = getExpectedColumnNames("Collections RecentView");
        String[] splitExpected = expected_Columns.split("[;]");

        //Get Actual Column Names from Application
        String[] app_columns = getActualColumnName();

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


    //Export Excel
    public void ExportCollectionRecentView() throws Exception {


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
//        boolean FailedResult = false;

        //Select Collections Option from Left Navigation
        driver.findElement(bt_leftContent_Collections).click();
        Thread.sleep(2000);

        //Create Simple Collection if not available
        int NoColletionlblfound = driver.findElements(By.xpath(lbl_xpath_NoCollection)).size();
        String collectionName=null;
        boolean createCollection = false;

        if(NoColletionlblfound>0){

            //Create Simple Collection
            collectionName = CreateSimpleCollection();

            createCollection = true;

            //Click on Collection
            driver.findElement(bt_leftContent_Collections).click();
            Thread.sleep(2000);

        }

        //Export Excel
        page_BasicSearch page_basicSearch = new page_BasicSearch();
//        page_basicSearch.ClickONExportExcel();
        ClickOnExport(bt_xpath_RC_GlobalExport,"Export All");
        boolean fileCheck = page_basicSearch.ExportedFileCheck(220);

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }

    }


    public void ClickOnExport(String xpath_export,String val_export) throws Exception {

        driver.findElement(By.xpath(xpath_export)).click();
        TestLogger.log("Clicked ON Export Button.");

        Thread.sleep(3000);

        String xPathExportOption = "//span[contains(@class,'MuiTypography-root') and text()='" + val_export + "']";

        //Click on XLSX
        driver.findElement(By.xpath(xPathExportOption)).click();
        TestLogger.log("Clicked ON XLSX Link.");
        Thread.sleep(6000);

    }


}
