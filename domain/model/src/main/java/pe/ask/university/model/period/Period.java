package pe.ask.university.model.period;

import java.util.UUID;

public class Period {
    private UUID id;
    private String name;

    public static PeriodBuilder builder() {
        return new PeriodBuilder();
    }

    public PeriodBuilder toBuilder() {
        return new PeriodBuilder().id(this.id).name(this.name);
    }

    public Period() {
    }

    public Period(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
