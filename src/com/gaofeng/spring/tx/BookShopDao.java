package com.gaofeng.spring.tx;

import com.sun.beans.editors.IntegerEditor;

public interface BookShopDao {
    public int findBookPriceByIsbn(int isbn);
    public void updateBookStock(int isbn);
    public void updateUserAccount(String username,double price);
}
