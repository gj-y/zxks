package com.gjy.zxks.service.inter;

import com.gjy.zxks.po.ProblemWithBLOBs;
import com.gjy.zxks.util.Page;

import java.io.IOException;
import java.util.List;

public interface ProblemServiceInter {

    ProblemWithBLOBs getById(int id);

    List<ProblemWithBLOBs> loadAll();

    Page loadByPage(Page page);

    int deleteById(int id);

    boolean addProblem(ProblemWithBLOBs problem) throws IOException;

    void update(ProblemWithBLOBs problem);
}
