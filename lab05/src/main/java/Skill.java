import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="PLAYERS_SKILLS")
public class Skill {
    @Id
    @Column(name="Id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name ="incrementator", strategy = "increment")
    private int id;

}
