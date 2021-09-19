package units;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {
    private User user;
    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {
        user = new User("Баир", 15, Sex.MALE);
        user1 = new User("Артём", 14, Sex.FEMALE);
        user2 = new User("Максим", 9, Sex.FEMALE);
    }

    @Test
    public void getAllUsers() {
        List<User> expected = User.getAllUsers();

        List<User> actual = new ArrayList<>();
        actual.add(user);
        actual.add(user1);
        actual.add(user2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllUsers_NO_NULL() {
        List<User> expected = User.getAllUsers();
        Assert.assertNotNull(expected);
    }

    @Test
    public void getAllUsers_MALE() {
        List<User> expected = User.getAllUsersBySex(Sex.MALE);

        List<User> actual = new ArrayList<>();
        actual.add(user);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllUsers_MALE_NO_NULL() {

        List<User> expected = User.getAllUsersBySex(Sex.MALE);
        Assert.assertNotNull(expected);
    }

    @Test
    public void getAllUsers_FEMALE() {
        List<User> expected = User.getAllUsersBySex(Sex.FEMALE);

        List<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllUsers_FEMALE_NO_NULL() {

        List<User> expected = User.getAllUsersBySex(Sex.FEMALE);
        Assert.assertNotNull(expected);
    }

    @Test
    public void getHowManyUsers() {
        int expected = User.getUsersCount();

        int actual = 3;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHowManyUsers_MALE() {
        int expected = User.getUsersCountBySex(Sex.MALE);

        int actual = 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getHowManyUsers_FEMALE() {
        int expected = User.getUsersCountBySex(Sex.FEMALE);

        int actual = 2;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllAgeUsers() {
        int expected = User.getAllAgeUsers();

        int actual = 15 + 14 + 9;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllAgeUsers_MALE() {
        int expected = User.getAllAgeUsersBySex(Sex.MALE);

        int actual = 15;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllAgeUsers_FEMALE() {
        int expected = User.getAllAgeUsersBySex(Sex.FEMALE);

        int actual = 14 + 9;

        Assert.assertEquals(expected, actual);
    }
}