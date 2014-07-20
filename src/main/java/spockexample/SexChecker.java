package spockexample;

/**
 * Created by yusuke on 2014/07/20.
 */
public class SexChecker {
    /**
     * 男性かどうかを判定する
     * @param person
     * @return
     */
    public boolean isMale(Person person) {
        System.out.println("this person's sex is " + person.getSex());

        return "m".equals(person.getSex());
    }
}
