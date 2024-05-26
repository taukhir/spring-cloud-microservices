package org.ahmed.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ahmed.util.AppConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class AuditableEntity implements Serializable {

    @JsonFormat(pattern = AppConstants.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = AppConstants.DATE_TIME_FORMAT)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @JsonFormat(pattern = AppConstants.DATE_TIME_FORMAT)
    @DateTimeFormat(pattern = AppConstants.DATE_TIME_FORMAT)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;


    @PrePersist
    protected void onCreate() {
        long nowInSeconds = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        this.createdAt = LocalDateTime.ofEpochSecond(nowInSeconds, 0, ZoneOffset.UTC);
        this.updatedAt = this.createdAt;
        this.createdBy = getCurrentUsername();
        this.updatedBy = getCurrentUsername();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = getCurrentUsername();
    }

    // Method to obtain the current username from the security context or session
    private String getCurrentUsername() {
        return AppConstants.DEFAULT_USER;
    }
}
