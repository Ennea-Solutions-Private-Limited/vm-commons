package com.ennea.enneaservices.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        int size = list.size();
        for(int i = 0; i < size; i += chunkSize){
            int end = Math.min(size, i + chunkSize);
            chunks.add(new ArrayList<>(list.subList(i, end)));
        }
        return chunks;
    }

}
