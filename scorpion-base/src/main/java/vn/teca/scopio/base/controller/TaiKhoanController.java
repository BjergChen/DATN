package vn.teca.scopio.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.teca.scopio.base.model.TaiKhoanNv;
import vn.teca.scopio.base.service.login.TaiKhoanNVService;
import vn.teca.scopio.base.service.taiKhoanKhachServices;
import vn.teca.scopio.base.service.thongTinKhachDatServices;

@RestController
@Controller
@RequestMapping("account")
public class TaiKhoanController {
     @Autowired
     taiKhoanKhachServices taiKhoanKhachServices;
     @Autowired
    thongTinKhachDatServices thongTinKhachDatServices;
    @Autowired
    TaiKhoanNVService taiKhoanNVService;

    @GetMapping("/khach-hang/hien-thi-list")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(taiKhoanKhachServices.getall());
    }

    @GetMapping("/khach-hang/tim-kiem")
    public ResponseEntity<?>locLoaiPhong(@RequestParam("id") String id){
        return ResponseEntity.ok(taiKhoanKhachServices.timkiem(id));
    }
    @GetMapping("/khach-hang/detail/{id}")
    public ResponseEntity<?>getone(@PathVariable int id){
        return ResponseEntity.ok(thongTinKhachDatServices.detail(id));
    }
    @GetMapping("/khach-hang/hien-thi")
    public ResponseEntity<?> getallnv(){
        return ResponseEntity.ok(taiKhoanNVService.getall());
    }
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<?>update(@PathVariable String id, @RequestBody TaiKhoanNv taiKhoanNv){
        return ResponseEntity.ok(taiKhoanNVService.update(taiKhoanNv,Integer.parseInt(id)));
    }
    // vô hiệu hóa  tài khoản khách hàng cần thêm trạng thái
    @PostMapping("/admin/save")
    public ResponseEntity<?>add(@RequestBody TaiKhoanNv taiKhoanNv){
        return ResponseEntity.ok(taiKhoanNVService.them(taiKhoanNv));
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable String id){
        return ResponseEntity.ok(taiKhoanNVService.xoa(Integer.parseInt(id)));
    }

}
