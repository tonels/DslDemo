package querydsl.repo.customer;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import querydsl.vo.CityHotelVo;
import querydsl.vo.CityHotelVo2;
import querydsl.vo.CityHotelVo3;

import java.util.List;

public interface TCityRepoCustom {

    /**
     * 关联查询示例,查询出城市和对应的旅店
     *
     * @param predicate 查询条件
     * @return 查询实体
     */
    List<Tuple> findCityAndHotel(Predicate predicate);

    /**
     * 关联查询示例,查询出城市和对应的旅店
     *
     * @param predicate 查询条件
     * @return 查询实体
     */
    QueryResults<Tuple> findCityAndHotelPage(Predicate predicate, Pageable pageable);

    // 自定义返回
    List<CityHotelVo> findcityHotel();

    List<CityHotelVo> findcityHotel_2();

    List<CityHotelVo2> findcityHotel_3();

    long count1();

    List<Long> count2();

    List<Tuple> count3();

    List<CityHotelVo3> findcityHotel_31();
}
