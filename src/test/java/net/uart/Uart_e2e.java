package net.uart;

import com.codeborne.selenide.WebDriverRunner;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Unit test for simple App.
 */
public class Uart_e2e extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Uart_e2e(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( Uart_e2e.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue( true );
    }

    public void testMarketplace() {
        open("https://localhost:8888");
        $x("flt-scene-host").should(visible).click();
        assertEquals("http://localhost:8888/#/marketplace", WebDriverRunner.getWebDriver().getCurrentUrl());
    }
}
