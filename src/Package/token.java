package Package;

public class token {

    public static enum Token_type {
        sahih, ashar, id , plus, intNumber , floatNumber , minus , leftParanters, rightParantese , semicolon, leftAccolade
        , rightAccolade, divide , lineComment ,multiComment, string , multiple , equal,
        equalTwice, less, lessEqual, more , moreEqual, notEqual,$, err,
        write, read, ifso, selector, loop, until
    }

    public  static  enum ID_type{sahih , ashar, none}
    private  String name;
    private  int blkNum;
    private  int blkOrder;
    private   Token_type  type ;
    private  ID_type idType;


    public token(String name , int blkNum , int blkOrder , Token_type type, ID_type idType){
    this.name = name;
    this.blkNum = blkNum;
    this.idType = idType;
    this.type = type;
    this.blkOrder = blkOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlkNum() {
        return blkNum;
    }

    public void setBlkNum(int blkNum) {
        this.blkNum = blkNum;
    }

    public int getBlkOrder() {
        return blkOrder;
    }

    public void setBlkOrder(int blkOrder) {
        this.blkOrder = blkOrder;
    }

    public Token_type getType() {
        return type;
    }

    public void setType(Token_type type) {
        this.type = type;
    }

    public ID_type getIdType() {
        return idType;
    }

    public void setIdType(ID_type idType) {
        this.idType = idType;
    }
}
