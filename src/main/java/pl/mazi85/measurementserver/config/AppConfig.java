package pl.mazi85.measurementserver.config;

import org.apache.plc4x.java.PlcDriverManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PlcDriverManager plcDriverManager(){
        return new PlcDriverManager();
    }
}
