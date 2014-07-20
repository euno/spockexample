package spockexample

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by yusuke on 2014/07/20.
 */
class FemaleAdultCounterSpec extends Specification {

    def "ポイント加算のメソッドが正しく呼ばれているか"() {
        setup:
        // テスト対象の初期化
        def sut = new FemaleAdultCounter()
        def ageChecker = new AgeChecker()
        def sexChecker = new SexChecker()
        // カウンターをモック化
        Counter counter = Mock()

        // Groovyではプロパティアクセスの簡略記法でsetterにアクセスできる
        sut.ageChecker = ageChecker
        sut.sexChecker = sexChecker
        sut.counter = counter

        when:
        // 20歳女性の場合
        def person1 = new Person("f", 20)
        sut.count(person1)
        then:
        // 3ポイント加算されるメソッドが1度呼ばれたはず
        1 * counter.addCount(3)

        when:
        // 19歳女性の場合
        def person2 = new Person("f", 19)
        sut.count(person2)
        then:
        // 1ポイント加算されるメソッドが1度呼ばれたはず
        1 * counter.addCount(1)

        when:
        // 20歳男性の場合
        def person3 = new Person("m", 20)
        sut.count(person3)
        // 19歳男性の場合
        def person4 = new Person("m", 19)
        sut.count(person4)
        then:
        // いずれも、ポイントを加算するメソッドが1度も呼ばれなかったはず
        0 * counter.addCount(_)
    }
}
