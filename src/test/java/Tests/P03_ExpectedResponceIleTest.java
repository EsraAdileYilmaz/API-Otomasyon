package Tests;

import BaseUrl.BaseUrlDummy;
import TestData.DummyDatas;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class P03_ExpectedResponceIleTest extends BaseUrlDummy {

    /*
    http://dummy.restapiexample.com/api/v1/update/21 url'ine asagidaki
     body'ye sahip bir PUT request gonderdigimizde donen response'un asagidaki gibi oldugunu test edin.
        Request Body
        {
            "status":"success",
            "data":{
                    "name":"Ahmet",
                    "salary":"1230",
                    "age":"44",
                    "id":40
                    }
        }
        Response Body
        {
        "status":"success",
        "data":{
            "status":"success",
            "data":{
                    "name":"Ahmet",
                    "salary":"1230",
                    "age":"44",
                    "id":40
                    }
               },
        "message":"Successfully! Record has been updated."
        }
             */

    @Test
    public void test01(){

        //1-End-point hazirlama ve Request body olusturma
        specDummy.pathParams("pp1","update","pp2","21");
        JSONObject requestBody= DummyDatas.requestBodyOlustur();

        //2-Expected body hazirlama
        JSONObject expectedBody=DummyDatas.responseBodyOlustur();
        System.out.println(expectedBody);

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specDummy).contentType(ContentType.JSON)
                          .when().body(requestBody.toString())
                          .put("{pp1}/{pp2}");
        response.prettyPrint();

        //4)Assertion
        JsonPath responseJsonPath=response.jsonPath();

        assertEquals(expectedBody.get("status"),responseJsonPath.get("status"));
        assertEquals(expectedBody.get("message"),responseJsonPath.get("message"));
        assertEquals(expectedBody.getJSONObject("data").getJSONObject("data").get("name"),responseJsonPath.get("data.data.name"));
        assertEquals(expectedBody.getJSONObject("data").getJSONObject("data").get("salary"),responseJsonPath.get("data.data.salary"));
        assertEquals(expectedBody.getJSONObject("data").getJSONObject("data").get("age"),responseJsonPath.get("data.data.age"));
        assertEquals(expectedBody.getJSONObject("data").getJSONObject("data").get("id"),responseJsonPath.get("data.data.id"));



    }

}
