package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C15_BaseUrlHerOkuAppPathParam extends BaseUrlHerokuapp {

    //  https://restful-booker.herokuapp.com/booking endpointine
    //    bir GET request gonderdigimizde
    //    donen response'un
    //   status code'unun 200 oldugunu
    //   ve Response'ta 12 booking oldugunu test edin

    @Test
    public void test01(){
        //1-End-point hazirlama ve Request body olusturma
       specHerokuapp.pathParam("pp1","booking");

       //2-Expected body hazirlama

       //3-Request gönderip, dönen response'i kaydetme
        Response response=given().when().spec(specHerokuapp).get("/{pp1}");

      //4-Assertion
        response.then().assertThat().statusCode(200)
                .body("bookingid", Matchers.hasItem(51));
        //bu assertion cesidi junit ile calisiyor.buyuzden @Test annotation junitten secilmeli.
    }






}
