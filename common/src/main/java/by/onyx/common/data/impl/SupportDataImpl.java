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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class SupportDataImpl implements SupportData {
    private final SupportRepository supportRepository;

    @Override
    public Support save(final Support support) {
        Optional.ofNullable(support).orElseThrow(() -> new IllegalArgumentException("Error: support should not be null!"));
        return supportRepository.saveAndFlush(support);
    }

    @Override
    public List<Support> getAll() {
        return supportRepository.findAll();
    }

    @Override
    public Support getById(final int id) {
        Support result = supportRepository.findOne(id);
        Optional.ofNullable(result).ifPresent(support -> Hibernate.initialize(support.getUser()));
        return result;
    }

    @Override
    public List<Support> getAllBySupportType(final SupportType type) {
        Optional.ofNullable(type).orElseThrow(() -> new IllegalArgumentException("Error: type should not be null!"));
        return Arrays.stream(SupportType.values())
                .filter(supportType -> supportType.equals(type))
                .flatMap(supportType -> supportRepository.findBySupportType(supportType).stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Support> getAllByStatus(final SupportStatus status) {
        Optional.ofNullable(status).orElseThrow(() -> new IllegalArgumentException("Error: status should not be null!"));
        return supportRepository.findBySupportStatus(status);
    }

    @Override
    public List<Support> getAllByUser(final User user) {
        Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException("Error: user should not be null!"));
        return supportRepository.findByUser(user);
    }
}