package com.yinshan.happycash.network.common.base;


/**
 * 自定义错误code类型:注解写法 可自由扩展
 *
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *  @author  admin
 *  on 2018/3/7
 *
 */
public class CodeException {

    /*未知错误*/
    public static final int E_201_ERROR = 201;
    /*未知错误*/
    public static final int UNKNOWN_ERROR = 204;
    /*运行时异常-包含自定义异常*/
    public static final int RUNTIME_ERROR = 205;


    /*未知错误*/
    public static final int E_EOF_ERROR = 1001;
}
