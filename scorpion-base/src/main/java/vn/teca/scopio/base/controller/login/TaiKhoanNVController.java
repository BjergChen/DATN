package vn.teca.scopio.base.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.login.LoginRequest;
import vn.teca.scopio.base.model.login.TaiKhoanNVDtoLogin;
import vn.teca.scopio.base.service.login.TaiKhoanNVService;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class TaiKhoanNVController {
    @Autowired
    private TaiKhoanNVService taiKhoanNVService;

    @GetMapping("/login")
    public Optional<TaiKhoanNVDtoLogin> checkUsernamePass(@RequestBody LoginRequest request){
        return taiKhoanNVService.findTaiKhoan(request.getSoDienThoai(),request.getPassword());
    }

}
