package exam09_menubar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class RootController implements Initializable {

	@FXML TextArea tx_edit;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		

	}
	
	//appendText : 있는거에 추가 // == setText(tx_edit.getText() + "New\n");
	//setText : 새로 텍스트 덮어쓰기
	
	public void handleNew(ActionEvent event) {
		tx_edit.appendText("New\n");
	}
	
	public void handleOpen(ActionEvent event) {
		tx_edit.appendText("Open\n");
	}
	
	public void handleSave(ActionEvent event) {
		tx_edit.appendText("Save\n");
	}
	
	public void handleClose(ActionEvent event) {
		tx_edit.appendText("Close\n");
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
		tx_edit.appendText("Paste\n");
	}
	
	public void handleDelete(ActionEvent event) {
		tx_edit.appendText("Delete\n");
	}
	
	public void handleAbout(ActionEvent event) {
		tx_edit.appendText("About\n");
	}
}
