import java.util.Vector;

public class Hand {
	
	Vector<Card>cards=new Vector<Card>();
	private int combination;
	private int highCard;
	
	public Hand(String [] input) {
		
		for(int i=0;i<input.length;i++)
			cards.add(new Card(input[i]));
		
		sort();
		
	}
	
	private void sort() {
	for(int i=0;i<cards.size();i++) 
	{
		int indexOfmin=i,min=getValue(i);
		for(int j=i+1;j<cards.size();j++)
		{
			if(getValue(j)<min) {
				indexOfmin=j;
			    min=getValue(j);
			    }
		}
		
		//swapping cards 
		Card temp = getCard(i);
		cards.set(i, getCard(indexOfmin));
		cards.set(indexOfmin, temp);
	}
	}
	
	
	public void printHand() {
		for(int i=0;i<cards.size();i++)
			System.out.print(Integer.toString(getValue(i))+getSuit(i)+" ");
		
		System.out.println();
		
	}

	public int getValue(int i) {
		return cards.get(i).getValue();
		
	}
	
	public char getSuit(int i) {
		return cards.get(i).getSuit();
	}

	public int getCombination() {
		return combination;
		
	}
	
	public int getHighCard() {
		return highCard;
	}
	public Card getCard(int i) {
		return cards.get(i);
	}

	public void setCombination(int c) {
		combination=c;
		
	}

	public void setHighCard(int i) {
		highCard=i;
		
	}
	
	public String highCardText(int x) {
		
		if(x==14)
			return "Ace";
		else if (x==13)
			return "King";
		else if(x==12)
			return "Queen";
		else if(x==11)
			return "Jack";
		else if(x>=2 && x<=10)
			return " "+x;
		else 
			return null;
			
		
	}
	
	public String combinationText(int x) {
		String text;
		switch(x) {
		case 8:
  		      text="Straight Flush";
  		      break;
		case 7:
	  		  text="Four of a kind";
	  		  break;
		case 6:
	  		  text="Full House";
	  		  break;
		case 5:
	  		  text="Flush";
	  		  break;
		case 4:
	  		  text="Straight";
	  		  break;
		case 3:
	  		  text="Three of a kind";
	  		  break;
		case 2:
	  		  text="Two Pairs";
	  		  break;
		case 1:
	  		  text="Pair";
	  		  break;
	  	default:
	  		text= null;
	  		break;
	  			  
  		  
		}
		return text;
		
	}
	

}
