package model;

import java.util.Collection;

public interface Store {

    <T> T create(T user);

    <T> Collection<T> allTasks();

    User findByUserEmail(String email);
}
