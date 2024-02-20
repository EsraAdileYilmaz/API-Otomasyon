package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import TestData.JsonPlaceDatas;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C20_TestDataKullanimiJsonPlace extends BaseUrlJsonPlaceholder{

   /*
   https://jsonplaceholder.typicode.com/posts/22 url'ine
   bir GET request yolladigimizda
   donen response'in
       status kodunun 200
       ve response body'sinin asagida verilen ile ayni oldugunu test ediniz

   Response body :(=>Expected body)
       {
       "userId":3,
       "id":22,
       "title":"dolor sint quo a velit explicabo quia nam",
       "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
                    um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
       }
  */

    //1-End-point hazirlama ve Request body olusturma
    //2-Expected body hazirla
    //3-Request gönderip, dönen response'i kaydetme
    //4)Assertion

    @Test
    public void test01(){
       //1-End-point hazirlama ve Request body olusturma
       specJsonPlaceholder.pathParams("pp1","posts","pp2","22");

       //2-Expected body hazirla
       JSONObject expectedBody= JsonPlaceDatas.expectedDataOlustur22();
       //JsonPlaceDatas class'indan expectedDataOlustur22() methodunu call ederek
       // ordaki verileri expectedBody'nin icine tasidik.

       //3-Request gönderip, dönen response'i kaydetme
       Response response=given()
              .when().spec(specJsonPlaceholder)
              .get("{pp1}/{pp2}");

       //4)Assertion
        JsonPath responseJsonPath=response.jsonPath();//response bilgilerini kolay elde etmek icin response'u jsonpath'e cast ediyoruz.

        assertEquals(JsonPlaceDatas.basariliStatusCode,response.getStatusCode());
        assertEquals(expectedBody.getInt("userId"),responseJsonPath.getInt("userId"));//farkli bir class'tan getirildigi icin variable cesidini belirtmeliyiz
        assertEquals(expectedBody.getInt("id"),responseJsonPath.getInt("id"));
        assertEquals(expectedBody.getString("title"),responseJsonPath.getString("title"));
        assertEquals(expectedBody.getString("body"),responseJsonPath.getString("body"));
        //expectedBody verileri farkli bir class'tan geldigi icin getString,getInt vb tarzinda values'lerin data type'lari belirtilmelidir.


   }


}
