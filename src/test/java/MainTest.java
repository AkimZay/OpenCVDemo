import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.mycompany.app.Main.main;

public class MainTest {


    @Before
    public void setUp() {
        System.setProperty("config.properties", "src/test/resources/config.properties");
    }

    @After
    public void tearDown() {
        System.clearProperty("config.properties");
    }


    @Test
    public void testMainTaskOne() throws Exception {

        String [] args = { "1" };
        main(args);
    }

//    @Test
//    public void testMainTaskTwo() throws Exception {
//        String [] args = { "2" };
//        main(args);
//    }
//
//    @Test
//    public void testMainTaskThree() throws Exception {
//
//        String [] args = { "3" };
//        main(args);
//    }
//
//    @Test
//    public void testMainTaskFour() throws Exception {
//
//        String [] args = { "4" };
//        main(args);
//    }

}
