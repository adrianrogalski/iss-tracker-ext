package util;

import model.Event;
import model.EventDto;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            final var configuration = new Configuration();
            configuration.configure(HibernateUtil.class.getResource("/hibernate.cfg.xml"));
            configureEntities(configuration);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static void configureEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Event.class);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private HibernateUtil() {}
}
