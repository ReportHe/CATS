package pack;

import struct.ArrayLengthMarker;
import struct.StructClass;
import struct.StructField;

@StructClass
public class PackageCtoTgs {
    @StructField(order = 0)
    @ArrayLengthMarker(fieldName = "IDv")
    public int IDvLen; // 服务器标识的长度

    @StructField(order = 1)
    public char[] IDv; // 服务器标识，这里选用服务器的IP地址作为标识

    @StructField(order = 2)
    @ArrayLengthMarker(fieldName = "TicketTgs")
    public int TicketTgsLen; // Client给TGS的票据的长度

    @StructField(order = 3)
    public byte[] TicketTgs; // Client给TGS的票据

    @StructField(order = 4)
    @ArrayLengthMarker(fieldName = "Authenticator")
    public int AuthenticatorLen; // 认证者长度

    @StructField(order = 5)
    public byte[] Authenticator; // 认证者

    @StructField(order = 6)
    public byte[] redundancy; // 冗余

    public PackageCtoTgs(String IDv1, byte[] TicketTgs1, byte[] Authenticator1) {
        this.IDvLen = IDv1.length();
        this.IDv = IDv1.toCharArray();
        this.TicketTgsLen = TicketTgs1.length;
        this.TicketTgs = TicketTgs1;
        this.AuthenticatorLen = Authenticator1.length;
        this.Authenticator = Authenticator1;
        this.redundancy = new byte[10];
    }
}
