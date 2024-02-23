package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import TestData.JsonPlaceDatas;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class P05_TestDataKullanimi extends BaseUrlJsonPlaceholder {


    /*
   https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET
   request yolladigimizda donen response'in status kodunun 200 ve
   response body'sinin asagida verilen ile ayni oldugunu test ediniz
      Response body :
       {
       "userId":3,
       "id":22,
       "title":"dolor sint quo a velit explicabo quia nam",
       "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
       um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
     }
     */

    @Test
    public void test01(){
        //1-End-point hazirlama ve Request body olusturma
        specJsonPlaceholder.pathParams("pp1","posts","pp2","22");

        //2-Expected body hazirlama
        JSONObject expectedData= JsonPlaceDatas.expectedDataOlustur22();

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specJsonPlaceholder)
                           .when()
                           .get("{pp1}/{pp2}");

        //4-Assertion
        JsonPath responseJsonPath=response.jsonPath();

        assertEquals(JsonPlaceDatas.basariliStatusCode,response.getStatusCode());
        assertEquals(expectedData.get("userId"),responseJsonPath.get("userId"));
        assertEquals(expectedData.get("id"),responseJsonPath.get("id"));
        assertEquals(expectedData.get("title"),responseJsonPath.get("title"));
        assertEquals(expectedData.get("body"),responseJsonPath.get("body"));


    }
}
