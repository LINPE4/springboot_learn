package com.wisely.highlight_spring4.ch1.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //1 @Configuration声明当前类是一个配置类
@ComponentScan("com.wisely.highlight_spring4.ch1.di") //2 使用@ComponentScan自动
            //扫描包名下所有使用@Service@Component@Repository和@Controller的类，并注册为Bean
public class DiConfig {

}
