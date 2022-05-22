package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CandidateDbStore {
    private BasicDataSource pool;

    public CandidateDbStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate")
        ) {
        try (ResultSet it = ps.executeQuery()) {
            while (it.next()) {
                candidates.add(new Candidate(it.getInt("id"),
                        it.getString("name"),
                        it.getString("description"),
                        it.getBytes("photo"),
                        it.getString("created"),
                        it.getBoolean("visible"),
                        new City(it.getInt("city_id"), null)
                ));
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        try (Connection cn = pool.getConnection();
        PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO candidate(name, description, photo, created, visible, city_id) "
                       + "VALUES (?, ?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setBytes(3, candidate.getPhoto());
            ps.setString(4, candidate.getCreated());
            ps.setBoolean(5, candidate.isVisible());
            ps.setInt(6, candidate.getCity().getId());
            ps.execute();
            try (ResultSet it = ps.getGeneratedKeys()) {
                if (it.next()) {
                    candidate.setId(it.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    public void update(Candidate candidate) {
        try (Connection cn = pool.getConnection();
        PreparedStatement ps = cn.prepareStatement(
                "UPDATE candidate SET name = ?, description = ?, photo = ?,"
                       + " created = ?, visible = ?, city_id = ? WHERE id = ?")
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setBytes(3, candidate.getPhoto());
            ps.setString(4, candidate.getCreated());
            ps.setBoolean(5, candidate.isVisible());
            ps.setInt(6, candidate.getCity().getId());
            ps.setInt(7, candidate.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Candidate findById(int id) {
        try (Connection cn = pool.getConnection();
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getBytes("photo"),
                            it.getString("created"),
                            it.getBoolean("visible"),
                            new City(it.getInt("city_id"), null)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clearTable() {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM candidate")
        ) {
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
