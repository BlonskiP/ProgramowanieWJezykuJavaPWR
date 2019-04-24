package DataBase;

import com.mysql.cj.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SqlManager {

    public static EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;


    public static void setUp()
    {
        entityManagerFactory= Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();


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

    public static List<Player> getPlayers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> cq = cb.createQuery(Player.class);
        Root<Player> rootEntry = cq.from(Player.class);
        CriteriaQuery<Player> all = cq.select(rootEntry);

        TypedQuery<Player> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Skill> getSkills(Player player) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Skill> cq = cb.createQuery(Skill.class);
        Root<Skill> rootEntry = cq.from(Skill.class);
        CriteriaQuery<Skill> all = cq.select(rootEntry);

        TypedQuery<Skill> allQuery = entityManager.createQuery(all);
        ArrayList<Skill> skills = (ArrayList<Skill>) allQuery.getResultList();
        ArrayList<Skill> playerSkills = new ArrayList<Skill>();
        for (Skill skill: skills
             ) {
            if(skill.getPlayer()==player)
                playerSkills.add(skill);
        }
        return playerSkills;
    }
}
