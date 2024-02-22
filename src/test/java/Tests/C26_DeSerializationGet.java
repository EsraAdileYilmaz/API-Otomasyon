package Tests;

import BaseUrl.BaseUrlDummy;
import TestData.DummyDatas;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_DeSerializationGet extends BaseUrlDummy{

    /*
     http://dummy.restapiexample.com/api/v1/employee/3 url’ine
     bir GET request gonderdigimizde
     donen response’un status code’unun 200,
     content Type’inin application/json
     ve body’sinin asagidaki gibi oldugunu test edin.

    Expected Response Body
    {
        "status":"success",
        "data":{
                "id":3,
                "employee_name":"Ashton Cox",
                "employee_salary":86000,
                "employee_age":66,
                "profile_image":""
                },
        "message":"Successfully! Record has been fetched."
    }
   */

    @Test
    public void test01(){

        //1-End-point hazirlama ve Request body olusturma
        specDummy.pathParams("pp1","employee","pp2","3");

        //2-Expected body hazirlama
        Map<String,Object> expectedBodyMap= DummyDatas.mapBodyOlustur();
        //System.out.println(expectedBodyMap);

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specDummy)
                          .when()
                          .get("{pp1}/{pp2}");
        //response.prettyPrint();

        //4)Assertion
        //Oncelikle response'u Map'e cevirmeliyiz.
        Map<String,Object> responseMap=response.as(HashMap.class);//response'i Map'e cevirdik
        //System.out.println(responseMap);

        assertEquals(DummyDatas.basariliStatusCode,response.getStatusCode());
        assertEquals(DummyDatas.contentType,response.getContentType());
        assertEquals(expectedBodyMap.get("status"),responseMap.get("status"));
        assertEquals(expectedBodyMap.get("message"),responseMap.get("message"));
        //expectedBodyMap.get("data") yaptigimizda data icindeki Map'in tamamini getiriyor.
        //Donen datanin type'inin map oldugunu(Map) yazarak deklare ediyoruz
        assertEquals(((Map)expectedBodyMap.get("data")).get("id"),
                     ((Map)responseMap.get("data")).get("id"));
        assertEquals(((Map)expectedBodyMap.get("data")).get("employee_name"),
                     ((Map)responseMap.get("data")).get("employee_name"));
        assertEquals(((Map)expectedBodyMap.get("data")).get("employee_salary"),
                    ((Map)responseMap.get("data")).get("employee_salary"));
        assertEquals(((Map)expectedBodyMap.get("data")).get("employee_age"),
                    ((Map)responseMap.get("data")).get("employee_age"));
        assertEquals(((Map)expectedBodyMap.get("data")).get("profile_image"),
                     ((Map)responseMap.get("data")).get("profile_image"));


    }




}
