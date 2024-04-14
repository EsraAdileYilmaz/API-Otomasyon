package Tests;

import BaseUrl.BaseUrlHerokuapp;
import Pojos.PojoHerokuappBookingdates;
import Pojos.PojoHerokuappRequestBody;
import Pojos.PojoHerokuappResponseBody;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C29_PojoClassPost extends BaseUrlHerokuapp {

        /*
       https://restful-booker.herokuapp.com/booking url'ine
       asagidaki body'ye sahip bir POST request gonderdigimizde
       donen response'un asagidaki gibi oldugunu test edin.
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
                   Response Body=Expected data
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
                    ,
                    "additionalneeds":"wi-fi"
                }
          */



    @Test
    public void test01(){

        //1-End-point hazirlama ve Request body olusturma
        specHerokuapp.pathParam("pp1","booking");
        PojoHerokuappBookingdates bookingdatesPojo=new
                PojoHerokuappBookingdates("2021-06-01","2021-06-10");
        PojoHerokuappRequestBody requestBodyPojo=new
                PojoHerokuappRequestBody("Ahmet","Bulut",500,false,bookingdatesPojo,"wi-fi");

        //2-Expected body hazirlama
        //Expected Body olusturmak icin herbir pojo class'a ulasmamiz lazim,buyuzden olusturdugumuz pojo class'lardan tekrar olusturuyoruz.
        bookingdatesPojo=new
                PojoHerokuappBookingdates("2021-06-01","2021-06-10");
        PojoHerokuappRequestBody bookingPojo=new
                PojoHerokuappRequestBody("Ahmet","Bulut",500,false,bookingdatesPojo,"wi-fi");
        PojoHerokuappResponseBody expectedResponseBodyPojo=new
                PojoHerokuappResponseBody(24,bookingPojo);
        //System.out.println(expectedResponseBodyPojo);
        /*
        PojoHerokuappResponseBody{
        bookingid=24,
        booking=PojoHerokuappRequestBody
             {
               firstname='Ahmet',
               lastname='Bulut',
               totalprice=500,
               depositpaid=false,
               bookingdates=PojoHerokuappBookingdates
                      {checkin='2021-06-01',
                      checkout='2021-06-10'},
              additionalneeds='wi-fi'
              }
        }


         */

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specHerokuapp).contentType(ContentType.JSON)
                          .when().body(requestBodyPojo)
                          .post("{pp1}");
       // response.prettyPrint();


        //4)Assertion
        PojoHerokuappResponseBody responseBodyPojo=response.as(PojoHerokuappResponseBody.class);
        //expectedResponseBodyPojo nun olusturuldugu class'a response cevrilir.cunku assertion da karsilastirma yaparken ikiside ayni class'tan olmali.
        //Yani Assertion'da hangi class'daki ile karsilastirma yapacaksan response'i o class'daki pojo'dan bir obje haline getiriyorsun.
        //expectedResponseBodyPojo <=======> responseBodyPojo
        //System.out.println(responseBodyPojo);
        /*
            PojoHerokuappResponseBody{
            bookingid=4739,
            booking=PojoHerokuappRequestBody{
                     firstname='Ahmet',
                     lastname='Bulut',
                     totalprice=500,
                     depositpaid=false,
                     bookingdates=PojoHerokuappBookingdates{
                                          checkin='2021-06-01',
                                          checkout='2021-06-10'},
                     additionalneeds='wi-fi'}}
         */


        assertEquals(expectedResponseBodyPojo.getBooking().getFirstname(),
                     responseBodyPojo.getBooking().getFirstname());
        assertEquals(expectedResponseBodyPojo.getBooking().getLastname(),
                     responseBodyPojo.getBooking().getLastname());
        assertEquals(expectedResponseBodyPojo.getBooking().getTotalprice(),
                     responseBodyPojo.getBooking().getTotalprice());
        assertEquals(expectedResponseBodyPojo.getBooking().isDepositpaid(),
                     responseBodyPojo.getBooking().isDepositpaid());
        assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckin(),
                     responseBodyPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckout(),
                     responseBodyPojo.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedResponseBodyPojo.getBooking().getAdditionalneeds(),
                     responseBodyPojo.getBooking().getAdditionalneeds());


    }




}
