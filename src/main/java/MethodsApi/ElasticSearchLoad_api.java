package MethodsApi;

import BaseClass.CommonApi;
import BaseClass.DBUtil;
import Reporting.TestLogger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jodd.util.StringUtil;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.*;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.search.sort.SortOrder;
import org.mortbay.io.EndPoint;
import org.testng.Assert;

import javax.print.attribute.TextSyntax;
import java.awt.print.Book;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.*;

//import org.json.JSONArray;
//import org.json.JSONObject;


public class ElasticSearchLoad_api extends CommonApi {


    public void getSecuredApiData(String sTokenString){

        //Get Token
        RestAssured.baseURI = "https://test-identity.npd.com/test/";
        String sResource = "/connect/token";

        //Grab All Responses
        Response Resp_token = given().
                    formParam("grant_type","client_credentials").
                    formParam("scope","bookscan-api").
                    formParam("client_id","bookscan-test").
                    formParam("client_secret","Test1234").
//                        body().
                    when().
                    post(sResource).
                    then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();
        String sResponse = Resp_token.asString();

        //Convert String Response to Jason
        JsonPath js1 = new JsonPath(sResponse);
        System.out.println("JASONOUT : " + js1.get("access_token").toString());
        System.out.println("Token : " + sResponse);

        //Api Detail
        RestAssured.baseURI = "http://wue2bkswbd01.npd.com/";
        String sResource2 = "/api/lookup/formats";

        //Grab All Responses
        Response Resp2 = given().
                header("Authorization", "Bearer "+ js1.get("access_token")).
                when().
                get(sResource2).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();

        //Convert Raw Response to String
        String responseString = Resp2.asString();

        System.out.println("RES STR : " + responseString);

        //Convert String Response to Jason
        JsonPath js = new JsonPath(responseString);

        //get all the values
        int nTotalTitle = js.get("results.size()");

        System.out.println(nTotalTitle);

    }


    public void AzureInsightApi(String AzureEnv,String success){

        String sResource = null;

        if(AzureEnv.equals("dev")){
            RestAssured.baseURI = "https://appi-bks-functions-test.azurewebsites.net";
            sResource = "/api/TrackAvailability?code=/0h46dDQ9zuHIASleFaX4TruT1sM4hJkazlNdlwWMiy5f24ZCq9veQ==";
        }else if(AzureEnv.equals("prod")){
            RestAssured.baseURI = "https://appi-bks-functions-test.azurewebsites.net";
            sResource = "/api/TrackAvailability?code=/0h46dDQ9zuHIASleFaX4TruT1sM4hJkazlNdlwWMiy5f24ZCq9veQ==";

        }


        //Grab All Responses
        Response Resp_token = given().
                queryParam("name","Test_Name").
                queryParam("success",success).
                queryParam("duration","3").
                queryParam("runLocation","Port Washington").
                queryParam("message","Test").
//                        body().
        when().
                        get(sResource).
                        then().assertThat().statusCode(200).and().extract().response();


        String responseString = Resp_token.asString();
        System.out.println("RES STR : " + responseString);

        //Convert String Response to Jason
        JsonPath js = new JsonPath(responseString);


    }


/*
    public String getApiTokenString(){


        //Get Token
        RestAssured.baseURI = "https://qa-identity.npd.com/";
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
*/


    public void DataLoadElasticSearch_Updated(String sfield,String StringApiToken,String datasource) throws Exception {

/*
        //Get Hive Data
        List<String> lookupdata_hive = getAdvanceSearchValues(sfield);

*/

        //Api Detail
//        RestAssured.baseURI = "http://wue2bkswbd01.npd.com/";
        RestAssured.baseURI = "http://bookscandev.npd.com/";
        String sResource2 = "/api/lookup/" + sfield;


        //Convert String Response to Jason
        JsonPath js_token = new JsonPath(StringApiToken);

        System.out.println("ACCESS TOKEN: " + js_token.get("access_token"));

        String datasource_code = getDataSourceCode_Api(datasource);

        //Grab All Responses
        Response Resp2 = given().
                header("Authorization", "Bearer "+ js_token.get("access_token")).
                header("DataSource",datasource_code).
                when().
                get(sResource2).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().extract().response();

        //Convert Raw Response to String
        String responseString = Resp2.asString();

        //Convert String Response to Jason
        JsonPath js = new JsonPath(responseString);

//        System.out.println(responseString);


        //get all the values
        int nTotalTitle = js.get("results.size()");
        String TotalCount_Api = Integer.toString(nTotalTitle);


        //Store Api Data in a String Array
        String[] lookUpApi = new String[nTotalTitle];

        String record_api;
        int idx = 0;
        for(int i_api = 0;i_api<=nTotalTitle-1;i_api++){
//            System.out.println(js.get("name[3]").toString());
            record_api = js.get("name[" + i_api + "]").toString().toUpperCase();
            if(record_api.equals("OTHER") || record_api.equals("NOT SPECIFIED")|| record_api.equals("NON-BOOK")){
//                System.out.println("NOT APPEARING : " + record_api);
            }else{
                lookUpApi[idx] = js.get("name[" + i_api + "]").toString();
                System.out.println(lookUpApi[idx]);
                idx++;
            }

        }


         //Get Hive Data
        List<String> lookupdata_hive = getAdvanceSearchValues(sfield,datasource);

        boolean overallpass = true;
        boolean ItemFound = false;

        //Validation
        for(int i_val=0;i_val<=lookupdata_hive.size()-1;i_val++){

/*
            if(lookupdata_hive.get(i_val).toUpperCase().replaceAll("\\s","").equals(lookUpApi[i_val].toUpperCase().replaceAll("\\s",""))){
                TestLogger.logPass(lookupdata_hive.get(i_val) + " matched");

            }else{
                TestLogger.logFail("database value : " + lookupdata_hive.get(i_val) + " api value : " + lookUpApi[i_val] + " did not match");
                overallpass = false;
            }
            */


            for(int i_idxApi = 0;i_idxApi<nTotalTitle-1;i_idxApi++){
/*                System.out.println(lookupdata_hive.get(i_val).toUpperCase().replaceAll("\\s",""));
                System.out.println(lookUpApi[i_idxApi].toUpperCase().replaceAll("\\s",""));*/

                try{
                    if(lookupdata_hive.get(i_val).toUpperCase().replaceAll("\\s","").equals(lookUpApi[i_idxApi].toUpperCase().replaceAll("\\s",""))){
                        TestLogger.logPass(lookupdata_hive.get(i_val) + " matched");
                        ItemFound = true;
                        break;
                    }

                }catch (Exception e){
/*                    System.out.println(i_val);
                    System.out.println(i_idxApi);
                    System.out.println(lookupdata_hive.get(i_val).toUpperCase().replaceAll("\\s",""));
                    System.out.println(lookUpApi[i_idxApi].toUpperCase().replaceAll("\\s",""));*/
                }


            }

            if(ItemFound==false){
                TestLogger.logFail("database value : " + lookupdata_hive.get(i_val) +  " is not appearing in API.");
                overallpass = false;
            }


        }

        if(overallpass==false){
            Assert.fail();
        }


        //Get Hive Data
//        List<String> lookupdata_hive = getAdvanceSearchValues(sfield);

//        Validate Name of the values between hive and Api


/*        String HiveRecCount = getLoadRecordCountFromHive(sfield);

        if(TotalCount_Api.equals(HiveRecCount)){
            TestLogger.logPass("Dataload count for " + sfield + " :" + nTotalTitle);
        }else{
            TestLogger.logFail("Dataload count did not match. Field Name : " + sfield + " Hive data Count : " + HiveRecCount + " Api Record Count : " + nTotalTitle);
            Assert.fail();
        }

        return nTotalTitle;*/

    }


    public String getDataSourceCode_Api(String Datasource){
        if(Datasource.equals("BNN")){
//            return "49333-668";
            return "668-0-";
        }else if(Datasource.equals("Bookscan")){
//            return "49333-303";
            return "303-0-";
        }else if(Datasource.equals("Pubtrack")){
            return "679-0-";
        }else{
            return "ENTER VALID DATASOURCE...No CODE";
        }

    }


    public double DataLoadinElasticSearch(String sfield,String datasource) throws Exception {

        //Api Detail
        RestAssured.baseURI = "http://wue2bkswbd01.npd.com/";
        String sResource = "/api/lookup/" + sfield;

        //Grab All Responses
                Response Resp = given().
                        when().
                        get(sResource).
                        then().assertThat().statusCode(200).and().
                        contentType(ContentType.JSON).and().log().all().
                        extract().response();

 /*               Response Resp = given().
                        when().
                        get(sResource);*/

        //Convert Raw Response to String
        String responseString = Resp.asString();

        //Convert String Response to Jason
        JsonPath js = new JsonPath(responseString);

        //get all the values
        int nTotalTitle = js.get("results.size()");

/*        for(int i_api=1;i_api<=nTotalTitle;i_api++){
            System.out.println(js.);
        }
        */

        String TotalCount_Api = Integer.toString(nTotalTitle);
        String SybaseRecCount = getLoadRecordCountFromSybase(sfield,datasource);


/*
        if(nTotalTitle>0){
            TestLogger.logPass("Dataload count for " + sfield + " :" + nTotalTitle);
        }else{
            TestLogger.logFail("No Data Available for " + sfield);
            Assert.fail();
        }
*/

        if(TotalCount_Api.equals(SybaseRecCount)){
            TestLogger.logPass("Dataload count for " + sfield + " :" + nTotalTitle);
        }else{
            TestLogger.logFail("Dataload count did not match. Field Name : " + sfield + " Sybase data Count : " + SybaseRecCount + " Api Record Count : " + nTotalTitle);
            Assert.fail();
        }

        return nTotalTitle;



    }


    public static String getLoadSchema(){

        DBUtil dbcon = new DBUtil();
//        String sQuery = "SELECT Load_Environment FROM NPDREPORTMANAGER_PROD.VW_SUMMARY_LOAD_LOG WHERE MODULE='bsc' And DATA_Frequency = 'usw' Order by END_LOAD_TIME Desc";
        String sQuery = "SELECT DATA_VERSION FROM NPDREPORTMANAGER_PROD.VW_SUMMARY_LOAD_LOG WHERE MODULE='bsc' And DATA_Frequency = 'usw' Order by END_LOAD_TIME Desc";

        List<String> sSchema = dbcon.readDataBasePDK(sQuery,"DATA_VERSION");

        String sSchemaName = "prod_bsc_pos_usw_" + sSchema.get(0);

//        return sSchema.get(0);
        return sSchemaName;

    }

    public String getLoadRecordCountFromSybase(String apiOptionName,String datasource) throws Exception {

        DBUtil dbUtil = new DBUtil();

        //get latest sybase load schema
        String SchemaName = getLoadSchema();
        String tableName = getTableNameforApiLoad(apiOptionName,datasource);

//        String sQuery = "SELECT Count(*) FROM " + SchemaName + "." + tableName;
        String sQuery = "SELECT Count(*) FROM " + SchemaName + "." + tableName;
//        ResultSet rs = dbUtil.ConnectSybase(sQuery);

        ResultSet rs = dbUtil.ConnectHive(sQuery);

        if(rs.next()){
            return rs.getString(1);
        }else{
            return "ERROR";
        }


    }



    public String getLoadRecordCountFromHive(String apiOptionName,String datasource) throws Exception {

        DBUtil dbUtil = new DBUtil();

        //get latest sybase load schema
        String SchemaName = getLoadSchema();
        String tableName = getTableNameforApiLoad(apiOptionName,datasource);

//        String sQuery = "SELECT Count(*) FROM " + SchemaName + "." + tableName;
        String sQuery = "SELECT Count(*) FROM " + SchemaName + "." + tableName + " Order by displayorder";
//        ResultSet rs = dbUtil.ConnectSybase(sQuery);

        ResultSet rs = dbUtil.ConnectHive(sQuery);

        if(rs.next()){
            System.out.println(rs.getString(1));
            return rs.getString(1);
        }else{
            return "ERROR";
        }


    }


    public List<String> getAdvanceSearchValues(String apiOptionName,String Datasource) throws Exception {

        DBUtil dbUtil = new DBUtil();

        //get latest Hive load schema
        String SchemaName = getLoadSchema();
        String tableName = getTableNameforApiLoad(apiOptionName,Datasource);
/*
        //Query for Hive OR Sybase
        String sQuery = "SELECT name,displayorder FROM " + SchemaName + "." + tableName + " Order by displayorder";*/

        //Query for Oracle
        //We may need to remove this when data will retrieve from Hive
        if(Datasource.equals("BNN")){
            SchemaName = "bsc_mkt_bnw_d2";
        }else if(Datasource.equals("Bookscan")){
            SchemaName = "bsc_mkt_usw_d2";
        }else if(Datasource.equals("Pubtrack")){
            SchemaName = "bsc_mkt_ptd_d2";
        }else{
            System.out.println("ENTER DATASOURCE");
        }


        String sQuery = "SELECT name,displayorder FROM " + SchemaName + "." + tableName + " Order by displayorder,name";


        System.out.println(sQuery);
//        ResultSet resultSet = dbUtil.ConnectSybase(sQuery);
//        ResultSet resultSet = dbUtil.ConnectHive(sQuery);
        ResultSet resultSet = dbUtil.ConnectOracle(sQuery);


        List<String> dataList = new ArrayList<>();
        while(resultSet.next()){
            String itemName = resultSet.getString("name");
            if(itemName.toUpperCase().equals("OTHER") || itemName.toUpperCase().equals("NOT SPECIFIED")|| itemName.toUpperCase().equals("NON-BOOK")){
                System.out.println("NOT ADDING: " + itemName);

            }else{
                dataList.add(itemName);
                System.out.println(itemName);
            }

        }
        return dataList;


    }


    public static String getTableNameforApiLoad(String loadOption,String datasource){

        String tableName;

        switch (loadOption){

            case "bisaclevel1s":
                tableName = "m_bisaclevels_bisaclevel1";
                break;

            case "bisaclevel2s":
                tableName = "m_bisaclevels_bisaclevel2";
                break;

            case "formats":
                tableName =  "m_format";
                break;

            case "vintages":
                tableName =  "m_vintage";
              break;

            case "aggregates":
                tableName =  "m_categories_bsrankaggregate";
                break;

            case "subjectgroups":
                tableName =  "m_categorie_bsranksubjectgroup";
                break;

            case "categorygroups":
                tableName =  "m_categori_bsrankcategorygroup";
                break;


            case "categories":
                tableName =  "m_categories_bsrankcategory";
                break;

            default:
                tableName =  "api name does not exist : " + loadOption;
                break;
        }




        if(datasource.equals("Bookscan")){
            switch (loadOption){

                case "bisaclevel1s":
                    tableName = "m_bisaclevels_bisaclevel1";
                    break;

                case "bisaclevel2s":
                    tableName = "m_bisaclevels_bisaclevel2";
                    break;

                case "formats":
                    tableName =  "m_format";
                    break;

                case "vintages":
                    tableName =  "m_vintage";
                    break;

                case "aggregates":
                    tableName =  "m_categories_bsrankaggregate";
                    break;

                case "subjectgroups":
                    tableName =  "m_categorie_bsranksubjectgroup";
                    break;

                case "categorygroups":
                    tableName =  "m_categori_bsrankcategorygroup";
                    break;


                case "categories":
                    tableName =  "m_categories_bsrankcategory";
                    break;

                default:
                    tableName =  "api name does not exist : " + loadOption;
                    break;
            }

        }else if(datasource.equals("BNN")){
            switch (loadOption){

                case "bisaclevel1s":
                    tableName = "m_bisaclevels_bisaclevel1";
                    break;

                case "bisaclevel2s":
                    tableName = "m_bisaclevels_bisaclevel2";
                    break;

                case "formats":
                    tableName =  "m_format";
                    break;

                case "vintages":
                    tableName =  "m_vintage";
                    break;

                case "aggregates":
                    tableName =  "M_BNNRANKS_BNNRANKAGGREGATE";
                    break;

                case "subjectgroups":
                    tableName =  "M_BNNRANKS_BNNRANKSUBJECTGROUP";
                    break;

                case "categorygroups":
                    tableName =  "M_CATEGORI_BSRANKCATEGORYGROUP";
                    break;


                case "categories":
                    tableName =  "M_BNNRANKS_BNNRANKCATEGORY";
                    break;

                default:
                    tableName =  "api name does not exist : " + loadOption;
                    break;
            }
        }else if(datasource.equals("Pubtrack")){

            switch (loadOption){

                case "bisaclevel1s":
                    tableName = "m_bisaclevels_bisaclevel1";
                    break;

                case "bisaclevel2s":
                    tableName = "m_bisaclevels_bisaclevel2";
                    break;

                case "formats":
                    tableName =  "m_format";
                    break;

                case "vintages":
                    tableName =  "m_vintage";
                    break;

                case "aggregates":
                    tableName =  "m_categories_bsrankaggregate";
                    break;

                case "subjectgroups":
                    tableName =  "m_categorie_bsranksubjectgroup";
                    break;

                case "categorygroups":
                    tableName =  "m_categori_bsrankcategorygroup";
                    break;


                case "categories":
                    tableName =  "m_categories_bsrankcategory";
                    break;

                default:
                    tableName =  "api name does not exist : " + loadOption;
                    break;
            }


        }



        return tableName;

    }

   /* public String getElasticSearchHostName(){

        String hostName = null;

        if(env_DQ.equals("PROD")){
            hostName = "lb-prod-bkses";
        }else if(env_DQ.equals("WORK")){

        }

    }*/


    //Collect To Elastic Search Api
    public void DQCheck_LatestTimePeriod(String DataSource) throws Exception {

        System.out.println(env_DQ);

//        String ES_HostName = getElasticSearchHostName();

        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("bks_svc", "e4??keTfp-dpdLTD"));

        RestClientBuilder builder = RestClient.builder(
                new HttpHost("lb-prod-bkses", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(
                            HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        TestLogger.log("Successfully Connected to Elastic Search Api");

        RestHighLevelClient client = new RestHighLevelClient(builder);

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

//        SearchRequest searchRequest = new SearchRequest("dev_bsc_mkt_usw_books");
//        SearchRequest searchRequest = new SearchRequest("prod_bookscan_metadata");
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

//        //Search Hits
//        SearchHits searchhits = searchResponse.getHits();



//        for(SearchHit hit:searchhits){
//
//            System.out.println(hit.);
//        }

//        System.out.println(searchResponse.getHits());

        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

/*        //Get Value
        String FieldValue_Units = getValue_ESResponse(sResponse,"units");
        String FieldValue_units_ytd = getValue_ESResponse(sResponse,"units_ytd");
        String FieldValue_units_rtd = getValue_ESResponse(sResponse,"units_rtd");

        System.out.println("Units : " + FieldValue_Units);
        System.out.println("Units YTD : " + FieldValue_units_ytd);
        System.out.println("Units RTD : " +FieldValue_units_rtd);*/

        //Get Latest Period:
//        String latest_period = getValue_ESResponse(sResponse,"latest_period");
        String latest_period = getValue_ESResponse(sResponse,"period");
//        latest_period = "2020-05-23";
        System.out.println("Latest Period : " + latest_period);
        TestLogger.log("Latest Period Elastic Search: " + latest_period);

        //Get Summary Schema Name From Oracle
        DBUtil dbUtil = new DBUtil();
//        ResultSet rsOracle = dbUtil.ConnectOracle("Select Summary_Schema_Name from NPDREPORTMANAGER_PROD.TB_MODULE_SUMMARY_SCHEMA Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);
        ResultSet rsOracle = dbUtil.ConnectOracle("Select Summary_Schema_Name from NPDREPORTMANAGER_" + env_DQ + ".TB_MODULE_SUMMARY_SCHEMA Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);
        String sum_SchemaName = null;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        ResultSet rs_sybase;

        //Connect With Sybase to get latest Period
//        ResultSet rs_sybase = dbUtil.ConnectSybase("Select description from bsc_mkt_usw_d2.m_time_periods_week Order by ppweek Desc");
        if(DataSource.equals("PubTrack")){
            rs_sybase = dbUtil.ConnectSybase("Select yearmonth_desc from "+ sum_SchemaName +".m_time_periods mtp Order by ppmonth Desc");
        }else{
            rs_sybase = dbUtil.ConnectSybase("Select description from "+ sum_SchemaName +".m_time_periods_week Order by ppweek Desc");
        }



        String LatestDate_DB = null;

        if(rs_sybase.next()){
            String latestPeriod_DB = rs_sybase.getString(1);
            System.out.println(latestPeriod_DB);

            if(DataSource.equals("PubTrack")){

                String[] SplitEndDate = latestPeriod_DB.split(" ");
                String txt_mon = SplitEndDate[0];
                String sYear = SplitEndDate[1];
                TestLogger.log("Month From Sybase : " + txt_mon);
                TestLogger.log("Year From Sybase : " + sYear);

                //Convert Mon Value to Integer
                DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM")
                        .withLocale(Locale.ENGLISH);
                TemporalAccessor accessor = parser.parse(txt_mon);
                int month = accessor.get(ChronoField.MONTH_OF_YEAR);

                LatestDate_DB = sYear + "-" + (month<10?("0"+month):(month)) + "-" + ("01");

            }else{

    /*            String[] splitDate = latestPeriod_DB.split(" - ");
                String EndDate_Entire = splitDate[1];
                String[] SplitEndDate = EndDate_Entire.split(" ");

                String txt_mon = SplitEndDate[0];
                int sDate = Integer.parseInt(SplitEndDate[1]);
                String sYear = SplitEndDate[2];

                TestLogger.log("Month From Sybase : " + txt_mon);
                TestLogger.log("Date From Sybase : " + sDate);
                TestLogger.log("Year From Sybase : " + sYear);

                DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM")
                        .withLocale(Locale.ENGLISH);
                TemporalAccessor accessor = parser.parse(txt_mon);
                int month = accessor.get(ChronoField.MONTH_OF_YEAR);

                LatestDate_DB = sYear + "-" + (month<10?("0"+month):(month)) + "-" + (sDate<10?("0"+sDate):(sDate));

                System.out.println(LatestDate_DB);
                TestLogger.log("Latest Period from Database : " + LatestDate_DB);*/

                LatestDate_DB = getEndTimePeriodDB(latestPeriod_DB,DataSource);

            }






            /*final String OldFormat = "mmm dd yyyy";
            final String NewFormat = "yyyy-mm-dd";

            SimpleDateFormat sdf = new SimpleDateFormat(OldFormat);
            Date d = sdf.parse(EndDate_Entire);
            sdf.applyPattern(NewFormat);
            String NewDateFormat = sdf.format(d);
            System.out.println(NewDateFormat);*/


        }else{
            System.out.println("No Data");
            TestLogger.logFail("Unable To retrieve Latest Time Period From Database.");
            Assert.fail();
        }


        //Validate
        if(LatestDate_DB.equals(latest_period)){
            System.out.println("Latest Time Period Matched between Application and Database : " +latest_period);
            TestLogger.logPass("Latest Time Period Matched between Application and Database : " +latest_period);
        }else{
            System.out.println("Latest Time Period did not Match between Application and Database. Application : " +latest_period + " Database : " + LatestDate_DB);
            TestLogger.logFail("Latest Time Period did not Match between Application and Database. Application : " +latest_period + " Database : " + LatestDate_DB);
            Assert.fail();
        }



/*        final JSONObject obj = new JSONObject(Boolean.parseBoolean(searchResponse.toString()));
        final JSONArray hit = obj.getJSONArray("hits");
        final int n = hit.size();

        System.out.println(sResponse);*/


   /*     //Convert String Response to Jason
        JsonPath js1 = new JsonPath(sResponse);

        String pJson = js1.getJsonObject("hits").toString();

//        JSONObject obj = new JSONObject(sResponse);
//        String info = obj.getJSONObject("hits").getString("isbn13");


//        System.out.println("JASONOUT : " + js1.get("hits[1].isbn13").toString());


//        List<SearchHit> searchHits = Arrays.asList(searchResponse.getHits().getHits());
//        List<SearchHit> searchHits = Arrays.asList(searchResponse.getHits().getHits());
//        List<book> results = new ArrayList<book>();



//        searchHits.forEach(
//
//
//                hit -> results.add(JSON.parseObject(hit.getSourceAsString(), book.class)));
//            hit -> results.add(JSON.parseObject(hit.getSourceAsString(), )));
//
//
//
//
//*/
   }


   //This Method will check Sales And Rank History Dataload
    public void DQCheck_SalesAndRankHistory(String DataSource) throws Exception {

        boolean IsTestFail = false;

//        //Get Bulk Sales ISBN
//        String BulkISBN_PPWeek = getBulkSalesISBN_DB(DataSource);
//        String[] SplitBulkISBN_PPWeek = BulkISBN_PPWeek.split("[|]");
//        String BulkISBN = SplitBulkISBN_PPWeek[0];




        //Identify Test ISBN from Bestsellers Index
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
//        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");


        //Get ISBN from Bestseller list (Get Test ISBN from Best Sellers List)
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");;
        TestLogger.log("Test ISBN : " + ISBN);

        //Get SalesIndex
        String SalesIndex = getSalesIndex(DataSource);

/*        SearchRequest searchRequest_SalesRank = new SearchRequest(SalesIndex);
        SearchSourceBuilder searchSourceBuilder_SalesRank = new SearchSourceBuilder();*/


        //Get Total Hit Count
        BoolQueryBuilder bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.termQuery("isbn13",ISBN));
//                .filter(QueryBuilders.termQuery("period",latestPeriod));

        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.trackTotalHits(true);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(SalesIndex);
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);
        ;
        sResponse = srchResponse.toString();

        System.out.println(sResponse);

        String TotalRecordCount_ES = getValue_ESResponse(sResponse,"value");
        String units_ES = getValue_ESResponse(sResponse,"units");
        String unitsRTD_ES = getValue_ESResponse(sResponse,"units_rtd");
        String unitsYTD_ES = getValue_ESResponse(sResponse,"units_ytd");
        String LatestTimePeriod_ES = getValue_ESResponse(sResponse,"period");

        TestLogger.log("App Record Count : " + TotalRecordCount_ES);
        TestLogger.log("App Latest Period : " + LatestTimePeriod_ES);
        TestLogger.log("App Units : " + units_ES);
        TestLogger.log("App RTD : " + unitsRTD_ES);
        TestLogger.log("App YTD : " + unitsYTD_ES);

        //****************************************************************************************************************************************************
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
//            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Now get Values from Sybase Database
        //DB Total Record Count
        String cnt_Query = "SELECT Count(*) from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "'";
        ResultSet rs_RecCount = dbUtil.ConnectSybase(cnt_Query);
        String Rec_Cnt_DB=null;

        if(rs_RecCount.next()){
            Rec_Cnt_DB = rs_RecCount.getString(1);
            TestLogger.log("Total Record Count for ISBN : " + ISBN + " is : " + Rec_Cnt_DB);
        }


        String sQuery = null;
        if(DataSource.equals("PubTrack")){
            sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppmonth from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "' Order by ppmonth Desc";
        }else{
            sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppweek from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "' Order by ppweek Desc";
        }

        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String unit_RTD_DB=null;
        String unit_YTD_DB=null;
        String ppweek = null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            unit_RTD_DB = rs_sybase.getString(2);
            unit_YTD_DB = rs_sybase.getString(3);
//            ppweek = Integer.parseInt(rsOracle.getString(4));
            ppweek = rs_sybase.getString(4);

            System.out.println(unit_DB);
            System.out.println(unit_RTD_DB);
            System.out.println(unit_YTD_DB);
            System.out.println(ppweek);

            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("RTD Database : " + unit_RTD_DB);
            TestLogger.logPass("YTD Database : " + unit_YTD_DB);
            TestLogger.logPass("PPWeek Database : " + ppweek);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

//        int ppWeek = 0;
        String sQuery_ppweekName = null;
        if(DataSource.equals("PubTrack")){
            sQuery_ppweekName = "Select yearmonth_desc from " +  sum_SchemaName + ".m_time_periods mtp Where ppmonth= " + ppweek;
        }else{
            sQuery_ppweekName = "Select description from " +  sum_SchemaName + ".m_time_periods_week mtp Where ppweek= " + ppweek;

        }

        ResultSet rs_ppWeek = dbUtil.ConnectSybase(sQuery_ppweekName);
        String nameWeekRange = null;

        if(rs_ppWeek.next()){
            nameWeekRange = rs_ppWeek.getString(1);
        }

        String EndTimePeriodDB  = getEndTimePeriodDB(nameWeekRange,DataSource);

        //****************************************************************************************************************************************************

//        Validation
        //Total Record Count for ISBN
        if(Rec_Cnt_DB.equals(TotalRecordCount_ES)){
            TestLogger.logPass("Total Sales Rank History Count for ISBN : " + ISBN + " matched with database. Record Count : " + Rec_Cnt_DB);
        }else{
            TestLogger.logFail("Total Sales Rank History Count for ISBN : " + ISBN + " did not match with database. App Record Count : " + TotalRecordCount_ES + " Database Record Count : " + Rec_Cnt_DB);
        }

        //Latest Time Period Check
        if(EndTimePeriodDB.equals(LatestTimePeriod_ES)){
            TestLogger.logPass("Latest Time Period in Sales And Rank History Matched for ISBN : " + ISBN + " Latest Period = " + EndTimePeriodDB);
        }else{
            TestLogger.logFail("Latest Time Period did not match in Sales And Rank History between Application And Database for ISBN : " + ISBN + " Application :" + LatestTimePeriod_ES + " Database: " + EndTimePeriodDB);
        }

        //Check Sales Data
        //----------------------------------------------------------------------------------------------
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value Matched in Sales And Rank History for ISBN " + ISBN + " Unit:"+ unit_DB);
        }else{
            TestLogger.logFail("Unit Value did not match in Sales And Rank History for ISBN " + ISBN + " Unit DB:"+ unit_DB + " Unit App:" + units_ES);
        }

        if(unit_RTD_DB.equals(unitsRTD_ES)){
            TestLogger.logPass("RTD Unit Value Matched in Sales And Rank History for ISBN " + ISBN + " Unit:"+ unit_RTD_DB);
        }else{
            TestLogger.logFail("RTD Unit Value did not match in Sales And Rank History for ISBN " + ISBN + " Unit DB:"+ unit_RTD_DB + " Unit App:" + unitsRTD_ES);
        }

        if(unit_YTD_DB.equals(unitsYTD_ES)){
            TestLogger.logPass("YTD Unit Value Matched in Sales And Rank History for ISBN " + ISBN + " Unit:"+ unit_YTD_DB);
        }else{
            TestLogger.logFail("YTD Unit Value did not match in Sales And Rank History for ISBN " + ISBN + " Unit DB:"+ unit_YTD_DB + " Unit App:" + unitsYTD_ES);
        }

        //----------------------------------------------------------------------------------------------


        if(IsTestFail==true){
            Assert.fail();
        }

    }



    //This Method will check Sales And Rank History Dataload
    public void DQCheck_SalesAndRankHistory_Bulk(String DataSource) throws Exception {

        boolean IsTestFail = false;

        //Get Bulk Sales ISBN
        String BulkISBN_PPWeek = getBulkSalesISBN_DB(DataSource);
        String[] SplitBulkISBN_PPWeek = BulkISBN_PPWeek.split("[|]");
        String BulkISBN = SplitBulkISBN_PPWeek[0];


        //Identify Test ISBN from Bestsellers Index
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
//        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");


        //Get ISBN from Bestseller list (Get Test ISBN from Best Sellers List)
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchSourceBuilder_bsl.trackTotalHits(true);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = BulkISBN;
        TestLogger.log("Test ISBN : " + ISBN);

        //Get SalesIndex
        String SalesIndex = getSalesIndex(DataSource);

/*        SearchRequest searchRequest_SalesRank = new SearchRequest(SalesIndex);
        SearchSourceBuilder searchSourceBuilder_SalesRank = new SearchSourceBuilder();*/


        //Get Total Hit Count
        BoolQueryBuilder bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.termQuery("isbn13",ISBN));
//                .filter(QueryBuilders.termQuery("period",latestPeriod));

        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
//        searchSrc_SalesRankHis.trackTotalHits(true);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(SalesIndex);
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);
        ;
        sResponse = srchResponse.toString();

        System.out.println(sResponse);

        String TotalRecordCount_ES = getValue_ESResponse(sResponse,"value");
        String units_ES = getValue_ESResponse(sResponse,"units");
        String unitsRTD_ES = getValue_ESResponse(sResponse,"units_rtd");
        String unitsYTD_ES = getValue_ESResponse(sResponse,"units_ytd");
        String LatestTimePeriod_ES = getValue_ESResponse(sResponse,"period");

        TestLogger.log("App Record Count : " + TotalRecordCount_ES);
        TestLogger.log("App Latest Period : " + LatestTimePeriod_ES);
        TestLogger.log("App Units : " + units_ES);
        TestLogger.log("App RTD : " + unitsRTD_ES);
        TestLogger.log("App YTD : " + unitsYTD_ES);

        //****************************************************************************************************************************************************
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
//            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Now get Values from Sybase Database
        //DB Total Record Count
        String cnt_Query = "SELECT Count(*) from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "'  And proj_unitssold<>0";
        ResultSet rs_RecCount = dbUtil.ConnectSybase(cnt_Query);
        String Rec_Cnt_DB=null;

        if(rs_RecCount.next()){
            Rec_Cnt_DB = rs_RecCount.getString(1);
            TestLogger.log("Total Record Count for ISBN : " + ISBN + " is : " + Rec_Cnt_DB);
        }


        String sQuery = null;
        if(DataSource.equals("PubTrack")){
            sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppmonth from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "' Order by ppmonth Desc";
        }else{
            sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppweek from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "' Order by ppweek Desc";
        }

        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String unit_RTD_DB=null;
        String unit_YTD_DB=null;
        String ppweek = null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            unit_RTD_DB = rs_sybase.getString(2);
            unit_YTD_DB = rs_sybase.getString(3);
//            ppweek = Integer.parseInt(rsOracle.getString(4));
            ppweek = rs_sybase.getString(4);

            System.out.println(unit_DB);
            System.out.println(unit_RTD_DB);
            System.out.println(unit_YTD_DB);
            System.out.println(ppweek);

            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("RTD Database : " + unit_RTD_DB);
            TestLogger.logPass("YTD Database : " + unit_YTD_DB);
            TestLogger.logPass("PPWeek Database : " + ppweek);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

//        int ppWeek = 0;
        String sQuery_ppweekName = null;
        if(DataSource.equals("PubTrack")){
            sQuery_ppweekName = "Select yearmonth_desc from " +  sum_SchemaName + ".m_time_periods mtp Where ppmonth= " + ppweek;
        }else{
            sQuery_ppweekName = "Select description from " +  sum_SchemaName + ".m_time_periods_week mtp Where ppweek= " + ppweek;

        }

        ResultSet rs_ppWeek = dbUtil.ConnectSybase(sQuery_ppweekName);
        String nameWeekRange = null;

        if(rs_ppWeek.next()){
            nameWeekRange = rs_ppWeek.getString(1);
        }

        String EndTimePeriodDB  = getEndTimePeriodDB(nameWeekRange,DataSource);

        //****************************************************************************************************************************************************

//        Validation
        //Total Record Count for ISBN
        if(Rec_Cnt_DB.equals(TotalRecordCount_ES)){
            TestLogger.logPass("Total Sales Rank History Count for ISBN : " + ISBN + " matched with database. Record Count : " + Rec_Cnt_DB);
        }else{
            TestLogger.logFail("Total Sales Rank History Count for ISBN : " + ISBN + " did not match with database. App Record Count : " + TotalRecordCount_ES + " Database Record Count : " + Rec_Cnt_DB);
            IsTestFail = true;
        }

        //Latest Time Period Check
        if(EndTimePeriodDB.equals(LatestTimePeriod_ES)){
            TestLogger.logPass("Latest Time Period in Sales And Rank History Matched for ISBN : " + ISBN + " Latest Period = " + EndTimePeriodDB);
        }else{
            TestLogger.logFail("Latest Time Period did not match in Sales And Rank History between Application And Database for ISBN : " + ISBN + " Application :" + LatestTimePeriod_ES + " Database: " + EndTimePeriodDB);
            IsTestFail = true;
        }

        //Check Sales Data
        //----------------------------------------------------------------------------------------------
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value Matched in Sales And Rank History for ISBN " + ISBN + " Unit:"+ unit_DB);
        }else{
            TestLogger.logFail("Unit Value did not match in Sales And Rank History for ISBN " + ISBN + " Unit DB:"+ unit_DB + " Unit App:" + units_ES);
            IsTestFail = true;
        }

        if(unit_RTD_DB.equals(unitsRTD_ES)){
            TestLogger.logPass("RTD Unit Value Matched in Sales And Rank History for ISBN " + ISBN + " Unit:"+ unit_RTD_DB);
        }else{
            TestLogger.logFail("RTD Unit Value did not match in Sales And Rank History for ISBN " + ISBN + " Unit DB:"+ unit_RTD_DB + " Unit App:" + unitsRTD_ES);
            IsTestFail = true;
        }

        if(unit_YTD_DB.equals(unitsYTD_ES)){
            TestLogger.logPass("YTD Unit Value Matched in Sales And Rank History for ISBN " + ISBN + " Unit:"+ unit_YTD_DB);
        }else{
            TestLogger.logFail("YTD Unit Value did not match in Sales And Rank History for ISBN " + ISBN + " Unit DB:"+ unit_YTD_DB + " Unit App:" + unitsYTD_ES);
            IsTestFail = true;
        }

        //----------------------------------------------------------------------------------------------

        if(IsTestFail==true){
            Assert.fail();
        }

    }





    //This Method will check Sales And Rank History Dataload
    public void DQCheck_SalesAndRankHistory_ALR(String DataSource) throws Exception {

        boolean IsTestFail = false;


        //Identify Test ISBN from Bestsellers Index
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
//        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");


        //Get ISBN from Bestseller list (Get Test ISBN from Best Sellers List)
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        TestLogger.log("Test ISBN : " + ISBN);

        //Get SalesIndex
        String SalesIndex = getSalesIndex(DataSource);

/*        SearchRequest searchRequest_SalesRank = new SearchRequest(SalesIndex);
        SearchSourceBuilder searchSourceBuilder_SalesRank = new SearchSourceBuilder();*/


        //Get Total Hit Count
        BoolQueryBuilder bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.termQuery("isbn13",ISBN));
//                .filter(QueryBuilders.termQuery("period",latestPeriod));

        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.trackTotalHits(true);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(SalesIndex);
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);
        ;
        sResponse = srchResponse.toString();

        System.out.println(sResponse);

        String testOutlet = "1315843760";

        String TotalRecordCount_ES = getValue_ESResponse(sResponse,"value");
        String units_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units");
        String unitsRTD_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units_rtd");
        String unitsYTD_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units_ytd");
        String LatestTimePeriod_ES = getValue_ESResponse(sResponse,"period");

        TestLogger.log("App Record Count : " + TotalRecordCount_ES);
        TestLogger.log("App Latest Period : " + LatestTimePeriod_ES);
        TestLogger.log("App Units : " + units_ES);
        TestLogger.log("App RTD : " + unitsRTD_ES);
        TestLogger.log("App YTD : " + unitsYTD_ES);

        //****************************************************************************************************************************************************
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
//            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Now get Values from Sybase Database
        //DB Total Record Count
        String cnt_Query = "SELECT Count(*) from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "'";
        ResultSet rs_RecCount = dbUtil.ConnectSybase(cnt_Query);
        String Rec_Cnt_DB=null;

        if(rs_RecCount.next()){
            Rec_Cnt_DB = rs_RecCount.getString(1);
            TestLogger.log("Total Record Count for ISBN : " + ISBN + " is : " + Rec_Cnt_DB);
        }

        String sppWeek = getPPWeekFromDB(LatestTimePeriod_ES,sum_SchemaName);

        String sQuery = null;
        if(DataSource.equals("PubTrack")){
            sQuery = "SELECT round(sum(proj_unitssold),0),round(sum(proj_unitssold_rtd),0),round(sum(proj_unitssold_ytd),0) from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "' Order by ppmonth Desc";
        }else{
            sQuery = "SELECT round(sum(proj_unitssold),0),round(Sum(proj_unitssold_rtd),0),round(Sum(proj_unitssold_ytd),0) from " +  sum_SchemaName + ".w_aggr_ytd_outlet where upc_code= '" + ISBN + "' And outletfamily = " + testOutlet + "And ppweek = " + sppWeek;
        }

        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String unit_RTD_DB=null;
        String unit_YTD_DB=null;
        String ppweek = null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            unit_RTD_DB = rs_sybase.getString(2);
            unit_YTD_DB = rs_sybase.getString(3);
//            ppweek = Integer.parseInt(rsOracle.getString(4));
//            ppweek = rs_sybase.getString(4);

            System.out.println(unit_DB);
            System.out.println(unit_RTD_DB);
            System.out.println(unit_YTD_DB);
            System.out.println(ppweek);

            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("RTD Database : " + unit_RTD_DB);
            TestLogger.logPass("YTD Database : " + unit_YTD_DB);
//            TestLogger.logPass("PPWeek Database : " + ppweek);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

//        int ppWeek = 0;
/*        String sQuery_ppweekName = null;
        if(DataSource.equals("PubTrack")){
            sQuery_ppweekName = "Select yearmonth_desc from " +  sum_SchemaName + ".m_time_periods mtp Where ppmonth= " + ppweek;
        }else{
            sQuery_ppweekName = "Select description from " +  sum_SchemaName + ".m_time_periods_week mtp Where ppweek= " + ppweek;

        }*/

//        ResultSet rs_ppWeek = dbUtil.ConnectSybase(sQuery_ppweekName);
//        String nameWeekRange = null;
//
//        if(rs_ppWeek.next()){
//            nameWeekRange = rs_ppWeek.getString(1);
//        }

//        String EndTimePeriodDB  = getEndTimePeriodDB(nameWeekRange,DataSource);

        //****************************************************************************************************************************************************

//        Validation
        //Total Record Count for ISBN
        if(Rec_Cnt_DB.equals(TotalRecordCount_ES)){
            TestLogger.logPass("Total Sales Rank History Count for ISBN : " + ISBN + " matched with database. Record Count : " + Rec_Cnt_DB);
        }else{
            TestLogger.logFail("Total Sales Rank History Count for ISBN : " + ISBN + " did not match with database. App Record Count : " + TotalRecordCount_ES + " Database Record Count : " + Rec_Cnt_DB);
        }

/*
        //Latest Time Period Check
        if(EndTimePeriodDB.equals(LatestTimePeriod_ES)){
            TestLogger.logPass("Latest Time Period in Sales And Rank History Matched for ISBN : " + ISBN + " Latest Period = " + EndTimePeriodDB);
        }else{
            TestLogger.logFail("Latest Time Period did not match in Sales And Rank History between Application And Database for ISBN : " + ISBN + " Application :" + LatestTimePeriod_ES + " Database: " + EndTimePeriodDB);
        }
*/

        //Check Sales Data
        //----------------------------------------------------------------------------------------------
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value Matched in Sales And Rank History for ISBN " + ISBN + " Unit:"+ unit_DB);
        }else{
            TestLogger.logFail("Unit Value did not match in Sales And Rank History for ISBN " + ISBN + " Unit DB:"+ unit_DB + " Unit App:" + units_ES);
        }

        if(unit_RTD_DB.equals(unitsRTD_ES)){
            TestLogger.logPass("RTD Unit Value Matched in Sales And Rank History for ISBN " + ISBN + " Unit:"+ unit_RTD_DB);
        }else{
            TestLogger.logFail("RTD Unit Value did not match in Sales And Rank History for ISBN " + ISBN + " Unit DB:"+ unit_RTD_DB + " Unit App:" + unitsRTD_ES);
        }

        if(unit_YTD_DB.equals(unitsYTD_ES)){
            TestLogger.logPass("YTD Unit Value Matched in Sales And Rank History for ISBN " + ISBN + " Unit:"+ unit_YTD_DB);
        }else{
            TestLogger.logFail("YTD Unit Value did not match in Sales And Rank History for ISBN " + ISBN + " Unit DB:"+ unit_YTD_DB + " Unit App:" + unitsYTD_ES);
        }

        //----------------------------------------------------------------------------------------------

    }




    public void CheckAllBooksCount() throws Exception {

        //Get Client
        RestHighLevelClient client = connectESClient();

        //Get Total Hit Count
        BoolQueryBuilder bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.matchAllQuery());
//                .filter(QueryBuilders.termQuery("period",latestPeriod));

        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
//        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.trackTotalHits(true);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest("prod_bsc_mkt_usw_books");
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);

        String sResponse = srchResponse.toString();

//        System.out.println(sResponse);

        String TotalRecordCount = getValue_ESResponse(sResponse,"value");

        System.out.println("Total Record Count : " + TotalRecordCount);

    }


   public String getSummarySourceTypeID(String Datasource){

        String SummarySourceTypeID = null;

        if(Datasource.equals("US BookScan")){
            SummarySourceTypeID = "303";
        }else if(Datasource.equals("B&N")){
            SummarySourceTypeID = "668";
        }else if(Datasource.equals("PubTrack")){
            SummarySourceTypeID = "679";
        }

        TestLogger.log("Summary Source Type ID : " + SummarySourceTypeID);
        return SummarySourceTypeID;

   }



    public String getValue_ESResponse(String sResponse1,String FieldName){

        String[] splitByComma = sResponse1.split("[,]");
        String FieldValue = null;

        //Search Field Value
        for(int i=0;i<=splitByComma.length;i++){

            if(splitByComma[i].contains(FieldName)){
                FieldValue = splitByComma[i];

                break;
            }

        }

        String[] splitValue = FieldValue.split("[:]");
        String ESValue = splitValue[splitValue.length-1].replace("\"", "").replace("}","").replace("]","");
        System.out.println("Value is : " + ESValue);
        TestLogger.log("Value Of " + FieldName + " is : " + ESValue);

        return ESValue;

    }



    //This method will check Sales Data of Basic Search and Advance Search
    //Data will be validated between Elastic Search and Sybase
    public void DQCheck_SalesData(String DataSource) throws Exception {

        boolean IsTestFail = false;


        //Identify Test ISBN from Bestsellers Index
//        SearchRequest searchRequest = new SearchRequest("dev_bsc_mkt_usw_books");
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
        String BookIndex = getBookIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(BookIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String units_rtd_ES = getValue_ESResponse(sResponse,"units_rtd");

        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_"+ env_DQ +".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Now get Values from Sybase Database
        String sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppweek from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "' And ppweek = " + ppWeek;
        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String unit_RTD=null;
        String unit_YTD=null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            unit_RTD = rs_sybase.getString(2);
            unit_YTD = rs_sybase.getString(3);

            System.out.println(unit_DB);
            System.out.println(unit_RTD);
            System.out.println(unit_YTD);
            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("RTD Database : " + unit_RTD);
            TestLogger.logPass("YTD Database : " + unit_YTD);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Unit RTD
        if(unit_RTD.equals(units_rtd_ES)){
            TestLogger.logPass("Unit RTD Value " + unit_RTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit RTD Value did not match for ISBN : " + ISBN + " Database : " + unit_RTD + " Application : " + units_rtd_ES);
            IsTestFail = true;
        }

        //Unit YTD
        if(unit_YTD.equals(units_ytd_ES)){
            TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
            IsTestFail = true;
        }



        if(IsTestFail==true){
            Assert.fail();
        }



    }



    public void DQCheck_SalesData_Bulk(String DataSource) throws Exception {

        boolean IsTestFail = false;

        //Get Bulk Sales ISBN
        String BulkISBN_PPWeek = getBulkSalesISBN_DB(DataSource);
        String[] splitBulkISBN_PPWeek = BulkISBN_PPWeek.split("[|]");
        String BulkISBN = splitBulkISBN_PPWeek[0];


        //Identify Test ISBN from Bestsellers Index
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
//        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        String ISBN = BulkISBN;
        TestLogger.log("Test ISBN : " + ISBN);


        //Get Sales Data from Basic Search or Advance Search Index
        String BookIndex = getBookIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(BookIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String units_rtd_ES = getValue_ESResponse(sResponse,"units_rtd");

        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Now get Values from Sybase Database
        String sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppweek from " +  sum_SchemaName + ".w_aggr_ytd where upc_code= '" + ISBN + "' And ppweek = " + ppWeek;
        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String unit_RTD=null;
        String unit_YTD=null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            unit_RTD = rs_sybase.getString(2);
            unit_YTD = rs_sybase.getString(3);

            System.out.println(unit_DB);
            System.out.println(unit_RTD);
            System.out.println(unit_YTD);
            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("RTD Database : " + unit_RTD);
            TestLogger.logPass("YTD Database : " + unit_YTD);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Unit RTD
        if(unit_RTD.equals(units_rtd_ES)){
            TestLogger.logPass("Unit RTD Value " + unit_RTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit RTD Value did not match for ISBN : " + ISBN + " Database : " + unit_RTD + " Application : " + units_rtd_ES);
            IsTestFail = true;
        }

        //Unit YTD
        if(unit_YTD.equals(units_ytd_ES)){
            TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
            IsTestFail = true;
        }



        if(IsTestFail==true){
            Assert.fail();
        }



    }



    //DQCheck Sales Data ALR
    public void DQCheck_SalesData_ALR(String DataSource) throws Exception {

        boolean IsTestFail = false;


        //Identify Test ISBN from Bestsellers Index
//        SearchRequest searchRequest = new SearchRequest("dev_bsc_mkt_usw_books");
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
        String BookIndex = getBookIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(BookIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        String testOutlet = "1315843760";


        String units_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units_ytd");
        String units_rtd_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units_rtd");
//        String period_ES = getValue_ESResponse(sResponse,"\"period\"");

        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_"+ env_DQ +".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }


//        String period_DB = getPPWeekFromDB(period_ES,sum_SchemaName);

   /*     //Varify if unitsold is appearing
        String sQueryUnitSale = "SELECT COALESCE(SUM(unitssold),0) from " +  sum_SchemaName + ".w_aggr_ytd_outlet where upc_code= '" + ISBN + "' And outletfamily = " + testOutlet + " And ppweek = " + period_DB;
        ResultSet rs_unitsold = dbUtil.ConnectSybase(sQueryUnitSale);
        String unitSoldinDB = "";
        if(rs_unitsold.next()){
            unitSoldinDB = rs_unitsold.getString(1);
        }*/

//        if(!unitSoldinDB.equals("0.0")){

            //Now get Values from Sybase Database
            String sQuery = "SELECT round(Sum(unitssold),0),round(Sum(unitssold_rtd),0),round(Sum(unitssold_ytd),0) from " +  sum_SchemaName + ".w_aggr_ytd_outlet where upc_code= '" + ISBN + "' And outletfamily = " + testOutlet + " And ppweek = " + ppWeek;
            System.out.println(sQuery);
            ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
            String unit_DB=null;
            String unit_RTD=null;
            String unit_YTD=null;
            if(rs_sybase.next()){
                unit_DB = rs_sybase.getString(1);
                unit_RTD = rs_sybase.getString(2);
                unit_YTD = rs_sybase.getString(3);

                System.out.println(unit_DB);
                System.out.println(unit_RTD);
                System.out.println(unit_YTD);
                TestLogger.logPass("Unit Database : " + unit_DB);
                TestLogger.logPass("RTD Database : " + unit_RTD);
                TestLogger.logPass("YTD Database : " + unit_YTD);
            }else{
                TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
                Assert.fail();
            }

            //Validation
            //Unit Sales
            if(unit_DB.equals(units_ES)){
                TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
                IsTestFail = true;
            }

            //Unit RTD
            if(unit_RTD.equals(units_rtd_ES)){
                TestLogger.logPass("Unit RTD Value " + unit_RTD + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit RTD Value did not match for ISBN : " + ISBN + " Database : " + unit_RTD + " Application : " + units_rtd_ES);
                IsTestFail = true;
            }

            //Unit YTD
            if(unit_YTD.equals(units_ytd_ES)){
                TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
                IsTestFail = true;
            }

       /* }else{

            if(unitSoldinDB.equals(units_ES)){
                TestLogger.logPass("Unit Value " + unitSoldinDB + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unitSoldinDB + " Application : " + units_ES);
                IsTestFail = true;
            }

        }
*/




        if(IsTestFail==true){
            Assert.fail();
        }



    }



    public String getBulkSalesISBN_DB(String DataSource) throws Exception {

        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Now get Values from Sybase Database
//        String sQuery = "SELECT upc_code  FROM " + sum_SchemaName + ".fact_posdata WHERE ppweek=" + ppWeek + " AND bulksaleflag <>0";
        String sQuery = "SELECT TOP 1 fp.upc_code  FROM " + sum_SchemaName + ".fact_posdata fp," + sum_SchemaName + ".rank_bulk_flag_tbl rbft \n" +
                "\tWHERE rbft.bulk_itemid = fp.itemid And rbft.ppweek = " + ppWeek;
        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase_DKReportBuilder(sQuery);
        String ISBN_DB=null;

        if(rs_sybase.next()){
            ISBN_DB = rs_sybase.getString(1);

            System.out.println(ISBN_DB);
            TestLogger.logPass("ISBN : " + ISBN_DB);

        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        TestLogger.log("Database ppweek : " + ppWeek);
        TestLogger.log("Database ISBN : " + ISBN_DB);
        String strppWeek = String.valueOf(ppWeek);

        String ppWeek_ES = getPPWeekforES(strppWeek,sum_SchemaName);

        return ISBN_DB + "|" + ppWeek_ES;

    }




    public RestHighLevelClient connectESClient(){

        //Connect To Elastic Search
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("bks_svc", "e4??keTfp-dpdLTD"));

        RestClientBuilder builder = RestClient.builder(
                new HttpHost("lb-prod-bkses", 9200))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(
                            HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder
                                .setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

        TestLogger.log("Successfully Connected to Elastic Search Api");

        RestHighLevelClient client = new RestHighLevelClient(builder);

        TestLogger.log("Connected to Elastic Search Api");

        return client;


    }


    public String getRankIndex(String Datasource){
            String RankIndex = null;

            if(env_DQ.equals("PROD")){
                if(Datasource.equals("US BookScan")){
                    RankIndex = "prod_bsc_mkt_usw_ranks";
                }else if(Datasource.equals("B&N")){
                    RankIndex = "prod_bsc_mkt_bnw_ranks";
                }else if(Datasource.equals("PubTrack")){
                    RankIndex = "prod_bsc_mkt_ptd_ranks";
                }

            }else if(env_DQ.equals("WORK")){
                if(Datasource.equals("US BookScan")){
                    RankIndex = "work_bsc_mkt_usw_ranks";
                }else if(Datasource.equals("B&N")){
                    RankIndex = "work_bsc_mkt_bnw_ranks";
                }else if(Datasource.equals("PubTrack")){
                    RankIndex = "work_bsc_mkt_ptd_ranks";
                }

            }


           return RankIndex;

    }


    public String getWeeksIndex(String Datasource,String sType){
        String WeeksIndex = null;

        if(env_DQ.equals("PROD")){
            if(Datasource.equals("US BookScan")){
                if(sType.equals("DMA")){
                    WeeksIndex = "prod_bsc_mkt_usw_dma_subcat_summary";
                }else if(sType.equals("National")){
                    WeeksIndex = "prod_bsc_mkt_usw_cd_subcat_summary";
                }
            }
        }else if(env_DQ.equals("WORK")){
            if(Datasource.equals("US BookScan")){
                if(sType.equals("DMA")){
                    WeeksIndex = "work_bsc_mkt_usw_dma_subcat_summary";
                }else if(sType.equals("National")){
                    WeeksIndex = "work_bsc_mkt_usw_cd_subcat_summary";
                }
            }
        }


        return WeeksIndex;

    }


    public String getRankIndex_Geo(String Datasource){

        String RankIndex = null;

        if(env_DQ.equals("PROD")){
            if(Datasource.equals("US BookScan")){
                RankIndex = "prod_bsc_mkt_usw_geo_ranks";
            }else if(Datasource.equals("B&N")){
                RankIndex = "prod_bsc_mkt_bnw_geo_ranks";
            }
        }else if(env_DQ.equals("WORK")){
            if(Datasource.equals("US BookScan")){
                RankIndex = "work_bsc_mkt_usw_geo_ranks";
            }else if(Datasource.equals("B&N")){
                RankIndex = "work_bsc_mkt_bnw_geo_ranks";
            }

        }


        return RankIndex;

    }



    public String getRankIndex_WSJ(String Datasource){

        String RankIndex = null;

        if(env_DQ.equals("PROD")){
            if(Datasource.equals("US BookScan")){
                RankIndex = "prod_bsc_mkt_usw_wsj_ranks";
            }
        }else if(env_DQ.equals("WORK")){
            if(Datasource.equals("US BookScan")){
                RankIndex = "work_bsc_mkt_usw_wsj_ranks";
            }

        }


        return RankIndex;

    }


    public String getRankIndex_YTD(String Datasource){

        String RankIndex = null;

        if(env_DQ.equals("PROD")){
            if(Datasource.equals("US BookScan")){
                RankIndex = "prod_bsc_mkt_usw_ytd_ranks";
            }else if(Datasource.equals("B&N")){
                RankIndex = "prod_bsc_mkt_bnw_ytd_ranks";
            }
        }else if(env_DQ.equals("WORK")){
            if(Datasource.equals("US BookScan")){
                RankIndex = "work_bsc_mkt_usw_ytd_ranks";
            }else if(Datasource.equals("B&N")){
                RankIndex = "work_bsc_mkt_bnw_ytd_ranks";
            }
        }


        return RankIndex;

    }


    public String getBookIndex(String Datasource){

        String BookIndex = null;

        if(env_DQ.equals("PROD")){
            if(Datasource.equals("US BookScan")){
                BookIndex = "prod_bsc_mkt_usw_books";
            }else if(Datasource.equals("B&N")){
                BookIndex = "prod_bsc_mkt_bnw_books";
            }
        }else if(env_DQ.equals("WORK")){
            if(Datasource.equals("US BookScan")){
                BookIndex = "work_bsc_mkt_usw_books";
            }else if(Datasource.equals("B&N")){
                BookIndex = "work_bsc_mkt_bnw_books";
            }

        }


        return BookIndex;

    }


    public String getSalesIndex(String Datasource){

        String SalesIndex = null;

        if(env_DQ.equals("PROD")){
            if(Datasource.equals("US BookScan")){
                SalesIndex = "prod_bsc_mkt_usw_sales";
            }else if(Datasource.equals("B&N")){
                SalesIndex = "prod_bsc_mkt_bnw_sales";
            }else if(Datasource.equals("PubTrack")){
                SalesIndex = "prod_bsc_mkt_ptd_sales";
            }

        }else if(env_DQ.equals("WORK")){
            if(Datasource.equals("US BookScan")){
                SalesIndex = "work_bsc_mkt_usw_sales";
            }else if(Datasource.equals("B&N")){
                SalesIndex = "work_bsc_mkt_bnw_sales";
            }else if(Datasource.equals("PubTrack")){
                SalesIndex = "work_bsc_mkt_ptd_sales";
            }

        }


        return SalesIndex;

    }

    public String GetMetaDataIndex(String Datasource){

        String index_Val = null;

        if(env_DQ.equals("PROD")){
            if(Datasource.equals("US BookScan")){
                index_Val= "prod_bsc_mkt_usw_meta";
            }else if(Datasource.equals("B&N")){
                index_Val= "prod_bsc_mkt_bnw_meta";
            }else if(Datasource.equals("PubTrack")){
                index_Val= "prod_bsc_mkt_ptd_meta";
            }
        }else if(env_DQ.equals("WORK")){
            if(Datasource.equals("US BookScan")){
                index_Val= "work_bsc_mkt_usw_meta";
            }else if(Datasource.equals("B&N")){
                index_Val= "work_bsc_mkt_bnw_meta";
            }else if(Datasource.equals("PubTrack")){
                index_Val= "work_bsc_mkt_ptd_meta";
            }

        }


        return index_Val;

    }

    public String get52WeeksIndex(String Datasource,String sType){

        String SalesIndex = null;

        if(Datasource.equals("US BookScan")){
            SalesIndex = "prod_bsc_mkt_usw_sales";
        }else if(Datasource.equals("B&N")){
            SalesIndex = "prod_bsc_mkt_bnw_sales";
        }else if(Datasource.equals("PubTrack")){
            SalesIndex = "prod_bsc_mkt_ptd_sales";
        }

        return SalesIndex;

    }


    public String getBookDMAIndex(String Datasource){

        String BookIndex = null;

        if(Datasource.equals("US BookScan")){
            BookIndex = "prod_bsc_mkt_usw_dma_sales";
        }else if(Datasource.equals("B&N")){
            BookIndex = "prod_bsc_mkt_bnw_dma_sales";
        }

        return BookIndex;

    }


    public String getBookCDIndex(String Datasource){

        String BookIndex = null;

        if(env_DQ.equals("PROD")){
            if(Datasource.equals("US BookScan")){
                BookIndex = "prod_bsc_mkt_usw_cd_sales";
            }else if(Datasource.equals("B&N")){
                BookIndex = "prod_bsc_mkt_bnw_cd_sales";
            }
        }else if(env_DQ.equals("WORK")){
            if(Datasource.equals("US BookScan")){
                BookIndex = "work_bsc_mkt_usw_cd_sales";
            }else if(Datasource.equals("B&N")){
                BookIndex = "work_bsc_mkt_bnw_cd_sales";
            }
        }


        return BookIndex;

    }


    public String getMarketOverviewIndex(String Datasource){

        String MOIndex = null;

        if(env_DQ.equals("PROD")){
            if(Datasource.equals("US BookScan")){
                MOIndex = "prod_bsc_mkt_usw_market_summary";
            }else if(Datasource.equals("PubTrack")){
                MOIndex = "prod_bsc_mkt_ptd_market_summary";
            }
        }else if(env_DQ.equals("WORK")){
            if(Datasource.equals("US BookScan")){
                MOIndex = "work_bsc_mkt_usw_market_summary";
            }else if(Datasource.equals("PubTrack")){
                MOIndex = "work_bsc_mkt_ptd_market_summary";
            }
        }

        return MOIndex;


    }


    public String getEndTimePeriodDB(String TimeRange,String Datasource){

        String EndPeriod = null;
        if(Datasource.equals("PubTrack")){
            String[] splitDate = TimeRange.split(" ");

            DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM")
                    .withLocale(Locale.ENGLISH);
            TemporalAccessor accessor = parser.parse(splitDate[0]);
            int month = accessor.get(ChronoField.MONTH_OF_YEAR);

            EndPeriod = splitDate[1] + "-" + (month<10?("0"+month):(month)) + "-" + "01";
            return EndPeriod;

        }else{
            String[] splitDate = TimeRange.split(" - ");
            String EndDate_Entire = splitDate[1];
            String[] SplitEndDate = EndDate_Entire.split(" ");


            String txt_mon = SplitEndDate[0];
            int sDate = Integer.parseInt(SplitEndDate[1]);
            String sYear = SplitEndDate[2];

            TestLogger.log("Month From Sybase : " + txt_mon);
            TestLogger.log("Date From Sybase : " + sDate);
            TestLogger.log("Year From Sybase : " + sYear);

            DateTimeFormatter parser = DateTimeFormatter.ofPattern("MMM")
                    .withLocale(Locale.ENGLISH);
            TemporalAccessor accessor = parser.parse(txt_mon);
            int month = accessor.get(ChronoField.MONTH_OF_YEAR);

            EndPeriod = sYear + "-" + (month<10?("0"+month):(month)) + "-" + (sDate<10?("0"+sDate):(sDate));

            System.out.println(EndPeriod);
            TestLogger.log("Latest Period from Database : " + EndPeriod);

            return EndPeriod;
        }



    }


    public void DQCheckDMASales(String DataSource) throws Exception {

        boolean IsTestFail = false;


//        String BulkISBN = null;
//        //Get Bulk Sales ISBN
//        if(DataSource.equals("US BookScan")){
//            BulkISBN = getBulkSalesISBN_DB(DataSource);
//        }


        //Identify Test ISBN from Bestsellers Index
//        SearchRequest searchRequest = new SearchRequest("dev_bsc_mkt_usw_books");
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");;
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
//        String BookDMAIndex = getBookDMAIndex(DataSource);
        //----------------------------------------------------
//        RestHighLevelClient client = connectESClient();


        BoolQueryBuilder bqb = null;



        bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.termQuery("isbn13",ISBN))
                .filter(QueryBuilders.termQuery("dma","1389"));


        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
//        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.trackTotalHits(true);
        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.size(1);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(getBookDMAIndex(DataSource));
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);

        sResponse = srchResponse.toString();
        System.out.println(sResponse);



        //-----------------------------------------------------
/*        String BookDMAIndex = getBookDMAIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(BookDMAIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchSourceBuilder_search.sort("period",SortOrder.DESC);
        searchSourceBuilder_search.size(1);
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        System.out.println(sResponse);*/

        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String units_rtd_ES = getValue_ESResponse(sResponse,"units_rtd");
        String dma_ES = getValue_ESResponse(sResponse,"\"dma\"");
        String ppweek_ES = getValue_ESResponse(sResponse,"\"period\"");
//        String dma_ES = "1384";

        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_"+ env_DQ +".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }


        String ppWeekDB = getPPWeekFromDB(ppweek_ES,sum_SchemaName);

        //Now get Values from Sybase Database
//        String sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppweek from " +  sum_SchemaName + ".w_aggr_dma_ytd where dma = " + dma_ES + " And upc_code= '" + ISBN + "' And ppweek = " + ppWeek + " Order by ppweek Desc";
        String sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppweek from " +  sum_SchemaName + ".w_aggr_dma_ytd where dma = " + dma_ES + " And upc_code= '" + ISBN + "' And ppweek = " + ppWeekDB + " Order by ppweek Desc";
        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String unit_RTD=null;
        String unit_YTD=null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            unit_RTD = rs_sybase.getString(2);
            unit_YTD = rs_sybase.getString(3);

            System.out.println(unit_DB);
            System.out.println(unit_RTD);
            System.out.println(unit_YTD);
            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("RTD Database : " + unit_RTD);
            TestLogger.logPass("YTD Database : " + unit_YTD);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Unit RTD
        if(unit_RTD.equals(units_rtd_ES)){
            TestLogger.logPass("Unit RTD Value " + unit_RTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit RTD Value did not match for ISBN : " + ISBN + " Database : " + unit_RTD + " Application : " + units_rtd_ES);
            IsTestFail = true;
        }

        //Unit YTD
        if(unit_YTD.equals(units_ytd_ES)){
            TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
            IsTestFail = true;
        }



        if(IsTestFail==true){
            Assert.fail();
        }


    }


    public void DQCheckDMASales_Bulk(String DataSource) throws Exception {

        boolean IsTestFail = false;

        //Get Bulk Sales ISBN
        String BulkISBN_PPWeek = getBulkSalesISBN_DB(DataSource);
        String[] splitBulkISBN_PPWeek = BulkISBN_PPWeek.split("[|]");
        String BulkISBN = splitBulkISBN_PPWeek[0];


        //Identify Test ISBN from Bestsellers Index
//        SearchRequest searchRequest = new SearchRequest("dev_bsc_mkt_usw_books");
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = BulkISBN;
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
        String BookDMAIndex = getBookDMAIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(BookDMAIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchSourceBuilder_search.sort("period",SortOrder.DESC);
        searchSourceBuilder_search.size(1);
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String units_rtd_ES = getValue_ESResponse(sResponse,"units_rtd");
        String dma_ES = getValue_ESResponse(sResponse,"\"dma\"");

        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Now get Values from Sybase Database
        String sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppweek from " +  sum_SchemaName + ".w_aggr_dma_ytd where dma = " + dma_ES + " And upc_code= '" + ISBN + "' And ppweek = " + ppWeek + " Order by ppweek Desc";
        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String unit_RTD=null;
        String unit_YTD=null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            unit_RTD = rs_sybase.getString(2);
            unit_YTD = rs_sybase.getString(3);

            System.out.println(unit_DB);
            System.out.println(unit_RTD);
            System.out.println(unit_YTD);
            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("RTD Database : " + unit_RTD);
            TestLogger.logPass("YTD Database : " + unit_YTD);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Unit RTD
        if(unit_RTD.equals(units_rtd_ES)){
            TestLogger.logPass("Unit RTD Value " + unit_RTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit RTD Value did not match for ISBN : " + ISBN + " Database : " + unit_RTD + " Application : " + units_rtd_ES);
            IsTestFail = true;
        }

        //Unit YTD
        if(unit_YTD.equals(units_ytd_ES)){
            TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
            IsTestFail = true;
        }



        if(IsTestFail==true){
            Assert.fail();
        }


    }



    public void DQCheckDMASales_ALR(String DataSource) throws Exception {

        boolean IsTestFail = false;


//        String BulkISBN = null;
//        //Get Bulk Sales ISBN
//        if(DataSource.equals("US BookScan")){
//            BulkISBN = getBulkSalesISBN_DB(DataSource);
//        }


        //Identify Test ISBN from Bestsellers Index
//        SearchRequest searchRequest = new SearchRequest("dev_bsc_mkt_usw_books");
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");;
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
//        String BookDMAIndex = getBookDMAIndex(DataSource);
        //----------------------------------------------------
//        RestHighLevelClient client = connectESClient();


        BoolQueryBuilder bqb = null;

        bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.termQuery("isbn13",ISBN))
                .filter(QueryBuilders.termQuery("dma","1389"));


        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
//        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.trackTotalHits(true);
        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.size(1);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(getBookDMAIndex(DataSource));
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);

        sResponse = srchResponse.toString();
        System.out.println(sResponse);



        //-----------------------------------------------------
/*        String BookDMAIndex = getBookDMAIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(BookDMAIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchSourceBuilder_search.sort("period",SortOrder.DESC);
        searchSourceBuilder_search.size(1);
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        System.out.println(sResponse);*/

        String testOutlet = "1315843759";

        String units_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units_ytd");
        String units_rtd_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units_rtd");
        String dma_ES = getValue_ESResponse(sResponse,"\"dma\"");
        String period_ES = getValue_ESResponse(sResponse,"\"period\"");
//        String dma_ES = "1384";


        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_"+ env_DQ +".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Find Year Value
        String[] split_Period_ES = period_ES.split("[-]");
        String Year_ES = split_Period_ES[0];

        String period_DB = getPPWeekFromDB(period_ES,sum_SchemaName);

        //Varify if unitsold is appearing
        String sQueryUnitSale = "SELECT COALESCE(SUM(unitssold),0) from " +  sum_SchemaName + ".w_aggr_dma_ytd_outlet where dma = " + dma_ES + " And upc_code= '" + ISBN + "' And outletfamily = " + testOutlet + " And ppweek = " + period_DB;
        ResultSet rs_unitsold = dbUtil.ConnectSybase(sQueryUnitSale);
        String unitSoldinDB = "";
        if(rs_unitsold.next()){
            unitSoldinDB = rs_unitsold.getString(1);
        }

        if(!unitSoldinDB.equals("0.0")){

            //Now get Values from Sybase Database
//            String sQuery = "SELECT round(Sum(unitssold),0),round(Sum(unitssold_rtd),0),round(Sum(unitssold_ytd),0) from " +  sum_SchemaName + ".w_aggr_dma_ytd_outlet where dma = " + dma_ES + " And upc_code= '" + ISBN + "'  And ppweek = " + period_DB + " And outletfamily = " + testOutlet;

            String sQuery = "SELECT \n" +
                    " sum(CASE WHEN ppweek = " + period_DB + " THEN unitssold ELSE 0 END) AS unitssold_wk,\n" +
                    " sum(CASE WHEN year = "+ Year_ES +"   THEN unitssold ELSE 0 END) AS unitssold_ytd,\n" +
                    " sum(unitssold) AS unitssold_rtd\n" +
                    " FROM " + sum_SchemaName + ".fact_data_item\n" +
                    " WHERE dma = 1389  \n" +
                    "  AND upc_code= '" + ISBN + "'  \n" +
                    "  And outletfamily = " + testOutlet + " \n";

            System.out.println(sQuery);
            ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
            String unit_DB=null;
            String unit_RTD=null;
            String unit_YTD=null;
            if(rs_sybase.next()){
                unit_DB = rs_sybase.getString(1);
                unit_YTD = rs_sybase.getString(2);
                unit_RTD = rs_sybase.getString(3);

                System.out.println(unit_DB);
                System.out.println(unit_RTD);
                System.out.println(unit_YTD);
                TestLogger.logPass("Unit Database : " + unit_DB);
                TestLogger.logPass("RTD Database : " + unit_RTD);
                TestLogger.logPass("YTD Database : " + unit_YTD);
            }else{
                TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
                Assert.fail();
            }

            //Validation
            //Unit Sales
            if(unit_DB.equals(units_ES)){
                TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
                IsTestFail = true;
            }

            //Unit RTD
            if(unit_RTD.equals(units_rtd_ES)){
                TestLogger.logPass("Unit RTD Value " + unit_RTD + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit RTD Value did not match for ISBN : " + ISBN + " Database : " + unit_RTD + " Application : " + units_rtd_ES);
                IsTestFail = true;
            }

            //Unit YTD
            if(unit_YTD.equals(units_ytd_ES)){
                TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
                IsTestFail = true;
            }

        }else{

            if(unitSoldinDB.equals(units_ES)){
                TestLogger.logPass("Unit Value " + unitSoldinDB + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unitSoldinDB + " Application : " + units_ES);
                IsTestFail = true;
            }

        }






        if(IsTestFail==true){
            Assert.fail();
        }


    }

    public String getPPWeekFromDB(String EndWeek,String SchemaName) throws Exception {

        System.out.println(EndWeek);
        String PPWeek_DB = "";

        String[] SplitEndWeek = EndWeek.split("[-]");
        String sYear = SplitEndWeek[0];
        String sMonth = SplitEndWeek[1];
        String sDate = SplitEndWeek[2];

        String sMon_Name_DB = formatMonth(sMonth);

        PPWeek_DB = sMon_Name_DB + " " + sDate + " " + sYear;

        System.out.println(PPWeek_DB);
        TestLogger.log(PPWeek_DB);

        //Get ppMonth From Sybase Table
        DBUtil dbUtil = new DBUtil();
        String sQueryPPWeek = "Select ppweek from " + SchemaName + ".m_time_periods_week Where description like '%" + PPWeek_DB + "%'";
        ResultSet rs_ppweek = dbUtil.ConnectSybase(sQueryPPWeek);

        if(rs_ppweek.next()){
            PPWeek_DB = rs_ppweek.getString(1);
        }

        TestLogger.log(PPWeek_DB);

        return PPWeek_DB;


    }


    //Get PPWeekforES
    public String getPPWeekforES(String ppWeekCode,String SchemaName) throws Exception {

/*        System.out.println(EndWeek);
        String PPWeek_DB = "";

        String[] SplitEndWeek = EndWeek.split("[-]");
        String sYear = SplitEndWeek[0];
        String sMonth = SplitEndWeek[1];
        String sDate = SplitEndWeek[2];

        String sMon_Name_DB = formatMonth(sMonth);

        PPWeek_DB = sMon_Name_DB + " " + sDate + " " + sYear;

        System.out.println(PPWeek_DB);
        TestLogger.logFail(PPWeek_DB);*/

        //Get ppMonth From Sybase Table
        DBUtil dbUtil = new DBUtil();
        String sQueryPPWeek = "Select description from " + SchemaName + ".m_time_periods_week Where ppweek = " + ppWeekCode;
        ResultSet rs_ppweek = dbUtil.ConnectSybase(sQueryPPWeek);
        String ppWeek_Description = "";
        if(rs_ppweek.next()){
            ppWeek_Description = rs_ppweek.getString(1);
        }

        //Split Description
        String[] splitDesc = ppWeek_Description.split("[-]");
        String trim_EndDesc = splitDesc[1].trim();
        String[] splitEndDesc = trim_EndDesc.split(" ");
        String sMonth = splitEndDesc[0];


        String sDate = splitEndDesc[1];
        String sYear = splitEndDesc[2];

        String sMonthSTR = ConvertMonthNameToNumber(sMonth);

        String ppWeek_ES = sYear + "-" + sMonthSTR + "-" + sDate;



        return ppWeek_ES;


    }


    public String ConvertMonthNameToNumber(String monthName){

        int monthNumber = 0;
        String strMonth = null;

        try{
            Date date = new SimpleDateFormat("MMM").parse(monthName);//put your month name here
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            monthNumber=cal.get(Calendar.MONTH) + 1;
//            strMonth = String.valueOf(monthNumber);
            strMonth = String.format("%02d", monthNumber);

            System.out.println(monthNumber);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(monthNumber);


        return strMonth;


    }


    public String formatMonth(String month) throws Exception {
        SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMM");
        return monthDisplay.format(monthParse.parse(month));
    }



    public void DQCheckCDSales(String DataSource) throws Exception {

        boolean IsTestFail = false;


        //Identify Test ISBN from Bestsellers Index
//        SearchRequest searchRequest = new SearchRequest("dev_bsc_mkt_usw_books");
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
        String BookDMAIndex = getBookCDIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(BookDMAIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchSourceBuilder_search.sort("period",SortOrder.DESC);
        searchSourceBuilder_search.size(1);
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String units_rtd_ES = getValue_ESResponse(sResponse,"units_rtd");
        String CD_ES = getValue_ESResponse(sResponse,"\"census_division\"");

        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Now get Values from Sybase Database
        String sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppweek from " +  sum_SchemaName + ".w_aggr_cd_ytd where censusdivisionusa = " + CD_ES + " And upc_code= '" + ISBN + "' And ppweek = " + ppWeek + " Order by ppweek Desc";
        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String unit_RTD=null;
        String unit_YTD=null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            unit_RTD = rs_sybase.getString(2);
            unit_YTD = rs_sybase.getString(3);

            System.out.println(unit_DB);
            System.out.println(unit_RTD);
            System.out.println(unit_YTD);
            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("RTD Database : " + unit_RTD);
            TestLogger.logPass("YTD Database : " + unit_YTD);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Unit RTD
        if(unit_RTD.equals(units_rtd_ES)){
            TestLogger.logPass("Unit RTD Value " + unit_RTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit RTD Value did not match for ISBN : " + ISBN + " Database : " + unit_RTD + " Application : " + units_rtd_ES);
            IsTestFail = true;
        }

        //Unit YTD
        if(unit_YTD.equals(units_ytd_ES)){
            TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
            IsTestFail = true;
        }



        if(IsTestFail==true){
            Assert.fail();
        }


    }



    public void DQCheckCDSales_Bulk(String DataSource) throws Exception {

        boolean IsTestFail = false;

        //Get Bulk Sales ISBN
        String BulkISBN_PPWeek = getBulkSalesISBN_DB(DataSource);
        String[] splitBulkISBN_PPWeek = BulkISBN_PPWeek.split("[|]");
        String BulkISBN = splitBulkISBN_PPWeek[0];

        //Identify Test ISBN from Bestsellers Index
//        SearchRequest searchRequest = new SearchRequest("dev_bsc_mkt_usw_books");
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = BulkISBN;
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
        String BookDMAIndex = getBookCDIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(BookDMAIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchSourceBuilder_search.sort("period",SortOrder.DESC);
        searchSourceBuilder_search.size(1);
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String units_rtd_ES = getValue_ESResponse(sResponse,"units_rtd");
        String CD_ES = getValue_ESResponse(sResponse,"\"census_division\"");

        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Now get Values from Sybase Database
        String sQuery = "SELECT round(proj_unitssold,0),round(proj_unitssold_rtd,0),round(proj_unitssold_ytd,0),ppweek from " +  sum_SchemaName + ".w_aggr_cd_ytd where censusdivisionusa = " + CD_ES + " And upc_code= '" + ISBN + "' And ppweek = " + ppWeek + " Order by ppweek Desc";
        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String unit_RTD=null;
        String unit_YTD=null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            unit_RTD = rs_sybase.getString(2);
            unit_YTD = rs_sybase.getString(3);

            System.out.println(unit_DB);
            System.out.println(unit_RTD);
            System.out.println(unit_YTD);
            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("RTD Database : " + unit_RTD);
            TestLogger.logPass("YTD Database : " + unit_YTD);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Unit RTD
        if(unit_RTD.equals(units_rtd_ES)){
            TestLogger.logPass("Unit RTD Value " + unit_RTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit RTD Value did not match for ISBN : " + ISBN + " Database : " + unit_RTD + " Application : " + units_rtd_ES);
            IsTestFail = true;
        }

        //Unit YTD
        if(unit_YTD.equals(units_ytd_ES)){
            TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
            IsTestFail = true;
        }



        if(IsTestFail==true){
            Assert.fail();
        }


    }




    public void DQCheckCDSales_ALR(String DataSource) throws Exception {

        boolean IsTestFail = false;


        //Identify Test ISBN from Bestsellers Index
//        SearchRequest searchRequest = new SearchRequest("dev_bsc_mkt_usw_books");
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex = getRankIndex(DataSource);
//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
        String BookDMAIndex = getBookCDIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(BookDMAIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchSourceBuilder_search.sort("period",SortOrder.DESC);
        searchSourceBuilder_search.size(1);
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        String testOutlet= "1315843759";

        String units_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units_ytd");
        String units_rtd_ES = getValue_ESResponse(sResponse,"of_" + testOutlet + "_units_rtd");
        String CD_ES = getValue_ESResponse(sResponse,"\"census_division\"");
        String period_ES = getValue_ESResponse(sResponse,"\"period\"");

        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }


        String period_DB = getPPWeekFromDB(period_ES,sum_SchemaName);

        //Varify if unitsold is appearing
        String sQueryUnitSale = "SELECT COALESCE(SUM(unitssold),0) from " +  sum_SchemaName + ".w_aggr_cd_ytd_outlet where censusdivisionusa = " + CD_ES + " And upc_code= '" + ISBN + "' And outletfamily = " + testOutlet + " And ppweek = " + period_DB;
        ResultSet rs_unitsold = dbUtil.ConnectSybase(sQueryUnitSale);
        String unitSoldinDB = "";
        if(rs_unitsold.next()){
            unitSoldinDB = rs_unitsold.getString(1);
        }

        if(!unitSoldinDB.equals("0.0")){


            //Now get Values from Sybase Database
            String sQuery = "SELECT round(Sum(unitssold),0),round(Sum(unitssold_rtd),0),round(Sum(unitssold_ytd),0) from " +  sum_SchemaName + ".w_aggr_cd_ytd_outlet where censusdivisionusa = " + CD_ES + " And upc_code= '" + ISBN + "' And ppweek = " + period_DB + " And  outletfamily = " + testOutlet;
            System.out.println(sQuery);
            ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
            String unit_DB=null;
            String unit_RTD=null;
            String unit_YTD=null;
            if(rs_sybase.next()){
                unit_DB = rs_sybase.getString(1);
                unit_RTD = rs_sybase.getString(2);
                unit_YTD = rs_sybase.getString(3);

                System.out.println(unit_DB);
                System.out.println(unit_RTD);
                System.out.println(unit_YTD);
                TestLogger.logPass("Unit Database : " + unit_DB);
                TestLogger.logPass("RTD Database : " + unit_RTD);
                TestLogger.logPass("YTD Database : " + unit_YTD);
            }else{
                TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
                Assert.fail();
            }

            //Validation
            //Unit Sales
            if(unit_DB.equals(units_ES)){
                TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
                IsTestFail = true;
            }

            //Unit RTD
            if(unit_RTD.equals(units_rtd_ES)){
                TestLogger.logPass("Unit RTD Value " + unit_RTD + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit RTD Value did not match for ISBN : " + ISBN + " Database : " + unit_RTD + " Application : " + units_rtd_ES);
                IsTestFail = true;
            }

            //Unit YTD
            if(unit_YTD.equals(units_ytd_ES)){
                TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
                IsTestFail = true;
            }

        }else{

            if(unitSoldinDB.equals(units_ES)){
                TestLogger.logPass("Unit Value " + unitSoldinDB + " Matched for ISBN : " + ISBN);
            }else{
                TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unitSoldinDB + " Application : " + units_ES);
                IsTestFail = true;
            }

        }




        if(IsTestFail==true){
            Assert.fail();
        }


    }


    //This Method will validate Rank Index
    public void DQCheckRank(String DataSource,String RankType) throws Exception {


        boolean IsTestFail = false;


        //Identify Test ISBN from Bestsellers Index
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.termQuery("summary_source_type_id",Sum_Src_ID));
//        searchSourceBuilder.sort("load_date",SortOrder.DESC);
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex ="";
        if(RankType.equals("Ranks")){
            RankIndex = getRankIndex(DataSource);
        }else if(RankType.equals("Geo")){
            RankIndex = getRankIndex_Geo(DataSource);
        }else if(RankType.equals("YTD")){
            RankIndex = getRankIndex_YTD(DataSource);
        }

//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
//        String BookDMAIndex = getBookCDIndex(DataSource);
        SearchRequest searchRequest_search = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchSourceBuilder_search.sort("period",SortOrder.DESC);
        searchSourceBuilder_search.size(1);
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        String rank_ES = getValue_ESResponse(sResponse,"\"rank\"");
        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String reportType_ES = getValue_ESResponse(sResponse,"report_type");
        String rank_report_id_ES = getValue_ESResponse(sResponse,"rank_report_id");


        //Retrieve Data From Database
        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Get Rank Table Based on RankType
        String RankTable = "";
        if(RankType.equals("Ranks")){
            RankTable = "rank_history";
        }else if(RankType.equals("Geo")){
            RankTable = "rank_history_geo";
        }else if(RankType.equals("YTD")){
            RankTable = "rank_history_ytd";
        }

      //Now get Values from Sybase Database
        String sQuery = "";
        if(DataSource.equals("PubTrack")){
            sQuery = "SELECT round(proj_unitssold,0),round(rank,0),round(ytdsales,0),ppweek from " +  sum_SchemaName + "." + RankTable +" where upc_code= '" + ISBN + "' And rank_report_id = '"+ rank_report_id_ES +"' And reporttype = " + reportType_ES + " Order by ppmonth Desc";
        }else{
            sQuery = "SELECT round(proj_unitssold,0),round(rank,0),round(ytdsales,0),ppweek from " +  sum_SchemaName + "." + RankTable +" where upc_code= '" + ISBN + "' And rank_report_id = '"+ rank_report_id_ES +"' And reporttype = " + reportType_ES + " Order by ppweek Desc";

        }

        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String rank_DB=null;
        String unit_YTD=null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            rank_DB = rs_sybase.getString(2);
            unit_YTD = rs_sybase.getString(3);

            System.out.println(unit_DB);
            System.out.println(rank_DB);
            System.out.println(unit_YTD);
            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("Rank Database : " + rank_DB);
            TestLogger.logPass("YTD Database : " + unit_YTD);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Unit RTD
        if(rank_DB.equals(rank_ES)){
            TestLogger.logPass("Rank Value " + rank_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Rank Value did not match for ISBN : " + ISBN + " Database : " + rank_DB + " Application : " + rank_ES);
            IsTestFail = true;
        }

        //Unit YTD
        if(unit_YTD.equals(units_ytd_ES)){
            TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
            IsTestFail = true;
        }



        if(IsTestFail==true){
            Assert.fail();
        }

    }



    //This Method will validate Rank Index
    public void DQCheckRank_Bulk(String DataSource,String RankType) throws Exception {


        boolean IsTestFail = false;

        //Get Bulk Sales ISBN
        String BulkISBN_PPWeek = getBulkSalesISBN_DB(DataSource);

        String[] splitBulkISBNPPWeek = BulkISBN_PPWeek.split("[|]");
        String BulkISBN = splitBulkISBNPPWeek[0];
        String PPWeek_ES = splitBulkISBNPPWeek[1];

        //Identify Test ISBN from Bestsellers Index
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex ="";
        if(RankType.equals("Ranks")){
            RankIndex = getRankIndex(DataSource);
        }else if(RankType.equals("Geo")){
            RankIndex = getRankIndex_Geo(DataSource);
        }else if(RankType.equals("YTD")){
            RankIndex = getRankIndex_YTD(DataSource);
        }

/*//        SearchRequest searchRequest_bsl = new SearchRequest("prod_bsc_mkt_usw_ranks");
        RestHighLevelClient client2 = connectESClient();
        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client2.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();*/

        //isbn
        String ISBN = BulkISBN;
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
/*        SearchRequest searchRequest_search = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchSourceBuilder_search.sort("period",SortOrder.DESC);
        searchSourceBuilder_search.size(1);
        searchRequest_search.source(searchSourceBuilder_search);*/


        //-----------------------------------------------------------------------------
        BoolQueryBuilder bqb = null;

        bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.termQuery("isbn13",ISBN))
                .filter(QueryBuilders.termQuery("period",PPWeek_ES));
//                .filter(QueryBuilders.termQuery("period","2020-06-27"));


        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
        searchSrc_SalesRankHis.trackTotalHits(true);
        searchSrc_SalesRankHis.size(1);
        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(RankIndex);
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);


        RestHighLevelClient client2 = connectESClient();

        SearchResponse srchResponse = client2.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);

        sResponse = srchResponse.toString();
        System.out.println(sResponse);
        //-----------------------------------------------------------------------------


/*
        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        Thread.sleep(3000);
        sResponse = searchResponse.toString();
        TestLogger.log(sResponse);
        Thread.sleep(3000);
*/


        String ppWeek_ES = getValue_ESResponse(sResponse,"\"period\"");
        TestLogger.log("ES PPWeek : " + ppWeek_ES);


        String rank_ES = getValue_ESResponse(sResponse,"\"rank\"");
        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String reportType_ES = getValue_ESResponse(sResponse,"report_type");
        String rank_report_id_ES = getValue_ESResponse(sResponse,"rank_report_id");


        //Retrieve Data From Database
        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Get Rank Table Based on RankType
        String RankTable = "";
        if(RankType.equals("Ranks")){
            RankTable = "rank_history";
        }else if(RankType.equals("Geo")){
            RankTable = "rank_history_geo";
        }else if(RankType.equals("YTD")){
            RankTable = "rank_history_ytd";
        }

        //Now get Values from Sybase Database
        String sQuery = "";
        if(DataSource.equals("PubTrack")){
            sQuery = "SELECT round(proj_unitssold,0),round(rank,0),round(ytdsales,0),ppweek from " +  sum_SchemaName + "." + RankTable +" where upc_code= '" + ISBN + "' And rank_report_id = '"+ rank_report_id_ES +"' And reporttype = " + reportType_ES + " Order by ppmonth Desc";
        }else{
            sQuery = "SELECT round(proj_unitssold,0),round(rank,0),round(ytdsales,0),ppweek from " +  sum_SchemaName + "." + RankTable +" where upc_code= '" + ISBN + "' And rank_report_id = '"+ rank_report_id_ES +"' And reporttype = " + reportType_ES + " Order by ppweek Desc";

        }

        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit_DB=null;
        String rank_DB=null;
        String unit_YTD=null;
        if(rs_sybase.next()){
            unit_DB = rs_sybase.getString(1);
            rank_DB = rs_sybase.getString(2);
            unit_YTD = rs_sybase.getString(3);

            System.out.println(unit_DB);
            System.out.println(rank_DB);
            System.out.println(unit_YTD);
            TestLogger.logPass("Unit Database : " + unit_DB);
            TestLogger.logPass("Rank Database : " + rank_DB);
            TestLogger.logPass("YTD Database : " + unit_YTD);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Unit RTD
        if(rank_DB.equals(rank_ES)){
            TestLogger.logPass("Rank Value " + rank_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Rank Value did not match for ISBN : " + ISBN + " Database : " + rank_DB + " Application : " + rank_ES);
            IsTestFail = true;
        }

        //Unit YTD
        if(unit_YTD.equals(units_ytd_ES)){
            TestLogger.logPass("Unit YTD Value " + unit_YTD + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit YTD Value did not match for ISBN : " + ISBN + " Database : " + unit_YTD + " Application : " + units_ytd_ES);
            IsTestFail = true;
        }



        if(IsTestFail==true){
            Assert.fail();
        }

    }







    //This Method will validate Rank Index
    public void DQCheckRank_WSJ(String DataSource,String RankType) throws Exception {


        boolean IsTestFail = false;


        //Identify Test ISBN from Bestsellers Index
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex ="";
        if(RankType.equals("Ranks")){
            RankIndex = getRankIndex_WSJ(DataSource);
        }

/*        SearchRequest searchRequest_bsl = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_bsl = new SearchSourceBuilder();
        searchSourceBuilder_bsl.query(QueryBuilders.termQuery("period",latestPeriod));
        searchSourceBuilder_bsl.size(1);
        searchRequest_bsl.source(searchSourceBuilder_bsl);

        searchResponse = client.search(searchRequest_bsl,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();

        //isbn
        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        TestLogger.log("Test ISBN : " + ISBN);

        //Get Sales Data from Basic Search or Advance Search Index
        SearchRequest searchRequest_search = new SearchRequest(RankIndex);
        SearchSourceBuilder searchSourceBuilder_search = new SearchSourceBuilder();
        searchSourceBuilder_search.query(QueryBuilders.termQuery("isbn13",ISBN));
        searchSourceBuilder_search.sort("period",SortOrder.DESC);
        searchSourceBuilder_search.size(1);
        searchRequest_search.source(searchSourceBuilder_search);

        searchResponse = client.search(searchRequest_search,RequestOptions.DEFAULT);
        sResponse = searchResponse.toString();*/

        //--------------------------------------------------------------------------
//        RestHighLevelClient client = connectESClient();
        BoolQueryBuilder bqb = null;

        bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.termQuery("period",latestPeriod))
                .filter(QueryBuilders.termQuery("rank_report_id","wsjhardcoverfiction"));
//                .filter(QueryBuilders.termQuery("period","2020-06-27"));


        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
//        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.trackTotalHits(true);
        searchSrc_SalesRankHis.size(1);
        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(RankIndex);
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);

        sResponse = srchResponse.toString();
        System.out.println(sResponse);



        String rank_ES = getValue_ESResponse(sResponse,"\"rank\"");
        String lwrank_ES = getValue_ESResponse(sResponse,"\"last_period_rank\"");
        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String reportType_ES = getValue_ESResponse(sResponse,"report_type");
        String rank_report_id_ES = getValue_ESResponse(sResponse,"rank_report_id");
        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        String ppWeek_ES = getValue_ESResponse(sResponse,"\"period\"");


        //Retrieve Data From Database
        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

/*        //Get Rank Table Based on RankType
        String RankTable = "";
        if(RankType.equals("Ranks")){
            RankTable = "rank_history";
        }else if(RankType.equals("Geo")){
            RankTable = "rank_history_geo";
        }else if(RankType.equals("YTD")){
            RankTable = "rank_history_ytd";
        }

        //Now get Values from Sybase Database
        String sQuery = "";
        if(DataSource.equals("PubTrack")){
            sQuery = "SELECT round(proj_unitssold,0),round(rank,0),round(ytdsales,0),ppweek from " +  sum_SchemaName + "." + RankTable +" where upc_code= '" + ISBN + "' And rank_report_id = '"+ rank_report_id_ES +"' And reporttype = " + reportType_ES + " Order by ppmonth Desc";
        }else{
            sQuery = "SELECT round(proj_unitssold,0),round(rank,0),round(ytdsales,0),ppweek from " +  sum_SchemaName + "." + RankTable +" where upc_code= '" + ISBN + "' And rank_report_id = '"+ rank_report_id_ES +"' And reporttype = " + reportType_ES + " Order by ppweek Desc";

        }*/

        String ppWeek_DB = getPPWeekFromDB(ppWeek_ES,sum_SchemaName);

        String sQuery = "SELECT [rank],lwrank,round(proj_unitssold,0) FROM " + sum_SchemaName + ".rank_history rh INNER JOIN " + sum_SchemaName + ".m_title_lookup_all t ON rh.itemnumber = t.itemnumber \n" +
                " WHERE reporttype=1318846876 AND ppweek=" + ppWeek_DB + " AND rank_report_id ='wsjhardcoverfiction' And t.upc_code = '"+ ISBN +"'\n" +
                " ORDER BY [rank]";


        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String rank_DB=null;
        String lwrank_DB=null;
        String unit_DB =null;
        if(rs_sybase.next()){
            rank_DB = rs_sybase.getString(1);
            lwrank_DB = rs_sybase.getString(2);
            unit_DB = rs_sybase.getString(3);


            TestLogger.logPass("Rank Database : " + rank_DB);
            TestLogger.logPass("Last Week Rank Database : " + lwrank_DB);
            TestLogger.logPass("Unit Sold Database : " + unit_DB);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Rank
        if(rank_DB.equals(rank_ES)){
            TestLogger.logPass("Rank Value " + rank_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Rank Value did not match for ISBN : " + ISBN + " Database : " + rank_DB + " Application : " + rank_ES);
            IsTestFail = true;
        }


        //Last Week Rank
        if(lwrank_DB.equals(lwrank_ES)){
            TestLogger.logPass("LW Rank Value " + lwrank_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("LW Rank Value did not match for ISBN : " + ISBN + " Database : " + lwrank_DB + " Application : " + lwrank_ES);
            IsTestFail = true;
        }





        if(IsTestFail==true){
            Assert.fail();
        }

    }


    //This Method will validate Rank Index
    public void DQCheckRank_ALR(String DataSource,String RankType) throws Exception {


        boolean IsTestFail = false;


        //Identify Test ISBN from Bestsellers Index
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        RestHighLevelClient client = connectESClient();

        String metaDataIndex = GetMetaDataIndex(DataSource);

        //Execute Elastic Search Query
        SearchRequest searchRequest = new SearchRequest(metaDataIndex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(1);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        //Search Hits
        SearchHits searchhits = searchResponse.getHits();
        System.out.println(searchResponse.toString());
        String sResponse = searchResponse.toString();

        //Get Latest Time Period
        String latestPeriod = getValue_ESResponse(sResponse,"period");

        //Get ISBN from Bestseller list
        String RankIndex ="";
        if(RankType.equals("Ranks")){
            RankIndex = getRankIndex(DataSource);
        }


        //--------------------------------------------------------------------------
//        RestHighLevelClient client = connectESClient();
        BoolQueryBuilder bqb = null;

        bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.termQuery("period",latestPeriod))
                .filter(QueryBuilders.termQuery("report_type","1318846877"));
//                .filter(QueryBuilders.termQuery("period","2020-06-27"));


        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
//        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.trackTotalHits(true);
        searchSrc_SalesRankHis.size(1);
        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(RankIndex);
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);

        sResponse = srchResponse.toString();
        System.out.println(sResponse);



        String rank_ES = getValue_ESResponse(sResponse,"\"rank\"");
        String lwrank_ES = getValue_ESResponse(sResponse,"\"last_period_rank\"");
        String units_ES = getValue_ESResponse(sResponse,"units");
        String units_ytd_ES = getValue_ESResponse(sResponse,"units_ytd");
        String reportType_ES = getValue_ESResponse(sResponse,"report_type");
        String rank_report_id_ES = getValue_ESResponse(sResponse,"rank_report_id");
        String ISBN = getValue_ESResponse(sResponse,"isbn13");
        String ppWeek_ES = getValue_ESResponse(sResponse,"\"period\"");


        //Retrieve Data From Database
        //Get DataBase Values
        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

/*        //Get Rank Table Based on RankType
        String RankTable = "";
        if(RankType.equals("Ranks")){
            RankTable = "rank_history";
        }else if(RankType.equals("Geo")){
            RankTable = "rank_history_geo";
        }else if(RankType.equals("YTD")){
            RankTable = "rank_history_ytd";
        }

        //Now get Values from Sybase Database
        String sQuery = "";
        if(DataSource.equals("PubTrack")){
            sQuery = "SELECT round(proj_unitssold,0),round(rank,0),round(ytdsales,0),ppweek from " +  sum_SchemaName + "." + RankTable +" where upc_code= '" + ISBN + "' And rank_report_id = '"+ rank_report_id_ES +"' And reporttype = " + reportType_ES + " Order by ppmonth Desc";
        }else{
            sQuery = "SELECT round(proj_unitssold,0),round(rank,0),round(ytdsales,0),ppweek from " +  sum_SchemaName + "." + RankTable +" where upc_code= '" + ISBN + "' And rank_report_id = '"+ rank_report_id_ES +"' And reporttype = " + reportType_ES + " Order by ppweek Desc";

        }*/

        String ppWeek_DB = getPPWeekFromDB(ppWeek_ES,sum_SchemaName);

        String sQuery = "SELECT [rank],lwrank,round(proj_unitssold,0) FROM " + sum_SchemaName + ".rank_history rh INNER JOIN " + sum_SchemaName + ".m_title_lookup_all t ON rh.itemnumber = t.itemnumber \n" +
                " WHERE reporttype=1318846877 AND ppweek=" + ppWeek_DB + " AND rank_report_id ='" + rank_report_id_ES + "' And t.upc_code = '"+ ISBN +"'\n" +
                " ORDER BY [rank]";


        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String rank_DB=null;
        String lwrank_DB=null;
        String unit_DB =null;
        if(rs_sybase.next()){
            rank_DB = rs_sybase.getString(1);
            lwrank_DB = rs_sybase.getString(2);
            unit_DB = rs_sybase.getString(3);


            TestLogger.logPass("Rank Database : " + rank_DB);
            TestLogger.logPass("Last Week Rank Database : " + lwrank_DB);
            TestLogger.logPass("Unit Sold Database : " + unit_DB);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //Unit Sales
        if(unit_DB.equals(units_ES)){
            TestLogger.logPass("Unit Value " + unit_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Unit Value did not match for ISBN : " + ISBN + " Database : " + unit_DB + " Application : " + units_ES);
            IsTestFail = true;
        }

        //Rank
        if(rank_DB.equals(rank_ES)){
            TestLogger.logPass("Rank Value " + rank_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("Rank Value did not match for ISBN : " + ISBN + " Database : " + rank_DB + " Application : " + rank_ES);
            IsTestFail = true;
        }


        //Last Week Rank
        if(lwrank_DB.equals(lwrank_ES)){
            TestLogger.logPass("LW Rank Value " + lwrank_DB + " Matched for ISBN : " + ISBN);
        }else{
            TestLogger.logFail("LW Rank Value did not match for ISBN : " + ISBN + " Database : " + lwrank_DB + " Application : " + lwrank_ES);
            IsTestFail = true;
        }





        if(IsTestFail==true){
            Assert.fail();
        }

    }

    public void DQCheckMarketOverview(String DataSource) throws Exception {

        boolean IsTestFail = false;

        RestHighLevelClient client = connectESClient();
        BoolQueryBuilder bqb = null;

        bqb = new BoolQueryBuilder()
                .filter(QueryBuilders.termQuery("publisher_family_code","1319047823"));
//                .filter(QueryBuilders.termQuery("period","2020-06-27"));


        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
//        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.trackTotalHits(true);
        searchSrc_SalesRankHis.size(1);
        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(getMarketOverviewIndex(DataSource));
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);

        String sResponse = srchResponse.toString();
        System.out.println(sResponse);

        //get ES Values
        String units_ES = getValue_ESResponse(sResponse,"units");
        String pubFamilyCode_ES = getValue_ESResponse(sResponse,"publisher_family_code");
        String RankCategoryCode_ES = getValue_ESResponse(sResponse,"rank_category_code");
        String RankSubjectGroup_ES = getValue_ESResponse(sResponse,"rank_subject_group_code");
        String format_code_ES = getValue_ESResponse(sResponse,"format_code");
        String vintage_code_ES = getValue_ESResponse(sResponse,"vintage_code");
        String period_ES = getValue_ESResponse(sResponse,"period");


        //Retrieve Data from Database
        String sum_SchemaName = getSummarySchema(DataSource);

        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();

        //Max Week
        String qMaxQuary = "Select max(ppweek) from " + sum_SchemaName + ".fact_posdata fp \n" +
                " Where publisherfamily = " + pubFamilyCode_ES + " And bsranksubjectgroup = " + RankSubjectGroup_ES + " And format = " + format_code_ES + " And vintage = " + vintage_code_ES + " And bsrankcategory = " + RankCategoryCode_ES + " \n" +
                " AND CATEGORYCODE NOT IN (1318846869,1315909228,1315909229,1315909230,1315909231,1315909232)\n" +
                " AND CHANNEL IN (1318342239,1318342238)";

        ResultSet rsMaxPPWeek = dbUtil.ConnectSybase(qMaxQuary);
        String ppweek_DB = null;
        if(rsMaxPPWeek.next()){
            ppweek_DB = rsMaxPPWeek.getString(1);
//            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Max ppWeek DB : " + ppweek_DB);
        }else{
            TestLogger.logFail("Max ppWeek DB : " + ppweek_DB);
            Assert.fail();
        }


        //get Market Overview Unit Value
        String sQuery = "Select sum(proj_unitssold),bsranksubjectgroup,bsrankcategory,publisherfamily,format,vintage from " + sum_SchemaName + ".fact_posdata fp \n" +
                " Where publisherfamily = " + pubFamilyCode_ES + " And bsranksubjectgroup = " + RankSubjectGroup_ES + " And format = " + format_code_ES + " And ppweek = " + ppweek_DB + " And vintage = " + vintage_code_ES + " And bsrankcategory = " + RankCategoryCode_ES + " \n" +
                " AND CATEGORYCODE NOT IN (1318846869,1315909228,1315909229,1315909230,1315909231,1315909232)\n" +
                " AND CHANNEL IN (1318342239,1318342238) \n" +
                " GROUP BY publisherfamily, bsranksubjectgroup, bsrankcategory,format,vintage";

        ResultSet rsOracle = dbUtil.ConnectSybase(sQuery);


        String units_DB = null;
        if(rsOracle.next()){
            units_DB = rsOracle.getString(1);
//            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + units_DB);
        }else{
            TestLogger.logFail("Summary Source Schema : " + units_DB);
            Assert.fail();
        }

        double dbl_unit_ES = Math.round(Double.parseDouble(units_ES));
        double dbl_unit_DB = Math.round(Double.parseDouble(units_DB));


        //Validation
        if(dbl_unit_ES == dbl_unit_DB){
            TestLogger.logPass("Unit Value Matched : " + dbl_unit_DB);
        }else{
            TestLogger.logFail("Unit Value did not Match. Application : " + dbl_unit_ES + " Darabase : " + dbl_unit_DB);
            IsTestFail = true;

        }

        if(IsTestFail==true){
            Assert.fail();
        }


    }


    //This Method will Return Go Live Summary Schema
    public String getSummarySchema(String DataSource) throws Exception {

        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        return sum_SchemaName;

    }





    //This Method will validate Rank Index
    public void DQCheck52Weeks(String DataSource,String DataType) throws Exception {


        boolean IsTestFail = false;
        RestHighLevelClient client = connectESClient();

        BoolQueryBuilder bqb = null;

        if(DataType.equals("DMA")){
             bqb = new BoolQueryBuilder()
                    .filter(QueryBuilders.termQuery("dma","1468"))
                    .filter(QueryBuilders.termQuery("bsrankcategory","1316656373"));

        }else if(DataType.equals("National")){
             bqb = new BoolQueryBuilder()
                    .filter(QueryBuilders.termQuery("census_division","1007"))
                    .filter(QueryBuilders.termQuery("bsrankcategory","1316656373"));

        }


        SearchSourceBuilder searchSrc_SalesRankHis = new SearchSourceBuilder();
        searchSrc_SalesRankHis.query(bqb);
//        searchSrc_SalesRankHis.sort("period",SortOrder.DESC);
        searchSrc_SalesRankHis.trackTotalHits(true);

        //Execute Query
        SearchRequest searchRequest_SalesRankHis = new SearchRequest(getWeeksIndex(DataSource,DataType));
        searchRequest_SalesRankHis.source(searchSrc_SalesRankHis);
        SearchResponse srchResponse = client.search(searchRequest_SalesRankHis,RequestOptions.DEFAULT);

        String sResponse = srchResponse.toString();
        System.out.println(sResponse);

        String unit52_ES = getValue_ESResponse(sResponse,"units_52w");
        Double dbl_unit52_ES = Double.parseDouble(unit52_ES);
        double rd_dbl_unit52_ES = Math.round(dbl_unit52_ES);

        System.out.println("Unit52w ES : " + unit52_ES);

        //Get Unit52 Value from Database
        //Get Datasource Specific info
        String Sum_Src_ID = getSummarySourceTypeID(DataSource);

        //Get latest ppweek from Oracle
        DBUtil dbUtil = new DBUtil();
        ResultSet rsOracle = dbUtil.ConnectOracle("Select LOAD_ENVIRONMENT,MAX_PPWEEK from NPDREPORTMANAGER_" + env_DQ + ".VW_SUMMARY_LOAD_LOG Where module = 'bsc' And Summary_Source_Type_ID = " + Sum_Src_ID);

        String sum_SchemaName = null;
        int ppWeek = 0;
        if(rsOracle.next()){
            sum_SchemaName = rsOracle.getString(1);
            ppWeek = Integer.parseInt(rsOracle.getString(2));
            System.out.println(sum_SchemaName);
            TestLogger.logPass("Summary Source Schema : " + sum_SchemaName);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //get type DB table name
        String RegTable_DB = getRegionalTable(DataType);

        int weekValue = ppWeek - 51;
        String sQuery = null;
        if(DataType.equals("DMA")){
            sQuery = "Select SUM(proj_unitssold) from " + sum_SchemaName + "."+ RegTable_DB +" sales\n" +
                    "\tINNER JOIN " + sum_SchemaName + ".m_title_lookup_all book on book.itemnumber = sales.itemnumbersuppressed\n" +
                    "\tWhere ppweek >= " + weekValue + " and dma=1468  AND bsrankcategory=1316656373";
        }else if (DataType.equals("National")){
            sQuery = "Select SUM(proj_unitssold) from " + sum_SchemaName + "."+ RegTable_DB +" sales\n" +
                    "\tINNER JOIN " + sum_SchemaName + ".m_title_lookup_all book on book.itemnumber = sales.itemnumbersuppressed\n" +
                    "\tWhere ppweek >= " + weekValue + " and censusdivisionusa=1007  AND bsrankcategory=1316656373";

        }


        System.out.println(sQuery);
        ResultSet rs_sybase = dbUtil.ConnectSybase(sQuery);
        String unit52_DB=null;
        double rd_dbl_unit52_DB = 0.0;
        if(rs_sybase.next()){
            unit52_DB = rs_sybase.getString(1);
            double dbl_unit52_DB = Double.parseDouble(unit52_DB);
            rd_dbl_unit52_DB = Math.round(dbl_unit52_DB);

            System.out.println(rd_dbl_unit52_DB);
            TestLogger.logPass("Unit Database : " + rd_dbl_unit52_DB);
        }else{
            TestLogger.logFail("Summary Source Schema : " + sum_SchemaName);
            Assert.fail();
        }

        //Validation
        //UnitWeek52 Sales
        if(rd_dbl_unit52_ES==rd_dbl_unit52_DB){
            TestLogger.logPass("Unit52Weeks Sales Value " + rd_dbl_unit52_DB);
        }else{
            TestLogger.logFail("Unit52Weeks Sales Value Database : " + rd_dbl_unit52_DB + " Application : " + rd_dbl_unit52_ES);
            IsTestFail = true;
        }


        if(IsTestFail==true){
            Assert.fail();
        }

    }


    public String getRegionalTable(String RegionType){

        String RegType_TB = null;
        if(RegionType.equals("DMA")){
            RegType_TB = "w_aggr_dma_ytd";
        }else if(RegionType.equals("National")){
            RegType_TB = "w_aggr_cd_ytd";
        }

        return RegType_TB;

    }


}
