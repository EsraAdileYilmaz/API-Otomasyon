package Tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class P02_JsonObjectIleJsonPathKullanimi {

    /*
    Verilen bilgileri türüne göre gruplayarak yazdirma islemi yapacagiz.
    Asagidaki gibi response elde edecegiz:
    {

      "firstName":"Harika",

      "lastName":"Wise",

      "address":{

             "streetAddress":"Susam Sokagi",

             "city":"Ankara",

         "postalCode":"06100" },

      "age":23,

      "phoneNumbers":[
    {
       "number":"532-555 55 55",
      "type":"cep" },
    {
      "number":"0312-123 4567",
      "type":"ev " }
          ]
}
     */

    @Test
    public void test01(){

        JSONObject cepPhone=new JSONObject();
        cepPhone.put("type","cep");
        cepPhone.put("number","532-555 55 55");

        JSONObject evPhone=new JSONObject();
        evPhone.put("type","ev ");
        evPhone.put("number","0312-123 4567");

        JSONArray phoneNumbers=new JSONArray();
        phoneNumbers.put(0,cepPhone);
        phoneNumbers.put(1,evPhone);

        JSONObject address=new JSONObject();
        address.put("streetAddress","Susam Sokagi");
        address.put("city","Ankara");
        address.put("postalCode","06100");

        JSONObject kisiBilgileri=new JSONObject();
        kisiBilgileri.put( "firstName","Harika");
        kisiBilgileri.put("lastName","Wise");
        kisiBilgileri.put("address",address);
        kisiBilgileri.put("age",23);
        kisiBilgileri.put("phoneNumbers",phoneNumbers);

        System.out.println(kisiBilgileri);


    }
}
