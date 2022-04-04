package model;

import org.hibernate.query.Query;

import java.util.Collection;

public interface Store {

    <T> T create(T user);

    <T> Collection<T> allTasks();

    <T> Query<T> findByUserEmail(String email);

    void updateDone(boolean done, int id);

    Task findByTaskId(int id);
}
