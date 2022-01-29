package studyeasy.entity;

public class Team {
	String name;
	String flag;
	String Position;
	int score;
	int wickets;
	int overs;
	int balls;

	public Team(String name2, String flag2) {
		// TODO Auto-generated constructor stub
		name = name2;
		flag = flag2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getOvers() {
		return overs;
	}

	public void setOvers(int overs) {
		this.overs = overs;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", flag=" + flag + ", score=" + score + ", wickets=" + wickets + ", overs="
				+ overs + "]";
	}

	public boolean validBall() {
		System.out.println("---------------------------------------");
		System.out.println("Teams Random value" + (int) ((Math.random() * 1000) % 3));
		System.out.println("---------------------------------------");
		if ((int) ((Math.random() * 1000) % 3) != 0)
			return true;
		return false;
	}

}
