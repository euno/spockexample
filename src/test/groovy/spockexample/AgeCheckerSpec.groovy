package spockexample

import spock.lang.Specification
import spock.lang.Unroll;

class AgeCheckerSpec extends Specification {

	@Unroll
	def "#ageで#sexの場合に大人かどうかの判定で#resultが返る"() {
		setup:
		def sut = new AgeChecker()
		def person = new Person()
		person.setAge(age)
		person.setSex(sex)

		expect:
		sut.isAdult(person) == result

		where:
		age | sex || result
		0  | "m" || false
		19 | "m" || false
		20 | "m" || true
		0  | "f" || false
		19 | "f" || false
		20 | "f" || true
	}

	@Unroll
	def "#ageA,#sexAと#ageB,#sexBの場合に歳の差#resultが返る"() {
		setup:
		def sut = new AgeChecker()
		def personA = new Person()
		personA.setAge(ageA)
		personA.setSex(sexA)
		def personB = new Person()
		personB.setAge(ageB)
		personB.setSex(sexB)

		expect:
		sut.getAgeDiff(personA, personB) == result

		where:
		ageA | sexA | ageB | sexB || result
		1    | "m"  | 20   | "m"  || 19
		20   | "m"  | 20   | "m"  || 0
		21   | "m"  | 20   | "m"  || 1
	}
}
