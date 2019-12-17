package querydsl.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 *
 * @param <T>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageBean<T> extends ResultBean {

    /**
     * 总的页数
     */
    private Integer totalPage;
    /**
     * 总记录数
     */
    private Long totalCount;
    /**
     * 分页数据
     */
    private List<T> result;

    /**
     * @param totalPage  总页数
     * @param totalCount 总数
     * @param result
     */
    public PageBean(Integer totalPage, Long totalCount, List<T> result) {
        super(GlobalCodeEnum.SUCCESS.getCode(), GlobalCodeEnum.SUCCESS.getMsg());
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.result = result;
    }

    public static <T> PageBean<T> ok(Integer totalPage, Long totalCount, List<T> result) {
        return new PageBean<>(totalPage, totalCount, result);
    }

    public static <T> PageBean<T> empty() {
        return new PageBean<>(0, 0L, new ArrayList<>());
    }

}
