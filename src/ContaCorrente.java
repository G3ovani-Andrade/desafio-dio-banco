
public class ContaCorrente extends Conta
{

	@Override
	public void imprimirExtrato()
	{
		System.out.println( "==== Extrato pra Conta Corrente==== " );
		super.extratoDetalhado();
	}
}
