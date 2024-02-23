package Pojos;

public class PojoDummyData {
    /*
    {
           "id":3,
           "employee_name":"Ashton Cox",
           "employee_salary":86000,
           "employee_age":66,
           "profile_image":""
           },
     */
    // 1- tum variable'lari private olarak olustur
      private int id;
      private String employee_name;
      private int employee_salary;
      private int employee_age;
      private String profile_image;

    // 2- tum variable'lar icin getter and setter metodlari olusturalim

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(int employee_salary) {
        this.employee_salary = employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(int employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    // 3- tum parametreleri kullanarak bir constructor olusturalim

    public PojoDummyData(int id, String employee_name, int employee_salary, int employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }

    // 4- default constructor yerine manuel olarak parametresiz bir constructor olusturalim

    public PojoDummyData() {
    }

    // 5- toString metodu olusturalim

    @Override
    public String toString() {
        return "PojoDummyData{" +
                "id=" + id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", employee_age=" + employee_age +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }
}
