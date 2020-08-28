package MethodsApi;

import BaseClass.CommonApi;
import MethodsApi.ElasticSearchLoad_api;
import Reporting.TestLogger;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;

public class BasicSearch_api extends CommonApi {
    //Excel Objects
    private static XSSFWorkbook wb = null;
    private static XSSFSheet sheet = null;
    private static Cell cell = null;
    private static FileOutputStream fio = null;

/*    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
    private Date today = new Date();
    private String outputFile = "C:\\Program Files\\APP_Bookscan_Reports\\BookscanOutput.xlsx";
    private long startTime, endTime, elapsedTime;*/



    public void searchCount(String SearchType,String StringToken) throws IOException, InvalidFormatException {

/*        String testName = new Throwable().getStackTrace()[0].getMethodName();
        dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
        startTime = System.currentTimeMillis();
        String date = dateFormat.format(today);*/


        //Retrieve Test Data from Resource File
        int startRow = 1;
        int res_col = 0;

        CommonApi commonApi = new CommonApi();
        boolean TestCaseStatus = true;

        //Excel Resource file Column
        switch(SearchType){

            case "Search All": res_col = 0;
                break;
            case "ISBN": res_col = 1;
                break;
            case "Author": res_col = 2;
                break;
            case "Title": res_col = 3;
                break;
            default:
                System.out.println("Column Not Found");


        }

        FileInputStream fis = new FileInputStream(sResourceFile);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheetAt(0);

        String SearchKey;
        String SrchType;

        for(int i_row=1;i_row<=sheet.getLastRowNum();i_row++){


            Cell c = sheet.getRow(i_row).getCell(res_col);



            try{
                if(c.getStringCellValue() == null || c.getCellType() == Cell.CELL_TYPE_BLANK){
                    break;
                }

                System.out.println(c.getStringCellValue());

                //Convert Excel Cell Type
                String cellvalue = commonApi.getCellValue(c);
                System.out.println(cellvalue);

//                SearchKey = c.getStringCellValue();
                SearchKey = cellvalue;

                SrchType = getSearchType(SearchType);


                //Api Detail
/*                RestAssured.baseURI = "https://qa-identity.npd.com/";
                String sResource = "/api/books/search?pageSize=1000&query=" + SearchKey + "&type=" + SrchType;


                Response Resp = given().
                        when().
                        get(sResource);*/

                //Convert String Response to Jason
                JsonPath js_token = new JsonPath(StringToken);
                RestAssured.baseURI = "http://bookscandev.npd.com/";
                String sResource = "/api/books/search?pageSize=10000&query=" + SearchKey + "&type=" + SrchType;


                //Grab All Responses
                Response Resp = given().
                        header("Authorization", "Bearer "+ js_token.get("access_token")).
                        header("DataSource","303-0-").
                        when().
                        get(sResource).
                        then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();



                //Convert Raw Response to String
                String responseString = Resp.asString();

                //Convert String Response to Jason
                JsonPath js = new JsonPath(responseString);

                //get all the values
                int nTotalTitle = js.get("results.size()");


                if(nTotalTitle > 0){
                    TestLogger.logPass("Total Record: " + " Key : " + SearchKey + " - " + nTotalTitle);

                }else{
                    TestLogger.logFail("Total Record: " + " Key : " + SearchKey + " - " + nTotalTitle);
                    TestCaseStatus = false;

/*                    softAssert.assertTrue(false);
                    softAssert.assertNotEquals(nTotalTitle,0);
                    softAssert.assertAll();*/

                }


            }catch(RuntimeException e){
                //e.printStackTrace();
                System.out.println("End of the Column");
                break;
            }

        }

        if(TestCaseStatus == false){
            Assert.fail();
        }

    }

    public static void checkResultCount(int cnt){
        Assert.assertEquals(cnt, 0);
    }

    public void testBasicSearchApi(){

        //Get Token
        ElasticSearchLoad_api elasticSearchLoad_api=new ElasticSearchLoad_api();
        String sToken = elasticSearchLoad_api.getApiTokenString();

        //Convert String Response to Jason
        JsonPath js_token = new JsonPath(sToken);

        System.out.println("ACCESS TOKEN: " + js_token.get("access_token"));

        String sfield = "Search All";
        String SearchKey = "Stephen King";
        String SrchType = "0";


/*        RestAssured.baseURI = "http://bookscandev.npd.com/";
        String sResource2 = "/api/lookup/" + sfield;*/

//        RestAssured.baseURI = "https://qa-identity.npd.com/";
        RestAssured.baseURI = "http://bookscandev.npd.com/";
        String sResource = "/api/books/search?pageSize=10000&query=" + SearchKey + "&type=" + SrchType;
//        String sResource = "/api/books/autocomplete?input=" + SearchKey + "&type=" + SrchType;


        //Grab All Responses
        Response Resp2 = given().
                header("Authorization", "Bearer "+ js_token.get("access_token")).
                header("DataSource","49333-303").
                when().
                get(sResource).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();

        //Convert Raw Response to String
        String responseString = Resp2.asString();

        //Convert String Response to Jason
        JsonPath js = new JsonPath(responseString);

        //get all the values
        int nTotalTitle = js.get("results.size()");

        System.out.println("Total Count: " + nTotalTitle);



    }



}
