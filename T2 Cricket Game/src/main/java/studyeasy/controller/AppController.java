package studyeasy.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import studyeasy.GameService.PlayerModel;
import studyeasy.entity.Team;

@Controller
public class AppController {

	static String json = "C:\\Users\\ranit\\Desktop\\ZPFG.json";

	@RequestMapping("/")
	public String CricketApp(HttpServletRequest request) {
		List<Team> teamModel = new ArrayList<Team>();
		JSONArray teams = null;
		try {
			teams = (JSONArray) new JSONParser().parse(new FileReader(json));
			System.out.println(teams);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Object a : teams) {
			JSONObject teamObj = (JSONObject) a;
			String name = (String) teamObj.get("name");
			String flag = (String) teamObj.get("flag");
			teamModel.add(new Team(name, flag));
		}
		String myObjectId = UUID.randomUUID().toString();
		request.getSession().setAttribute(myObjectId, teamModel);
		request.setAttribute("myObjectId", myObjectId);
		return "SelectTeam";
	}

	@GetMapping("OtherTeamSelect")
	public String SelectTeam(@RequestParam("myObjectId") String myObjectIdForm,
			@RequestParam("myObjectId3") String myObjectIdForm1, @RequestParam("name") String name,
			@RequestParam("flag") String flag, HttpServletRequest request) {
		List<Team> activePlayers = (List<Team>) request.getSession().getAttribute(myObjectIdForm1);
		if (activePlayers == null) {
			activePlayers = new ArrayList<Team>(2);
		}
		String teamName = name;
		String teamFlag = flag;
		String myObjectId = myObjectIdForm;
		System.out.println("UUID = " + myObjectId);
		List<Team> myObjectList = (List<Team>) request.getSession().getAttribute(myObjectId);
		System.out.println("ListSize = " + myObjectList.size());
		for (Team obj : myObjectList) {
			if (obj.getName().equals(teamName) && obj.getFlag().equals(teamFlag)) {
				System.out.println("[Server:] Name = " + obj.getName());
				System.out.println("[Server:] Flag = " + obj.getFlag());
				activePlayers.add(obj);
				myObjectList.remove(obj);
				break;
			}
		}
		String myObjectId2 = UUID.randomUUID().toString();
		String myObjectId3 = UUID.randomUUID().toString();
		request.getSession().setAttribute(myObjectId2, myObjectList);
		request.getSession().setAttribute(myObjectId3, activePlayers);
		request.setAttribute("myObjectId", myObjectId2);
		request.setAttribute("myObjectId3", myObjectId3);
		return "SelectTeam";
	}

	@GetMapping("GameTime")
	public String StartGame(@RequestParam("myObjectId3") String myObjectIdForm, HttpServletRequest request) {
		String myObjectId = myObjectIdForm;
		System.out.println("UUID = " + myObjectId);
		List<Team> myObjectList = (List<Team>) request.getSession().getAttribute(myObjectId);
		myObjectList.get(0).setPosition("Batting");
		myObjectList.get(1).setPosition("Bowling");
		System.out.println("ListSize = " + myObjectList.size());
		String myObjectId2 = UUID.randomUUID().toString();
		request.getSession().setAttribute(myObjectId2, myObjectList);
		boolean isGameOver = false;
		request.setAttribute("myObjectId", myObjectId2);
		request.setAttribute("isGameOver", isGameOver);
		return "GameTime";
	}

	@GetMapping("PlayNextBall")
	public String NextBall(@RequestParam("myObjectId") String myObjectIdForm, HttpServletRequest request) {
		System.out.println("UUID = " + myObjectIdForm);
		List<Team> activePlayers = (List<Team>) request.getSession().getAttribute(myObjectIdForm);
		System.out.println("activePlayers sie = " + activePlayers.size());
		PlayerModel playerGameModel = new PlayerModel();
		int batScore = playerGameModel.playGame(
				(activePlayers.get(0).getPosition().equals("Batting")) ? activePlayers.get(0) : activePlayers.get(1),
				(activePlayers.get(0).getPosition().equals("Balling")) ? activePlayers.get(0) : activePlayers.get(1));
		boolean isGameOver = playerGameModel.isGameOver(
				(activePlayers.get(0).getPosition().equals("Batting")) ? activePlayers.get(0) : activePlayers.get(1),
				(activePlayers.get(0).getPosition().equals("Balling")) ? activePlayers.get(0) : activePlayers.get(1));
		System.out.println("siGameOver = " + isGameOver);
		String myObjectId = UUID.randomUUID().toString();
		System.out.println("UUID =" + myObjectId);
		request.getSession().setAttribute(myObjectId, activePlayers);
		request.setAttribute("myObjectId", myObjectId);
		request.setAttribute("batScore", batScore);
		request.setAttribute("isGameOver", isGameOver);
		return "GameTime";
	}
}
