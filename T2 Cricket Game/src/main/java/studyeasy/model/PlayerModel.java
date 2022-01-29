package studyeasy.model;

import studyeasy.entity.Team;

public class PlayerModel {

	public int playGame(Team battingTeam, Team ballingTeam) {
		// TODO Auto-generated method stub
		int nextBallScore = 0;
		if (ballingTeam.validBall()) {
			nextBallScore = scoreValue();
			if (nextBallScore == 7) {
				System.out.println("Out");
				battingTeam.setWickets(battingTeam.getWickets() + 1);
				if (battingTeam.getWickets() == 3) {
					System.out.println("Innings over for batting team");
					battingTeam.setPosition("balling");
					ballingTeam.setPosition("batting");

				}
			} else {
				battingTeam.setScore(battingTeam.getScore() + nextBallScore);
			}

			if (battingTeam.getBalls() == 6) {
				battingTeam.setOvers(battingTeam.getOvers() + 1);
				battingTeam.setBalls(0);
				if (battingTeam.getOvers() == 2) {
					System.out.println("Batting is Over");
					nextBallScore = 111;
					battingTeam.setPosition("Balling");
					ballingTeam.setPosition("Batting");
				}
			} else {
				battingTeam.setBalls(battingTeam.getBalls() + 1);
			}
		} else {
			battingTeam.setScore(battingTeam.getScore() + 1);
		}
		return nextBallScore;
	}

	public boolean isGameOver(Team battingTeam, Team ballingTeam) {
		// TODO Auto-generated method stub
		if (((battingTeam.getWickets() == 3) || (battingTeam.getOvers() == 2))
				&& ((ballingTeam.getWickets() == 3) || (ballingTeam.getOvers() == 2)))
			return true;
		return false;
	}

	private int scoreValue() {
		System.out.println("Random value" + Math.random());
		return (int) ((Math.random() * 1000) % 8);
	}

}
