package ru.job4j.dreamjob.store;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.User;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDbStoreTest {

    private static UserDbStore store;
    private static User user1;
    private static User user2;
    private static User user3;

    @BeforeClass
    public static void initConnection() {
        store = new UserDbStore(new Main().loadPool());
    }

    @Before
    public void newUser() {
        user1 = new User(0, "Vova", "vova@ya.ru", "world");
        user2 = new User(0, "Dima", "dima@ya.ru", "happy");
        user3 = new User(0, "Maxim", "maxim@ya.ru", "password");
    }

    @After
    public void clear() {
        store.clearTable();
    }

    @Test
    public void whenCreateUsersAndFindUserByEmailAndPwd() {
        store.add(user1);
        store.add(user2);
        store.add(user3);
        User expected = store.findUserByEmailAndPwd(user1.getEmail(), user1.getPassword()).get();
        assertThat(user1, is(expected));
    }

}