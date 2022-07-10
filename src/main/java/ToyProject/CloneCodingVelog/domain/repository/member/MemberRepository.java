package ToyProject.CloneCodingVelog.domain.repository.member;

import ToyProject.CloneCodingVelog.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>, MemberRepositoryCustom {
}
