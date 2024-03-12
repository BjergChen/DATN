package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "tien_ich_loai_phong")
public class TienIchLoaiPhong {
    @Id
    @Column(name = "id_tien_ich_loai_phong", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tien_ich_id_tien_ich", nullable = false)
    private TienIch tienIchIdTienIch;

}