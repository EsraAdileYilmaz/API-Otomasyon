package Tests;

import BaseUrl.BaseUrlDummy;
import TestData.DummyDatas;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;


public class C24_TestDummyGet extends BaseUrlDummy {

    /*
     http://dummy.restapiexample.com/api/v1/employee/3 url'ine
     bir GET request gonderdigimizde
     donen response'un status code'unun 200,
     content Type'inin application/json
     ve body'sinin asagidaki gibi oldugunu test edin.

     Expected=Response Body
       {
        "status":"success",
        "data":{
                "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                },
        "message":"Successfully! Record has been fetched."
       }
    */


    @Test
    public void test01(){

        //1-End-point hazirlama ve Request body olusturma
        specDummy.pathParams("pp1","employee","pp2","3");

        //2-Expected body hazirlama
        JSONObject expectedBody=DummyDatas
                .expectedDataOlustur(3,"Ashton Cox",86000,66,"");

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specDummy)
                           .when()
                           .get("{pp1}/{pp2}");

        //4)Assertion
        JsonPath responseJsonPath=response.jsonPath();

        assertEquals(DummyDatas.basariliStatusCode,response.getStatusCode());
        assertEquals(DummyDatas.contentType,response.getContentType());
        assertEquals(expectedBody.get("status"),responseJsonPath.get("status"));
        assertEquals(expectedBody.getJSONObject("data").get("id"),responseJsonPath.get("data.id"));
        assertEquals(expectedBody.getJSONObject("data").get("employee_name"),responseJsonPath.get("data.employee_name"));
        assertEquals(expectedBody.getJSONObject("data").get("employee_salary"),responseJsonPath.get("data.employee_salary"));
        assertEquals(expectedBody.getJSONObject("data").get("employee_age"),responseJsonPath.get("data.employee_age"));
        assertEquals(expectedBody.getJSONObject("data").get("profile_image"),responseJsonPath.get("data.profile_image"));
        assertEquals(expectedBody.get("message"),responseJsonPath.get("message"));


        //Eger failed oldugunda statusCode=429 ise siteye asiri yuklenme var demektir
    }



}
