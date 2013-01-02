package ru.coolga.chain.core;

/**
 * Add some comments here...
 *
 * @author: Dmitry Coolga
 * Date:    30.12.2012 10:26 PM
 */
public class QueryParameter {

    private DataType type;
    private Object value;

    public QueryParameter(DataType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public DataType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

}
