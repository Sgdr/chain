package ru.coolga.chain.clause;

/**
 * Add some comments here...
 *
 * @author : Dmitry Coolga
 * Date :    31.12.2012 5:28 PM
 */
public interface ExecuteWhereClause<T> {

    public ExecuteRelationClause<T> where(String column);

    public T all();

}
