package com.example.demo;

import com.example.demo.domain.TeUsers;
import com.example.demo.modules.designPattern.sevice.builder.BuilderCart;
import com.example.demo.modules.designPattern.sevice.builder.Cart;
import com.example.demo.modules.designPattern.sevice.builder.Manufacturer;
import com.example.demo.modules.designPattern.sevice.strategymodel.BraisedCrabs;
import com.example.demo.modules.designPattern.sevice.strategymodel.CrabCooking;
import com.example.demo.modules.designPattern.sevice.strategymodel.Kitchen;
import com.example.demo.modules.designPattern.sevice.strategymodel.SteamedCrabs;
import com.example.demo.modules.rabbitmq.service.SendRabbitRpcMsgService;
import com.example.demo.modules.redis.service.RedisBasicService;
import com.example.demo.modules.test.service.CurdJpaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
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
    void  testBuilder(){
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
        BuilderCart.Builder builder = new BuilderCart.Builder().engine("engine").tyre("tyre").fuselage("fuselage").build();
        BuilderCart.Builder builderCart = new BuilderCart(builder).name("宝马").build().getBuilder();

        System.out.println(builderCart.toString());
    }




}
