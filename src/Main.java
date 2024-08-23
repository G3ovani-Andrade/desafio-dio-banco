
public class Main
{
	public static void main( String[] args )
	{
		Conta contaCorrente = new ContaCorrente();
		contaCorrente.depositar( 10 );
		contaCorrente.imprimirExtrato();

		Conta contaPoupanca = new ContaPoupanca();
		contaPoupanca.depositar( 100 );
		contaPoupanca.transferir( 10, contaCorrente );
		contaPoupanca.imprimirExtrato();

		Conta contaPoupanca2 = new ContaPoupanca();
		contaPoupanca2.imprimirExtrato();
	}
}
