package MainPackage;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame implements GameStartListener, PropertyChangeListener{


	private static final long serialVersionUID = 1L;
	public ConfigFrame cfgFrame;
	public GamePanel gamePanel;
	public JLabel lblGracz_1;
	public JLabel lblGracz_2;
	public Player player1;
	public Player player2;
	public List<Player> playerList;
	
	@Override
	public void gameStart() {
		createGame();
	}
	
	public void createGame()
	{
		player1 = cfgFrame.getPlayer1();
		player2 = cfgFrame.getPlayer2();
		player1.myLabel = lblGracz_1;
		player2.myLabel = lblGracz_2;
		
		//Lista graczy dla u�atwienia prze��czania pomi�dzy nimi
		playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		
		for (int i = 0; i< playerList.size(); i++)
		{
			playerList.get(i).myLabel.setText("" + playerList.get(i).getName()); 
		}
		
		gamePanel.addPropertyChangeListener(this);
		start();
	}
	
	public void start()
	{
		changeTurns();
	}
	
	public void changeTurns()
	{
		//Zamiana pozycji graczy - ten z pozycj� 0 zawsze b�dzie aktywny
		Collections.swap(playerList, 0, 1);
		
		Player p = playerList.get(0);
		p.myLabel.setForeground(p.getColor());
		gamePanel.setPlayerColor(p.getColor());
		
		Player old = playerList.get(1);
		old.myLabel.setForeground(Color.BLACK);
	}
	
	public void isGameWon()
	{
		//tu sprawdzanie, czy kto� wygra�
		changeTurns();
	}
	
	//Uruchamia si�, gdy ruch zosta� wykonany prawid�owo - GamePanel, linia 90-91
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		gamePanel.resetSuccess();
		isGameWon();
	}
}