package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.MockDeck;

public class PlayerTest {

    @Nested
    @DisplayName("drawCard는")
    class DrawCard {

        @Test
        @DisplayName("Card를 자신의 패에 추가한다.")
        void addCard() {
            Participant player = new Player("roma", 0);
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.JACK));
            Assertions.assertThat(player.getTotalNumber()).isEqualTo(10);
        }
    }

    @Nested
    @DisplayName("isBust는")
    class IsBust {

        @ParameterizedTest
        @CsvSource(value = {"ACE|false", "TWO|true"}, delimiter = '|')
        @DisplayName("패의 합이 21이 넘는지 유무를 알려준다.")
        void returnFalse(CardNumber cardNumber, boolean expected) {
            Participant player = new Player("roma", 0);
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.JACK));
            player.drawCard(Card.of(CardPattern.CLOVER, CardNumber.KING));
            player.drawCard(Card.of(CardPattern.CLOVER, cardNumber));

            Assertions.assertThat(player.isBust()).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("Compete는")
    class Compete {

        @ParameterizedTest
        @CsvSource(value = {"FOUR|LOSE", "FIVE|DRAW", "SIX|WIN"}, delimiter = '|')
        @DisplayName("딜러와 점수를 비교하여 승부 결과를 반환한다.")
        void returnResult(CardNumber cardNumber, Score expected) {
            Player player = new Player("player", 0);
            Dealer dealer = new Dealer();
            player.drawCard(Card.of(CardPattern.DIAMOND, cardNumber));
            dealer.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.FIVE));
            Assertions.assertThat(player.compete(dealer)).isEqualTo(expected);
        }

        @Test
        @DisplayName("본인의 카드가 버스트일 때 항상 패배한다.")
        void returnResultWithPlayerBust() {
            Player player = new Player("player", 0);
            Dealer dealer = new Dealer();
            MockDeck mockDeck = new MockDeck(List.of(
                    Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                    Card.of(CardPattern.CLOVER, CardNumber.QUEEN),
                    Card.of(CardPattern.CLOVER, CardNumber.TEN)
            ));
            for (int i = 0; i < 3; i++) {
                player.drawCard(mockDeck.draw());
            }
            dealer.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.FIVE));

            Assertions.assertThat(player.compete(dealer)).isEqualTo(Score.LOSE);
        }

        @ParameterizedTest
        @CsvSource(value = {"TEN|LOSE", "ACE|WIN"}, delimiter = '|')
        @DisplayName("딜러가 버스트일 때 승부 결과를 반환한다.")
        void returnResultWithDealerBust(CardNumber cardNumber, Score expected) {
            Player player = new Player("player", 0);
            Dealer dealer = new Dealer();
            player.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
            player.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
            player.drawCard(Card.of(CardPattern.DIAMOND, cardNumber));

            dealer.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
            dealer.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
            dealer.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.TWO));

            Assertions.assertThat(player.compete(dealer)).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("getTotalProfit은")
    class GetTotalProfit {

        @ParameterizedTest
        @CsvSource(value = {"WIN,10000", "DRAW,0", "LOSE,-10000"})
        @DisplayName("최종 수익을 반환한다.")
        void returnTotalProfit(Score score, double expected) {
            Player player = new Player("player", 10000);
            Assertions.assertThat(player.getTotalProfit(score)).isEqualTo(expected);
        }

        @Test
        @DisplayName("블랙잭으로 승리할 경우 1.5배의 수익을 낸다.")
        void returnTotalProfitWithBlackjack() {
            Player player = new Player("player", 10000);
            player.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.ACE));
            player.drawCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
            Assertions.assertThat(player.getTotalProfit(Score.WIN)).isEqualTo(15000);
        }
    }
}
