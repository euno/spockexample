package spockexample;

public class Person {
	/** 性別("m" or "f") */
	private String sex;
	/** 年齢 */
	private int age;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
