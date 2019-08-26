package com.blog.blogBack.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Data
public class Carousel extends BaseEntity implements Serializable {

    /**
     * 图片
     */
    private String image;

    /**
     * 顺序
     */
    @Column(name = "image_order")
    private Integer imageOrder;

    /**
     * 链接
     */
    private String url;

}
