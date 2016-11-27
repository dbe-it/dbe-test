package info.novatec.mysud.fixture;
 
import info.novatec.testit.livingdoc.reflect.annotation.Alias;
import info.novatec.testit.livingdoc.reflect.annotation.FixtureClass;
 
@FixtureClass({ "concatenate some words", "another alias" })
public class MyFirstHelloWorldExampleFixture {
 
    private String firstWord;
    private String secondWord;
 
    @Alias({"words concatenated"})
    public String concatenateTwoWords() {
        return firstWord + " " + secondWord;
    }
 
    public String getFirstWord() {
        return firstWord;
    }
 
    @Alias({"the 1st word"})
    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }
 
    public String getSecondWord() {
        return secondWord;
    }
 
    @Alias({"the 2nd word"})
    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }
}