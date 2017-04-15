package donkey.bora.com.model;

import com.google.gson.annotations.SerializedName;

public class BoardVO {

    @SerializedName("board_status")
    private int boardStatus;
    private String title;
    private String desc;

    // FIXME: fixed api first
    //@SerializedName("region_a")
    //private RegionAVO regionA;
    //@SerializedName("region_b")
    //private RegionBVO regionB;
    //private UniversityVO university;
    //private DepartmentVO department;
    //@SerializedName("school_type")
    //private SchoolTypeVO schoolType;
    //@SerializedName("university_type")
    //private UniversityTypeVO universityType;
    //@SerializedName("created_at")
    //private Date createdAt;
    //@SerializedName("modified_at")
    //private Date modifiedAt;

}
