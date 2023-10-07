package com.idb.tasks.serviceImpl;

import com.idb.tasks.entity.User;
import com.idb.tasks.exceptions.InvalidOperationException;
import com.idb.tasks.repository.UserRepository;
import com.idb.tasks.service.UserService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.hibernate.Session;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ResourceLoader resourceLoader;
    private final DataSource dataSource;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) throws Exception {
        if (user.hasId()) {
            return save(user);
        } else {
            throw new InvalidOperationException("User id required for update operation");
        }
    }

    @Override
    public Page<User> getByIds(Pageable pageable, Long... ids) {
        if (ids == null || ids.length < 1) {
            return getAll(pageable);
        } else {
            return userRepository.getByIds(Arrays.asList(ids), pageable);
        }
    }

    @Override
    public void deleteByIds(Long... ids) {
        userRepository.deleteAllByIdInBatch(Arrays.asList(ids));
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public JasperPrint getUserListReport(Long... ids) {
        try (Connection connection = dataSource.getConnection()) {
            String relativeReportPath = "classpath:static/reports/userList.jasper";
            String absoluteReportPath = resourceLoader.getResource(relativeReportPath).getURI().getPath();
            Map<String, Object> params = new HashMap<>();
            params.put("id", ids[0]);
            return JasperFillManager.fillReport(absoluteReportPath, params, connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
