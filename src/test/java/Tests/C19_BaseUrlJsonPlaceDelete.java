package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C19_BaseUrlJsonPlaceDelete extends BaseUrlJsonPlaceholder{

    // https://jsonplaceholder.typicode.com/posts/50 endpointine
    //   bir DELETE request gonderdigimizde donen response'un
    //   status code'unun 200 oldugunu
    //   ve response body'sinin null oldugunu test edin.

    @Test
    public void test01(){

        //1-End-point hazirlama ve Request body olusturma
        specJsonPlaceholder.pathParams("pp1","posts","pp2","50");

        //2-Expected body hazirlama

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given()
                .when().spec(specJsonPlaceholder)
                .delete("{pp1}/{pp2}");

        //4-Assertion
        response.then().assertThat()
                .statusCode(200)
                .body("title", Matchers.nullValue());


    }


}
