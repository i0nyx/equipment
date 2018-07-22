package by.onyx.common.data;

import by.onyx.common.pojo.Support;
import by.onyx.common.pojo.SupportStatus;
import by.onyx.common.pojo.SupportType;
import by.onyx.common.pojo.profile.User;

import java.util.List;

public interface SupportData {

    Support save(Support support);

    List<Support> getAll();

    Support getById(int id);

    List<Support> getAllBySupportType(SupportType type);

    List<Support> getAllByStatus(SupportStatus status);

    List<Support> getAllByUser(User user);
}
