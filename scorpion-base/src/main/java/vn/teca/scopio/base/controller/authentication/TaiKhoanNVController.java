<<<<<<< HEAD:scorpion-base/src/main/java/vn/teca/scopio/base/controller/authentication/TaiKhoanNVController.java
package vn.teca.scopio.base.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.teca.scopio.base.model.authentication.LoginRequest;
import vn.teca.scopio.base.model.authentication.TaiKhoanNVDtoLogin;
import vn.teca.scopio.base.service.authentication.TaiKhoanNVService;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class TaiKhoanNVController {
    @Autowired
    private TaiKhoanNVService taiKhoanNVService;

    @PostMapping("/login")
    public Optional<TaiKhoanNVDtoLogin> checkUsernamePass(@RequestBody LoginRequest request){
        return taiKhoanNVService.findTaiKhoan(request.getSoDienThoai(),request.getPassword());
    }


}
=======
//package vn.teca.scopio.base.controller.login;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import vn.teca.scopio.base.model.login.LoginRequest;
//import vn.teca.scopio.base.model.login.TaiKhoanNVDtoLogin;
//import vn.teca.scopio.base.service.login.TaiKhoanNVService;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/admin")
//public class TaiKhoanNVController {
//    @Autowired
//    private TaiKhoanNVService taiKhoanNVService;
//
//    @GetMapping("/login")
//    public Optional<TaiKhoanNVDtoLogin> checkUsernamePass(@RequestBody LoginRequest request){
//        return taiKhoanNVService.findTaiKhoan(request.getSoDienThoai(),request.getPassword());
//    }
//
//
//}
>>>>>>> 79a92b7 (add ảnh (chỉ có add ảnh chưa fix load )fix port từ 1234 thành 8080):scorpion-base/src/main/java/vn/teca/scopio/base/controller/login/TaiKhoanNVController.java
