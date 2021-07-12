package com.example.demo;

import com.example.demo.domain.TeUsers;
import com.example.demo.modules.designPattern.builder.service.CalculationService;
import com.example.demo.modules.designPattern.decorate.ConcreteComponent;
import com.example.demo.modules.designPattern.decorate.service.ConcreteDecoratorA;
import com.example.demo.modules.designPattern.decorate.service.ConcreteDecoratorB;
import com.example.demo.modules.designPattern.strategy.BraisedCrabs;
import com.example.demo.modules.designPattern.strategy.CrabCooking;
import com.example.demo.modules.designPattern.strategy.Kitchen;
import com.example.demo.modules.designPattern.strategy.SteamedCrabs;
import com.example.demo.modules.entrust.service.impl.CalculateThePriceService;
import com.example.demo.modules.entrust.service.impl.IntermediateMemberEntrustServiceImpl;
import com.example.demo.modules.rabbitmq.service.SendRabbitRpcMsgService;
import com.example.demo.modules.redis.service.RedisBasicService;
import com.example.demo.modules.test.service.CurdJpaService;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private CurdJpaService curdJpaService;

    @Autowired
    private RedisBasicService redisBasicService;

    @Autowired
    private SendRabbitRpcMsgService sendRabbitRpcMsgService;

    @Autowired
    private CalculateThePriceService calculateThePriceService;

    @Autowired
    private CalculationService calculationService;


    @Test
    void contextLoads() {
        TeUsers index = curdJpaService.index();
        log.info(index.toString());
    }





    void TestStream(){
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Object[] users = new Object[10];
        //2.通过Arrays中的静态方法stream() 获取数组流
        Stream<Object> stream2 = Arrays.stream(users);

        //3.通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        //4.创建无限流
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream3.forEach(System.out::println);
        stream4.limit(10).forEach(x -> System.out.println(x));


    }


    @Test
    void  testRedis() throws InterruptedException {
        redisBasicService.RedisLock();
    }

    @Test
    void  rpc() throws IOException, InterruptedException {
        sendRabbitRpcMsgService.index();
    }

    @Test
    void  testStrategy(){
        //策略设计模式
        CrabCooking steamedCrabs = new SteamedCrabs();
        CrabCooking braisedCrabs = new BraisedCrabs();
        Kitchen kitchen = new Kitchen();
        kitchen.setStrategy(steamedCrabs);
        kitchen.CookingMethod();

        kitchen.setStrategy(braisedCrabs);
        kitchen.CookingMethod();
    }


    @Test
    void fileDir(){
        String path = "D:\\www\\uniapp\\fmall-delight\\src";
        //路径是否是目录
        getDirectory(path);
    }

    private void getDirectory(String path){
        File file = new File(path);
        //是否是目录
        boolean directory = file.isDirectory();
        if(directory){
            System.out.println("目录："+path);
            String[] list = file.list(new ImageFilter());
            assert list != null;
            for (String subPath: list ) {
                getDirectory(path+File.separator+subPath);
            }
        }else {
            System.out.println("文件："+path);
        }
    }

    public static class ImageFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            File file = new File(dir.getPath()+File.separator + name);
            // 指定允许的文件类型
            return file.getName().endsWith(".png") || file.getName().endsWith(".js") || file.isDirectory();
        }
    }



    @Test
    void InputStream(){
        /*byte[] b = new byte[] { 1, -1, 25, -22, -5, 23 }; // 创建数组
        ByteArrayInputStream bais = new ByteArrayInputStream(b, 0, 6); // 创建字节数组输入流
        int i = bais.read(); // 从输入流中读取下一个字节，并转换成int型数据
        while (i != -1) { // 如果不返回-1，则表示没有到输入流的末尾
            System.out.println("原值=" + (byte) i + "\t\t\t转换为int类型=" + i);
            i = bais.read(); // 读取下一个
        }*/
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[] { 1, -1, 25, -22, -5, 23 }; // 创建数组
        baos.write(b, 0, 6); // 将字节数组b中的前4个字节元素写到输出流中
        System.out.println("数组中一共包含：" + baos.size() + "字节"); // 输出缓冲区中的字节数
        byte[] newByteArray = baos.toByteArray(); // 将输出流中的当前内容转换成字节数组
        System.out.println(Arrays.toString(newByteArray)); // 输出数组中的内容
    }

    @Test
    void readFileContent() throws IOException {
        String path = "D:\\www\\uniapp\\fmall-delight\\src\\App.vue";
        RandomAccessFile randomAccessFile = new RandomAccessFile(path,"r");

        Long fileLenth = randomAccessFile.length();

        randomAccessFile.seek(0);

        byte[] bytes = new byte[10];
        int byteread = 0;
        while ((byteread  = randomAccessFile.read(bytes)) != -1){
            System.out.write(bytes,0,byteread);
        }
    }


    @Test
    void builderCart(){

    }

    @Test
    void bigDecimalToInt(){
        // bin 计算
        BigDecimal bigDecimal = new BigDecimal("2.5");
        System.out.println(bigDecimal);
        BigDecimal bigDecimal1 = new BigDecimal("0.1");
        BigDecimal res = bigDecimal.multiply(bigDecimal1);
        System.out.println(res);


        // string 转double
        String  number = "20.0000000";
        double ratio =  Double.parseDouble(number);
        System.out.println(ratio);


    }

    @Test
    public void entrustTest(){
        IntermediateMemberEntrustServiceImpl intermediateMemberEntrustService = new IntermediateMemberEntrustServiceImpl();
        calculateThePriceService.CalculateThePriceService(intermediateMemberEntrustService);
        CalculateThePriceService calculateThePriceService = this.calculateThePriceService.calculateThePrice(20.00);
        log.info(calculateThePriceService.toString());

    }

    @Test
    public void  test(){
        Map<String, String> params = Maps.newHashMap();
        params.put("cust_id", String.valueOf(123456));
        params.put("apikey", "apikey");
        params.put("msg_id", String.valueOf(3434355));
        params.put("order_id", String.valueOf(3434355));
        params.put("security_code", StringUtils.trimToEmpty(String.valueOf(345454544)));
        params.put("code", StringUtils.trimToEmpty(String.valueOf(345454544)));
        params.put("qrCodeUrl", "http://www.zowoyoo.com/.....");
        params.put("qrType", "0");
        params.put("printState", "0");
        params.put("codeUrls", "[{codeUrl:\"\", codeType: 1},{codeUrl:\"\", codeType: 1}]");
        log.info(params.toString());
    }


    @Test
    public void arrayTest(){
        List<Cart> cartList = new ArrayList<>();
        log.info("数组的初始数量："+cartList.size());
        for(int i = 0 ;i <=5 ;i++){
            Cart cart = new Cart();
            cart.setName("name"+i);
            cart.setTime(i);
            cartList.add(cart);
        }
        log.info("填充数组的数量："+cartList.size());
        log.info("获取第一个数据："+cartList.get(0));
        cartList.remove(0);

        log.info("删除数组的数量："+cartList.size());
        log.info("获取第一个数据："+cartList.get(0));

    }


    @Test
    public void builderTTest(){
        calculationService.index();
    }

    @Test
    public void testDecorate(){
        //装饰器设计模式  再不改变现有业务下 进行扩展，减少继承子类的增加
        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB(new ConcreteDecoratorA(new ConcreteComponent()));

        concreteDecoratorB.eat();
    }



    @Data
    public static   class Cart{

        //名称
        private  String name;

        //生产时间
        private Integer time;


    }



}
