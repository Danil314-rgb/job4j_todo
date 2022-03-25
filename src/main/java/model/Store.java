package model;

import org.hibernate.SessionFactory;

import java.util.Collection;

public interface Store {

    void addTask(Task task);

    Collection<Task> allTasks();
}
