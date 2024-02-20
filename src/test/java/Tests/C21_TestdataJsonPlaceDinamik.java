package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import TestData.JsonPlaceDatas;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C21_TestdataJsonPlaceDinamik extends BaseUrlJsonPlaceholder {

    /*
        https://jsonplaceholder.typicode.com/posts/40 url'ine
        bir GET request yolladigimizda
        donen response'in
              status kodunun 200
              ve response body'sinin asagida verilen ile ayni oldugunu test ediniz

       Response body :
      {
       "userId":4,
       "id":40,
       "title":"enim quo cumque",
       "body":"ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam"
      }
     */

    @Test
    public void test01(){
        //1-End-point hazirlama ve Request body olusturma
        specJsonPlaceholder.pathParams("pp1","posts","pp2","40");

        //2-Expected body hazirlama
        JSONObject expectedBody= JsonPlaceDatas.jsonDataOlustur(4,40,"enim quo cumque",
                "ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam");

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given()
                .when().spec(specJsonPlaceholder)
                .get("{pp1}/{pp2}");

        //4)Assertion
        JsonPath responseJsonPath=response.jsonPath();

        assertEquals(JsonPlaceDatas.basariliStatusCode,response.getStatusCode());
        assertEquals(expectedBody.getInt("userId"),responseJsonPath.getInt("userId"));
        assertEquals(expectedBody.getInt("id"),responseJsonPath.getInt("id"));
        assertEquals(expectedBody.getString("title"),responseJsonPath.getString("title"));
        assertEquals(expectedBody.getString("body"),responseJsonPath.getString("body"));
        //expectedBody verileri farkli bir class'tan geldigi icin getString,getInt vb tarzinda values'lerin data type'lari belirtilmelidir.



    }

}
