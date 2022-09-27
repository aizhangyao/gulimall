package com.aiz.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @ClassName ListValue
 * @Description 自定义校验注解
 * @Author Administrator
 * @Date Create in 23:16 2020/7/4 0004
 * @Version 1.0
 */

@Documented
@Constraint(
        validatedBy = {ListValueConstraintValidator.class}//可以指定多个不同的校验器，适配不同类型的校验
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListValue {
    String message() default "{com.aiz.common.valid.ListValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] vals() default {};
}
