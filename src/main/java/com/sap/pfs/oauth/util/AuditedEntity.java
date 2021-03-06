package com.sap.pfs.oauth.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author : fahadfazil
 * @since : 22/12/17
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(
        ignoreUnknown = true
)
@MappedSuperclass
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class AuditedEntity {

    @Id
    @Column(name = "ID")
    protected String id;

    @JsonIgnore
    @Version
    private Long version;

    @CreatedDate
    private LocalDateTime created;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime modified;

    @LastModifiedBy
    private String lastModifiedBy;


    public AuditedEntity() {
    }

    @PrePersist
    public void init() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
        created = LocalDateTime.now();
    }

    @PostUpdate
    public void modify(){
        modified = LocalDateTime.now();
    }
}