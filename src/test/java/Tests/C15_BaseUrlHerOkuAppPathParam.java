package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C15_BaseUrlHerOkuAppPathParam extends BaseUrlHerokuapp {

    //  https://restful-booker.herokuapp.com/booking endpointine
    //    bir GET request gonderdigimizde
    //    donen response'un
    //   status code'unun 200 oldugunu
    //   ve "bookingid" si 258 olan rezervasyonun var oldugunu test edin

    @Test
    public void test01(){
        //1-End-point hazirlama ve Request body olusturma
       specHerokuapp.pathParam("pp1","booking");

       //2-Expected body hazirlama

       //3-Request gönderip, dönen response'i kaydetme
        Response response=given().when().spec(specHerokuapp).get("/{pp1}");
        response.prettyPrint();

      //4-Assertion
        JsonPath responseJsonPath=response.jsonPath();//response'dan donen bilgileri kolay elde etmek icin jsonpath e cast ediyoruz
        System.out.println(responseJsonPath.getList("bookingid").size());//burasi bize donen listenin uzunlugunu verir

        response.then().assertThat().statusCode(200)
                .body("bookingid", Matchers.hasItem(258));
        //bu assertion cesidi junit ile calisiyor.buyuzden @Test annotation junitten secilmeli.
    }






}
