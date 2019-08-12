package exam11_mouse_event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RootController implements Initializable {

	@FXML Label lb_level;
	@FXML ImageView iv1, iv2, iv3, iv4, iv5, iv6;
	@FXML ImageView iv_level2_1, iv_level2_2, iv_level2_3, iv_level2_4,
	iv_level2_5, iv_level2_6, iv_level2_7, iv_level2_8,
	iv_level2_9, iv_level2_10, iv_level2_11, iv_level2_12,
	iv_level2_13, iv_level2_14, iv_level2_15, iv_level2_16;
	@FXML Button btn_start, btn_restart, btn_next_level, btn_before_level;
	
	
	public static final int GAMEIN = 0;
	public static final int GAMEREADY = 1;
	public static final int GAMESTART = 2;
	public static final int GAMEEND = 3;
	
	public static final int LEVEL1 = 1;
	public static final int LEVEL2 = 2;
	public static final int LEVEL3 = 3;
	
	private Stage primaryStage; //실행한 윈도우 //이 위에 다이얼로그 띄우기
	int current_level;
	public void setPrimaryStage(Stage primaryStage, int level) {
		this.primaryStage = primaryStage;
		current_level = level;
		if(current_level == LEVEL1) {
			row = 2; column = 3;
			imageViewID = new ImageView[row][column];
			
			imageViewID[0][0] = iv1;
			imageViewID[0][1] = iv2;
			imageViewID[0][2] = iv3;
			imageViewID[1][0] = iv4;
			imageViewID[1][1] = iv5;
			imageViewID[1][2] = iv6;
			
			
			CardShuffle(row, column);
			onDraw();
		}
		else if(current_level == LEVEL2) {
			row = 4; column = 4;
			imageViewID = new ImageView[row][column];
			
			imageViewID[0][0] = iv_level2_1;
			imageViewID[0][1] = iv_level2_2;
			imageViewID[0][2] = iv_level2_3;
			imageViewID[0][3] = iv_level2_4;
			imageViewID[1][0] = iv_level2_5;
			imageViewID[1][1] = iv_level2_6;
			imageViewID[1][2] = iv_level2_7;
			imageViewID[1][3] = iv_level2_8;
			imageViewID[2][0] = iv_level2_9;
			imageViewID[2][1] = iv_level2_10;
			imageViewID[2][2] = iv_level2_11;
			imageViewID[2][3] = iv_level2_12;
			imageViewID[3][0] = iv_level2_13;
			imageViewID[3][1] = iv_level2_14;
			imageViewID[3][2] = iv_level2_15;
			imageViewID[3][3] = iv_level2_16;
			
			CardShuffle(row, column);
			
			
			onDraw();
			
		}
	}
	
		
	String[] CardLevel1Image = {"image/card_front_red.png", "image/card_front_blue.png", "image/card_front_black.png"};
	String[] CardLevel2Image = {"image/card_level1_1.jpg", "image/card_level1_2.jpg",
			"image/card_level1_3.jpg","image/card_level1_4.jpg","image/card_level1_5.jpg",
			"image/card_level1_6.jpg","image/card_level1_7.jpg","image/card_level1_8.jpg"};
	
	int[] Level1ContainX = {150,300,450};
	int[] Level1ContainY = {25,225};
	
	int[] Level2ContainX = {150, 300, 450, 600};
	int[] Level2ContainY = {25, 200, 375, 550};
	
	ImageView[][] imageViewID;
	
	Card m_Card[][];
	Card m_Shuffle_Card1 = null, m_Shuffle_Card2 = null;
	Card m_Selected_Card1 = null, m_Selected_Card2 = null;

	int row, column;
	int game_state = GAMEIN;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		CardMatchThread thread = new CardMatchThread(this);
		thread.start();
	}
	

	
	public void CardShuffle(int row, int column) {
		int x, y, temp;
		int image_num = 4;
		m_Card = new Card[row][column];
		
		
		for(x = 0; x < row; x++) {
			for(y = 0; y < column; y++) {
				if(current_level == LEVEL1) m_Card[x][y] = new Card(y+1);
				else if(current_level == LEVEL2) {
					 m_Card[x][y] = new Card(image_num);
					 image_num++;
					 if(image_num == 12) image_num = 4;
				}	
			}
		}
		
		for(int i = 0; i < 100; i++) {
			x = (int)(Math.random() * row);
			y = (int)(Math.random() * column);
			
			m_Shuffle_Card1 = m_Card[x][y];
			
			x = (int)(Math.random() * row);
			y = (int)(Math.random() * column);
			
			m_Shuffle_Card2 = m_Card[x][y];
			
			
			temp = m_Shuffle_Card1.m_Image;
			m_Shuffle_Card1.m_Image = m_Shuffle_Card2.m_Image;
			m_Shuffle_Card2.m_Image = temp;
			
			m_Shuffle_Card1 = null;
			m_Shuffle_Card2 = null;
			
		}
	}
	
	public void OnMouseClicked(MouseEvent event) { //event : 임베디드로 치면 외부 인터럽트(timer 인터럽트 같은 것)가 발생한 것
		int px = (int)event.getX();
		int py = (int)event.getY();
		
		if(game_state == GAMEREADY) {
			game_state = GAMESTART;
			for(int x = 0; x < row; x++) {
				for(int y = 0; y < column; y++) {
					m_Card[x][y].m_State = Card.CARD_CLOSE;
				}
			}
			onDraw();
		}
		else if(game_state == GAMESTART) {
			for(int x = 0; x < row; x++) {
				for(int y = 0; y < column; y++) {
					if(current_level == LEVEL1) {
						if(checkContains(Level1ContainX[y], Level1ContainY[x], px, py)) {
							if(m_Card[x][y].m_State == Card.CARD_CLOSE) {
								if(m_Selected_Card1 == null) {
									m_Selected_Card1 = m_Card[x][y];
									m_Card[x][y].m_State = Card.CARD_PLAYER_OPEN;
								}
								else if(m_Selected_Card2 == null) {
									m_Selected_Card2 = m_Card[x][y];
									m_Card[x][y].m_State = Card.CARD_PLAYER_OPEN;
								}
							}
						}
					}
					else if(current_level == LEVEL2) {
						if(checkContains(Level2ContainX[y], Level2ContainY[x], px, py)) {
							if(m_Card[x][y].m_State == Card.CARD_CLOSE) {
								if(m_Selected_Card1 == null) {
									m_Selected_Card1 = m_Card[x][y];
									m_Card[x][y].m_State = Card.CARD_PLAYER_OPEN;
								}
								else if(m_Selected_Card2 == null) {
									m_Selected_Card2 = m_Card[x][y];
									m_Card[x][y].m_State = Card.CARD_PLAYER_OPEN;
								}
							}
						}
					}
				}
			}
		}
		
		onDraw();
	}
	
	public boolean checkContains(int x, int y, int px, int py) {//범위 안에 들어오면 true return 
		//x, y : 카드 좌표 //px, py : 마우스 클릭 좌표
		if((x < px) && (px < (x + 100)) && (y < py) && (py < (y+150))) {
			// 카드를 클릭 했다면
			return true;
		}
		else return false;
	}
	
	void onDraw() {
		
		for(int x = 0; x < row; x++) {
			for(int y = 0; y < column; y++) {
				
				
				if(m_Card[x][y].m_State == Card.CARD_CLOSE) {
					imageViewID[x][y].setImage(new Image(getClass().getResource("image/card_backside.png").toString()));
					 
				}
				else {
					if(current_level == LEVEL1) {
						imageViewID[x][y].setImage(new Image(getClass().getResource(CardLevel1Image[m_Card[x][y].m_Image-1]).toString()));
					}
					else if(current_level == LEVEL2) {
						imageViewID[x][y].setImage(new Image(getClass().getResource(CardLevel2Image[m_Card[x][y].m_Image-4]).toString()));
					}
				}
				
			}
		}
	}
	
	void checkMatch() {
		if(m_Selected_Card1 != null && m_Selected_Card2 != null) {
			if(m_Selected_Card1.m_Image == m_Selected_Card2.m_Image) {
				m_Selected_Card1.m_State = Card.CARD_MATCH;
				m_Selected_Card2.m_State = Card.CARD_MATCH;
				
				m_Selected_Card1 = null;
				m_Selected_Card2 = null;
				
				for(int x = 0; x < row; x++) {
					for(int y = 0; y < column; y++) {
						if(m_Card[x][y].m_State != Card.CARD_MATCH) {
							onDraw();
							return;
						}
					}
				}
				game_state = GAMEEND;
				btn_restart.setDisable(false);
				if(current_level == LEVEL1)btn_next_level.setDisable(false);
				else if(current_level == LEVEL2) btn_before_level.setDisable(false);
			}
			else {
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				m_Selected_Card1.m_State = Card.CARD_CLOSE;
				m_Selected_Card2.m_State = Card.CARD_CLOSE;
				
				m_Selected_Card1 = null;
				m_Selected_Card2 = null;
				
			}
			onDraw();
		}
	
	}
	
	public void onActionBtnStart(ActionEvent event) {
		
		game_state = GAMEREADY;
		btn_start.setDisable(true);
		btn_restart.setDisable(true);
		if(current_level == LEVEL1) btn_next_level.setDisable(true);
		else if(current_level == LEVEL2) btn_before_level.setDisable(true);
		for(int x = 0; x < row; x++) {
			for(int y = 0; y < column; y++) {
				m_Card[x][y].m_State = Card.CARD_SHOW;
			}
		}
		onDraw();
	}
	
	public void onActionBtnRestart(ActionEvent event) {
		btn_start.setDisable(false);
		btn_restart.setDisable(true);
		if(current_level == LEVEL1)btn_next_level.setDisable(true);
		else if(current_level == LEVEL2) btn_before_level.setDisable(true);
		if(current_level == LEVEL1) {
			row = 2; column = 3;
			game_state = GAMEREADY;
						
			CardShuffle(row, column);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			onDraw();				
		}
		else if(current_level == LEVEL2) {
			row = 4; column = 4;
			game_state = GAMEREADY;
			
			CardShuffle(row, column);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			onDraw();				
		}
	}

	public void onActionBtnNextLevel(ActionEvent event) {
		
		try {
			FXMLLoader loadNextLevel = new FXMLLoader(getClass().getResource("NextLevel.fxml"));
			Parent nextLevel = loadNextLevel.load();
			
			RootController controller = loadNextLevel.getController(); //root.fxml에 적용된 컨트롤러 객체를 넣어주는 것
			controller.setPrimaryStage(primaryStage, 2);
			
			Scene sceneNextLevel = new Scene(nextLevel);
			
			primaryStage.setScene(sceneNextLevel);
			primaryStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	public void onActionBtnBeforeLevel(ActionEvent event) {
		
		try {
			FXMLLoader loadNextLevel = new FXMLLoader(getClass().getResource("Root.fxml"));
			Parent nextLevel = loadNextLevel.load();
			
			RootController controller = loadNextLevel.getController(); //root.fxml에 적용된 컨트롤러 객체를 넣어주는 것
			controller.setPrimaryStage(primaryStage, 1);
			
			Scene sceneNextLevel = new Scene(nextLevel);
			
			primaryStage.setScene(sceneNextLevel);
			primaryStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
