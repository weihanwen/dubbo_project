package com.jiuyuvip.controller;

import com.jiuyuvip.entity.Goods;

import com.jiuyuvip.service.GoodsService;
import com.jiuyuvip.util.ServiceHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *  GoodsController
 *
 */
@Controller
public class GoodsController  {
    @Resource(name="goodsService")
    private GoodsService goodsService;

    //updateStock.do---判断库存---id含有效果
    @RequestMapping("/updateStock")
    @ResponseBody
    public synchronized Integer updateStock(Integer goods_id,Integer shopnumber){
        Integer result=0;
        try {
            Goods goods= ServiceHelper.getGoodsService().selectGoodsById(goods_id);
            if(goods != null && ((int)goods.getStock_number())-shopnumber >= 0){
                goods.setStock_number(((int)goods.getStock_number())-shopnumber);
                goods.setVersion(goods.getVersion()+1);
                result=(int)ServiceHelper.getGoodsService().versionGoodsStock(goods);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    //testone.do
    @RequestMapping(value = "/testone", method = RequestMethod.POST)
    @ResponseBody
    public String testone(@RequestBody String req) {

        return "";
    }

    //testtwo.do
    @RequestMapping(value = "/testtwo")
    @ResponseBody
    public String testtwo(@RequestBody String req) {

        return "";
    }

    //testthree.do
    @RequestMapping(value = "/testthree")
    @ResponseBody
    public String testthree( String req) {

        return "";
    }

    //updateStock2.do---判断库存注解实现效果
    @RequestMapping("/updateStock2")
    @ResponseBody
    public synchronized Integer updateStock2(Integer goods_id,Integer shopnumber){
        Integer result=0;
        try {
            Goods goods= ServiceHelper.getGoodsService().selectGoodsById(goods_id);
            if(goods != null && ((int)goods.getStock_number())-shopnumber >= 0){
                goods.setStock_number(((int)goods.getStock_number())-shopnumber);
                goods.setVersion(goods.getVersion()+1);
                result=(int)ServiceHelper.getGoodsService().updateUserNoVersionLocker(goods);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }




    public static void main(String args[]) {

        System.out.println("jieshu");
    }


}