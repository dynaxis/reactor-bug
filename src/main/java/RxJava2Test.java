import io.reactivex.Observable;

import java.util.Collections;

public class RxJava2Test {
    public static void main(String... args) {
        Observable<Integer> source = Observable.create((e) -> {
            for (int i = 0; i < 300; i++) {
                e.onNext(i);
            }
            e.onComplete();
        });
        Observable<Integer> cached = source.cache();

        long sourceCount = source.concatMapIterable(Collections::singleton)
                .distinct().count().blockingGet();
        long cachedCount = cached.concatMapIterable(Collections::singleton)
                .distinct().count().blockingGet();

        System.out.println("source: " + sourceCount);
        System.out.println("cached: " + cachedCount);
    }
}
