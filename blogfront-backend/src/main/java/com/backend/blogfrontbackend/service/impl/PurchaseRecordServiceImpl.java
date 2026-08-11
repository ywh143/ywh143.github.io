package com.backend.blogfrontbackend.service.impl;

import com.backend.blogfrontbackend.entity.course.PurchaseRecord;
import com.backend.blogfrontbackend.mapper.PurchaseRecordMapper;
import com.backend.blogfrontbackend.service.PurchaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {
    @Autowired
    PurchaseRecordMapper purchaseRecordMapper;

/*
    @Override
    public int Insert_zfb_PurchaseRecord_One(String userId, String courseId, double cost, int channelId) {
        return 0;
    }
*/

    @Override
    public int Insert_zfb_PurchaseRecord_One(String userId, String courseId, double cost, int channelId,String out_trade_no) {
        return purchaseRecordMapper.Insert_zfb_PurchaseRecord_One(userId,courseId,cost,channelId,out_trade_no);
    }

//    @Override
//    public List<PurchaseRecord> AllPurchaseRecord() {
//        return purchaseRecordMapper.AllPurchaseRecord();
//    }
//
//    @Override
//    public int deleteout_trade_no(int out_trade_no) {
//        return purchaseRecordMapper.deleteout_trade_no(out_trade_no);
//    }


}
