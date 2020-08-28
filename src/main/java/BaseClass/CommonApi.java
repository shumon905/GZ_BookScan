package BaseClass;

import Reporting.ExtentManager;
import Reporting.ExtentTestManager;
import Reporting.TestLogger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.calcite.tools.Program;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


import java.io.*;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


import static io.restassured.RestAssured.given;

public class CommonApi {

    protected static WebDriver driver = null;
    private static ExtentReports extent;
    protected static long sOverallStartTime;
    protected static long sOverallEndTime;
    protected static long sOverallElapsedTime;
    protected static long sStartTime;
    protected static String currentTestName;
    protected static String actualElapsedTime="";
    public String TestModule;

    protected static String userName = System.getenv("USERNAME");

    Properties prop = new Properties();

    //Excel Objects
    private static XSSFWorkbook wb = null;
    private static XSSFSheet sheet = null;
    private static Cell cell = null;
    private static FileOutputStream fio = null;

    public Assertion hardAssert = new Assertion();
    public SoftAssert softAssert = new SoftAssert();
    public boolean ScreenshotRequired = false;
    public String Api_Token;
    public String envName;
    protected static String env_DQ;
    String env_url;

//    protected static String ScreenShootLocal = "C:\\Program Files\\APP_DecisionKey_Reports\\Mobile\\Screenshots";
//    protected static String ScreenShootLocal = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\Results\\Screenshots";
    protected static String ScreenShootLocal = "C:\\Program Files\\APP_Bookscan_Reports\\Screenshots";
    protected static String htmlReportLocal = "C:\\Program Files\\APP_Bookscan_Reports\\ExtentReports";
    protected static String htmlReportFile = htmlReportLocal + "\\ExtentReport.html";
    protected static String BSresourcefile_parent = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\";
    public static String sResourceFile = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\ResourceFile.xlsx";



    @BeforeTest
    public void setupmethod() throws IOException {
/*        FileInputStream fis = new FileInputStream("C:\\Program Files\\mavenPOMIssue\\src\\main\\resources\\env.properties");

        prop.load(fis);*/


    }


    @BeforeTest
    public static void BeforeAnyTest(){
        sStartTime = System.currentTimeMillis();
        System.out.println("Global Start" + sStartTime);

    }


    public void createSanityOutput(String sanityOutput){
        CommonApi commonApi = new CommonApi();
        createFile(sanityOutput);
        commonApi.waitThreeSecond(driver);
        SanityHeader(sanityOutput);
    }

    public void waitThreeSecond(WebDriver driver) {
        try {
            Thread.sleep(3000L);
            driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        } catch (Exception var3) {
        }

    }


    public boolean IsElementPresent(String xpathString,boolean isAppeared){

        //Appear variable initialioze
        boolean appearing = true;

        if(driver.findElements(By.xpath(xpathString)).size() > 0){
//            TestLogger.logPass("Element : " + xpathString + " exists.");
            appearing = true;
        }else{
            appearing = false;
        }

        if(appearing==isAppeared){
            TestLogger.logPass("Element : " + xpathString + " exists.");
            return true;
        }else{
            TestLogger.logFail("Element : " + xpathString + " does not exist.");
            return false;
        }

    }


    public boolean IsElementPresent(String xpathString){

        if(driver.findElements(By.xpath(xpathString)).size() > 0){
            TestLogger.logPass("Element : " + xpathString + " exists.");
            return true;
        }else{
            return false;
        }

    }


    //Close Drop Down
    public void closeASOpenDropDown(){
        //Close Drop Down
        String ddicon = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall' and not(contains(@aria-label,'change date'))]";
        int ddCount = driver.findElements(By.xpath(ddicon)).size();

        for(int i=0;i<=ddCount;i++){

            try{
                driver.findElements(By.xpath(ddicon)).get(i).click();
                TestLogger.log("Closed Drop Down Value.");
            }catch (Exception e){
                continue;
            }

        }
    }


    public void ClickONExportExcelAndExport(String xPath_Exportbutton,String xPath_ExportOption) throws Exception {

        driver.findElement(By.xpath(xPath_Exportbutton)).click();
        TestLogger.log("Clicked ON Export Button.");

        Thread.sleep(3000);

        //Click on XLSX
        driver.findElement(By.xpath(xPath_ExportOption)).click();
        TestLogger.log("Clicked ON " +xPath_ExportOption  + " Link.");
        Thread.sleep(6000);
    }


    //Create File
    private void createFile(String FilePath) {
        File file = new File(FilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();

                FileOutputStream fos = new FileOutputStream(file);
                XSSFWorkbook wb = new XSSFWorkbook();
                wb.write(fos);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                file.delete();

                file.createNewFile();

                FileOutputStream fos = new FileOutputStream(file);
                XSSFWorkbook wb = new XSSFWorkbook();
                wb.write(fos);
                fos.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static WebDriver HighlightElement(String xPath_Element){

        WebElement element = driver.findElement(By.xpath(xPath_Element));

        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        return driver;
    }

    //Create Output Files
    private void SanityHeader(String FilePath) {
        File file = new File(FilePath);
        try {
            FileInputStream fileIn = new FileInputStream(file);
            Workbook wb = WorkbookFactory.create(fileIn);
            Sheet sheet = wb.createSheet("BSTest");

            sheet.createRow(0).createCell(0).setCellValue("Test Name");
            sheet.autoSizeColumn(0);
            sheet.getRow(0).createCell(1).setCellValue("Test Status");
            sheet.autoSizeColumn(1);
            sheet.getRow(0).createCell(2).setCellValue("Test Exec. Date");
            sheet.autoSizeColumn(2);
            sheet.getRow(0).createCell(3).setCellValue("Elapsed Time");
            sheet.autoSizeColumn(3);

            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void launchbrowser() throws Exception {


        CommonApi commonApi = new CommonApi();

//        commonApi.extentSetup(ITestContext context);

        //Java Check if directory exist if not then create
        ManageExportFolderLocation();
/*        String DirLoc = System.getProperty("java.io.tmpdir") + "Bookscan";
        File file = new File(DirLoc);

        if(file.isDirectory()){
            System.out.println(DirLoc + " : Exists.");

            for(File f : file.listFiles()){

                System.out.println(f.getName());

                if(!f.isDirectory()){
                    f.delete();
                }

            }

            file.delete();
            System.out.println("Deleted Dir :" +  DirLoc);
            Thread.sleep(3000);
            file.mkdir();
            System.out.println("Created Dir :" +  DirLoc);
            Thread.sleep(3000);
        }else{
            System.out.println(DirLoc + " : does not Exist.");
            file.mkdir();
            Thread.sleep(3000);
        }*/


        commonApi.setupDriver(driver, "chrome", "./driver/chromedriver.exe");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.default_content_setting_values.notifications", 2);
        chromePrefs.put("download.default_directory", System.getProperty("java.io.tmpdir") + "Bookscan");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("start-maximized");
//        options.addArguments("--no-sandbox");

        System.out.println("Download Location : " + System.getProperty("java.io.tmpdir") + "Bookscan");

/*
        options.addArguments("enable-automation");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-gpu");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
*/


        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        String currentWindowHandle = driver.getWindowHandle();
        ((JavascriptExecutor)driver).executeScript("alert('Test')");
        driver.switchTo().alert().accept();
        driver.switchTo().window(currentWindowHandle);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);


//        System.out.println(System.getProperty(("user.dir"))+"\\src\\main\\java\\prop\\bookscan_env.properties");


//        String prop_loc = System.getProperty(("user.dir"))+"\\src\\main\\java\\prop\\bookscan_env.properties";
        String prop_loc = "./src/main/java/prop/bookscan_env.properties";
        File f = new File(prop_loc);
        FileReader fileReader = new FileReader(f);
        prop.load(fileReader);

        env_url = prop.getProperty("url");

        //define env
        envName = getEnvName();

//        driver.get("http://wue2bkswbd01.npd.com/");
//        driver.get("http://bookscandev.npd.com/");
        driver.get(env_url);

        System.out.println(env_url);

    }


    public String getDQCheck_Env() throws Exception {

        String prop_loc = "./src/main/java/prop/bookscan_env.properties";
        File f = new File(prop_loc);
        FileReader fileReader = new FileReader(f);
        prop.load(fileReader);

        env_DQ = prop.getProperty("env").toUpperCase();

        return env_DQ;

    }


    public void ManageExportFolderLocation() throws Exception {

        String DirLoc = System.getProperty("java.io.tmpdir") + "Bookscan";
        File file = new File(DirLoc);

        if(file.isDirectory()){
            System.out.println(DirLoc + " : Exists.");

            for(File f : file.listFiles()){

                System.out.println(f.getName());

                if(!f.isDirectory()){
                    f.delete();
                }

            }

            file.delete();
            System.out.println("Deleted Dir :" +  DirLoc);
            Thread.sleep(3000);
            file.mkdir();
            System.out.println("Created Dir :" +  DirLoc);
            Thread.sleep(3000);
//            TestLogger.log("Created New Folder : " + DirLoc);
            Thread.sleep(3000);
        }else{
            System.out.println(DirLoc + " : does not Exist.");
            file.mkdir();
//            TestLogger.log("Created New Folder : " + DirLoc);
            Thread.sleep(3000);
        }


    }


    public String getEnvName() throws Exception {

        String prop_loc = "./src/main/java/prop/bookscan_env.properties";
        File f = new File(prop_loc);
        FileReader fileReader = new FileReader(f);
        prop.load(fileReader);

        env_url = prop.getProperty("url");

        if(env_url.contains("dev")){
            envName = "Dev";
        }else if(env_url.contains("https://bookscanqa.npd.com")){
            envName = "qa";
        }else if(env_url.contains("https://bookscanqa.npd.com")){
            envName = "qa";
        }else if(env_url.contains("wue2bkswbd01")){
            envName = "Dev";
        }else if(env_url.contains("wue2bkseswp")){
            envName = "work";
        }else if(env_url.contains("wue2bkseswp01")){
            envName = "preprod";
        }
        else{
            envName = "prod";
        }

        return envName;

    }

    public String getVersion() throws Exception {

        String prop_loc = "./src/main/java/prop/bookscan_env.properties";
        File f = new File(prop_loc);
        FileReader fileReader = new FileReader(f);
        prop.load(fileReader);

        String version = prop.getProperty("version");

        return version;

    }



    //Setup Chrome Driver
    public void setupDriver(WebDriver driver, String driverType, String driverPath){
        try {
            if (driverType.equalsIgnoreCase("chrome")){
                System.setProperty("webdriver.chrome.driver", driverPath);
            }else if (driverType.equalsIgnoreCase("firefox")){
                System.setProperty("webdriver.gecko.driver", driverPath);
            }else if (driverType.equalsIgnoreCase("ie")){
                System.setProperty("webdriver.ie.driver", driverPath);
            }else if (driverType.equalsIgnoreCase("edge")){
                System.setProperty("webdriver.edge.driver", driverPath);
            }else {
                System.out.println("Invalid driver");
            }
        }catch (Exception e){
            //e.printStackTrace();
        }
    }


    public static String getSearchType(String sType){

        System.out.println(sType.toUpperCase());

        String contype = null;
        if(sType.toUpperCase().equals("SEARCH ALL")){
            contype = "0";
        }else if(sType.toUpperCase().equals("ISBN")){
            contype =  "1";
        }else if(sType.toUpperCase().equals("AUTHOR")){
            contype =  "2";
        }else if(sType.toUpperCase().equals("TITLE")){
            contype =  "3";
        }

        return contype;


    }


    //This Method will Return Unique Token configured for Bookscan Test User
    public String getApiTokenString(){


        //Get Token
        RestAssured.baseURI = "https://test-identity.npd.com/test/";
        String sResource = "/connect/token";

        //Grab All Responses
        Response Resp_token = given().
                formParam("grant_type","client_credentials").
                formParam("scope","bookscan-api").
                formParam("client_id","bookscan-test").
                formParam("client_secret","Test1234").
                when().
                post(sResource).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
        String sResponse = Resp_token.asString();

        System.out.println("Token is Generated..Please Proceed...");

        return sResponse;


    }


    public void searchResults_api(String qValue,String searchType) throws IOException {

        String srchType = getSearchType(searchType);

        System.out.println(srchType);

        //RestAssured.baseURI = prop.getProperty("HOST");
        RestAssured.baseURI = "http://wpwdkdev01.npd.com/bookscan";
        //System.out.println(prop.getProperty("HOST"));
//        String sResource = "/bookscan-dev-experimental/_search";
//        String sResource = "/bookscan-dev-test/_search?size=500";
        String sResource = "/api/books/search?size=500&query=" + qValue + "&type=" + srchType;
        //String sResource = "/api/books/?query=9780735219090&type=1";

        System.out.println(sResource);

        //Grab All Responses
        Response Resp = given().
                when().
                get(sResource).
                then().assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().log().all().
                extract().response();

        //Convert Raw Response to String
        String responseString = Resp.asString();
        //System.out.println(responseString);

        //Convert String Response to Jason
        JsonPath js = new JsonPath(responseString);
        //String stitle = js.get("hits.hits[1]._source.title");
        //System.out.println(stitle);
//        String stitle = js.get("results[0].title");
//        System.out.println(stitle);

/*        //get all the values
        int nTotalTitle = js.get("hits.hits.size()");
        System.out.println("Total Record: " + nTotalTitle);
        System.out.println("********************************************************************");*/

/*        for(int i=0;i<=nTotalTitle-1;i++){
            System.out.println("ISBN : " + js.get("hits.hits[" + i + "]._source.isbn13"));
            System.out.println("Title : " + js.get("hits.hits[" + i + "]._source.title"));
            System.out.println("Author : " + js.get("hits.hits[" + i + "]._source.author"));
            System.out.println("Publisher : " + js.get("hits.hits[" + i + "]._source.publisher"));
            System.out.println("Publisher Family : " + js.get("hits.hits[" + i + "]._source.publisherfamily"));
            System.out.println("Format : " + js.get("hits.hits[" + i + "]._source.format"));
            System.out.println("msrp : " + js.get("hits.hits[" + i + "]._source.msrp"));
            System.out.println("********************************************************************");

        }*/


        //Excel
        //Excel Objects
//        String fPath = "C:\\Users\\Gazi Uddin\\Desktop Items_Gazi\\Bookscan 2.0\\BookscanSearchResult1.xlsx";
        String fPath = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";



        int startRow = 1;
        wb = new XSSFWorkbook();
        sheet = wb.createSheet();
        fio = new FileOutputStream(new File(String.valueOf(fPath)));

        //get all the values
        int nTotalTitle = js.get("results.size()");
        System.out.println("Total Record: " + nTotalTitle);
        System.out.println("********************************************************************");
        Row row = sheet.createRow(0);
        Cell sRow = row.createCell(0);
        sRow.setCellValue("Total Record: ");
        sRow = row.createCell(1);
        sRow.setCellValue(nTotalTitle);

        //Create Columns
        String all_columns = "ISBN|Format|Title|Price|Author|Publisher|Publish Date|RTD Sales|TW Sales|YTD Sales";
        String[] arr_columns = all_columns.split("\\|");

        row = sheet.createRow(1);
        for(int i_col=0;i_col <= arr_columns.length-1;i_col++){
            sRow = row.createCell(i_col);
            //System.out.println(arr_columns[i_col].toString());
            sRow.setCellValue(arr_columns[i_col].toString());
        }



        for(int i=0;i<=nTotalTitle-1;i++){
            System.out.println("ISBN : " + js.get("results[" + i + "].isbn"));
            System.out.println("Title : " + js.get("results[" + i + "].title"));
            System.out.println("Format : " + js.get("results[" + i + "].format"));
            System.out.println("Author : " + js.get("results[" + i + "].author"));
            System.out.println("Publisher : " + js.get("results[" + i + "].publisher"));
            System.out.println("Publish Date : " + js.get("results[" + i + "].publishDate"));
            System.out.println("RTD Sales : " + js.get("results[" + i + "].rtdUnits"));
            System.out.println("TW Sales : " + js.get("results[" + i + "].units"));
            System.out.println("YTD Sales : " + js.get("results[" + i + "].ytdUnits"));
            System.out.println("********************************************************************");


            //Write To Excel
            row = sheet.createRow(i+2);

            sRow = row.createCell(0);
            sRow.setCellValue("" + js.get("results[" + i + "].isbn"));

            sRow = row.createCell(1);
            sRow.setCellValue("" + js.get("results[" + i + "].format"));

            sRow = row.createCell(2);
            sRow.setCellValue("" + js.get("results[" + i + "].title"));

            sRow = row.createCell(3);
            sRow.setCellValue("" + js.get("results[" + i + "].price"));

            sRow = row.createCell(4);
            sRow.setCellValue("" + js.get("results[" + i + "].author"));

            sRow = row.createCell(5);
            sRow.setCellValue("" + js.get("results[" + i + "].publisher"));

            sRow = row.createCell(6);
            sRow.setCellValue("" + js.get("results[" + i + "].publishDate"));

            sRow = row.createCell(7);
            sRow.setCellValue("" + js.get("results[" + i + "].rtdUnits"));

            sRow = row.createCell(8);
            sRow.setCellValue("" + js.get("results[" + i + "].units"));

            sRow = row.createCell(9);
            sRow.setCellValue("" + js.get("results[" + i + "].ytdUnits"));

        }


        wb.write(fio);
        fio.close();



    }


    public int SearchResultCount_Api(String SearchType, String SearchKey){


        String srchType = getSearchType(SearchType);

        System.out.println(srchType);
        RestAssured.baseURI = "http://wpwdkdev01.npd.com/bookscan";
        String sResource = "/api/books/search?size=500&query=" + SearchKey + "&type=" + srchType;
        System.out.println(sResource);

        //Grab All Responses
        Response Resp = given().
                when().
                get(sResource).
                then().assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().log().all().
                extract().response();

        //Convert Raw Response to String
        String responseString = Resp.asString();
        //System.out.println(responseString);

        //Convert String Response to Jason
        JsonPath js = new JsonPath(responseString);
        //get all the values
        int nTotalTitle = js.get("results.size()");

        return nTotalTitle;

    }


    public boolean waitUntilVisible(String xPathItem) throws Exception {
        System.out.println("Wait Until " + xPathItem + " is Visible");
        WebDriverWait wait = new WebDriverWait(driver, 300);
        int DSSize =0;

        int waitCounter = 0;
        boolean Isvisible = true;

        do{
            Thread.sleep(3000);

            try{
                DSSize = driver.findElements(By.xpath(xPathItem)).size();
                System.out.println(DSSize);
            }catch(Exception e){
                DSSize = 0;
            }

            waitCounter++;

            if(waitCounter > 40){
                TestLogger.logFail("Element is not visible after 2 minutes");
                Isvisible = false;
                break;
            }

        }while(DSSize==0);

        return Isvisible;


    }

    public static boolean waitUntilInvisible(String xPathItem) throws Exception {

        System.out.println("Wait Until " + xPathItem + " is invisible");

        WebDriverWait wait = new WebDriverWait(driver, 300);
        int DSSize =0;

        boolean IsInvisible = false;
            int invisibleCounter = 0;

        do{
            Thread.sleep(3000);

            try{
                DSSize = driver.findElements(By.xpath(xPathItem)).size();
                System.out.println(DSSize);
            }catch(Exception e){
                System.out.println(xPathItem + " not found");
            }

            invisibleCounter++;

            if(invisibleCounter>40){
                IsInvisible = false;
                TestLogger.logFail("Element is still not invisible after 2 Minutes");
                break;
            }

        }while(DSSize>0);

        return IsInvisible;


    }

    //Scroll down the page until the web element is visible
    public void scrollDownToElement(WebDriver driver,WebElement sElement){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", sElement);
    }

    //Scroll down the page until the web element is visible
    public void scrollDownToXPath(WebDriver driver,String xPath){

        WebElement sElement = driver.findElement(By.xpath(xPath));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", sElement);
    }

    public void scrollToBottom(WebDriver driver)
    {
//        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0,-1000)");
//        ((JavascriptExecutor) driver).executeScript("scroll(0,300)");
    }

    public void ScrollTotheElemnet(String xpathdetail) throws Exception {
        WebElement element = driver.findElement(By.xpath(xpathdetail));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);


    }

    public static void scrollToTop(WebDriver driver)
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(250, 0)");    }

    public static void scrollToMiddle(WebDriver driver)
    {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight/2,document.body.scrollHeight,document.documentElement.clientHeight/2));");
    }

    @BeforeSuite(alwaysRun = true)
    public void extentSetup(ITestContext context){
//        updateFunctionLibraryRepo();
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
        //genericAPI.killAllBrowserProcess();
    }

    @BeforeMethod(alwaysRun = true)
    public void startExtent(Method method){
        String className = method.getDeclaringClass().getSimpleName();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }



    @AfterMethod
    public void afterEachTestMethod(ITestResult result) throws Exception {
//        DKCommon dkCommon = new DKCommon();
        CommonApi commonApi = new CommonApi();

        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
//        String outputFile = "C:\\Program Files\\APP_DecisionKey_Reports\\MobileSanityOutput.xlsx";
        String outputFile = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
        Date today = new Date();

        long endTime = System.currentTimeMillis();
        long elapsedTime =  endTime - sStartTime;
        System.out.println("End Time : " + endTime);

        System.out.println(endTime);
        System.out.println(dateFormat2.format(new Date(elapsedTime)));

        String date = dateFormat.format(today);


        for (String group : result.getMethod().getGroups()){
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1){
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
            //createTestOutput(result.getName(), "PASSED");

            if(actualElapsedTime!=""){
                testOutput(outputFile, currentTestName, "PASSED", date, actualElapsedTime);
            }else{
                testOutput(outputFile, currentTestName, "PASSED", date, dateFormat2.format(new Date(elapsedTime)));
            }


        } else if (result.getStatus() == 2){
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
            //createTestOutput(result.getName(), "FAILED");
            if(actualElapsedTime!=""){
                testOutput(outputFile, currentTestName, "FAILED", date, actualElapsedTime);
            }else{
                testOutput(outputFile, currentTestName, "FAILED", date, dateFormat2.format(new Date(elapsedTime)));
            }


        } else if (result.getStatus() == 3){
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if ((result.getStatus() == ITestResult.FAILURE) && (ScreenshotRequired == true)){
            commonApi.captureScreenshot(driver, ScreenShootLocal, result.getName());
            driver.quit();
            //setUp();

            //Launch Application
            launchbrowser();

            //Assert Test Fail

        }
    }


    //Is Search Result Appearing in the Table
    public boolean IsSearchResultAppearing() throws Exception {

        String xPathNoData = "//p[contains(@class,'MuiTypography-root') and text()='Your search criteria returned 0 results.']";
        int noDataReturn = 0;
        boolean noDataConfirmed = false;

        int waitCouter = 0;
        do{

            TimeUnit.SECONDS.sleep(1);
            waitCouter ++;

            noDataReturn = driver.findElements(By.xpath(xPathNoData)).size();

            if(noDataReturn>0){
                noDataConfirmed = true;
                break;
            }

            if(waitCouter>60){
                break;
            }

            System.out.println("Record Count : "+ driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size());

        }while(driver.findElements(By.xpath("//tr[contains(@class,'MuiTableRow-root')]")).size()<2);

        //Wait for search Result
        if(noDataConfirmed==true){
            TestLogger.logFail("No Records are appearing.");
            return false;
        }
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


    }

    //Captures Screenshots
    public void captureScreenshot(WebDriver driver, String outPutPath, String screenshotName){
        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            //FileUtils.copyFile(file, new File(System.getProperty("user.dir")+ "/screenshots/"+screenshotName+" "+df.format(date)+".jpg"));
            FileUtils.copyFile(file, new File(outPutPath+"\\"+screenshotName+"("+timeStamp()+").jpg"));
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot "+e.getMessage());
        }
    }


    //Get Time stamp
    public String timeStamp() {
        return GetCurrentTimeStamp();
    }

    // Get current system time
    public String GetCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat(
                "yyyy-MM-dd-HH-mm");// dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        System.out.println(strDate);
        return strDate;
    }

    public Date getTime(long millis){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    private String getStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }



    //Copy files
    public void copyFiles(String sourceFile, String destinationFile){
        try {
            File source = new File(sourceFile);
            File dest = new File(destinationFile);
            FileUtils.copyFile(source, dest);
        }catch (Exception e){
            //e.printStackTrace();
        }
    }

    //Copy all the files in a Directory
    public void copyDirectory(String srcDirectory, String desDirectory) {
        File srcDir = new File(srcDirectory);
        File destDir = new File(desDirectory);

        try {
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    //Delete Files in a Directory
    public static void DeleteFiles(String dirPath) throws IOException {
        FileUtils.cleanDirectory(new File(dirPath));
    }


    protected void testOutput(String FilePath, String testName, String testStatus, String date, String Elapsed_time){
        File file = new File(FilePath);
        try {
            FileInputStream fileIn = new FileInputStream(file);
            Workbook wb = WorkbookFactory.create(fileIn);
            Sheet sheet = wb.getSheet("BSTest");

            XSSFCellStyle Red = (XSSFCellStyle) wb.createCellStyle();
            XSSFFont redFont = (XSSFFont) wb.createFont();
            redFont.setColor(IndexedColors.RED.getIndex());
            Red.setFont(redFont);

            XSSFCellStyle Green = (XSSFCellStyle) wb.createCellStyle();
            XSSFFont greenFont = (XSSFFont) wb.createFont();
            greenFont.setColor(IndexedColors.GREEN.getIndex());
            Green.setFont(greenFont);

            sheet.createRow(sheet.getLastRowNum()+1).createCell(0).setCellValue(testName);
            sheet.autoSizeColumn(0);
            if (testStatus.equalsIgnoreCase("passed")){
                sheet.getRow(sheet.getLastRowNum()).createCell(1).setCellValue(testStatus);
                sheet.getRow(sheet.getLastRowNum()).getCell(1).setCellStyle(Green);
                sheet.autoSizeColumn(1);
            }else {
                sheet.getRow(sheet.getLastRowNum()).createCell(1).setCellValue(testStatus);
                sheet.getRow(sheet.getLastRowNum()).getCell(1).setCellStyle(Red);
                sheet.autoSizeColumn(1);
            }
            sheet.getRow(sheet.getLastRowNum()).createCell(2).setCellValue(date);
            sheet.autoSizeColumn(2);
            sheet.getRow(sheet.getLastRowNum()).createCell(3).setCellValue(Elapsed_time);
            sheet.autoSizeColumn(3);

            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //Send email with total Report and the status count
    protected void sendSanityOutput(String BS_Module, String environment, String browser, String testName, String testSuite, String sanityOutput,String testhtmlURL, String sender_name) throws Exception {

        CommonApi commonApi = new CommonApi();

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");

        sOverallEndTime = System.currentTimeMillis();
        sOverallElapsedTime = sOverallEndTime - sOverallStartTime;

        System.out.println("Unformatted: " + sOverallElapsedTime);
        System.out.println("Formatted: " + dateFormat2.format(new Date(sOverallElapsedTime)));


        String Test_Outputs = sanityOutput;
        ArrayList<String> Test_Name_Array = new ArrayList<>();
        ArrayList<String> Test_Status_Array = new ArrayList<>();
        ArrayList<String> Test_Passed_Array = new ArrayList<>();
        ArrayList<String> Test_Failed_Array = new ArrayList<>();
        ArrayList<String> Test_Date_Array = new ArrayList<>();
        ArrayList<String> Test_ElapsedTime_Array = new ArrayList<>();
        String Test_Name, Test_Status, Test_Date, Test_ElapsedTime;

        FileInputStream fis = new FileInputStream(Test_Outputs);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheetAt(0);

        boolean failureExist = false;

        for (int i = 1; i < sheet.getLastRowNum()+1; i++){
            Test_Name = sheet.getRow(i).getCell(0).getStringCellValue();
            Test_Status = sheet.getRow(i).getCell(1).getStringCellValue();
            Test_Date = sheet.getRow(i).getCell(2).getStringCellValue();
            Test_ElapsedTime = sheet.getRow(i).getCell(3).getStringCellValue();
            Test_Name_Array.add(Test_Name);
            Test_Status_Array.add(Test_Status);
            Test_Date_Array.add(Test_Date);
            Test_ElapsedTime_Array.add(Test_ElapsedTime);
            if (Test_Status.equalsIgnoreCase("PASSED")){
                Test_Passed_Array.add(Test_Name);
            }else {
                Test_Failed_Array.add(Test_Name);
                failureExist = true;
            }
        }
        int totalTests = Test_Status_Array.size();
        String url = testhtmlURL;
        //Send Email
//        String sent_to = "Gazi.Uddin@npd.com";
        String emailList;

        if(failureExist==true){
            emailList = getEmailAddressList("Failed");
        }else{
            emailList = getEmailAddressList("Regular");

        }

        //Send Email
//        emailList = "gazi.uddin@npd.com";
//        emailList = "Alert Notification - PQM Automation Team <dcf8a89f.npd.com@amer.teams.ms>";

        String sent_to = emailList;
        String message_subject = "Bookscan Test Results";


        StringBuilder buf = new StringBuilder();
        buf.append("<html>"
                + "<body>"
                + "<B>Test Name:</B> "+"<a href='"+url.replace(" ","%20")+"'>"+testSuite+"</a>"
                //+ "<B>Test Name:</B> "+"<a href='"+url.replace(" ","%20")+"'>"+BSresourcefile_parent + "\\Results\\HTML Reports\\" + TestModule +"_ExtendReport.html"+"</a>"
                + "<br />"
                + "<B>Host Name: </B>"+GetTestVm()
                + "<br />"
                + "<B>User Name: </B>"+userName
                + "<br />"
                + "<B>Environment: </B>"+  envName //env[1][0].toUpperCase()
                + "<br />"
                + "<br />"
                + "<table border='5' width='300'>"
                + "<tr>"
                + "<th BGCOLOR='33BBFF'>Total Bookscan Tests"
                + "<th BGCOLOR='33BBFF'>Passed"
                + "<th BGCOLOR='33BBFF'>Failed"
                + "</tr>");
        buf.append("<tr><td>")
                .append(totalTests)
                .append("</td><td><font color='green'>")
                .append(Test_Passed_Array.size())
                .append("</font></td><td><font color='red'>")
                .append(Test_Failed_Array.size())
                .append("</font></td>"
                        + "</table>"
                        + "<br />"
                        + "<table border='5'>"
                        + "<tr>"
                        + "<th BGCOLOR='33BBFF'>Test Name"
                        + "<th BGCOLOR='33BBFF'>Test Status"
                        + "<th BGCOLOR='33BBFF'>Test Exec. Date"
                        + "<th BGCOLOR='33BBFF'>Test Elapsed Time"
                        + "</tr>");
        for (int i = 0; i < totalTests; i++){
            if (Test_Status_Array.get(i).equalsIgnoreCase("PASSED")){
                buf.append("<tr><td>")
                        .append(Test_Name_Array.get(i))
                        .append("</td><td><font color='green'>")
                        .append(Test_Status_Array.get(i))
                        .append("</font></td><td>")
                        .append(Test_Date_Array.get(i))
                        .append("</td><td>")
                        .append(Test_ElapsedTime_Array.get(i))
                        .append("</td>");
            }else {
                buf.append("<tr><td>")
                        .append(Test_Name_Array.get(i))
                        .append("</font></td><td><font color='red'>")
                        .append(Test_Status_Array.get(i))
                        .append("</font></td><td>")
                        .append(Test_Date_Array.get(i))
                        .append("</td><td>")
                        .append(Test_ElapsedTime_Array.get(i))
                        //.append("</td><td>");
                        .append("</td>");
            }
        }
        buf.append("</table>"
                + "<br />"
                + "Best Regards,"
                + "<br />"
                + "PQM Automation Team<br />"
                + "</body>"
                + "</html>"
        );
        String message_body = buf.toString();

        commonApi.sendMail(sent_to, message_subject, message_body, sender_name);
    }


    //This Method will generate Random Integer
    public String GenerateRandomNumber(){

        Random rand = new Random();
        int rand_int = rand.nextInt(1000);
        String str = String.valueOf(rand_int);
        return str;
    }


    public String getCellValue(Cell cell){
        Object value = null;

        int dataType = cell.getCellType();

        System.out.println("Cell Data Type:" + dataType);

        switch (dataType){
            case HSSFCell.CELL_TYPE_NUMERIC:
                value = cell.getNumericCellValue();
                break;
            case HSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            //case HSSFCell.CELL_TYPE_BLANK:
            //value = "Empty Cell";
            //break;
        }
        return  value.toString();
    }


    private String getEmailAddressList(String ListType){
        String emailExcelFile = "\\\\w2k8qafs1\\DataQuality\\Data Quality Automation\\Bookscan2.0\\DK_EmailList.xlsx";
        String emailAddress, listToEmail = null, list = null;
        String sheetName;

        List<String> emails = new ArrayList<>();

        if(ListType.equals("Regular")){
            sheetName = "Email to List";
        }else{
            sheetName = "Error Email List";
        }

        try {
            FileInputStream fis = new FileInputStream(emailExcelFile);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            listToEmail = sheet.getRow(1).getCell(0).getStringCellValue();

        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream(emailExcelFile);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(listToEmail.trim());
            for (int i = 1; i < sheet.getLastRowNum()+1; i++){
                emailAddress = sheet.getRow(i).getCell(0).getStringCellValue();
                emails.add(emailAddress);
            }
            list = emails.toString().replace("[", "").replace("]", "");
            //System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }



    //Send email without attachment
    public void sendMail(String send_to, String message_subject, String html_message, String Sender) throws MessagingException, UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp2.npd.com");
        props.put("mail.smtp.ssl.trust", "smtp2.npd.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");


/*        props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "m.outlook.com");
        props.put("mail.smtp.auth", "true");*/

////        final String username = "Gazi.Uddin@office365.com";
//        final String username = "Gazi.Uddin@npd.com";
//        final String passwd = "Layla905%";
//        Properties props = new Properties();
//        props.put("mail.host", "outlook.office365.com");
//        props.put("mail.store.protocol", "pop3s");
//        props.put("mail.pop3s.auth", "true");
//        props.put("mail.pop3s.port", "995");
//
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, passwd);
//            }
//        });



        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("selenium@npd.com", "********");
//                return new PasswordAuthentication("Gazi.Uddin@npd.com", "Layla905%");            }
                return new PasswordAuthentication("selenium@npd.com", "********");            }
        });

        //session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress("selenium@npd.com", Sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(send_to));
        message.setSubject(message_subject);
        message.setContent(html_message, "text/html; charset=utf-8");

        Transport.send(message);
    }


    //Send email with attachment
    public void sendMailWithExcelAttachment(String send_to, String message_subject, String html_message, String linkToLogs, String Sender,String OutputFileName) throws MessagingException, UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp2.npd.com");
        props.put("mail.smtp.ssl.trust", "smtp2.npd.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("selenium@npd.com", "********");
            }
        });

        //session.setDebug(true);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress("selenium@npd.com", Sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(send_to));
        message.setSubject(message_subject);
        message.setContent("Hello", "text/html; charset=utf-8");

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(html_message, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        //String filename = linkToLogs;
        File file = new File(linkToLogs);
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(OutputFileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
        Transport.send(message);
    }



//    public void sendEmailOffice365(){
//
//        Email email = new SimpleEmail();
//
//        email.setHostName("smtp.office365.com");
//        email.setSmtpPort(587);
//        email.setAuthenticator(new DefaultAuthenticator("a@b.com", "****"));
//        email.setStartTLSEnabled(true);
//        try {
//            email.setFrom("a@b.com");
//            email.setSubject("Job Failure");
//            email.setDebug(true);
//            email.setMsg("This is a test mail ... :-)" );
//            email.addTo("a@y.com");
//            email.send();
//        } catch (EmailException e) {
//            e.printStackTrace();
//        }
//
//    }


    // Get Current Host Name
    protected static String GetTestVm(){
        InetAddress localMachine = null;
        try {
            localMachine = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String hostName = localMachine.getHostName();
        System.out.println(hostName);
        return hostName;
    }


    //Get Date Time Stamp String
    public String getCurrentDateTime(){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String sTimeStamp = timestamp.toString();
        String[] arrTimeStamp = sTimeStamp.split(" ");

        DateFormatSymbols dfs = new DateFormatSymbols();

        //Retrieve Date Values
        String[] arrDate = arrTimeStamp[0].split("-");
        String sYear = arrDate[0];
//        int sMonth = Integer.parseInt(arrDate[1]);
        int sMonth = Integer.parseInt(arrDate[1]);
        String[] arrMonth = dfs.getMonths();
        String sMonthName = arrMonth[sMonth];

        String sDay = arrDate[2];


        //Retrieve Time Values
        String[] arrTime = arrTimeStamp[1].split(":");
        String sHour = arrTime[0];
        String sMinute = arrTime[1];
        String sSecond = arrTime[2];

        //Concatenates String
        String sConcatStr = sYear + "_" + sMonthName + "_" + sDay + "_" + sHour + "_" + sMinute + "_" + sSecond;

        return sConcatStr;


    }


    public static void ClickOnHome() throws Exception {
        driver.findElement(By.xpath("//div[contains(@class,'-paperAnchorLeft MuiDrawer-paperAnchorDockedLeft')]//ancestor::div[text()='Home']")).click();
        Thread.sleep(3000);
        TestLogger.log("Clicked On Home");

    }



    public String[] getActualColumnName(){

        String tableHeader = "//span[contains(@class,'MuiButtonBase-root MuiTableSortLabel-root')]";
        int HeaderCount = driver.findElements(By.xpath(tableHeader)).size();
        String[] actual_columns = new String[HeaderCount];

        int tabidx = 0;

        for(int tab_h=0;tab_h<=HeaderCount-1;tab_h++){
            tabidx++;

            try{
                String xPathTH = "//th[contains(@class,'MuiTableCell-root MuiTableCell-head')][" + tabidx + "]//span[contains(@class,'MuiButtonBase-root')]";
                System.out.println(driver.findElement(By.xpath(xPathTH)).getText());
                actual_columns[tab_h] = driver.findElement(By.xpath(xPathTH)).getText();
            }catch(Exception e){
                 break;
            }

        }

        return actual_columns;

    }


    public String getExpectedColumnNames(String ScreenName) throws Exception {
        //Get Expected Values
        FileInputStream fis = new FileInputStream(sResourceFile);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet("ColumnNames");
        int TotalRowRes = sheet.getLastRowNum();

//        String ScreenName = "Basic Search";
        String Rowval;
        String FieldNames = null;

        boolean screenFound = false;
        for(int row_excel = 1;row_excel<=TotalRowRes;row_excel++){
            Rowval = sheet.getRow(row_excel).getCell(0).toString();

            if(Rowval.equals(ScreenName)){

                FieldNames = sheet.getRow(row_excel).getCell(1).toString();
                TestLogger.logPass("Expected Screen Name Found in the Resource File : " + ScreenName);
                screenFound = true;
                break;
            }
        }

        if(screenFound==false){
            TestLogger.logPass("Could not found Expected Screen Name in the Resource File : " + ScreenName);
        }

        return FieldNames;

    }

//    public String getEnv(){
//
//        File file = new File();
//
//
//    }

    public boolean FileExists_PartialText(String Folder,String PartialFileName,String extention){

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

    }


    //This method will delete files from folder depending on creation Date
    public int DeleteFileByDates(String FolderLocation) throws Exception {

        File Folder = new File(FolderLocation);
        File file;
        File[] listOfFiles = Folder.listFiles();

        BasicFileAttributes attributes=null;
        String filepath;
        String filename;

        BasicFileAttributes attr;
        Path path;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


/*
        Date date = new Date();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        System.out.println(formatter.format(date));
*/

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        System.out.println("Date = "+ cal.getTime());
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        System.out.println("Formatted Date : " + formatter.format(cal.getTime()));

        //Date To Complere with
        Date delDate = sdf.parse(formatter.format(cal.getTime()));

        int cntFileDelete = 0;

        for(File file1:listOfFiles){
            filename = file1.getName();
            filepath = FolderLocation + filename;
            path = Paths.get(filepath);
//            file=new File(filepath);
            try{
                attr = Files.readAttributes(path,BasicFileAttributes.class);

//                System.out.println("Creation Date:" + attr.creationTime());
//                String[] splietDate = attr.creationTime().toString().split("T");
                String[] splitModifiedDate = attr.lastModifiedTime().toString().split("T");
//                System.out.println("Last Modified Date : " + splitModifiedDate[0]);

//                System.out.println(splitModifiedDate[0]);
                Date d2 = sdf.parse(splitModifiedDate[0]);

                if(delDate.compareTo(d2)>0){
                    cntFileDelete++;
                    file = new File(filepath);
                    file.delete();
//                    System.out.println("File To Delete : " + filename + " Creation Date: " + splitModifiedDate[0]);

                }

            }catch(Exception e){

            }



        }

        System.out.println("Number Of File Delete From Folder : " + FolderLocation + " is : " + cntFileDelete);

        return cntFileDelete;


    }


    //Check if File exist
    public boolean IsFileExist(String PartialFileName){

        boolean FileExist = false;

        File folder = new File(System.getProperty("java.io.tmpdir") + "Bookscan");
        File[] listOfFiles = folder.listFiles();

        for(int i=0;i<listOfFiles.length;i++){

            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                if(listOfFiles[i].getName().contains(PartialFileName)){
                    FileExist = true;
                    break;
                }

            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }

        }

        return FileExist;

    }


    //Delete files
    public void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            boolean result = Files.deleteIfExists(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete Target Folder
    public void deleteFolder(String folderPath) {
        try {
            FileUtils.deleteDirectory(new File(folderPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete all the files in a directory
    public void deleteAllFilesInDirectory(String directoryPath) {
        try {
            FileUtils.cleanDirectory(new File(directoryPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
