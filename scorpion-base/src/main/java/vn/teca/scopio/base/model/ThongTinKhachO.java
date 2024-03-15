package vn.teca.scopio.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Table(name = "thong_tin_khach_o")
public class ThongTinKhachO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_khach_o", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "phong_dat_id_phong_dat", nullable = false)
    private PhongDat phongDatIdPhongDat;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "ho_ten", nullable = false, length = 200)
    private String hoTen;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "quoc_tich", nullable = false, length = 200)
    private String quocTich;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "ten_giay_to", nullable = false, length = 20)
    private String tenGiayTo;

    @Size(max = 200)
    @NotNull
    @Nationalized
    @Column(name = "so_giay_to", nullable = false, length = 200)
    private String soGiayTo;

}