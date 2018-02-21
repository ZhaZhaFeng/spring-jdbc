package com.gaofeng.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {
    @Autowired
    private BookShopDao bookShopDao;
    //添加事务注解
    //1、使用propagation指定事务的传播行为，即当前的事务方法被另一个事务方法调用时如何使用事务
    //默认取值为REQUIRED，即使用调用方法的事务
    //2、REQUIREDS_NEW:事务自己的事务，调用的事务方法事务被挂起
    //3、使用isolation指定事务隔离级别，最长用的取值为READ_COMMITTED
    //4、默认情况下spring的声明使事务对所有的运行时异常进行回滚，也可以他通过对应的属性进行设置
    //5、若真的是一个只读数据库值的方法，应设置readOonly=true
    //6、使用timeout指定强制回滚之前事务可以占用的时间
   /* @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED，
            noRollbackFor = {UserAccountException.class},
            readOnly=false,
            timeout=1)*/
    @Override
    public void purchase(String username, int isbn) {
        //1、获取书的单价
        double price=bookShopDao.findBookPriceByIsbn(isbn);
        //更新数据库
        bookShopDao.updateBookStock(isbn);
        //更新用户余额
        bookShopDao.updateUserAccount(username,price);
    }
}
