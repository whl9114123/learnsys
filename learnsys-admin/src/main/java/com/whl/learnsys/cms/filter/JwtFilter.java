//package com.whl.learnsys.cms.filter;
//
//import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
//import com.whl.common.exception.RRException;
//import com.whl.common.util.JwtUtils;
//import io.jsonwebtoken.Claims;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//
//public class JwtFilter extends FormAuthenticationFilter {
//
//
//    /**
//     * 判断token是否为空、过期
//     *
//     * @param request
//     * @param response
//     * @param mappedValue
//     * @return
//     */
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        String token = getRequestToken((HttpServletRequest) request);
//        if (ObjectUtils.isNull(token)) {
//            return false;
//        }
//        if (StringUtils.isBlank(token)) {
//            throw new RRException("token不能为空", 10003);
//        }
//        Claims claims = JwtUtils.parseJwt(token);
//        if (ObjectUtils.isNull(claims)) {
//            throw new RRException("token过期", 10004);
//        }
//        return true;
//    }
//
//    /**
//     * 上面的方法如果返回false,则接下来会执行这个方法,如果返回为true,则不会执行这个方法
//     * 判断是否为登录url,进一步判断请求是不是post
//     *
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        if (isLoginRequest(request, response)) {
//            if (isLoginSubmission(request, response)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 获取请求中的token,首先从请求头中获取,如果没有,则尝试从请求参数中获取
//     *
//     * @param request
//     * @return
//     */
//    private String getRequestToken(HttpServletRequest request) {
//        String token = request.getHeader(JwtUtils.token);
//        if (StringUtils.isBlank(token)) {
//            token = request.getParameter(JwtUtils.token);
//        }
//        return token;
//    }
//
//}
