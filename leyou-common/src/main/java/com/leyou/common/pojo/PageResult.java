package com.leyou.common.pojo;

import java.util.List;

/**
 * @program: leyou
 * @description: 分页结果集
 * @author: Mr.D
 * @create: 2019-11-09 00:09
 **/
public class PageResult<T> {
    //总数量
    private Long total;
    //总页数
    private Integer totalPage;
    //当前页的数据
    private List<T> items;

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
