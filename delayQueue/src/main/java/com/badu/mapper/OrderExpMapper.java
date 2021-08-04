package com.badu.mapper;

import com.badu.model.OrderExp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderExpMapper {
    List<OrderExp> findAll();
    void  insertDelayOrder(@Param("order") OrderExp order,
                           @Param("expire_duration") long expire_duration);
    OrderExp findById(@Param("id")int id);
}
