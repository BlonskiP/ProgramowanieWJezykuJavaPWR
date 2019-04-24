import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SqlManager {

    public static EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;

    public static void setUp()
    {
        entityManagerFactory= Persistence.createEntityManagerFactory("org.hibernate.pwr.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        entityManagerFactory.close();
    }
    public static void addSkill(Skill skill)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(skill);
        entityManager.getTransaction().commit();
    }

    public static void addPlayer(Player player)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(player);
        entityManager.getTransaction().commit();
    }


}
