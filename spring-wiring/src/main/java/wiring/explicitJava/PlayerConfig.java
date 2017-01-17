package wiring.explicitJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import wiring.auto.CompactDisc;
import wiring.auto.CompactDiscConfig;


@Configuration
@Import(CompactDiscConfig.class)
public class PlayerConfig {

    @Autowired
    CompactDisc cd;

    @Bean
    public Player player(){
        return new CDPlayer(cd);
    }

}
