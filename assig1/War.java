// Michael Adams
// TuTh 2pm

public class War
{
	private Card item0, item1, tempItem0, tempItem1;
	
	public static void main (String[] args)
	{
		int numRounds;
		//read in and handle command line arg for number of rounds
		try {
			numRounds = Integer.parseInt(args[0]);
			new War(numRounds);
		}
		catch (NumberFormatException e) {
			System.out.println("num was not an int - good thing this doesnt really need to be handled");
		}
		
		//new War(numRounds);
	}

	public War(int n)
	{
		int numRounds = n;
			
		//small talk
		System.out.println("Welcome to the Game of War!");
		System.out.println();
		System.out.println("Now dealing the cards to the players...");
		System.out.println();
		
		//create deck of Card
		Card tempCard;
		MultiDS<Card> deck = new MultiDS<Card>(52);
		for (Card.Suits s : Card.Suits.values()) {
			for (Card.Ranks r : Card.Ranks.values()) {
				tempCard = new Card(s, r);
				deck.addItem(tempCard);
			}
		}
		
		//shuffle deck of Card
		deck.shuffle();
		
		//give players the Hands
		MultiDS<Card> p0Hand = new MultiDS<Card>(52);
		MultiDS<Card> p1Hand = new MultiDS<Card>(52);
		
		int j = 0;
		for (int i = 0; i < 26; i ++) {
			p0Hand.addItem(deck.removeItem()); //[j] = deck[i];
			p1Hand.addItem(deck.removeItem());
			//p1Hand[j] = deck[i + 1];
			//j++;
		}
		
		//print players hands
		System.out.println("Here is Player 0's Hand:");
		System.out.println("Contents:");
		System.out.println(p0Hand.toString());
		System.out.println("Here is Player 1's Hand:");
		System.out.println("Contents:");
		System.out.println(p1Hand.toString());
		
		//war time
		System.out.println();
		System.out.println("Starting the WAR!");
		System.out.println();
		
		MultiDS<Card> p0Pile = new MultiDS<Card>(52);
		MultiDS<Card> p1Pile = new MultiDS<Card>(52);
		
		boolean isWinner = false;
		int counter = 0;
		//while war isnt over
		while(counter < numRounds && !isWinner)
		{
			//see who won the hand if game isnt over
			if (p0Hand.size() > 0 && p1Hand.size() > 0) {
				item0 = p0Hand.removeItem();
				item1 = p1Hand.removeItem();
				
				if (item0.compareTo(item1) > 0) {
					p0Pile.addItem(item0);
					p0Pile.addItem(item1);
					
					System.out.println("Player 0 wins Rnd " + counter + ": " + item0.toString() + " beats " + item1.toString() + " : 2 cards");
					
					counter++;
				}
				else if (item0.compareTo(item1) < 0) {
					p1Pile.addItem(item0);
					p1Pile.addItem(item1);
					
					System.out.println("Player 1 wins Rnd " + counter + ": " + item0.toString() + " loses to " + item1.toString() + " : 2 cards");
					
					counter++;
				}
				
				MultiDS<Card> tempArr = new MultiDS<Card>(52);
				int warCounter = 0;
				while (item0.compareTo(item1) == 0 && (p0Hand.size() + p0Pile.size()) > 2 && (p1Hand.size() + p1Pile.size() > 2)) {
					System.out.println("	WAR: " + item0.toString() + " ties " + item1.toString());
					warCounter++;
					tempArr.addItem(item0);
					tempArr.addItem(item1);
					
					//make sure hands arent empty
					if (p0Hand.size() < 1 && p0Pile.size() > 0) {
				System.out.println("	Getting and shuffling the pile for player 0");
				for (int i = 0; i < p0Pile.size(); i++) {
					p0Hand.addItem(p0Pile.removeItem());
				}
				p0Hand.shuffle();
			}
			if (p1Hand.size() < 1 && p1Pile.size() > 0) {
				System.out.println("	Getting and shuffling the pile for player 1");
				for (int i = 0; i < p1Pile.size(); i++) {
					p1Hand.addItem(p1Pile.removeItem());
				}
				p1Hand.shuffle();
			}
					
					tempItem0 = p0Hand.removeItem();
					tempItem1 = p1Hand.removeItem();
					
					//System.out.println("item0 is: " + item0.toString());
					//System.out.println("item1 is: " + item1.toString());
					//System.out.println("tempItem0 is: " + tempItem0.toString());
					//System.out.println("tempItem1 is: " + tempItem1.toString());
					System.out.println("	Player 0: " + tempItem0.toString() + " and Player 1: " + tempItem1.toString() + " are at Risk!");
			
					tempArr.addItem(tempItem0);
					tempArr.addItem(tempItem1);
					
					//shuffle if need be
					if (p0Hand.size() < 1 && p0Pile.size() > 0) {
				System.out.println("	Getting and shuffling the pile for player 0");
				for (int i = 0; i < p0Pile.size(); i++) {
					p0Hand.addItem(p0Pile.removeItem());
				}
				p0Hand.shuffle();
			}
			if (p1Hand.size() < 1 && p1Pile.size() > 0) {
				System.out.println("	Getting and shuffling the pile for player 1");
				for (int i = 0; i < p1Pile.size(); i++) {
					p1Hand.addItem(p1Pile.removeItem());
				}
				p1Hand.shuffle();
			}
					
					item0 = p0Hand.removeItem();
					item1 = p1Hand.removeItem();
						//System.out.println("item0 is: " + item0.toString());
					//System.out.println("item1 is: " + item1.toString());
					tempArr.addItem(item0);
					tempArr.addItem(item1);
			
					if (item0.compareTo(item1) > 0) {
						for (int i = 0; i < tempArr.size(); i++) {
						p0Pile.addItem(tempArr.removeItem());
						}
						System.out.println("Player 0 wins Rnd " + counter + ": " + item0.toString() + " beats " + item1.toString() + " : " + (2 + warCounter * 4) + " cards");
						counter++;
					}
					else if (item0.compareTo(item1) < 0) {
						for (int i = 0; i < tempArr.size(); i++) {
						p1Pile.addItem(tempArr.removeItem());
						}
						System.out.println("Player 1 wins Rnd " + counter + ": " + item0.toString() + " loses to " + item1.toString() + " : " + (2 + warCounter * 4) + " cards");
						counter++;
					}
				}
				
				if (item0.compareTo(item1) == 0 && ((p0Hand.size() + p0Pile.size()) < 2 || (p1Hand.size() + p1Pile.size() < 2))) {
					if (p0Hand.size() < 1 && p0Pile.size() < 1) {
					System.out.println("Player 0 is out of cards!");
					System.out.println("Player 1 wins!");
					isWinner = true;
					}
					else if (p1Hand.size() < 1 && p1Pile.size() < 1) {
						System.out.println("Player 1 is out of cards!");
						System.out.println("Player 0 wins!");
						isWinner = true;
					}
				}
			}//if not empty
			else if (p0Hand.size() < 1 && p0Pile.size() > 0) {
				System.out.println("	Getting and shuffling the pile for player 0");
				for (int i = 0; i < p0Pile.size(); i++) {
					p0Hand.addItem(p0Pile.removeItem());
				}
				p0Hand.shuffle();
			}
			else if (p1Hand.size() < 1 && p1Pile.size() > 0) {
				System.out.println("	Getting and shuffling the pile for player 1");
				for (int i = 0; i < p1Pile.size(); i++) {
					p1Hand.addItem(p1Pile.removeItem());
				}
				p1Hand.shuffle();
			}
			else if (p0Hand.size() < 1 && p0Pile.size() < 1) {
				System.out.println("Player 0 is out of cards!");
				System.out.println("Player 1 wins!");
				isWinner = true;
			}
			else if (p1Hand.size() < 1 && p1Pile.size() < 1) {
				System.out.println("Player 1 is out of cards!");
				System.out.println("Player 0 wins!");
				isWinner = true;
			}
		}//while (counter
		//declare winner
		if (counter >= numRounds) {
			System.out.println();
			System.out.println("After " + numRounds + " here is the status:");
			System.out.println();
			System.out.println("Player 0 has " + (p0Hand.size() + p0Pile.size()) + " cards");
			System.out.println("Player 1 has " + (p1Hand.size() + p1Pile.size()) + " cards");
			System.out.println();
			
			if ((p0Hand.size() + p0Pile.size() > (p1Hand.size() + p1Pile.size()))) {
				System.out.println("Player 0 wins!");
			}
			else if ((p0Hand.size() + p0Pile.size() < (p1Hand.size() + p1Pile.size()))) {
				System.out.println("Player 1 wins!");
			}
			else if ((p0Hand.size() + p0Pile.size() == (p1Hand.size() + p1Pile.size()))) {
				System.out.println("stalemate lol");
			}
		}
	}
}
