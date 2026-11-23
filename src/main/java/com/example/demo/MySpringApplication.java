package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.demo.difexamples.ClientComponent;
import com.example.demo.difexamples.SingletonComponent;
import com.example.demo.difexamples.TransientComponent;

@SpringBootApplication
public class MySpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MySpringApplication.class, args);

        TransientComponent transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();
        transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        SingletonComponent singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();
        singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        ClientComponent c = context.getBean(ClientComponent.class);
        c.operation();
    }
}