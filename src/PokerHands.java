import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PokerHands {

	public Hand player1,player2;
	private int numOfCards=5;
	
	public PokerHands(String inputFile) {
		try {
			BufferedReader file= new BufferedReader(new FileReader(inputFile));
			String line= file.readLine();
			String black= line.substring(line.indexOf(":")+1, line.indexOf("White:"));
			String white=line.substring(line.lastIndexOf(":")+1);
			String [] cardsofBlack = black.trim().split(" ");
			String [] cardsofWhite = white.trim().split(" ");
			player1 = new Hand(cardsofBlack);
			player2 = new Hand(cardsofWhite);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void judgeHands()
	{
		//System.out.print("Black: ");
		//player1.printHand();
		//System.out.print("White: ");
		//player2.printHand();
		getCombination(player1);
		getCombination(player2);
		System.out.println(getWinner());
		
	}

	private void getCombination(Hand player) {
		
		if(hasStraightFlush(player))
		{
			player.setCombination(8);
			int temp = player.getValue(4);
			player.setHighCard(temp);
			
		}
		
		else if(hasFourOfaKind(player)) {
			player.setCombination(7);
			player.setHighCard(player.getValue(1));  
			
			
		}
		
		else if(hasFullHouse(player)) {
			player.setCombination(6);
			player.setHighCard(player.getValue(2));
			
		}
		
	else if(hasFlush(player)) {
			player.setCombination(5);
			player.setHighCard(player.getValue(4));
			
			
		}
		
	else if(hasStraight(player)) {
			player.setCombination(4);
			int temp = player.getValue(4);
			player.setHighCard(temp);
		}
		
		else if(hasThreeOfAKind(player))
		{
			player.setCombination(3);
			player.setHighCard(player.getValue(2));
			
			
		}
		
		else if(hasTwoPair(player)) {
			player.setCombination(2);
			player.setHighCard(player.getValue(3));
			
		}
		
		else if(hasPair(player))
		{
			player.setCombination(1);
			if(isPair(player.getCard(0),player.getCard(1))|| isPair(player.getCard(2),player.getCard(1)) )
			player.setHighCard(player.getValue(1));
			else
				player.setHighCard(player.getValue(3));	
		}
		
		else 
			player.setHighCard(player.getValue(4));
		
		
	}

	private boolean hasStraightFlush(Hand player) {
		return hasFlush(player) && hasStraight(player);
	}

	private boolean hasFourOfaKind(Hand player) {
		for(int i=0;i<numOfCards-3;i++) {
			if(isThreeOfAKind(player.getCard(i),player.getCard(i+1),player.getCard(i+2))&&
					isPair(player.getCard(i+2),player.getCard(i+3)))
				return true;	
			
		}
		return false;
	}

	private boolean hasFullHouse(Hand player) {

		return (isPair(player.getCard(0),player.getCard(1))&& 
				isThreeOfAKind(player.getCard(2),player.getCard(3),player.getCard(4)))|| 
				(isPair(player.getCard(3),player.getCard(4))&& 
			isThreeOfAKind(player.getCard(0),player.getCard(1),player.getCard(2)));

		
	}

	private boolean hasFlush(Hand player) {
	      for(int i=0;i<numOfCards-1;i++)
	      {
	    	  if(player.getSuit(i)!=player.getSuit(i+1))
	    		  return false;
	      }
	      
	      return true;
	}

	private boolean hasStraight(Hand player) {
		for(int i=numOfCards-1;i>0;i--)
		{
			int one=player.getValue(i);
			int two=player.getValue(i-1);
	//		if(one==14 && two==5)
	//			continue;
			if(one-two!=1)
				return false;
			
		}
		return true;
		
		
	}

	private boolean hasThreeOfAKind(Hand player) {
		for(int i=0;i<numOfCards-2;i++)
		{
			if(isThreeOfAKind(player.getCard(i),player.getCard(i+1),player.getCard(i+2)))
					return true;
			
		}
		return false;
	}

	private boolean isThreeOfAKind(Card card1, Card card2, Card card3) {
		return card1.getValue()==card2.getValue() && card2.getValue()==card3.getValue();
	}

	private boolean hasTwoPair(Hand player) {
		for(int i=0;i<numOfCards-3;i++)
		{
			if(isPair(player.getCard(i),player.getCard(i+1)))
					for(int j=i+2;j<numOfCards-1;j++) {
						if(isPair(player.getCard(j),player.getCard(j+1)))
							return true;
					}
			
		}
		return false;
	}

	private boolean hasPair(Hand player) {
	
		for(int i=0;i<numOfCards-1;i++)
		{
			if(isPair(player.getCard(i),player.getCard(i+1)))
					return true;
			
		}
		return false;
		
	}

	private boolean isPair(Card card1, Card card2) {
		return card1.getValue()==card2.getValue();
	}
	
	public String getWinner() {
		if(player1.getCombination()==player2.getCombination()) {
			if(player1.getHighCard() == player2.getHighCard())
				return resolveTie();
			else 
			return player1.getHighCard() > player2.getHighCard() ? "Black wins - with high card: "+player1.highCardText(player1.getHighCard()):"White wins - with high card: "+player2.highCardText(player2.getHighCard());
		}
		else
			return player1.getCombination() > player2.getCombination() ? "Black wins with "+player1.combinationText(player1.getCombination()):"White wins with "+player2.combinationText(player2.getCombination());
				
	}

	private String resolveTie() {
		for(int i=numOfCards-1;i>=0;i--) {
			if(player1.getValue(i)==player2.getValue(i))
				continue;
			return player1.getValue(i) > player2.getValue(i) ? "Black wins - with card value: "+player1.getValue(i):"White wins - with card value: "+player2.getValue(i);
			
		}
			
		return "Tie";
	}
	
}
