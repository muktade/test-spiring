package com.idb.tasks.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -4196550013222459134L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "is_approved", nullable = false)
    private boolean isApproved;

    public boolean hasId() {
        return id != null && id > 0;
    }


}
