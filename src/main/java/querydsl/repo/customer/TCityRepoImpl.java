package querydsl.repo.customer;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;
import querydsl.vo.CityHotelVo;
import querydsl.vo.CityHotelVo2;
import querydsl.vo.CityHotelVo3;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class TCityRepoImpl implements TCityRepoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Tuple> findCityAndHotel(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        JPAQuery<Tuple> jpaQuery = queryFactory.select(
                QTCity.tCity,
                QTHotel.tHotel).from(QTCity.tCity)
                .leftJoin(QTHotel.tHotel)
                .on(QTHotel.tHotel.city.longValue().eq(QTCity.tCity.id.longValue()));
        jpaQuery.where(predicate);
        return jpaQuery.fetch();
    }

    @Override
    public QueryResults<Tuple> findCityAndHotelPage(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        JPAQuery<Tuple> jpaQuery = queryFactory.select(
                QTCity.tCity.id,
                QTHotel.tHotel).from(QTCity.tCity)
                .leftJoin(QTHotel.tHotel)
                .on(QTHotel.tHotel.city.longValue().eq(QTCity.tCity.id.longValue()))
                .where(predicate)
//                .orderBy(new OrderSpecifier<>(Order.DESC,QTCity.tCity.id))
                .orderBy(QTCity.tCity.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return jpaQuery.fetchResults();
    }

    /**
     * @return
     */
    @Override
    public List<CityHotelVo> findcityHotel() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        JPAQuery<Tuple> on = query.select(
                c.id,
                c.name,
                h.name,
                h.address).from(c).leftJoin(h).on(c.id.eq(h.city));

        QueryResults<Tuple> rts = on.fetchResults();
        List<Tuple> results = rts.getResults();

        return results.stream().map(CityHotelVo::new).collect(Collectors.toList());
    }

    /**
     * todo 这里暂未调通
     *
     * @return
     */
    @Override
    public List<CityHotelVo> findcityHotel_2() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        List<CityHotelVo> results1 = query.select(Projections.bean(CityHotelVo.class,
                c.id.as("id"),
                c.name.as("cityName"),
                h.name.as("hotelName"),
                h.address.as("address"))).from(c).leftJoin(h).on(c.id.eq(h.city)).fetchResults().getResults();
        return results1;
    }

    @Override
    public List<CityHotelVo3> findcityHotel_31() {
        JPAQuery<CityHotelVo3> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        List<CityHotelVo3> results1 = query.select(Projections.bean(CityHotelVo3.class,
                h.name,
                h.address)).from(c).leftJoin(h).on(c.id.eq(h.city)).fetchResults().getResults();
        return results1;
    }

    /**
     * todo 这里暂未调通
     *
     * @return
     */
    @Override
    public List<CityHotelVo2> findcityHotel_3() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        JPAQuery<CityHotelVo2> on = query.select(Projections
                .bean(CityHotelVo2.class,
                        c.id,
                        h.address)).from(c).leftJoin(h).on(c.id.eq(h.city));
        List<CityHotelVo2> results = on.fetchResults().getResults();
        return results;
    }

    // select count(tcity0_.map) as col_0_0_ from t_city tcity0_
    @Override
    public long count1() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        Long one = query.select(
                c.map.count()
        ).from(c).fetchOne();
        return one;
    }

    // select count(tcity0_.map) as col_0_0_ from t_city tcity0_ group by tcity0_.map
    @Override
    public List<Long> count2() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        return query.select(
                c.map.count()
        ).from(c).groupBy(c.map).fetch();
    }

    // select tcity0_.map as col_0_0_, count(tcity0_.map) as col_1_0_ from t_city tcity0_ group by tcity0_.map
    @Override
    public List<Tuple> count3() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        return query.select(
                c.map,
                c.map.count()
        ).from(c).groupBy(c.map).fetch();
    }
}
