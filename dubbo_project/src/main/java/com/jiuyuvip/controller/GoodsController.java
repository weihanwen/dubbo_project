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


    //findGoodsById.do---判断库存---id含有效果redis缓存
    @RequestMapping("/findGoodsById")
    @ResponseBody
    public Goods findGoodsById(Integer goods_id ){
            Goods goods=null;
         try {
             goods= ServiceHelper.getGoodsService().selectGoodsById(goods_id);
          } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return goods;
     }


    //updateStockOneNumber.do---判断库存---id含有效果
    @RequestMapping("/updateStockOneNumber")
    @ResponseBody
    public synchronized Integer updateStockOneNumber(Integer goods_id,Integer shopnumber){
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

    //updateStockTwoNumber.do---判断库存注解实现效果
    @RequestMapping("/updateStockTwoNumber")
    @ResponseBody
    public synchronized Integer updateStockTwoNumber(Integer goods_id,Integer shopnumber){
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


    //one.do
    @RequestMapping(value = "/one", method = RequestMethod.POST)
    @ResponseBody
    public String one(@RequestBody String req) {

        return "";
    }

    //two.do
    @RequestMapping(value = "/two")
    @ResponseBody
    public String two(@RequestBody String req) {

        return "";
    }

    //three.do
    @RequestMapping(value = "/three")
    @ResponseBody
    public String three( String req) {

        return "";
    }


    public static void main(String args[]) {

        System.out.println("jieshu");
    }


}