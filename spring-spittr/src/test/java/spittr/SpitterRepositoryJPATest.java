package spittr;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spittr.config.JPAConfiguration;
import spittr.data.Spitter;
import spittr.data.repository.SpitterRepositoryJPA;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfiguration.class)
public class SpitterRepositoryJPATest {

    @Autowired
    SpitterRepositoryJPA spitterRepository;

    @Test
    public void findByName(){
        Spitter foundSpitter = spitterRepository.findByUsername("Pirax");
        assertThat(foundSpitter.getFirstName()).isEqualTo("Daniel");
    }

    @Test
    public void findById(){
        Spitter foundSpitter = spitterRepository.findOne(1l);
        assertThat(foundSpitter.getFirstName()).isEqualTo("Daniel");
    }

    @Test
    public void findFirstName(){
        String foundName = spitterRepository.findFirstnameByUsername("Pirax");
        assertThat(foundName).isEqualTo("Daniel");
    }

}
