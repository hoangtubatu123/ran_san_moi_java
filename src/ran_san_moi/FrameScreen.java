package ran_san_moi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class FrameScreen extends JFrame {
	GameScreen gameScreen ;
	public static ArrayList<User> users;  // load user
	
    public FrameScreen() {
    	gameScreen = new GameScreen();
    	add(gameScreen);
    	this.addKeyListener(new handler());
    	users = new ArrayList<User>();
    	readData();
    	this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				updateData();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		} );
    	
    }
    public static void main(String arg[]) {
    	FrameScreen f = new FrameScreen();
    	f.setSize(750, 500);
    	f.setVisible(true);
    }
    private class handler implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {	
		}

		@Override
		public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_UP) {
						gameScreen.ran.setVector(conRan.GO_UP);
					}
					if(e.getKeyCode() == KeyEvent.VK_DOWN) {
						gameScreen.ran.setVector(conRan.GO_DOWN);
					}
					if(e.getKeyCode() == KeyEvent.VK_LEFT) {
						gameScreen.ran.setVector(conRan.GO_LEFT);
					}
					if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
						gameScreen.ran.setVector(conRan.GO_RIGHT);
					}
					if(e.getKeyCode() == KeyEvent.VK_SPACE) {
						GameScreen.isPlaying = !GameScreen.isPlaying;
						if(GameScreen.gameOver) { 
							GameScreen.gameOver = false;
						    gameScreen.ran.resetGame();	
						}
					}
					
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		
    	
    }
    public static void updateData() {
    	try {
    		FileWriter fw = new FileWriter("data/data.txt");
        	BufferedWriter bw = new BufferedWriter(fw);
        	for(User u:users) {
        		bw.write(u.getName() + " " + u.getLevel() + " " + u.getScore());
        		bw.newLine();
        	}
        	
        	bw.close();
    	}catch(IOException e) {}
    	
    }
    public static void readData() {
    	try {
    		FileReader fr = new FileReader("data/data.txt");
    		BufferedReader br = new BufferedReader(fr); // doc du lieu
    		
    		String line = null;
    		while((line = br.readLine()) != null) {
    			String[] str = line.split(" ");
    			users.add(new User(str[0], str[1], str[2]));
    		}
    		 
    		br.close();
    	} catch (IOException e){
    		
    	}
    	
    	
    }
}
