package bd.dbo;

public class Aluno implements Cloneable {
	
	private int ra;
	private String nome;
	private String email;
	private int cep;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	private int numero;

	public int getRa() {
		return this.ra;
	}

	public String getNome() {
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setRa(int ra) throws Exception {
		if (ra <= 0)
			throw new Exception("RA invalido");

		this.ra = ra;
	}

	public void setNome(String nome) throws Exception {
		if (nome == null || nome.equals(""))
			throw new Exception("NOME invalido");

		this.nome = nome;
	}

	public void setEmail(String email) throws Exception {
		if (email == null || email.equals(""))
			throw new Exception("Email invalido");

		this.email = email;
	}
	
	public int getCep() {
			return cep;
	}

	public void setCep(int cep) {
			this.cep = cep;
	}

    public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro= bairro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
    
    public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Aluno(int ra, String nome, String email, int cep, String endereco, String bairro, String cidade, String estado, String complemento, int numero) throws Exception {
		this.setRa(ra);
		this.setNome(nome);
		this.setEmail(email);
		this.setCep(cep);
		this.setEndereco(endereco);
		this.setBairro(bairro);
		this.setEstado(estado);
		this.setCidade(cidade);
		this.setComplemento(complemento);
		this.setNumero(numero);
	}

	public Aluno() {}
		  
	public String toString()
	{
		String ret="";

		ret +="Ra : " + this.ra+"\n";
		ret +="Nome : " + this.nome+"\n";
		ret +="Email : " + this.email+"\n";
		ret +="CEP : " + this.cep+"\n";
		ret +="Endereco: " + this.endereco+"\n";
		ret +="Bairro : " + this.bairro+"\n";
		ret +="Estado :" + this.estado+"\n";
		ret +="Cidade :" + this.cidade+"\n";
		ret +="Complemento :" + this.complemento+"\n";
		ret +="Numero :" + this.numero+"\n";
		return ret;
	}
		  
	public int hashCode ()
	{
		int ret=666;

		ret = ret + Integer.valueOf(this.ra).hashCode();
		ret = ret + this.nome.hashCode();
		ret = ret + this.email.hashCode();
		ret = ret + Integer.valueOf(this.cep).hashCode();
		ret = ret + this.complemento.hashCode();
		ret = ret + this.bairro.hashCode();
		ret = ret + this.estado.hashCode();
		ret = ret + this.cidade.hashCode();
		ret = ret + this.complemento.hashCode();
		ret = ret + Integer.valueOf(this.numero).hashCode();

		return ret;
	}
	 
	public Aluno (Aluno modelo) throws Exception
	{
		this.ra = modelo.ra; // nao clono, pq nao eh objeto
		this.nome = modelo.nome;
		this.email = modelo.email;
        this.cep = modelo.cep;
        this.complemento = modelo.complemento;
        this.bairro = modelo.bairro;
        this.estado = modelo.estado;
        this.cidade = modelo.cidade;
	}
	
	public Object clone ()
	{
		Aluno ret=null;

	    try
	    {
	    	ret = new Aluno (this);
	    }
	    catch (Exception erro)
	    {} // nao trato, pq this nunca � null e construtor de
	           // copia da excecao qdo seu parametro for null

	    return ret;
    }

 }