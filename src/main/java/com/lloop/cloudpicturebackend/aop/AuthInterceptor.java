package com.lloop.cloudpicturebackend.aop;

import com.lloop.cloudpicturebackend.annotation.AuthCheck;
import com.lloop.cloudpicturebackend.exception.BusinessException;
import com.lloop.cloudpicturebackend.exception.ErrorCode;
import com.lloop.cloudpicturebackend.exception.ThrowUtils;
import com.lloop.cloudpicturebackend.model.domain.User;
import com.lloop.cloudpicturebackend.model.enums.UserRoleEnum;
import com.lloop.cloudpicturebackend.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author lloop
 * @Create 2025/3/17 16:06
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 1. 获取需要的用户权限
        String mustRole = authCheck.mustRole();
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 2. 如果不需要权限,则直接通过
        if(mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        // 3. 如果需要权限校验,则查询用户权限并进行比较
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        ThrowUtils.throwIf(userRoleEnum == null, new BusinessException(ErrorCode.NO_AUTH_ERROR));
        ThrowUtils.throwIf(mustRoleEnum.equals(UserRoleEnum.ADMIN) && !userRoleEnum.equals(UserRoleEnum.ADMIN), new BusinessException(ErrorCode.NO_AUTH_ERROR));
        return joinPoint.proceed();
    }


}
