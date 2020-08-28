package PageObjects;

import BaseClass.CommonApi;
import BaseClass.DBUtil;
import Reporting.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Logger;

public class page_LandingPage extends CommonApi {

    public String logo_xpath_npd = "//img[@alt='logo']";
    public String lbl_xpath_bookscan = "//div[contains(text(),'BookScan')]";
    public String txt_xpath_search = "//input[@id='basicSearch']";
    public String lnk_xpath_AdvanceSearch = "//button[@id='advanced-search-button']";
    public String dd_xpath_Datasource = "//div[contains(@aria-labelledby,'data-source-selection')]";
    public String lnk_xpath_Home = "//div[text()='Home']";
    public String lnk_xpath_Bestsellers = "//div[text()='Bestsellers']";
    public String lnk_xpath_Collections = "//div[text()='Collections']";
    public String lnk_xpath_ReportBuilder = "//div[text()='Report Builder']";
    public String lbl_xpath_whatyouliketodo = "//h3[text()='What would you like to do?']";
    public String bt_xpath_search = "//button[contains(@class,'MuiButtonBase-root MuiIconButton-root') and @id='search']";
    public String bt_xpath_bestsellers = "//div[@class='MuiGrid-root MuiGrid-item']//ancestor::a[@id='bestsellers']";
    public String bt_xpath_collections = "//div[@class='MuiGrid-root MuiGrid-item']//ancestor::a[@id='collections']";
    public String bt_xpath_reportbuilder = "//div[@class='MuiGrid-root MuiGrid-item']//ancestor::a[@id='reportbuilder']";
    public String lnk_xpath_bottonNPDLink = "//a[@href='https://www.npd.com' and text()='The NPD Group, Inc.']";
    public String lnk_xpath_Logout = "//a[@href='/account/logout']";
    public String bt_xpath_userinitial = "//div[text()='B']";
//    public String a_xpath_Datasource = "//a[@class='MuiTypography-root MuiLink-root MuiLink-underlineNone MuiTypography-colorPrimary']";
    public String a_xpath_Datasource = "//li[contains(@class,'MuiButtonBase-root MuiListItem-root')]";
    public String dd_xpath_DataSource = "//div[@id='data-source-selection']";
    public String bt_xpath_leftContent_Bestsellers = "//div[contains(@class,'MuiDrawer-root MuiDrawer-docked')]//ancestor::div[text()='Bestsellers']";
    public String bt_xpath_leftContent_Collections = "//div[contains(@class,'MuiDrawer-root MuiDrawer-docked')]//ancestor::div[text()='Collections']";
    public String bt_xpath_leftContent_ReportBuilder = "//div[contains(@class,'MuiDrawer-root MuiDrawer-docked')]//ancestor::div[text()='Report Builder'']";
//    private String lbl_xpath_version = "//p[contains(@class,'MuiTypography-root')]";
//    private String bt_xPath_Help = "//span[contains(@class,'MuiButton-label')]//ancestor::div[text()='Help']";

    private String bt_xPath_Help = "//*[name()='svg']//*[@id='Symbols']";
    private String title_xpath_HelpCenter = "//div[contains(@class,'MuiGrid-root')]//ancestor::h2[text()='Help Center']";

    private String bt_xpath_avatar = "//div[contains(@class,'MuiAvatar-root MuiAvatar-circle')]";
    private String lbl_xpath_version =  "//span[contains(@class,'MuiTypography-root MuiTypography-caption MuiTypography-displayBlock')]";

    By logo_npd = By.xpath(logo_xpath_npd);
    By lbl_bookscan = By.xpath(lbl_xpath_bookscan);
    By lnk_Logout = By.xpath(lnk_xpath_Logout);
    By bt_userinitial = By.xpath(bt_xpath_userinitial);
    By dd_Datasource = By.xpath(dd_xpath_DataSource);
    By a_DataSource = By.xpath(a_xpath_Datasource);
    By lbl_Version = By.xpath(lbl_xpath_version);




    public void CheckLandingPageContents() throws Exception {

        CommonApi commonApi = new CommonApi();
        page_Login login = new page_Login();

        //Login
        login.Login("Bob Backline","Baseball1");
        Thread.sleep(5000);

        //Validate Landing Page Elements
        boolean IsTestFail = false;

        if(IsElementPresent(logo_xpath_npd,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_bookscan,true)==false){ IsTestFail = true; }
        if(IsElementPresent(txt_xpath_search,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lnk_xpath_AdvanceSearch,true)==false){ IsTestFail = true; }
        if(IsElementPresent(dd_xpath_Datasource,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lnk_xpath_Home,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lnk_xpath_Bestsellers,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lnk_xpath_Collections,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lnk_xpath_ReportBuilder,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lbl_xpath_whatyouliketodo,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_search,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_bestsellers,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_collections,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_reportbuilder,true)==false){ IsTestFail = true; }
//        if(IsElementPresent(lnk_xpath_bottonNPDLink,true)==false){ IsTestFail = true; }

        if(IsTestFail==true){
            Assert.fail();
        }

/*
        if(IsElementPresent(bt_xpath_collections,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xpath_collections,true)==false){ IsTestFail = true; }

        IsElementPresent(lbl_xpath_bookscan,true);
        IsElementPresent(lbl_xpath_bookscan,true);
        IsElementPresent(txt_xpath_search,true);
        IsElementPresent(lnk_xpath_AdvanceSearch,true);
        IsElementPresent(dd_xpath_Datasource,true);
        IsElementPresent(lnk_xpath_Home,true);
        IsElementPresent(lnk_xpath_Bestsellers,true);
        IsElementPresent(lnk_xpath_Collections,true);
        IsElementPresent(lnk_xpath_ReportBuilder,true);
        IsElementPresent(lbl_xpath_whatyouliketodo,true);
        IsElementPresent(bt_xpath_search,true);
        IsElementPresent(bt_xpath_bestsellers,true);
        IsElementPresent(bt_xpath_collections,true);
        IsElementPresent(bt_xpath_reportbuilder,true);
        IsElementPresent(lnk_xpath_bottonNPDLink,true);
*/


    }


    public void Logout() throws Exception {


        CommonApi commonApi = new CommonApi();
        page_Login login = new page_Login();

        //Login
        //Log in if already not logged in
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        driver.findElement(bt_userinitial).click();
        Thread.sleep(2000);

        //Check if logout link exists
        if(IsElementPresent(lnk_xpath_Logout)==true){
            driver.findElement(lnk_Logout).click();
            Thread.sleep(2000);

            if(IsElementPresent("//input[@id='Username']") == true){
                TestLogger.logPass("Application Loggedout successfully");
            }else{
                TestLogger.logFail("Application did not logged out successfully.");
                Assert.fail();
            }

        }else{
            driver.close();
            TestLogger.logFail("Unable to Find Log Out Button.");
            Assert.fail();
        }


    }


    public void CheckAppVersion() throws Exception {

//        String ExpectedVersion = "1.0.0-beta.1";


        envName = getEnvName();

        String ExpectedVersion = getVersion();
//        ExpectedVersion = "1.0.0-beta.2+7";

//        if(envName.toUpperCase().equals("PROD")){
            if(ExpectedVersion.contains("+")){
                //split
                String[] sVer = ExpectedVersion.split("\\+");
                ExpectedVersion = sVer[0];
                System.out.println(sVer[0]);
            }
        //}

        String ver_lable;

        boolean IsFailed = false;
        boolean VersionTextFound = false;

        CommonApi commonApi = new CommonApi();
        page_Login login = new page_Login();

        //Login
        login.Login("Bob Backline","Baseball1");

        //Click on Avatar on Right Top Corner
        driver.findElement(By.xpath(bt_xpath_avatar)).click();
        Thread.sleep(1000);
        ////span[@class='MuiTypography-root MuiTypography-caption MuiTypography-displayBlock']

        int cntlbl = driver.findElements(By.xpath(lbl_xpath_version)).size();

        if(cntlbl>0){

            for(int i=0;i<=cntlbl;i++){

                ver_lable = driver.findElements(By.xpath(lbl_xpath_version)).get(i).getText();

                if(ver_lable.contains("Version")){
                    VersionTextFound = true;

 /*                   if(envName.toUpperCase().equals("PROD")){
                        if(ver_lable.contains("+")){
                            //split
                            String[] sVer = ver_lable.split("\\+");
                            System.out.println(sVer[0]);
                        }
                    }*/

                    if(ver_lable.contains(ExpectedVersion)){
                        TestLogger.logPass("Bookscan Correct Version is Population:" + ver_lable);
                    }else{
                        TestLogger.logFail("Version is not matching. Actual : " + ver_lable + " Expected: " + ExpectedVersion);
                        IsFailed = true;
                    }

                    break;
                }

            }


        }

        if(VersionTextFound==false){
            TestLogger.logFail("Version Text did not found");
            IsFailed = true;
        }

        if(IsFailed==true){
            Assert.fail();
        }




    }


//    public void MariaDBTest() throws Exception {
//
//        DBUtil dbUtil = new DBUtil();
//        dbUtil.connectMariaDB();
//
//    }


    public void CheckDisplayUserName() throws Exception {

        CommonApi commonApi = new CommonApi();
        page_Login login = new page_Login();

        //Login
        //Log in if already not logged in
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Click On Home
        ClickOnHome();

        //Check User Display Name
        if(IsElementPresent("//h1[text()='Bob']")==true){
            TestLogger.logPass("User First Name is displayed after login.");
        }else{
            driver.close();
            TestLogger.logFail("User First Name is not displayed after login.");
            Assert.fail();
        }

    }


    public void NavigateToHelpCenter() throws Exception {

        CommonApi commonApi = new CommonApi();
        page_Login login = new page_Login();

        //Login
        //Log in if already not logged in
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Click On Home
        ClickOnHome();

        //Click on Help
        driver.findElement(By.xpath(bt_xPath_Help)).click();
        TestLogger.log("Clicked on Help Button.");
        Thread.sleep(3000);

        int cnt = driver.findElements(By.xpath(title_xpath_HelpCenter)).size();

        if(cnt>0){
            TestLogger.logPass("Help Center Screen is appearing.");
        }else{
            TestLogger.logFail("Help Center Screen is not appearing.");
            Assert.fail();
        }

        //Click On Home
        ClickOnHome();

    }


    public void CheckDataSourceAccess() throws Exception {

        CommonApi commonApi = new CommonApi();
        page_Login login = new page_Login();

        //Login
        //Log in if already not logged in
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Click On Home
        ClickOnHome();

        //Click on DataSource Selection DropDown
        driver.findElement(dd_Datasource).click();
        Thread.sleep(1000);

        //Get All the Datasourceses Name
        List<WebElement> DSNames = driver.findElements(a_DataSource);
        //System.out.println(DSNames.size());

        if(DSNames.size()>0){
            for (WebElement webElement : DSNames) {
                String name = webElement.getText();
//                System.out.println(name);
            }

        }



        //Get DataSource Names from Database
        //Connect To Oracle Database. SRV_PDK

        String []DatasourceNames_DB = new String[3];
        DatasourceNames_DB[0] = "US BookScan";
        DatasourceNames_DB[1] = "B&N BookScan";
        DatasourceNames_DB[2] = "US PubTrack Digital";


/*
        List<String>DatasourceNames_DB;

        DBUtil dbUtil = new DBUtil();

        String sQuery = "SELECT DISTINCT summaryschema.DISPLAY_NAME SUMMARY_SOURCE_TYPE_NAME, dar.display_name DATA_ACCESS_ROLE_NAME\n" +
                "FROM NPDREPORTMANAGER_PROD.TB_DATA_ACCESS_ROLE dar INNER JOIN NPDREPORTMANAGER_PROD.TB_COMPANY c ON dar.company_id = c.company_id\n" +
                "INNER JOIN NPDREPORTMANAGER_PROD.TB_DEPARTMENT d ON d.company_id = c.company_id AND Upper(d.department) IN ('BPNA')\n" +
                "INNER JOIN NPDREPORTMANAGER_PROD.TB_ACCESS_GROUP_ROLE agr ON agr.data_access_role_id = dar.data_access_role_id AND agr.department_id = d.department_id\n" +
                "INNER JOIN NPDREPORTMANAGER_PROD.TB_MODULE_SUMMARY_SCHEMA summaryschema ON UPPER(summaryschema.MODULE) = UPPER(dar.MODULE)\n" +
                "INNER JOIN NPDREPORTMANAGER_PROD.TB_W_VIEW_PACKAGE viewPackage ON viewPackage.SUMMARY_SOURCE_TYPE_ID = summaryschema.SUMMARY_SOURCE_TYPE_ID\n" +
                "WHERE dar.active_ind = 'T' AND dar.dashboard_app_ind = 'T' AND summaryschema.MODULE = 'bsc' --AND summaryschema.SUMMARY_SOURCE_TYPE_ID = 303 \n" +
                "AND agr.DATA_ACCESS_ROLE_ID != 48699\n" +
                "AND EXISTS (SELECT SUMMARY_SOURCE_TYPE_ID\n" +
                "FROM NPDREPORTMANAGER_PROD.TB_REPORT_COLUMN_SOURCE_TYPE\n" +
                "WHERE SUMMARY_SOURCE_TYPE_ID = summaryschema.SUMMARY_SOURCE_TYPE_ID\n" +
                "AND SUMMARY_SOURCE_TYPE_ID not in (668,679))";

        DatasourceNames_DB = dbUtil.readDataBasePDK(sQuery,"SUMMARY_SOURCE_TYPE_NAME|DATA_ACCESS_ROLE_NAME");
*/

        String DS_DB;
        boolean DSFoundinUI = false;

        for(int iDS_DB= 0; iDS_DB<= DatasourceNames_DB.length-1;iDS_DB++){
//        for(int iDS_DB= 0; iDS_DB<= DatasourceNames_DB.size()-1;iDS_DB++){
            DS_DB = DatasourceNames_DB[iDS_DB];

            if(DSNames.size()>0){
                DSFoundinUI = false;
                for (WebElement webElement : DSNames) {
                    String name = webElement.getText();
                    //System.out.println(name);

                    if(name.equals(DS_DB)){
                        TestLogger.logPass(DS_DB + " Found in UI");
                        DSFoundinUI = true;
                        break;
                    }

                }

                if(DSFoundinUI == false){
                    TestLogger.logFail(DS_DB + " is not found in UI");
                    Assert.fail();
                    driver.close();
                }

            }else{
                TestLogger.logFail("No DataSource is displaying in the Application");
                Assert.fail();
                driver.close();
            }

        }





    }


    public void SelectDatasource(String datasource) throws Exception {

        //Click on DataSource Selection DropDown

        try{
            if(driver.findElement(By.xpath("//div[@id='data-source-selection']")).getText().equals(datasource)){
                TestLogger.logPass("Datasource : " + datasource + " is already selected");

            }else{
                driver.findElement(dd_Datasource).click();
                Thread.sleep(1000);

                //Select Datasource
                driver.findElement(By.xpath("//a[text()='" + datasource + "']")).click();
                Thread.sleep(3000);
                TestLogger.logPass("Datasource : " + datasource + " is selected");

            }

        }catch (Exception e){
            TestLogger.logFail("Unable to select Datasource : " + datasource);
        }




    }


    public void NavigateToBestsellerList() throws Exception {

        boolean IsFailed = false;


        CommonApi commonApi = new CommonApi();
        page_Login login = new page_Login();

        //Login
        //Log in if alreadu not logged in
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Click on Home
        ClickOnHome();

        //Select Datasource
        page_LandingPage page_landingPage = new page_LandingPage();
        page_landingPage.SelectDatasource("US BookScan");

        //Click on Bestseller button
        driver.findElement(By.xpath(bt_xpath_bestsellers)).click();
        TestLogger.log("Clicked on Bestseller Button");

        Thread.sleep(3000);

        if(driver.findElements(By.xpath("//div[contains(@class,'MuiGrid-root') and text()='Recently Viewed Bestseller Lists']")).size()>0){
            TestLogger.logPass("Bestseller Screen is appearing");
        }else{
            TestLogger.logFail("Bestseller Screen is not appearing");
            IsFailed = true;
        }


        //Click on Home
        ClickOnHome();

        if(IsFailed==true){
            Assert.fail();
        }

    }




    public void NavigateToCollectionList() throws Exception {

        boolean IsFailed = false;


        CommonApi commonApi = new CommonApi();
        page_Login login = new page_Login();

        //Login
        //Log in if alreadu not logged in
        login.CheckLoginBoundary("Bob Backline","Baseball1",true);

        //Click on Home
        ClickOnHome();

        //Click on Bestseller button
        driver.findElement(By.xpath(bt_xpath_collections)).click();
        TestLogger.log("Clicked on Collection Button");

        Thread.sleep(3000);

        if(driver.findElements(By.xpath("//p[contains(@class,'MuiTypography-root') and text()='View your collections to compare, export and view at a glance key title vs title sales']")).size()>0){
            TestLogger.logPass("Collection Screen is appearing");
        }else{
            TestLogger.logFail("Collection Screen is not appearing");
            IsFailed = true;
        }


        //Click on Home
        ClickOnHome();

        if(IsFailed==true){
            Assert.fail();
        }

    }


}
