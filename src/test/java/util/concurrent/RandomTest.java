package util.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomTest {

    @Test
    public void 확률_테스트() {
        //given
        List<Ad> adList = Arrays.asList(
                generate(1L, 5_000),
                generate(2L, 3_000),
                generate(3L, 2_000)
        );

        Long total = adList.stream()
                .map(Ad::getPrice)
                .reduce(0L, (a, b) -> a + b);


        double probability = (double) adList.get(0).getPrice() / (double) total;

        //when
        int count = 0;
        for (int i = 0; i < 100; i++) {
            double rand = ThreadLocalRandom.current().nextDouble(0, 1);
            if (probability > rand) {
                count++;
            }
        }

        //then
        assertThat(count).isGreaterThanOrEqualTo(4);
    }

    private Ad generate(Long id, long price) {
        return new Ad(id, price);
    }

    private static class Ad {
        private Long id;
        private long price;

        public Ad(Long id, long price) {
            this.id = id;
            this.price = price;
        }

        public Long getId() {
            return id;
        }

        public long getPrice() {
            return price;
        }
    }
}
