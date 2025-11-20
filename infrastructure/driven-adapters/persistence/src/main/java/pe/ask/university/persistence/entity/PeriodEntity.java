package pe.ask.university.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Table("period")
@NoArgsConstructor
@AllArgsConstructor
public class PeriodEntity {
    @Id
    @Column("id")
    private UUID id;

    @Column("name")
    private String name;
}
