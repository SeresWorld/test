package data;

public class UserList {

    public Object[][] getUserList() {
        return new Object[][] {
                { new User().setLogin("7756655544").setPassword("orapas123") },
                { new User().setLogin("7478488956").setPassword("Orapas#123") }};
    }
}
