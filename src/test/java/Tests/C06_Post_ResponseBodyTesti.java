package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class C06_Post_ResponseBodyTesti {

    /*
       https://jsonplaceholder.typicode.com/posts url'ine
       asagidaki body ile bir POST request gonderdigimizde

        {
        "title":"API",
        "body":"API ogrenmek ne guzel",
        "userId":10,
        }

        donen Response'un,
        status code'unun 201,
        ve content type'inin application/json; charset=utf-8,
        ve Response Body'sindeki,
         "title"'in "API" oldugunu
        "userId" degerinin 100'den kucuk oldugunu
        "body" nin "API" kelimesi icerdigini test edin.
 */

    @Test
    public void postResponseBodyTesti(){
        //1- End-point hazirlama ve Request body olusturma
        String url="https://jsonplaceholder.typicode.com/posts";

        JSONObject requestBody=new JSONObject();
        requestBody.put("title","API");
        requestBody.put("body","API ogrenmek ne guzel");
        requestBody.put("userId",10);

        //2- Expected Data hazirlama

        //3- Request gönderip, dönen response'i kaydetme
        Response response=given().contentType(ContentType.JSON)
                          .when().body(requestBody.toString())
                          .post(url);

        // 4- Assertion
        response.then().assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", equalTo("API"))
                .body("userId",lessThan(100))
                .body("body",containsString("API"));


    }
}
