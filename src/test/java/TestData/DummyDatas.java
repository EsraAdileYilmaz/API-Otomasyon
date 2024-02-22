package TestData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DummyDatas {

    /*
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

    public static  int basariliStatusCode=200;
    public static String contentType="application/json";

    public static JSONObject expectedDataOlustur(int id,String employee_name,int employee_salary,int employee_age,String profile_image){
        //Burada parametreleri vererek dinamik bir method hazirlmais oluyoruz
        JSONObject expectedData=new JSONObject();
        JSONObject data=new JSONObject();
        data.put( "id",id);
        data.put( "employee_name",employee_name);
        data.put( "employee_salary",employee_salary);
        data.put( "employee_age",employee_age);
        data.put( "profile_image",profile_image);

        expectedData.put("status","success");
        expectedData.put("data",data);
        expectedData.put("message","Successfully! Record has been fetched.");

         return expectedData;
    }
    /*
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
    public static Map<String,Object> mapBodyOlustur(){

        Map<String,Object> dataMap=new HashMap<>();
        dataMap.put("id",3.0);
        dataMap.put("employee_name","Ashton Cox");
        dataMap.put("employee_salary",86000.0);
        dataMap.put("employee_age",66.0);
        dataMap.put("profile_image","");

        Map<String,Object> bodyMap=new HashMap<>();
        bodyMap.put("status","success");
        bodyMap.put("data",dataMap);
        bodyMap.put("message","Successfully! Record has been fetched.");

        return bodyMap;
    }


}
