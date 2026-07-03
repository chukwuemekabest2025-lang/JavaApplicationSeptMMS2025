public class PokerGame {
    public static void main(String[] args) {
        DeckOfCards myDeck = new DeckOfCards();
        myDeck.shuffle();

        Card[] hand1 = new Card[5];
        Card[] hand2 = new Card[5];

        
        for (int i = 0; i < 5; i++) {
            hand1[i] = myDeck.dealCard();
            hand2[i] = myDeck.dealCard();
        }

        System.out.println("Hand 1:");
        for (Card card : hand1) System.out.println("  " + card);
        int score1 = DeckOfCards.evaluateHand(hand1);
        System.out.println("Result: " + DeckOfCards.getHandName(score1) + "\n");

        System.out.println("Hand 2:");
        for (Card card : hand2) System.out.println("  " + card);
        int score2 = DeckOfCards.evaluateHand(hand2);
        System.out.println("Result: " + DeckOfCards.getHandName(score2) + "\n");

        if (score1 > score2) {
            System.out.println("Hand 1 wins!");
        } else if (score2 > score1) {
            System.out.println("Hand 2 wins!");
        } else {
            System.out.println("It's a tie (or down to high-card kicker logic)!");
        }
    }
}