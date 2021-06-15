package com.example.demo.util;

import lombok.Data;
import java.io.*;
import java.util.Date;

/**
 * @description: Builder工具测试
 * @author: Mr.monster.liu
 * @create: 2021-06-15 10:02
 **/
@Data
public class RabbitRpcBuilderUtil implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String data;

    private Header header;

    public RabbitRpcBuilderUtil(String data,Header header){
        this.data = data;
        this.header = header;
    }

   public RabbitRpcBuilderUtil(Builder builder){
        this.data = builder.getData();
        this.header = builder.getHeader();
    }

    public RabbitRpcBuilderUtil build(){
        if(data == null){
            this.data = "";
        }
        if (header == null){
            this.header = new Header().build();
        }
        return this;
    }

    @Data
    public static class Builder implements Serializable{

        private static final Long serialVersionUID = 1L;

        private String data;

        private  Header header;

        Builder builder( String data, Header header){
            this.data = data;
            this.header = header;
            return this;
        }
        public Builder setData(String data){
            this.data  = data;
            return this;
        }

        public Builder setHeader(Header header){
            this.header  = header;
            return this;
        }

        public Builder build(){

            return this;
        }

    }

    @Data
    public static class Header implements Serializable{

        private static final Long serialVersionUID = 1L;

        private String ContentType;

        private Date date;

        public Header header(){
            return this;
        }

        public Header header(String ContentType,Date date){
            this.ContentType = ContentType;
            this.date = date;
            return this;
        }

        public Header setContentType(String contentType){
            this.ContentType = contentType;
            return  this;
        }


        public Header build(){
            if(this.ContentType == null){
                this.ContentType = "application/json";
            }
            if(this.date == null){
                this.date = new Date();
            }
            return  this;
        }

    }

    /**
    * 对象转字节数组
    */
    public static byte[] objectToBytes(Object obj) throws IOException {
        try(
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream sOut = new ObjectOutputStream(out);
        ){
            sOut.writeObject(obj);
            sOut.flush();
            byte[] bytes = out.toByteArray();
            return bytes;
        }
    }

    /**
     * 字节数组转对象
     */
    public static RabbitRpcBuilderUtil bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        try(
                ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                ObjectInputStream sIn = new ObjectInputStream(in);
        ){
            return (RabbitRpcBuilderUtil) sIn.readObject();
        }
    }


}