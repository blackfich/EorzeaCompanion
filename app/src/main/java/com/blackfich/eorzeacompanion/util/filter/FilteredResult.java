package com.blackfich.eorzeacompanion.util.filter;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class FilteredResult<T> {

    private final int realPosition;
    private final int timeColor;

    public FilteredResult(int realPosition, int timeColor) {
        this.realPosition = realPosition;
        this.timeColor = timeColor;
    }

    public int getRealPosition() {
        return realPosition;
    }

    public int getTimeColor() {
        return timeColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FilteredResult<?> that = (FilteredResult<?>) o;

        if (realPosition != that.realPosition) {
            return false;
        }
        return timeColor == that.timeColor;

    }

    @Override
    public int hashCode() {
        int result = realPosition;
        result = 31 * result + timeColor;
        return result;
    }
}
