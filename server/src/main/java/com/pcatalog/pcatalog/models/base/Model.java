package com.pcatalog.pcatalog.models.base;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public abstract class Model {

    @Id
    @GeneratedValue
    private Long recordId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordCreated;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordLastTimeEdited;

    public Model() {
        setRecordCreated(new Date());
        setRecordLastTimeEdited(new Date());
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Date getRecordCreated() {
        return recordCreated;
    }

    public void setRecordCreated(Date recordCreated) {
        this.recordCreated = recordCreated;
    }

    public Date getRecordLastTimeEdited() {
        return recordLastTimeEdited;
    }

    public void setRecordLastTimeEdited(Date recordLastTimeEdited) {
        this.recordLastTimeEdited = recordLastTimeEdited;
    }
}
