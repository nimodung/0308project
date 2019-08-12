package exam07_media;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class RootController implements Initializable {

	@FXML private MediaView mediaview;
	@FXML private ImageView imageview;
	@FXML private Button btn_play, btn_pause, btn_stop;
	
	private boolean endOfMedia;
	MediaPlayer mediaPlayer;
	Media media;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		media = new Media(getClass().getResource("media/video.m4v").toString());
		
		mediaPlayer = new MediaPlayer(media);
	
		mediaview.setMediaPlayer(mediaPlayer);

		
		
		mediaPlayer.setOnReady(new Runnable() {
			
			@Override
			public void run() {
				btn_play.setDisable(false); //disable 속성이 false : 활성화
				btn_pause.setDisable(true);
				btn_stop.setDisable(true);
				
				if(mediaPlayer.isAutoPlay()) {
					mediaview.setVisible(false);
				}
				
				
			}
		
		});
		
		mediaPlayer.setOnPlaying(new Runnable() {
			
			@Override
			public void run() {
				btn_play.setDisable(true);
				btn_pause.setDisable(false);
				btn_stop.setDisable(false);
			}
		});
		
		mediaPlayer.setOnPaused(new Runnable() {
			
			@Override
			public void run() {
				btn_play.setDisable(false);
				btn_pause.setDisable(true);
				btn_stop.setDisable(true);
				
			}
		});
		
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			
			@Override
			public void run() {
				endOfMedia = true;
				btn_play.setDisable(false);
				btn_pause.setDisable(true);
				btn_stop.setDisable(true);
			}
		});
		
		btn_play.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(endOfMedia) {
					mediaPlayer.stop();
					mediaPlayer.seek(mediaPlayer.getStartTime());
				}
				mediaPlayer.play();
				endOfMedia = false;
				
				
			}
		});
		
		btn_pause.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mediaPlayer.pause();
				
			}
		});

	}

}
