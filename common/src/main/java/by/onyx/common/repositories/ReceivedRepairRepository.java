package by.onyx.common.repositories;

import by.onyx.common.pojo.ReceivedRepair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceivedRepairRepository extends JpaRepository<ReceivedRepair, Integer> {

    List<ReceivedRepair> findByState(boolean status);

}
