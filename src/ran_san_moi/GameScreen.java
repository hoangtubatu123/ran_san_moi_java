package ran_san_moi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
// lớp để vẽ các đối 
public class GameScreen extends JPanel implements Runnable {
	static int [][] bg = new int [20][20];
	static int padding = 10; 
	static int WIDTH = 400;
	static int HEIGHT = 400;
	static boolean isPlaying = false;
	static boolean gameOver = false;
	static boolean enableText  = true;
	static int currentLevel = 1;
	static int scrore = 0;
	conRan ran ;
	int a = 0;
	
	Thread thread ;
    public GameScreen() {
    	ran = new conRan();
    	Data.loadImage();
    	Data.loadAllAnim();
    	thread = new Thread(this);
    	thread.start();
         bg[1][2] = 1;
         bg[4][7] = 0;
         bg[5][5] = 2;
    }
    
    public void paintBg(Graphics g) {
    	g.setColor(Color.black);
    	g.fillRect(0, 0, WIDTH + padding * 2 + 300, HEIGHT + padding * 2);
    	for (int i = 0; i< 20; i++) {
    		for (int j = 0; j<20; j++) {	
//    			g.fillRect(i * 20+1, j * 20+1, 18, 18);
    			if(bg[i][j] == 2) {
//    				g.setColor(Color.red);
//    				g.fillRect(i * 20+1, j * 20+1, 18, 18);
//    				g.setColor(Color.gray);
    				g.drawImage(Data.Worm.getCurrentImage(), i* 20 - 7 + padding, j * 20-7 + padding, null );
    			}
    		}
    	}
    }
    private void veKhung(Graphics g) {
    	g.setColor(Color.orange);
    	g.drawRect(0, 0, WIDTH + padding*2, HEIGHT + padding * 2);
    	g.drawRect(2, 2, WIDTH + padding*2 - 4, HEIGHT + padding * 2 - 4);
    	
    	g.drawRect(0, 0, WIDTH + padding*2 + 300, HEIGHT + padding * 2);
    	g.drawRect(2, 2, WIDTH + padding*2 - 4 + 300, HEIGHT + padding * 2 - 4);
    }
    public void paint(Graphics g) {
    	paintBg(g);
    	ran.veRan(g);
    	veKhung(g);
    	if(!isPlaying) {
    		if(enableText) {
    		g.setColor(Color.white);
    		g.setFont(g.getFont().deriveFont(18.0f));
    		g.drawString("PRESS SPACE TO PLAY GAME", 60, 200);
    	}
    	}
    	if(gameOver) {
    		g.setColor(Color.white);
    		g.setFont(g.getFont().deriveFont(28.0f));
    		g.drawString("Game Over", 100, 250);
    	}
    	g.setColor(Color.white);
		g.setFont(g.getFont().deriveFont(28.0f));
		g.drawString("Level :" + currentLevel, 450, 50);
		
		g.setFont(g.getFont().deriveFont(28.0f));
		g.drawString("Score :" + scrore, 450, 100);
		
		for (int i = 0; i< FrameScreen.users.size(); i++) {
			g.setFont(g.getFont().deriveFont(18.0f));
			g.drawString(FrameScreen.users.get(i).toString(), 450, i * 30 + 150);
		}
    }
    public void run() {
    	long t = 0;
    	long t2 = 0;
    	while(true) {
    		
    		if(System.currentTimeMillis() - t2 > 300) {  
    			enableText = !enableText;
    			t2 = System.currentTimeMillis();
    		}
    		if(isPlaying) {
    			if(System.currentTimeMillis() - t > 200) {
        			Data.Worm.update();
        			t = System.currentTimeMillis();
        		}
        		ran.update();
    		}
    		
//    		System.out.println(String.valueOf(a));
    		repaint(); // gọi lại phương thức 
    		try {
    			thread.sleep(20);
    		}catch (InterruptedException e) {}
    }
}
}
