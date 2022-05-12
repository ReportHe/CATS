package pack;

import struct.ArrayLengthMarker;
import struct.StructClass;
import struct.StructField;

@StructClass
public class TicketTgs {
    @StructField(order = 0)
    @ArrayLengthMarker(fieldName = "KcTgs")
    public int KcTgsLen; // AS生成的与Client与TGS共享的会话密钥的长度

    @StructField(order = 1)
    public char[] KcTgs; // AS生成的与Client与TGS共享的会话密钥

    @StructField(order = 2)
    @ArrayLengthMarker(fieldName = "IDc")
    public int IDcLen; // Client的标识的长度

    @StructField(order = 3)
    public char[] IDc; // Client的标识，这里选用Client的账号作为标识

    @StructField(order = 4)
    @ArrayLengthMarker(fieldName = "ADc")
    public int ADcLen;    //Client的网络地址的长度

    @StructField(order = 5)
    public char[] ADc;   //Client的网络地址，防止非法冒用

    @StructField(order = 6)
    @ArrayLengthMarker(fieldName = "IDtgs")
    public int IDtgsLen;   //TGS标识的长度

    @StructField(order = 7)
    public char[] IDtgs;  //TGS的标识，这里选用TGS的IP地址作为标识

    @StructField(order = 8)
    @ArrayLengthMarker(fieldName = "TS")
    public int TSLen;     //时间戳的长度

    @StructField(order = 9)
    public char[] TS;   //时间戳，告诉Client票据签发的时间

    @StructField(order = 10)
    @ArrayLengthMarker(fieldName = "Lifetime")
    public int LifetimeLen;

    @StructField(order = 11)
    public char[] Lifetime;

    public TicketTgs(String KcTgs1, String IDc1, String ADc1, String IDtgs1, String TS1, String Lifetime1) {
        this.KcTgsLen = KcTgs1.length();
        this.KcTgs = KcTgs1.toCharArray();
        this.IDcLen = IDc1.length();
        this.IDc = IDc1.toCharArray();
        this.ADcLen = ADc1.length();
        this.ADc = ADc1.toCharArray();
        this.IDtgsLen = IDtgs1.length();
        this.IDtgs = IDtgs1.toCharArray();
        this.TSLen = TS1.length();
        this.TS = TS1.toCharArray();
        this.LifetimeLen = Lifetime1.length();
        this.Lifetime = Lifetime1.toCharArray();
    }
}
