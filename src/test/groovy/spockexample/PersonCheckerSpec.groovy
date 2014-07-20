package spockexample

import spock.lang.Specification
import spock.lang.Unroll;

class PersonCheckerSpec extends Specification {

	@Unroll
    def "#age歳で性別が#sexの場合に大人かどうかの判定で#resultが返る"() {
        setup:
        def sut = new PersonChecker()

        expect:
        sut.isAdult(new Person(sex, age)) == result

        where:
        age | sex || result
        0   | "m" || false
        19  | "m" || false
        20  | "m" || true
        0   | "f" || false
        19  | "f" || true
        20  | "f" || false
    }

    @Unroll
    def "#age歳で性別が#sexの場合に大人かどうかの判定で#resultが返る2"() {
        setup:
        def sut = new PersonChecker()

        when:
        def person = new Person(sex, age);

        then:
        sut.isAdult(person) == result

        where:
        age | sex || result
        0   | "m" || false
        19  | "m" || false
        20  | "m" || true
        0   | "f" || false
        19  | "f" || false
        20  | "f" || true
        30  | "m" || true
    }

    @Unroll
    def "#age歳で性別が#sexの場合に、男性化どうかの判定で#resultが返る()"() {
        setup:
        def sut = new PersonChecker()

        expect:
        sut.isMale(new Person(sex, age)) == result

        where:
        age | sex || result
        19  | "m" || true
        20  | "m" || true
        19  | "f" || false
        20  | "f" || false
    }
}
