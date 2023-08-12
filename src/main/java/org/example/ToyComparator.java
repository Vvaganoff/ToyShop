package org.example;

import java.util.Comparator;

public class ToyComparator implements Comparator<Toy1> {
    @Override
    public int compare(Toy1 o1, Toy1 o2) {
            return o1.compareTo(o2);
    }
}
