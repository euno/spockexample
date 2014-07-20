package spockexample;

/**
 *
 * @author yusuke
 */
public class AttractionRoom {
	private PersonChecker personChecker;
	public void setPersonChecker(PersonChecker personChecker) {
		this.personChecker = personChecker;
	}

    private CapacityCounter capacityCounter;
    public void setCapacityCounter(CapacityCounter capacityCounter) {
        this.capacityCounter = capacityCounter;
    }

    private int capacityLimit = 20;
    public void setCapacityLimit(int capacityLimit) {
        this.capacityLimit = capacityLimit;
    }

    /**
	 * 女性でかつ大人であれば3ポイント、女性で子どもであれば1ポイントを加算する
	 * @param person
	 */
	public void add(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("nullは許可されていません");
        }

        int add = 0;
        if (!personChecker.isAdult(person)) {
            // 子どもは男女とも1
            add = 1;
        } else if (personChecker.isMale(person)) {
            // 大人で男性の場合は3
            add = 3;
		} else {
            // 大人で女性の場合は2
            add = 2;
        }

        if (capacityCounter.getCount() > (capacityLimit - add)) {
            throw new IllegalArgumentException("limit over");
        }

		capacityCounter.addCount(add);
	}

	public int getCount() {
		return this.capacityCounter.getCount();
    }
}
