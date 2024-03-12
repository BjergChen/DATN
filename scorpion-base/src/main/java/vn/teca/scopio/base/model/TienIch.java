package vn.teca.scopio.base.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tien_ich")
public class TienIch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tien_ich", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Nationalized
    @Column(name = "ten_tien_ich", nullable = false, length = 100)
    private String tenTienIch;

    @OneToMany(mappedBy = "tienIchIdTienIch")
    private Set<TienIchLoaiPhong> tienIchLoaiPhongs = new LinkedHashSet<>();

}