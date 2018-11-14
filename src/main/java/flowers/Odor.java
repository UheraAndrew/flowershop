package flowers;

import lombok.Getter;

@Getter
public enum Odor {
    Good("It smells good"), BED("It smells bed");
    private String odor;

    Odor(String odor) {
        this.odor = odor;
    }
}
