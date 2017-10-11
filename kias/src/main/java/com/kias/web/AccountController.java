package com.kias.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kias.model.Account;
import com.kias.model.Organization;
import com.kias.model.Userinfo;
import com.kias.service.AccountService;
import com.kias.service.OrganizatonService;
import com.kias.service.UserinfoService;
import com.kias.utils.Const;
import com.kias.utils.ExtReturn;
@RequestMapping("/user")
@Controller
public class AccountController {
	private static Logger logger = LogManager.getLogger(AccountController.class); 
	@Autowired
	public AccountService accountSer;
	@Autowired
	public UserinfoService userinfoSer;
	@Autowired
	public OrganizatonService orgSer;
	//用户登录验证
	@ResponseBody
	@RequestMapping("/login")
	public Object index(HttpServletRequest request,HttpServletResponse response){
		String accountname = request.getParameter("accountname");
		String password = request.getParameter("password");
		Account account = new Account();
		account.setAccountname(accountname);
		account.setPassword(password);
		account.setSalt(accountname+password);
		String departCom = request.getParameter("departCom");  //登录科室
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(accountname,account.getPassword());
        token.setRememberMe(true);
        try {
            user.login(token);
            user.getSession().setAttribute(Const.SESSION_CURRENT_DEPART, departCom);//当前登录科室
            return new ExtReturn(true, "success");
        }catch (AuthenticationException e) {
        	logger.error("登录失败错误信息:"+e);
            token.clear();
            return new ExtReturn(false, "用户名或者密码错误!");
        }		
	}
	//根据工号获取科室信息
	@ResponseBody
	@RequestMapping("/getOrg")
	public List<Organization> departByName(HttpServletRequest request,HttpServletResponse response){
		String loginName = request.getParameter("loginName");//工号
		if(null==loginName || "".equals(loginName)){
			return null;
		}
		Userinfo userinfo = new Userinfo();
		userinfo.setLoginname(loginName);
		List<Userinfo> userList = userinfoSer.findUserByParams(userinfo);
		if(userList.size()>0){
			List<Organization> orgList = orgSer.findOrgByCodes(null, userList.get(0).getLogincodes());
			return orgList;
		}
		return null;
	}
	//退出登录
	 @RequestMapping(value="/logout")    
    public String logout(HttpServletRequest request,HttpServletResponse response){   
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
        SecurityUtils.getSubject().logout(); 
        return "index";  
    }
	public static void main(String[] args) {
		String ad="s,m,k,l,";
		String[] d=ad.split(",");
		for(int i=0;i<d.length;i++){
			System.out.print(d[i]);
		}
	}
}
