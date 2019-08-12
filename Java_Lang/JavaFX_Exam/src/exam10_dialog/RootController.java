package exam10_dialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {

	private Stage primaryStage; //실행한 윈도우 //이 위에 다이얼로그 띄우기
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML TextArea tx_edit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}
	public void handleNew(ActionEvent event) {
		//tx_edit.appendText("New\n");
		
		
		tx_edit.clear();
	}
	
	public void handleOpen(ActionEvent event) {
		//tx_edit.appendText("Open\n");
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("All Files", "*.*"),
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("Image Files", "*.png", "*.img", "*.gif"),
				new ExtensionFilter("Audio Files", "*.mp3", "*.wav", "*.aac")
				);
		
		//modal? : 다이얼로그를 소유하고 있는 윈도우가 비활성되는거(다이얼로그 창을 닫기 전까지 윈도우를 선택할 수 없음)
		//modalless? : 다이얼로그랑 윈도우랑 따로 움직일 수 있음
		
		File selectedFile = fileChooser.showOpenDialog(primaryStage); //.getScene().getWindow());
		//opendialog :  파일을 선택하면 파일을 리턴해줌 //취소하면 아무것도 리턴하지않음
		if(selectedFile != null) { //파일을 선택했을 때
			String filePath = "";
			String fileName = "";
			filePath = selectedFile.getPath();
			fileName = selectedFile.getName();
			//tx_edit.setText(fileName + "\r\n" + filePath); 
			primaryStage.setTitle(fileName);
			/*
			String fileStr = "";
			fileStr = getTextFromFile(selectedFile);
			tx_edit.setText(fileStr);
			*/
			
		}
	}
	
	public String getTextFromFile(File file) {
		
		String text = "";
		
		try {
			BufferedReader buffReader  = null;
			buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String strLine;
			try {
				while((strLine = buffReader.readLine()) != null) {
					text = text + strLine + "\n"; 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return text;
	}
	
	public void handleSave(ActionEvent event) {
		//tx_edit.appendText("Save\n");
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("All Files", "*.*"),
				new ExtensionFilter("Text Files", "*.txt")
				);
		File file = fileChooser.showSaveDialog(primaryStage);
//				showSaveDialog(primaryStage.getScene().getWindow());
		if(file != null) {
			saveFile(file);                                                                                                                             
		}
	}
	
	public void saveFile(File file) {
		
		try {
			FileWriter writer = null;
			writer = new FileWriter(file);
			writer.write(tx_edit.getText().replace("\n", "\r\n"));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleClose(ActionEvent event) {
		//tx_edit.appendText("Close\n");
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("!!! Warning !!!");
		alert.setHeaderText("  Look !!!");
		alert.setContentText("저장하지 않고 종료 하시겠습니까?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			Platform.exit();
		}
		else {
			alert.close();
		}
	}
	
	
	
	
	public void handleUndo(ActionEvent event) {
		tx_edit.appendText("Undo\n");
	}
	
	public void handleRedo(ActionEvent event) {
		tx_edit.appendText("Redo\n");
	}
	
	public void handleCopy(ActionEvent event) {
		tx_edit.appendText("Copy\n");
	}
	
	public void handleCut(ActionEvent event) {
		tx_edit.appendText("Cut\n");
	}
	
	public void handlePaste(ActionEvent event) {
		//tx_edit.appendText("Paste\n");
		handleTextInput();
	}
	
	public void handleTextInput() {
		TextInputDialog dialog = new TextInputDialog("");
		
		//optional<?> : 내가 받을 형식을 정해준다
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> {
			tx_edit.appendText(name);
		});
		
	}
	
	public void handleDelete(ActionEvent event) {
		//tx_edit.appendText("Delete\n");
		tx_edit.setText("");
	}
	
	public void handleAbout(ActionEvent event) {
		//tx_edit.appendText("About\n");
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		dialog.setTitle("About");
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("custom_dialog.fxml"));
			Label txtTitle = (Label)parent.lookup("#txtTitle");
			txtTitle.setText("notePad v1.0");
			TextArea txtDescript = (TextArea)parent.lookup("#txtDescript");
			txtDescript.setText("c) Copyright Eclipse contributors and others"
					+ "\r\n"
					+ " 2000, 2019. All rights reserved. "
					+ "\r\n Eclipse and the Eclipse logo are "
					+ "\r\n trademarks of the Eclipse Foundation, Inc.,"
					+ "\r\n https://www.eclipse.org/. "
					);
			Button btnOK = (Button)parent.lookup("#btnOK");
			btnOK.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					dialog.close();
				}
			});
			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
