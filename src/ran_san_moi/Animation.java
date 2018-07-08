package ran_san_moi;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
   public Image[] images;
   public int n;
   public int currentImage ;
   public Animation() {
	  n = 0;
	  currentImage= 0 ;
   }
   public void addImage(Image image) {
	    n++;
	    Image[] arr = images;
	    
	    images = new Image[n];
	    for(int i = 0; i<n-1; i++) {
	    	images[i] = arr[i];
	    }
	    images[n-1] = image;
        
	   }
   
   
   
   public void update() {
	   currentImage++;
	   if(currentImage >= n) currentImage = 0;
   }
   public Image getCurrentImage() {
	   return images[currentImage];
   }
   
}
