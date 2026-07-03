import java.security.SecureRandom;
import java.util.Arrays;

public class DeckOfCards {
    private Card[] deck;
    private int currentCard;
    private static final int NUMBER_OF_CARDS = 52;
    private static final SecureRandom randomNumbers = new SecureRandom();

    private static final String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", 
        "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private static final String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

    public DeckOfCards() {
        deck = new Card[NUMBER_OF_CARDS];
        currentCard = 0;

        for (int count = 0; count < deck.length; count++) {
            deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }
    }

    public void shuffle() {
        currentCard = 0;
        for (int first = deck.length - 1; first > 0; first--) {
            int second = randomNumbers.nextInt(first + 1);
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    public Card dealCard() {
        if (currentCard < deck.length) {
            return deck[currentCard++];
        } else {
            return null;
        }
    }


    private static int getFaceIndex(Card card) {
        for (int i = 0; i < faces.length; i++) {
            if (faces[i].equals(card.getFace())) return i;
        }
        return -1;
    }

    public static boolean hasPair(Card[] hand) {
        int[] counts = getFaceCounts(hand);
        for (int count : counts) {
            if (count == 2) return true;
        }
        return false;
    }

    public static boolean hasTwoPairs(Card[] hand) {
        int[] counts = getFaceCounts(hand);
        int pairs = 0;
        for (int count : counts) {
            if (count == 2) pairs++;
        }
        return pairs == 2;
    }

    public static boolean hasThreeOfAKind(Card[] hand) {
        int[] counts = getFaceCounts(hand);
        for (int count : counts) {
            if (count == 3) return true;
        }
        return false;
    }

    public static boolean hasFourOfAKind(Card[] hand) {
        int[] counts = getFaceCounts(hand);
        for (int count : counts) {
            if (count == 4) return true;
        }
        return false;
    }

    public static boolean hasFlush(Card[] hand) {
        String suit = hand[0].getSuit();
        for (int i = 1; i < hand.length; i++) {
            if (!hand[i].getSuit().equals(suit)) return false;
        }
        return true;
    }

    public static boolean hasStraight(Card[] hand) {
        int[] counts = getFaceCounts(hand);
        int i = 0;
        while (i < counts.length && counts[i] == 0) i++;
        
        for (int j = 0; j < 5; j++) {
            if (i + j >= counts.length || counts[i + j] != 1) return false;
        }
        return true;
    }

    public static boolean hasFullHouse(Card[] hand) {
        return hasThreeOfAKind(hand) && hasPair(hand);
    }

    private static int[] getFaceCounts(Card[] hand) {
        int[] counts = new int[13];
        for (Card card : hand) {
            if (card != null) {
                counts[getFaceIndex(card)]++;
            }
        }
        return counts;
    }

    public static int evaluateHand(Card[] hand) {
        if (hasFlush(hand) && hasStraight(hand)) return 8; 
        if (hasFourOfAKind(hand)) return 7;
        if (hasFullHouse(hand)) return 6;
        if (hasFlush(hand)) return 5;
        if (hasStraight(hand)) return 4;
        if (hasThreeOfAKind(hand)) return 3;
        if (hasTwoPairs(hand)) return 2;
        if (hasPair(hand)) return 1;
        return 0; 
    }
    
    public static String getHandName(int score) {
        String[] names = {"High Card", "a Pair", "Two Pairs", "Three of a Kind", 
                          "a Straight", "a Flush", "a Full House", "Four of a Kind", "a Straight Flush"};
        return names[score];
    }
}