//package com.kris.acg.fliter;
//
//import cn.hutool.jwt.Claims;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.kris.acg.common.HeaderKeyConstant;
//import com.kris.acg.service.TokenService;
//import com.kris.acg.utils.JwtUtil;
//import com.kris.acg.utils.UserContext;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Program: acg
// * @Description:
// * @Author: kris
// * @Create: 2023-09-18 18:46
// **/
//
//public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {
//
//    @Resource
//    TokenService tokenService;
//
//    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {
//        // 获取请求头中JWT的Token
//        String tokenHeader = request.getHeader(HeaderKeyConstant.AUTHORIZATION);
//        if (null!=tokenHeader && tokenHeader.startsWith(JWTConfig.tokenPrefix)) {
//            try {
//                // 截取JWT前缀
//                String token = tokenHeader.replace(JWTConfig.tokenPrefix, "");
//                // 解析JWT
//                Claims claims = Jwts.parser()
//                        .setSigningKey(JWTConfig.secret)
//                        .parseClaimsJws(token)
//                        .getBody();
//                // 获取用户名
//                String username = claims.getSubject();
//                String userId=claims.getId();
//                if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(userId)) {
//                    // 获取角色
//                    List<GrantedAuthority> authorities = new ArrayList<>();
//                    String authority = claims.get("authorities").toString();
//                    if(!StringUtils.isEmpty(authority)){
//                        List<Map<String,String>> authorityMap = JSONObject.parseObject(authority, List.class);
//                        for(Map<String,String> role : authorityMap){
//                            if(!StringUtils.isEmpty(role)) {
//                                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
//                            }
//                        }
//                    }
//                    //组装参数
//                    SelfUserEntity selfUserEntity = new SelfUserEntity();
//                    selfUserEntity.setUsername(claims.getSubject());
//                    selfUserEntity.setUserId(Long.parseLong(claims.getId()));
//                    selfUserEntity.setAuthorities(authorities);
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, userId, authorities);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            } catch (ExpiredJwtException e){
//                log.info("Token过期");
//            } catch (Exception e) {
//                log.info("Token无效");
//            }
//        }
//        filterChain.doFilter(request, response);
//        return;
//    }
//}
