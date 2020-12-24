
package ql_sanpham.util;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import ql_sanpham.bussiness.Product_function;
import ql_sanpham.entity.Product;

/*
 *@Author: AnthonyLe
 * *Vjp pRo
 */

public class CheckProductIdVerify extends InputVerifier {     // có lỗi cần xem xét lại
    Product product; // để nhận giá trị này cần 1 hàm tạo

    public CheckProductIdVerify(Product product) {
        this.product = product;
    }

    Product_function product_function = new Product_function();

    @Override
    public boolean verify(JComponent input) {
        if (input instanceof JTextField) {
            String text = ((JTextField) input).getText().trim(); // lấy về 1 xâu text
            if (text.equals(product.getProductid())) {
                return true;
            } else {
                // không bằng set text có tồn tại csdl không ?
                Product p = product_function.get(text).get(); // trả về 1 optional
                if (p.getId() > 0) {
                    // nếu id > -0 thì sản phẩm này tồn tại
                    return false;
                } else {
                    return true; // chưa tồn tại
                }
            }
        }
        return false;
    }

    @Override
    public boolean shouldYieldFocus(JComponent input) {
        // lấy về kết quả để thông báo
        boolean retval = verify(input); // trả về true false
        if (!retval) { // nếu false
            JOptionPane.showMessageDialog(input, "Mã sản phẩm đã tồn tại, hãy nhập mã khác");
        }
        return retval;
    }
}
