# java-blackjack

블랙잭 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 구현할 기능 목록 (Application)
- [x] 게임에 참여할 사람의 이름 입력 받는다.
  - [x] `,` 기준으로 분리한다.
  - [x] 문자 시작과 끝의 공백은 제거한다.
  - [x] <예외> 인원수는 최대 8명이다.
  - [x] <예외> 공백이면 안된다.
- [x] 플레이어와 딜러에게 각각 2장씩 준다.
  - [x] 딜러 카드는 1장만 보여준다.
  - [x] 플레이어 카드는 2장 모두 공개한다.
  - [x] 카드는 중복이 되면 안된다.
- [x] 각 플레이어가 카드를 뽑을지 결정한다.
  - [x] "y", "n"으로 입력을 받는다. (대문자 가능)
  - [x] <예외> 이외의 문자나 공백이 들어오면 안된다.
  - [x] 합이 21이 넘으면 더 이상 뽑을 수 없다.
- [x] 딜러는 카드의 총합이 17이 넘을 때까지 카드를 뽑는다.
- [x] 플레이어들의 카드와 총합을 공개한다.
  - [x] ACE가 포함될 경우 21을 넘지 않은 가까운 수가 되도록 1 또는 11로 선택된다.
- [ ] 승패를 결정한다.
  - [ ] 참가한 플레이어와 딜러의 카드 결과를 비교하여 승패를 결정한다.

## 도메인
> Card

- 문양과 번호를 가진다.
- 블랙잭에 필요한 카드들을 생성하여 전달한다.
- 카드의 번호를 반환한다.

> Cards

- Card의 일급 컬렉션
- Card 리스트의 총합을 알려준다.
- Card를 추가한다.

> Deck

- 52장의 섞인 카드를 가진다.
- 한 장씩 꺼낼 수 있다.

> Name

- 빈 값, 공백을 가지지 않는다.

> Player

- Cards를 가진다.
- 이름을 가진다.
- 카드를 뽑는다.
- 버스트인지 알려준다.

> Dealer

- Cards를 가진다.
- 승부결과를 가진다.
- 카드를 뽑는다.
- 카드의 총합이 버스트인지 확인한다.
- 카드의 총합이 17이 넘는지 확인한다.

> Players

- 딜러와 각 플레이어와 승부를 가리고 플레이어들의 승부결과를 반환한다.
  - 딜러에게 각 플레이어의 결과를 전달한다.

> Result

- 각 플레이어의 승부 결과를 반환한다.
