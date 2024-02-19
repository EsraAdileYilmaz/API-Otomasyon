package Tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class C08_JsonPathKullanimi {

    /*
       { ==>1.json objesi(en genel json objesi)
         "firstName": "John",
         "lastName": "doe",
          "age": 26,
          "address": { ==>2.json objesi
          "streetAddress": "naist street",
          "city": "Nara",
          "postalCode": "630-0192"
                     },
           "phoneNumbers": [ ==>3. json array objesi
                         { ==>4. json objesi
                          "type": "iPhone",
                          "number": "0123-4567-8888"
                         },
                        { ==>5. json objesi
                        "type": "home",
                        "number": "0123-4567-8910"
                       }
                ]
           }

           Biz burada 5 json objesi iceren bir nesne olusturmak istiyoruz.

       */

    @Test
    public void jsonPathKullanimi(){

        JSONObject homeTel=new JSONObject();//json array'inin icindeki 2.json objesi(array'in index=1)
        homeTel.put("type", "home");
        homeTel.put("number","0123-4567-8910");

        JSONObject iPhone=new JSONObject();//json array'inin icindeki 1.json objesi(array'in index=0)
        iPhone.put("type","iPhone");
        iPhone.put( "number","0123-4567-8888");

        JSONArray phoneNumbers=new JSONArray();//json array'i
        phoneNumbers.put(0,iPhone);
        phoneNumbers.put(1,homeTel);

        JSONObject address=new JSONObject();//json objesi
        address.put("streetAddress","naist street");
        address.put("city","Nara");
        address.put("postalCode","630-0192");


        JSONObject personelInfo=new JSONObject();//json objesi tumunu kapsayan genel obje
        personelInfo.put( "firstName","John");
        personelInfo.put("lastName","doe");
        personelInfo.put("age",26);
        personelInfo.put("address",address);
        personelInfo.put("phoneNumbers",phoneNumbers);

        //JsonPath ile her bir key'in value'sune ulasilabilir.Olusturulan JsonObje uzerinden key'ler kullanilarak value'lere ulasilabilir.
        System.out.println("isim: "+personelInfo.get("firstName"));
        System.out.println("soyadi: "+personelInfo.get("lastName"));
        System.out.println("yas: "+personelInfo.get("age"));
        System.out.println("Adres: "+personelInfo.getJSONObject("address").get("streetAddress")+
                                    "\n"+personelInfo.getJSONObject("address").get("city")+
                                    "\n"+personelInfo.getJSONObject("address").get("postalCode"));
        System.out.println("Telefon : "+personelInfo.getJSONArray("phoneNumbers").getJSONObject(0).get("type")+"="
                +personelInfo.getJSONArray("phoneNumbers").getJSONObject(0).get("number")+"\n\t\t"
                +personelInfo.getJSONArray("phoneNumbers").getJSONObject(1).get("type")+"="
                +personelInfo.getJSONArray("phoneNumbers").getJSONObject(1).get("number"));
        /*
        System.out.println("iPhone: "+personelInfo.getJSONArray("phoneNumbers")
                                       .getJSONObject(0).get("number"));
        System.out.println("Tel type: "+personelInfo.getJSONArray("phoneNumbers")
                                       .getJSONObject(0).get("type"));

        System.out.println("Ev tel: "+personelInfo.getJSONArray("phoneNumbers")
                                      .getJSONObject(1).get("number"));
        System.out.println("Tel type: "+personelInfo.getJSONArray("phoneNumbers")
                                        .getJSONObject(1).get("type"));
        */




    }

}
