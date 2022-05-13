package fr.ulco.feedbacks.Entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "forms")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @NotBlank(message = "Please enter a name")
    private String formName;

    @OneToMany(fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Question> questions;

    private Instant createdAt;
    private Instant updatedAt;

    // a form has one author, an author can have many forms
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Form form = (Form) o;
        return formId != null && Objects.equals(formId, form.formId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
