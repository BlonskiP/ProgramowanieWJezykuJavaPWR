package DataBase;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PLAYERS_SKILLS")
public class Skill implements Serializable {
    @Id
    @Column(name="Id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementator", strategy = "increment")
    private int id;
    @ManyToOne
    private Player player;

    public Skill() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    @Column(name="description")
    private String description;
    @Column(name="SkillTitle")
    private String skillTitle;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return skillTitle + "  : " + description;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }
}
