package com.gjy.zxks.service.inter;

import com.gjy.zxks.po.SourceCode;

public interface SourceCodeServiceInter {
    SourceCode getById(int id);

    void insert(SourceCode sourceCode);
}
