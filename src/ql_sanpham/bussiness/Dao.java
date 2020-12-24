
package ql_sanpham.bussiness;

import java.util.List;
import java.util.Optional;

/*
 *@Author: AnthonyLe
 * *Vjp pRo
 */

public interface Dao<T> {
    List<T> getAll();
    Optional<T> get(int id);
    int insert(T t);
    boolean update(T t);
    boolean delete(T t);
}
