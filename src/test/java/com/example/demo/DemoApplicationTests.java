package com.example.demo;

import com.example.demo.domain.TeUsers;
import com.example.demo.modules.async.service.AsyncTaskService;
import com.example.demo.modules.design.builder.service.CalculationService;
import com.example.demo.modules.design.decorate.ConcreteComponent;
import com.example.demo.modules.design.decorate.service.ConcreteDecoratorA;
import com.example.demo.modules.design.decorate.service.ConcreteDecoratorB;
import com.example.demo.modules.design.strategy.BraisedCrabs;
import com.example.demo.modules.design.strategy.CrabCooking;
import com.example.demo.modules.design.strategy.Kitchen;
import com.example.demo.modules.design.strategy.SteamedCrabs;
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

    @Autowired
    private AsyncTaskService asyncTaskService;


    @Test
    void contextLoads() {
        TeUsers index = curdJpaService.index();
        log.info(index.toString());
    }





    void TestStream(){
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Object[] users = new Object[10];
        //2.??????Arrays??????????????????stream() ???????????????
        Stream<Object> stream2 = Arrays.stream(users);

        //3.??????Stream?????????????????????of()
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        //4.???????????????
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
        //??????????????????
        CrabCooking steamedCrabs = new SteamedCrabs();
        CrabCooking braisedCrabs = new BraisedCrabs();
        Kitchen kitchen = new Kitchen();
        kitchen.setStrategy(steamedCrabs);
        kitchen.cookingMethod();

        kitchen.setStrategy(braisedCrabs);
        kitchen.cookingMethod();
    }


    @Test
    void fileDir(){
        String path = "D:\\www\\uniapp\\fmall-delight\\src";
        //?????????????????????
        getDirectory(path);
    }

    private void getDirectory(String path){
        File file = new File(path);
        //???????????????
        boolean directory = file.isDirectory();
        if(directory){
            System.out.println("?????????"+path);
            String[] list = file.list(new ImageFilter());
            assert list != null;
            for (String subPath: list ) {
                getDirectory(path+File.separator+subPath);
            }
        }else {
            System.out.println("?????????"+path);
        }
    }

    public static class ImageFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            File file = new File(dir.getPath()+File.separator + name);
            // ???????????????????????????
            return file.getName().endsWith(".png") || file.getName().endsWith(".js") || file.isDirectory();
        }
    }



    @Test
    void InputStream(){
        /*byte[] b = new byte[] { 1, -1, 25, -22, -5, 23 }; // ????????????
        ByteArrayInputStream bais = new ByteArrayInputStream(b, 0, 6); // ???????????????????????????
        int i = bais.read(); // ???????????????????????????????????????????????????int?????????
        while (i != -1) { // ???????????????-1???????????????????????????????????????
            System.out.println("??????=" + (byte) i + "\t\t\t?????????int??????=" + i);
            i = bais.read(); // ???????????????
        }*/
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[] { 1, -1, 25, -22, -5, 23 }; // ????????????
        baos.write(b, 0, 6); // ???????????????b?????????4?????????????????????????????????
        System.out.println("????????????????????????" + baos.size() + "??????"); // ??????????????????????????????
        byte[] newByteArray = baos.toByteArray(); // ???????????????????????????????????????????????????
        System.out.println(Arrays.toString(newByteArray)); // ????????????????????????
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
    public void threadTest() {
        for (int i = 0; i < 20; i++) {
            asyncTaskService.executeAsyncTask(i);
        }
    }

    @Test
    void bigDecimalToInt(){
        // bin ??????
        BigDecimal bigDecimal = new BigDecimal("2.5");
        System.out.println(bigDecimal);
        BigDecimal bigDecimal1 = new BigDecimal("0.1");
        BigDecimal res = bigDecimal.multiply(bigDecimal1);
        System.out.println(res);


        // string ???double
        String  number = "20.0000000";
        double ratio =  Double.parseDouble(number);
        System.out.println(ratio);


    }

    @Test
    public void entrustTest(){
        IntermediateMemberEntrustServiceImpl intermediateMemberEntrustService = new IntermediateMemberEntrustServiceImpl();
        calculateThePriceService.calculateThePriceService(intermediateMemberEntrustService);
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
        log.info("????????????????????????"+cartList.size());
        for(int i = 0 ;i <=5 ;i++){
            Cart cart = new Cart();
            cart.setName("name"+i);
            cart.setTime(i);
            cartList.add(cart);
        }
        log.info("????????????????????????"+cartList.size());
        log.info("????????????????????????"+cartList.get(0));
        cartList.remove(0);

        log.info("????????????????????????"+cartList.size());
        log.info("????????????????????????"+cartList.get(0));

    }


    @Test
    public void builderTTest(){
        calculationService.index(1);
    }

    @Test
    public void testDecorate(){
        //?????????????????????  ??????????????????????????? ??????????????????????????????????????????
        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB(new ConcreteDecoratorA(new ConcreteComponent()));

        concreteDecoratorB.eat();
    }



    @Data
    public static   class Cart{

        //??????
        private  String name;

        //????????????
        private Integer time;


    }



}
