package com.badu.service;

import com.badu.model.OrderExp;

import java.util.List;

public interface IOrderExpService {
    List<OrderExp> findAll();
    void insert(int orderNumber) ;
}
