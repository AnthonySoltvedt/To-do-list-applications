import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColourTest {
    @Test
    public void testRedReturnText() {
        String result = Colour.red("test");
        assertEquals("\u001B[0;31mtest\u001B[0m", result);
    } // <-

    @Test
    public void testGreenReturnText() {
        String result = Colour.green("test");
        assertEquals("\u001B[0;32mtest\u001B[0m", result);
    } // <-

    @Test
    public void testYellowReturnText() {
        String result = Colour.yellow("test");
        assertEquals("\u001B[0;33mtest\u001B[0m", result);
    } // <-

    @Test
    public void testBlueReturnText() {
        String result = Colour.blue("test");
        assertEquals("\u001B[0;34mtest\u001B[0m", result);
    } // <-

    @Test
    public void testMagentaReturnText() {
        String result = Colour.magenta("test");
        assertEquals("\u001B[0;35mtest\u001B[0m", result);
    } // <-

    @Test
    public void testCyanReturnText() {
        String result = Colour.cyan("test");
        assertEquals("\u001B[0;36mtest\u001B[0m", result);
    } // <-











}
