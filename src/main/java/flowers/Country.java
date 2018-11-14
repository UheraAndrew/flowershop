package flowers;

import lombok.Getter;

@Getter
public enum Country {
    UKRAINE("Ukraine");
    private String name;

    Country(String name) {
        this.name = name;
    }
}
