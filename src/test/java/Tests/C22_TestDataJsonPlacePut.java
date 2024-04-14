package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import TestData.JsonPlaceDatas;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C22_TestDataJsonPlacePut extends BaseUrlJsonPlaceholder {

    /*
   https://jsonplaceholder.typicode.com/posts/70 url'ine
   asagidaki body'e sahip bir PUT request yolladigimizda
   donen response'in
       status kodunun 200,
       content type'inin "application/json; charset=utf-8",
       Connection header degerinin "keep-alive"
       ve response body'sinin asagida verilen ile ayni oldugunu test ediniz
    Request Body
       {
       "title":"Ahmet",
       "body":"Merhaba",
       "userId":10,
       "id":70
       }
   Response body (=Expected Body) :
       {
       "title":"Ahmet",
       "body":"Merhaba",
       "userId":10,
       "id":70
       }
*/
    @Test
    public void test01(){
        //1-End-point hazirlama ve Request body olusturma
       specJsonPlaceholder.pathParams("pp1","posts","pp2","70");

        //2-Expected body hazirla
       //request body olusturmaliyiz
        JSONObject requestBody= JsonPlaceDatas.jsonDataOlustur(10,70,"Ahmet","Merhaba");//Gonderilen
        JSONObject expectedBody=JsonPlaceDatas.jsonDataOlustur(10,70,"Ahmet","Merhaba");//Beklenen
        //expectedBody.put(); yapip yeni bir data ekleyebiliriz

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().contentType(ContentType.JSON)
                          .when().spec(specJsonPlaceholder).body(requestBody.toString())
                          .put("{pp1}/{pp2}");

        //4)Assertion
        JsonPath responseJsonpath=response.jsonPath();//response bilgileri kolay alinsin diye response'i jsonpath'e cast ettik.

        assertEquals(JsonPlaceDatas.basariliStatusCode,response.getStatusCode());
        assertEquals(JsonPlaceDatas.contentType,response.getContentType());
        assertEquals(JsonPlaceDatas.header,response.getHeader("Connection"));
        assertEquals(expectedBody.getInt("userId"),responseJsonpath.getInt("userId"));
        assertEquals(expectedBody.getInt("id"),responseJsonpath.getInt("id"));
        assertEquals(expectedBody.getString("title"),responseJsonpath.getString("title"));
        assertEquals(expectedBody.getString("body"),responseJsonpath.getString("body"));
        //expectedBody verileri farkli bir class'tan geldigi icin getString,getInt vb tarzinda values'lerin data type'lari belirtilmelidir.

    }

}
