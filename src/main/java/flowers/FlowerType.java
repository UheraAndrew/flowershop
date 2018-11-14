package flowers;

import lombok.Getter;

@Getter
public enum FlowerType {
    Chamomile("Chamomile"), Tulip("Tulip"), Rose("Rose");
    private String flower_type;

    FlowerType(String flower_type) {
        this.flower_type = flower_type;
    }
}