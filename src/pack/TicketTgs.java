package pack;

import java.security.Key;
import java.security.Timestamp;

import struct.ArrayLengthMarker;
import struct.StructClass;
import struct.StructField;

@StructClass
public class TicketTgs {
    @StructField(order = 0)
    @ArrayLengthMarker(fieldName = "KcTgs")
    public int KeyClientTgsLength; // AS生成的与Client与TGS共享的会话密钥的长度

    @StructField(order = 1)
    public char[] KcTgs; // AS生成的与Client与TGS共享的会话密钥

    @StructField(order = 2)
    @ArrayLengthMarker(fieldName = "IdClient")
    public int IdClientLength; // Client的标识的长度

    @StructField(order = 3)
    public char[] IdClient; // Client的标识，这里选用Client的账号作为标识

    @StructField(order = 4)
    @ArrayLengthMarker(fieldName = "AddressClient")
    public int AddressClientLength; // Client的网络地址的长度

    @StructField(order = 5)
    public char[] AddressClient; // Client的网络地址，防止非法冒用

    @StructField(order = 6)
    @ArrayLengthMarker(fieldName = "IDtgs")
    public int IdTgsLength; // TGS标识的长度

    @StructField(order = 7)
    public char[] IdTgs; // TGS的标识，这里选用TGS的IP地址作为标识

    @StructField(order = 8)
    @ArrayLengthMarker(fieldName = "TS")
    public int TSLen; // 时间戳的长度

    @StructField(order = 9)
    public char[] TS; // 时间戳，告诉Client票据签发的时间

    @StructField(order = 10)
    @ArrayLengthMarker(fieldName = "Lifetime")
    public int LifetimeLen;   //票据的生命期数组长度

    @StructField(order = 11)
    public char[] Lifetime;   //票据的生命期

    public TicketTgs(String KeyClientTgs_, String IdClient_, String AddressClient_, String IdTgs_, String TimeStamp_,
            String LifeTime_) {
        this.KeyClientTgsLength = KeyClientTgs_.length();
        this.KcTgs = KeyClientTgs_.toCharArray();
        this.IdClientLength = IdClient_.length();
        this.IdClient = IdClient_.toCharArray();
        this.AddressClientLength = AddressClient_.length();
        this.AddressClient = AddressClient_.toCharArray();
        this.IdTgsLength = IdTgs_.length();
        this.IdTgs = IdTgs_.toCharArray();
        this.TSLen = TimeStamp_.length();
        this.TS = TimeStamp_.toCharArray();
        this.LifetimeLen = LifeTime_.length();
        this.Lifetime = LifeTime_.toCharArray();
    }
}
