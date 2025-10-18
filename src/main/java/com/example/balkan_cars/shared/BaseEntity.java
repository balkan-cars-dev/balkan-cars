package com.example.balkan_cars.shared;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @Column(name = "id", columnDefinition = "UUID", nullable = false, unique = true)
    private UUID id;
    @Column(name = "businessId", columnDefinition = "UUID", nullable = false, updatable = false)
    private UUID businessId;
    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    private Instant created;
    @LastModifiedDate
    @Column(name = "updated")
    private Instant updated;

    @PrePersist
    protected void onCreate() {
        addId(this.id);
        addBusinessId(this.businessId);
        addCreated(created);
        addUpdated(updated);
    }

    private void addUpdated(Instant updated) {
        if (updated == null) {
            this.updated = Instant.now();
        }
    }

    private void addCreated(Instant created) {
        if (created == null) {
            this.created = Instant.now();
        }
    }

    private void addBusinessId(UUID businessId) {
        if (businessId == null) {
            this.businessId = UUID.randomUUID();
        }
    }

    private void addId(UUID id) {
        if (id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, updated);
    }
}
