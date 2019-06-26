package ok.pages;

import ok.pages.bookmark.GroupBookmarkWrapper;
import ok.pages.bookmark.UserBookmarkWrapper;
import ok.pages.group.GroupWrapper;
import ok.pages.mypage.MenuWrapper;
import ok.pages.post.PostWrapper;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardTransformer {
    private CardTransformer() {
    }

    ;

    public static <G> List<G> wrapFirstVersion(List<WebElement> elements, Class<G> name) {
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<G> list = new ArrayList<>();
        switch (name.getSimpleName()) {
            case "GroupWrapper":
                for (WebElement card : elements) {
                    list.add(name.cast(new GroupWrapper(card)));
                }
                break;
            case "GroupBookmarkWrapper":
                for (WebElement card : elements) {
                    list.add(name.cast(new GroupBookmarkWrapper(card)));
                }
                break;
            case "MenuWrapper":
                for (WebElement card : elements) {
                    list.add(name.cast(new MenuWrapper(card)));
                }
                break;
            case "PostWrapper":
                for (WebElement card : elements) {
                    list.add(name.cast(new PostWrapper(card)));
                }
                break;
            case "UserBookmarkWrapper":
                for (WebElement card : elements) {
                    list.add(name.cast(new UserBookmarkWrapper(card)));
                }
                break;
            default: {
                System.out.println("Класс не прописан в трансформере: " + name.getSimpleName());
            }
            break;
        }
        return list;
    }


    public static <G> List<G> wrap(List<WebElement> elements, Class<G> name) {
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<G> list = new ArrayList<>();

        if (GroupWrapper.class.isAssignableFrom(name)){
            for (WebElement card : elements) {
                list.add(name.cast(new GroupWrapper(card)));
            }
        } else if (GroupBookmarkWrapper.class.isAssignableFrom(name)){
            for (WebElement card : elements) {
                list.add(name.cast(new GroupBookmarkWrapper(card)));
            }
        } else if (MenuWrapper.class.isAssignableFrom(name)){
            for (WebElement card : elements) {
                list.add(name.cast(new MenuWrapper(card)));
            }
        } else if (PostWrapper.class.isAssignableFrom(name)){
            for (WebElement card : elements) {
                list.add(name.cast(new PostWrapper(card)));
            }
        } else if (UserBookmarkWrapper.class.isAssignableFrom(name)){
            for (WebElement card : elements) {
                list.add(name.cast(new UserBookmarkWrapper(card)));
            }
        } else System.out.println("Класс не прописан в трансформере: " + name.getSimpleName());

        return list;
    }
}