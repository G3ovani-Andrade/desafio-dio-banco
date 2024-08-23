import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public abstract class Conta implements IConta
{
	protected static int AGENCIA_PADRAO = 0001;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected List<String> extrato;
	@Getter
	protected UUID id;

	public Conta()
	{
		this.extrato = new ArrayList<>();
		this.agencia = AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.id = UUID.randomUUID();
		Banco.contas.add( this );
	}

	@Override
	public void sacar( double valor )
	{
		if ( valor >= this.saldo )
		{
			throw new SaldoNotFoudException( "Saldo insuficicente" );
		}
		this.saldo -= valor;
		this.extrato.add( "Saque no valor de " + valor + " Total da conta " + this.saldo );
	}

	@Override
	public void depositar( double valor )
	{
		if ( valor <= 0 )
		{
			throw new ValorNotValidException( "Valor inválido para depósito" );
		}
		this.saldo += valor;
		this.extrato.add( "Desposito no valor de " + valor + " Total da conta " + this.saldo );
	}

	@Override
	public void transferir( double valor, Conta contaDestino )
	{
		if ( !Util.verificarConta( contaDestino ) )
		{
			throw new EntityNotFoundException( "A conta não existe" );
		}
		if(valor<=0 && valor <= this.saldo) {
			throw new ValorNotValidException( "Valor inválido para transferência" );
		}
		this.saldo -= valor;
		contaDestino.depositar( valor );
		this.extrato.add( "Transferência no valor de " + valor + " Total da conta " + this.saldo );
	}

	protected void extratoDetalhado()
	{
		System.out.println( String.format( "Conta: %d ", this.numero ) );
		System.out.println( String.format( "Agência: %d ", this.agencia ) );
		this.extrato.stream().forEach( e -> System.out.println( e ) );
		System.out.println( "" );
	}

}
