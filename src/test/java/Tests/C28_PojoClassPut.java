package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import Pojos.PojoJsonPlaceholder;
import TestData.JsonPlaceDatas;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C28_PojoClassPut extends BaseUrlJsonPlaceholder {

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
        Response body : // expected data
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
        PojoJsonPlaceholder requestBodyPojo=new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);
        //request body'mizi PojoJsonPlaceholder class'indan olusturduk


        //2-Expected body hazirlama
        PojoJsonPlaceholder expectedBodyPojo=new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);
        //request body ve expected body ayni formatta oldugu icin ayni pojo class'indan hazirladik

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                          .when().body(requestBodyPojo)
                          .put("{pp1}/{pp2}");
        response.prettyPrint();

        //4)Assertion
        //expected data=pojo <======>response=response
        //Oncelikle response'imizi pojo'ya cevirmeliyiz.
        //Bunu yapmak icin response'i olusturdugumuz pojo class'inin bir objesi haline getirecegiz.
        PojoJsonPlaceholder responsePojo=response.as(PojoJsonPlaceholder.class);
        //bu kod ile (PojoJsonPlaceholder.class) bu class'in bir objesi haline dondurdugu response'i getirecek

        //simdi assertion yapabiliriz
        //expectedBodyPojo <====>  responsePojo her ikisininde data type'i pojo oldu
        System.out.println(expectedBodyPojo);
        System.out.println(responsePojo);

        //status kodunun 200 oldugunu
        assertEquals(JsonPlaceDatas.basariliStatusCode,response.getStatusCode());

        //content type'inin "application/json; charset=utf-8" oldugunu
        assertEquals(JsonPlaceDatas.contentType,response.getContentType());

        //Connection header degerinin "keep-alive"
        assertEquals(JsonPlaceDatas.header,response.header("Connection"));

        //response body'sinin asagida verilen ile ayni oldugunu test ediniz
        assertEquals(expectedBodyPojo.getTitle(),responsePojo.getTitle());
        assertEquals(expectedBodyPojo.getBody(),responsePojo.getBody());
        assertEquals(expectedBodyPojo.getUserId(),responsePojo.getUserId());
        assertEquals(expectedBodyPojo.getId(),responsePojo.getId());

    }

}
