package com.jiuyuvip.enums;

/**
 * 收支来源
 * Created by Administrator on 2017/5/27.
 */
public enum TradeTypeEnum implements CodeBaseEnum {
    Recharge(0,"充值金币"),
    RechargeReward(1,"充值送金币"),
    TaskReward(2,"竞猜收入"),
    TaskCost(3,"竞猜下注"),
    ItemExchange(4,"商品兑换"),
    TaskLotteryIncome(5,"发布竞猜盈利"),
    TaskLotteryExpend(6,"发布竞猜赔付"),
    RegisterReward(7,"新用户注册奖励"),
    LoginReward(8,"登录奖励");

    private final int code;
    private final String desc;

    TradeTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
