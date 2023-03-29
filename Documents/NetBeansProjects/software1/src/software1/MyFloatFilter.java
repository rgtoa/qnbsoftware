/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package software1;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

// DocumentFilter Taken From StackOverflow
public class MyFloatFilter extends DocumentFilter {
   @Override
   public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
         AttributeSet attr) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.insert(offset, string);

      if (test(sb.toString())) {
         super.insertString(fb, offset, string, attr);
      } else {
         // warn the user and don't allow the insert
      }
   }

   private boolean test(String text) {
      try {
         Float.parseFloat(text);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }

   @Override
   public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
         AttributeSet attrs) throws BadLocationException {
      
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);
      if (sb.toString().length() == 0) {
          super.replace(fb, offset, length, "", null);
      }
      else if (test(sb.toString()) && !(sb.toString().endsWith("f") ||
                                       sb.toString().endsWith("d"))) {
         super.replace(fb, offset, length, text, attrs);
      } else {
         // warn the user and don't allow the insert
      }

   }

   @Override
   public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
         throws BadLocationException {
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.delete(offset, offset + length);
      
      if (sb.toString().length() == 0) {
          super.replace(fb, offset, length, "", null);
      }
      else if (test(sb.toString())) {
         super.remove(fb, offset, length);
      } else {
         // warn the user and don't allow the insert
      }
      
   }
}