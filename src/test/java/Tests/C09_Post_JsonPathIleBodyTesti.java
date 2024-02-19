import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
//import static org.hamcrest.core.IsEqual.equalTo; // bende bu cikiyor
import static org.hamcrest.Matchers.equalTo; //bunu hocadan aldim

public class C09_Post_JsonPathIleBodyTesti {

    /*
      https://restful-booker.herokuapp.com/booking url'ine asagidaki body'ye sahip
      bir POST request gonderdigimizde
      {
         "firstname" : "Ahmet",
         "lastname" : "Bulut",
         "totalprice" : 500,
         "depositpaid" : false,
         "bookingdates" :{
                     "checkin" : "2021-06-01",
                     "checkout" : "2021-06-10"
                     },
         "additionalneeds" : "wi-fi"
      }

      donen Response'un,
      status code'unun 200,
      ve content type'inin application/json,
      ve response body'sindeki
      "firstname"in,"Ahmet",
      ve "lastname"in, "Bulut",
      ve "totalprice"in,500,
      ve "depositpaid"in,false,
      ve "checkin" tarihinin 2021-06-01 ve "checkout" tarihinin 2021-06-10
      ve "additionalneeds"in,"wi-fi"
      oldugunu test edin


 */

   @Test
   public void jsonPathIleBodyTesti(){

       //1- End-point hazirlama ve Request body olusturma
       String url="https://restful-booker.herokuapp.com/booking";


       JSONObject bookingDate=new JSONObject();
       bookingDate.put("checkin","2021-06-01");
       bookingDate.put("checkout", "2021-06-10");

       JSONObject requestBody=new JSONObject();
       requestBody.put("firstname","Ahmet");
       requestBody.put("lastname","Bulut");
       requestBody.put("totalprice",500);
       requestBody.put("depositpaid",false);
       requestBody.put("bookingdates",bookingDate);
       requestBody.put("additionalneeds","wi-fi");

       // 2- Expected Data hazirlama

       //3- Request gönderip, dönen response'i kaydetme

       Response response=given().contentType(ContentType.JSON)
                         .when().body(requestBody.toString())
                         .post(url);
       response.prettyPrint();

       /*DONEN RESPONSE=>
       {
          "bookingid": 4661,
          "booking": {
                    "firstname": "Ahmet",
                    "lastname": "Bulut",
                    "totalprice": 500,
                    "depositpaid": false,
                    "bookingdates": {
                                 "checkin": "2021-06-01",
                                 "checkout": "2021-06-10"
                                   },
                    "additionalneeds": "wi-fi"
                   }
        }
        */

        //4- Assertion
       response.then().assertThat()
               .statusCode(200)
               .contentType(ContentType.JSON)
               .body("booking.firstname", equalTo("Ahmet"),
                       "booking.lastname",equalTo("Bulut"),
                             "booking.totalprice",equalTo(500),
                             "booking.depositpaid",equalTo(false),
                             "booking.bookingdates.checkin",equalTo("2021-06-01"),
                             "booking.bookingdates.checkout",equalTo("2021-06-10"),
                             "booking.additionalneeds",equalTo("wi-fi"));

   }



}




