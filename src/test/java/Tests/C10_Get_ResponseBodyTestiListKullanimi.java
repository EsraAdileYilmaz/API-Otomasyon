package Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C10_Get_ResponseBodyTestiListKullanimi {

    /*
         http://dummy.restapiexample.com/api/v1/employees url'ine
         bir GET request yolladigimizda
         donen Response'in
            status code'unun 200,
            ve content type'inin Aplication.JSON,
            ve response body'sindeki
            employees sayisinin 24
            ve employee'lerden birinin "Ashton Cox"
            ve girilen yaslar icinde 61,21 ve 35 degerinin oldugunu test edin.
 */

    @Test
    public void listKullanimi(){
        //1- End-point hazirlama ve Request body olusturma
        String url="http://dummy.restapiexample.com/api/v1/employees";

        // 2- Expected Data hazirlama

        //3- Request gönderip, dönen response'i kaydetme
        Response response=given().when().get(url);
        response.prettyPrint();
        /*
        {
    "status": "success",
    "data": [
        {
            "id": 1,
            "employee_name": "Tiger Nixon",
            "employee_salary": 320800,
            "employee_age": 61,
            "profile_image": ""
        },
        bize bir array dondu ve biz arrayin size()'inin 24 oldugunu test edicez

         */
        //4-Assertion
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .body("data.id",hasSize(24),
                       "data.employee_name",hasItem("Ashton Cox"),
                       "data.employee_age",hasItems(61,21,35));


    }


}
