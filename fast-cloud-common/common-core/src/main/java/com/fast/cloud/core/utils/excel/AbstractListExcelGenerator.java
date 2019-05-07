package com.fast.cloud.core.utils.excel;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 *
 * @author batman.qin
 */
public abstract class AbstractListExcelGenerator<BEAN, CONDITION>
        extends AbstractExcelGenerator<BEAN, Object, List<BEAN>, CONDITION> {


    @Override
    protected Map<String, Function<Object, Object>> summaryGetter(List<String> fields, CONDITION condition) {
        return null;
    }

    @Override
    protected List<BEAN> detail(List<BEAN> data) {
        return data;
    }

    @Override
    protected Object summary(List<BEAN> data) {
        return null;
    }
}
