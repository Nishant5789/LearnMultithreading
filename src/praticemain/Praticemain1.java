package praticemain;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

class First
{
    int i = 10;
    public First(int j)
    {
        System.out.println(i);
        this.i = j * 10;
    }
}

class Second extends First
{
    public Second(int j)
    {
        super(j);
        System.out.println(i);
        this.i = j * 20;
    }
}

public class Praticemain1 {
    public static void main(String[] args) {
//        Second n = new Second(20);
//        System.out.println(n.i);
//        Object i = new ArrayList().iterator();
//        System.out.print((i instanceof List) + ", ");
//        System.out.print((i instanceof Iterator) + ", ");
//        System.out.print(i instanceof ListIterator);

        List<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
            list.add("c");
        }
        while (iterator.hasNext()) {
            String element = iterator.next();
            System.out.println(element);
        }
    }
}
