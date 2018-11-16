package cn.nj.springbootone.AA;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 对于Java 8 Lambda表达式的使用
 * */
public class Test {

    public static void main(String[] args) {

        List<Object> list = new ArrayList<>();
        list.add("测试数据1");
        list.add("测试数据2");
        list.add("测试数据3");
        list.add("测试数据12");

       /* //传统的遍历
        for (Object ob:list) {
            System.out.println(ob);
        }*/

        //使用Lambda表达式的遍历
     list.forEach(i->{
         System.out.println(i);
     });

       /* //结合Predicate使用和过滤条件筛选元素
        Predicate<String> contain1 = n -> n.contains("1");
        Predicate<String> contain2 = n -> n.contains("2");*/
    }




    
}
