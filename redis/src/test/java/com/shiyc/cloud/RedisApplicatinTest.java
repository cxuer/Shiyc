package com.shiyc.cloud;

import com.shiyc.cloud.bean.RedisObjectBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicatinTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void RedisTemplateTest(){

        RedisObjectBean bean=new RedisObjectBean();
        bean.setId(11);
        bean.setName("objectBean");
        // 保存对象,如果没有序列化对象，直接保存会报如下错误，序列化(implements Serializable)后保存正常
        // java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable payload but received an object of type [com.shiyc.cloud.bean.RedisObjectBean]
        // Serializable序列化后保存到redis根本看不懂，不是想要的效果
        // 原因是默认的序列化器是：this.defaultSerializer = new JdkSerializationRedisSerializer
        redisTemplate.opsForValue().set("bean-1",bean);
//        Object o = redisTemplate.opsForValue().get(bean);
//        System.out.println(o);


    }


    @Test
    public void StringRedisTemplateTest(){
        // 保存字符串 K-V
        //stringRedisTemplate.opsForValue().append("testVal","bbbbbb");

        String testVal = stringRedisTemplate.opsForValue().get("testVal");

        System.out.format(" ===> %s%n",testVal);

        // 保存list
        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","3");
        Long mylist = stringRedisTemplate.opsForList().leftPush("mylist", "5");
        System.out.println(mylist);

    }

    @Test
    //格式化输出，%s表示字符串，%d表示数字，%n表示换号
    public void SentenceFormat() {
        String name = "盖伦";
        int kill = 8;
        String title = "超神";

        String sentence = name + " 在进行了连续  " + kill + " 次击杀后，获得了 " + title + " 的称号 ";
        //直接使用+进行字符串连接，编码感觉会比较繁琐，并且维护性差，易读性差
        System.out.println(sentence);

        String sentenceFormat = "%s 在进行了连续 %d 次击杀后， 获得了 %s 的称号%n";
        //格式化输出，%s表示字符串，%d表示数字，%n表示换号
        System.out.printf(sentenceFormat, name, kill, title);
        System.out.format(sentenceFormat, name, kill, title);
        //format和printf能够达到一模一样的效果

        int year = 2020;

        System.out.format("%d%n",year);		//用%n或\n没差
        //直接打印数字
        System.out.printf("%8d%n",year);	//用printf还是format没差
        //总长度为8，默认右对齐
        System.out.printf("%-8d%n",year);
        //总长度为8，默认左对齐
        System.out.printf("%08d%n",year);
        //总长度为8，不够补0
        System.out.printf("%,8d%n",year*10000);
        //千位分隔符
        System.out.format("%.2f%n",Math.PI);
        //小数点位数

    }

}
