package dbe.google;

import static info.novatec.testit.webtester.conditions.Conditions.visibleText;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import dbe.pageobject.google.Result;
import dbe.pageobject.google.WelcomePage;
import info.novatec.testit.webtester.browser.Browser;
import info.novatec.testit.webtester.browser.proxy.FirefoxFactory;
import info.novatec.testit.webtester.events.Event;
import info.novatec.testit.webtester.events.EventListener;
import info.novatec.testit.webtester.junit5.EnableWebTesterExtensions;
import info.novatec.testit.webtester.junit5.extensions.browsers.CreateBrowsersUsing;
import info.novatec.testit.webtester.junit5.extensions.browsers.EntryPoint;
import info.novatec.testit.webtester.junit5.extensions.browsers.Managed;
import info.novatec.testit.webtester.junit5.extensions.browsers.Registered;
import info.novatec.testit.webtester.waiting.Wait;

//import static info.novatec.testit.webtester.conditions.Conditions.visibleText;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import dbe.pageobject.google.Result;
//import dbe.pageobject.google.WelcomePage;
//import info.novatec.testit.webtester.browser.Browser;
//import info.novatec.testit.webtester.browser.proxy.FirefoxFactory;
//import info.novatec.testit.webtester.junit5.EnableWebTesterExtensions;
//import info.novatec.testit.webtester.junit5.extensions.browsers.CreateBrowsersUsing;
//import info.novatec.testit.webtester.junit5.extensions.browsers.Managed;
//import info.novatec.testit.webtester.waiting.Wait;

//try {
//	Wait.exactly(5, TimeUnit.SECONDS);
//} catch (Exception e) {
//	e.printStackTrace();
//}


@EnableWebTesterExtensions
@CreateBrowsersUsing(FirefoxFactory.class)
@RunWith(JUnitPlatform.class)
public class GoogleSearchTest {

	@Managed("firefox")
	@EntryPoint("https://www.google.de")
    private Browser browser;
	
    private WelcomePage googleWelcomePage;
	
	String test = "abc";
	
//	@Registered(targets="firefox")
	public MyEventListener listener;
	
	public String testField = "12";

    @BeforeEach
    public void initStartPage() {
    	System.out.println("BEFORE");
//    	listener = (event) -> System.out.println(event);
    	browser.events().register(listener);
    	googleWelcomePage = browser.create(WelcomePage.class);
    }

    @Test
    public void aGoogleSearchTest() {
    	System.out.println("TESTSTART");
    	googleWelcomePage = browser.create(WelcomePage.class);
    	
        String searchValue = "the answer to life the universe and everything plus number of horns on a unicorn";
        Result resultPage = googleWelcomePage.search(searchValue);
        
        browser.events().fireEvent(new Event() {
			
			@Override
			public LocalDateTime getTimestamp() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String describe() {
				// TODO Auto-generated method stub
				return "12345";
			}
		});
        
        Wait.until(resultPage.result()).is(visibleText("43"));
        System.out.println("PageTitle:"+browser.currentPageTitle());
    }
    
    @AfterEach
    public void cleanUp() {
    	System.out.println("AFTER");
    	browser.events().deregister(listener);
    }
}
