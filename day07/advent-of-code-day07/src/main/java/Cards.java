import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cards implements Comparable<Cards> {
    private char[] cardsInHand = new char[5];
    private int bid;
    private static final Map<Character, Integer> cardValues = new TreeMap<>();

    static {
        cardValues.put('A', 1);
        cardValues.put('K', 2);
        cardValues.put('Q', 3);
        cardValues.put('J', 4);
        cardValues.put('T', 5);
        cardValues.put('9', 6);
        cardValues.put('8', 7);
        cardValues.put('7', 8);
        cardValues.put('6', 9);
        cardValues.put('5', 10);
        cardValues.put('4', 11);
        cardValues.put('3', 12);
        cardValues.put('2', 13);
    }

    private int rank;

    public Cards(char[] cardsInHand, int bid) {
        this.cardsInHand = cardsInHand;
        this.bid = bid;
        calcRank();
    }

    public char[] getCardsInHand() {
        return cardsInHand;
    }

    public int getBid() {
        return bid;
    }

    public int getRank() {
        return rank;
    }

    private void calcRank() {
        Map<Character, Integer> cardsMap = new TreeMap<>();
        for (char c: getCardsInHand()) {
            int value =1;
            if (cardsMap.containsKey(c)) {
                 value = cardsMap.get(c) + 1;
            }
            cardsMap.put(c,value);
        }
        if (cardsMap.keySet().size() == 5) {
            this.rank = 0;
        }

        long numberOfPairs = cardsMap.values().stream()
                .filter(i -> i == 2)
                .count();

        if (numberOfPairs == 1) {
            this.rank = 1;
        }

        if (numberOfPairs == 2) {
            this.rank = 2;
        }

        if (cardsMap.containsValue(3) && !cardsMap.containsValue(2)) {
            this.rank = 3;
        }

        if (cardsMap.containsValue(3) && cardsMap.containsValue(2)) {
            this.rank = 4;
        }

        if (cardsMap.containsValue(4)) {
            this.rank = 5;
        }

        if (cardsMap.keySet().size() == 1) {
            this.rank = 6;
        }

    }

    @Override
    public int compareTo(Cards o) {
        int rankOne =getRank();
        int rankTwo =o.getRank();
        if ( rankOne == rankTwo) {
            for (int i = 0; i < Math.min(getCardsInHand().length, o.getCardsInHand().length); i++) {
                Character cOne =getCardsInHand()[i];
                Character cTwo = o.getCardsInHand()[i];
                int cOneValue = cardValues.get(cOne);
                int cTwoValue = cardValues.get(cTwo);
                if (cOneValue != cTwoValue) {
                    return cTwoValue - cOneValue;
                }
            }
            return 0;
        } else {
            return rankOne-rankTwo;
        }

    }
}
