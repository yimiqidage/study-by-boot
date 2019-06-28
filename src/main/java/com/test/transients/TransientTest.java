package com.test.transients;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**使用 transient 修饰的变量，不会被序列化
 * @author weishi8
 * @create 2019-05-17
 * @description
 */
public class TransientTest implements Serializable{
    private static final long serialVersionUID = 1L;
    private transient String name;        //注意：name前面加了transient关键字
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * 测试整体步骤：
     * 1.生成一个文件，写入序列化后的数据；（其中的一个字段name，使用transient修饰，代表不序列化字段name）；
     * 2.读取序列化后的文件内容，读取结果中，没有打印name的值，因此可以说明name并未序列化到文件。
     */
    public static void main(String[] args) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        String path = "object.txt";
        File file = new File(path);
        TransientTest tr = new TransientTest();
        tr.setName("姓名");
        tr.setPassword("密码");
        ObjectOutput output = new ObjectOutputStream(new FileOutputStream(file));
        output.writeObject(tr);
        output.flush();
        output.close();
        ObjectInput input = new ObjectInputStream(new FileInputStream(file));
        TransientTest demo = (TransientTest) input.readObject();

        System.out.println(demo.getName() + "，"+demo.getPassword());

    }


}
