package com.wisely.highlight_spring4.ch1.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //1 使用@Configuration注解表明当前类是一个配置类，此处没有使用包扫描，是因为所有的Bean都在此定义
public class JavaConfig {
	@Bean //2 使用@Bean注解声明当前方法FunctionService的返回值是一个Bean,Bean的名称是方法名
	public FunctionService functionService(){
		return new FunctionService();
	}
	
	@Bean 
	public UseFunctionService useFunctionService(){
		UseFunctionService useFunctionService = new UseFunctionService();
		useFunctionService.setFunctionService(functionService()); //3 注入FunctionService的Bean时候直接调用functionService()
		return useFunctionService;
		
	}

//	@Bean 
//	public UseFunctionService useFunctionService(FunctionService functionService){//4
//		UseFunctionService useFunctionService = new UseFunctionService();
//		useFunctionService.setFunctionService(functionService);
//		return useFunctionService;
//	}
}
