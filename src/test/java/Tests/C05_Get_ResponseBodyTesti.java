import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class C05_Get_ResponseBodyTesti {

     //   https://jsonplaceholder.typicode.com/posts/44 url'ine bir GET request yolladigimizda
     //   donen Response'in
     //   status code'unun 200,
    //   ve content type'inin application/json; charset=utf-8,
    //   ve response body'sinde bulunan userId'nin 5,
    //   ve response body'sinde bulunan title'in "optio dolor molestias sit" oldugunu test edin.

    @Test
    public void responseBodyTest(){

        //  1- End-point hazirlama ve Request body olusturma
        String url="https://jsonplaceholder.typicode.com/posts/44";

        //2- Expected Data hazirlama

        // 3- Request gönderip, dönen response'i kaydetme
        Response response=given().when().get(url);
        //44 nolu postun tum bilgilerinin getir ve response objesine ata.

        //4_Assertion
        response.then().assertThat()
                                   .statusCode(200)
                                   .contentType("application/json; charset=utf-8")
                                    .body("userId",equalTo(5))
                                    .body("title",equalTo("optio dolor molestias sit"));


    }

}
