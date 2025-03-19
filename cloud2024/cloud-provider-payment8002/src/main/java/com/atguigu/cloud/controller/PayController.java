package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "订单CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @Operation(summary = "新增", description = "新增支付流水, 参数是JSON字符串")
    @PostMapping(value = "/pay/add")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
       int i=payService.add(pay);
        return ResultData.success("成功插入记录，返回值："+i);
    }

    @Operation(summary = "删除", description = "删除支付流水, 参数是Id")
    @DeleteMapping(value = "/pay/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){

       // System.out.println();
        int i = payService.delete(id);

        return ResultData.success(i);
    }
    @Operation(summary = "更新", description = "更新支付流水, 参数是JSON字符串, 根据Id更新")
    @PutMapping(value = "/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        int i=payService.update(pay);
        return ResultData.success("成功修改记录，返回值："+i);

    }
    @Operation(summary = "查询单个", description = "查询支付流水, 参数是Id")
    @GetMapping(value = "/pay/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){

        Pay pay=payService.getById(id);
        return ResultData.success(pay);
    }

    /*@GetMapping(value = "/pay/getAll")
    public List<Pay> getAllPay(@RequestParam List<Pay> pays){
        List<Pay> pays = payService.getAll();
        List<PayDTO> PayDTOs = BeanCopyUtil.copyListProperties(pays, PayDTO::new);
        return Result.success(PayDTOs);
    }*/

    //写一个error
    @GetMapping(value = "/pay/error")
    public ResultData<Integer> getPayErroe(){
        Integer integer = Integer.valueOf(200);
        try {
            System.out.println("come in payerror");
            int age=10/0;
        }catch (Exception e){
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return ResultData.success(integer);
    }
    @Value("${server.port}")
    private String port;
    @GetMapping(value = "/pay/get/info")
    public String getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo){
        return "atguiguInfo:"+atguiguInfo+"\t"+"port:"+port;

    }
}
