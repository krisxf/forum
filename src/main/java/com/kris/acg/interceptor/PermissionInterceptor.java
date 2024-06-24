package com.kris.acg.interceptor;

import com.kris.acg.common.Constant;
import com.kris.acg.entity.rbac.*;
import com.kris.acg.enums.MyHttpMethod;
import com.kris.acg.exception.BusinessException;
import com.kris.acg.service.rbac.AuthorizeService;
import com.kris.acg.service.rbac.UserRoleService;
import com.kris.acg.utils.ApiTreeUtils;
import com.kris.acg.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: acg
 * @Description: 权限拦截拦截器
 * @Author: kris
 * @Create: 2024-06-06 14:48
 **/

@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Resource
    ApiTreeFactory apiTreeFactory;
    @Resource
    UserRoleService userRoleService;
    @Resource
    AuthorizeService authorizeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求路径
        String requestURI = request.getRequestURI();
        // 获取请求方式
        String method = request.getMethod();

        // 获取Api树
        Api api = ApiTreeUtils.getMatchApi(apiTreeFactory.getApiTree(), requestURI, method);

        //api不存在
        if(api == null){
//            UserContext.remove();
//            throw new BusinessException("api 不存在");
            return true;
        }

        // 获取当前用户id，如果无则默认为游客，设定默认权限
        Long userId = UserContext.getUserId();
        if(userId == null){
            userId = (long) Constant.DEFAULT_ID;
        }
        // 查询当前用户角色以及权限信息
        List<UserRole> userRoles = userRoleService.queryRoleByUserId(userId);
        List<Integer> permissions = new ArrayList<>();
        for (UserRole r: userRoles
             ) {
            List<Permission> permissions1 = authorizeService.queryPermissionByRoleId(r.getRoleId());
            for (Permission p: permissions1
                 ) {
                permissions.add(p.getId());
            }
        }

        // 判断当前api是否允许当前权限使用
        if(permissions.contains(api.getPermissionId())){
            return true;
        }else{
            throw new BusinessException("权限不足");
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
