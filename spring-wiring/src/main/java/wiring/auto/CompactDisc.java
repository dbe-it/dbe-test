package wiring.auto;

public interface CompactDisc {
    String artist = null;
    String title = null;

    default String play() {
        throw new RuntimeException("No disc injected");
    }

}
