package spockexample

import spock.lang.Specification
import spock.lang.Unroll;

class AgeCheckerSpec extends Specification {

	@Unroll
	def "#ageで#sexの場合に大人かどうかの判定で#resultが返る"() {
		setup:
		def sut = new AgeChecker()

		expect:
		sut.isAdult(new Person(sex, age)) == result

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
    def "#ageで#sexの場合に大人かどうかの判定で#resultが返る2"() {
        setup:
        def sut = new AgeChecker()

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
	def "Aさん#ageA,#sexAとBさん#ageB,#sexBの歳の差#resultが返る"() {
		setup:
		def sut = new AgeChecker()

		expect:
		sut.getAgeDiff(new Person(sexA, ageA), new Person(sexB, ageB)) == result

		where:
		ageA | sexA | ageB | sexB || result
		1    | "m"  | 20   | "m"  || 19
		20   | "m"  | 20   | "m"  || 0
		21   | "m"  | 20   | "m"  || 1
	}
}
