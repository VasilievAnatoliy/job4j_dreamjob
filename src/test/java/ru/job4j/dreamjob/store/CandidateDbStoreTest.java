package ru.job4j.dreamjob.store;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CandidateDbStoreTest {
    private static CandidateDbStore store;
    private static Candidate candidate1;
    private static Candidate candidate2;
    private static Candidate candidate3;

    @BeforeClass
    public static void initConnection() {
        store = new CandidateDbStore(new Main().loadPool());
    }

    @Before
    public void newPost() {
        candidate1 = new Candidate(0, "Petr", "Java Senior", new byte[]{1,2,3,4},
                "10-10-2022", true, new City(1, null));
        candidate2 = new Candidate(1, "Maxim", "C++ Middle", new byte[]{5, 6, 7},
                "10-10-2022", false, new City(1, null));
        candidate3 = new Candidate(2, "Vlad", "Java Junior", new byte[]{8, 9},
                "10-11-2022", true, new City(2, null));
    }

    @After
    public void clear() {
        store.clearTable();
    }

    @Test
    public void whenCreatePost() {
        store.add(candidate1);
        Candidate postInDb = store.findById(candidate1.getId());
        assertThat(postInDb, is(candidate1));
    }

    @Test
    public void whenUpdate() {
        store.add(candidate1);
        candidate2.setId(candidate1.getId());
        store.update(candidate2);
        assertThat(store.findById(candidate1.getId()), is(candidate2));
    }

    @Test
    public void whenFindAll() {
        List<Candidate> lists = List.of(candidate1, candidate2, candidate3);
        for (Candidate list : lists) {
            store.add(list);
        }
        assertThat(lists, is(store.findAll()));
    }
}