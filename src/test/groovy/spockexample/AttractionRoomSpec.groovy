package spockexample

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by euno7 on 2014/07/20.
 */
class AttractionRoomSpec extends Specification {

    def "ポイント加算のメソッドが正しく呼ばれているか"() {
        setup:
        // テスト対象の初期化
        def sut = new AttractionRoom()
        def ageChecker = new PersonChecker()
        // カウンターをモック化
        CapacityCounter capacityCounter = Mock()
        // こっちの書き方でも可
        //def capacityCounter = Mock(CapacityCounter)

        // Groovyではプロパティアクセスの簡略記法でsetterにアクセスできる
        sut.personChecker = ageChecker
        sut.capacityCounter = capacityCounter

        when:
        // 20歳女性の場合
        def person1 = new Person("f", 20)
        sut.add(person1)
        then:
        // 2ポイント加算されるメソッドが1度呼ばれたはず
        1 * capacityCounter.addCount(2)

        when:
        // 19歳女性の場合
        def person2 = new Person("f", 19)
        sut.add(person2)
        then:
        // 1ポイント加算されるメソッドが1度呼ばれたはず
        1 * capacityCounter.addCount(1)

        when:
        // 20歳男性の場合
        def person3 = new Person("m", 20)
        sut.add(person3)
        then:
        // 3ポイント加算されるメソッドが1度呼ばれたはず
        1 * capacityCounter.addCount(3)

        when:
        // 19歳男性の場合
        def person4 = new Person("m", 19)
        sut.add(person4)
        then:
        // 1ポイント加算されるメソッドが1度呼ばれたはず
        1 * capacityCounter.addCount(1)
    }

    @Unroll
    def "モックとデータ駆動テストの組み合わせ"() {
        setup:
        // テスト対象の初期化
        def sut = new AttractionRoom()
        def ageChecker = new PersonChecker()
        // カウンターをモック化
        CapacityCounter capacityCounter = Mock()
        // こっちの書き方でも可
        //def capacityCounter = Mock(CapacityCounter)

        // Groovyではプロパティアクセスの簡略記法でsetterにアクセスできる
        sut.personChecker = ageChecker
        sut.capacityCounter = capacityCounter

        when:
        def person = new Person(sex, age)
        sut.add(person)

        then:
        // addをパラメータとするaddCountがcalled回呼ばれたはず
        called * capacityCounter.addCount(add)

        where:
        age | sex || add | called
        20  | "m" || 3   | 1
        20  | "f" || 2   | 1
        19  | "m" || 1   | 1
        19  | "f" || 1   | 1
    }

    def "例外のテスト"() {
        setup:
        // テスト対象の初期化
        def sut = new AttractionRoom()

        when:
        sut.add(null)

        then:
        // IllegalArgumentExceptionがスローされるはず
        def ex = thrown(IllegalArgumentException)
        // Exceptionのメッセージは「null is not acceptable.」のはず
        ex.getMessage() == "null is not acceptable."
    }

    def "実際の処理を行いつつスパイで多重化テスト"() {
        setup:
        def sut = new AttractionRoom()
        def ageChecker = new PersonChecker()
        // カウンターをスパイとして作成
        CapacityCounter capacityCounter = Spy(CapacityCounter)

        // Groovyではプロパティアクセスの簡略記法でsetterにアクセスできる
        sut.personChecker = ageChecker
        sut.capacityCounter = capacityCounter

        when:
        // 20歳女性の場合
        def person1 = new Person("f", 20)
        // 19歳女性の場合
        def person2 = new Person("f", 19)
        // 20歳男性の場合
        def person3 = new Person("m", 20)
        // 19歳男性の場合
        def person4 = new Person("m", 19)

        sut.add(person1)
        sut.add(person2)
        sut.add(person3)
        sut.add(person4)

        then:
        // スパイにより、メソッド呼び出しが監視できる
        1 * capacityCounter.addCount(3)
        1 * capacityCounter.addCount(2)
        2 * capacityCounter.addCount(1)
        // 0 * はthen:ブロックの最後に書くこと
        0 * capacityCounter.reduceCount(_)

        expect:
        // モックではなくスパイを使うことで、本物の振る舞いも確認できる
        capacityCounter.getCount() == 7
    }

    def "スタブによってモック化したオブジェクトの振る舞いを定義する"() {
        setup:
        def sut = new AttractionRoom()
        def ageChecker = new PersonChecker()

        // capacityCounter をモック化
        def capacityCounter = Mock(CapacityCounter)
        // getCount()が常に19を返すようスタブを宣言する
        // 19を返す場合、子どもなら追加できるが、大人は追加できない
        capacityCounter.getCount() >> 19

        // Groovyではプロパティアクセスの簡略記法でsetterにアクセスできる
        sut.personChecker = ageChecker
        sut.capacityCounter = capacityCounter

        when:
        def person1 = new Person("m", 19)
        sut.add(person1)

        then:
        1 * capacityCounter.addCount(1)

        when:
        def person2 = new Person("f", 20)
        sut.add(person2)

        then:
        thrown(IllegalArgumentException)
    }
}
