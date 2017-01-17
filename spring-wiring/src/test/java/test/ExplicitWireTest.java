package test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import utils.TestClassExecutor;
import wiring.auto.CompactDisc;
import wiring.explicitJava.Player;
import wiring.explicitJava.PlayerConfig;
import wiring.explicitJava.Recorder;
import wiring.explicitXml.Monitor;
import wiring.explicitXml.MonitorConfig;
import wiring.explicitXml.TV;


@SpringJUnitConfig(classes = PlayerConfig.class)
public class ExplicitWireTest {

    @Test
    public void wireJavaSuccess() throws Exception {
        TestClassExecutor.execute(JavaSuccess.class);
    }

    @SpringJUnitConfig(classes = PlayerConfig.class)
    private static class JavaSuccess {
        @Autowired
        private Player player;

        @Test
        public void checkConditionalWiring() {
            assertThat(player).isNotNull();
        }
    }

    @Test
    public void wireJavaFail() throws Exception {
        assertThrows(UnsatisfiedDependencyException.class, () -> {
            TestClassExecutor.execute(JavaFail.class);
        });
    }

    @SpringJUnitConfig(classes = PlayerConfig.class)
    private static class JavaFail {
        @Autowired
        private Recorder recorder;

        @Test
        public void checkConditionalWiring() {
            assertThat(recorder).isNull();
        }
    }

    @Test
    public void wireXmlSuccess() throws Exception {
        TestClassExecutor.execute(XmlSuccess.class);
    }

    @SpringJUnitConfig(classes = MonitorConfig.class)
    private static class XmlSuccess {

        @Autowired
        private Monitor monitor;

        @Test
        public void checkConditionalWiring() {
            assertThat(monitor).isNotNull();
            assertThat(monitor.getConnectedPlayer()).isNotNull();
            CompactDisc cd = monitor.getConnectedPlayer().getCompactDisc();
            assertThat(cd.play()).isEqualTo("Playing 'Nimm mich mit' by Stilbruch.tv");
        }
    }

    @Test
    public void wireXmlFail() throws Exception {
        assertThrows(UnsatisfiedDependencyException.class, () -> {
            TestClassExecutor.execute(XmlFail.class);
        });
    }

    @SpringJUnitConfig(classes = MonitorConfig.class)
    private static class XmlFail {

        @Autowired
        private TV tv;

        @Test
        public void checkConditionalWiring() {
            assertThat(tv).isNull();
        }
    }

}
