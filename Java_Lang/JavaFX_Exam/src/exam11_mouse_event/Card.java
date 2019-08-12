package exam11_mouse_event;

public class Card {

	public static final int CARD_SHOW = 1;
	public static final int CARD_CLOSE = 2;
	public static final int CARD_PLAYER_OPEN = 3;
	public static final int CARD_MATCH = 4;

	
	public static final int IMG_RED = 1;
    public static final int IMG_BLUE = 2;
    public static final int IMG_BLACK = 3;
    
    public static final int IMG_LEVEL1_1 = 4;
    public static final int IMG_LEVEL1_2 = 5;
    public static final int IMG_LEVEL1_3 = 6;
    public static final int IMG_LEVEL1_4 = 7;
    public static final int IMG_LEVEL1_5 = 8;
    public static final int IMG_LEVEL1_6 = 9;
    public static final int IMG_LEVEL1_7 = 10;
    public static final int IMG_LEVEL1_8 = 11;
	
	int m_State;
	int m_Image;
	
	Card(int _image) {
		m_State = CARD_CLOSE;
		m_Image = _image;
	}
}
