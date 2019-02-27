package com.dzf.serviceprovideruser.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@RestController
public class UserController {

    @RequestMapping(value="/query/user/{id}")
    public String queryUserById(@PathVariable("id")String id){
        return "user:" + id;
    }

}
