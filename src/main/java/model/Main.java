package model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;

import java.time.LocalDateTime;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        /*User user = DbStore.instOf().create(User.of("Max"));
        DbStore.instOf().create(Task.of(
                "fds",
                 LocalDateTime.now(),
                true,
                user));*/

        User user = DbStore.instOf().findByUserEmail("");
        System.out.println(user.getName() + user.getId());

    }
}
