package spockexample;

import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
         * パラメータ化テストのパラメータとなるFixture定義
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

        /**
         * テストに使用するパラメータを定義
         */
        @DataPoints
        public static Fixture[] fixtures = {
                new Fixture(0, "m", false),
                new Fixture(19, "m", false),
                new Fixture(20, "m", true),
                new Fixture(0, "f", false),
                new Fixture(19, "f", true),
                new Fixture(20, "f", false),
        };

        @Theory
        public void testIsAdult(Fixture fixture) {
            // Fixtureの値を使ってPersonオブジェクトを初期化
            Person person = new Person(fixture.sex, fixture.age);

            // テストメソッド実行＆結果判定
            assertThat(sut.isAdult(person), is(fixture.result));
        }
    }

    @RunWith(Theories.class)
    public static class isMaleTest {
        static PersonChecker sut;

        @Before
        public void setup() {
            sut = new PersonChecker();
        }

        /**
         * パラメータ化テストのパラメータとなるFixture定義
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

        /**
         * テストに使用するパラメータを定義
         */
        @DataPoints
        public static Fixture[] fixtures = {
                new Fixture(19, "m", true),
                new Fixture(20, "m", true),
                new Fixture(19, "f", false),
                new Fixture(20, "f", false),
        };

        @Theory
        public void testIsMale(Fixture fixture) {
            // Fixtureの値を使ってPersonオブジェクトを初期化
            Person person = new Person(fixture.sex, fixture.age);

            // テストメソッド実行＆結果判定
            assertThat(sut.isMale(person), is(fixture.result));
        }
    }
}
