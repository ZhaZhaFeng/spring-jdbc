package com.gaofeng.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int findBookPriceByIsbn(int isbn) {
        String sql="select price from book where isbn=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,isbn);
    }

    @Override
    public void updateBookStock(int isbn) {
        String sql="select stock from book_stock where isbn=?";
        int stock=jdbcTemplate.queryForObject(sql,Integer.class,isbn);
        if(stock==0){
            throw new BookStockException("库存不足");
        }
        String sql2="update book_stock set stock=(stock-1) where isbn=?";
        jdbcTemplate.update(sql2,isbn);
    }

    @Override
    public void updateUserAccount(String username, double price) {
        String sql="select balance from account where username=?";
        int balance=jdbcTemplate.queryForObject(sql,Integer.class,username);
        if(balance<(int) price ){
            throw new BookStockException("余额不足");
        }
        String sql2="update account set balance=balance-? where username=?";
        jdbcTemplate.update(sql2,price,username);
    }
}
