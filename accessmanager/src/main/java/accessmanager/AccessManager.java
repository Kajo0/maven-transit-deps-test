package accessmanager;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class AccessManager {

    public Collection<Integer> sort(List<Integer> toBeSorted, final Integer filterOut) {
        return Collections2.filter(toBeSorted, i -> !Objects.equals(i, filterOut));
    }

    public static void main(String[] args) {
        new AccessManager().sort(Lists.newArrayList(1, 2, 3), 2).forEach(System.out::println);
    }

}
