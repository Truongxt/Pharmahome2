/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.regex.Pattern;

/**
 *
 * @author Lenovo
 */
public class regax_Nhan {
    public boolean passwordNV(String mk){
           String regex = "^(?=.*[a-zA-Z])(?=.*\\d).{6,}$";
         if (Pattern.matches(regex, mk)) {
               return true;
            } 
         return false;
    }
}
