package TestData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HerokuappDatas {
    /*
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
     */
    public static JSONObject jsonHerokuappRequestBodyOlustur(){

       JSONObject bookingdates=new JSONObject();
       bookingdates.put("checkin" , "2021-06-01");
       bookingdates.put("checkout" , "2021-06-10");

       JSONObject requestBody=new JSONObject();
        requestBody.put("firstname" , "Ahmet");
        requestBody.put("lastname" , "Bulut");
        requestBody.put("totalprice" , 500);
        requestBody.put("depositpaid" , false);
        requestBody.put("bookingdates" ,bookingdates);
        requestBody.put("additionalneeds" , "wi-fi");

        return requestBody;
    }
    /*Response body (=Expected body)
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
    public static JSONObject jsonexpectedBodyOlustur(){

        JSONObject expectedBody=new JSONObject();
        JSONObject booking=jsonHerokuappRequestBodyOlustur();//request body'i booking JSONObject booking'in icine attik
        expectedBody.put("bookingid",24);
        expectedBody.put("booking",booking);

        return  expectedBody;
    }

    /*
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
     */

        public static Map<String,Object> mapRequestBodyOlustur(){
        //icice giren mapler varsa herbir map ayri bir methodda olusturulabilir
            Map<String,Object> requestBodyMap=new HashMap<>();
            requestBodyMap.put("firstname" , "Ahmet");
            requestBodyMap.put("lastname" , "Bulut");
            requestBodyMap.put("totalprice" , 500.0);
            requestBodyMap.put("depositpaid" , false);
            requestBodyMap.put("bookingdates",mapBookingdatesOlustur());
            requestBodyMap.put("additionalneeds" , "wi-fi");

            return requestBodyMap;
        }
        public static Map<String,String> mapBookingdatesOlustur(){

            Map<String,String> bookingdatesMap=new HashMap<>();
            bookingdatesMap.put("checkin" , "2021-06-01");
            bookingdatesMap.put("checkout" , "2021-06-10");

            return bookingdatesMap;
        }
        /*
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

    public static Map<String,Object> responseBodyMapOlustur(){

        Map<String,Object> responseBodyMap=new HashMap<>();
        responseBodyMap.put("bookingid",24);
        responseBodyMap.put("booking",mapRequestBodyOlustur());

        return responseBodyMap;
    }

}
