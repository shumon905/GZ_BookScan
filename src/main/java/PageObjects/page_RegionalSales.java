package PageObjects;

import BaseClass.CommonApi;

import BaseClass.CommonApi;
import BaseClass.DBUtil;
import Reporting.TestLogger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;

import static MethodsApi.ElasticSearchLoad_api.getLoadSchema;


public class page_RegionalSales extends CommonApi {

    String regionName;

    public String li_xpath_options = "//li[contains(id,region-search-autocomplete-big-option-)]";
    public String tab_xpath_RegionalSales = "//span[text()='Regional Sales']";
    public String bt_xpath_National = "//span[text()=\"National\"]";
    public String bt_xpath_DMA = "//span[text()=\"DMA\"]";
    public String lbl_xpath_Region = "//h6[text()='Regional Type']";
    public String li_xpath_optionName = "//li[contains(@id,\"region-search-autocomplete-big-option\")][text()=\"" + regionName + "\"]";


    By li_Options = By.xpath(li_xpath_options);
    By tab_RegionalSales = By.xpath(tab_xpath_RegionalSales);
    By bt_National = By.xpath(bt_xpath_National);
    By bt_DMA = By.xpath(bt_xpath_DMA);


    public void checkRegionNames(String RegionType) throws Exception {




        //Log in if alreadu not logged in
        page_Login login = new page_Login();
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);



        page_RegionalSales RS = new page_RegionalSales();
        RS.navigateToSalesRegion();


        scrollToTop(driver);
        //Thread.sleep(3000);

        if(RegionType.equals("National")){
            driver.findElement(bt_National).click();
            //Thread.sleep(1000);
        }else{
            driver.findElement(bt_DMA).click();
            //Thread.sleep(1000);
        }

        //Thread.sleep(3000);

        int TotalItem_UI = driver.findElements(li_Options).size()-1;
        System.out.println(TotalItem_UI);
        Actions action  = new Actions(driver);

        String[] arr_DMA_App = new String[TotalItem_UI];
/*        System.out.println("App Values:");
        System.out.println("**************************");
        System.out.println("**************************");
        System.out.println("**************************");*/
        String UI_Item;
        for(int i_options = 0;i_options<=TotalItem_UI; i_options++){

            try{
                WebElement element = driver.findElement(By.xpath("//li[@id='region-search-autocomplete-big-option-" + i_options + "']"));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView();", element);
                Thread.sleep(10);



                UI_Item = driver.findElement(By.xpath("//li[@id='region-search-autocomplete-big-option-" + i_options + "']")).getText();

                if(UI_Item!=null){
                    arr_DMA_App[i_options]= UI_Item;
                }else{
                    System.out.println(i_options + " in this index no data");
                }

//                System.out.println(arr_DMA_App[i_dma]);

            }catch(Exception e){
                System.out.println("No More DMAs available.");
                break;

            }

        }


        //Get DMA Names from Database
        DBUtil dbUtil = new DBUtil();

        //get latest sybase load schema
        String SchemaName = getLoadSchema();
        String scntQuery;
        String sQuery;
//        String tableName = getTableNameforApiLoad(apiOptionName);

        if(RegionType.equals("National")){
            scntQuery = "Select Count(*) from " + SchemaName + ".m_censusdivisionusa";
        }else{
            scntQuery = "Select Count(*) from " + SchemaName + ".m_dma Where dma != 1611";
        }


        ResultSet rs_cnt = dbUtil.ConnectHive(scntQuery);

        int cntDB = 0;

        if(rs_cnt.next()){
            cntDB = rs_cnt.getInt(1);
            System.out.println("Total Count DB : " + cntDB);
            rs_cnt.close();
        }

        if(RegionType.equals("National")){
            sQuery = "Select * from " + SchemaName + ".m_censusdivisionusa Order By name";
        }else{
            sQuery = "Select * from " + SchemaName + ".m_dma Where dma != 1611 Order By name";
        }

        ResultSet rs = dbUtil.ConnectHive(sQuery);
        String[] arr_DMA_DB = new String[cntDB];

/*        System.out.println("Database Values:");
        System.out.println("**************************");
        System.out.println("**************************");
        System.out.println("**************************");*/

        for(int i_db=0;i_db<=cntDB;i_db++){
            if(rs.next()){
//                System.out.println(rs.getString(4));
                arr_DMA_DB[i_db] = rs.getString(4);
//                System.out.println(arr_DMA_DB[i_db]);
            }else{
                System.out.println("No More DB Data");
            }
        }

        //Validation : Databse Items should present in UI
        TestLogger.log("Validation :: Databse Items Present in UI : " + arr_DMA_DB.length);
        TestLogger.log("*********************************************");
        TestLogger.log("*********************************************");
        TestLogger.log("*********************************************");
        boolean TestFail = false;
        String da_appData;
        String da_DBData;

        System.out.println("Validations : Databse Items should present in UI");
        System.out.println("**************************");
        System.out.println("**************************");
        System.out.println("**************************");


        boolean IsFound;
        for(int i_arrDB=0;i_arrDB<=arr_DMA_DB.length;i_arrDB++ ){
                try{
                    da_DBData = arr_DMA_DB[i_arrDB];

                    IsFound = false;
                    for(int i_arrApp=0;i_arrApp<=arr_DMA_App.length;i_arrApp++){

                        try{
                                //System.out.println("APP : " + arr_DMA_App[i_arrApp]);

                                da_appData = arr_DMA_App[i_arrApp];

                                if(da_DBData.equals(da_appData)){
                                    TestLogger.logPass(da_DBData + " : Passed");
                                    IsFound = true;
                                    break;
                                }

                        }catch(Exception e){
                            System.out.println(da_DBData + " :: Database Item not found in UI");
                            TestLogger.logFail(da_DBData + " :: Database Item not found in UI");
                            if(TestFail == false){
                                TestFail = true;
                            }
                            break;
                        }
                    }

                    if(IsFound = false){
                        TestLogger.logFail(arr_DMA_DB[i_arrDB] + " : Failed");

                        if(TestFail == false){
                            TestFail = true;
                        }

                    }

            }catch(Exception e){

            }

            }





        //UI Items Present in Databse
        TestLogger.log("Validation :: UI Items Present in Databse : " + arr_DMA_App.length);
        TestLogger.log("*********************************************");
        TestLogger.log("*********************************************");
        TestLogger.log("*********************************************");
        String ad_AppData;
        String ad_DBData;


        for(int i_appidx = 0; i_appidx<=arr_DMA_App.length; i_appidx++){

            try{

                ad_AppData = arr_DMA_App[i_appidx];
                IsFound = false;

                if(ad_AppData != null || !ad_AppData.equals(""))
                {

                    for(int i_dbidx = 0;i_dbidx<=arr_DMA_DB.length;i_dbidx++){

                        try{

                            ad_DBData = arr_DMA_DB[i_dbidx];

                            if(ad_AppData.equals(ad_DBData)){
                                TestLogger.logPass(ad_AppData + " : Passed");
                                IsFound = true;
                                break;
                            }

                        }catch(Exception e){
                            System.out.println(ad_AppData + " :: UI Item not found in Database");
                            TestLogger.logFail(ad_AppData + " :: UI Item not found in Database");
                            if(TestFail == false){
                                TestFail = true;
                            }
                            break;
                        }

                    }

                    if(IsFound = false){
                        TestLogger.logFail(ad_AppData + " : Failed");

                        if(TestFail == false){
                            TestFail = true;
                        }

                    }

                }else{
                    System.out.println("Null Found");
                }


            }catch(Exception e){

            }


        }


        if(TestFail == true){
            Assert.fail();
        }



    }


    public void navigateToSalesRegion() throws Exception {



        try{
            if(driver.findElement(By.xpath(lbl_xpath_Region)).isDisplayed()){
            }

        }catch(Exception e){

            String isbn = "9781982110567";

            page_BasicSearch bs = new page_BasicSearch();
            bs.basicSearchAction(isbn);


            //Click on Search Link To navigate Sales And Rank History//*[text()='9780735219090']
            String xPathSearchResultISBN = "//*[text()='" + isbn + "']";
            driver.findElement(By.xpath(xPathSearchResultISBN)).click();
            Thread.sleep(6000);
            driver.findElement(tab_RegionalSales).click();
            Thread.sleep(6000);


        }

    }


    public void selectRegionValidation(String RegionType,String RegionName) throws Exception {


        page_RegionalSales RS = new page_RegionalSales();
        RS.navigateToSalesRegion();

        scrollToTop(driver);
        Thread.sleep(3000);

        if(RegionType.equals("National")){
            driver.findElement(bt_National).click();
            Thread.sleep(1000);
        }else{
            driver.findElement(bt_DMA).click();
            Thread.sleep(1000);
        }

        Thread.sleep(3000);

        boolean TestCaseFail = false;

        //Select Region
        driver.findElement(By.xpath("//li[contains(@id,\"region-search-autocomplete-big-option\")][text()=\"" + RegionName + "\"]")).click();
        Thread.sleep(1000);


        //Validate Item selected
        if(driver.findElements(By.xpath("//span[contains(@class,'MuiChip-label MuiChip-labelSmall')][text()=\"" + RegionName + "\"]")).size() > 0){
            TestLogger.logPass("Region has been selected : " + RegionName);
        }else{
            TestLogger.logFail("Region has not been selected : " + RegionName);
            TestCaseFail = true;
        }

        //Validate Item is appearing on the table
        if(driver.findElements(By.xpath("//table[contains(@class,'MuiTable-stickyHeader')]//ancestor::td[text()=\"" + RegionName + "\"]")).size()>0){
            TestLogger.logPass("Region has been populated in the table : " + RegionName);
        }else{
            TestLogger.logFail("Region has not been populated in the table : " + RegionName);
            TestCaseFail = true;
        }

        //Validate Only 4 rows will be displayed in the table
        int tableRows = driver.findElements(By.xpath("//table[contains(@class,'MuiTable-stickyHeader')]//ancestor::tr")).size();
        if(tableRows==4){
            TestLogger.logPass("4 rows are displayed in the table : Week,Table Header,Total And Selected Region" );
        }else{
            TestLogger.logFail("4 rows are not displayed in the table. Please check. Number of rows : " + tableRows );
        }


        if(TestCaseFail==true){
            Assert.fail();
        }


    }


    public void getHiveDataFromNAV(){

        DBUtil dbUtil = new DBUtil();
        dbUtil.getHiveConnect_NAV();
//        dbUtil.getHiveConnect();
    }



}
