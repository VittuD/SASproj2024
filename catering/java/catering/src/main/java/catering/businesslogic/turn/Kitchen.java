package catering.businesslogic.turn;

import java.util.UUID;

public class Kitchen {
    private String name;
    private UUID id;

    public Kitchen(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Kitchen{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
