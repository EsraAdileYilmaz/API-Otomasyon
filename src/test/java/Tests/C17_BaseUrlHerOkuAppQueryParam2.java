package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C17_BaseUrlHerOkuAppQueryParam2 extends BaseUrlHerokuapp {


    /*
    https://restful-booker.herokuapp.com/booking endpointine
    gerekli Query parametrelerini yazarak
        "firstname" degeri "Sally"
        ve "lastname" degeri "Jackson" olan
    rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
        donen response'un
        status code'unun 200 oldugunu
        ve "Sally Jackson" ismine sahip en az bir booking oldugunu test edin
 */


     @Test
    public void queryParams(){
         //1-End-point hazirlama ve Request body olusturma
         specHerokuapp.pathParam("pp1","booking").queryParams("firstname","Sally","lastname","Jackson");
         //2-Expected body hazirlama

         //3-Request gönderip, dönen response'i kaydetme
         Response response=given().when().spec(specHerokuapp)
                           .get("{pp1}");//query parametreleri gonderilmiyor
         //response.prettyPrint();

         //4-Assertion
         response.then().assertThat().statusCode(200)
                 .body("bookingid", Matchers.hasSize(1));//body icindeki herhangi bir key'den test yapilabilir



     }



}
