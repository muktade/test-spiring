package com.idb.tasks.service;

import com.idb.tasks.entity.User;
import net.sf.jasperreports.engine.JasperPrint;

public interface UserService extends BaseService<User, Long> {

    JasperPrint getUserListReport(Long... ids);

}
