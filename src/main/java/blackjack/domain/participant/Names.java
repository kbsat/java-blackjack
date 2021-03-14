package blackjack.domain.participant;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Names {

    private final List<Name> names;

    public Names(final List<String> names) {
        this.names = convertNames(names);
        validateDuplicate(this.names);
    }

    private List<Name> convertNames(final List<String> names) {
        return names.stream()
            .map(Name::new)
            .collect(Collectors.toList());
    }

    private void validateDuplicate(final List<Name> names) {
        Set<String> nameSet = names.stream()
            .map(Name::getValue)
            .collect(Collectors.toSet());

        if(nameSet.size() != names.size()) {
            throw new IllegalArgumentException("중복되는 이름은 사용할 수 없습니다.");
        }
    }

    public List<Name> toList() {
        return this.names;
    }
}
