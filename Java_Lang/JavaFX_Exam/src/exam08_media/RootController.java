package exam08_media;

import java.net.URL;
import java.util.ResourceBundle;

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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Media media = new Media(getClass()
				.getResource("media/Dream Glow.mp4").toString());
		
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaview.setMediaPlayer(mediaPlayer);
		mediaPlayer.setOnReady(new Runnable() {
			
			@Override
			public void run() {
				btn_play.setDisable(false);
				btn_pause.setDisable(true);
				btn_stop.setDisable(true);
			
				if(mediaPlayer.isAutoPlay()) {
					mediaview.setVisible(false);
				}
			}
		});
		
		
	}
	
}
