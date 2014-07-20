package spockexample;

import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by yusuke on 2014/07/20.
 */
@RunWith(Enclosed.class)
public class PersonCheckerTest {

    @RunWith(Theories.class)
    public static class isAdultTest {
        static PersonChecker sut;

        @Before
        public void setup() {
            sut = new PersonChecker();
        }

        /**
         * パラメータ化テストのパラメータクラス定義
         */
        static class Fixture {
            int age;
            String sex;
            boolean result;

            Fixture(int age, String sex, boolean expected) {
                this.age = age;
                this.sex = sex;
                this.result = expected;
            }
        }

        @DataPoints
        public static Fixture[] fixtures = {
                new Fixture(0, "m", false),
                new Fixture(19, "m", false),
                new Fixture(20, "m", true),
                new Fixture(0, "f", false),
                new Fixture(19, "f", false),
                new Fixture(20, "f", true),
        };

        @Theory
        public void testIsAdult(Fixture fixture) {
            Person person = new Person(fixture.sex, fixture.age);

            assertThat(sut.isAdult(person), is(fixture.result));
        }
    }
}
