public class Util
{
	public static Boolean verificarConta( Conta conta )
	{
			return Banco.contas.stream().anyMatch( c -> c.id.toString().equals( conta.getId().toString() ) );
	}
}
