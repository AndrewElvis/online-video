package zust.xyt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author AndrewElvis
 * @date 2020-06-29-19:51
 * @description
 */
public class NumTest {
    @Test
    public void test1() {
        List<Long> nums = new ArrayList<>();
        nums.add(3L);
        nums.add(1L);
        nums.add(11L);
        nums.add(7L);

        nums.sort(Comparator.comparingLong(a -> a));
        nums.forEach(System.out::println);
    }
}
