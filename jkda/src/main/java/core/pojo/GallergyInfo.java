package core.pojo;



import java.util.Date;

public class GallergyInfo {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private String gallergy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGallergy() {
        return gallergy;
    }

    public void setGallergy(String gallergy) {
        this.gallergy = gallergy == null ? null : gallergy.trim();
    }
}