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

    private SexChecker sexChecker;
    public void setSexChecker(SexChecker sexChecker) {
        this.sexChecker = sexChecker;
    }

    private Counter counter;
    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    /**
	 * 女性でかつ大人であれば3ポイント、女性で子どもであれば1ポイントを加算する
	 * @param person
	 */
	public void count(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("nullは許可されていません");
        }

		if (sexChecker.isMale(person)) {
			return;
		}

		if (ageChecker.isAdult(person)) {
            counter.addCount(3);
		}

		counter.addCount(1);
	}

	public int getPoint() {
		return this.counter.getCount();
	}
}
