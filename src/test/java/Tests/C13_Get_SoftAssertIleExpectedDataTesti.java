package Tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C13_Get_SoftAssertIleExpectedDataTesti {

       /*
      http://dummy.restapiexample.com/api/v1/employee/3 url'ine
      bir GET request gonderdigimizde
      donen response'un asagidaki gibi oldugunu test edin.

       Response Body=(Expected body demek)
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
    public void softAssertTesti(){

        //1-End-point hazirlama ve Request body olusturma
        String url="http://dummy.restapiexample.com/api/v1/employee/3";

        //2-Expected body hazirla

        JSONObject data=new JSONObject();
        data.put("id",3);
        data.put("employee_name","Ashton Cox");
        data.put("employee_salary",86000);
        data.put("employee_age",66);
        data.put("profile_image","");

        JSONObject expectedBody=new JSONObject();
        expectedBody.put("status","success");
        expectedBody.put("data",data);
        expectedBody.put("message","Successfully! Record has been fetched.");

        //3-Request gönderip, dönen response'i kaydetme
        Response response=given().when().get(url);

        //4-Assertion
        //Oncelikle response uzerindeki bilgileri kolay almak icin response'i JsonPath'e cast edelim
        JsonPath responseJsonPath=response.jsonPath();//Gelen cevabi yani response'i JsonPath e cast ediyoruz
        //response'dan gelen bizim actual data'mizi olusturuyor.
        //bizim olusturdugumuz ise expected data oluyor.

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(responseJsonPath.get("status"),expectedBody.get("status"));
        softAssert.assertEquals(responseJsonPath.get("data.id"),expectedBody.getJSONObject("data").get("id"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_name"),expectedBody.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_salary"),expectedBody.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_age"),expectedBody.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(responseJsonPath.get("data.profile_image"),expectedBody.getJSONObject("data").get("profile_image"));
        softAssert.assertEquals(responseJsonPath.get("message"),expectedBody.get("message"));

        softAssert.assertAll();//burayi yazmazsak assertion gerceklesmez ve raporlama olmaz






    }




}
