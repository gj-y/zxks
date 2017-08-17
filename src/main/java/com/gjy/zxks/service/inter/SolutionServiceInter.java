package com.gjy.zxks.service.inter;

import com.gjy.zxks.po.Solution;

import java.util.List;

public interface SolutionServiceInter {

    int update(Solution solution);

    int checkout(String sid, String result);

    Solution getById(int sid);

    void insert(Solution solution);

    List<Solution> getByTestId(int testid);
}
