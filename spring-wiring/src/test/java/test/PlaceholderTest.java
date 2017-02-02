package test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import placeholder.CompactDisc;
import placeholder.CompactDiscConfig;
import utils.TestClassExecutor;


public class PlaceholderTest {

    @Disabled //test buggy
    @Test
    public void wireSuccess() throws Exception {
        TestClassExecutor.execute(PlaceholderFound.class);
    }

    @SpringJUnitConfig(classes = CompactDiscConfig.class)
    private static class PlaceholderFound {
        @Autowired
        @Qualifier("stilbruch")
        private CompactDisc cd;

        @Test
        public void checkConditionalWiring() {
            assertThat(cd).isNotNull();
            assertThat(cd.play()).isEqualTo("Playing 'Nimm mich mit' by Stilbruch.tv");
        }
    }

    @Disabled //test buggy
    @Test
    public void wireFail() throws Exception {
        assertThrows(IllegalStateException.class, () -> {
            TestClassExecutor.execute(PlaceholderNotFound.class);
        });

    }

    @SpringJUnitConfig(classes = CompactDiscConfig.class)
    private static class PlaceholderNotFound {
        @Autowired
        @Qualifier("tomorrowland")
        private CompactDisc cd;

        @Test
        public void checkConditionalWiring() {
            assertThat(cd).isNotNull();
            assertThat(cd.play()).isEqualTo("Playing 'null' by null");
        }
    }
}
