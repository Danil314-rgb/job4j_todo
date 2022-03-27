package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.management.Query;
import java.util.Collection;
import java.util.function.Function;

public class DbStore implements Store {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private DbStore() {

    }

    private static final class Lazy {
        private static final Store INST = new DbStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public <T> Collection<T> allTasks() {
        Session session = sf.openSession();
        session.beginTransaction();
        Collection tasks = session.createQuery("from model.Task").list();
        session.getTransaction().commit();
        session.close();
        return tasks;
    }

    @Override
    public <T> T create(T model) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        session.close();
        return model;
    }

    @Override
    public User findByUserEmail(String email) {
        Session session = sf.openSession();
        session.beginTransaction();
        User user = session.get(User.class, email);
        session.getTransaction().commit();
        session.close();
        return user;
    }

}
