package pageobjects.google;

import info.novatec.testit.webtester.conditions.pagefragments.Visible;
import info.novatec.testit.webtester.pagefragments.Span;
import info.novatec.testit.webtester.pagefragments.annotations.IdentifyUsing;
import info.novatec.testit.webtester.pagefragments.annotations.PostConstructMustBe;
import info.novatec.testit.webtester.pagefragments.annotations.WaitUntil;
import info.novatec.testit.webtester.pagefragments.identification.producers.Id;
import info.novatec.testit.webtester.pages.Page;


public interface Calculator extends Page{

	@WaitUntil(Visible.class)
	@PostConstructMustBe(Visible.class)
	@IdentifyUsing(value="cwos", how=Id.class)
	Span result();
}
