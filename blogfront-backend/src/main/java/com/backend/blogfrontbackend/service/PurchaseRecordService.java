package com.backend.blogfrontbackend.service;

import com.backend.blogfrontbackend.entity.course.PurchaseRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PurchaseRecordService {


    int Insert_zfb_PurchaseRecord_One(String userId, String courseId, double cost, int channelId,String out_trade_no);

//    List<PurchaseRecord> AllPurchaseRecord();//zhao全部


//    int deleteout_trade_no(int out_trade_no);


}
