package _06_overloading;

import java.awt.Color;

public class LeagueOptionPaneRunner {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		LeagueOptionPane pane = new LeagueOptionPane();
		pane.showMessageDialog("amasing");
		pane.showMessageDialog("amasing number 2", "title");
		pane.showMessageDialog("amasing numer 3???!!" , "title number 2", "java.png");
		pane.showMessageDialog("amasing number 4 :DD", "Challenge title", "league.png", Color.BLACK);
		
	}
}
