package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.Collection;

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
    public Query<User> findByUserEmail(String email) {
        Session session = sf.openSession();
        Query query = session.createQuery("from User u where u.email = :email");
        query.setParameter("email", email);
        return query;
    }

    @Override
    public void updateDone(/*String id, */String name) {
        boolean done = Boolean.parseBoolean(name);
        /*done = !done;*/

        Session session = sf.openSession();
        Query query = session.createQuery(
                "update Task t set t.done = :newDone where t.id = 1" /*where t.id = :tId*/
        );
        query.setParameter("newDone", done);
        /*query.setParameter("tId", Integer.parseInt(id));*/
        query.executeUpdate();
    }

}
