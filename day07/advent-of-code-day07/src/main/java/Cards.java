import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cards implements Comparator<Cards> {
    private char[] cardsInHand = new char[5];
    private int bid;
    private final Map<Character, Integer> cardValues =
            Stream.of(new Object[][] {
                    {'A', 1},
                    {'K', 2},
                    {'Q', 3},
                    {'J', 4},
                    {'T', 5},
                    {'9', 6},
                    {'8', 7},
                    {'7', 8},
                    {'6', 9},
                    {'5', 10},
                    {'4', 11},
                    {'3', 12},
                    {'2', 13}
            }).collect(Collectors.toMap(data -> (Character) data[0], data -> (Integer) data[1]));

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
            this.rank = 1;
        }


    }
    @Override
    public int compare(Cards o1, Cards o2) {
        int rankOne =o1.getRank();
        int rankTwo =o1.getRank();
        if ( rankOne == rankTwo) {
            for (int i = 0; i < Math.min(o1.getCardsInHand().length, o2.getCardsInHand().length); i++) {
                Character cOne =o1.getCardsInHand()[i];
                Character cTwo = o2.getCardsInHand()[i];
                if ( cOne != cTwo) {
                    return cardValues.get(cTwo) - cardValues.get(cOne);
                }
            }
            return 0;
        } else {
            return rankTwo-rankOne;
        }
    }
}
