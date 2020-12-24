/*
 *@Author: AnthonyLe
 */
package ql_sanpham.util;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StringVerify extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        if (input instanceof JTextField) {
            // ép kiểu về JTextField
            try {
                String text = ((JTextField) input).getText().trim();
                if (text != null && text.length() > 0) {  // ít nhất 1 kí tự
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean shouldYieldFocus(JComponent input) {
        // lấy về kết quả để thông báo
        boolean retval = verify(input); // trả về true false
        if (!retval) { // nếu false
            JOptionPane.showMessageDialog(input, "Phải nhập dữ liệu trường này ít nhất 1 kí tự !!!");
        }
        return retval;
    }

}
