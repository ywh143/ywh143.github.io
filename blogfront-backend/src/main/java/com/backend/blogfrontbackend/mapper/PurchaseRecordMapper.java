package com.backend.blogfrontbackend.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PurchaseRecordMapper {
    @Insert("""
            INSERT INTO blog.purchase_record
            VALUES(null,#{user2Id},#{courseId},#{cost},NOW(),#{channelId},#{out_trade_no})
            """)
    int Insert_zfb_PurchaseRecord_One(@Param("user2Id") String userId,
                                      @Param("courseId") String courseId,
                                      @Param("cost") double cost,
                                      @Param("channelId") int channelId,
                                      @Param("out_trade_no") String out_trade_no
    );//插入课程（保证课程和用户和支付状态是存在（limit））


//    @Select("""
//            SELECT *  FROM blog.purchase_record
//            """)
//    @Results({
//            @Result(property = "recordId", column = "record_id"),
//            @Result(property = "user", column = "user_id",
//                    javaType = User.class, //要封装的实体类型
//                    one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
//            @Result(property = "course", column = "course_id",
//                    javaType = Course.class, //要封装的实体类型
//                    one = @One(select = "com.backend.mapper.CourseMapper.findCourseById")),
//   /*             @Result(property = "cost", column = "cost"),
//           // @Result(property = "purchaseTime", column = "purchaseTime"),*/
//            @Result(property = "paymentChannel", column = "payment_channel_id",
//                    javaType = PaymentChannel.class, //要封装的实体类型
//                    one = @One(select = "com.backend.mapper.PaymentChannelMapper.findPaymentChannelById")),
//            @Result(property = "outTradeNo", column = "out_trade_no"),
//    })
//    List<PurchaseRecord> AllPurchaseRecord();


//    @Delete("""
//              DELETE FROM blog.purchase_record
//              WHERE blog.purchase_record.out_trade_no = #{out_trade_no}
//            """)
//    int deleteout_trade_no(@Param("out_trade_no") int out_trade_no);


}
