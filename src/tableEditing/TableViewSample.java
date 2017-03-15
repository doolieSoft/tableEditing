package tableEditing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class TableViewSample extends Application {
	private List<String> cols = new ArrayList<String>();
	private List<String> emiRatios = Arrays.asList("10","20","30","10");
	
    private TableView<CIRatio> table = new TableView<CIRatio>();
    private final ObservableList<CIRatio> data =
            FXCollections.observableArrayList(
            new CIRatio("EMI",emiRatios));
    final HBox hb = new HBox();
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(550);
 
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        cols.add("ALMASMU");
        cols.add("MITABES");
        cols.add("KIKIDAN");
        cols.add("STARUSY");
        
        TableColumn[] agentsRatioColumns = new TableColumn[cols.size()];
        for(int i = 0 ; i< cols.size();i++) {
        	TableColumn agentRatioColumn = new TableColumn(cols.get(i));
            agentRatioColumn.setMinWidth(100);
            agentRatioColumn.setCellValueFactory(
                new PropertyValueFactory<CIRatio, String>(cols.get(i)));
            agentRatioColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            table.getColumns().addAll(agentRatioColumn);
        }
        
        table.setItems(data);
 
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        //addFirstName.setMaxWidth(agentRatioColumn.getPrefWidth());
        final TextField addLastName = new TextField();
        //addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addEmail = new TextField();
        //addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");
 
        final Button addButton = new Button("Add");
        
 
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class CIRatio {
 
        private final SimpleStringProperty ciName;
        private final List<SimpleStringProperty> ratios;
        
 
        private CIRatio(String ciName, List<String> r) {
            this.ciName = new SimpleStringProperty(ciName);
            ratios = new ArrayList<SimpleStringProperty>();
            for(String ratio : r) {
            	ratios.add(new SimpleStringProperty(ratio));
            }
        }
        
        
    }
}