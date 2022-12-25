package server.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String nickName;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 30)
    private String country;

    @Column(nullable = false)
    private Integer reputation;

    @Column(length = 50)
    private String title;

    @Column(length = 500)
    private String introduction;

    @Column(length = 200)
    private String link;
}
