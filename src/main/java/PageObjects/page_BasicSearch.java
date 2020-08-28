package PageObjects;

import BaseClass.CommonApi;
import Reporting.TestLogger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.zookeeper.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class page_BasicSearch extends CommonApi {

    public String txt_xpath_searchbox = "//input[@id='basicSearch']";
//    public String txt_xpath_searchbox = "//input[@id='searchBox']";
    public String bt_xpath_searchbox = "//button[@id='searchIcon']";
    public String row_xpath_searchTable = "//tr[contains(@class,'MuiTableRow-root')]";
    public String dd_xpath_basicsearch = "//div[@id='searchType']";
    public String li_xpath_SearchAll = "//li[contains(@class,'MuiButtonBase-root') and contains(text(),'Search All')]";
    public String li_xpath_ISBN = "//li[contains(@class,'MuiButtonBase-root') and contains(text(),'ISBN')]";
    public String li_xpath_Author = "//li[contains(@class,'MuiButtonBase-root') and contains(text(),'Author')]";
    public String li_xpath_Title = "//li[contains(@class,'MuiButtonBase-root') and contains(text(),'Title')]";
    private String bt_xpath_basicsearch = "//button[@type='button' and @variant='small']//ancestor::span[@class='MuiIconButton-label']//img[@alt='download']";
    private String lnk_xpath_XLSX = "//span[contains(@class,'MuiTypography-root') and contains(text(),'Export Top')]";

    private String page_xPath_SalesAndRankHistory = "//div[contains(@class,'MuiToggleButtonGroup-root') and @role = 'group']//ancestor::span[text()='Table']";
    private String page_xPath_Metadata = "//div[contains(@class,'MuiGrid-container MuiGrid-item MuiGrid-wrap-xs-nowrap')]//ancestor::h5[text()='Title']";
    private String page_xPath_AuthorHistory = "//table[contains(@class,'MuiTable-root jss1431 MuiTable-stickyHeader')]//ancestor::td[text()='TOTAL SALES']";
    private String page_xPath_RegionalSales = "//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid-xs-true')]//ancestor::span[text()='DMA']";
    private String COVID_Text_info1 = "We were unable to report these sales figures due to the Covid-19 outbreak. The RTD and YTD figures therefore omit sales from the affected weeks.  Learn More";


    private String SRH_bt_xpath_table = "//div[contains(@class,'MuiToggleButtonGroup-root')]//button//span[@class='MuiToggleButton-label']//span[text()='Table']";
    private String SRH_bt_xpath_graph = "//div[contains(@class,'MuiToggleButtonGroup-root')]//button//span[@class='MuiToggleButton-label']//span[text()='Graph']";
    private String SRH_lbl_xpath_view = "//div[contains(@class,'MuiToolbar-root MuiToolbar-regular')]//div//p//strong[text()='View']";
    private String SRH_lbl_xpath_sales = "//p[contains(@class,'MuiTypography-root') and text()='Sales']";
    private String SRH_lbl_xpath_ranks = "//p[contains(@class,'MuiTypography-root') and text()='Ranks']";
    private String SRH_lbl_xpath_graph_title = "//div[@data-highcharts-chart='0']//*[name()='svg']//*[name()='text']//*[name()='tspan' and text()='Harry Potter and the Chamber of Secrets']";
    private String SRH_lbl_xpath_graph_subtitle = "//div[@data-highcharts-chart='0']//*[name()='svg']//*[name()='text']//*[name()='tspan' and text()='US BookScan Unit Sales by Weekly & Ranking by Overall']";



    public String exportFolder = System.getProperty("java.io.tmpdir") + "Bookscan";
    //public String li_xpath_SearchSuggestions = "//h5[@class='MuiTypography-root jss1385 MuiTypography-h5']";
    //public String li_xpath_SearchSuggestions = "//div[@class='MuiGrid-root MuiGrid-item']";

    public String li_xpath_SearchSuggestions = "//*[@class='MuiGrid-root MuiGrid-item']/child::h2/following-sibling::h5/child::a";
//    public String li_xpath_SearchSuggestions = "//*[@class='MuiGrid-root MuiGrid-item']/child::h2";

    By txt_searchbox = By.xpath(txt_xpath_searchbox);
    By bt_search = By.xpath(bt_xpath_searchbox);
    By row_searchTable = By.xpath(row_xpath_searchTable);
    By dd_basicsearch = By.xpath(dd_xpath_basicsearch);
    By li_SearchSuggestion = By.xpath(li_xpath_SearchSuggestions);


    public void basicSearchCheckpoints() throws Exception {
        CommonApi commonApi = new CommonApi();

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        commonApi.IsElementPresent(txt_xpath_searchbox,true);
        commonApi.IsElementPresent(bt_xpath_searchbox,true);
        commonApi.IsElementPresent(dd_xpath_basicsearch,true);

        //Click on Drop Down
        driver.findElement(dd_basicsearch).click();

        commonApi.IsElementPresent(li_xpath_SearchAll,true);
        commonApi.IsElementPresent(li_xpath_ISBN,true);
        commonApi.IsElementPresent(li_xpath_Author,true);
        commonApi.IsElementPresent(li_xpath_Title,true);

        driver.findElement(By.xpath("//li[contains(@class,'MuiButtonBase-root') and contains(text(),'Search All')]")).click();

//        driver.close();


    }


    //This method will check all the checkpoints of Sales And Rank History Screen
    public void CheckPoint_SalesAndRankHistory() throws Exception {

        String sKey = "Harry";

        //Log in if already not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys(sKey);
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");
        waitUntilVisible(row_xpath_searchTable);

        int TotalRow = driver.findElements(row_searchTable).size();
        if(TotalRow>0){
            TestLogger.logPass("Total Row record appeared : " + TotalRow);
        }else{
            TestLogger.logFail("Total Row record appeared : " + TotalRow);
            Assert.fail();
        }


        //Click First Record from Search Result
        driver.findElement(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//ancestor::tr[contains(@class,'MuiTableRow-root')]//td[2]")).click();
        Thread.sleep(3000);
//        }

        //Select Single Title Tab
        SelectSingleTitleTabs("Sales & Rank History");

    }


    public void EnterIteminBasicSearch(String sKey) throws Exception {

        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        driver.findElement(txt_searchbox).sendKeys(sKey);

    }


    public void ClickOnBasicSearch(){

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");
    }


    public void searchbyKey(String sKey, String option) throws Exception {

        boolean IsFailed = false;

/*
        //Launch Browser and Navigate
        launchbrowser();
        Thread.sleep(3000);
*/

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        HighlightElement(dd_xpath_basicsearch);
        Thread.sleep(3000);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        //Click on Drop Down
        if(commonApi.IsElementPresent("//li[contains(@class,'MuiButtonBase-root') and contains(text(),'" + option + "')]") == false){
            driver.findElement(dd_basicsearch).click();
            Thread.sleep(3000);
        }

        //Select Option
        driver.findElement(By.xpath("//li[contains(@class,'MuiButtonBase-root') and contains(text(),'" + option + "')]")).click();
        Thread.sleep(3000);


        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys(sKey);
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");

        boolean IsResuleAppearing = IsSearchResultAppearing();

        if(IsResuleAppearing==false){
            IsFailed = true;
        }


/*        waitUntilVisible(row_xpath_searchTable);

        int TotalRow = driver.findElements(row_searchTable).size();
        if(TotalRow>0){
            TestLogger.logPass("Total Row record appeared : " + TotalRow);
        }else{
            TestLogger.logFail("Total Row record appeared : " + TotalRow);
            Assert.fail();
        }*/

        //Click on Home
        ClickOnHome();

        if(IsFailed==true){
            Assert.fail();
        }


/*        //kill Browser
        driver.close();*/

    }


    public void basicSearchAction(String sKey) throws Exception {

//        driver.findElement(By.xpath("//span[text()='Got it!']")).click();
//        Thread.sleep(2000);
        driver.findElement(txt_searchbox).sendKeys(sKey);
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");
        Thread.sleep(10);

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");
        waitUntilVisible(row_xpath_searchTable);
    }



    public void searchSuggestion(String option) throws Exception {

/*
        //Launch Browser and Navigate
        launchbrowser();
        Thread.sleep(3000);
*/

        CommonApi commonApi = new CommonApi();


        FileInputStream fis = new FileInputStream(sResourceFile);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheetAt(1);

        String SearchKey;
        String SrchType;
        String ExpectedSuggestions;

        System.out.println(sheet.getLastRowNum());

        boolean TestFailed = false;
        int res_startCol = 0;

        if(option.equals("Search All")){
            res_startCol = 0;
        }else if(option.equals("Author")){
            res_startCol = 4;
        }else if(option.equals("Title")){
            res_startCol = 8;
        }

        for(int i_row=2;i_row<=sheet.getLastRowNum();i_row++){



            Cell searchKey = sheet.getRow(i_row).getCell(res_startCol);
            //Cell sOption = sheet.getRow(i_row).getCell(1);
            Cell sSuggstion = sheet.getRow(i_row).getCell(res_startCol + 1);


            //try{

                if(searchKey.getStringCellValue() == null || searchKey.getCellType() == Cell.CELL_TYPE_BLANK){
                    System.out.println("End of the Row");
                    break;
                }

                //Convert Excel Cell Type
                String cellvalue_searchkey = commonApi.getCellValue(searchKey);
                //String cellvalue_option = commonApi.getCellValue(sOption);
                String cellvalue_suggestion = commonApi.getCellValue(sSuggstion);

/*
                System.out.println(cellvalue_searchkey);
                System.out.println(cellvalue_option);
                System.out.println(cellvalue_suggestion);
*/


                //Enter Search Key
                driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
                driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
                Thread.sleep(2000);

 /*               HighlightElement(dd_xpath_basicsearch);
                Thread.sleep(3000);*/

                //Click on Drop Down
                //if(commonApi.IsElementPresent("//li[contains(@class,'MuiButtonBase-root') and contains(text(),'" + option + "')]") == false){


            if(driver.findElement(dd_basicsearch).getText().equals(option)){

            }else{
                driver.findElement(dd_basicsearch).click();
                Thread.sleep(1000);
                //}

                //Select Option
                driver.findElement(By.xpath("//li[contains(@class,'MuiButtonBase-root') and contains(text(),'" + option + "')]")).click();
                Thread.sleep(1000);
            }

                //Enter Search Key
                driver.findElement(txt_searchbox).sendKeys(cellvalue_searchkey);

                //Click on Search
                driver.findElement(bt_search).click();
                TestLogger.logPass("Search Button has been clicked");
                Thread.sleep(3000);

//                waitUntilVisible(row_xpath_searchTable);

/*                System.out.println("Suggested Value : " + driver.findElements(li_SearchSuggestion).size());
                System.out.println("Suggested Value : " + driver.findElement(li_SearchSuggestion).getText());*/

/*                int TotalRow = driver.findElements(row_searchTable).size();
                if(TotalRow>0){
                    TestLogger.logPass("Total Row record appeared : " + TotalRow);
                }else{
                    TestLogger.logFail("Total Row record appeared : " + TotalRow);
                    //Assert.fail();
                }*/



                if(driver.findElements(li_SearchSuggestion).size() > 0){

                    String suggestedName_App = driver.findElement(li_SearchSuggestion).getText().toLowerCase();
                    String suggestedName_Expected = cellvalue_suggestion.toLowerCase();

                    if(suggestedName_App.equals(suggestedName_Expected) ){
                        TestLogger.logPass("Matched with Suggested Name : " + suggestedName_Expected);
                    }else{
                        TestLogger.logFail("App suggested name : " + suggestedName_App + " did not match with expected Suggested Name : " + suggestedName_Expected);

                        if(TestFailed==false){
                            TestFailed = true;
                        }

                    }

                }else{
                    TestLogger.logFail("Suggestions is not appearing for :  " + cellvalue_searchkey);
                    if(TestFailed==false){
                        TestFailed = true;
                    }

                }




/*
            }catch(Exception e){
                //e.printStackTrace();
                System.out.println("End of the Column");
                break;

            }
*/


        }

        if(TestFailed==true){
            Assert.fail();
        }

    }


    public void exportBasicSearchResults() throws Exception {


        boolean IsTestFail = false;

//        basicsrch.searchbyKey("Steve","Search All");

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

//        HighlightElement(dd_xpath_basicsearch);
//        Thread.sleep(3000);

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys("Steve");
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");
        waitUntilVisible(row_xpath_searchTable);

        int TotalRow = driver.findElements(row_searchTable).size();
        if(TotalRow>0){
            TestLogger.logPass("Total Row record appeared : " + TotalRow);
        }else{
            TestLogger.logFail("Total Row record appeared : " + TotalRow);
            Assert.fail();
        }


        //Export Excel
        ClickONExportExcel();
        boolean fileCheck = ExportedFileCheck(1200);

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }

    }



    public boolean ExportedFileCheck(int Expected_RowCount) throws Exception {

        boolean IsFailed = false;

        //Check if File Exists
        File file = new File(exportFolder);
        int fileCount = file.list().length;
        int ExpectedRowCount = Expected_RowCount;
        TestLogger.log("Defiend Expected Row Limit " + ExpectedRowCount);

        if(fileCount>0){
            TestLogger.logPass("Exported File Exist in : " + exportFolder);

            //Check Record Count in the File
            File expFile = new File(exportFolder);

            for(File f:expFile.listFiles()){

                if(!f.isDirectory()){
                    //Get Expected Values
                    int ActualRowCount = getExcelFileLastRowNumber(f.getAbsolutePath());
                    TestLogger.log("Actual Row : " + ActualRowCount);

//                    int StartRow_Table = getStartRow

                    if(ActualRowCount<ExpectedRowCount){
                        TestLogger.logPass("Expected Row Matched : " + Expected_RowCount);
                    }else{
                        TestLogger.logFail("Expected Row did not match. Actual Row: " + ActualRowCount + " Expected Row Range : " + ExpectedRowCount);
                        IsFailed = true;
                    }

                }

                break;

            }

        }else{
            TestLogger.logFail("Exported File does not exist.");
            IsFailed = true;
        }

        if(IsFailed==true){
            return false;
        }else{
            return true;
        }

    }



    public void CheckTableHeaders() throws Exception {

        boolean IsTestFail = false;

        //Log in if already not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);

        driver.findElement(txt_searchbox).sendKeys("Steve");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");
        waitUntilVisible(row_xpath_searchTable);

        int TotalRow = driver.findElements(row_searchTable).size();
        if(TotalRow>0){
            TestLogger.logPass("Total Row record appeared : " + TotalRow);
        }else{
            TestLogger.logFail("Total Row record appeared : " + TotalRow);
            Assert.fail();
        }


        //Get Expected Column Names from Resource File
        String expected_Columns = getExpectedColumnNames("Basic Search");
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



    public void ClickONExportExcel() throws Exception {

        driver.findElement(By.xpath(bt_xpath_basicsearch)).click();
        TestLogger.log("Clicked ON Export Button.");

        Thread.sleep(3000);

        //Click on XLSX
        driver.findElement(By.xpath(lnk_xpath_XLSX)).click();
        TestLogger.log("Clicked ON XLSX Link.");
        Thread.sleep(6000);
    }


    public void HighChartCheckpoint() throws Exception {

        boolean IsFailed = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys("9780439064873");
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");

        boolean IsResuleAppearing = IsSearchResultAppearing();

        if(IsResuleAppearing==false){
            IsFailed = true;
        }

        //Click First Record from Search Result
        driver.findElement(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//ancestor::tr[contains(@class,'MuiTableRow-root')]//td[2]")).click();
        Thread.sleep(3000);

        //Select Graph Option
        driver.findElement(By.xpath(SRH_bt_xpath_graph)).click();
        Thread.sleep(2000);

        if(IsElementPresent(SRH_bt_xpath_table,true)==false){ IsFailed = true; }
        if(IsElementPresent(SRH_lbl_xpath_graph_subtitle,true)==false){ IsFailed = true; }
        if(IsElementPresent(SRH_lbl_xpath_graph_title,true)==false){ IsFailed = true; }
        if(IsElementPresent(SRH_lbl_xpath_ranks,true)==false){ IsFailed = true; }
        if(IsElementPresent(SRH_lbl_xpath_sales,true)==false){ IsFailed = true; }
//        if(IsElementPresent(SRH_lbl_xpath_view,true)==false){ IsFailed = true; }


        //Check Drop Down Values


//        driver.findElement(By.xpath("//div[contains(@class,'MuiSelect-root') and @id='salesDropDown']")).click();
        driver.findElement(By.xpath("//p[contains(@class,'MuiTypography-root') and text()='Sales']//parent::div//div[1]//div[contains(@class,'MuiSelect-root MuiSelect-select')]")).click();


        if(IsElementPresent("//li[contains(@class,'MuiButtonBase-root MuiListItem-root') and text()='Weekly']",true)==false){ IsFailed = true; }
        if(IsElementPresent("//li[contains(@class,'MuiButtonBase-root MuiListItem-root') and text()='RTD']",true)==false){ IsFailed = true; }

        //Close Sales Drop Down
//        driver.findElement(By.xpath("//div[contains(@class,'MuiSelect-root') and @id='salesDropDown']")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        Thread.sleep(2000);


        //Open Rank DropDown
//        driver.findElement(By.xpath("//div[contains(@class,'MuiSelect-root') and @id='ranksDropDown']")).click();
        driver.findElement(By.xpath("//p[contains(@class,'MuiTypography-root') and text()='Ranks']//parent::div//div[1]//div[contains(@class,'MuiSelect-root MuiSelect-select')]")).click();
        if(IsElementPresent("//li[contains(@class,'MuiButtonBase-root MuiListItem-root') and text()='Overall']",true)==false){ IsFailed = true; }
        if(IsElementPresent("//li[contains(@class,'MuiButtonBase-root MuiListItem-root') and text()='Trade Paperback']",true)==false){ IsFailed = true; }
        if(IsElementPresent("//li[contains(@class,'MuiButtonBase-root MuiListItem-root') and text()='Juvenile Fiction']",true)==false){ IsFailed = true; }
        if(IsElementPresent("//li[contains(@class,'MuiButtonBase-root MuiListItem-root') and text()='Science Fiction/Fantasy/Magic - JF']",true)==false){ IsFailed = true; }


        //Close Sales Drop Down
//        driver.findElement(By.xpath("//div[contains(@class,'MuiSelect-root') and @id='ranksDropDown']")).click();
        action.sendKeys(Keys.ESCAPE).build().perform();
        Thread.sleep(2000);


        //Click on Home
        ClickOnHome();

        if(IsFailed==true){
            Assert.fail();
        }


    }



        public void SalesRank_Graph_DropDownValues(String nameDropDown) throws Exception {

        boolean IsFailed = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys("9780439064873");
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");

        boolean IsResuleAppearing = IsSearchResultAppearing();

        if(IsResuleAppearing==false){
            IsFailed = true;
        }

        //Click First Record from Search Result
        driver.findElement(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//ancestor::tr[contains(@class,'MuiTableRow-root')]//td[2]")).click();
        Thread.sleep(3000);

        //Select Graph Option
        driver.findElement(By.xpath(SRH_bt_xpath_graph)).click();
        Thread.sleep(2000);


        //Get DropDown Value
        String ExpectedDDValues = getGraphDropDownValue(nameDropDown);

        String[] spliExpectedValue = ExpectedDDValues.split("[|]");

        //Open Drop Down
//        driver.findElement(By.xpath("//div[contains(@class,'MuiSelect-root') and @id='" + nameDropDown + "']")).click();
        driver.findElement(By.xpath("//p[contains(@class,'MuiTypography-root') and text()='" + nameDropDown + "']//parent::div//div[1]//div[contains(@class,'MuiSelect-root MuiSelect-select')]")).click();



        for(int i=0;i<=spliExpectedValue.length-1;i++){
            if(IsElementPresent("//li[contains(@class,'MuiButtonBase-root MuiListItem-root') and text()='" + spliExpectedValue[i] + "']",true)==false){ IsFailed = true; }
        }


        //Close Sales Drop Down
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
        Thread.sleep(2000);


        //Click on Home
        ClickOnHome();

        if(IsFailed==true){
            Assert.fail();
        }


    }



    public String getGraphDropDownValue(String nameDD){

        String DDValues = null;

        if(nameDD.equals("Sales")){
            DDValues = "Weekly|RTD";
        }else if(nameDD.equals("Ranks")){
            DDValues = "Overall|Trade Paperback|Juvenile Fiction|Science Fiction/Fantasy/Magic - JF";
        }

        return DDValues;


    }



    public int getExcelFileLastRowNumber(String fileLocation) throws Exception {


        FileInputStream fis = new FileInputStream(fileLocation);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheetAt(0);
        int cnt = sheet.getLastRowNum();
        TestLogger.log("Actual Row : " + cnt);

 /*       //Get Row Number of ISBN Row
        int exp_row=0;
        for(exp_row=0;exp_row<=cnt;exp_row++){

            try{
                if(sheet.getRow(exp_row).getCell(0).toString().equals("ISBN")){
                    break;
                }

            }catch (Exception e){
                continue;
            }


        }

        int RecordExported = cnt - exp_row - 3;

        TestLogger.log("Total Row including Header : " + RecordExported);
*/
        return cnt;

    }


    public void CheckSingleTitleSelectionTab(String tabName) throws Exception {

        String sKey = "Harry";

        //Log in if already not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

//        boolean isInSTV = AlreadyInSingleTileView();

//        if(isInSTV==false){


                //Go Back to Home
                ClickOnHome();

                //Enter Search Key
                driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
                driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
                Thread.sleep(2000);
                //HighlightElement(txt_xpath_searchbox);
                driver.findElement(txt_searchbox).sendKeys(sKey);
                //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

                //Click on Search
                driver.findElement(bt_search).click();
                TestLogger.logPass("Search Button has been clicked");
                waitUntilVisible(row_xpath_searchTable);

                int TotalRow = driver.findElements(row_searchTable).size();
                if(TotalRow>0){
                    TestLogger.logPass("Total Row record appeared : " + TotalRow);
                }else{
                    TestLogger.logFail("Total Row record appeared : " + TotalRow);
                    Assert.fail();
                }


                //Click First Record from Search Result
                driver.findElement(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//ancestor::tr[contains(@class,'MuiTableRow-root')]//td[2]")).click();
                Thread.sleep(3000);
//        }

        //Select Single Title Tab
        SelectSingleTitleTabs(tabName);


        //Go Back to Home
        ClickOnHome();

//        //Navigate TO Previous Page
//        driver.navigate().back();
        Thread.sleep(3000);

    }


    public boolean AlreadyInSingleTileView(){

        int cnt_SRH = driver.findElements(By.xpath(page_xPath_SalesAndRankHistory)).size();
        int cnt_MetaData = driver.findElements(By.xpath(page_xPath_Metadata)).size();
        int cnt_AuthorHis = driver.findElements(By.xpath(page_xPath_AuthorHistory)).size();
        int cnt_RegionSales = driver.findElements(By.xpath(page_xPath_RegionalSales)).size();

        if(cnt_SRH > 0 || cnt_MetaData> 0 || cnt_AuthorHis > 0 ||cnt_RegionSales > 0){
            return true;
        }else{
            return false;
        }


    }


    public boolean SelectSingleTitleTabs(String tabName) throws InterruptedException {


        //Click on the Tab
        String xTabPath = "//div[@class='MuiTabs-scroller MuiTabs-fixed']//ancestor::span[text()='" + tabName + "']";

        System.out.println(driver.findElements(By.xpath(xTabPath)).size());
        String identifyScreenxPath = null;
        driver.findElement(By.xpath(xTabPath)).click();
        Thread.sleep(3000);

        if(tabName.equals("Sales & Rank History")){
            identifyScreenxPath = "//div[contains(@class,'MuiToggleButtonGroup-root') and @role = 'group']//ancestor::span[text()='Table']";
        }else if(tabName.equals("Metadata")){
            identifyScreenxPath = "//div[contains(@class,'MuiGrid-container MuiGrid-item MuiGrid-wrap-xs-nowrap')]//ancestor::h5[text()='Title']";
        }else if(tabName.equals("Author History")){
            identifyScreenxPath = "//table[contains(@class,'MuiTable-root jss1431 MuiTable-stickyHeader')]//ancestor::td[text()='TOTAL SALES']";
        }else if(tabName.equals("Regional Sales")){
            identifyScreenxPath = "//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid-xs-true')]//ancestor::span[text()='DMA']";
        }


        if(driver.findElements(By.xpath(identifyScreenxPath)).size()>0){
            TestLogger.logPass(tabName + " is appearing.");
            return true;
        }else{
            TestLogger.logPass(tabName + " is not appearing.");
            return false;
        }

    }



    public void CheckCOVIDDataColor_BasicSearch(String DataType,String ISBN,String HexColorCode) throws Exception {

        boolean IsTestFail = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys(ISBN);
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");

        boolean IsResuleAppearing = IsSearchResultAppearing();
        String val_xpath_RTDSales = null;
        String val_xpath_YTDSales = null;


        if(IsResuleAppearing==false){
            IsTestFail = true;
        }

        if(DataType.equals("COVID")){
            val_xpath_RTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//td[9]//span[1]";
            val_xpath_YTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//td[11]//span[1]";
        }else if(DataType.equals("NON COVID")){
            val_xpath_RTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//td[9]";
            val_xpath_YTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//td[11]";
        }else{
            TestLogger.logFail("Please Enter DataType Correctly");
            IsTestFail = true;
        }


        //Get Font Color RBG Value
        String ColorValue_RTD = driver.findElement(By.xpath(val_xpath_RTDSales)).getCssValue("color").toString();
        String ColorValue_YTD = driver.findElement(By.xpath(val_xpath_YTDSales)).getCssValue("color").toString();

        //Get Font Color Hex Value
        String hex_RTD = Color.fromString(ColorValue_RTD).asHex().toUpperCase();
        String hex_YTD = Color.fromString(ColorValue_YTD).asHex().toUpperCase();

        System.out.println("RTD : " + ColorValue_RTD);
        System.out.println("RTD HEX : " + hex_RTD);
        System.out.println("YTD HEX : " + hex_YTD);

        if(hex_RTD.equals(HexColorCode.toUpperCase())){
            TestLogger.logPass("RTD Value matched with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
        }else{
            TestLogger.logFail("RTD Value did not match with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
            IsTestFail = true;
        }

        if(hex_YTD.equals(HexColorCode.toUpperCase())){
            TestLogger.logPass("YTD Value matched with expected font color : " + HexColorCode + " Actual Font Color : " + hex_YTD);
        }else{
            TestLogger.logFail("YTD Value did not match with expected font color : " + HexColorCode + " Actual Font Color : " + hex_YTD);
            IsTestFail = true;
        }

        //Go Back to Home
        ClickOnHome();


        if(IsTestFail==true){
            Assert.fail();
        }


    }



    public void CheckCOVIDData_SalesAndRank(String DataType,String ISBN,String TimePeriod,String HexColorCode) throws Exception {

        boolean IsTestFail = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys(ISBN);
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");

        boolean IsResuleAppearing = IsSearchResultAppearing();

        //Click First Record from Search Result
        driver.findElement(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//ancestor::tr[contains(@class,'MuiTableRow-root')]//td[2]")).click();
        Thread.sleep(3000);


        //Select Single Title Tab
        SelectSingleTitleTabs("Sales & Rank History");
        String xPath_YTDSales = null;
        String xPath_RTDSales = null;

        if(DataType.toUpperCase().equals("COVID")){
            xPath_YTDSales = "//tr[contains(@class,'MuiTableRow-root')]//td[text()='" + TimePeriod + "']//parent::tr//td[3]//span";
            xPath_RTDSales = "//tr[contains(@class,'MuiTableRow-root')]//td[text()='" + TimePeriod + "']//parent::tr//td[4]//span";
        }else if(DataType.toUpperCase().equals("NON COVID")){
            xPath_YTDSales = "//tr[contains(@class,'MuiTableRow-root')]//td[text()='" + TimePeriod + "']//parent::tr//td[3]";
            xPath_RTDSales = "//tr[contains(@class,'MuiTableRow-root')]//td[text()='" + TimePeriod + "']//parent::tr//td[4]";

        }else{
            TestLogger.logFail("Data Type is not matching");
        }


        String ColorValue_RTD = driver.findElement(By.xpath(xPath_RTDSales)).getCssValue("color").toString();
        String ColorValue_YTD = driver.findElement(By.xpath(xPath_YTDSales)).getCssValue("color").toString();

        //Get Font Color Hex Value
        String hex_YTD = Color.fromString(ColorValue_YTD).asHex().toUpperCase();
        String hex_RTD = Color.fromString(ColorValue_RTD).asHex().toUpperCase();

        if(hex_YTD.equals(HexColorCode.toUpperCase())){
            TestLogger.logPass("YTD Value matched with expected font color : " + HexColorCode + " Actual Font Color : " + hex_YTD);
        }else{
            TestLogger.logFail("YTD Value did not match with expected font color : " + HexColorCode + " Actual Font Color : " + hex_YTD);
            IsTestFail = true;
        }

        if(hex_RTD.equals(HexColorCode.toUpperCase())){
            TestLogger.logPass("RTD Value matched with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
        }else{
            TestLogger.logFail("RTD Value did not match with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
            IsTestFail = true;
        }

        if(IsTestFail==true){
            Assert.fail();
        }

        //Go Back to Home
        ClickOnHome();



    }


    public void CheckCOVIDFormatMetaData(String DataType,String ISBN,String HexColorCode) throws Exception {

        boolean IsTestFail = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys(ISBN);
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");

        boolean IsResuleAppearing = IsSearchResultAppearing();

        //Click First Record from Search Result
        driver.findElement(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//ancestor::tr[contains(@class,'MuiTableRow-root')]//td[2]")).click();
        Thread.sleep(3000);


        //Select Single Title Tab
        SelectSingleTitleTabs("Metadata");


        String xPath_RTDSales=null;

        if(DataType.toUpperCase().equals("COVID")){
            xPath_RTDSales = "//p[contains(@class,'MuiTypography-root') and text()='RTD Sales']//parent::div//following-sibling::div//p//span[1]";
        }else if(DataType.toUpperCase().equals("NON COVID")){
            xPath_RTDSales = "//p[contains(@class,'MuiTypography-root') and text()='RTD Sales']//parent::div//following-sibling::div//p[1]";

        }else{
            TestLogger.logFail("Data Type is not matching");
        }


        String ColorValue_RTD = driver.findElement(By.xpath(xPath_RTDSales)).getCssValue("color").toString();

        //Get Font Color Hex Value
        String hex_RTD = Color.fromString(ColorValue_RTD).asHex().toUpperCase();

        if(hex_RTD.equals(HexColorCode.toUpperCase())){
            TestLogger.logPass("RTD Value matched with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
        }else{
            TestLogger.logFail("RTD Value did not match with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
            IsTestFail = true;
        }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail == true){
            Assert.fail();
        }


    }


    public void CheckCOVIDFormatAuthorHistory(String DataType,String ISBN,String HexColorCode) throws Exception {

        boolean IsTestFail = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys(ISBN);
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");

        boolean IsResuleAppearing = IsSearchResultAppearing();

        //Click First Record from Search Result
        driver.findElement(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//ancestor::tr[contains(@class,'MuiTableRow-root')]//td[2]")).click();
        Thread.sleep(3000);


        //Select Single Title Tab
        SelectSingleTitleTabs("Author History");

        //Scroll To the ISBN
        WebElement weISBN = driver.findElement(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//a[text()='" + ISBN + "']"));
        scrollDownToElement(driver,weISBN);


        String xPath_RTDSales= null;
        String xPath_TWSales = null;
        String xPath_YTDSales = null;

        if(DataType.toUpperCase().equals("COVID")){
            xPath_RTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//a[text()='" + ISBN + "']//parent::td//parent::tr//td[9]//span[1]" ;
            xPath_TWSales = "//tbody[contains(@class,'MuiTableBody-root')]//a[text()='" + ISBN + "']//parent::td//parent::tr//td[10]//span[1]" ;
            xPath_YTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//a[text()='" + ISBN + "']//parent::td//parent::tr//td[11]//span[1]" ;
        }else if(DataType.toUpperCase().equals("NON COVID")){
            xPath_RTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//a[text()='" + ISBN + "']//parent::td//parent::tr//td[9]" ;
            xPath_TWSales = "//tbody[contains(@class,'MuiTableBody-root')]//a[text()='" + ISBN + "']//parent::td//parent::tr//td[10]" ;
            xPath_YTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//a[text()='" + ISBN + "']//parent::td//parent::tr//td[11]" ;

        }else{
            TestLogger.logFail("Data Type is not matching");
        }


        String ColorValue_RTD = driver.findElement(By.xpath(xPath_RTDSales)).getCssValue("color").toString();
//        String ColorValue_TW = driver.findElement(By.xpath(xPath_TWSales)).getCssValue("color").toString();
        String ColorValue_YTD = driver.findElement(By.xpath(xPath_YTDSales)).getCssValue("color").toString();

        //Get Font Color Hex Value
        String hex_RTD = Color.fromString(ColorValue_RTD).asHex().toUpperCase();
//        String hex_TW = Color.fromString(ColorValue_TW).asHex().toUpperCase();
        String hex_YTD = Color.fromString(ColorValue_YTD).asHex().toUpperCase();

        if(hex_RTD.equals(HexColorCode.toUpperCase())){
            TestLogger.logPass("RTD Value matched with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
        }else{
            TestLogger.logFail("RTD Value did not match with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
            IsTestFail = true;
        }


/*        if(hex_TW.equals(HexColorCode.toUpperCase())){
            TestLogger.logPass("TW Value matched with expected font color : " + HexColorCode + " Actual Font Color : " + hex_TW);
        }else{
            TestLogger.logFail("TW Value did not match with expected font color : " + HexColorCode + " Actual Font Color : " + hex_TW);
            IsTestFail = true;
        }*/


        if(hex_YTD.equals(HexColorCode.toUpperCase())){
            TestLogger.logPass("YTD Value matched with expected font color : " + HexColorCode + " Actual Font Color : " + hex_YTD);
        }else{
            TestLogger.logFail("YTD Value did not match with expected font color : " + HexColorCode + " Actual Font Color : " + hex_YTD);
            IsTestFail = true;
        }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail == true){
            Assert.fail();
        }

    }


    public void CheckCOVIDDataFormatSingleTitle(String DataType,String ISBN,String HexColorCode) throws Exception {


        boolean IsTestFail = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys(ISBN);
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");

        boolean IsResuleAppearing = IsSearchResultAppearing();

        //Click First Record from Search Result
        driver.findElement(By.xpath("//tbody[contains(@class,'MuiTableBody-root')]//ancestor::tr[contains(@class,'MuiTableRow-root')]//td[2]")).click();
        Thread.sleep(3000);


        //Select Single Title Tab
        SelectSingleTitleTabs("Sales & Rank History");
        String xPath_YTDSales = null;
        String xPath_RTDSales = null;

        if(DataType.toUpperCase().equals("COVID")){
            xPath_RTDSales = "//th[contains(@class,'MuiTableCell-root MuiTableCell-body') and text()='RTD Sales']//parent::tr//td//span[1]";
        }else if(DataType.toUpperCase().equals("NON COVID")){
            xPath_RTDSales = "//th[contains(@class,'MuiTableCell-root MuiTableCell-body') and text()='RTD Sales']//parent::tr//td[1]";

        }else{
            TestLogger.logFail("Data Type is not matching");
        }


        String ColorValue_RTD = driver.findElement(By.xpath(xPath_RTDSales)).getCssValue("color").toString();

        //Get Font Color Hex Value
        String hex_RTD = Color.fromString(ColorValue_RTD).asHex().toUpperCase();

        if(hex_RTD.equals(HexColorCode.toUpperCase())){
            TestLogger.logPass("RTD Value matched with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
        }else{
            TestLogger.logFail("RTD Value did not match with expected font color : " + HexColorCode + " Actual Font Color : " + hex_RTD);
            IsTestFail = true;
        }

        if(IsTestFail==true){
            Assert.fail();
        }

        //Go Back to Home
        ClickOnHome();



    }


    public void CheckCOVIDToolTip(String FieldType,String ISBN) throws Exception {

        boolean IsTestFail = false;

        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        CommonApi commonApi = new CommonApi();

        //Go Back to Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");


        //Enter Search Key
        driver.findElement(txt_searchbox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(txt_searchbox).sendKeys(Keys.DELETE);
        Thread.sleep(2000);
        //HighlightElement(txt_xpath_searchbox);
        driver.findElement(txt_searchbox).sendKeys(ISBN);
        //TestLogger.logPass("Search Item " + sKey + " has been Entered in Search Box");

        //Click on Search
        driver.findElement(bt_search).click();
        TestLogger.logPass("Search Button has been clicked");

        boolean IsResuleAppearing = IsSearchResultAppearing();
        String val_xpath_RTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//td[9]//span[1]";
        String val_xpath_YTDSales = "//tbody[contains(@class,'MuiTableBody-root')]//td[11]//span[1]";


        if(IsResuleAppearing==false){
            IsTestFail = true;
        }


        //get RTD Tooltip
        String tooltip_RTD = getTooltipText(val_xpath_RTDSales);
        String tooltip_YTD = getTooltipText(val_xpath_YTDSales);


        if(tooltip_RTD.equals(COVID_Text_info1)){
            TestLogger.logPass("Expected Tool Tip is appearing for RTD : " + tooltip_RTD);
        }else{
            TestLogger.logFail("Expected ToolTip: " + COVID_Text_info1 + " Actual : " + tooltip_RTD);
            IsTestFail = true;
        }

        if(tooltip_YTD.equals(COVID_Text_info1)){
            TestLogger.logPass("Expected Tool Tip is appearing for RTD : " + tooltip_YTD);
        }else{
            TestLogger.logFail("Expected ToolTip: " + COVID_Text_info1 + " Actual : " + tooltip_YTD);
            IsTestFail = true;
        }

        //Go Back to Home
        ClickOnHome();

        if(IsTestFail==true){
            Assert.fail();
        }


    }


    public String getTooltipText(String xPath_Element) throws Exception {

        //Tooltip
        Actions action = new Actions(driver);
        WebElement we_tooltip = driver.findElement(By.xpath(xPath_Element));
        action.moveToElement(we_tooltip).perform();

//        we_tooltip.click();
        Thread.sleep(2000);
/*        Actions builder = new Actions (driver);
        builder.clickAndHold().moveToElement(we_tooltip);
        builder.moveToElement(we_tooltip).build().perform();*/

        WebElement toolTipElement = driver.findElement(By.xpath("//div[@role='tooltip']//div[1]//div[1]"));

        String actualTooltip = toolTipElement.getText();
        TestLogger.log("Tool Tip Text : " + actualTooltip);

        return actualTooltip;



    }



}
