package person;

public class Person {
    private String _name;
    private int _age;
    private Address _address;


    public Person(String name, int age, String street, String city){
        this._name = name;
        this._age = age;
        this._address = new Address(street, city);
    }

    public class Address{
        private String _street;
        private String _city;

        public Address(String street, String city){
            this._street = street;
            this._city = city;
        }

        public void displayAdress(){
            System.out.println("Address: " + this._street + ", " + this._city);
        }
    }

    public void displayPersonInfo(){
        System.out.println("Name: " + this._name);
        System.out.println("Age: " + this._age);
        this._address.displayAdress();
    }
}
