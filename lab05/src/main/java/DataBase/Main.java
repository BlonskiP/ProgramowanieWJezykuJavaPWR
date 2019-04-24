package DataBase;

public class Main {

    public static void main(String[] argv)
    {
    SqlManager.setUp();
    Player p=new Player();
    p.setName("Piotrek");
    SqlManager.addPlayer(p);
    Skill codeing=new Skill();
    codeing.setPlayer(p);
    SqlManager.addSkill(codeing);
        SqlManager.entityManagerFactory.close();
    }
}
