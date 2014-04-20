package spockexample;

/**
 *
 * @author yusuke
 */
public class FemaleAdultCounter {
	private AgeChecker ageChecker;
	public void setAgeChecker(AgeChecker ageChecker) {
		this.ageChecker = ageChecker;
	}

	private int point = 0;

	/**
	 * 女性でかつ大人であれば3ポイント、女性で子どもであれば1ポイントを加算する
	 * @param person
	 */
	public void count(Person person) {
		if (person.getSex().equals("m")) {
			return;
		}

		if (ageChecker.isAdult(person)) {
			addPoint(3);
		}

		addPoint(1);
	}

	private void addPoint(int c) {
		this.point += c;
	}

	public int getPoint() {
		return this.point;
	}
}

