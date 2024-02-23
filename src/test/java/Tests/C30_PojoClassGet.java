package Tests;

import BaseUrl.BaseUrlDummy;
import Pojos.PojoDummyData;
import Pojos.PojoDummyResponseBody;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C30_PojoClassGet extends BaseUrlDummy {

        /*
     http://dummy.restapiexample.com/api/v1/employee/3 url'ine
     bir GET request gonderdigimizde donen response'un asagidaki gibi oldugunu test edin.

     Response Body// expected data
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
        PojoDummyData dataPojo=new PojoDummyData(3,"Ashton Cox",86000,66,"");
        PojoDummyResponseBody expectedBodyPojo=new PojoDummyResponseBody("success",dataPojo,"Successfully! Record has been fetched.");

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().spec(specDummy)
                          .when()
                          .get("{pp1}/{pp2}");
        //4)Assertion
        //oncelikle response'u expectedBodyPojo'nun olusturuldugu class'tan pojo objesine cevirmeliyiz.
        PojoDummyResponseBody responsePojo=response.as(PojoDummyResponseBody.class);

        assertEquals(expectedBodyPojo.getStatus(),
                     responsePojo.getStatus());
        assertEquals(expectedBodyPojo.getData().getId(),
                     responsePojo.getData().getId());
        assertEquals(expectedBodyPojo.getData().getEmployee_name(),
                     responsePojo.getData().getEmployee_name());
        assertEquals(expectedBodyPojo.getData().getEmployee_salary(),
                     responsePojo.getData().getEmployee_salary());
        assertEquals(expectedBodyPojo.getData().getEmployee_age(),
                     responsePojo.getData().getEmployee_age());
        assertEquals(expectedBodyPojo.getData().getProfile_image(),
                     responsePojo.getData().getProfile_image());
        assertEquals(expectedBodyPojo.getMessage(),
                     responsePojo.getMessage());



    }


}
