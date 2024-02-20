package Tests;

import BaseUrl.BaseUrlJsonPlaceholder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C18_BaseUrlJsonPlaceholder extends BaseUrlJsonPlaceholder {

    // https://jsonplaceholder.typicode.com/posts/44 endpointine
    //   bir GET request gonderdigimizde donen response'un
    //   status code'unun 200 oldugunu
    //   ve "title" degerinin "optio dolor molestias sit" oldugunu test edin

   @Test
    public void test01(){
       //1-End-point hazirlama ve Request body olusturma
       specJsonPlaceholder.pathParams("pp1","posts","pp2","44");

       //2-Expected body hazirlama

       //3-Request gönderip, dönen response'i kaydetme
       Response response=given()
               .when().spec(specJsonPlaceholder)
               .get("{pp1}/{pp2}");

       //4-Assertion
       response.then().assertThat()
               .statusCode(200)
               .body("title", Matchers.equalTo("optio dolor molestias sit")) ;




   }

   /*
   //    https://jsonplaceholder.typicode.com/posts endpointine
   //    bir GET request gonderdigimizde donen response'un
   //    status code'unun 200 oldugunu ve Response'ta 100 kayit oldugunu test edin
    */

   @Test
    public void test02(){

       //1-End-point hazirlama ve Request body olusturma
       specJsonPlaceholder.pathParams("pp1","posts");

       //2-Expected body hazirlama

       //3-Request gönderip, dönen response'i kaydetme
       Response response=given()
               .when().spec(specJsonPlaceholder)
               .get("{pp1}");
       response.prettyPrint();

       //4-Assertion
       response.then().assertThat()
                                 .statusCode(200)
                                 .body("title", Matchers.hasSize(100)) ;//body'nin icindeki userId,id,title,body key'lerinden biriyle bu test yapilabilir

   }


}
