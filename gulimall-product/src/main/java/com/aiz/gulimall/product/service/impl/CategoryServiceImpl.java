package com.aiz.gulimall.product.service.impl;

import com.aiz.gulimall.product.entity.CategoryBrandRelationEntity;
import com.aiz.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aiz.common.utils.PageUtils;
import com.aiz.common.utils.Query;

import com.aiz.gulimall.product.dao.CategoryDao;
import com.aiz.gulimall.product.entity.CategoryEntity;
import com.aiz.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Resource
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1.查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);

        //2.组装成父子的树形结构
        //2.1找到所有的一级分类
//        List<CategoryEntity> level1Menus = entities.stream().filter((categoryEntity) -> {
//            return categoryEntity.getParentCid() == 0;
//        }).collect(updateBatchByIdCollectors.toList());

//        List<CategoryEntity> level1Menus = entities.stream().filter(categoryEntity ->
//                categoryEntity.getParentCid() == 0
//        ).collect(Collectors.toList());

        List<CategoryEntity> level1Menus = entities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).map((menu) -> {
            menu.setChildren(getChildrens(menu, entities));
            return menu;
        }).sorted((menu1, menu2) -> {
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //1.检查当前的菜单，是否被别的地方引用
        List<CategoryBrandRelationEntity> categoryBrandRelation =
                categoryBrandRelationService.list(new QueryWrapper<CategoryBrandRelationEntity>().in("catelog_id", asList));
        if (categoryBrandRelation.size() == 0) {
            //2.逻辑删除
            baseMapper.deleteBatchIds(asList);
        } else {
            throw new RuntimeException("该菜单下面还有属性，无法删除!");
        }
    }

    //[2,25,225]
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);
        Collections.reverse(parentPath);
        return (Long[]) parentPath.toArray();
    }

    /**
     * 级联更新所有关联的数据
     * @param category
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    //225,25,2
    private List<Long> findParentPath(Long catelogId,List<Long> paths){
        //1.收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);
        }
        return paths;
    }

    //递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all.stream().filter(categoryEntity -> {
            return categoryEntity.getParentCid() == root.getCatId();
        }).map(categoryEntity -> {
            //1.找到子菜单
            categoryEntity.setChildren(getChildrens(categoryEntity, all));
            return categoryEntity;
        }).sorted((menu1, menu2) -> {
            //2.菜单的排序
            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
        }).collect(Collectors.toList());
        return children;
    }

}