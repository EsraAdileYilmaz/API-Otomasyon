package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import TestData.JsonPlaceDatas;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C25_DeSerializationPut extends BaseUrlJsonPlaceholder{

    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine
    asagidaki body'e sahip bir PUT request yolladigimizda
    donen response'in response body'sinin asagida verilen ile ayni oldugunu test ediniz

    Request Body

    {
        "title":"Ahmet",
        "body":"Merhaba",
        "userId":10,
        "id":70
    }

    Expected Response Body:

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
        //Request body'sini Map olarak olusturalim
        Map<String,Object> requestBodyMap= JsonPlaceDatas.bodyOlusturMap();
        //System.out.println("request body: "+requestBodyMap);


        //2-Expected body hazirlama
        Map<String,Object> expectedDataMap=JsonPlaceDatas.bodyOlusturMap();
        //soruda request body ve response=(expected) birebir ayni oldugu icin ayni methodu bodyOlusturMap() kullandik

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                          .when().body(requestBodyMap)
                          .put("{pp1}/{pp2}");
        //response.prettyPrint();

        //4)Assertion
        //expected response body <=====> response
        //       Map                     response
        //Assertion yapabilmemiz icin response'i Map'e cevirmemiz gerekir.Buna da De-Serialization denir.
        Map<String,Object> responseMap=response.as(HashMap.class);//response'i map'e cevirdik
        //System.out.println("responseMap: "+responseMap);


        /*
        request body: {id=70.0, title=Ahmet, body=Merhaba, userId=10.0}
        responseMap: {id=70.0, title=Ahmet, body=Merhaba, userId=10.0}
        sayisal degerler double olarak dondugu icin bizimde bizde body olustururken sayisal degerleri double olarak girmeliyiz
         */

        // Map<String,Object> expectedDataMap <========>Map<String,Object> responseMap karsilastiracagiz

        assertEquals(expectedDataMap.get("title"),responseMap.get("title"));
        assertEquals(expectedDataMap.get("body"),responseMap.get("body"));
        assertEquals(expectedDataMap.get("userId"),responseMap.get("userId"));
        assertEquals(expectedDataMap.get("id"),responseMap.get("id"));



    }

}
