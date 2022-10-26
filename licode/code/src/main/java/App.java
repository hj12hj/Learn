import java.io.File;
import java.util.*;

/**
 * @author: hj
 * @date: 2022/10/26
 * @time: 11:19 AM
 */
public class App {
    public static void main(String[] args) {

        File file = new File("");
        boolean exists = file.exists();




        System.out.println(111);

    }


    public static <T> Set<T> difference(Set<T> l1, Set<T> l2) {
        Set<T> result = new HashSet<>();
        for (T t : l1) {
            if (!l2.contains(t)) {
                result.add(t);
            }
        }
        return result;
    }

}
