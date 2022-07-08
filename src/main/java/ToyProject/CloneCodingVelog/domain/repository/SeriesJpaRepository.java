package ToyProject.CloneCodingVelog.domain.repository;

import ToyProject.CloneCodingVelog.domain.entity.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesJpaRepository extends JpaRepository<SeriesEntity, Long> {
}
