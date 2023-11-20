package tools;

public class People {
    public String name;
    public String address;
    public String tel;

    public People(String name, String address, String tel) {
        this.name = name;
        this.address = address;
        this.tel = tel;
    }
    public People(){
        name = "NULL";
        address = "NULL";
        tel = "NULL";
    }
    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel ;
    }
}
