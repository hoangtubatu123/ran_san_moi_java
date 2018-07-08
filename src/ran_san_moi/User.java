package ran_san_moi;

public class User {
	private String name;
	private String level;
	
	private String score;
   public User(String name, String level, String score) {
	   this.name = name;
	   this.level = level;
	   this.score = score;
   }


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getLevel() {
	return level;
}


public void setLevel(String level) {
	this.level = level;
}


public String getScore() {
	return score;
}


public void setScore(String score) {
	this.score = score;
}


public String toString() {
	return name + " " + "level" + " " +level + " " + "score" + " " +  score;
	
}


}
