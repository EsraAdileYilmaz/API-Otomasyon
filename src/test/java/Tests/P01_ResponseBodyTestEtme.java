package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import TestData.JsonPlaceDatas;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class P01_ResponseBodyTestEtme extends BaseUrlJsonPlaceholder {

    /*
   https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki
   Json formatindaki body ile bir PUT request gonderdigimizde
           {
           "title":"Elif",
           "body":"Merhaba dünya",
           "userId":10,
           "id":70
           }
   donen Response'un,
       status code'unun 200,
       ve content type'inin application/json; charset=utf-8,
       ve Server isimli Header'in degerinin cloudflare,
       ve status Line'in HTTP/1.1 200 OK
       ve title'in "Elif"
       ve body attribute'unun "Merhaba dünya"
       ve userId'nin 10 oldugunu test edin
 */
    @Test
    public void put01(){

        //1-End-point hazirlama ve Request body olusturma
        specJsonPlaceholder.pathParams("pp1","posts","pp2","70");
        JSONObject requestBody= JsonPlaceDatas.jsonDataOlustur(10,70,"Elif","Merhaba dünya");
        System.out.println(requestBody);
        //2-Expected body hazirlama

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                          .when().body(requestBody.toString())
                          .put("{pp1}/{pp2}");//bu bizim actual datamiz
        response.prettyPrint();

        //4)Assertion
        JsonPath responceJsonPath=response.jsonPath();
        assertEquals(JsonPlaceDatas.basariliStatusCode,response.getStatusCode());
        assertEquals(JsonPlaceDatas.contentType,response.getContentType());
        assertEquals(JsonPlaceDatas.header2,response.header("Server"));
        assertEquals(JsonPlaceDatas.statusLine,response.getStatusLine());
        assertEquals(requestBody.get("title"),responceJsonPath.get("title"));
        assertEquals(requestBody.get("body"),responceJsonPath.get("body"));
        assertEquals(requestBody.get("userId"),responceJsonPath.get("userId"));


    }
}
