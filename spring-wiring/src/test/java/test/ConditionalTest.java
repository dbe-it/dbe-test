package test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import conditional.MagicBean;
import conditional.MagicConfig;
import utils.TestClassExecutor;


@SpringJUnitConfig(classes = MagicConfig.class)
public class ConditionalTest {

    @Test
    public void conditionNotMet() throws Exception{
        TestClassExecutor.execute(ConditionNotMet.class);
    }

    private class ConditionNotMet{
        @Autowired
        @Qualifier("magicBean")
        MagicBean bean;

        @Test
        public void test() {
            assertThat(bean).isNull();
        }
    }

    @Test
    public void conditionMet() throws Exception{
        TestClassExecutor.execute(ConditionMet.class);
    }

    private class ConditionMet{
        @Autowired
        @Qualifier("noMagicBean")
        MagicBean bean;

        @Test
        public void test() {
            assertThat(bean).isNotNull();
        }
    }
}
