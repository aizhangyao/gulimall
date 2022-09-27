package com.aiz.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName ListValueConstraintValidator
 * @Description 自定义的校验器
 * @Author ZhangYao
 * @Date Create in 23:51 2020/7/4 0004
 * @Version 1.0
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Integer> {
    private Set<Integer> set = new HashSet<>();

    //初始化方法
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int val : vals){
            set.add(val);
        }
    }

    //判断是否校验成功

    /**
     *
     * @param value 需要校验的值
     * @param Context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext Context) {
        return set.contains(value);
    }
}
