package ok.pages;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardTransformer {
    private CardTransformer(){};

    public static List<MenuWrapper> wrapMenu(List<WebElement> elements){
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<MenuWrapper> cardMenu = new ArrayList<>();
        for (WebElement card: elements) {
            cardMenu.add(new MenuWrapper(card));
        }
        return cardMenu;
    }

    public static List<MarkWrapper> wrapMark(List<WebElement> elements){
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<MarkWrapper> cardMark = new ArrayList<>();
        for (WebElement card: elements) {
            cardMark.add(new MarkWrapper(card));
        }
        return cardMark;
    }
}