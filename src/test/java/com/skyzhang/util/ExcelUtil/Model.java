/**
 * @author SargerasWang
 */
package com.skyzhang.util.ExcelUtil;

import java.util.Date;

import com.skyzhang.util.ExcelUtil.ExcelCell;


/**
 * The <code>Model</code>
 * 
 * @author SargerasWang Created at 2014年8月7日 下午5:09:29
 */
public class Model {
    @ExcelCell(index = 0)
    private String a;
    @ExcelCell(index = 1)
    private int b;
    @ExcelCell(index = 2)
    private String c;
    @ExcelCell(index = 4, ignore = true)
    private Date d;

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }
    
    public Model() {
    	
    }

    public Model(String a, int b, String c,Date d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * @return the a
     */
    public String getA() {
        return a;
    }

    /**
     * @param a
     *            the a to set
     */
    public void setA(String a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public int getB() {
        return b;
    }

    /**
     * @param b
     *            the b to set
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * @return the c
     */
    public String getC() {
        return c;
    }

    /**
     * @param c
     *            the c to set
     */
    public void setC(String c) {
        this.c = c;
    }
}
