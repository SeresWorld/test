package data;

import utils.Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class UserList {

    public Object[][] getUserList()  {
        Decoder decoder = new Decoder();
        return new Object[][] {
                { new User().setLogin("7756655544").setPassword(
                        decoder.getPassword("T5PpK1BdwBh9xawmMSxBmQ==")) }
        };
    }
}