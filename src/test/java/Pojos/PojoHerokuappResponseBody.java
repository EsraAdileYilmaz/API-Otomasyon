package Pojos;

public class PojoHerokuappResponseBody {
    /*
     Response Body // expected data
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
                    ,
                    "additionalneeds":"wi-fi"
                }
     */
    // 1- tum variable'lari private olarak olustur
    //variable'larin degerlerini dinamik olarak parametre seklinde gonderecegiz.
    private int bookingid;
    private PojoHerokuappRequestBody booking;

    // 2- tum variable'lar icin getter and setter metodlari olusturalim

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public PojoHerokuappRequestBody getBooking() {
        return booking;
    }

    public void setBooking(PojoHerokuappRequestBody booking) {
        this.booking = booking;
    }

    // 3- tum parametreleri kullanarak bir constructor olusturalim

    public PojoHerokuappResponseBody(int bookingid, PojoHerokuappRequestBody booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    // 4- default constructor yerine manuel olarak parametresiz bir constructor olusturalim

    public PojoHerokuappResponseBody() {
    }

    // 5- toString metodu olusturalim

    @Override
    public String toString() {
        return "PojoHerokuappResponseBody{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
