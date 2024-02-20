package Tests;

import BaseUrl.BaseUrlHerokuapp;
import TestData.HerokuappDatas;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class C23_TestDataHerokuAppPost extends BaseUrlHerokuapp{


    /*
        https://restful-booker.herokuapp.com/booking url'ine
        asagidaki body'ye sahip bir POST request gonderdigimizde
        donen response'un id haric asagidaki gibi oldugunu test edin.
        Request body
               {
                "firstname" : "Ahmet",
                "lastname" : "Bulut",
                "totalprice" : 500,
                "depositpaid" : false,
                "bookingdates" : {
                        "checkin" : "2021-06-01",
                        "checkout" : "2021-06-10"
                        },
                "additionalneeds" : "wi-fi"
                }

       Response body (=Expected body)
                {
                 "bookingid":24,
                 "booking":{
                    "firstname":"Ahmet",
                    "lastname":"Bulut",
                    "totalprice":500,
                    "depositpaid":false,
                    "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                    },
                    "additionalneeds":"wi-fi"
                            }
                  }
 */


    @Test
    public void test01(){
        //1-End-point hazirlama ve Request body olusturma
        specHerokuapp.pathParam("pp1","booking");

        //2-Expected body hazirla
        //request body olusturmaliyiz
        JSONObject requestBody= HerokuappDatas.jsonHerokuappRequestBodyOlustur();
        JSONObject expectedBody=HerokuappDatas.jsonexpectedBodyOlustur();

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().contentType(ContentType.JSON)
                .when().spec(specHerokuapp).body(requestBody.toString())
                .post("{pp1}");

        //4)Assertion
        JsonPath responseJsonpath=response.jsonPath();
        assertEquals(expectedBody.getJSONObject("booking").get("firstname"),
                     responseJsonpath.get("booking.firstname"));
        assertEquals(expectedBody.getJSONObject("booking").get("lastname"),
                     responseJsonpath.get("booking.lastname"));
        assertEquals(expectedBody.getJSONObject("booking").get("totalprice"),
                     responseJsonpath.get("booking.totalprice"));
        assertEquals(expectedBody.getJSONObject("booking").get("depositpaid"),
                     responseJsonpath.get("booking.depositpaid"));
        assertEquals(expectedBody.getJSONObject("booking").get("additionalneeds"),
                     responseJsonpath.get("booking.additionalneeds"));
        assertEquals(expectedBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                     responseJsonpath.get("booking.bookingdates.checkin"));
        assertEquals(expectedBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                     responseJsonpath.get("booking.bookingdates.checkout"));


    }


}
