package com.hongye.base;

/**
 * @author hongye
 *
 */
public class BusinessException extends Exception{
    /**
     * Comment for &lt;code&gt;serialVersionUID&lt;/code&gt;
     */
    private static final long serialVersionUID = 3455708526465670030L;

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(String msg,String code){
        super(msg+":--:"+code);
    }
}