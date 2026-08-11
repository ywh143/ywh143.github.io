package com.backend.blogfrontbackend.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.backend.blogfrontbackend.entity.course.PaymentConstants;
import com.backend.blogfrontbackend.service.PurchaseRecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PaymentController {
    @Autowired
    private PurchaseRecordService purchaseRecordService;


    @RequestMapping("app_pay")
    @ResponseBody
    public Map PaymentController() {
        Map result = new HashMap();
        try {

            AlipayClient alipayClient = new DefaultAlipayClient(PaymentConstants.URL,
                    PaymentConstants.APPID,
                    PaymentConstants.PRIVATE_KEY,
                    PaymentConstants.FORMAT,
                    PaymentConstants.CHARSET,
                    PaymentConstants.ALIPAY_PUBLIC_KEY,
                    PaymentConstants.SIGN_TYPE);
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setNotifyUrl("");//花生壳配置的url
            request.setReturnUrl("");
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", "20210817010101004");//订单号（系统生成）
            bizContent.put("total_amount", 0.01);//支付宝（元）、微信（分）
            bizContent.put("subject", "测试商品");
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

            request.setBizContent(bizContent.toString());
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                System.out.println(response.getBody());
                result.put("body", response.getBody());
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping("/pc_pay")
    public void pc(HttpServletResponse servletResponse,
                   @RequestParam String subject,
                   @RequestParam String traceNo,
                   @RequestParam double totalAmount) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(PaymentConstants.URL,
                    PaymentConstants.APPID,
                    PaymentConstants.PRIVATE_KEY,
                    PaymentConstants.FORMAT,
                    PaymentConstants.CHARSET,
                    PaymentConstants.ALIPAY_PUBLIC_KEY,
                    PaymentConstants.SIGN_TYPE);
            System.out.println(servletResponse +"asdasd");
            System.out.println(subject+","+traceNo+","+totalAmount);
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            request.setNotifyUrl("");//花生壳配置的url
            request.setReturnUrl(PaymentConstants.RETURN_URL);
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", System.currentTimeMillis() + "");//订单号（系统生成）(支付号、交易流水号)
            System.out.println("订单号:" + bizContent.getString("out_trade_no"));//打印订单号用来调用关单操作


            bizContent.put("total_amount", 0.01);//支付宝（元）、微信（分）

            bizContent.put("subject", subject);//产品名称

            bizContent.put("product_code", traceNo);

            request.setBizContent(bizContent.toString());

            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);

            if (response.isSuccess()) {

                System.out.println(response.getBody());

                servletResponse.setContentType("text/html;charset=UTF-8");  //设置servlet响应的编码 和格式

                PrintWriter writer = servletResponse.getWriter();  //写输出流

                writer.write("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Title</title>\n" +
                        "</head>\n" +
                        "<body>");  //写头内容

                writer.write(response.getBody());
                writer.write("</body>\n" +
                        "</html>\n"); //写尾内容
                writer.flush();//缓冲区中的内容写入到目标输出流
                writer.close();//关闭输出流
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/pc_return")
    public void pcReturn(HttpServletRequest request, HttpServletResponse servletResponse) {
        try {
            Map<String, String[]> params = request.getParameterMap();//getParameterMap这个方法可以拿到所有的参数
            Map<String, String> map = new HashMap<>(); //将异步通知中收到的所有参数(所有参数都在Request里面)都存放到map中
            for (String s : params.keySet()) {//P3 17.58
                map.put(s, params.get(s)[0]);
            }

            boolean signVerified = AlipaySignature.rsaCheckV1(map, PaymentConstants.ALIPAY_PUBLIC_KEY,
                    PaymentConstants.CHARSET, PaymentConstants.SIGN_TYPE); //调用SDK验证签名


            servletResponse.setContentType("text/html;charset=UTF-8");  //设置servlet响应的编码 和格式
            PrintWriter writer = servletResponse.getWriter();  //写输出流
            writer.write("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "</head>\n" +
                    "<body>");  //写头内容


            if (signVerified) {
                writer.write("success");
                // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            } else {
                writer.write("success");
                // TODO 验签失败则记录异常日志，并在response中返回failure.
            }


            writer.write("</body>\n" +
                    "</html>\n"); //写尾内容
            writer.flush();//缓冲区中的内容写入到目标输出流
            writer.close();//关闭输出流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*      退款回调和支付回调都是走这里
     * 要判断
     *  退款要加入退款单
     *   refund_fee：总退款金额。退款通知中，返回总退款金额，单位为人民币（元），精确到小数点后 2 位
     *   gmt_refund：交易退款时间。格式为 yyyy-MM-dd HH:mm:ss.S
     *            */
    @RequestMapping("alipay/callback")
    @ResponseBody
    public String callback(HttpServletRequest request, HttpServletResponse servletResponse) {
        System.out.println("callback ==>");
        try {
            Map<String, String[]> params = request.getParameterMap();//getParameterMap这个方法可以拿到所有的参数
            Map<String, String> map = new HashMap<>(); //将异步通知中收到的所有参数(所有参数都在Request里面)都存放到map中
            System.out.println("  for (String s : params.keySet()) == begin ==> ");
            for (String s : params.keySet()) {//P3 17.58
                map.put(s, params.get(s)[0]);
                System.out.print("params.get(s)[0] ==>");
                System.out.print(params.get(s)[0]);
                System.out.println();
            }
            System.out.println("  for (String s : params.keySet()) == end ==> ");
            boolean signVerified = AlipaySignature.rsaCheckV1(map, PaymentConstants.ALIPAY_PUBLIC_KEY,
                    PaymentConstants.CHARSET, PaymentConstants.SIGN_TYPE); //调用SDK验证签名
            if (signVerified) {
                System.out.println("异步成功");
                System.out.print("map.get(out_trade_no) ==>");
                System.out.println(map.get("out_trade_no"));
                if (map.get("refund_fee") != null) {
                    System.out.println("这是一个退款单");
                    AlipayTradeFastpayRefundQueryResponse queryResponse = queryRefund(map.get("trade_no"), map.get("out_biz_no"));
                    System.out.println("退款状态:" + queryResponse.getRefundStatus());
                } else {
                    System.out.println("这是一个支付单");
                    AlipayTradeQueryResponse queryResponse = query(map.get("out_trade_no"));
                    if (!queryResponse.getTotalAmount().equals(map.get("total_amount"))) {//查询支付金额和回调交易金额一样（预防报文被修改） && 数据库金额一样（所有钱一致）
                        //校验查询出的支付金额和回调金额，查询金额和数据库订单金额
                        return "failure";
                    }
                    if (queryResponse.getTradeStatus().equals("TRADE_SUCCESS")) {
                        //交易成功，更新订单
                        System.out.println("支付成功");
                    } else {
                        System.out.println("支付失败");
                        //交易不成功，发短信
                    }
                }
//                System.out.print("map.get(out_trade_no) 支付宝交易号 ==>");
//                System.out.println(map.get("out_trade_no"));//支付宝交易号   -存起来
//
//                System.out.print("map.get(trade_no) 支付宝交易号 ==>");
//                System.out.println(map.get("trade_no"));// X 支付宝交易号   -存起来
//
//                System.out.print("map.get(trade_status) 交易状态 ==>");
//                System.out.println(map.get("trade_status")); //交易状态
//
//                System.out.print("map.get(total_amount) 付款金额（对比数据库金额预防篡改） ==>");
//                System.out.println(map.get("total_amount"));//付款金额（对比数据库金额预防篡改）
//
//                System.out.print("map.get(notify_id)       通知校验 ID    判断通知消息是否同一支付账单 ==>");
//                System.out.println(map.get("notify_id"));//	 通知校验 ID    判断通知消息是否同一支付账单

                //第一次不直接修改数据库，以防假报文（调用支付宝查询接口）

                String OUT = map.get("out_trade_no");
                Double totalAmount = Double.valueOf(map.get("total_amount"));

                //数据库写入
                String Userid = "1";
                String Courseid = "2";
                int Payment_channel_id = 1;

                int i = purchaseRecordService.Insert_zfb_PurchaseRecord_One(Userid, Courseid, totalAmount, Payment_channel_id, OUT);
                System.out.print("i ==>");
                System.out.println(i);

                //跳转回去
                return "购买成功  <a href=\"http://localhost:3334/video\">返回首页</a>";
                // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            } else {
                return "failure";
                // TODO 验签失败则记录异常日志，并在response中返回failure.
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failure";
    }


    public AlipayTradeQueryResponse query(String out_trade_no) {//调用交易查询接口查看支付状态（去支付宝查付没有付钱）

        System.out.println("query == begin==>");
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(PaymentConstants.URL,
                    PaymentConstants.APPID,
                    PaymentConstants.PRIVATE_KEY,
                    PaymentConstants.FORMAT,
                    PaymentConstants.CHARSET,
                    PaymentConstants.ALIPAY_PUBLIC_KEY,
                    PaymentConstants.SIGN_TYPE);
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            JSONObject bizCotent = new JSONObject();
            bizCotent.put("out_trade_no", out_trade_no);
            request.setBizContent(bizCotent.toString());
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.print("response.getBody() ==>");
                System.out.println(response.getBody());
                System.out.print("response.getTradeStatus() ==> ");
                System.out.println(response.getTradeStatus()); //获取到：trade_status（支付状态）
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("query == end==>");
        return null;

    }


    /*
     * 一笔订单可以退多次，查那一次退了多少（requestNo）
     * */
    public AlipayTradeFastpayRefundQueryResponse queryRefund(String tradeNo, String requestNo) {//调用交易查询接口查看支付状态（去支付宝查付没有付钱）

        System.out.println("queryRefund ==begin==>");
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(PaymentConstants.URL,
                    PaymentConstants.APPID,
                    PaymentConstants.PRIVATE_KEY,
                    PaymentConstants.FORMAT,
                    PaymentConstants.CHARSET,
                    PaymentConstants.ALIPAY_PUBLIC_KEY,
                    PaymentConstants.SIGN_TYPE);

            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
            JSONObject bizContent = new JSONObject();
            bizContent.put("trade_no", "2021081722001419121412730660");
            bizContent.put("refund_amount", 0.01);
            bizContent.put("out_request_no", "HZ01RF001");

//// 返回参数选项，按需传入
//JSONArray queryOptions = new JSONArray();
//queryOptions.add("refund_detail_item_list");
//bizContent.put("query_options", queryOptions);

            request.setBizContent(bizContent.toString());
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
/*
    @PostMapping("/tq2")
        //测试通
    void findByAllUser2(@RequestParam("out_trade_noinput") String out_trade_noinput,
                        @RequestParam("refund_amount") double refund_amount,
                        HttpServletResponse response) {//获取全部直接变json（改，增加分页）
        System.out.println("/tq2 ==>");
        System.out.println("out_trade_noinput =");
        System.out.println(out_trade_noinput);

        System.out.println("refund_amount =");
        System.out.println(refund_amount);
    }
*/


}
