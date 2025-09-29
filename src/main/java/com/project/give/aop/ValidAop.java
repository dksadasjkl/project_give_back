package com.project.give.aop;

import com.project.give.dto.account.request.NicknameCheckRequestDto;
import com.project.give.dto.account.request.OAuth2SignupRequestDto;
import com.project.give.dto.account.request.UserSignupRequestDto;
import com.project.give.dto.account.request.UsernameCheckRequestDto;
import com.project.give.exception.ValidException;
import com.project.give.repository.UserMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ValidAop {

    @Autowired
    private UserMapper userMapper;

    @Pointcut("@annotation(com.project.give.aop.annotation.ValidAspect)")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();

        Object[] args = proceedingJoinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for (Object arg : args) {
            if (arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }
        }

        if(methodName.equals("checkUsername")) {
            UsernameCheckRequestDto usernameCheckRequestDto = null;
            for(Object arg : args) {
                if(arg.getClass() == UsernameCheckRequestDto.class) {
                    usernameCheckRequestDto = (UsernameCheckRequestDto) arg;
                }
            }
            if(userMapper.countByUsername(usernameCheckRequestDto.getUsername()) > 0){
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
                bindingResult.addError(objectError);
            }
        }

        if(methodName.equals("checkNickname")) {
            NicknameCheckRequestDto nicknameCheckRequestDto = null;
            for(Object arg : args) {
                if(arg.getClass() == NicknameCheckRequestDto.class) {
                    nicknameCheckRequestDto = (NicknameCheckRequestDto) arg;
                }
            }
            if(userMapper.countByNickname(nicknameCheckRequestDto.getNickname()) > 0){
                ObjectError objectError = new FieldError("nickname", "nickname", "이미 존재하는 닉네임입니다.");
                bindingResult.addError(objectError);
            }
        }


        if(methodName.equals("signup")) {
            UserSignupRequestDto authSignupRequestDto = null;
            for(Object arg : args) {
                if(arg.getClass() == UserSignupRequestDto.class) {
                    authSignupRequestDto = (UserSignupRequestDto) arg;
                }
            }
            if(userMapper.findUserByUsername(authSignupRequestDto.getUsername()) != null){
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
                bindingResult.addError(objectError);
            }
        }

        if(methodName.equals("oAuth2Signup")) {
            OAuth2SignupRequestDto oAuth2SignupRequestDto = null;
            for(Object arg : args) {
                if(arg.getClass() == OAuth2SignupRequestDto.class) {
                    oAuth2SignupRequestDto = (OAuth2SignupRequestDto) arg;
                }
            }
            if(userMapper.findUserByUsername(oAuth2SignupRequestDto.getUsername()) != null){
                ObjectError objectError = new FieldError("username", "username", "이미 존재하는 사용자이름입니다.");
                bindingResult.addError(objectError);
            }
        }

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errorMap.put(fieldName, message);
            }
            throw new ValidException(errorMap);
        }

        return proceedingJoinPoint.proceed();
    }
}