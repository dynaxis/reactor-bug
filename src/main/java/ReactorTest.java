import reactor.core.publisher.Flux;

import java.util.Collections;

public class ReactorTest {
    public static void main(String... args) {
        Flux<Integer> source = Flux.create((sink) -> {
            for (int i = 0; i < 300; i++) {
                sink.next(i);
            }
            sink.complete();
        });
        Flux<Integer> cached = source.cache();

        long sourceCount = source.concatMapIterable(Collections::singleton)
                .distinct().count().block();
        long cachedCount = cached.concatMapIterable(Collections::singleton)
                .distinct().count().block();

        System.out.println("source: " + sourceCount);
        System.out.println("cached: " + cachedCount);
    }
}
