package spockexample;

public class PersonChecker {
	/**
	 * Personが大人(20歳以上)かどうかを返す
	 * @param person
	 * @return
	 */
	public boolean isAdult(Person person) {
		return person.getAge() >= 20;
	}

	/**
	 * 2つのPersonを受け取って、その2人の歳の差を正の整数で返す
	 * @param
	 * @param
	 * @return
	 */
	public int getAgeDiff(Person personA, Person personB) {
		if (personA.getAge() > personB.getAge()) {
			return personA.getAge() - personB.getAge();
		}
		return personB.getAge() - personA.getAge();
	}

    /**
     * 男性かどうかを判定する
     * @param person
     * @return
     */
    public boolean isMale(Person person) {
        return "m".equals(person.getSex());
    }
}
