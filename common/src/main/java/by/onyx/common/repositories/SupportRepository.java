package by.onyx.common.repositories;

import by.onyx.common.pojo.Support;
import by.onyx.common.pojo.profile.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportRepository extends JpaRepository<Support, Integer>{

    List<Support> findBySupportType(Support.SupportType type);

    List<Support> findBySupportStatus(Support.SupportStatus status);

    List<Support> findByUser(User user);

}
