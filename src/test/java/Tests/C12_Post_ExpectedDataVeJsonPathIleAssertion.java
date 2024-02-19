package Tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {

    /*
        https://restful-booker.herokuapp.com/booking url'ine
        asagidaki body'ye sahip bir POST request gonderdigimizde
        donen response'un bookindid haric asagidaki gibi oldugunu test edin.

        Request body
        {
        "firstname" : "Hasan",
        "lastname" : "Yagmur",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
            "checkin" : "2021-06-01",
            "checkout" : "2021-06-10"
            },
        "additionalneeds" : "wi-fi"
        }

        Expected Response Body
        {
        "bookingid":24,
        "booking":{
            "firstname":"Hasan",
            "lastname":"Yagmur",
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
        String url="https://restful-booker.herokuapp.com/booking";


        JSONObject bookingDates=new JSONObject();
        bookingDates.put("checkin","2021-06-01");
        bookingDates.put("checkout","2021-06-10");

        JSONObject requestBody=new JSONObject();
        requestBody.put("firstname","Hasan");
        requestBody.put("lastname","Yagmur");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingDates);
        requestBody.put("additionalneeds","wi-fi");

        //2-Expected body hazirla
        //expected response body olusturuyoruz
        JSONObject expectedData=new JSONObject();
        expectedData.put("bookingid",24);
        expectedData.put("booking",requestBody);

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().contentType(ContentType.JSON)
                          .when().body(requestBody.toString())
                          .post(url);
        response.prettyPrint();
        //4-Assertion ==>
        JsonPath responseJsonPath=response.jsonPath();//burda ONCELIKLE response objeyi json path formatina cevirdik

        assertEquals(expectedData.getJSONObject("booking").get("firstname"),responseJsonPath.get("booking.firstname"));
        assertEquals(expectedData.getJSONObject("booking").get("lastname"),responseJsonPath.get("booking.lastname"));
        assertEquals(expectedData.getJSONObject("booking").get("totalprice"),responseJsonPath.get("booking.totalprice"));
        assertEquals(expectedData.getJSONObject("booking").get("depositpaid"),responseJsonPath.get("booking.depositpaid"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),responseJsonPath.get("booking.bookingdates.checkin"));
        assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),responseJsonPath.get("booking.bookingdates.checkout"));
        assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"),responseJsonPath.get("booking.additionalneeds"));


    }






}
