package by.onyx.common.data.impl;

import by.onyx.common.pojo.Support;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.repositories.SupportRepository;
import by.onyx.common.data.SupportData;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupportDataImpl implements SupportData {

    private static final Logger log = LoggerFactory.getLogger(SupportData.class);

    @Autowired
    private SupportRepository supportRepository;

    @Transactional
    public Support save(Support support) {
        Support result = null;
        try{
            if(support != null){
                result = supportRepository.saveAndFlush(support);
            }
        }catch (Exception e){
            log.error("can't save support form " + e);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<Support> getAll() {
        List<Support> result = supportRepository.findAll();
        return result;
    }

    @Transactional(readOnly = true)
    public Support getById(int id) {
        Support result = supportRepository.findOne(id);
        Hibernate.initialize(result.getUser());
        return result;
    }

    public List<Support> getAllBySupportType(Support.SupportType type) {
        List<Support> result = null;
        for(Support.SupportType supportType : Support.SupportType.values()){
            if(supportType.equals(type)){
                result = supportRepository.findBySupportType(supportType);
            }
        }
        return result;
    }

    @Transactional
    public List<Support> getAllByStatus(Support.SupportStatus status) {
        return supportRepository.findBySupportStatus(status);
    }

    @Transactional
    public List<Support> getAllByUser(User user) {
        List<Support> result = null;
        if(user != null){
            result = supportRepository.findByUser(user);
        }
        return result;
    }
}
