package querydsl.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询条件
 */

@Data
@Accessors(chain = true)
public class CityHotelVo2 implements Serializable {

    private static final long serialVersionUID = 2546523L;

    private Integer id;

    private String address;


}
