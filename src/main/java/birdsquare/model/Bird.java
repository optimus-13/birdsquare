package birdsquare.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bird")
public class Bird {

    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
    private long id;
    private String common_name;
    private String scientific_name;
    private String family_name;
    private String order_name;
    private String image_url;


    public String getCommon_name() {
        return common_name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public String getOrder_name() {
        return order_name;
    }

    public long getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }
}
