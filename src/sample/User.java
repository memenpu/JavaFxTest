package sample;

public class User {
    int id;
    String firstName;
    String lastName;

    @Override
    public String toString(){
        return String.format("id - %d, firstName - %s lastName - %s ", id, firstName, lastName);
    }
}
