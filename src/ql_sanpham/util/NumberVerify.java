/*
 *@Author: AnthonyLe
 */
package ql_sanpham.util;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NumberVerify extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        if (input instanceof JTextField) {
            // ép kiểu về JTextField
            try {
                Integer.parseInt(((JTextField) input).getText().trim());
                return true;
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
            JOptionPane.showMessageDialog(input, "Dữ liệu trường này phải là số!!!");
        }
        return retval;
    }
}
