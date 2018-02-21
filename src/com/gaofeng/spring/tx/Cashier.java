package com.gaofeng.spring.tx;

import java.util.List;

public interface Cashier {
    public void checkout(String name, List<Integer> isbn);
}
