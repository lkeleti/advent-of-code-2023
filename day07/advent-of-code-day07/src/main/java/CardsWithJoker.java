import java.util.*;

public class CardsWithJoker implements Comparable<CardsWithJoker> {
    private char[] cardsInHand = new char[5];
    private int bid;
    private static final Map<Character, Integer> cardValuesWithJoker = new TreeMap<>();
    static {
        cardValuesWithJoker.put('A', 1);
        cardValuesWithJoker.put('K', 2);
        cardValuesWithJoker.put('Q', 3);
        cardValuesWithJoker.put('T', 4);
        cardValuesWithJoker.put('9', 5);
        cardValuesWithJoker.put('8', 6);
        cardValuesWithJoker.put('7', 7);
        cardValuesWithJoker.put('6', 8);
        cardValuesWithJoker.put('5', 9);
        cardValuesWithJoker.put('4', 10);
        cardValuesWithJoker.put('3', 11);
        cardValuesWithJoker.put('2', 12);
        cardValuesWithJoker.put('J', 13);
    }

    private int rank;

    public CardsWithJoker(char[] cardsInHand, int bid) {
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
        int numberOfJokers = 0;
        List<Character > cardsWithoutJoker = new ArrayList<>();
        for (char c: getCardsInHand()) {
            if (c != 'J') {
                cardsWithoutJoker.add(c);
            } else {
                numberOfJokers++;
            }
        }

        Map<Character, Integer> cardsMap = new TreeMap<>();
        for (char c: cardsWithoutJoker) {
            int value =1;
            if (cardsMap.containsKey(c)) {
                value = cardsMap.get(c) + 1;
            }
            cardsMap.put(c,value);
        }
        if (cardsMap.keySet().size() == 5) {
            this.rank = 0;
            //impossible with joker
        }

        if (cardsMap.keySet().size() == 4 && numberOfJokers == 1) {
            this.rank = 1;
        }

        if (cardsMap.keySet().size() == 3 && numberOfJokers == 2) {
            this.rank = 3;
        }

        if (cardsMap.keySet().size() == 2 && numberOfJokers == 3) {
            this.rank = 5;
        }

        if (cardsMap.keySet().size() == 1 && numberOfJokers == 4) {
            this.rank = 6;
        }

        long numberOfPairs = cardsMap.values().stream()
                .filter(i -> i == 2)
                .count();

        if (numberOfPairs == 1) {
            this.rank = 1;

            switch (numberOfJokers) {
                case 1:
                    this.rank += 2;
                    break;
                case 2:
                    this.rank += 4;
                    break;
                case 3:
                    this.rank += 5;
                    break;
            }
        }

        if (numberOfPairs == 2) {
            this.rank = 2;
            if (numberOfJokers > 0 ) {
                this.rank += 2;
            }
        }

        if (cardsMap.containsValue(3) && !cardsMap.containsValue(2)) {
            this.rank = 3;
            if (numberOfJokers > 0) {
                this.rank +=2;
            }
        }

        if (cardsMap.containsValue(3) && cardsMap.containsValue(2)) {
            this.rank = 4 + numberOfJokers;
        }

        if (cardsMap.containsValue(4)) {
            this.rank = 5 + numberOfJokers;
        }

        if (cardsMap.keySet().size() == 1) {
            this.rank = 6;
        }

        if (numberOfJokers == 5) {
            this.rank = 6;
        }
        System.out.println(this.rank);
    }

    @Override
    public int compareTo(CardsWithJoker o) {
        int rankOne =getRank();
        int rankTwo =o.getRank();
        if ( rankOne == rankTwo) {
            for (int i = 0; i < Math.min(getCardsInHand().length, o.getCardsInHand().length); i++) {
                Character cOne =getCardsInHand()[i];
                Character cTwo = o.getCardsInHand()[i];
                int cOneValue = cardValuesWithJoker.get(cOne);
                int cTwoValue = cardValuesWithJoker.get(cTwo);
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
