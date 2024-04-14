package Tests;

import BaseUrl.BaseUrlHerokuapp;
import TestData.HerokuappDatas;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_DeSerializationPost extends BaseUrlHerokuapp {

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
                        Response Body // expected data
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
    public void test01() {

        //1-End-point hazirlama ve Request body olusturma
        specHerokuapp.pathParam("pp1", "booking");
        Map<String, Object> requestBodyMap = HerokuappDatas.mapRequestBodyOlustur();

        //2-Expected body hazirlama
        Map<String, Object> expectedBodyMap = HerokuappDatas.responseBodyMapOlustur();
        //System.out.println(expectedBodyMap);

        //3-Request gönderip, dönen response'i kaydetme
        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON)
                            .when().body(requestBodyMap)
                            .post("{pp1}");
        //response.prettyPrint();
        //4)Assertion
        //Oncelikle response'u Map'e cevirmeliyiz.
        Map<String, Object> responseMap = response.as(HashMap.class);//response'i Map'e cevirdik

        assertEquals(((Map) expectedBodyMap.get("booking")).get("firstname"),
                ((Map) responseMap.get("booking")).get("firstname"));
        assertEquals(((Map) expectedBodyMap.get("booking")).get("lastname"),
                ((Map) responseMap.get("booking")).get("lastname"));
        assertEquals(((Map) expectedBodyMap.get("booking")).get("totalprice"),
                ((Map) responseMap.get("booking")).get("totalprice"));
        assertEquals(((Map) expectedBodyMap.get("booking")).get("depositpaid"),
                ((Map) responseMap.get("booking")).get("depositpaid"));
        assertEquals(((Map) ((Map) expectedBodyMap.get("booking")).get("bookingdates")).get("checkin"),
                ((Map) ((Map) responseMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(((Map) ((Map) expectedBodyMap.get("booking")).get("bookingdates")).get("checkout"),
                ((Map) ((Map) responseMap.get("booking")).get("bookingdates")).get("checkout"));
        assertEquals(((Map) expectedBodyMap.get("booking")).get("additionalneeds"),
                ((Map) responseMap.get("booking")).get("additionalneeds"));



        /*API Interview sorulari=
        1-I am using the get request and getting security number,
        how do you check that security number is correct or wrong?
        Ben bir get request kullaniyorum ve bana guvenlik numarasini getiriyor,
        bu guvenlik numarasinin dogru veya yanlis oldugunu nasil kontrol ederim?
        Cevap=Assertion yapariz
        2-API testine neden ihtiyac duyariz?
        Iletisimin dogru olup olmadigini kontrol etmek icin.verilerin dogrulugu,guvenlik aciklarinin olusmamasi,
        sistemin devamliliginin saglanmasi,performans kontrolu icin(olculebilir bir performansi olculur)
        3-API testi yaparken ihtiyac duydugumuz temel bilesenler nelerdir?
        API-swagger(open API) dokumani,endpoint,http methodlari,
        request-response,kimliklendirmeye ihtiyacimiz var
         */

    }
}
