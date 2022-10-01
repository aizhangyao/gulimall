package com.aiz.gulimall.search.service;

import com.aiz.gulimall.search.vo.SearchParam;
import com.aiz.gulimall.search.vo.SearchResult;

/**
 * @ClassName MallSearchService
 * @Description
 * @Author Yao
 * @Date Create in 11:22 下午 2020/11/28
 * @Version 1.0
 */
public interface MallSearchService {
    /**
     *
     * @param searchParam 检索的所有参数
     * @return 返回检索的结果,里面包含页面需要的所有信息
     */
    SearchResult search(SearchParam searchParam);
}
