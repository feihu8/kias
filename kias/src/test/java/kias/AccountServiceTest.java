package kias;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kias.model.Account;
import com.kias.service.AccountService;

public class AccountServiceTest extends TestBaseCase {
	@Autowired
	private AccountService aser;
	 @Test  
    public void selectUserByIdTest(){  
        Account atd= aser.findAccountByUsername("2360");
        System.out.println(atd.getAccountname()+"--"+atd.getSalt());
        logger.info("查找结果" + atd);  
    }  
}
