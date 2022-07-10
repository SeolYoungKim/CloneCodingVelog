package ToyProject.CloneCodingVelog.domain.repository.member;

import ToyProject.CloneCodingVelog.domain.entity.MemberEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static ToyProject.CloneCodingVelog.domain.entity.QMemberEntity.memberEntity;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MemberEntity findByMemberId(String memberId) {
        return jpaQueryFactory
                .selectFrom(memberEntity)
                .where(memberEntity.memberId.eq(memberId))
                .fetchOne();
    }
}
