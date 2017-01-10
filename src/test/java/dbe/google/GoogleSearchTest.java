package dbe.google;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import dbe.pageobject.google.WelcomePage;
import info.novatec.testit.webtester.browser.Browser;
import info.novatec.testit.webtester.browser.proxy.FirefoxFactory;
import info.novatec.testit.webtester.junit5.EnableWebTesterExtensions;
import info.novatec.testit.webtester.junit5.extensions.browsers.CreateBrowsersUsing;
import info.novatec.testit.webtester.junit5.extensions.browsers.Managed;
import info.novatec.testit.webtester.junit5.extensions.eventlisteners.Registered;

@EnableWebTesterExtensions
@CreateBrowsersUsing(FirefoxFactory.class)
@RunWith(JUnitPlatform.class)
public class GoogleSearchTest {

	@Managed("firefox")
//	@EntryPoint("https://www.google.de")
    private Browser browser;
	
    static private WelcomePage googleWelcomePage;
	
	@Registered(targets="firefox")
	public MyEventListener customListener = new MyEventListener();
	
    @BeforeEach
    public void initStartPage() {
    	browser.open("https://www.google.de");
    	googleWelcomePage = browser.create(WelcomePage.class);
    }

    @Test
    public void aGoogleSearchTest() {
    	
    	System.out.println("TESTSTART");
    	
        String searchValue = "the answer to life the universe and everything plus number of horns on a unicorn";
        googleWelcomePage.setSearchValue(searchValue);
        
//        Wait.until(resultPage.result()).is(visibleText("43"));
        System.out.println("PageTitle:"+browser.currentPageTitle());
    }
    
    @AfterEach
    public void cleanUp() {
    	System.out.println("AFTER");
    	googleWelcomePage.clickSearch();
    }
    
}
