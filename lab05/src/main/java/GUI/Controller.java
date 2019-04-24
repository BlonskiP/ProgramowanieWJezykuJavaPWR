package GUI;

import DataBase.Player;
import DataBase.Skill;
import DataBase.SqlManager;
import DataBase.XMLManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {
    @FXML
    ListView<Player> playersView;
    @FXML
    ListView<Skill> skillsView;
    @FXML
    TextField playerNameText;
    @FXML
            TextField skillDesc;
    @FXML
            TextField skillTitle;
    ChangeListener<Player> playerListChange = new ChangeListener<Player>() {
        public void changed(ObservableValue<? extends Player> observable, Player oldValue, Player newValue) {
            System.out.println(newValue.getName());
            GetNewSkills();
        }
    };

    private void GetNewSkills() {
        skillsView.getItems().clear();
        ArrayList<Skill> PlayerSkills=(ArrayList<Skill>) SqlManager.getSkills(playersView.getSelectionModel().getSelectedItem());
        for (Skill skill:PlayerSkills
             ) {
            skillsView.getItems().add(skill);
        }
        skillsView.refresh();
    }

    public Controller()
    {



    }

    @FXML
    public void RefreshPlayers()
    {
        playersView.getItems().clear();
        ArrayList<Player> players =(ArrayList<Player>) SqlManager.getPlayers();
        for (Player player:players
             ) {
            playersView.getItems().add(player);
        }
        playersView.refresh();
        playersView.getSelectionModel().selectedItemProperty().addListener(playerListChange);
    }

    @FXML
    public void AddPlayer()
    {
        Player player = new Player();
        player.setName(playerNameText.getText());
        SqlManager.addPlayer(player);
        RefreshPlayers();
    }
    @FXML
    public void UpdatePlayer()
    {
        Player player = playersView.getSelectionModel().getSelectedItem();
        player.setName(playerNameText.getText());
        RefreshPlayers();
    }
    @FXML
    public void AddSkill()
    {
        Skill skill = new Skill();
        skill.setPlayer(playersView.getSelectionModel().getSelectedItem());
        skill.setDescription(skillDesc.getText());
        skill.setSkillTitle(skillTitle.getText());
        SqlManager.addSkill(skill);
        RefreshSkills();
    }
    @FXML
    public void UpdateSkill()
    {
        Skill skill = skillsView.getSelectionModel().getSelectedItem();
        skill.setDescription(skillDesc.getText());
        skill.setSkillTitle(skillTitle.getText());
        RefreshSkills();
    }

    private void RefreshSkills() {
        GetNewSkills();
    }
    public void ParseToXML()
    {
        XMLManager.ParseToXML();
    }
}
