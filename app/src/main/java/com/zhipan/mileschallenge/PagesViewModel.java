package com.zhipan.mileschallenge;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PagesViewModel extends ViewModel {

    private List<Integer> pageIdList;

    public PagesViewModel() {
        pageIdList = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
    }


    public List<Integer> getPageIdList() {
        return pageIdList;
    }
}
