package Tests;

import BaseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuAppQueryParam1 extends BaseUrlHerokuapp {

    /*

       "https://restful-booker.herokuapp.com/booking" endpointine
       gerekli Query parametrelerini yazarak
       "firstname" degeri "Jim" olan rezervasyon oldugunu
       test edecek bir GET request gonderdigimizde,
       donen response'un
           status code'unun 200 oldugunu
           ve "Jim" ismine sahip 2 booking oldugunu test edin

 */


    @Test
    public void baseUrlQuery(){
        //1-End-point hazirlama ve Request body olusturma
        specHerokuapp.pathParam("pp1","booking").queryParam("firstname","Jim");

        //2-Expected body hazirlama

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().when().spec(specHerokuapp).get("/{pp1}");

        response.prettyPrint();

        //4-Assertion
        response.then().assertThat().statusCode(200)
                .body("bookingid",Matchers.hasItem(620));//burada degerler surekli degistigi icin toplam bookingid'nin 620 olmasini test ettik

    }

}
