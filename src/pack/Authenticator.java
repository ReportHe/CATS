package pack;

import struct.ArrayLengthMarker;
import struct.StructClass;
import struct.StructField;

@StructClass
public class Authenticator {
    @StructField(order = 0)
    @ArrayLengthMarker(fieldName = "IDc")
    public int IDcLen;   //Client的标识的长度

    @StructField(order = 1)
    public char[] IDc;   //Client的标识，这里使用Client的账号作为标识

    @StructField(order = 2)
    @ArrayLengthMarker(fieldName = "ADc")
    public int ADcLen;     //Client的网络地址的长度

    @StructField(order = 3)
    public char[] ADc;    //Client的标识，这里使用Client的网络地址作为标识

    @StructField(order = 4)
    @ArrayLengthMarker(fieldName = "TS")
    public int TSLen;    //Client生成票据时的时间戳的长度

    @StructField(order = 5)
    public char[] TS;    //Client生成的票据的时间

    @StructField(order = 6)
    public byte[] redundancy;   //冗余位，方便扩展功能

    public Authenticator(String IDc1, String ADc1, String TS1) {
        this.IDcLen = IDc1.length();
        this.IDc = IDc1.toCharArray();
        this.ADcLen = ADc1.length();
        this.ADc = ADc1.toCharArray();
        this.TSLen = TS1.length();
        this.TS = TS1.toCharArray();
        this.redundancy = new byte[10];
    }
}
