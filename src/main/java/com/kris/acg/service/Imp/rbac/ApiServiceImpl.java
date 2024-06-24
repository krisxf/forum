package com.kris.acg.service.Imp.rbac;

import com.kris.acg.entity.rbac.Api;
import com.kris.acg.entity.req.ApiAddReq;
import com.kris.acg.entity.req.ApiModifyReq;
import com.kris.acg.enums.MyHttpMethod;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.mapper.ApiMapper;
import com.kris.acg.service.rbac.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Program: acg
 * @Description:
 * @Author: kris
 * @Create: 2024-06-11 14:32
 **/

@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

    @Resource
    ApiMapper apiMapper;

    @Override
    public List<Api> queryApiByKeyWord(String keyWord) {
        return apiMapper.selectApiByKeyWord(keyWord);
    }

    @Override
    public Api queryApiById(Integer id) {

        return apiMapper.selectApiById(id);
    }

    @Override
    public void addApi(ApiAddReq apiAddReq) {
        String path = apiAddReq.getPath();
        String note = apiAddReq.getNote();
        MyHttpMethod method = apiAddReq.getMethod();
        //todo 权限id待修改
        Integer permissionId = 30;

        Api api = new Api();
        api.setPath(path);
        api.setMethod(method);
        api.setDeleted(false);
        api.setEnable(true);
        api.setNote(note);
        api.setPermissionId(permissionId);
        Date now = new Date();
        api.setCreateTime(now);
        api.setUpdateTime(now);

        int i = apiMapper.addApi(api);

        if(i <= 0){
            throw new BusinessException("api插入失败");
        }
    }

    @Override
    public void deleteApi(Integer id) {
        int i = apiMapper.deleteApi(id);
        if(i <= 0){
            throw new BusinessException("api删除失败");
        }
    }

    @Override
    public void updateApi(ApiModifyReq apiModifyReq) {
        Integer id = apiModifyReq.getId();
        Api api = apiMapper.selectApiById(id);
        api.setNote(apiModifyReq.getNote());
        api.setPermissionId(apiModifyReq.getPermissionId());
        api.setPath(apiModifyReq.getPath());
        api.setUpdateTime(new Date());

        int i = apiMapper.updateApi(api);
        if(i <= 0){
            throw new BusinessException("api更新失败");
        }
    }
}
