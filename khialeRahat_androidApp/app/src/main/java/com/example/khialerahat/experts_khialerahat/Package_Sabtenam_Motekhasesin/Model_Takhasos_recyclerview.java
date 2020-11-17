package com.example.khialerahat.experts_khialerahat.Package_Sabtenam_Motekhasesin;

public class Model_Takhasos_recyclerview {
    String takhasos;
    String image_link;

    public void setTakhasos(String takhasos) {
        this.takhasos = takhasos;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getSub_categuti_status() {
        return sub_categuti_status;
    }

    public void setSub_categuti_status(String sub_categuti_status) {
        this.sub_categuti_status = sub_categuti_status;
    }

    String sub_categuti_status;

    public String getTakhasos() {
        return takhasos;
    }

    public Model_Takhasos_recyclerview(String takhasos,String sub_categuti_status ,String image_link) {
        this.takhasos = takhasos;
        this.sub_categuti_status=sub_categuti_status;
        this.image_link=image_link;
    }
}
