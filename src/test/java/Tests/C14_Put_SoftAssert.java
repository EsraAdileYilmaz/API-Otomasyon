package Tests;

import BaseUrl.BaseUrlDummy;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C14_Put_SoftAssert extends BaseUrlDummy{

    /*
       http://dummy.restapiexample.com/api/v1/update/21 url'ine
       asagidaki body'ye sahip bir PUT request gonderdigimizde
       donen response'un asagidaki gibi oldugunu test edin.
       Request Body
               {
               "status": "success",
               "data": {
                   "name": "Ahmet",
                   "salary": "1230",
                   "age": "44",
                   "id": 40
                       }
              }
       Response Body=Expected body
               {
               "status": "success",
               "data": {
                   "status": "success",
                   "data": {
                       "name": "Ahmet",
                       "salary": "1230",
                       "age": "44",
                       "id": 40
                   }
               },
               "message": "Successfully! Record has been updated."
           }

*/
    @Test
    public void softAssertTest(){

        //1-End-point hazirlama ve Request body olusturma
        specDummy.pathParams("pp1","update", "pp2","21");

        JSONObject data=new JSONObject();
        data.put("name", "Ahmet");
        data.put("salary", "1230");
        data.put("age", "44");
        data.put("id", 40);

        JSONObject requestBody=new JSONObject();
        requestBody.put("status", "success");
        requestBody.put("data",data);

        //2-Expected body hazirla
        //Response body=Expected body
        JSONObject expectedBody=new JSONObject();
        expectedBody.put("status", "success");
        expectedBody.put("data",requestBody);
        expectedBody.put("message", "Successfully! Record has been updated.");

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().contentType(ContentType.JSON)
                          .when().spec(specDummy).body(requestBody.toString())
                          .put("{pp1}/{pp2}");

        //4-Assertion
        //Oncelikle response uzerindeki bilgileri kolay almak icin response'i JsonPath'e cast edelim
        JsonPath responseJsonPath=response.jsonPath();

        SoftAssert softAssert=new SoftAssert();
        //response=actual data,expectedBody=expected data
        softAssert.assertEquals(responseJsonPath.get("status"),expectedBody.get("status"));
        softAssert.assertEquals(responseJsonPath.get("data.status"),
                                expectedBody.getJSONObject("data").get("status"));
        softAssert.assertEquals(responseJsonPath.get("data.data.name"),
                                expectedBody.getJSONObject("data").getJSONObject("data").get("name"));
        softAssert.assertEquals(responseJsonPath.get("data.data.salary"),
                                expectedBody.getJSONObject("data").getJSONObject("data").get("salary"));
        softAssert.assertEquals(responseJsonPath.get("data.data.age"),
                                expectedBody.getJSONObject("data").getJSONObject("data").get("age"));
        softAssert.assertEquals(responseJsonPath.get("data.data.id"),
                                expectedBody.getJSONObject("data").getJSONObject("data").get("id"));

        softAssert.assertEquals(responseJsonPath.get("message"),expectedBody.get("message"));

        softAssert.assertAll();
    }


}
