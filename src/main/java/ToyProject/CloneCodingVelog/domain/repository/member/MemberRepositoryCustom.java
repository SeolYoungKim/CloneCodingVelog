package ToyProject.CloneCodingVelog.domain.repository.member;

import ToyProject.CloneCodingVelog.domain.entity.MemberEntity;

public interface MemberRepositoryCustom {

    MemberEntity findByMemberId(String memberId);
}
