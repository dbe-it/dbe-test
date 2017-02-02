package google;//package dbe.webtester2.google;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pageobjects.google.Result;
import pageobjects.google.WelcomePage;

import info.novatec.testit.webtester.browser.Browser;
import info.novatec.testit.webtester.browser.proxy.FirefoxFactory;
import info.novatec.testit.webtester.junit5.EnableWebTesterExtensions;
import info.novatec.testit.webtester.junit5.extensions.browsers.CreateBrowsersUsing;
import info.novatec.testit.webtester.junit5.extensions.browsers.EntryPoint;
import info.novatec.testit.webtester.junit5.extensions.browsers.Managed;
import info.novatec.testit.webtester.junit5.extensions.browsers.Registered;


@EnableWebTesterExtensions
@CreateBrowsersUsing(FirefoxFactory.class)
public class GoogleSearchTest {

	@Managed("firefox")
	@EntryPoint("https://www.google.de")
    private Browser browser;

    static private WelcomePage googleWelcomePage;

	@Registered(targets="firefox")
	public MyEventListener customListener = new MyEventListener();

    @BeforeEach
    public void initStartPage() {
    	googleWelcomePage = browser.create(WelcomePage.class);
    }

    @Test
    public void aGoogleSearchTest() {
        String searchValue = "the answer to life the universe and everything plus number of horns on a unicorn";
        Result resultPage = googleWelcomePage.search(searchValue);
        assertThat(resultPage.result().getVisibleText()).isEqualTo("43");
    }

}
