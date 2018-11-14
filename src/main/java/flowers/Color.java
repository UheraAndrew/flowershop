package flowers;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public enum Color {
    Blue("#ffff00"), White("#fffffff"), Red("#ff0000"), Purple("#ff00ff");
    private String rgb;

    Color(String rgb) {
        this.rgb = rgb;
    }
}
