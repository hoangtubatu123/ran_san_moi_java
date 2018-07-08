package ran_san_moi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JOptionPane;

public class conRan {
     int doDai = 3;
     int [] x;
     int [] y;
     long t1 = 0;
     long t2 = 0;
     int speed = 200;
     int maxLength = 10;
     boolean  updateChangeVector = false;
     public static int GO_UP = 1; // biến_tinh
     public static int GO_DOWN = -1;
     public static int GO_LEFT = 2;
     public static int GO_RIGHT = -2;
     int vector  = conRan.GO_DOWN;
     public conRan() {
    	 x = new int [20];
    	 y = new int[20];
    	 x[0] = 5;
    	 y[0] = 4;
    	 x[1] = 5;
    	 y[1]= 3;
    	 x[2] = 5;
    	 y[2] = 2;
    	 
     }
     
     public void resetGame() {
    	 x = new int [500];
    	 y = new int[500];
    	 x[0] = 5;
    	 y[0] = 4;
    	 x[1] = 5;
    	 y[1]= 3;
    	 x[2] = 5;
    	 y[2] = 2;
    	 doDai = 3;
    	
     }
     public void setVector(int v) { // huong cua con ran
    	if(vector != -v && updateChangeVector) {
    		vector = v;
    		updateChangeVector = false;
    	}  	 
     }
     public boolean cordinateInSnake(int x1, int y1) {
    	 boolean kt = true;
    	 for (int i = 0; i<doDai; i++) {
    		 if(x[i] == x1 && y[i] == y1 ) {kt = true;}
    		 else { kt = false; break;}
    	 }
    	 return kt;
     }
     public Point getCordinates() {
    	 Random r = new Random();
    	 int x,y;
    	 do {
    	  x= r.nextInt(19);
    	  y = r.nextInt(19);
    	 }while(cordinateInSnake(x, y));
    	 return new Point(x, y) ;
     }
     
     public int getCurrentSpeed() {
    	 int speed = 200;
    	 for (int i = 0; i< GameScreen.currentLevel; i++) {
    		 speed*=0.8;
    	 }
    	 return speed;
     }
     public void update() {
    	 // cập nhật vị trí con 
    	 if(doDai == maxLength) {
    		 GameScreen.isPlaying = false;
    		 resetGame();
    		 GameScreen.currentLevel++;
    		 maxLength +=5;
    		 speed = getCurrentSpeed();
    	 }
    	 
    	 // game over
    	 for (int i = 1; i< doDai; i++) {
    		 if(x[0] == x[i] && y[i] == y[0]) {
    			 String name =  JOptionPane.showInputDialog("Mời bạn nhập tên :");
    			 FrameScreen.users.add(new User(name, String.valueOf(GameScreen.currentLevel), String.valueOf(GameScreen.scrore)));
//    			 FrameScreen.updateData();
    			 
    			 GameScreen.isPlaying = false;
    			 GameScreen.gameOver = true;
    			 GameScreen.scrore = 0;
    	    	 GameScreen.currentLevel = 1;
    	    	 
    		 }
    	 }
    	 
    	 if(System.currentTimeMillis() - t2 > 200) {
    		 updateChangeVector = true;
    		 Data.HeadGoUp.update();
    		 Data.HeadGoDown.update();
    		 Data.HeadGoLeft.update();
    		 Data.HeadGoRight.update();
    		 t2 = System.currentTimeMillis();
    	 }
    	 
    	 if(System.currentTimeMillis() - t1 > speed) {
    		
    		 if(GameScreen.bg[x[0]][y[0]] == 2) {
    			 doDai ++;
    			 GameScreen.bg[x[0]][y[0]] = 0;
    			 GameScreen.bg[getCordinates().x][getCordinates().y] = 2; 
    		     GameScreen.scrore += 100;
    		 }
    		 
    		 for (int i = doDai - 1; i>0; i--) {
    			 x[i] = x[i-1];
    			 y[i] = y[i-1];
    		 }
    		if(vector == conRan.GO_UP) y[0]--;
    		if(vector == conRan.GO_DOWN) y[0]++;
    		if(vector == conRan.GO_LEFT) x[0]--;
    		if(vector == conRan.GO_RIGHT) x[0]++;
    		
    		if(x[0]<0) x[0] = 19;
    		if(x[0]>19) x[0] = 0;
    		if(y[0]<0) y[0] =19;
    		if(y[0] > 19) y[0] = 0;
        	 t1 = System.currentTimeMillis();
    	 }
    	
     }
     public void veRan (Graphics g) {
    	 g.setColor(Color.red);
    	 for (int i = 0; i<doDai; i++) {
    		 g.drawImage(Data.imageBody, x[i] * 20 + GameScreen.padding, y[i] * 20+ GameScreen.padding, null);
    	 }
    	 
    	 if(vector==conRan.GO_UP)g.drawImage(Data.HeadGoUp.getCurrentImage(), x[0] * 20 - 6 + GameScreen.padding, y[0] * 20 - 6+ GameScreen.padding, null);
    	 else if(vector == conRan.GO_RIGHT) g.drawImage(Data.HeadGoRight.getCurrentImage(), x[0] * 20 - 6 + GameScreen.padding, y[0] * 20 - 6 + GameScreen.padding, null);
    	 else if(vector == conRan.GO_LEFT) g.drawImage(Data.HeadGoLeft.getCurrentImage(), x[0] * 20 - 6 + GameScreen.padding, y[0] * 20 - 6 + GameScreen.padding, null);
    	 else  g.drawImage(Data.HeadGoDown.getCurrentImage(), x[0] * 20 - 6 + GameScreen.padding, y[0] * 20 - 6 + GameScreen.padding, null);
     }
}
