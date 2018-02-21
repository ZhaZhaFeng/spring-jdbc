package com.gaofeng.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cashier")
public class CashierImpl implements Cashier {
    @Autowired
    private BookShopService bookShopService;

    @Transactional
    @Override
    public void checkout(String name, List<Integer> isbns) {
        for(int isbn:isbns){
            bookShopService.purchase(name,isbn);
        }
    }
}
