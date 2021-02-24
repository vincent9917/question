package com.jkwl.question.config;

import com.jkwl.question.config.assembler.Assembler;
import com.jkwl.question.config.assembler.AssemblerFactory;
import com.jkwl.question.config.assembler.AssemblerFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssemblerConfig {

    public AssemblerConfig() {
    }

    @Bean
    public AssemblerFactory assemblerFactory(Assembler<?,?>... assemblers) {
        AssemblerFactory assemblerFactory = new AssemblerFactoryImpl();
        for (Assembler<?,?> assembler : assemblers)
            assemblerFactory.register(assembler);
        return assemblerFactory;
    }
}
