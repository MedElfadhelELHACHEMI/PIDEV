/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exceptions;

/**
 *
 * @author haikal
 */
public class FormulaireException extends Exception{

    public FormulaireException(String message) {
        super(message);
    }

    public FormulaireException(Throwable cause) {
        super(cause);
    }

    public FormulaireException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
