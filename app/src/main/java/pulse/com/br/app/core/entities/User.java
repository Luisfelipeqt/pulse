package pulse.com.br.app.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User extends Auditable implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(length = 45)
    private String name;

    @Column( length = 45, unique = true)
    private String login;

    @Column(length = 100)
    private String password;
}