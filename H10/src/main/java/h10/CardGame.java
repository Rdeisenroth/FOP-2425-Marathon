package h10;

import org.jetbrains.annotations.Nullable;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Represents a simple card game with four players and a deck of cards.
 */
public class CardGame {

    /**
     * The players in the game.
     */
    @DoNotTouch
    MyList<CardGamePlayer> players;

    /**
     * An iterator over the players in the game.
     */
    @DoNotTouch
    private BidirectionalIterator<CardGamePlayer> iter;

    /**
     * The deck of cards that are used in the game.
     */
    @DoNotTouch
    private MyList<PlayingCard> cardDeck;

    // Game state variables

    /**
     * The direction of play in the game which determines the order in which the players take turns.
     */
    @DoNotTouch
    boolean reverseDirection = false;

    /**
     * The state of the next player in the game. If true, the next player is skipped.
     */
    @DoNotTouch
    boolean skipNextPlayer = false;

    /**
     * The number of cards the next player has to draw in the game.
     */
    @DoNotTouch
    int takeCards = 0;

    /**
     * The current player in the game whose turn it is.
     */
    @DoNotTouch
    @Nullable CardGamePlayer currentPlayer = null;

    /**
     * The card that is played in the current turn.
     */
    @DoNotTouch
    @Nullable PlayingCard currentCard = null;

    @DoNotTouch
    private CardGame() {
    }

    /**
     * Creates a new card game with the given players and card deck.
     *
     * @param players  the players in the game
     * @param cardDeck the deck of cards used in the game
     */
    @DoNotTouch
    public CardGame(MyList<CardGamePlayer> players, MyList<PlayingCard> cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;
        this.iter = this.players.cyclicIterator();
    }

    /**
     * Creates a new card game with four players and a deck of 100 cards.
     *
     * <p>The deck is shuffled and each player gets five random cards.
     *
     * @return a new card game with four players and a deck of 100 cards
     */
    @DoNotTouch
    public static CardGame generateRandomCardGame() {
        CardGame cardGame = new CardGame();

        cardGame.players = new DoublyLinkedList<>();
        cardGame.cardDeck = new DoublyLinkedList<>();

        // Create a card deck with 100 random cards
        PlayingCard[] cards = PlayingCard.values();
        for (int i = 0; i < 100; i++) {
            cardGame.cardDeck.add(cards[(int) (Math.random() * 4)]);
        }

        // 4 players with 5 cards each
        for (int i = 1; i < 5; i++) {
            CardGamePlayer player = new CardGamePlayer("Player " + i);
            cardGame.players.add(player);
            for (int j = 0; j < 5; j++) {
                player.takeCard(cardGame.cardDeck.removeAtIndex(0));
            }
        }

        cardGame.iter = cardGame.players.cyclicIterator();

        return cardGame;
    }

    /**
     * Determines the loser of the game.
     *
     * @return the last player who still has cards in their hand
     */
    @DoNotTouch
    public CardGamePlayer determineLoser() {
        while (players.size() > 1) {
            doTurn();
        }

        return players.get(0);
    }

    /**
     * Executes a turn in the game.
     */
    @StudentImplementationRequired("H10.3.3")
    private void doTurn() {
        crash(); // TODO: Implement H10.3.3
    }

    @Override
    @DoNotTouch
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            sb.append(players.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
