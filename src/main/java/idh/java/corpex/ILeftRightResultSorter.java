package idh.java.corpex;

import java.util.Collection;

public interface ILeftRightResultSorter extends IResultSorter {
    public Collection<Result> sortLeft(Collection<Result> results);

}
