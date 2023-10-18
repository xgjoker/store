package com.sy.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
@SpringBootApplication
@MapperScan("com.sy.store.mapper")
//https://gitee.com/Jodie0419/computer-store/blob/master/src/main/java/com/jodie/store/controller/AddressController.java
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	public MultipartConfigElement getMultipartConfigElement(){
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(10));
		factory.setMaxRequestSize(DataSize.ofMegabytes(15));
		return factory.createMultipartConfig();
	}
}

