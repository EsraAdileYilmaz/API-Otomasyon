package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P04_BaseUrlKullanimi extends BaseUrlJsonPlaceholder {
    //Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin

    /*
        1-  https://jsonplaceholder.typicode.com/posts endpointine bir GET
         request gonderdigimizde donen response'un status code'unun 200 oldugunu
         ve Response'ta ("title" degerlerinin) 100 kayit oldugunu test edin.
        */

    /*
        2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET
            request gonderdigimizde donen response'un status code'unun 200 oldugunu
            ve "title" degerinin "optio dolor molestias sit" oldugunu test edin
         */

     /*
        3- https://jsonplaceholder.typicode.com/posts/50 endpointine bir DELETE
            request gonderdigimizde donen response'un status code'unun 200 oldugunu ve
            response body'sinin null oldugunu test edin
         */

    @Test
    public void get01(){
        //1-End-point hazirlama ve Request body olusturma
        specJsonPlaceholder.pathParam("pp1","posts");
        //2-Expected body hazirlama

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specJsonPlaceholder)
                          .when()
                          .get("{pp1}");

        response.then().assertThat()
                .statusCode(200)
                .body("title", hasSize(100));//bu assertion body'si olmayanlarda kullaniliyor


    }


    @Test
    public void get02(){
        //1-End-point hazirlama ve Request body olusturma
        specJsonPlaceholder.pathParams("pp1","posts","pp2","44");
        //2-Expected body hazirlama

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specJsonPlaceholder)
                .when()
                .get("{pp1}/{pp2}");

        response.then().assertThat()
                .statusCode(200)
                .body("title",equalTo("optio dolor molestias sit"));


    }

    @Test
    public void delete01(){
        //1-End-point hazirlama ve Request body olusturma
        specJsonPlaceholder.pathParams("pp1","posts","pp2","50");
        //2-Expected body hazirlama

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specJsonPlaceholder)
                .when()
                .delete("{pp1}/{pp2}");



        response.then().assertThat()
                .statusCode(200)
                .body("id",nullValue());

    }


}
