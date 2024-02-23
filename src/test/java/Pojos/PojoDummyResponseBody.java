package Pojos;

public class PojoDummyResponseBody {

    /*
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
    // 1- tum variable'lari private olarak olustur
        private String status;
        private PojoDummyData data;
        private String message;

    // 2- tum variable'lar icin getter and setter metodlari olusturalim

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PojoDummyData getData() {
        return data;
    }

    public void setData(PojoDummyData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // 3- tum parametreleri kullanarak bir constructor olusturalim

    public PojoDummyResponseBody(String status, PojoDummyData data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    // 4- default constructor yerine manuel olarak parametresiz bir constructor olusturalim

    public PojoDummyResponseBody() {
    }

    // 5- toString metodu olusturalim

    @Override
    public String toString() {
        return "PojoDummyResponseBody{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
