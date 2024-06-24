package com.kris.acg.service.rbac;

import com.kris.acg.entity.rbac.Api;
import com.kris.acg.entity.req.ApiAddReq;
import com.kris.acg.entity.req.ApiModifyReq;

import java.util.List;

/**
 * @author Kristin
 */
public interface ApiService {

    /**
     * 根据关键字查询所有api
     * @param keyWord
     * @return 返回api集合
     */
    List<Api> queryApiByKeyWord(String keyWord);

    /**
     * 根据id 查询api
     * @param id api 的 id
     * @return 返回
     */
    Api queryApiById(Integer id);

    /**
     * 增加一个新的api
     * @param apiAddReq api添加请求封装对象
     */
    void addApi(ApiAddReq apiAddReq);

    /**
     * 删除api
     * @param id 需要删除的api的id
     */
    void deleteApi(Integer id);

    /**
     * 更新api信息
     * @param apiModifyReq api请求更新封装对象
     */
    void updateApi(ApiModifyReq apiModifyReq);
}
