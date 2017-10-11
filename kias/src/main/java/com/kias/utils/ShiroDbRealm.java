package com.kias.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.kias.model.Account;
import com.kias.service.AccountService;
import com.kias.service.RoleService;

public class ShiroDbRealm extends AuthorizingRealm {
	@Autowired 
	private AccountService accountSer;  
	@Autowired
	private RoleService roleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Account at=(Account)SecurityUtils.getSubject().getSession().getAttribute(Const.SESSION_USER); 
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        Set<String> permissionsSetStr = new HashSet<String>();  
        try {  
        	Set<String> roleSet = new HashSet<String>();
        	if(at.getRoleCodes().length()>0){
        		String [] stringArr= at.getRoleCodes().split(",");
        		roleSet=new HashSet<String>(Arrays.asList(stringArr));
        	}else{
        		roleSet = Collections.emptySet();
        	}
            authorizationInfo.setRoles(roleSet); 
            permissionsSetStr = roleService.findResByRoles(at.getRoleCodes());  
            authorizationInfo.setStringPermissions(permissionsSetStr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return authorizationInfo;  
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		 UsernamePasswordToken token = (UsernamePasswordToken)arg0;
		 SimpleAuthenticationInfo authenticationInfo = null;  
		 Account at = new Account();
		 at.setAccountname(token.getUsername());
		 at.setPassword(String.valueOf(token.getPassword()));
		 try {
			 Account account = accountSer.findAccountByUsername(token.getUsername());
			 if(account==null){
	             throw new UnknownAccountException();//没找到帐号  
	         }
	         if(account.getLocked().equals("0")){
	        	 throw new LockedAccountException(); //帐号锁定  
	         }
	         //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配  
	         authenticationInfo = new SimpleAuthenticationInfo(
	                 account.getAccountname(), //用户名  
	                 account.getPassword(), //密码  
	                 ByteSource.Util.bytes(account.getSalt()),//salt=username+salt  
	                 this.getName()  //realm name
	         );
	         Session session = SecurityUtils.getSubject().getSession();  
	         session.setAttribute(Const.SESSION_USER, account);
	         session.setAttribute(Const.SESSION_REALNAME, account.getRealname());
		 	} catch (Exception e) {  
	            e.printStackTrace();  
	        } 
		return authenticationInfo;
	}

	@Override  
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {  
        super.clearCachedAuthorizationInfo(principals);  
    }  
  
    @Override  
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {  
        super.clearCachedAuthenticationInfo(principals);  
    }  
  
    @Override  
    public void clearCache(PrincipalCollection principals) {  
        super.clearCache(principals);  
    }  
  
    public void clearAllCachedAuthorizationInfo() {  
        getAuthorizationCache().clear();  
    }  
  
    public void clearAllCachedAuthenticationInfo() {  
        getAuthenticationCache().clear();  
    }  
  
    public void clearAllCache() {  
        clearAllCachedAuthenticationInfo();  
        clearAllCachedAuthorizationInfo();  
    } 
	public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "123456";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("user");
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations).toHex();
        System.out.println(obj);
    }
}
