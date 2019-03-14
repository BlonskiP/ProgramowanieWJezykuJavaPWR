package GUI;

import GUI.Controls.Warehouse;
import KnapsackProblem.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;


public class Controller {
    @FXML
    private TextField itemNameInput;
    @FXML
    private TextField itemValueInput;
    @FXML
    private TextField itemWeightInput;
    @FXML
    private ListView<Item> itemList;
    @FXML
    public void addItem()
    {
        Item item = new Item(Float.parseFloat(itemValueInput.getText()),Integer.parseInt(itemWeightInput.getText()),itemNameInput.getText());
        Warehouse.ItemList.add(item);
        itemList.getItems().add(item);
        System.out.println(item.toString());
    }
    @FXML
    public void LangChange(String code)
    {
        Warehouse.tempItemName=itemNameInput.getText();
        Warehouse.tempItemValue=itemValueInput.getText();
        Warehouse.tempItemWeight=itemWeightInput.getText();

        System.out.println("TEST COMPLETE");
        NationalizationManager.setLocal(code);
        restoreValue();

    }
    @FXML
    public void LangEN()
    {
        LangChange("en");
    }
    public void LangPL()
    {
        LangChange("pl");
    }

    public void restoreValue()
    {
        itemNameInput.setText(Warehouse.tempItemName);
        itemValueInput.setText(Warehouse.tempItemValue);
        itemWeightInput.setText(Warehouse.tempItemWeight);

    }
}
