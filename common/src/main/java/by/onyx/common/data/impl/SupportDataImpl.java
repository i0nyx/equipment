package by.onyx.common.data.impl;

import by.onyx.common.data.SupportData;
import by.onyx.common.pojo.Support;
import by.onyx.common.pojo.SupportStatus;
import by.onyx.common.pojo.SupportType;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.repositories.SupportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SupportDataImpl implements SupportData {

    private SupportRepository supportRepository;

    @Override
    public Support save(Support support) {
        Support result = null;
        try {
            if (support != null) {
                result = supportRepository.saveAndFlush(support);
            }
        } catch (Exception e) {
            log.error("can't save support form " + e);
        }
        return result;
    }

    @Override
    public List<Support> getAll() {
        return supportRepository.findAll();
    }

    @Override
    public Support getById(int id) {
        Support result = supportRepository.findOne(id);
        Hibernate.initialize(result.getUser());
        return result;
    }

    @Override
    public List<Support> getAllBySupportType(SupportType type) {
        List<Support> result = null;
        for (SupportType supportType : SupportType.values()) {
            if (supportType.equals(type)) {
                result = supportRepository.findBySupportType(supportType);
            }
        }
        return result;
    }

    @Override
    public List<Support> getAllByStatus(SupportStatus status) {
        return supportRepository.findBySupportStatus(status);
    }

    @Override
    public List<Support> getAllByUser(User user) {
        List<Support> result = null;
        if (user != null) {
            result = supportRepository.findByUser(user);
        }
        return result;
    }
}
