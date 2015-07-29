package com.authority.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.authority.dm.AuthorityVO;
import com.authority.entity.Account;
import com.authority.entity.User;
import com.authority.service.AccountService;
import com.authority.service.AuthorityService;
import com.authority.service.RoleService;
import com.authority.service.UserService;

/**
 * 登陆、缓存信息加载
 * @author daxinxin
 */
@Controller
@RequestMapping("/framework/login")
@SessionAttributes("authority")
public class LoginController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    
    
    /**
     * 验证用户是否存在，成功返回T，失败返回F
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public String login(ModelMap model, String name, String pwd, HttpSession session) {
        Account account = accountService.valiAccount(name, pwd);
        if(account != null) {
            //验证成功，查询用户信息和权限信息
            User user = userService.queryUserById(account.getUserId());
//            List<Role> roleList = roleService.queryRoleByUser(user);
//            Map<String, Module> moduleMap = authorityService.getModuleByRole(roleList);
//            AuthorityVO vo = authorityService.getAuthority(account, user, moduleMap);
//            //将权限信息加入会话级别的session中
//            model.addAttribute("accountName", account.getAccountName());
//            model.addAttribute("username", user.getName());
//            model.addAttribute("authority", vo);
//            //将权限信息加入到本地缓存中
//            LocalCache.put(account.getAccountName(), vo);
            return "T";
        }
        return "F";
    }
    
    /**
     * 跳转到main页面的方法
     * @param account
     * @return
     */
    @RequestMapping(value = "/main")
    public ModelAndView main(ModelMap model, HttpSession session) {
        //转发到主页面
        return new ModelAndView("main", model);
    }
    
}