package spittr;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spittr.config.JPAConfiguration;
import spittr.data.repository.SpittleRepositoryJPA;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfiguration.class)
public class SpittleRepositoryTest {

    @Autowired
    SpittleRepositoryJPA spittleRepository;

    @Test
    public void findLatestSpittle() {
//        List<Spittle> latestSpittle = spittleRepository.findLatestSpittles(new PageRequest(0, 1));
//        assertThat(latestSpittle).isNotEmpty();
//        assertThat(latestSpittle.get(0)).isNotNull();

        assertThat(spittleRepository.findOne(3L)).isNotNull();
    }
}
