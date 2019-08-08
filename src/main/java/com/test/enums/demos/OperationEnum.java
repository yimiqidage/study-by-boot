package com.test.enums.demos;

/**
 * Creator weishi8
 * Date&Time 2019-07-05 16:47
 * description
 */
public enum OperationEnum {
    PLUS {
        double eval(double x, double y) { return x + y; }
    },
    MINUS {
        double eval(double x, double y) { return x - y; }
    }, TIMES {
        double eval(double x, double y) { return x * y; }
    },
    DIVIDED_BY {
        double eval(double x, double y) { return x / y; }
    };

    abstract double eval(double x, double y);

    public static void main(String args[]) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        for (OperationEnum op : OperationEnum.values())
            System.out.println(x + " " + op + " " + y +
                    " = " + op.eval(x, y));
    }
}
