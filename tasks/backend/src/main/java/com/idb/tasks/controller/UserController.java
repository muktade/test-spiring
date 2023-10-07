package com.idb.tasks.controller;

import com.idb.tasks.entity.User;
import com.idb.tasks.service.UserService;
import com.idb.tasks.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController implements BaseController<User, Long> {

    private final UserService userService;

    @Override
    public ResponseEntity<User> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<String> update(@RequestBody User user) {
        try {
            userService.update(user);
            return ResponseEntity.ok("Successfully updated user");
        } catch (Exception e){
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<User> getById(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Page<User>> getByIds(@PathVariable("ids") Long... ids) {
        Page<User> userList = userService.getByIds(Pageable.ofSize(10), ids);
        return ResponseEntity.ok(userList);
    }

    @Override
    public ResponseEntity<String> deleteByIds(@PathVariable("ids") Long... ids) {
        userService.deleteByIds(ids);
        return ResponseEntity.ok("Users deleted successfully");
    }

    @Override
    public ResponseEntity<Page<User>> getAll(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortDirection", required = false) String sortDirection
    ) {
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<User> page = userService.getAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "user-list/{ids}")
    public ResponseEntity<byte[]> getAll(@PathVariable(value = "ids") Long... ids) throws Exception{
        JasperPrint jasperPrint = userService.getUserListReport(ids);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/pdf");
        httpHeaders.add("Content-Disposition", "attachment; filename=user_list.pdf");
        return ResponseEntity.ok().headers(httpHeaders).body(out.toByteArray());
    }



}
