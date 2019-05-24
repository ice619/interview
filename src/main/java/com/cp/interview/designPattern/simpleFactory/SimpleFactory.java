package com.cp.interview.designPattern.simpleFactory;

/**
 * Description:
 *
 * @author chenpeng
 * @date 2019/5/24 16:12
 */
public class SimpleFactory {
    public static Product getProduct(String arg){
        Product product = null;
        //productA 参数可以读取配置文件
        if(arg.equalsIgnoreCase("productA")){
            product = new ProdectA();
            System.out.println("初始化 productA");
        }else if(arg.equalsIgnoreCase("productB")){
            product = new ProdectB();
            System.out.println("初始化 productB");
        }
        return  product;
    }
}
