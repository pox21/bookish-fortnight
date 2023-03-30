package user;

import exceptions.NewIllegalException;
import org.junit.jupiter.api.Test;
import user.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void userCreate() {
        User user = new User("", "mail@gmail.com", "1234");

        assertTrue(user.getName().length() > 4);

        assertThrowsExactly(NewIllegalException.class, () -> new User("", "", ""));
        assertThrowsExactly(NewIllegalException.class, () -> new User("Name", "", ""));
        assertThrowsExactly(NewIllegalException.class, () -> new User("Name", "asd", ""));


    }
}
