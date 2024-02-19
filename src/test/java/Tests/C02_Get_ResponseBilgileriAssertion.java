package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_ResponseBilgileriAssertion {
    /*
     https://restful-booker.herokuapp.com/booking/10 url'ine bir GET request
     gonderdigimizde donen Response'un,
     status code'unun 200,
     ve content type'inin application/json; charset=utf-8, ve Server isimli Header'in degerinin Cowboy,
     ve status Line'in HTTP/1.1 200 OK olduğunu assert ediniz.
     */


    @Test
    public void get01(){
        //1- End-point hazirlama ve Request body olusturma
        String url="https://restful-booker.herokuapp.com/booking/10";

        //2- Expected Data hazirlama

        //3- Request gönderip, dönen response'i kaydetme
        Response response=given().when().get(url);


        //4- Assertion

        response.then().assertThat()
                                    .statusCode(200)
                                    .contentType("application/json; charset=utf-8")
                                    .header("Server","Cowboy")
                                    .statusLine("HTTP/1.1 200 OK");
        //yukardaki testte junitteki hard assertle donen response'in oz bilgilerini assertion yaptik.
    }

}
