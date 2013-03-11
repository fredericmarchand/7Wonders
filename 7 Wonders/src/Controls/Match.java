package Controls;

import java.util.ArrayList;
import Player.Player;
import Structures.Structure;
import java.util.Scanner;
import java.util.Random;

public class Match {
	
	
	private ArrayList<Player> players;
	@SuppressWarnings("unused")
	private int age, turn;
	private ArrayList<Structure> age1Deck, age2Deck, age3Deck, discarded;
	
	public Match()
	{
		players = new ArrayList<Player>();
		age = 0;
		turn = 0;
		age1Deck = null;
		age2Deck = null;
		age3Deck = null;
		discarded = null;
	}
	
	public void runConsoleGame()
	{
		Scanner in = new Scanner(System.in);
		Random r = new Random();
		discarded = new ArrayList<Structure>();
		
		@SuppressWarnings("unused")
		Match newMatch = new Match();
		System.out.println("Please input how many players will play: ");
		int numPlayers = (int)Integer.parseInt(in.nextLine());
		
		age1Deck = CardHandler.BuildAge1Deck(numPlayers);
		age2Deck = CardHandler.BuildAge2Deck(numPlayers);
		age3Deck = CardHandler.BuildAge3Deck(numPlayers);
		
		for ( int i = 0; i < numPlayers; ++i )
		{
			System.out.println("Please input a username for player " + (i + 1) + ": ");
			String un = in.nextLine();
			players.add(new Player(un, (i * r.nextInt())));
		}
		CardHandler.DistributeRandomWonderBoards(players, 0);
		for ( Player p: players )
		{
			p.getResources().addCoins(3);
		}
		
		for ( int age = 1; age < 4; ++age )
		{
			System.out.println("\nAge " + age + "\n");
			if ( age == 1 )      CardHandler.DistributeCards(players, age1Deck);
			else if ( age == 2 ) CardHandler.DistributeCards(players, age2Deck);
			else if ( age == 3 ) CardHandler.DistributeCards(players, age3Deck);
			
			for ( int i = 0; i < 6; ++i )
			{
				 //each player makes their moves
				 for ( Player p: players )
				 {
						
					 System.out.println("Its your turn " + p.getUsername() + ", please input the ID of the card you want to play: ");
					 int l = 0;
					 for ( Structure s: p.getCards() ) 
					 {
					 	System.out.println(l++ + " " + s.getName());
					 }
					 int move = Integer.parseInt(in.nextLine());
					 p.chooseCard(move);
					 if ( p.getCards().size() == 1 ) p.discardHand(discarded);
					 System.out.println("Please select your move:\n1 - Build the Structure\n2 - Build a Stage of your Wonder\n3 - Discard the card for 3 coins");
					 move = Integer.parseInt(in.nextLine());
					 @SuppressWarnings("unused")
					 boolean valid = true;
					 switch ( move )
					 {
					 	case 1:
					 		valid = p.buildStructure();
					  		break;
					  	case 2:
					  		valid = p.buildStage();
					  		break;
					  	case 3:
					  		p.discard(discarded);
					  		break;
					  	default: System.out.println("Invalid... Please try again: ");
					 }
				  }
				  CardHandler.PassCardsToNeighbors(players, age);
			}
		}
		in.close();  
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Match m = new Match();
		m.runConsoleGame();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public int getAge() {
		return age;
	}

	public int getTurn() {
		return turn;
	}

	public ArrayList<Structure> getAge1Deck() {
		return age1Deck;
	}

	public ArrayList<Structure> getAge2Deck() {
		return age2Deck;
	}

	public ArrayList<Structure> getAge3Deck() {
		return age3Deck;
	}

	public ArrayList<Structure> getDiscarded() {
		return discarded;
	}

}
