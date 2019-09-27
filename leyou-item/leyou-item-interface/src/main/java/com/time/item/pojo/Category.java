package com.time.item.pojo;

import javax.persistence.*;


//下面这些注解需要依赖这个包
//<dependency>
//<groupId>javax.persistence</groupId>
//<artifactId>persistence-api</artifactId>
//<version>1.0</version>
//</dependency>

@Table(name = "tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //'父类目id,顶级类目填0',
    @Column(name = "parent_id")
    private Long parentId;

    //'是否为父节点，0为否，1为是',
    @Column(name = "is_parent")
    private Boolean isParent; // 注意isParent生成的getter和setter方法需要手动加上Is 是否为父节点，

    private Integer sort;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
