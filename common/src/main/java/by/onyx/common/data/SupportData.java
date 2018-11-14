package by.onyx.common.data;

import by.onyx.common.pojo.Support;
import by.onyx.common.pojo.SupportStatus;
import by.onyx.common.pojo.SupportType;
import by.onyx.common.pojo.profile.User;

import java.util.List;

public interface SupportData {
    Support save(final Support support);
    List<Support> getAll();
    Support getById(final int id);
    List<Support> getAllBySupportType(final SupportType type);
    List<Support> getAllByStatus(final SupportStatus status);
    List<Support> getAllByUser(final User user);
}
