package TestData;

import org.json.JSONObject;

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
}
