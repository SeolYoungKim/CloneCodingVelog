package ToyProject.CloneCodingVelog.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mId;

    @Column(unique = true)
    private String memberId;

    @Column
    private String password;

    @Builder
    public MemberEntity(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
