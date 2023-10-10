package lk.ijse.spring.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JPAConfig.class})
@ComponentScan(basePackages = "lk.ijse.spring.services")
public class WebRootConfig {
    public WebRootConfig(){
        System.out.println("WebRootConfig Constructor");
    }

    @Bean
    public ModelMapper modelMapper(){
        System.out.println();
        return new ModelMapper();
    }
}
