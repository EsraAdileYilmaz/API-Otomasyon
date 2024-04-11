package Tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C11_Get_ExpectedDataOlusturma {

 /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine
    bir GET request yolladigimizda
    donen response body'sinin asagida verilen ile ayni oldugunu test ediniz

    Response body :(Expected body)

    {
    "userId":3,
    "id":22,
    "title":"dolor sint quo a velit explicabo quia nam",
    "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
    }

 */

    @Test
    public void expectedBodyTesti(){
        //1-End-point hazirlama ve Request body olusturma
        String url="https://jsonplaceholder.typicode.com/posts/22";

        //2-Expected body hazirla
        JSONObject expectedBody=new JSONObject();
        expectedBody.put("userId",3);
        expectedBody.put("id",22);
        expectedBody.put("title","dolor sint quo a velit explicabo quia nam");
        expectedBody.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().when().get(url);

        //4-Assertion
        JsonPath responseJsonpath=response.jsonPath();//burda cevabi json objesine cevirdik
        assertEquals(expectedBody.get("userId"),responseJsonpath.get("userId"));
        assertEquals(expectedBody.get("id"),responseJsonpath.get("id"));
        assertEquals(expectedBody.get("title"),responseJsonpath.get("title"));
        assertEquals(expectedBody.get("body"),responseJsonpath.get("body"));
        //json obje ile json path'i karsilastirmis oluyoruz.Ama formatlari ayni.Formatlar uzerinden karsilastirma yapiyoruz



    }

}
