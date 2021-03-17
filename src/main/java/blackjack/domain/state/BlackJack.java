package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.rule.ScoreRule;

import java.util.List;

public class BlackJack implements State {
    private Cards cards;

    public BlackJack(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    @Override
    public boolean isEndState() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackJack() {
        return true;
    }

    @Override
    public double calculateEarningRate(State enemyState) {
        if (enemyState.isBlackJack()) {
            return 0;
        }

        return 1.5;
    }

    @Override
    public int sumTotalScore(ScoreRule scoreRule) {
        return cards.sumTotalScore(scoreRule);
    }

    @Override
    public State changeState() {
        return this;
    }

    @Override
    public State stay() {
        return this;
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}