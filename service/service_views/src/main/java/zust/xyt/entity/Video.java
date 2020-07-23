package zust.xyt.entity;


import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * @author AndrewElvis
 * @since 2020-06-25
 */
@Data
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String categoryId;

    private String userId;

    private String title;

    private String cover;

    private String intro;

    private String sourceId;

    private String originalName;

    private Long count;

    private Long likes;

    private Float duration;

    private String tags;

    private String status;

    private Long size;

    private Long version;

    private Date gmtCreate;

    private Date gmtModified;


}
