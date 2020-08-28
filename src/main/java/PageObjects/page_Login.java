package PageObjects;

import BaseClass.CommonApi;
import Reporting.TestLogger;
import org.openqa.selenium.By;
import org.testng.Assert;

public class page_Login extends CommonApi {


    public String txt_xPath_Userid = "//input[@id='Username']";
    public String txt_xPath_Password = "//input[@id='Password']";
    public String bt_xPath_Next = "//button[@id='nextBtn']";
    public String bt_xPath_SignIn = "//button[@class='btn btn-raised btn-primary two-button sign-in-button']";
    public String img_xPath_NPDLogo = "//div[@class='npd-logo']";
    public String label_xPath_SignIn = "//h4[text()='Sign in']";
    public String label_xPath_errorLogin = "//li[text()='Your Username/Password is not correct. Please try again. Remember, passwords are case-sensitive.']";
    public String label_xPath_errorUserName = "//span[@id='Username-error']";
    public String label_xPath_errorPassword = "//span[@id='Password-error']";
    public String label_liketoDo = "//h3[text()='What would you like to do?']";
    private String label_xPath_Copyright = "//small[text()='Copyright 2020. The NPD Group, Inc.  All Rights Reserved.  Proprietary and Confidential Property of NPD and its Affiliates, Licensed for Use by NPD Clients Only.']";
    private String label_xPath_Terms = "//a[@href='#' and text()='Terms']";
    private String label_xPath_Privacy = "//a[@href='#' and text()='Privacy']";
    private String label_xPath_forgotpassword = "//p[contains(text(),'Forgot your username')]";
    private String lnk_xPath_ClickHere = "//a[@href='#' and text()='Click here']";
    private String label_xPath_Needhelp = "//p[contains(text(),'Need help signing in?')]";
    private String lnk_xPath_ContactSupport = "//a[@href='#' and text()='Contact Support']";




    By txt_Userid = By.xpath(txt_xPath_Userid);
    By txt_Password = By.xpath(txt_xPath_Password);
    By bt_Next = By.xpath(bt_xPath_Next);
    By bt_Signin = By.xpath(bt_xPath_SignIn);
    By img_NPDLogo = By.xpath(img_xPath_NPDLogo);
    By label_errorLogin = By.xpath(label_xPath_errorLogin);
    By label_errorUsername = By.xpath(label_xPath_errorUserName);




    public void CheckLoginPageElements(){

//        CommonApi commonApi = new CommonApi();

        boolean IsTestFail = false;

        if(IsElementPresent(txt_xPath_Userid,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xPath_Next,true)==false){ IsTestFail = true; }
        if(IsElementPresent(img_xPath_NPDLogo,true)==false){ IsTestFail = true; }
        if(IsElementPresent(label_xPath_SignIn,true)==false){ IsTestFail = true; }
        if(IsElementPresent(label_xPath_Copyright,true)==false){ IsTestFail = true; }
        if(IsElementPresent(label_xPath_Terms,true)==false){ IsTestFail = true; }
        if(IsElementPresent(label_xPath_forgotpassword,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lnk_xPath_ClickHere,true)==false){ IsTestFail = true; }



        //Enter User ID and Click on Next
        driver.findElement(txt_Userid).sendKeys("Bob Backline");
        driver.findElement(bt_Next).click();


        if(IsElementPresent(txt_xPath_Password,true)==false){ IsTestFail = true; }
        if(IsElementPresent(lnk_xPath_ContactSupport,true)==false){ IsTestFail = true; }
        if(IsElementPresent(bt_xPath_SignIn,true)==false){ IsTestFail = true; }
        if(IsElementPresent(label_xPath_Needhelp,true)==false){ IsTestFail = true; }


        if(IsTestFail==true){
            Assert.fail();
        }


    }


    public boolean Login(String Userid,String Password) throws Exception {


        //Enter User ID
        driver.findElement(txt_Userid).sendKeys(Userid);
        TestLogger.logPass("Entered User ID : " + Userid);

        driver.findElement(bt_Next).click();
        TestLogger.logPass("Click on Next in Button.");

/*        if(driver.findElement(By.xpath(label_xPath_errorUserName)).isDisplayed()){
            return false;
        }*/

        //Enter Password
        driver.findElement(txt_Password).sendKeys(Password);
        TestLogger.logPass("Entered Password : " + Password);

        driver.findElement(bt_Signin).click();
        TestLogger.logPass("Click on Sign in Button.");

        if(driver.findElements(By.xpath("//span[text()='Got it!']")).size()>0){
            driver.findElement(By.xpath("//span[text()='Got it!']")).click();
            Thread.sleep(2000);
            TestLogger.log("Got it! - info pop up is appearing as it is the first time login.");
        }else{
            TestLogger.log("Got it! - info pop up is not appearing as it is not the first time login.");
        }



        if(driver.findElements(By.xpath("//span[@class='MuiIconButton-label']")).size() > 0){
            TestLogger.log("Sucessfully Logged in.");
            return true;
        }else{
            TestLogger.log("Not Logged in.");
            return false;
        }


    }


    public void BlankUserIDCheck(){

        //Click on Next without entering User ID
        driver.findElement(bt_Next).click();
        if(driver.findElements(By.xpath(label_xPath_errorUserName)).size()>0){
            TestLogger.logPass("Unable to proceed using Blank User ID");
        }else{
            TestLogger.logFail("Able to proceed using Blank User ID");
        }

    }


    public void BlankPasswordCheck(String Userid){

        //Enter User ID
        driver.findElement(txt_Userid).sendKeys(Userid);
        driver.findElement(bt_Next).click();

        //Do not Enter Password and Click Login
        driver.findElement(bt_Signin).click();

        if(driver.findElements(By.xpath(label_xPath_errorPassword)).size()>0){
            TestLogger.logPass("Unable to proceed using Blank Password");
        }else{
            TestLogger.logFail("Able to proceed using Blank Password");
        }

    }


    public void CheckLoginBoundary(String Userid,String Password,boolean ExpectedStatus) throws Exception {


        //Check URL Value
        String sURLValue = driver.getCurrentUrl();
        TestLogger.log(sURLValue);

        int loginScreenAvailable = driver.findElements(By.xpath(label_liketoDo)).size();

        if(loginScreenAvailable==0) {

            page_Login page_login = new page_Login();
            boolean IsLoginSucessfully = page_login.Login(Userid,Password);

            if(ExpectedStatus == IsLoginSucessfully){
                TestLogger.logPass("Login functionality passed. User ID: " + Userid + " Password: " + Password);
            }else{
                TestLogger.logFail("Login functionality Failed. User ID: " + Userid + " Password: " + Password);
                Assert.fail();

            }
        }


    }



    public void CheckNoProAccess(String UID, String PWD) throws Exception {

        //Log in
        CheckLoginBoundary(UID,PWD,true);

        //Check "Advance Search" option will not appear
        int cntBTAdvanceSearch = driver.findElements(By.xpath("//button[@type='button' and @id='advanced-search-button']")).size();

        boolean IsTestFailed = false;

        if(cntBTAdvanceSearch>0){
            TestLogger.logFail("Advance Search Option is appearing. It should not appear for the User who does not have access to Pro.");
            IsTestFailed = true;
        }else{
            TestLogger.logPass("Advance Search Option is not appearing as expected.It should not appear for the User who does not have access to Pro.");
        }

        if(IsTestFailed==true){
            Assert.fail();
        }




    }



}
