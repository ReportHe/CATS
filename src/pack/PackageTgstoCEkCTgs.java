package pack;

import struct.ArrayLengthMarker;
import struct.StructClass;
import struct.StructField;

@StructClass
public class PackageTgstoCEkCTgs {
    @StructField(order = 0)
    @ArrayLengthMarker(fieldName = "Kcv")
    public int KcvLen;

    @StructField(order = 1)
    public char[] Kcv;

    @StructField(order = 2)
    @ArrayLengthMarker(fieldName = "IDv")
    public int IDvLen;

    @StructField(order = 3)
    public char[] IDv;

    @StructField(order = 4)
    @ArrayLengthMarker(fieldName = "TS")
    public int TSLen;

    @StructField(order = 5)
    public char[] TS;

    @StructField(order = 6)
    @ArrayLengthMarker(fieldName = "TicketV")
    public int TicketVLen;

    @StructField(order = 7)
    public byte[] TicketV;

    @StructField(order = 8)
    public byte[] redundancy;

    public PackageTgstoCEkCTgs(String KeyClientServer_, String IdServer_, String TimeStamp_, byte[] TicketServer_) {
        this.KcvLen = KeyClientServer_.length();
        this.Kcv = IdServer_.toCharArray();
        this.IDvLen = IdServer_.length();
        this.IDv = IdServer_.toCharArray();
        this.TSLen = TimeStamp_.length();
        this.TS = TimeStamp_.toCharArray();
        this.TicketVLen = TicketServer_.length;
        this.TicketV = TicketServer_;
        this.redundancy = new byte[10];
    }
}
