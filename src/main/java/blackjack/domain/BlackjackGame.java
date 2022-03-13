package blackjack.domain;

public class BlackjackGame {

    private static final int INITIAL_CARD_NUMBER = 2;

    private final Players players;
    private final Dealer dealer;
    private final Drawable deck;

    public BlackjackGame(Players players) {
        this.players = players;
        this.dealer = new Dealer();
        this.deck = new Deck();
    }

    public void drawStartingCard() {
        for (int i = 0; i < INITIAL_CARD_NUMBER; i++) {
            players.drawAll(deck);
            dealer.drawCard(deck);
        }
    }

    public boolean checkDealerDrawable() {
        return dealer.isDrawable();
    }

    public void drawDealerCard() {
        dealer.drawCard(deck);
    }

    public ScoreResult makeResults() {
        return players.compete(dealer);
    }

    public void drawPlayerCard() {
        players.drawPlayerCard(deck);
    }

    public void proceedTurn() {
        players.proceedTurn();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public String getNowTurnPlayerName() {
        return getNowTurnPlayer().getName();
    }

    public Player getNowTurnPlayer() {
        return players.getCurrentTurnPlayer();
    }

    public boolean isBustCurrentTurn() {
        return players.isBustCurrentTurnPlayer();
    }

    public boolean isEndAllPlayersTurn() {
        return players.hasNoNext();
    }
}
