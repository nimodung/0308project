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
				"우혁", "은주", "지훈", "재성", "종하", "춘우", "재형"));
		listview.setStyle("-fx-alignment : CENTER");
		
		listview.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				tableview.getSelectionModel().select(newValue.intValue());
				tableview.scrollTo(newValue.intValue());
				
			}
		});
		
		
		//Name class로 ObservalbleList 만들기
		ObservableList nameList = FXCollections.observableArrayList(
				new Name("우혁", "wh.png"),
				new Name("은주", "ej.png"),
				new Name("지훈", "jh.png"),
				new Name("재성", "js.png"),
				new Name("종하", "kjh.png"),
				new Name("춘우", "cw.png"),
				new Name("재형", "sjh.png")
				);
		
		TableColumn tcName = tableview.getColumns().get(0); //TableView의 column 0번 인덱스를 가져와 tcName에 set
		tcName.setCellValueFactory(
				new PropertyValueFactory("name")
				); //tcName 초기화
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
		
		System.out.println("ListView 이름 : " + item);
		
		Name name = tableview.getSelectionModel().getSelectedItem();
		System.out.println("tableview 이름 : " + name.getName());
		System.out.println("tableview 이미지 : " + name.getImage());
	}
	
	public void handelBtnFinishAction(ActionEvent event) {
		Platform.exit();
	}

}
