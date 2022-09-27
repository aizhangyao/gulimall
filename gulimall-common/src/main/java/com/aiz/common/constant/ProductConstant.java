package com.aiz.common.constant;

/**
 * @ClassName ProductConstant
 * @Description 与商品模块有关的常量
 * @Author Zhang Yao
 * @Date Create in 12:34 2020/7/11 0011
 * @Version 1.0
 */

public class ProductConstant {
    public enum AttrEnum{
        ATTR_TYPE_BASE(1,"基本属性"),ATTR_TYPE_SALE(0,"销售属性");

        private int code;
        private String msg;
        AttrEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 商品状态
     */
    public enum StatusEnum{
        NEW_SPU(0,"新建"),SPU_UP(1,"商品上架"),SPU_DOWN(2,"商品下架");

        private int code;
        private String msg;
        StatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}
