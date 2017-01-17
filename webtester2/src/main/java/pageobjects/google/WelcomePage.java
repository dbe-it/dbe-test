package pageobjects.google;

import info.novatec.testit.webtester.conditions.pagefragments.Visible;
import info.novatec.testit.webtester.pagefragments.Button;
import info.novatec.testit.webtester.pagefragments.TextField;
import info.novatec.testit.webtester.pagefragments.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pagefragments.annotations.PostConstructMustBe;
import info.novatec.testit.webtester.pagefragments.annotations.WaitUntil;
import info.novatec.testit.webtester.pagefragments.identification.producers.Id;
import info.novatec.testit.webtester.pagefragments.identification.producers.Name;
import info.novatec.testit.webtester.pages.Page;


public interface WelcomePage extends Page {
	
    @PostConstructMustBe(Visible.class)
    @WaitUntil(Visible.class)
    @IdentifyUsing(value="lst-ib", how=Id.class)
    TextField searchField();
    
    @IdentifyUsing(value="btnG", how=Name.class)
    Button searchButton();
    
    default Result search(String searchValue) {
        return setSearchValue(searchValue).clickSearch();
    }

    default WelcomePage setSearchValue(String searchValue) {
    	searchField().setText(searchValue);
        return this;
    }

    default Result clickSearch() {
    	searchButton().click();
        return create(Result.class);
    }
}
