package com.songj.demo.model;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(User.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(null != o){
            User user = (User)o;
            if(StringUtils.isEmpty(user.getName())){
                errors.reject("name", "名称不能为空");
            }
            if(null == user.getAge()){
                errors.reject("age", "年龄不能为空");
            }
        }
    }
}
