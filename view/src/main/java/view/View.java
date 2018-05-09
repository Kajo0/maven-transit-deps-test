package view;

//import accessmanager.AccessManager;
//import com.google.common.collect.Lists;
import framework.Framework;

public class View {

    public static void main(String[] args) {
        System.out.println(new Framework().formatPhone("555333111"));

//        new AccessManager().sort(Lists.newArrayList(1, 2, 3), 1).forEach(System.out::println);
    }

}
