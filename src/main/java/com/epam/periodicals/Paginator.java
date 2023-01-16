package com.epam.periodicals;

import java.util.ArrayList;
import java.util.List;

public class Paginator <K> {
    private final List <K> outputList = new ArrayList<K>();
    public List <K> listDevider(int page, List <K>inputList) {

        for (K o :inputList) {
            if (inputList.indexOf(o)/5 == page - 1) {
                outputList.add(o);
            }
        }
        return outputList;
    }
}
