package com.gaofeng.spring.tx;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringTransactionTest {
    private ApplicationContext context=null;
    private BookShopDao bookShopDao=null;
    private BookShopService bookShopService=null;
    private Cashier cashier=null;
    {
        context=new ClassPathXmlApplicationContext("spring-config.xml");
        bookShopDao=(BookShopDao) context.getBean("bookShopDao");
        bookShopService=(BookShopService) context.getBean("bookShopService");
        cashier=(Cashier) context.getBean("cashier");
    }
    @Test
    public void test1(){
        System.out.println(bookShopDao.findBookPriceByIsbn(1001));
    }
    @Test
    public void testBookShopDaoUpdateBookStock(){
        bookShopDao.updateBookStock(1001);
    }
    @Test
    public void test3(){
        bookShopDao.updateUserAccount("AA",100);
    }
    @Test
    public void testBookShopService(){
        bookShopService.purchase("AA",1001);
    }
    @Test
    public void testTransactionPropagation(){
        cashier.checkout("AA", Arrays.asList(1001,1002));
    }
}
