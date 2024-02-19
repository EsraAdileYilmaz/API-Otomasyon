package Tests;

import org.json.JSONObject;
import org.testng.annotations.Test;

public class C03_JsonObjesiOlusturma {
    /*
    Asagidaki JSON Objesini olusturup konsolda yazdirin.
     {
     "title":"Ahmet",
     "body":"Merhaba",
     "userId":1
      }
     */

    @Test
    public void jsonData(){

        JSONObject jsonData=new JSONObject();
        jsonData.put("title","Ahmet");
        jsonData.put("body","Merhaba");
        jsonData.put("userId",1);

        System.out.println("Ilk olusturulan json data: "+jsonData);
    }

    @Test
    public void innerJsonData(){

       /*
        Asagidaki JSON Objesini olusturup konsolda yazdirin.

       {
         "firstname":"Jim",
          "additionalneeds":"Breakfast",
            "bookingdates":{
                "checkin":"2018-01-01",
                "checkout":"2019-01-01"
                },
         "totalprice":111,
         "depositpaid":true,
         "lastname":"Brown"
        }
 */

        //Once inner json obje olusturulur
        JSONObject innerJsonData=new JSONObject();
        innerJsonData.put("checkin","2018-01-01");
        innerJsonData.put("checkout","2019-01-01");

        //Sonra outer json obje olusturulur,yeri geldiginde inner json obje yerine yerlestirilir
        JSONObject outerJsonData=new JSONObject();
        outerJsonData.put("firstname","Jim");
        outerJsonData.put("additionalneeds","Breakfast");
        outerJsonData.put("bookingdates",innerJsonData);
        outerJsonData.put("totalprice",111);
        outerJsonData.put("depositpaid",true);
        outerJsonData.put("lastname","Brown");

        System.out.println(outerJsonData);

        //burada JSONObject yardimiyla body olusturduk.

    }
}
