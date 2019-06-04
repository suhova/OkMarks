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

    public static List<GroupMarkWrapper> wrapMark(List<WebElement> elements){
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<GroupMarkWrapper> cardMark = new ArrayList<>();
        for (WebElement card: elements) {
            cardMark.add(new GroupMarkWrapper(card));
        }
        return cardMark;
    }

    public static List<GroupWrapper> wrapGroup(List<WebElement> elements){
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<GroupWrapper> cardGroup = new ArrayList<>();
        for (WebElement card: elements) {
            cardGroup.add(new GroupWrapper(card));
        }
        return cardGroup;
    }

    public static List<PostWrapper> wrapPost(List<WebElement> elements){
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<PostWrapper> cardPost = new ArrayList<>();
        for (WebElement card: elements) {
            cardPost.add(new PostWrapper(card));
        }
        return cardPost;
    }


}