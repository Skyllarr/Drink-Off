package com.violetbutterfly.drinkoff.persistence.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity<T   extends Serializable> {

    @Column(nullable = false)
    private boolean deleted;

    public abstract T getId();

    public abstract void setId(T id);

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
