package spittr;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spittr.config.JDBCConfiguration;
import spittr.data.repository.SpitterRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JDBCConfiguration.class)
public class SpitterRepositoryJDBCTest {

    @Autowired
    SpitterRepository spitterRepository;

    @Test
    public void test(){
        String foundName = spitterRepository.findFirstnameByUsername("Pirax");
        assertThat(foundName).isEqualTo("Daniel");
    }


}
