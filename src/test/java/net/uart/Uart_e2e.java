package net.uart;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.WebDriverRunner;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * e2e tests
 */
public class Uart_e2e extends TestCase {
    private static final String PINCHUK_TEXT = "PinchukArtCentre розташований в старовинному архітектурному ансамблі Бессарабського кварталу Києва це міжнародний центр сучасного мистецтва XXI сторіччя, відкрита платформа для митців, мистецтва та суспільства";
    private static final String LESYA_TEXT = "Український театр у Києві, що займає приміщення колишнього театру Бергоньє";
    private static final String OPERA_TEXT = "Самобутні інтерпретації класичної музичної спадщини і сучасних творів, виняткової злагодженості оркестр і хор, талановиті солісти, яскраві й самобутні виконавські традиції дозволили театру досягти мистецьких вершин і посісти важливе місце в сьогоденній музичній культурі Європи.";
    private static final String ARSENAL_TEXT = "Національний культурно-мистецький та музейний комплекс «Мистецький арсенал», відкритий у Києві у 2007. Один з найбільших у Європі";

    public Uart_e2e(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( Uart_e2e.class );
    }

    public void testMap() {
        open("http://localhost:8888");
        $x("//flt-glass-pane").should(visible).click(ClickOptions.withOffset(50, 0));
        assertEquals("http://localhost:8888/#/map", WebDriverRunner.getWebDriver().getCurrentUrl());
        $x("//area[@title='ПінчукАртЦентр']").should(visible).click();
        String text = $x("//div[@class='infowindow-snippet']").should(visible).getText();
        assertEquals(PINCHUK_TEXT, text);
        $x("//button[@title='Close']").should(enabled).click();

        $x("//area[@title='Національний академічний драматичний театр імені Лесі Українки']").should(visible).click();
        text = $x("//div[@class='infowindow-snippet']").should(visible).getText();
        assertEquals(LESYA_TEXT, text);
        $x("//button[@title='Close']").should(enabled).click();

        $x("//area[@title='Національна Опера']").should(visible).click();
        text = $x("//div[@class='infowindow-snippet']").should(visible).getText();
        assertEquals(OPERA_TEXT, text);
        $x("//button[@title='Close']").should(enabled).click();

        $x("//area[@title='Мистецький арсенал']").should(visible).click();
        text = $x("//div[@class='infowindow-snippet']").should(visible).getText();
        assertEquals(ARSENAL_TEXT, text);
        $x("//button[@title='Close']").should(enabled).click();
    }

    public void testMarketplace() throws IOException {
        open("http://localhost:8888");
        $x("//flt-glass-pane").should(visible).click(ClickOptions.withOffset(-50, 0));
        assertEquals("http://localhost:8888/#/marketplace", WebDriverRunner.getWebDriver().getCurrentUrl());
        sleep(3000);
        BufferedImage screenshot = $x("//flt-glass-pane").screenshotAsImage();
        BufferedImage shrek = ImageIO.read(new File("shrek.png"));
        boolean found = false;
        for (int i = 0; i < Objects.requireNonNull(screenshot).getWidth() - shrek.getWidth(); i++) {
            for (int j = 0; j < screenshot.getHeight() - shrek.getHeight(); j++) {
                boolean suits = true;
                for (int ii = 0; ii < shrek.getWidth(); ii++) {
                    for (int jj = 0; jj < shrek.getHeight(); jj++) {
                        if (screenshot.getRGB(i+ii, j+jj) != shrek.getRGB(ii, jj)) {
                            suits = false;
                            break;
                        }
                    }
                    if (!suits) {
                        found = false;
                        break;
                    } else {
                        found = true;
                    }
                }
                if (found) break;
            }
            if (found) break;
        }
        assertTrue(found);
    }
}
//https://media.2x2tv.ru/content/images/2022/05/ssssss.jpg