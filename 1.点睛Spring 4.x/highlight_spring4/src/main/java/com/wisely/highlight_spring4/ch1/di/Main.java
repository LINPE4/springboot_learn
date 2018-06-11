package com.wisely.highlight_spring4.ch1.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		 AnnotationConfigApplicationContext context =
	                new AnnotationConfigApplicationContext(DiConfig.class); //1 使用AnnotationConfigApplicationContext作为spring容器
				//接受一个配置类作为参数
		 
		 UseFunctionService useFunctionService = context.getBean(UseFunctionService.class); //2 获得声明配置的UseFunctionService的Bean
		 
		 System.out.println(useFunctionService.SayHello("world"));
		 
		 context.close();
	}
}
