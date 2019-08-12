package exam06_view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RootController implements Initializable {

	@FXML private ListView<String> listview;
	@FXML private TableView<Name> tableview;
	@FXML private ImageView imgview;
	@FXML private Button btn_ok, btn_finish;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listview.setItems(FXCollections.observableArrayList(
				"����", "����", "����", "�缺", "����", "���", "����"));
		listview.setStyle("-fx-alignment : CENTER");
		
		listview.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tableview.getSelectionModel().select(newValue.intValue());
				tableview.scrollTo(newValue.intValue());
				
			}
		});
		
		
		//Name class�� ObservalbleList �����
		ObservableList nameList = FXCollections.observableArrayList(
				new Name("����", "wh.png"),
				new Name("����", "ej.png"),
				new Name("����", "jh.png"),
				new Name("�缺", "js.png"),
				new Name("����", "kjh.png"),
				new Name("���", "cw.png"),
				new Name("����", "sjh.png")
				);
		
		TableColumn tcName = tableview.getColumns().get(0); //TableView�� column 0�� �ε����� ������ tcName�� set
		tcName.setCellValueFactory(
				new PropertyValueFactory("name")
				); //tcName �ʱ�ȭ
		tcName.setStyle("-fx-alignment : CENTER");
		
		TableColumn tcImage = tableview.getColumns().get(1);
		tcImage.setCellValueFactory(
				new PropertyValueFactory("image")
				);
		tcImage.setStyle("-fx-alignment : CENTER");
		
		tableview.setItems(nameList);
		
		tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Name>() {

			@Override
			public void changed(ObservableValue<? extends Name> observable, Name oldValue, Name newValue) {
				imgview.setImage(new Image(getClass().getResource("image/"+ newValue.getImage()).toString()));
				
			}
		});
		
		
	}
	
	public void handleBtnOkAction(ActionEvent event) {
		String item = listview.getSelectionModel().getSelectedItem();
		
		System.out.println("ListView �̸� : " + item);
		
		Name name = tableview.getSelectionModel().getSelectedItem();
		System.out.println("tableview �̸� : " + name.getName());
		System.out.println("tableview �̹��� : " + name.getImage());
	}
	
	public void handelBtnFinishAction(ActionEvent event) {
		Platform.exit();
	}

}
