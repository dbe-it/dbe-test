package test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import utils.TestClassExecutor;
import wiring.auto.CompactDiscConfig;
import wiring.auto.DVD;
import wiring.auto.Stilbruch;


public class AutoWireTest {

    @Test
    public void wireSuccess() throws Exception {
        TestClassExecutor.execute(Success.class);
    }

    @SpringJUnitConfig(classes = CompactDiscConfig.class)
    private static class Success {
        @Autowired
        private Stilbruch cd;

        @Test
        public void checkConditionalWiring() {
            assertThat(cd).isNotNull();
            assertThat(cd.play()).isEqualTo("Playing 'Nimm mich mit' by Stilbruch.tv");
        }
    }

    @Test
    public void wireFail() throws Exception {
        try {
            assertThrows(UnsatisfiedDependencyException.class, () -> {
                TestClassExecutor.execute(Fail.class);
            });
        } catch (Exception e) {
            System.out.println("TEST");
        }

    }

    @SpringJUnitConfig(classes = CompactDiscConfig.class)
    private static class Fail {
        @Autowired
        private DVD dvd;

        @Test
        public void checkConditionalWiring() {
            assertThat(dvd).isNull();
        }
    }
}
