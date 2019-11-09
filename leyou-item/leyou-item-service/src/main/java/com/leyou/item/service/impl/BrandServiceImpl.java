package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: leyou
 * @description:
 * @author: Mr.D
 * @create: 2019-11-09 01:37
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * @Description: 根据查询条件分页并排序查询品牌信息
     * @Param: [key, page, rows, sortBy, desc]
     * @return: com.leyou.common.pojo.PageResult<com.leyou.item.pojo.Brand>
     * @Author: Mr.D
     * @Date: 2019/11/9
     */
    @Override
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //根据name模糊查询或者根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key + "%");
        }

        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = brandMapper.selectByExample(example);

        //先包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        PageResult<Brand> pageResult = new PageResult<>(pageInfo.getTotal(), pageInfo.getList());

        return pageResult;
    }

    /**
     * 新增品牌
     *
     * @param brand
     * @param cids
     */
    @Override
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        //新增一个brand
        brandMapper.insertSelective(brand);
        //然后新增中间表
        cids.forEach(cid -> {
            brandMapper.insertCategoryAndBrand(cid, brand.getId());
        });
    }
}

