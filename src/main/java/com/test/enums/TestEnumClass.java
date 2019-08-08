package com.test.enums;

/**
 * Creator weishi8
 * Date&Time 2019-07-05 15:07
 * description
 */
public class TestEnumClass {
    public static void main(String[] args) {
        System.out.println(ComplexEnum.ZHANGSAN);
        Enum zhangsan = ComplexEnum.ZHANGSAN;
        System.out.println("zhangsan == ComplexEnum.ZHANGSAN:"+(zhangsan == ComplexEnum.ZHANGSAN));
        System.out.println("zhangsan.equals(ComplexEnum.ZHANGSAN):"+(zhangsan.equals(ComplexEnum.ZHANGSAN)));

        ComplexEnum[] complexEnums = ComplexEnum.values();
        for (int i = 0; i <complexEnums.length ; i++) {
            ComplexEnum complexEnum = complexEnums[i];
            System.out.println(complexEnum+","+complexEnum.ordinal());
        }

        System.out.println(ComplexEnum.ZHANGSAN.compareTo(ComplexEnum.LISI));
        System.out.println(ComplexEnum.LISI.compareTo(ComplexEnum.ZHANGSAN));
        System.out.println(ComplexEnum.ZHANGSAN.compareTo(ComplexEnum.ZHANGSAN));

        System.out.println(SimpleEnum.CAT);

    }
}
