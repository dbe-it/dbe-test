package dbe.google;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.expectThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;

import info.novatec.testit.webtester.browser.Browser;
import info.novatec.testit.webtester.events.Event;
import info.novatec.testit.webtester.events.EventListener;
import info.novatec.testit.webtester.events.EventSystem;
import info.novatec.testit.webtester.junit5.EnableWebTesterExtensions;
import info.novatec.testit.webtester.junit5.extensions.browsers.CreateBrowsersUsing;
import info.novatec.testit.webtester.junit5.extensions.browsers.CreateUsing;
import info.novatec.testit.webtester.junit5.extensions.browsers.Managed;
import info.novatec.testit.webtester.junit5.extensions.eventlisteners.BrowserAssignmentException;
import info.novatec.testit.webtester.junit5.extensions.eventlisteners.Registered;
import info.novatec.testit.webtester.junit5.extensions.pages.StaticPageFieldsNotSupportedException;
import utils.TestBrowserFactory;
import utils.TestClassExecutor;

@RunWith(JUnitPlatform.class)
public class RegisteredExtensionIntegrationTest {

	@Test
	@DisplayName("@Registered registers the EventListener. An event will be handled.")
	void registerEventListenerField() throws Exception {
		TestClassExecutor.execute(RegisteredField.class);
	}
	
	@Test
	@DisplayName("@Registered with static field will throw exception")
	void registerStaticEventListenerField() throws Exception {
		expectThrows(StaticPageFieldsNotSupportedException.class, () -> {
			TestClassExecutor.execute(RegisteredStaticField.class);
		});
	}

	@Test
	@DisplayName("@Registered not set for EventListener.")
	void dontRegisterEventListenerField() throws Exception {
		TestClassExecutor.execute(NoRegisteredField.class);
	}
	
	@Test
	@DisplayName("Dont initialize EventListener if pre-initialized.")
	void dontReinitializeEventListenerField() throws Exception {
		TestClassExecutor.execute(RegisterPreInitializedField.class);
	}
	
	@Test
	@DisplayName("Use default constructor to instanciate event listener.")
	void useDefaultConstructor() throws Exception {
		TestClassExecutor.execute(DefaultConstructor.class);
	}
	
	@Test
	@DisplayName("EventListener is registered at the beginning and unregistered at the end of a test.")
	void lifecycleOfEventListener() throws Exception {
		TestClassExecutor.execute(LifeCycle.class);
	}
	
	@Test
	@DisplayName("@Registered registers the EventListener to unnamed browser.")
	void registerEventListenerToUnnamedBrowser() throws Exception {
		TestClassExecutor.execute(UnnamedBrowser.class);
	}
	
	@Test
	@DisplayName("@Registered registers the EventListener to 2 browser.")
	void registerEventListenerTo2Browser() throws Exception {
		TestClassExecutor.execute(MultiBrowser.class);
	}
	
	@Test
	@DisplayName("@Registered has no clear assignment to multi browser fields.")
	void noClearBrowserAssignment() throws Exception {
		expectThrows(BrowserAssignmentException.class, () -> {
			TestClassExecutor.execute(NoClearBrowserAssignment.class);
		});
	}

	private static class RegisteredStaticField extends AbstractManagedBrowser {

		@Registered(targets = "myBrowser")
		private static CustomEventListener eventListener;

		@Test
		void test() {
		}

	}

	private static class RegisteredField extends AbstractManagedBrowser {

		@Registered(targets = "myBrowser")
		private CustomEventListener eventListener;

		@Test
		void test() {
			browser.open("http://www.google.de");
			String eventDescription = eventListener.getRecentEvent().describe();
			assertThat(eventDescription).isEqualTo("opened url: 'http://www.google.de'");
		}

	}

	private static class NoRegisteredField extends AbstractManagedBrowser {

		private CustomEventListener eventListener;

		@Test
		void test() {
			assertThat(eventListener).isNull();
		}

	}
	
	private static class RegisterPreInitializedField extends AbstractManagedBrowser {

		@Registered(targets = "myBrowser")
		private CustomEventListener registeredEventListener = new CustomEventListener();
		
		CustomEventListener preInitialized = registeredEventListener;
		
		@Test
		void test() {
			assertThat(preInitialized).isEqualTo(registeredEventListener);
		}

	}
	
	private static class DefaultConstructor extends AbstractManagedBrowser {

		@Registered(targets = "myBrowser")
		private CustomEventListener eventListener;

		@Test
		void test() {
			assertThat(eventListener.isUsedDefaultConstructor()).isTrue();
		}

	}
	
	private static class UnnamedBrowser extends BaseTestClass{

		@Managed
		Browser browser;
		
		@Registered
		private CustomEventListener eventListener;
		
		@Test
		void test() {
			browser.open("http://www.google.de");
			String eventDescription = eventListener.getRecentEvent().describe();
			assertThat(eventDescription).isEqualTo("opened url: 'http://www.google.de'");
		}

	}
	
	private static class NoClearBrowserAssignment extends BaseTestClass{

		@Managed("browser-1")
		Browser browser1;
		
		@Managed("browser-2")
	    Browser browser2;
		
		@Registered
		private CustomEventListener eventListener;
		
		@Test
		void test() {
		}

	}
	
	private static class MultiBrowser extends BaseTestClass{

		@Managed("browser-1")
		Browser browser1;
		
		@Managed("browser-2")
	    Browser browser2;
		
		@Managed()
	    Browser browser3;
		
		@Registered(targets={"browser-1", "browser-2"})
		private CustomEventListener eventListener;
		
		@Test
		void test() {
			browser1.open("http://www.google.de");
			String eventDescription = eventListener.getRecentEvent().describe();
			assertThat(eventDescription).isEqualTo("opened url: 'http://www.google.de'");
			eventListener.clear();
			
			browser2.open("http://www.google.de");
			eventDescription = eventListener.getRecentEvent().describe();
			assertThat(eventDescription).isEqualTo("opened url: 'http://www.google.de'");
			eventListener.clear();
			
			browser3.open("http://www.google.de");
			assertThat(eventListener.getRecentEvent()).isNull();
		}

	}
	
	@EnableWebTesterExtensions
	private static class LifeCycle {

		static Map<Browser, EventListener> map = new HashMap<>();

		@Managed
		@CreateUsing(SpyableEventsTestBrowserFactory.class)
		Browser browser;

		@Registered
		EventListener el = new MyEventListener();

		@Test
		void triggerExecution1(){
		}

		@Test
		void triggerExecution2(){
		}

		@AfterEach
		void rememberBrowserAndEventLsitener(){
			map.put(browser, el);
		}

		@AfterAll
		static void verifyRegistrationAndDeregistration(){
			map.forEach((browser, eventListener) -> {
				EventSystem es = browser.events();
				InOrder inOrder = Mockito.inOrder(es);
				inOrder.verify(es).register(eventListener);
				inOrder.verify(es).deregister(eventListener);
				inOrder.verifyNoMoreInteractions();
			});
		}

	}
	
	private static abstract class AbstractManagedBrowser extends BaseTestClass{

		@Managed("myBrowser")
		Browser browser;
	}
	
	@EnableWebTesterExtensions
	@CreateBrowsersUsing(TestBrowserFactory.class)
	private static abstract class BaseTestClass {

	}
	
	public static class CustomEventListener implements EventListener {

		private Event recentEvent = null;
		
		boolean usedDefaultConstructor = false;
		
		public CustomEventListener(){
			usedDefaultConstructor = true;
		}
		
		public CustomEventListener(String aString){
			//should be unused
		}

		@Override
		public void eventOccurred(Event event) {
			recentEvent = event;
			System.out.println("EVENT OCCURED:" + event.describe());
		}

		public Event getRecentEvent() {
			return recentEvent;
		}

		public boolean isUsedDefaultConstructor() {
			return usedDefaultConstructor;
		}
		
		public void clear(){
			recentEvent = null;
			usedDefaultConstructor = false;
		}
		
	}

}
