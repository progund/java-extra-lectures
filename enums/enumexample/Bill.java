package enumexample;

public enum Bill{
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000);
    private int value;
    Bill(int value){
        this.value=value;
    }

    public static void main(String[] args){
        Bill bill = Bill.FIVE_HUNDRED;
    }

}
