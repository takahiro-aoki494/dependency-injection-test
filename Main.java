public class Main {
    // 仮想的なInjector
    public static void main(String[] args) {

        //Paymentに渡すためのオブジェクトを生成
        RaiseTechPayProcessor RaiesTechPay = new RaiseTechPayProcessor();
        TestPaymentProcessor TestPayment = new TestPaymentProcessor();

        //上で生成したオブジェクトを引数にして実際に処理を行うオブジェクトを生成
        Payment raise = new Payment(RaiesTechPay);
        Payment test = new Payment(TestPayment);

        System.out.println("Inject object");
        System.out.println("---------------------------------------");

        System.out.println(raise.paymentProcess(1000));
        System.out.println("---------------------------------------");

        System.out.println(test.paymentProcess(2000));
        System.out.println("---------------------------------------");
    }
}

/*
引数でインターフェース(PaymentProcessor)を実装したクラスをオブジェクト化して持ってくる
Paymentのコンストラクタ内でnewしてオブジェクトを生成していないので
Payment自体に変更を加えることなくRaiesTechPayとTestPaymentを切り替えることができる
 */
class Payment {
    PaymentProcessor pay;

    //コンストラクタ
    public Payment(PaymentProcessor payment) {//
        this.pay = payment;
    }

    public String paymentProcess(int amountToPay) {

        System.out.println("Call makePayment for Class Payment");
        return pay.makePayment(amountToPay);
    }
}

//インターフェース
interface PaymentProcessor {
    String makePayment(int amountToday);
}

//実際の処理を行うクラス
class RaiseTechPayProcessor implements PaymentProcessor {
    @Override
    public String makePayment(int amountToday) {
        return "RaiseTechPayで" + amountToday + "円を⽀払いました。";
    }
}

//実際の処理を行うクラス
class TestPaymentProcessor implements PaymentProcessor {
    @Override
    public String makePayment(int amountToday) {
        return "テスト⽤の⽀払いプロセッサーで" + amountToday + "円を⽀払いました。";
    }
}
