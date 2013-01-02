package ru.coolga.chain.clause;

import java.sql.Date;

/**
 * Add some comments here...
 *
 * @author: Dmitry Coolga
 * Date:    31.12.2012 5:18 PM
 */
public interface ExecuteRelationClause<T> {

    public T equal(Long value);
    public T equal(Integer value);
    public T equal(String value);
    public T equal(Date value);

    public T less(Long value);
    public T less(Integer value);
    public T less(String value);
    public T less(Date value);

    public T greater(Long value);
    public T greater(Integer value);
    public T greater(String value);
    public T greater(Date value);

}
