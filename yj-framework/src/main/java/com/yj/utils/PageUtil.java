package com.yj.utils;

import com.yj.domain.vo.PageVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Package com.yj.utils
 * @Author yJade
 * @Date 2023-02-22 17:28
 */
public class PageUtil {
    public static <T> PageVO<T> listConvertToPage(Integer pageNum, Integer pageSize, List<T> list, Comparator<T> comparator) {
        if (Objects.nonNull(comparator)) {
            list.sort(comparator);
        }
        PageVO<T> pageVO = new PageVO<>();
        //总记录数
        pageVO.setTotal((long) list.size());
        //总页数
        pageVO.setPageTotal((long) ((list.size() + pageSize - 1) / pageSize));

        //手动分页
        List<T> rows = new ArrayList<>();
        for (int i = (pageNum - 1) * pageSize; i < pageNum * pageSize && i < list.size(); i++) {
            rows.add(list.get(i));
        }
        pageVO.setRows(rows);

        return pageVO;
    }

    public static <T> PageVO<T> listConvertToPage(Integer pageNum, Integer pageSize, List<T> list) {
        return listConvertToPage(pageNum, pageSize, list, null);
    }
}
