package com.pigspace.common.support;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@NoArgsConstructor @AllArgsConstructor
public abstract class BaseEntity implements Persistable<Integer>{

	@Column(updatable = false) // createdDate 컬럼은 insert 시에 들어가는 값이 쭉 유지되어야 하므로 update 되어서는 안되기에 처리
    protected LocalDateTime createdDate;

	protected LocalDateTime updatedDate;

	@Transient
	@Builder.Default // DB 테이블에 적용되지 않음
	protected boolean isCreateMode = true;

	@Override
	public boolean isNew() {
		return isCreateMode;
	}


    @PrePersist
    public void prePersist() {
    	// isNew가 먼저 실행됨.
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now;
    }

    @PreUpdate
    public void preUpdate() {
    	updatedDate = LocalDateTime.now();
    }
}
