package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuAppQueryParam1 extends BaseUrlHerokuapp {

    /*

     "https://restful-booker.herokuapp.com"/booking endpointine
     gerekli Query parametrelerini yazarak
         "firstname" degeri "Susan" olan rezervasyon oldugunu
     test edecek bir GET request gonderdigimizde,
     donen response'un
         status code'unun 200 oldugunu
         ve "Susan" ismine sahip 2 booking oldugunu test edin

 */


    @Test
    public void baseUrlQuery(){

      specHerokuapp.pathParam("pp1","booking").queryParam("firstname","Jim");

        Response response=given().when().spec(specHerokuapp).get("/{pp1}");

        response.prettyPrint();

        response.then().assertThat().statusCode(200)
                .body("bookingid",Matchers.hasItem(620));

    }

}
