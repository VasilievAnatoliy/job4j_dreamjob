package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище вакансий(Post) - ConcurrentHashMap.
 * PostStore - Слой персистенции.
 */

@Repository
public class PostStore {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    public void add(Post post) {
        post.setId(count.incrementAndGet());
        post.setCreated(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.replace(post.getId(), post);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
