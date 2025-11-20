package pe.ask.university.model.period;

import java.util.UUID;

public class PeriodBuilder {
    private UUID id;
    private String name;

    PeriodBuilder() {
    }

    public PeriodBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public PeriodBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Period build() {
        return new Period(id, name);
    }
}
