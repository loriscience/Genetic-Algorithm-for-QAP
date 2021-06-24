package com.company;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    private ListUtils() {
    }

    public static ArrayList<Integer> getFilledList(int size) {
        ArrayList<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }

    public static ArrayList<Integer> getFilledListWithZeroes(int size) {
        ArrayList<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(0);
        }
        return result;
    }

    public static ArrayList<Integer> getFilledListWithMinusOne(int size) {
        ArrayList<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(-1);
        }
        return result;
    }

    public static List<Boolean> getFalseList(int size) {
        List<Boolean> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(false);
        }
        return result;
    }
}
