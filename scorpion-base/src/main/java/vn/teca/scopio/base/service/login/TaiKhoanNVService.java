package vn.teca.scopio.base.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.teca.scopio.base.model.TaiKhoanNv;
import vn.teca.scopio.base.model.login.TaiKhoanNVDtoLogin;
import vn.teca.scopio.base.repository.TaiKhoanNvRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaiKhoanNVService {
    @Autowired
    private TaiKhoanNvRepository repository;

    public Optional<TaiKhoanNVDtoLogin> findTaiKhoan(String soDienThoai, String password) {
        Optional<TaiKhoanNVDtoLogin> optionalTaiKhoan = repository.getInfoLoginNV(soDienThoai, password);

        if (optionalTaiKhoan.isPresent() && Objects.nonNull(optionalTaiKhoan.get())) {
            // Result is present and not null
            TaiKhoanNVDtoLogin taiKhoanNVDtoLogin = optionalTaiKhoan.get();
            // Do something with taiKhoanNVDtoLogin
            return Optional.of(taiKhoanNVDtoLogin);
        } else {
            // Handle the case where the result is not present or is present but null
            System.out.println("Login failed. Invalid phone number or password.");
            return Optional.empty();
        }
    }
    public List<TaiKhoanNv> getall(){
        return repository.findAll();
    }
    public TaiKhoanNv xoa(Integer id){
        Optional<TaiKhoanNv> optional=repository.findById(id);
        return optional.map(o->{
            repository.delete(o);
            return o;
        }).orElse(null);
    }
    public TaiKhoanNv them(TaiKhoanNv taiKhoanNv){
        return repository.save(taiKhoanNv);
    }
    public TaiKhoanNv update(TaiKhoanNv taiKhoanNv,Integer id){
        Optional<TaiKhoanNv>optional=repository.findById(id);
        return optional.map(o -> {
            o.setCccd(taiKhoanNv.getCccd());
            o.setEmail(taiKhoanNv.getEmail());
            o.setHoTen(taiKhoanNv.getHoTen());
            o.setTenTaiKhoan(taiKhoanNv.getTenTaiKhoan());
            o.setQuyenHanIdQuyenHan(taiKhoanNv.getQuyenHanIdQuyenHan());
            o.setMatKhau(taiKhoanNv.getMatKhau());
            return repository.save(taiKhoanNv);
        }).orElse(null);
    }

}
